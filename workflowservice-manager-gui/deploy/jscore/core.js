/* Copyright (c) Ericsson 2014 */

define('jscore/ext/utils/base/trigger',[],function () {
    'use strict';

    /**
     Event type mappings.
     This is not an exhaustive list.
     */
    var eventTypes = {
        load:        'HTMLEvents',
        unload:      'HTMLEvents',
        abort:       'HTMLEvents',
        error:       'HTMLEvents',
        select:      'HTMLEvents',
        change:      'HTMLEvents',
        submit:      'HTMLEvents',
        reset:       'HTMLEvents',
        focus:       'HTMLEvents',
        blur:        'HTMLEvents',
        resize:      'HTMLEvents',
        scroll:      'HTMLEvents',
        input:       'HTMLEvents',

        keyup:	     'KeyboardEvent',
        keydown:	 'KeyboardEvent',

        click:       'MouseEvents',
        dblclick:    'MouseEvents',
        mousedown:   'MouseEvents',
        mouseup:     'MouseEvents',
        mouseover:   'MouseEvents',
        mousemove:   'MouseEvents',
        mouseout:    'MouseEvents',
        contextmenu: 'MouseEvents'
    };

// Default event properties:
    var defaults = {
        clientX:    0,
        clientY:    0,
        button:     0,
        ctrlKey:    false,
        altKey:     false,
        shiftKey:   false,
        metaKey:    false,
        bubbles:    true,
        cancelable: true,
        view:       document.defaultView,
        key:        '',
        location:   0,
        modifiers:  '',
        repeat:     0,
        locale:     ''
    };

    /**
     * Trigger a DOM event.
     *
     *    trigger(document.body, "click", {clientX: 10, clientY: 35});
     *
     * Where sensible, sane defaults will be filled in.  See the list of event
     * types for supported events.
     *
     * Loosely based on:
     * https://github.com/kangax/protolicious/blob/master/event.simulate.js
     */
    function trigger(el, name, options){
        var event, type;

        options = options || {};
        for (var attr in defaults) {
            if (!options.hasOwnProperty(attr)) {
                options[attr] = defaults[attr];
            }
        }

        if (document.createEvent) {
            // Standard Event
            type = eventTypes[name] || 'CustomEvent';
            event = document.createEvent(type);
            initializers[type](el, name, event, options);
            el.dispatchEvent(event);
        } else {
            // IE Event
            event = document.createEventObject();
            for (var key in options){
                event[key] = options[key];
            }
            el.fireEvent('on' + name, event);
        }
    }

    var initializers = {
        HTMLEvents: function(el, name, event, o){
            return event.initEvent(name, o.bubbles, o.cancelable);
        },

        KeyboardEvent: function(el, name, event, o){
            // Use a blank key if not defined and initialize the charCode
            var key = ('key' in o) ? o.key : "";
            var charCode;
            var modifiers;

            // 0 is the default location
            var location = ('location' in o) ? o.location : 0;

            if (event.initKeyboardEvent) {
                // Chrome and IE9+ uses initKeyboardEvent
                if (! 'modifiers' in o) {
                    modifiers = [];
                    if (o.ctrlKey) modifiers.push("Ctrl");
                    if (o.altKey) modifiers.push("Alt");
                    if (o.ctrlKey && o.altKey) modifiers.push("AltGraph");
                    if (o.shiftKey) modifiers.push("Shift");
                    if (o.metaKey) modifiers.push("Meta");
                    modifiers = modifiers.join(" ");
                } else {
                    modifiers = o.modifiers;
                }

                return event.initKeyboardEvent(
                    name, o.bubbles, o.cancelable, o.view,
                    key, location, modifiers, o.repeat, o.locale
                );
            } else {
                // Mozilla uses initKeyEvent
                charCode = ('charCode' in o) ? o.charCode : key.charCodeAt(0) || 0;
                return event.initKeyEvent(
                    name, o.bubbles, o.cancelable, o.view,
                    o.ctrlKey, o.altKey, o.shiftKey,
                    o.metaKey, charCode, key
                );
            }
        },

        MouseEvents: function(el, name, event, o){
            var screenX = ('screenX' in o) ? o.screenX : o.clientX;
            var screenY = ('screenY' in o) ? o.screenY : o.clientY;
            var clicks;
            var button;

            if ('detail' in o) {
                clicks = o.detail;
            } else if (name === 'dblclick') {
                clicks = 2;
            } else {
                clicks = 1;
            }

            // Default context menu to be a right click
            if (name === 'contextmenu') {
                button = button = o.button || 2;
            }

            return event.initMouseEvent(name, o.bubbles, o.cancelable, o.view,
                clicks, screenX, screenY, o.clientX, o.clientY,
                o.ctrlKey, o.altKey, o.shiftKey, o.metaKey, button, el);
        },

        CustomEvent: function(el, name, event, o){
            return event.initCustomEvent(name, o.bubbles, o.cancelable, o.detail);
        }
    };
    return trigger;
});

define('jscore/ext/validationPatterns',[],{'alphanumeric':{'title':'Text with only alpha numeric chars','pattern':'^[a-zA-Z0-9\\s]+$'},'integer':{'title':'Should be an integer','pattern':'^-?[0-9]+$'},'integerPositive':{'title':'Should be a positive integer','pattern':'^\\+?[0-9]+$'},'ipv4':{'title':'This should be a valid IPv4 Address','pattern':'((^|\\.)((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]?\\d))){4}$'},'ipv6':{'title':'This should be a valid IPv6 Address','pattern':'((^\\s*((([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))\\s*$)|(^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$))|(^\\s*((?=.{1,255}$)(?=.*[A-Za-z].*)[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?)*)\\s*$)'},'number':{'title':'Should be an integer or a decimal','pattern':'^[-\\+]?[0-9]+([\\.,][0-9][0-9]*)?$'}});

