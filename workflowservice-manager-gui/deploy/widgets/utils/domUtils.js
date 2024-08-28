/* Copyright (c) Ericsson 2014 */

define('widgets/utils/domUtils',['jscore/base/jquery','jscore/core'],function ($, core) {
    'use strict';

    function transitionEndEventName() {
        var i,
            el = document.createElement('div'),
            transitions = {
                'transition': 'transitionend',
                'OTransition': 'otransitionend',  // oTransitionEnd in very old Opera
                'MozTransition': 'transitionend',
                'WebkitTransition': 'webkitTransitionEnd'
            };

        for (i in transitions) {
            if (transitions.hasOwnProperty(i) && el.style[i] !== undefined) {
                return transitions[i];
            }
        }
        return 'webkitTransitionEnd';
    }

    /**
     * The domUtils used to work with native DOM elements.
     *
     * @class utils.domUtils
     */
    return {
        /**
         * Adds "resize" event listener to window element.
         *
         * @method addWindowResizeHandler
         * @param {Function} callBack The function which will be called, when resize is triggered.
         * @param {Object} [context] Optional
         * @param {boolean} [useCapture] Optional
         */
        addWindowResizeHandler: function (callBack, context, useCapture) {
            core.Window.addEventHandler('resize', callBack.bind(context));
        },

        /**
         * Removes "resize" event listener from window element.
         *
         * @method removeWindowResizeHandler
         * @param {Function} callBack The function which will be called, when resize is triggered.
         * @param {Object} [context] Optional
         * @param {boolean} [useCapture] Optional
         */
        removeWindowResizeHandler: function (callBack, context, useCapture) {
            window.removeEventListener('resize', callBack.bind(context), useCapture);
        },

        /**
         * Returns window width and height of the browser window
         *
         * @method getWindowDimensions
         * @returns {{width: Number, height: Number}}
         */
        getWindowDimensions: function () {
            return {
                width: window.innerWidth,
                height: window.innerHeight
            };
        },

        /**
         * Returns window width and height of the element
         *
         * @method getElementDimensions
         * @param {core.Element} element
         * @returns {{width: Number, height: Number}}
         */
        getElementDimensions: function (element) {
            var nativeElt = element._getHTMLElement();
            return {
                width: nativeElt.clientWidth,
                height: nativeElt.clientHeight
            };
        },

        /**
         * Returns a list of core.Element objects found in the element
         *
         * @method findAll
         * @param {string} selector
         * @param {core.Element} element
         * @returns {Array} A list of core.Element objects
         */
        findAll: function (selector, element) {
            var $el;
            if (element) {
                $el = $($.find(selector, element._getHTMLElement()));
            }
            var results = [];
            $el.each(function () {
                var $value = $(this);
                var result = $value.data('element');

                if (!result) {
                    results.push(core.Element.wrap(this));
                    $value.data('element', result);
                } else {
                    results.push(result);
                }
            });
            return results;
        },

        /**
         * Stops propagation according to provided event
         *
         * @method stopPropagation
         * @param {Object} e Native event
         */
        stopPropagation: function (e) {
            var event = e.originalEvent || window.event;
            e.preventDefault();
            event.stopPropagation();
        },

        /**
         * Attaches a handler to one or more events for all elements that match the selector, now or in the future,
         * based on a specific set of root elements.
         *
         * @method delegate
         * @param {core.Element} element The Element which attaches a handler
         * @param {String} selector Used to fire event when selectors match
         * @param {String} eventName Event name
         * @param {Function} callBack The function which will be called, when resize is triggered.
         * @param {Object} [context] Optional
         */
        delegate: function (element, selector, eventName, callBack, context) {
            $(element._getHTMLElement()).delegate(selector, eventName, callBack.bind(context));
        },

        /**
         * Returns mouse position on the screen.
         *
         * @method getMousePosEvt
         * @param {Object} e Native event
         * @returns {{left: Number, top: Number}}
         */
        getMousePosEvt: function (e) {
            var event = e.originalEvent || window.event;
            return {
                left: event.clientX,
                top: event.clientY
            };
        },

        /**
         * Returns element outerHeight or undefined
         *
         * @method getOuterHeight
         * @param {core.Element} element
         * @returns {Number|undefined}
         */
        getOuterHeight: function (element) {
            if (element) {
                return $(element._getHTMLElement()).outerHeight(true);
            }
            return undefined;
        },

        slideDown: function (element, options) {
            $(element._getHTMLElement()).slideDown(options);
        },

        scrollIntoView: function (element, container, alignTop) {
            if (!this.isScrolledElementVisible(element, container)) {
                element._getHTMLElement().scrollIntoView(alignTop);
            }
        },

        isScrolledElementVisible: function(element, container){
            var elPos = element.getPosition();
            var cPos = container.getPosition();
            return (elPos.bottom < cPos.bottom) && (elPos.top > cPos.top);
        },

        slideUp: function (element, options) {
            $(element._getHTMLElement()).slideUp(options);
        },

        hasModifier: function (element, key, prefix) {
            var fullPrefix = ((prefix && prefix !== '') ? prefix : element._modifierPrefix) + '_' + key;
            return (element._modifiers && element._modifiers[fullPrefix]);
        },

        transitionEventName: transitionEndEventName()

    };

});

