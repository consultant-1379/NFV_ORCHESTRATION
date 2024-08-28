/* Copyright (c) Ericsson 2014 */

define('jscore/ext/binding',['jscore/core','jscore/ext/utils/base/underscore'],function (core, _) {

    /**
     * Observable represents a value changing over time. The main difference from a typical variable is that you can be notified every time it changes.
     */
    var Observable = function (subscribe) {
        this.subscribe = subscribe;
    };

    Observable.extend = core.extend;

    Observable.prototype = {
        map: function (fn) {
            return new Observable(function (subscriber) {
                this.subscribe(function (val) {
                    subscriber(fn(val));
                });
            }.bind(this));
        }
    };

    /**
     * Creates Observable from an event target and event name. Event target can be any object with addEventHandler method, such as Widget or Model. Subscribers will be called every time a new event is emitted.
     *
     * @private
     * @method eventTargetObservable
     * @param {Object} target
     * @param {String} eventName
     * @returns {Observable} observable
     */
    var eventTargetObservable = function (target, eventName) {
        return new Observable(function (subscriber) {
            target.addEventHandler(eventName, subscriber);
        });
    };

    var Bindable = Observable.extend({

        /**
         * Bindable is a value changing over time which can also be bound to another value, so that they stay in sync.
         *
         * The following options are accepted:
         *   <ul>
         *       <li>get: a function which should return current value this object is wrapping.</li>
         *       <li>set: a function which should set wrapped object's value to the one provided in the first argument.</li>
         *       <li>events: an Observable this bindable will be subscribed to. Every time event is emitted, bindable will get the new value using get method and inform its listeners that the value has been changed.</li>
         *   </ul>
         *
         * //@class ext.binding.Bindable
         * @constructor
         * @param {Object} options
         */
        constructor: function (options) {
            this.subscribers = [];
            this.options = options;
            if (options.events && options.get) {
                this.events = options.events.map(options.get);
                this.trigger(options.get());
                this.events.subscribe(this.trigger.bind(this));
            } else {
                this.events = new Observable(function () {});
            }
        },

        subscribe: function (subscriber) {
            if (_.isFunction(subscriber)) {
                this.events.subscribe(subscriber);
            } else {
                this.subscribers.push(subscriber);
                if (this.value !== undefined) {
                    subscriber.fn(this.value);
                }
            }
        },

        addSyncSource: function (source) {
            source.subscribe({
                source: this,
                fn: function (value) {
                    this.value = value;
                    if (this.options.set) {
                        this.options.set(value);
                    }
                    this.notifyExcept(source);
                }.bind(this)
            });
        },

        trigger: function (newValue) {
            if (this.value !== newValue) {
                this.value = newValue;
                this.notifyExcept();
            }
        },

        notifyExcept: function (ignoredSubscriber) {
            for (var i in this.subscribers) {
                var subscriber = this.subscribers[i];
                if (subscriber.source === ignoredSubscriber) {
                    continue;
                }
                subscriber.fn(this.value);
            }
        },

        /**
         * Binds current object to another Bindable, so that their values stay in sync.
         *
         * @private
         * @method bind
         * @param {Bindable} other
         *
         */
        bind: function (other) {
            other.addSyncSource(this);
            this.addSyncSource(other);
        }
    });

    function modelAttribute (model, attribute) {
        return new Bindable({
            get: function () { return model.get(attribute); },
            set: function (val) { return model.set(attribute, val); },
            events: eventTargetObservable(model, 'change:' + attribute)
        });
    }

    var bindings = {
        text: function (el) {
            return new Bindable({
                set: el.setText.bind(el)
            });
        },

        value: function (el) {
            return new Bindable({
                get: el.getValue.bind(el),
                set: el.setValue.bind(el),
                events: eventTargetObservable(el, 'focusOut change paste cut input')
            });
        }
    };

    /**
     * The ext.binding object provides the ability to bind models to views declaratively.
     * See the {{#crossLink "ext.binding/bind:method"}}{{/crossLink}} method documentation for more details.
     *
     * @class ext.binding
     * @static
     */
    var binding = {
        /**
         * Establishes a two-way binding between model's attribute and a view element according to the binding type. Binding type defines how the data should be bound to the element. The following types are supported:
         * <ul>
         *   <li><strong>text</strong> - binds data to the inner text of an element.</li>
         *   <li><strong>value</strong> - binds data to the value attribute of an input element.</li>
         * </ul>
         *
         * @method bind
         * @static
         * @param {Model} model
         * @param {String} attribute
         * @param {Element} element
         * @param {String} bindingType
         * @example
         *      binding.bindModel(model, 'firstName', element.find('.eaMyApp-MyRegion-firstNameInput'), 'text');
         */
        bindModel: function (model, attribute, element, type) {
            var attributeObservable = modelAttribute(model, attribute);
            var binding = _.isFunction(type) ? type : bindings[type];
            var elementObservable = binding(element);
            attributeObservable.bind(elementObservable);
        },

        /**
         * Renders supplied collection in the placeholderElement and keeps the two in sync. newWidgetCallback will be called for every model and should return a new Widget. Every change in the collection will be reflected in DOM. Pelase bear in mind that changes to the models themselves should be taken care of by the widget returned by newWidgetCallback function.
         * Returns function which you can use to stop binding. This will also empty the placeholderElement.
         *
         * @private
         * @method bindCollection
         * @static
         * @param {Collection} collection
         * @param {Element} placeholderElement
         * @param {Function} createWidgetFunction
         * @return {Function} unbindFunction
         * @example
         *   binding.binbindCollection(collection, this.getElement(), function (model) {
         *       return new MyWidget({model: model});
         *  });
         */
        bindCollection: function (collection, placeholderElement, newWidgetCallback) {
            var _events = {};
            var _models = {};

            var addModel = function (model) {
                var widget = _models[model.cid];
                if (!widget) {
                    widget = newWidgetCallback(model);
                    _models[model.cid] = widget;
                }
                widget.attachTo(placeholderElement);
            };

            var addAll = function () {
                collection.each(addModel);
            };

            var removeModel = function (model) {
                _models[model.cid].destroy();
                delete _models[model.cid];
            };

            var removeAll = function () {
                for (var cid in _models) {
                    _models[cid].destroy();
                    delete _models[cid];
                }
            };

            addAll();

            _events.add = collection.addEventHandler("add", addModel);
            _events.remove = collection.addEventHandler("remove", removeModel);
            _events.reset = collection.addEventHandler("reset", function () {
                setData(collection);
            });
            _events.sort = collection.addEventHandler("sort", function () {
                for (var cid in _models) {
                    _models[cid].detach();
                }
                addAll();
            });

            return function () {
                for (var event in _events) {
                    collection.removeEventHandler(event, _events[event]);
                }
                removeAll();
            };
        },

        Bindable: Bindable,
        modelAttribute: modelAttribute,

        /**
         * Wraps an input element into Bindable. Subscribers are going to be notified on every input modification, while external events will update input element value.
         *
         * @private
         * @method value
         * @static
         */
        value: bindings.value,

        /**
         * Wraps an element text content into Bindable. The bindable of this type doesn't emit any events, but will respond to external events by updating text content.
         *
         * @private
         * @method text
         * @static
         */
        text: bindings.text,


        eventTargetObservable: eventTargetObservable
    };

    binding.bind = binding.bindModel;

    return binding;

});