define('jscore/ext/dom',['jscore/base/jquery','jscore/ext/validationPatterns','jscore/ext/utils/base/trigger'],function ($, validationPatterns, trigger) {
    'use strict';

    function _parse() {
        /*jshint validthis:true */
        return $.parseHTML.apply(this, arguments)[0];
    }

    function getResult(el) {
        var $el = $(el);
        var result = $el.data('element');

        if (!result) {
            result = Element.wrap(el);
            $el.data('element', result);
        }
        return result;
    }

    function _addModifiers(element) {
        var $el = $(element._getHTMLElement());

        var classes = $el.attr("class");
        if (classes !== undefined) {

            classes = classes.split(" ");

            if (!element._modifiersInitialised) {
                element._modifiers = {};

                var regex = new RegExp("(?:([a-zA-Z0-9-]+))(?:_([a-zA-Z0-9]+))?(?:_[a-zA-Z0-9]+)?");
                for (var i = 0; i < classes.length; i++) {
                    var matches = classes[i].match(regex);
                    if (matches) {
                        if (i === 0) {
                            element._modifierPrefix = matches[1];
                        }
                        if (matches[2]) {
                            element._modifiers[matches[1] + "_" + matches[2]] = matches[0];
                        }
                    }
                }

                element._modifiersInitialised = true;
            }
        }
    }

    /**
     * The Element is a common abstraction of the HTML DOM element.
     * If the tag name is not provided a default div tag is set
     *
     * @param {String} [tagName]
     * @class ext.dom.Element
     * @example
     * var divElt = new core.Element();
     * var inputElt = new core.Element('input');
     */
    function Element(tagName) {
        this._setHTMLElement(document.createElement(tagName || 'div'));
    }

    Element.prototype = {
        /**
         * Find an Element within the scope of the Element using a CSS selector String.
         *
         * @method find
         * @param {String} selector
         * @return {Element} element
         * @example
         *    element.find(".MyClass");
         */
        find: function (selector) {
            return dom.find(selector, this);
        },

        /**
         * Returns the value of the DOM element.
         *
         * @method getValue
         * @return {String} value
         * @example
         *    var val = element.getValue();
         */
        getValue: function () {
            return dom.getValue(this);
        },

        /**
         * Sets the value of the DOM element
         *
         * @method setValue
         * @param {any} value
         * @example
         *    element.setValue("someValue");
         */
        setValue: function (value) {
            dom.setValue(this, value);
        },

        /**
         * Appends a child Element to the Element.
         *
         * @method append
         * @param {Element} child
         * @example
         *    element.append(childElement);
         */
        append: function (child) {
            dom.append(this, child);
        },

        /**
         * Removes the Element from the DOM.
         *
         * @method remove
         * @example
         *    element.remove();
         */
        remove: function () {
            dom.remove(this);
        },
        /**
         * Detach the Element from the DOM.
         *
         * @method detach
         * @example
         *    element.detach();
         */
        detach: function () {
            dom.detach(this);
        },
        /**
         * Finds all child Elements of the Element.
         *
         * @method children
         * @return {Element[]}elements
         * @example
         *    element.children();
         */
        children: function () {
            return dom.children(this);
        },

        /**
         * Sets up a DOM event handler for the Element. Event is passed into the callback, and contains "originalEvent" and "preventDefault".
         *
         * @method addEventHandler
         * @param {String} event
         * @param {Function} callback
         * @param {Object} [context]
         * @return {String} eventID

         * @example
         * element.addEventHandler("click", function() {
		 *     console.log("element clicked!");
		 * });
         */
        addEventHandler: function (event, callback, context) {
            return dom.addEventHandler(this, event, callback, context);
        },
        /**
         * Removes a DOM event handler for the element.
         *
         * @method removeEventHandler
         * @param {Object} eventObject

         * @example
         *    element.removeEventHandler(eventId);
         */
        removeEventHandler: function (eventObject) {
            dom.removeEventHandler(eventObject);
        },
        /**
         * Trigger a DOM event handler for the Element.
         *
         * @method trigger
         * @param {String} event
         * @param {Object} options
         * @example
         *    element.trigger("click");
         */
        trigger: function (event, options) {
            dom.trigger(this, event, options);
        },

        /**
         * Returns the text content of the DOM element.
         *
         * @method getText
         * @return {String} text
         * @example
         *    var text = element.getText();
         */
        getText: function () {
            return dom.getText(this);
        },

        /**
         * Sets the text content of the DOM element.
         *
         * @method setText
         * @param {String} text
         * @example
         *    element.setText("someText");
         */
        setText: function (text) {
            dom.setText(this, text);
        },

        /**
         * Attributes may be defined in static HTML or using the setAttribute() method.
         * The getAttribute() method gets the value of the specified attribute of the DOM element.
         * To access and modify DOM properties such as the checked, selected, or disabled state of form elements,
         * use the <a href="#methods_setProperty">setProperty()</a> and <a href="#methods_getProperty">getProperty()</a> methods.
         *
         * @method getAttribute
         * @param {String} attr
         * @return {String} value
         * @example
         *    var attr = element.getAttribute("data-someAttr");
         */
        getAttribute: function (attr) {
            return dom.getAttribute(this, attr);
        },

        /**
         * Attributes may be defined in static HTML or using the setAttribute() method.
         * The setAttribute() method may also be used to change an attributes value.
         * To access and modify DOM properties such as the checked, selected, or disabled state of form elements,
         * use the <a href="#methods_setProperty">setProperty()</a> and <a href="#methods_getProperty">getProperty()</a> methods.
         *
         * @method setAttribute
         * @param {String} attr
         * @param {String} value
         * @example
         *    element.setAttribute("data-someAttr", "value");
         */
        setAttribute: function (attr, value) {
            dom.setAttribute(this, attr, value);
        },

        /**
         * Attributes may be defined in static HTML or using the setAttribute() method.
         * The removeAttribute() method is to remove the attribute from the element.
         * To access and modify DOM properties such as the checked, selected, or disabled state of form elements,
         * use the <a href="#methods_setProperty">setProperty()</a> and <a href="#methods_getProperty">getProperty()</a> methods.
         *
         * @method removeAttribute
         * @param {String} attr
         * @example
         *    dom.removeAttribute("someAttr");
         */
        removeAttribute: function (attr) {
            dom.removeAttribute(this, attr);
        },

        /**
         * Gets the value of the property for the DOM element.
         * To retrieve, change or remove DOM attributes such as data-attributes that are part of the HTML mark-up,
         * use the <a href="#methods_getAttribute">getAttribute()</a>, <a href="#methods_setAttribute">setAttribute()</a>
         * and <a href="#methods_removeAttribute">removeAttribute()</a> methods.
         *
         * @method getProperty
         * @param {String} prop
         * @return {Any} value
         * @example
         *    var prop = element.getProperty("checked");
         */
        getProperty: function (prop) {
            return dom.getProperty(this, prop);
        },

        /**
         * Sets the property for the DOM element equal to the value.
         * To retrieve, change or remove DOM attributes such as data-attributes that are part of the HTML mark-up,
         * use the <a href="#methods_getAttribute">getAttribute()</a>, <a href="#methods_setAttribute">setAttribute()</a>
         * and <a href="#methods_removeAttribute">removeAttribute()</a> methods.
         *
         * @method setProperty
         * @param {String} prop
         * @param {Any} value
         * @example
         *    element.setProperty("checked", true);
         */
        setProperty: function (prop, value) {
            dom.setProperty(this, prop, value);
        },

        /**
         * Gets the value of the specified inline style for the element.
         *
         * @method getStyle
         * @param {String} styleName
         * @return {String} value
         *
         * @example
         *    element.getStyle("background-color");
         */
        getStyle: function (styleName) {
            return dom.getStyle(this, styleName);
        },

        /**
         * Sets the inline style values for the element.
         * If an object is passed as a first parameter, that object will be used to set the styles.
         * If the first parameter is a string (the name of the property), then the second parameter should be a string with the value for the property.
         *
         * @method setStyle
         * @param {Object/String} styles
         * @param {String} [value]
         *
         * @example
         * element.setStyle({
		 *     "background-color": "blue"
		 * });
         *
         * element.setStyle("background-color", "blue");
         */
        setStyle: function (styles, value) {
            dom.setStyle(this, styles, value);
        },

        /**
         * Remove the inline style values for the element providing the name of the CSS property
         *
         * @method removeStyle
         * @param {String} styles
         *
         * @example
         * dom.removeStyle("background-color");
         */
        removeStyle: function (styles) {
            dom.removeStyle(this, styles);
        },

        /**
         * Adds a CSS Class to the element in the following form prefix&#95;key&#95;value (If no value is specified, the class will be prefix_key). If the
         * element already has a CSS class with the modifier, it is replaced instead. Prefix is equal to the existing class name the element already has.
         * If the element does not have a class name, this function will throw an error. A custom prefix can be provided as a third parameter.
         * For more information about modifiers, please check the CSS style guide <a href="../../guidelines/css.html">here</a>.
         *
         * @method setModifier
         * @param {String} key
         * @param {String} [value]
         * @param {String} [prefix]
         *
         * @example
         * element.setModifier("disabled");
         * element.setModifier("opacity", "80");
         * element.setModifier("disabled", "", "ebBtn");
         */
        setModifier: function (key, value, prefix) {
            dom.setModifier(this, key, value, prefix);
        },

        /**
         * Return if the element has any class name that has been created by the setModifier function. A prefix can be provided as a second parameter to detect a modifier
         * which starts with a different prefix.
         *
         * @method hasModifier
         * @param {String} key
         * @param {String} [value]
         * @param {String} [prefix]
         *
         * @example
         *  element.hasModifier("disabled");
         *  element.hasModifier("disabled", "ebBtn");
         *  element.hasModifier("color", "blue", "ebBtn");
         */
        hasModifier: function (key, value, prefix) {
            return dom.hasModifier(this, key, value, prefix);
        },

        /**
         * Removes any class name that has been created by the setModifier function. A prefix can be provided as a second parameter to remove a modifier
         * which starts with a different prefix.
         *
         * @method removeModifier
         * @param {String} key
         * @param {String} [prefix]
         *
         * @example
         *    element.removeModifer("disabled");
         */
        removeModifier: function (key, prefix) {
            dom.removeModifier(this, key, prefix);
        },

        /**
         * Returns true if the passed element is a child element of this element, false otherwise.
         *
         * @method contains
         * @beta
         * @param {Element} child
         * @return {Boolean} result
         *
         * @example
         *    element.contains(child);
         */
        contains: function (child) {
            return dom.contains(this, child);
        },

        /**
         * The returned value is read-only left, top, right and bottom properties describing the border-box in pixels. top and left are relative to the top-left of the viewport.
         *
         * @method getPosition
         * @beta
         * @return {Object} top/left
         */
        getPosition: function () {
            return dom.getPosition(this);
        },
        /**
         * Method sets focus on the specified element, if it can be focused.
         *
         * @method focus
         * @beta
         */
        focus: function(){
            dom.focus(this);
        },

        /**
         * Set the validation constraints for the input (text, url, email or password) elements ONLY.
         * <br/>
         * Custom pattern {Object}:
         *   <ul>
         *     <li>title: [option] The text displayed in case of input error</li>
         *     <li>pattern: The pattern used to validate the input data</li>
         *   </ul>
         * Supported types {String}:
         *   <ul>
         *     <li>alphanumeric: Only allows alphanumeric chars to be submitted</li>
         *     <li>integer: Only allows integer</li>
         *     <li>integerPositive:  Only allows positive integers to be submitted</li>
         *     <li>ipv4:  Only allows the IPv4 format to be submitted</li>
         *     <li>ipv6:  Only allows the IPv6 format to be submitted</li>
         *     <li>number:  Only allows numbers (positive and negative; integers and decimals) to be submitted</li>
         *   </ul>
         *
         * @method setValidator
         * @beta
         * @param {Object|String} options
         * @param {Boolean} required, define if the input must be filled to be valid, default is true
         * @example
         *    // custom
         *    element.setValidator({
         *        title:'Lower case letters only',
         *        pattern: '[a-z]+'
         *    });
         *    // supported
         *    element.setValidator('alphanumeric');
         */
        setValidator: function (options, required) {
            dom.setValidator(this, options, required);
        },

        _getHTMLElement: function () {
            return this.element;
        },

        _setHTMLElement: function (el) {
            this.element = el;
            this._modifiersInitialised = false;

            _addModifiers(this);

        }

    };

    /**
     * Parses a HTML String and creates an Element from it.
     *
     * @method parse
     * @static
     * @param {String} html
     * @return {Element} element
     *
     * @example
     *    var element = core.Element.parse("<div></div>");
     */
    Element.parse = function (html) {
        var element = new Element();
        element._setHTMLElement(_parse(html));
        return element;
    };

    /**
     * Wraps a native element and creates an Element.
     *
     * @method wrap
     * @static
     * @param {HTMLElement} element
     * @return {Element} element
     *
     * @example
     * var nativeElement = document.createElement("div");
     * var element = core.Element.wrap(nativeElement);
     */
    Element.wrap = function (element) {
        var wrappedElement = new Element();
        wrappedElement._setHTMLElement(element);
        return wrappedElement;
    };
    /**
     * The ext.dom package provides an abstract API for DOM manipulation.
     *
     * @class ext.dom
     */
    var dom = {
        /**
         * Access the ext.dom.Element object
         *
         * @property Element
         * @type Element
         */
        Element: Element,

        /**
         * Find an Element within the scope of the provided Element using a CSS selector String.
         *
         * @method find
         * @static
         * @param {String} selector
         * @param {Element} element
         * @return {Element} element
         *
         * @example
         *    dom.find(".MyClass", element);
         */
        find: function (selector, element) {
            if (!element) return;
            var newElement = $(element._getHTMLElement()).find(selector)[0];
            if (newElement !== undefined) {
                return getResult(newElement);
            }
        },

        /**
         * Sets the value of the DOM element.
         *
         * @method setValue
         * @static
         * @param {Element} element
         * @param {any} value
         * @example
         *    dom.setValue(element, "someValue");
         */
        setValue: function (element, value) {
            $(element._getHTMLElement()).val((value !== null && value !== undefined) ? value : '');
        },

        /**
         * Returns the value of the DOM element.
         *
         * @method getValue
         * @static
         * @param {Element} element
         * @return {String} value
         *
         * @example
         *    var val = dom.getValue(element);
         */
        //TODO: this method need remove, and move for input fields
        getValue: function (element) {
            return $(element._getHTMLElement()).val();
        },

        /**
         * Appends child element to the target element.
         *
         * @method append
         * @static
         * @param {Element} parent
         * @param {Element} child
         *
         * @example
         *    dom.append(parentElement, childElement);
         */
        append: function (parent, child) {
            $(parent._getHTMLElement()).append(child._getHTMLElement());
        },
        /**
         * Removes the element from the DOM.
         *
         * @method remove
         * @static
         * @param {Element} element
         *
         * @example
         *    dom.remove(element);
         */
        remove: function (element) {
            $(element._getHTMLElement()).remove();

        },
        /**
         * Detaches the element from the DOM.
         *
         * @method detach
         * @static
         * @param {Element} element
         *
         * @example
         *    dom.detach(element);
         */
        detach: function (element) {
            $(element._getHTMLElement()).detach();
        },
        /**
         * Finds all child elements of the element.
         *
         * @method children
         * @static
         * @param {Element} element
         * @return {Element[]} elements
         *
         * @example
         *    dom.children(element);
         */
        //TODO: Review this method
        children: function (element) {
            var $el = $(element._getHTMLElement()).children();
            var result = [];
            for (var i = 0; i < $el.length; i++) {
                result.push(getResult($el[i]));
            }

            return result;

        },

        /**
         * Returns the text content of the DOM element.
         *
         * @method getText
         * @param {Element} element
         * @return {String} text
         * @example
         *    var text = dom.getText(element);
         */
        getText: function (element) {
            return $(element._getHTMLElement()).text();
        },

        /**
         * Sets the text content of the DOM element.
         *
         * @method setText
         * @static
         * @param {Element} element
         * @param {String} text
         * @example
         *    dom.setText(element, "someText");
         */
        setText: function (element, text) {
            $(element._getHTMLElement()).text((text !== null && text !== undefined) ? text : '');
        },

        /**
         * Attributes may be defined in static HTML or using the setAttribute() method.
         * The getAttribute() method gets the value of the specified attribute of the DOM element.
         * To access and modify DOM properties such as the checked, selected, or disabled state of form elements,
         * use the <a href="#methods_setProperty">setProperty()</a> and <a href="#methods_getProperty">getProperty()</a> methods.
         *
         * @method getAttribute
         * @static
         * @param {Element} element
         * @param {String} attr
         * @return {String} value
         * @example
         *    var attr = dom.getAttribute(element, "data-someAttr");
         */
        getAttribute: function (element, attr) {
            return $(element._getHTMLElement()).attr(attr);
        },

        /**
         * Attributes may be defined in static HTML or using the setAttribute() method.
         * The setAttribute() method may also be used to change an attributes value.
         * To access and modify DOM properties such as the checked, selected, or disabled state of form elements,
         * use the <a href="#methods_setProperty">setProperty()</a> and <a href="#methods_getProperty">getProperty()</a> methods.
         *
         * @method setAttribute
         * @static
         * @param {Element} element
         * @param {String} attr
         * @param {String} value
         * @example
         *    dom.setAttribute(element, "data-someAttr", "value");
         */
        setAttribute: function (element, attr, value) {
            $(element._getHTMLElement()).attr(attr, value);
        },

        /**
         * Attributes may be defined in static HTML or using the setAttribute() method.
         * The removeAttribute() method removes the specified attribute from the DOM element.
         * To access and modify DOM properties such as the checked, selected, or disabled state of form elements,
         * use the <a href="#methods_setProperty">setProperty()</a> and <a href="#methods_getProperty">getProperty()</a> methods.
         *
         * @method removeAttribute
         * @static
         * @param {Element} element
         * @param {String} attr
         * @example
         *    dom.removeAttribute(element, "someAttr");
         */
        removeAttribute: function (element, attr) {
            $(element._getHTMLElement()).removeAttr(attr);
        },

        /**
         * Gets the value of the property for the DOM element.
         * To retrieve, change or remove DOM attributes such as data-attributes that are part of the HTML mark-up,
         * use the <a href="#methods_getAttribute">getAttribute()</a>, <a href="#methods_setAttribute">setAttribute()</a>
         * and <a href="#methods_removeAttribute">removeAttribute()</a> methods.
         *
         * @method getProperty
         * @static
         * @param {Element} element
         * @param {String} prop
         * @return {Any} value
         * @example
         *    var prop = dom.getProperty(element, "checked");
         */
        getProperty: function (element, prop) {
            return $(element._getHTMLElement()).prop(prop);
        },

        /**
         * Sets the property for the DOM element equal to the value.
         * To retrieve, change or remove DOM attributes such as data-attributes that are part of the HTML mark-up,
         * use the <a href="#methods_getAttribute">getAttribute()</a>, <a href="#methods_setAttribute">setAttribute()</a>
         * and <a href="#methods_removeAttribute">removeAttribute()</a> methods.
         *
         * @method setProperty
         * @static
         * @param {Element} element
         * @param {String} prop
         * @param {Any} value
         * @example
         *    dom.setProperty(element, "checked", true);
         */
        setProperty: function (element, prop, value) {
            $(element._getHTMLElement()).prop(prop, value);
        },

        /**
         * Sets up a DOM event handler for an element. Event is passed into the callback, and contains "originalEvent" and "preventDefault".
         *
         * @method addEventHandler
         * @static
         * @param {String} event
         * @param {Object} core.Element
         * @param {Function} callback
         * @param {Object} [context]
         * @return {Object} eventId
         *
         * @example
         * dom.addEventHandler("click", element, function() {
		 *     console.log("element clicked!");
		 * });
         */
        addEventHandler: function (element, event, callback, context) {
            if (!context) {
                context = this;
            }

            var wrappedCallback = function (e) {
                function preventDefault() {
                    e.preventDefault();
                }

                function stopPropagation() {
                    e.stopPropagation();
                }

                var wrappedEvent = {
                    originalEvent: e,
                    preventDefault: preventDefault,
                    stopPropagation: stopPropagation
                };

                callback.call(context, wrappedEvent);
            };

            var nativeElement = element._getHTMLElement();
            var events = event.split(' ');
            events.forEach(function (evt) {
                nativeElement.addEventListener(evt, wrappedCallback);
            });

            return {
                destroy: function () {
                    events.forEach(function (evt) {
                        nativeElement.removeEventListener(evt, wrappedCallback);
                    });
                }
            };
        },
        /**
         * Removes a DOM event handler for the element.
         *
         * @method removeEventHandler
         * @static
         * @param {Object} eventId
         *
         * @example
         *    dom.removeEventHandler(eventIdString, element);
         */

        removeEventHandler: function (eventObject) {
            eventObject.destroy();
        },
        /**
         * Trigger a DOM event handler for the element.
         *
         * @method trigger
         * @static
         * @param {Element} element
         * @param {String} event
         * @param {Object} options
         * @example
         *    dom.trigger("click", element);
         */
        trigger: function (element, event, options) {
            trigger(element._getHTMLElement(), event, options);
        },

        /**
         * Gets the value of the specified inline style for the element.
         *
         * @method getStyle
         * @static
         * @param {Element} element
         * @param {String} styleName
         * @return {String} value
         *
         * @example
         *    dom.getStyle(element, "background-color");
         */
        getStyle: function (element, styleName) {
            return $(element._getHTMLElement()).css(styleName);
        },

        /**
         * Sets the inline style values for the element. If an object is passed as a second parameter, that object will be used to set the styles. If the second parameter is a string (the name of the property), then the third parameter should be a string with the value for the property.
         *
         * @method setStyle
         * @static
         * @param {Element} element
         * @param {Object/String} styles
         * @param {String} [value]
         *
         * @example
         * dom.setStyle(element, {
		 *     "background-color": "blue"
		 * });
         *
         * dom.setStyle(element, "background-color", "blue");
         */
        setStyle: function (element, styles, value) {
            if (value !== undefined) {
                $(element._getHTMLElement()).css(styles, value);
            } else {
                $(element._getHTMLElement()).css(styles);
            }
        },

        /**
         * Remove the inline style values for the element. The second argument is the name of the CSS property
         *
         * @method removeStyle
         * @static
         * @param {Element} element
         * @param {String} styles
         *
         * @example
         * dom.removeStyle(element, "background-color");
         */
        removeStyle: function (element, styles) {
            $(element._getHTMLElement()).css(styles, '');
        },

        /**
         * Adds a CSS Class to the element in the following form prefix&#95;key&#95;value (If no value is specified, the class will be prefix_key). If the
         * element already has a CSS class with the modifier, it is replaced instead. Prefix is equal to the existing class name the element already has.
         * If the element does not have a class name, this function will throw an error. A custom prefix can be provided as a third parameter. For more information about modifiers, please check the CSS
         * style guide <a href="../../guidelines/css.html">here</a>.
         *
         * @method setModifier
         * @static
         * @param {Element} element
         * @param {String} key
         * @param {String} [value]
         * @param {String} [prefix]
         *
         * @example
         * dom.setModifier(element, "disabled");
         * dom.setModifier(element, "opacity", "80");
         * dom.setModifier(element, "disabled", "", "ebBtn");
         */
        setModifier: function (element, key, value, prefix) {

            if ((key && key.indexOf(" ") >= 0) || (value && value.indexOf(" ") >= 0)) {
                throw new Error("Key/Value in modifier should not contain whitespace");
            }

            if (element._modifiers === undefined) {
                element._modifiers = {};
            }

            var $el = $(element._getHTMLElement());

            _addModifiers(element);

            if (element._modifierPrefix !== undefined || prefix) {
                var fullPrefix = ((prefix && prefix !== "") ? prefix : element._modifierPrefix) + "_" + key;

                var fullModifier = fullPrefix;
                if (value && value !== "") {
                    fullModifier += "_" + value;
                }
                if (element._modifiers[fullPrefix]) {
                    $el.removeClass(element._modifiers[fullPrefix]);
                }
                $el.addClass(fullModifier);

                element._modifiers[fullPrefix] = fullModifier;
            } else {
                throw new Error("Element does not have a class.");
            }
        },

        /**
         * Return if the element has any class name that has been created by the setModifier function. A prefix can be provided as a second parameter to detect a modifier
         * which starts with a different prefix.
         *
         * @method hasModifier
         * @static
         * @param {Element} element
         * @param {String} key
         * @param {String} [prefix]
         *
         * @example
         *    dom.hasModifier(element, "disabled");
         *    dom.hasModifier(element, "disabled", "ebBtn");
         *    dom.hasModifier("color", "blue", "ebBtn");
         */
        hasModifier: function (element, key, value, prefix) {
            var fullPrefix = ((prefix && prefix !== "") ? prefix : element._modifierPrefix) + "_" + key;
            return (element._modifiers && element._modifiers[fullPrefix] !== undefined && (!value || (value !== undefined && element._modifiers[fullPrefix] === (fullPrefix + '_' + value))));
        },

        /**
         * Removes any class name that has been created by the setModifier function. A prefix can be provided as a second parameter to remove a modifier
         * which starts with a different prefix.
         *
         * @method removeModifier
         * @static
         * @param {Element} element
         * @param {String} key
         * @param {String} [value]
         * @param {String} [prefix]
         *
         * @example
         *  dom.hasModifier("disabled");
         *  dom.hasModifier("disabled", "ebBtn");
         *  dom.hasModifier("color", "blue", "ebBtn");
         */
        removeModifier: function (element, key, prefix) {
            var $el = $(element._getHTMLElement());
            var fullPrefix = ((prefix && prefix !== "") ? prefix : element._modifierPrefix) + "_" + key;

            if (element._modifiers && element._modifiers[fullPrefix]) {
                $el.removeClass(element._modifiers[fullPrefix]);
                delete element._modifiers[fullPrefix];
            }
        },

        /**
         * Returns true if the passed element is a child element of the parent element, false otherwise.
         *
         * @method contains
         * @beta
         * @param {Element} element
         * @param {Element} child
         * @return {Boolean} result
         */
        contains: function (element, child) {
            return element._getHTMLElement().contains(child._getHTMLElement());
        },

        /**
         * The returned value is read-only left, top, right and bottom properties describing the border-box in pixels. top and left are relative to the top-left of the viewport.
         *
         * @method getPosition
         * @beta
         * @param {Element} element
         * @return {Object} top/right/bottom/left
         */
        getPosition: function (element) {
            var rect = element._getHTMLElement().getBoundingClientRect();
            return {
                top: rect.top,
                right: rect.right,
                bottom: rect.bottom,
                left: rect.left
            };
        },

        /**
         * Method sets focus on the specified element, if it can be focused.
         *
         * @method focus
         * @beta
         * @param {Element} element
         */
        focus: function(element){
            element._getHTMLElement().focus();
        },

        /**
         * Set the validation constraints for the input (text, url, email or password) elements ONLY.
         * <br/>
         * Custom pattern {Object}:
         *   <ul>
         *     <li>title: [optional] The text displayed in case of input error</li>
         *     <li>pattern: The pattern used to validate the input data</li>
         *   </ul>
         * Supported types {String}:
         *   <ul>
         *     <li>alphanumeric: Only allows alphanumeric chars to be submitted</li>
         *     <li>integer: Only allows integer</li>
         *     <li>integerPositive:  Only allows positive integers to be submitted</li>
         *     <li>ipv4:  Only allows the IPv4 format to be submitted</li>
         *     <li>ipv6:  Only allows the IPv6 format to be submitted</li>
         *     <li>number:  Only allows numbers (positive and negative; integers and decimals) to be submitted</li>
         *   </ul>
         * @example
         *    // custom
         *    element.setValidator(element, {
         *        title:'Lower case letters only',
         *        pattern: '[a-z]+'
         *    });
         *    // supported
         *    element.setValidator(element, 'alphanumeric');
         *
         * @method setValidator
         * @static
         * @beta
         * @param {Element} element
         * @param {Object|String} options
         * @param {Boolean} required, define if the input must be filled to be valid, default is true
         */
        setValidator: function (element, options, required) {

            if (element._getHTMLElement().tagName.toLowerCase() !== 'input') {
                throw new Error('Element must be an input.');
            }

            var supportedNativeTypes = ['email',
                                        'url',
                                        'password'], typeVal = 'text', typeDesc;

            if (typeof options === 'string' || options instanceof String) {
                // if the string passed is a supported native type
                if (supportedNativeTypes.indexOf(options) !== -1) {
                    typeVal = options;
                }
                // if the string passed is a property in the validation ext library
                else if (validationPatterns[options]) {
                    // take the description (title and pattern) provided in the lib
                    typeDesc = validationPatterns[options];
                } else {
                    throw new Error('Type \'' + options + '\' not supported.');
                }
                // else if a description object is provided
            } else if (typeof options === 'object' && options.pattern) {
                typeDesc = options;
            }

            if (typeDesc) {
                // it should not override title if defined
                if (element.getAttribute('title') === undefined && typeDesc.title) {
                    element.setAttribute('title', typeDesc.title);
                }
                element._getHTMLElement().setAttribute('pattern', typeDesc.pattern);
            }

            // it should not override type if it is password
            var prevType = element.getAttribute('type');
            if (!prevType || (prevType && prevType !== 'password')) {
                element.setAttribute('type', typeVal);
            }

            // required is optional
            var req = (required !== undefined) ? required : 'true';
            element.setProperty('required', req);
        }

    };

    return  dom;

});

