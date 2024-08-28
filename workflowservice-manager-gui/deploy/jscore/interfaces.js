/* Copyright (c) Ericsson 2014 */

define('jscore/interfaces',[],function () {
    'use strict';
    /**
     *  private method, to make available for tests
     */
    var _private = {};
    /**
     * Underscore each helper
     */
    _private._each = _each;
    function _each(obj, iterator, context) {
        if (obj === null) {
            return;
        }
        if (Array.prototype.forEach && obj.forEach === Array.prototype.forEach) {
            obj.forEach(iterator, context);
        }

    }


    /** Generate Random UID
     *
     * @method guidGenerator
     * @private
     * @return {String}
     * */

    _private.guidGenerator = guidGenerator;
    function guidGenerator() {
        /**
         * @method S4
         * @private
         * @return {String}
         * */
        function S4() {
            /*jshint bitwise: false */
            return  (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }

        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4() + "-" + new Date().getTime());
    }


    /**
     * Underscore extend helper

     */
    _private._objExtend = _objExtend;
    function _objExtend(obj) {
        _each(Array.prototype.slice.call(arguments, 1), function (source) {
            for (var prop in source) {
                if (prop) {
                    obj[prop] = source[prop];
                }
            }
        });


        return obj;
    }

    /**
     * __extend
     * @param protoProps
     * @param staticProps
     * @returns {*}
     * @private
     */
    _private.__extend = __extend;
    function __extend(protoProps, staticProps) {
        /*jshint validthis:true */
        var parent = this;
        var child;

        if (protoProps && Object.prototype.hasOwnProperty.call(protoProps, 'constructor')) {
            child = protoProps.constructor;
        }
        else {
            child = function () {
                parent.apply(this, arguments);
            };
        }

        _objExtend(child, parent, staticProps);


        var Surrogate = function () {
            this.constructor = child;
        };

        Surrogate.prototype = parent.prototype;
        child.prototype = new Surrogate();

        if (protoProps) {
            _objExtend(child.prototype, protoProps);
        }

        child.__super__ = parent.prototype;

        return child;
    }

    /**
     Add to Context
     */
    _private.__AddtoContext = __AddtoContext;
    function __AddtoContext(context) {
        /*jshint validthis:true */
        this.context = context;

    }

    /**
     *
     * @function createDiv
     * @private
     */
    _private._createDiv = _createDiv;
    function _createDiv() {
        return document.createElement('div');
    }

    /**
     * The App interface represents the main controller of the client application.
     *
     * @class interfaces.App
     */
    function App(options) {
        this.options = options || {};
        if (!this.constructor.prototype.uid) {
            this.constructor.prototype.uid = guidGenerator();
        }

        this.init.apply(this, arguments);
        this.constructor.prototype.counter = this.constructor.prototype.counter || 0;
    }

    App.prototype = {

        /**
         * Implement this method instead of using the View property if some data needs to be pre-processed before the View is instantiated. Note that this method must return an instance of the View.
         *
         * @method view
         * @return {View} view
         * @example
         *    view: function() {
         *     return new View(this.options)
         * }
         */

         /**
         * Allows a View to be associated with this object. Must be assigned to the uninitialized View.
         *
         * @property View
         * @type View
         *
         * @example
         *   View: MyView
         */

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs is passed into the constructor then the options variable will have those key/value pairs.
         *
         * @param {Object} options
         * @method init
         */
        init: function (options) {
        },

        /**
         * Call this method to start the app.
         *
         * @method start
         * @param {HTMLElement} container
         * @example
         *    app.start();
         */
        start: function (container) {
            if (this.element === undefined) {
                this.context = this.createContext();
                this._createElement();
                this.onStart();
                this._addToContainer(container);
            }
        },

        /**
         * Implement this method to define how to start the app.
         *
         * @method onStart
         * @example
         *    onStart: function() {
		 *     console.log("app started");
		 * }
         */
        onStart: function () {
        },

        /**
         * Call this method stop the App.
         *
         * @method stop
         * @example
         *    app.stop();
         */
        stop: function () {
            if (this.element !== undefined) {
                this._removeFromContainer();
                this.context.destroyAll();
                delete this.context;
                this.onStop();
            }
        },

        /**
         * Implement this method to define how to stop the app.
         *
         * @method onStop
         * @example
         *    onStop: function() {
		 *     console.log("app stopped");
		 * }
         */
        onStop: function () {
        },

        _createElement: function () {
            this.element = _createDiv();
        },

        /**
         * Implement this method to create an AppContext instance.
         *
         * @method createContext
         * @return {AppContext} context
         *
         * @example
         *    createContext: function() {
		 *     return {
         *         myContextFunction: function(){}
         *     }
		 * }
         */
        createContext: function () {
            return new AppContext();
        },

        /**
         * Returns the instance of the AppContext for the App
         *
         * @method getContext
         * @return {AppContext} context
         *
         * @example
         *    app.getContext();
         */
        getContext: function () {
            return this.context;
        },

        /**
         * Appends the app to a native HTMLElement. Used by container.
         *
         * @method _addToContainer
         * @private
         * @param {HTMLElement} container
         */
        _addToContainer: function (container) {
            container.appendChild(this.element);
        },

        /**
         * Removes the app from the container.
         *
         * @method _removeFromContainer
         * @private
         */
        _removeFromContainer: function () {
            this.element.parentNode.removeChild(this.element);
        },

        /**
         * Get the root HTMLElement of the App.
         *
         * @method getElement
         * @return {HTMLElement} element
         *
         * @example
         *    app.getElement();
         */
        getElement: function () {
            return this.element;
        }

    };

    /**
     * To create an App child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {App} app
     * @example
     *    interfaces.App.extend({
	 *     onStart: function() {
	 *     }
	 * });
     */
    App.extend = __extend;

    /**
     * The UIComponent interface is implemented by UIComponents such as Regions or Widgets that are managed by the App.
     *
     * @class interfaces.UIComponent
     */
    function UIComponent(options) {
        this.options = options = options || {};
        this.uid = guidGenerator();

        if (options.context) {
            __AddtoContext.call(this, options.context);
            delete options.context;
        }
        this.init.apply(this, arguments);
        this._afterInit.apply(this, arguments);

    }

    UIComponent.prototype = {

        _afterInit:function(options){

        },

        /**
         * Implement this method instead of using the View property if some data needs to be pre-processed before the View is instantiated. Note that this method must return an instance of the View.
         *
         * @method view
         * @return {View} view
         * @example
         *    view: function() {
         *     return new View(this.options);
         * }
         */

         /**
         * Allows a View to be associated with this object. Must be assigned to the uninitialized View.
         *
         * @property View
         * @type View
         *
         * @example
         *   View: MyView
         */

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs is passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param options
         */
        init: function (options) {
        },

        /**
         * Sets the root element of the UIComponent.
         *
         * @method setElement
         * @private
         * @param {HTMLElement} element
         */
        setElement: function (element) {
            this.element = element;
        },
        _createElement: function () {
            this.element = _createDiv();
        },
        /**
         * Get the root HTMLElement of the UIComponent.
         *
         * @method getElement
         * @return {HTMLElement} element
         * @example
         *    uicomponent.getElement();
         */
        getElement: function () {
            return this.element;
        },
        destroy: function () {
            var element = this.getElement();
            element.parentNode.removeChild(element);

        }
    };

    /**
     * To create an UIComponent child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {UIComponent} component
     * @example
     *    interfaces.UIComponent.extend({
	 *     init: function() {
	 *     }
	 * });
     */
    UIComponent.extend = __extend;


    /**
     * The AppContext interface represents a utility that the App uses to share a common context between Regions using the sandbox pattern.
     *
     * @class interfaces.AppContext
     */
    function AppContext() {
        this.uid = guidGenerator();
        this._components = {};
        this.init.apply(this, arguments);
    }

    //TODO: need implement method to Remove Specific Component
    AppContext.prototype = {

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs is passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param options
         */
        init: function (options) {
        },

        /**
         * Destroy all registered components.
         *
         * @method destroyAll
         * @private
         */
        destroyAll: function () {
            var components = this._components;
            Object.keys(components).forEach(function (key) {
                components[key].destroy();

            });
        }
    };

    /**
     * To create an AppContext child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {AppContext} context
     */
    AppContext.extend = __extend;


    return {
        App: App,
        UIComponent: UIComponent,
        AppContext: AppContext,
        extend: __extend,
        _private: _private
    };
});