define('jscore/core',['./interfaces','./ext/dom'],function (interfaces, dom) {
    'use strict';
    /**
     *  private method, to make available for tests
     */
    var _private = {},
        appStyles = [],
        appWindowEvents = {},
        appWindowIntervals = {},
        appWindowEachFrames = {},
        windowEventsEnabled = false;

    /**
     * Polyfill for requestAnimationFrame
     */
    (function () {
        var lastTime = 0;
        var vendors = ['webkit', 'moz'];
        for (var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
            window.requestAnimationFrame = window[vendors[x] + 'RequestAnimationFrame'];
            window.cancelAnimationFrame =
                window[vendors[x] + 'CancelAnimationFrame'] || window[vendors[x] + 'CancelRequestAnimationFrame'];
        }

        if (!window.requestAnimationFrame)
            window.requestAnimationFrame = function (callback, element) {
                var currTime = new Date().getTime();
                var timeToCall = Math.max(0, 16 - (currTime - lastTime));
                var id = window.setTimeout(function () {
                        callback(currTime + timeToCall);
                    },
                    timeToCall);
                lastTime = currTime + timeToCall;
                return id;
            };

        if (!window.cancelAnimationFrame)
            window.cancelAnimationFrame = function (id) {
                clearTimeout(id);
            };
    }());

    /**
     * setRootElement
     *  @return {Element}
     */
    _private.setRootElement = setRootElement;
    function setRootElement() {
        /*jshint validthis:true */
        if (this.View !== undefined) {
            this.view = new this.View({presenter: this });
        }
        if (this.view !== undefined) {
            if (typeof this.view === 'function') {
                this.view = this.view();
            }
            this.view.render();
            return this.view.element;
        } else {
            return new Element();

        }
    }

    _private.addStyles = addStyles;
    function addStyles(styles, Object) {
        if (Object && Object.constructor.prototype.styles === undefined) {
            var style = document.createElement("style");
            style.textContent = styles;
            Object.constructor.prototype.styles = style;
            appStyles.push(style);
            document.head.appendChild(style);
        }
    }

    _private.removeChannel = removeChannel;
    function removeChannel(channel, identifier) {
        /*jshint validthis:true */
        var subscribers = this._getChannel(channel);

        var x = subscribers.length;
        for (var i = 0; i < x; i++) {
            if (subscribers[i].id === identifier) {
                subscribers = subscribers.splice(i, 1);
                break;
            }
        }
    }

    /**
     * core.Element is an alias for <a href="../classes/ext.dom.Element.html">ext.dom.Element</a>
     *
     * @class core.Element
     *
     */
    var Element = dom.Element;

    /**
     * Window class. Represents the browser window. Useful for listening to browser window events.
     *
     * @class core.Window
     */
    var Window = {

        /**
         * Adds an event listener to the window
         *
         * @method addEventHandler
         * @param {String} eventName
         * @param {Function} callback
         * @return {String} eventId
         *
         * @example
         *    var eventId = core.Window.addEventHandler('resize', callback);
         */
        addEventHandler: function (eventName, callback) {
            var eventId = guidGenerator();

            var wrappedCallback = function (e) {
                var evt = {};
                if (e) {
                    evt.originalEvent = e;
                }

                if (windowEventsEnabled) {
                    callback(evt);
                }
            };

            appWindowEvents[eventId] = {id: eventId, eventName: eventName, callback: wrappedCallback};
            window.addEventListener(eventName, wrappedCallback, false);
            return eventId;
        },

        /**
         * Removes an event listener from the window based on the eventId
         *
         * @method removeEventHandler
         * @param {String} eventId
         *
         * @example
         *    core.Window.removeEventHandler(eventId);
         */
        removeEventHandler: function (eventId) {
            var event = appWindowEvents[eventId];
            if (event === undefined) {
                return;
            }
            window.removeEventListener(event.eventName, event.callback, false);
            delete appWindowEvents[eventId];
        },

        /**
         * Callback function executes every X ms, where X is the time passed as the second argument.
         *
         * @method setInterval
         * @param {Function} callback
         * @param {Integer} interval (in milliseconds)
         * @return {Object} intervalObject (contains stop() method to stop the interval)
         *
         * @example
         *    core.Window.setInterval(function(){
         *        console.log("Triggers every 5 seconds");
         *    }, 5000);
         */
        setInterval: function (callback, interval) {
            var intervalId = guidGenerator();

            var wrappedCallback = function () {
                if (windowEventsEnabled) {
                    callback();
                }
            };

            appWindowIntervals[intervalId] = setInterval(wrappedCallback, interval);
            return {
                stop: function () {
                    clearInterval(appWindowIntervals[intervalId]);
                    delete appWindowIntervals[intervalId];
                }
            };
        },

        /**
         * Callback function executes every browser frame.
         *
         * @method eachFrame
         * @param {Function} callback
         * @return {Object} eachFrameObj (contains stop() method to stop the loop)
         *
         * @example
         *    var obj = core.Window.eachFrame(function(){
         *        console.log("Triggers every browser frame");
         *        obj.stop();
         *    });
         */
        eachFrame: function(callback) {
            var id = guidGenerator();
            var _break = false;

            var wrappedCallback = function() {
                if (windowEventsEnabled) {
                    callback();
                }

                // If the stop method is called inside the callback, then this check is required
                if (!_break) {
                    appWindowEachFrames[id] = requestAnimationFrame(wrappedCallback);
                }
            };

            appWindowEachFrames[id] = requestAnimationFrame(wrappedCallback);
            return {
                stop: function() {
                    cancelAnimationFrame(appWindowEachFrames[id]);
                    delete appWindowEachFrames[id];
                    _break = true;
                }
            };
        },

        /**
         * Removes all event listeners from the window
         *
         * @method removeAllEventHandlers
         * @private
         *
         * @example
         *    core.Window.removeAllEventHandlers();
         */
        removeAllEventHandlers: function () {
            for (var eventId in appWindowEvents) {
                Window.removeEventHandler(eventId);
            }
        },

        /**
         * Removes all intervals from the window
         *
         * @method removeAllIntervals
         * @private
         */
        removeAllIntervals: function() {
            for (var id in appWindowIntervals) {
                clearInterval(appWindowIntervals[id]);
            }
            appWindowIntervals = {};
        },

        /**
         * Removes all eachFrame callbacks from the window
         *
         * @method removeAllEachFrames
         * @private
         */
        removeAllEachFrames: function() {
            for (var id in appWindowEachFrames) {
                cancelAnimationFrame(appWindowEachFrames[id]);
            }
            appWindowEachFrames = {};
        },

        /**
         * Triggers the callbacks of all event listeners
         *
         * @method trigger
         * @param {String} eventName
         * @param {Object} [eventObject]
         *
         * @example
         *    core.Window.trigger("resize");
         *    core.Window.trigger("resize:"+eventId)
         */
        // TODO: Add this functionality to the event bus, and put in subscribe, not the publish
        trigger: function (event, eventObject) {
            var regex = /^([^:]+)(?::(.*))?/;
            var matches = event.match(regex);

            if (matches) {
                var eventName = matches[1];
                var eventId = matches[2];

                if (eventId) {
                    appWindowEvents[eventId].callback(eventObject);
                } else if (eventName) {
                    for (var eid in appWindowEvents) {
                        if (appWindowEvents[eid].eventName === eventName) {
                            appWindowEvents[eid].callback(eventObject);
                        }
                    }
                }

            }
        },

        /**
         * Gets a property of the window. Useful for getting information such as innerHeight or scrollTop
         *
         * @method getProperty
         * @param {String} property
         *
         * @example
         *    core.Window.getProperty("innerHeight");
         */
        getProperty: function (property) {
            return window[property];
        },

        /**
         * Loads the browser's print dialog
         *
         * @method showPrintDialog
         */
        showPrintDialog: function () {
            window.print();
        }

    };


    /**
     * The EventBus allows objects to subscribe and publish events that other components may react to.
     *
     * @class core.EventBus
     */


    function EventBus() {
        this._channels = [];
    }

    /** Generate Random UID
     *
     * @method guidGenerator
     * @private
     * @return {String}
     * */
    var guidGenerator = interfaces._private.guidGenerator;

    EventBus.prototype = {

        _hasChannel: function (channel) {
            return this._channels.hasOwnProperty(channel);
        },

        _addChannel: function (name) {
            this._channels[name] = [];
        },

        _getChannel: function (name) {
            return this._channels[name];
        },


        /**
         * Subscribe to events on a channel. Provide a callback function to handle the event. Returns a subscription id that can be used to remove the subscription.
         *
         * @method subscribe
         * @param {String} channel
         * @param {Function} callback
         * @param {Object} [context]
         * @return  {String} eventId
         *
         * @example
         * eventBus.subscribe("myTopic", function(data) {
		 *     console.log(data);
		 * });
         */
        subscribe: function (channel, callback, context) {
            if (!this._hasChannel(channel)) {

                this._addChannel(channel);
            }

            context = context ? context : this;

            var subscription = {
                id: guidGenerator(),
                channel: channel,
                fn: callback,
                context: context
            };

            this._getChannel(channel).push(subscription);
            return subscription.id;

        },

        /**
         * Publish data on a channel on the EventBus. If the channel does not exist it is created.
         *
         * @method publish
         * @param {String} channel
         * @param {any} [data]*
         *
         * @example
         *    eventBus.publish("myTopic", "someData");
         */
        publish: function (channel) {
            if (this._hasChannel(channel)) {
                var subscribers = this._getChannel(channel);
                var args = Array.prototype.slice.call(arguments, 1);

                var x = subscribers.length;
                for (var i = 0; i < x; i++) {
                    subscribers[i].fn.apply(subscribers[i].context, args);
                }
            }
        },
        /**
         * Removes a subscription from the EventBus so that subscription will no longer receive any events.
         * First attribute is channel or identifier (identifier is id which returns by subscribe).
         * Second attribute use identifier if channel is defined.
         *
         *
         * @method unsubscribe
         * @param {String} channel || identifier
         * @param {String} [identifier]
         *
         * @example
         *    eventBus.unsubscribe("myTopic", subscriptionId);
         */
        unsubscribe: function (channel, identifier) {
            if (!identifier) {
                identifier = channel;
                Object.keys(this._channels).forEach(function (channel) {
                    removeChannel.call(this, channel, identifier);
                }, this);
            } else {
                if (this._hasChannel(channel)) {
                    removeChannel.call(this, channel, identifier);
                }
            }
        }
    };


    /**
     * The AppContext interface represents a utility that the App uses to share a common context between Regions using the sandbox pattern.
     *
     * @class core.AppContext
     * @extends interfaces.AppContext
     */
    var AppContext = interfaces.AppContext.extend({
        /**
         * Destroy all registered components.
         *
         * @method destroyAll
         * @private
         */
        destroyAll: function () {
            var components = this._components;
            Object.keys(components).forEach(function (key) {
                components[key].stop();

            });
        }
    });
    /**
     * To create an AppContext child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {AppContext} context
     *
     * @example
     * core.AppContext.extend({
	 *     init: function() {
	 *         console.log("Hello Context!");
	 *     }
	 * });
     */

    /**
     * The App class is the main controller of the client application. It is responsible for overall UI layout, co-ordination of the different component parts of the UI and URL management.
     *
     * @class core.App
     * @extends interfaces.App
     */

    var App = interfaces.App.extend({
        /**
         * Creates an AppContext with an EventBus instance. May be overriden by developer to add additional context.
         *
         * @method createContext
         * @return {Object} context
         *
         * @example
         * createContext: function() {
		 *     return new MyCustomAppContext();
		 * }
         */
        createContext: function () {
            var appContext = new AppContext();
            appContext.eventBus = new EventBus();
            return appContext;
        },

        /**
         * Appends the app to a native HTMLElement or Element.
         *
         * @method _addToContainer
         * @private
         * @param {HTMLElement/Element} container
         */
        _addToContainer: function (container) {
            var parentElement;
            if (container instanceof HTMLElement) {
                parentElement = Element.wrap(container);
            }
            else {
                parentElement = container;
            }
            this._container = parentElement;

            this.attach();
        },

        /**
         * Removes the app from the container.
         *
         * @method _removeFromContainer
         * @private
         */
        _removeFromContainer: function () {
            this.detach();
            delete this._container;
            delete this.element;
        },

        _createElement: function () {
            this.element = setRootElement.apply(this, arguments);
        },
        /**
         * Function called any time it's attached to Parent.
         *
         * @method attach
         * @example
         *  app.attach();
         */
        onAttach: function () {

        },
        /**
         * Places the detached App back into the Parent.
         *
         * @method attach
         * @example
         *  app.attach();
         */
        attach: function () {
            var element = this.element;
            if (element !== undefined && element._getHTMLElement().parentNode !== this._container._getHTMLElement()) {
                this.constructor.prototype.counter++;
                if (this.constructor.prototype.counter === 1) {
                    var head = document.head;
                    appStyles.forEach(function (style) {
                        head.appendChild(style);
                    });
                }
                this._container.append(element);
                this.onAttach();
                windowEventsEnabled = true;
                Window.trigger("resize");
                Window.trigger("hashchange");
            }
        },

        /**
         * Appends the app to a new container.
         *
         * @param {HTMLElement/Element} container
         */
        attachTo: function (container) {
            var element = this.element;
            container = (container instanceof HTMLElement) ? container : container._getHTMLElement();
            if (element === undefined || element._getHTMLElement().parentNode !== container) {
                this.detach();
                this._addToContainer(container);
                this.onAttach();
                windowEventsEnabled = true;
                Window.trigger("resize");
                Window.trigger("hashchange");
            }
        },

        /**
         * Removes the App from the DOM, but does not stop the App. DOM events will still work when App is attached back.
         *
         * @method detach
         * @example
         *  app.detach();
         */
        detach: function () {
            var element = this.element;
            if (element !== undefined && element._getHTMLElement().parentNode === this._container._getHTMLElement()) {
                this.constructor.prototype.counter--;
                element.detach();
                if (this.constructor.prototype.counter === 0) {
                    appStyles.forEach(function (style) {
                        style.parentNode.removeChild(style);
                    });
                }
                windowEventsEnabled = false;
            }
        },
        /**
         * Shortcut method for getting the eventBus off the AppContext.
         *
         * @method getEventBus
         * @return {EventBus} eventBus
         *
         * @example
         *    app.getEventBus();
         */

        getEventBus: function () {
            return this.getContext().eventBus;
        },

        /**
         * Get the root Element of the App.
         *
         * @method getElement
         * @return {Element} element
         * @example
         *    app.getElement();
         */
        getElement: function () {
            return this.element;
        },

        stop: function () {
            interfaces.App.prototype.stop.apply(this, arguments);
            windowEventsEnabled = false;
            Window.removeAllEventHandlers();
            Window.removeAllIntervals();
            Window.removeAllEachFrames();
        }


    });
    /**
     * To create an App child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {App} app
     * @example
     * core.App.extend({
	 *     onStart: function() {
	 *         console.log("Hello App!");
	 *     }
	 * });
     */


    /**
     * The View class is responsible for template management. It may be used by any visible client component (App, Region, Widget) to load a HTML template.
     *
     * @class core.View
     */
    function View(options) {
        this.options = options || {};
        this.init.apply(this, arguments);
    }


    View.prototype = {

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
         * Get the HTML template as a string. The developer must implement this method.
         *
         * @method getTemplate
         * @return {String} template
         *
         * @example
         * getTemplate: function() {
		 *     return "<div><p>MyTemplate</p></div>";
		 * }
         */
        getTemplate: function () {
        },

        /**
         * Get the template CSS as a string. The developer must implement this method if CSS is needed for the template.
         *
         * @method getStyle
         * @return {String} css
         * @example
         * getStyle: function() {
		 *     return ".myCSSStyle{color:blue}";
		 * }
         */
        getStyle: function () {
            return '';
        },

        /**
         * Render the template to the DOM.
         *
         * @method render
         * @example
         *    view.render();
         */
        render: function () {
            var style = this.getStyle();
            if (style.trim() !== '') {
                addStyles(style, this);
            }

            var template = this.getTemplate();

            if (typeof template === "string") {
                this.element = Element.parse(template);
            } else {
                this.element = template;
            }
        },

        /**
         * Get the root Element of the View.
         *
         * @method getElement
         * @return {Element} element
         * @example
         *    view.getElement();
         */
        getElement: function () {
            return this.element;
        }

    };

    /**
     * To create a View child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {View} view

     * @example
     * core.View.extend({
	 *     getTemplate: function() {
	 *         return template;
	 *     }
	 * });
     */
    View.extend = interfaces.extend;


    /**
     * The App may create Region instances to manage specific areas of the screen with a well defined responsibility. Regions are completely independent of each other and communicate via event passing using the EventBus. In this way any Region may be removed without affecting other regions. Likewise Regions may be independently restarted should they get into an error state. For these reasons, regions may not be nested.
     *
     * @class core.Region
     * @extends interfaces.UIComponent
     */
    var Region = interfaces.UIComponent.extend({
        _listenForDOMAttach: function () {
            if (this.onDOMAttach) {
                var intervalFunction = function () {
                    if (document.body.contains(this.getElement()._getHTMLElement())) {
                        this.onDOMAttach();
                    } else {
                        requestAnimationFrame(intervalFunction);
                    }
                }.bind(this);

                requestAnimationFrame(intervalFunction);
            }
        },
        /**
         * Call this method to start the region.
         *
         * @method start
         * @param {Element} parent
         * @example
         *    region.start();
         */
        start: function (parent) {
            if (!this._parent) {
                this._createElement();
                parent.append(this.getElement());
                this._listenForDOMAttach();
                this._parent = parent;
                this.onStart();
                var context = this.context;
                if (context && context._components) {
                    context._components[this.uid] = this;
                }

            }

        },

        /**
         * Implement this method to define how to start the Region.
         *
         * @method onStart
         * @example
         * onStart: function() {
		 *     console.log("onStart called");
		 * }
         */
        onStart: function () {
        },

        /**
         * Call this method to stop the region.
         *
         * @method stop
         * @example
         *    region.stop();
         */
        //TODO: stop method need to finish
        stop: function () {
            if (this._parent) {
                var context = this.getContext();
                if (context !== undefined) {
                    var components = context._components;
                    if (components !== undefined && components[this.uid] !== undefined) {
                        delete components[this.uid];
                    }
                }

                this.destroy();
                delete this._parent;
                this.onStop();
            }

        },

        /**
         * Implement this method to define how to stop the Region.
         *
         * @method onStop
         * @example
         * onStop: function() {
		 *     console.log("Stopping Region...");
		 * }
         */
        onStop: function () {
        },
        /**
         * Places the detached Widget back into the defined parent element.
         *
         * @method attach
         * @example
         *  region.attach();
         */
        attach: function () {
            var element = this.element;
            var parent = this._parent;
            if (element !== undefined && parent !== undefined && element._getHTMLElement().parentNode !== parent._getHTMLElement()) {
                parent.append(element);
            }

        },

        /**
         * Implement this method to run statements after the region has been attached to the DOM.
         *
         * @method onDOMAttach
         * @beta
         * @example
         * onDOMAttach: function() {
         *     this.view.doSomething();
         * }
         */

        /**
         * Removes the Widget from the parent element, but does not destroy the Widget. DOM events will still work when Widget is attached back.
         *
         * @method detach
         * @example
         *  region.detach();
         */
        detach: function () {
            var element = this.element;
            var parent = this._parent;
            if (element !== undefined && parent !== undefined && element._getHTMLElement().parentNode === parent._getHTMLElement()) {
                element.detach();
            }

        },
        /**
         * Create the Region root Element.
         *
         * @method createElement
         * @return {Element} element
         */
        _createElement: function () {
            this.element = setRootElement.apply(this, arguments);
        },

        /**
         * Shortcut method for getting the eventBus from the AppContext.
         *
         * @method getEventBus
         * @return {EventBus} eventBus
         */
        getEventBus: function () {
            return this.getContext().eventBus;
        },

        /**
         * Removes the Region root Element from the DOM.
         *
         * @method destroy
         * @private
         */
        destroy: function () {
            this.getElement().remove();

        },

        /**
         * Set the AppContext of the Region.
         *
         * @method setContext
         * @private
         * @param {AppContext} context
         */
        setContext: function (context) {
            context._components[this.uid] = this;
            this.context = context;
        },

        /**
         * Returns the instance of the AppContext
         *
         * @method getContext
         * @return {AppContext} context
         * @example
         *    region.getContext();
         */
        getContext: function () {
            return this.context;
        }

        /**
         * Get the root Element of the Region.
         *
         * @method getElement
         * @return {Element} element
         * @example
         *    region.getElement();
         */
    });
    /**
     * To create an Region child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {Region} region
     * @example
     * core.Region.extend({
	 *     onStart: function() {
	 *     }
	 * });
     */

    /**
     * Initialises the Region with an AppContext and returns an instance.
     *
     * @method create
     * @static
     * @private
     * @param {AppContext} context
     * @return {Region} region
     * @example
     *    var regionInstance = Region.create(this.getContext());
     */


    /**
     * The Widget class is a piece of reusable generic UI code. It does not have any knowledge of its context or how it is being used. Widgets can be simple like buttons or text input fields or complex like data-grids or maps. Widgets are composable and may be nested as required to construct composite widgets. Widgets may be reused in multiple apps. A common set of widgets is provided as a common UI component.
     *
     * The use of widgets for purely layout purposes should be avoided as the parent (App, Place or Region) template may be used for this purpose along with appropriate CSS. Widgets may be used where some user interaction is required. Even in this scenario widgets are not mandatory and the functionality may be handled directly in the parent JavaScript. Widgets should be considered for encapsulating complex interaction logic and where code reuse is possible.
     *
     * @class core.Widget
     * @extends interfaces.UIComponent
     */
    var Widget = interfaces.UIComponent.extend({
        _listenForDOMAttach: function () {
            if (this.onDOMAttach) {
                var intervalFunction = function () {
                    if (document.body.contains(this.getElement()._getHTMLElement())) {
                        this.onDOMAttach();
                    } else {
                        requestAnimationFrame(intervalFunction);
                    }
                }.bind(this);

                requestAnimationFrame(intervalFunction);
            }
        },

        _afterInit: function (options) {
            this._createElement();
            this.onViewReady.apply(this, arguments);


        },
        /**
         * Implement this method to run statements after the view for the Widget has been initialized. This is automatically called after init.
         *
         * @method onViewReady
         * @param {Object} options
         * @example
         * onViewReady: function(options) {
         *     this.view.doSomething();
         * }
         */
        onViewReady: function (options) {

        },

        /**
         * Implement this method to run statements after the widget has been attached to the parent element.
         *
         * @method onAttach
         * @example
         * onAttach: function() {
         *     this.view.doSomething();
         * }
         */
        onAttach: function () {
        },

        /**
         * Implement this method to run statements after the widget has been attached to the DOM.
         *
         * @method onDOMAttach
         * @beta
         * @example
         * onDOMAttach: function() {
         *     this.view.doSomething();
         * }
         */

        // DO NOT PUT ANYTHING HERE FOR THIS FUNCTION, OTHERWISE IT WILL NOT BE OPTIMISED

        /**
         * Adds the Widget's element to the new parent element.
         *
         * @method attachTo
         * @param {Element} parent
         * @example
         *    widget.attachTo(this.getElement());
         */
        attachTo: function (parent) {
            if (!this._parent || (this.element._getHTMLElement().parentNode !== parent._getHTMLElement())) {
                this.detach();
                this._parent = parent;
                this.attach();
            }
        },

        /**
         * Places the detached Widget back into the defined parent element.
         *
         * @method attach
         * @example
         *  widget.attach();
         */
        attach: function () {
            var element = this.element;
            var parent = this._parent;
            if (element !== undefined && parent !== undefined && element._getHTMLElement().parentNode !== parent._getHTMLElement()) {
                parent.append(element);
                this.onAttach();
                this._listenForDOMAttach();
            }

        },

        /**
         * Removes the Widget from the parent element, but does not destroy the Widget. DOM events will still work when Widget is attached back.
         *
         * @method detach
         * @example
         *  widget.detach();
         */
        detach: function () {
            var element = this.element;
            var parent = this._parent;
            if (element !== undefined && parent !== undefined && element._getHTMLElement().parentNode === parent._getHTMLElement()) {
                element.detach();
            }

        },
        /**
         * Bind an event handling function to the Widget event.
         *
         * @method addEventHandler
         * @param {String} eventName
         * @param {Function} fn
         * @return {String} id
         * @example
         * widget.addEventHandler("customEvent", function(data) {
		 *     console.log("customEvent was triggered with " + data);
		 * });
         */
        addEventHandler: function (eventName, callback, context) {
            if (this.eventBus === undefined) {
                this.eventBus = new EventBus();
            }

            return this.eventBus.subscribe.apply(this.eventBus, arguments);
        },

        /**
         * Unbind an event handling function from the Widget event.
         *
         * @method removeEventHandler
         * @param {String} eventName
         * @param {String} id

         * @example
         *    widget.removeEventHandler("customEvent", eventId);
         */
        removeEventHandler: function (eventName, id) {
            if (this.eventBus) {
                this.eventBus.unsubscribe.apply(this.eventBus, arguments);
            }
        },

        /**
         * Trigger a Widget event.
         *
         * @method trigger
         * @param {String} eventName
         * @param {any} [data]*
         *
         * @example
         *    widget.trigger("customEvent", "someData");
         */
        trigger: function (eventName, data) {
            if (this.eventBus) {
                this.eventBus.publish.apply(this.eventBus, arguments);
            }
        },

        /**
         * Create the Widget root Element.
         *
         * @method _createElement
         * @private
         * @return {Element} element
         *
         */
        _createElement: function () {
            this.element = setRootElement.apply(this, arguments);
        },

        /**
         * Removes the Widget root Element from the DOM.
         *
         * @method destroy
         *
         * @example
         *    widget.destroy();
         */
        destroy: function () {
            this.onDestroy();
            delete this.eventBus;
            this.getElement().remove();
        },

        /**
         * Called when destroy method is called.
         *
         * @method onDestroy
         */
        onDestroy: function () {

        }

        /**
         * Get the root Element of the Widget.
         *
         * @method getElement
         * @return {Element} element
         *
         * @example
         *    widget.getElement();
         */
    });
    /**
     * To create an Widget child class call extend providing the class definition.
     *
     * @method extend
     * @static
     * @param {Object} definition
     * @return {Widget} widget
     * @example
     * core.Widget.extend({
	 *     init: function() {
	 *     }
	 * });
     */

    /**
     * Custom XmlHttpRequest class. Returned by networking functions.
     *
     * @class core.XHR
     */
    var XHR = function (nativeXHR) {
        this._nativeXHR = nativeXHR;
    };

    XHR.prototype = {
        /**
         * Aborts the current request.
         *
         * @method abort
         *
         * @example
         *    xhr.abort();
         */
        abort: function () {
            this._nativeXHR.abort.call(this._nativeXHR);
        },

        /**
         * Gets the current state of the request.
         *
         * @method getReadyState
         * @return {Integer} readyState
         *
         * @example
         *    xhr.getReadyState();
         */
        getReadyState: function () {
            return this._nativeXHR.readyState;
        },

        /**
         * Gets the HTTP status code of the request
         *
         * @method getStatus
         * @return {Integer} status
         *
         * @example
         *    xhr.getStatus();
         */
        getStatus: function () {
            return this._nativeXHR.status;
        },

        /**
         * Gets the response text of the request as JSON
         *
         * @method getResponseJSON
         * @return {Object} responseJSON
         *
         * @example
         *    xhr.getResponseJSON();
         */
        getResponseJSON: function () {
            return JSON.parse(this._nativeXHR.responseText);
        },

        /**
         * Gets the response text of the request
         *
         * @method getResponseText
         * @return {String} responseText
         *
         * @example
         *    xhr.getResponseText();
         */
        getResponseText: function () {
            return this._nativeXHR.responseText;
        },

        /**
         * Gets the HTTP status text of the request
         *
         * @method getStatusText
         * @return {String} statusText
         *
         * @example
         *    xhr.getStatusText();
         */
        getStatusText: function () {
            return this._nativeXHR.statusText;
        }
    };


    return {
        App: App,
        AppContext: AppContext,
        EventBus: EventBus,
        Region: Region,
        Widget: Widget,
        Window: Window,
        View: View,
        Element: Element,
        XHR: XHR,
        extend: interfaces.extend,
        _private: _private,
        version: "1.3.3" // TODO: remove hardcode
    };
});

