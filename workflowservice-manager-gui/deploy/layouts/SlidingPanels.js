define('layouts/ext/ext.dom',['jscore/base/jquery','jscore/core'],function ($, core) {

    function checkBrowser() {
        var browser;
        if (navigator.userAgent.indexOf('Gecko/') !== -1) {//Firefox
            browser = 'Firefox';
        }
        else if (navigator.userAgent.indexOf('Trident/') !== -1) {//IE
            browser = 'MSIE';
        } else {
            browser = 'WebKit';
        }
        return browser;
    }

    function transitionEndEventName () {
        var i,
            el = document.createElement('div'),
            transitions = {
                'transition':'transitionend',
                'OTransition':'otransitionend',  // oTransitionEnd in very old Opera
                'MozTransition':'transitionend',
                'WebkitTransition':'webkitTransitionEnd'
            };

        for (i in transitions) {
            if (transitions.hasOwnProperty(i) && el.style[i] !== undefined) {
                return transitions[i];
            }
        }
        return 'webkitTransitionEnd';
    }

    var transitionEventNames = {
            'WebKit': 'webkitTransitionEnd',
            'Firefox': 'transitionend',
            'MSIE': 'transitionend'
        },
        animEndEventNames = {
            'WebKit': 'webkitAnimationEnd',
            'Firefox': 'animationend',
            'MSIE': 'animationend'
        };


    var domExt = {
        //Transition Event Name
        transitionEventName: transitionEndEventName(),
        // animation end event name
        animEndEventName: animEndEventNames[checkBrowser()],

        /**
         * Returns window width and height of the browser window
         *
         * @method getWindowDimensions
         * @returns {{width: Number, height: Number}}
         * @private
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
         * @private
         */
        getElementDimensions: function (element) {
            var nativeElt = element._getHTMLElement();
            return {
                width: nativeElt.offsetWidth,
                height: nativeElt.offsetHeight
            };
        },

        /**
         * Returns a list of core.Element objects found in the element
         *
         * @method findAll
         * @param {string} selector
         * @param {core.Element} element
         * @returns {Array} A list of core.Element objects
         * @private
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
         * @private
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
         * @private
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
         * @private
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
         * @private
         */
        getOuterHeight: function (element) {
            if (element) {
                return $(element._getHTMLElement()).outerHeight(true);
            }
            return undefined;
        },

        hasModifier: function (element, key, prefix) {
            var fullPrefix = ((prefix && prefix !== '') ? prefix : element._modifierPrefix) + '_' + key;
            return (element._modifiers && element._modifiers[fullPrefix]);
        }
    };

    domExt.swipeEvents = function (element) {
        var startX,
            startY,
            $this = $(element._getHTMLElement());
        $this.on('touchstart', touchstart);

        function touchstart(event) {
            var touches = event.originalEvent.touches;
            if (touches && touches.length) {
                startX = touches[0].pageX;
                startY = touches[0].pageY;
                $this.on('touchmove', touchmove);
            }
            event.preventDefault();
        }

        function touchmove(event) {
            var touches = event.originalEvent.touches;
            if (!$('button, a').has($(event.target)).length) {
                event.preventDefault();
            }
            if (touches && touches.length) {
                var deltaX = startX - touches[0].pageX;
                var deltaY = startY - touches[0].pageY;
                if (deltaX >= 50) {
                    $this.trigger("swipeLeft");
                }
                if (deltaX <= -50) {
                    $this.trigger("swipeRight");
                }
                if (deltaY >= 50) {
                    $this.trigger("swipeUp");
                }
                if (deltaY <= -50) {
                    $this.trigger("swipeDown");
                }
                if (Math.abs(deltaX) >= 50 || Math.abs(deltaY) >= 50) {
                    $this.off('touchmove', touchmove);
                }
            }
            event.preventDefault();
        }

    };
    domExt.findAll = function (selector, element) {
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
    };
    domExt.clickTargets = function (selector) {
        $(selector).click();
    };
    domExt.animate = function (element, options, speed, callback) {
        $(element._getHTMLElement()).animate(options, speed, callback);
    };
    domExt.hide = function (element, duration, complete) {
        $(element._getHTMLElement()).hide(duration, complete);
    };

    domExt.slideDown = function (element, duration, complete) {
        $(element._getHTMLElement()).slideDown(duration, complete);
    };

    domExt.slideUp = function (element, duration, complete) {
        $(element._getHTMLElement()).slideUp(duration, complete);
    };

    domExt.getProperty = function (element, property) {
        return $(element._getHTMLElement()).prop(property);
    };

    domExt.setProperty = function (element, property, value) {
        $(element._getHTMLElement()).prop(property, value);
    };

    domExt.addClass = function (element, className) {
        $(element._getHTMLElement()).addClass(className);
    };

    domExt.removeClass = function (element, className) {
        $(element._getHTMLElement()).removeClass(className);
    };

    domExt.position = function (element) {
        return $(element._getHTMLElement()).position();
    };

    domExt.offset = function (element) {
        return $(element._getHTMLElement()).offset();
    };

    domExt.outerWidth = function (element, includeMargin) {
        return $(element._getHTMLElement()).outerWidth(includeMargin ? true : false);
    };
    domExt.prepend = function (element, text) {
        return $(element._getHTMLElement()).prepend(text);
    };
    domExt.resize = function (cb) {
        $(window).resize(cb);
    };

    return domExt;

});

define('text!layouts/sliding_panels/panel_button/right/right.html',[],function () { return '<div class="elLayouts-PanelButton">\n    <div class="elLayouts-QuickActionBar-separator"></div>\n    <div class="elLayouts-PanelButton-menu">\n        <button class="elLayouts-PanelButton-button"><span class="elLayouts-PanelButton-text"></span> <span class="ebIcon"></span></button>\n    </div>\n</div>\n';});

define('layouts/sliding_panels/panel_button/right/RightView',['../PanelButtonView','text!./right.html'],function (View, template) {
    'use strict';

    return View.extend({

        getTemplate: function () {
            return template;
        }

    });

});

define('layouts/sliding_panels/panel_button/right/Right',['../PanelButton','./RightView'],function (Widget, View) {

    return Widget.extend({

        View: View

    });

});

define('text!layouts/sliding_panels/panel_button/left/left.html',[],function () { return '<div class="elLayouts-PanelButton">\n    <div class="elLayouts-PanelButton-menu">\n        <button class="elLayouts-PanelButton-button"><span class="ebIcon"></span> <span class="elLayouts-PanelButton-text"></span></button>\n    </div>\n    <div class="elLayouts-QuickActionBar-separator"></div>\n</div>\n';});

define('layouts/sliding_panels/panel_button/left/LeftView',['../PanelButtonView','text!./left.html'],function (View, template) {
    'use strict';

    return View.extend({

        getTemplate: function () {
            return template;
        }

    });

});

define('styles!layouts/sliding_panels/panel_button/panelButton.less',[],function () { return '.elLayouts-PanelButton-menu {\n  height: 100%;\n  line-height: 24px;\n  margin-right: 0.5rem;\n  margin-left: 0.5rem;\n  display: inline-block;\n}\n.elLayouts-PanelButton-menu > span {\n  vertical-align: middle;\n}\n.elLayouts-PanelButton-button {\n  position: relative;\n  display: inline-block;\n  vertical-align: middle;\n  background-color: transparent;\n  border: none;\n  min-width: 60px;\n  -webkit-border-radius: 3px;\n  -moz-border-radius: 3px;\n  -ms-border-radius: 3px;\n  border-radius: 3px;\n  -webkit-box-sizing: border-box;\n  -moz-box-sizing: border-box;\n  -ms-box-sizing: border-box;\n  box-sizing: border-box;\n  padding: 0 6px;\n  line-height: 1.2rem;\n  height: 2.4rem;\n  font-size: 1.2rem;\n  text-decoration: none;\n  text-align: center;\n  cursor: pointer;\n  white-space: nowrap;\n  border: 1px solid transparent;\n}\n.elLayouts-PanelButton-button > * {\n  vertical-align: middle;\n}\n.elLayouts-PanelButton-button:hover {\n  border-color: #999;\n}\n.elLayouts-PanelButton-icon {\n  width: 14px !important;\n  height: 14px !important;\n  line-height: 14px !important;\n  margin-left: 5px;\n}\n.elLayouts-PanelButton-icon_icon_left {\n  background-image: url("layouts/resources/layouts/sliding_panels/panel_button/arrow_left.svg");\n}\n.elLayouts-PanelButton-icon_icon_right {\n  background-image: url("layouts/resources/layouts/sliding_panels/panel_button/arrow_right.svg");\n}\n';});

define('layouts/sliding_panels/panel_button/PanelButtonView',['jscore/core','styles!./panelButton.less'],function (core, styles) {
    'use strict';

    return core.View.extend({

        getStyle: function () {
            return styles;
        },

        getButton: function () {
            return this.getElement().find('.elLayouts-PanelButton-button');
        },

        setText: function (text) {
            this.getElement().find('.elLayouts-PanelButton-text').setText(text);
        },

        setArrow: function (direction) {
            var icon = this.getElement().find('.ebIcon');
            icon.removeModifier('rightArrowLarge');
            icon.removeModifier('leftArrowLarge');
            icon.setModifier(direction + 'ArrowLarge', undefined, 'ebIcon');
        },

        setArrowLeft: function () {
            this.setArrow('left');
        },

        setArrowRight: function () {
            this.setArrow('right');
        },

        hide: function () {
            this.getElement().setStyle('display', 'none');
        },

        show: function () {
            this.getElement().setStyle('display', 'block');
        }

    });

});

define('layouts/sliding_panels/panel_button/PanelButton',['jscore/core','./PanelButtonView'],function (core, View) {

    return core.Widget.extend({

        View: View,

        init: function (options) {
            this.options = options;
        },

        onViewReady: function () {
            this.view.getButton().addEventHandler('click', this.options.click);
        },

        setLabel: function (text) {
            this.view.setText(text);
        },

        setLeftArrow: function () {
            this.view.setArrowLeft();
        },

        setRightArrow: function () {
            this.view.setArrowRight();
        },

        hide: function () {
            this.view.hide();
        },

        show: function () {
            this.view.show();
        }

    });

});

define('layouts/sliding_panels/panel_button/left/Left',['../PanelButton','./LeftView'],function (Widget, View) {

    return Widget.extend({

        View: View

    });

});

define('styles!layouts/sliding_panels/slidingPanels.less',[],function () { return '.elLayouts-SlidingPanels {\n  overflow: hidden;\n  position: relative;\n  min-height: 100%;\n}\n.elLayouts-SlidingPanels-center {\n  width: 100%;\n  position: relative;\n}\n.elLayouts-SlidingPanels-leftWrapper,\n.elLayouts-SlidingPanels-rightWrapper {\n  width: 0;\n  position: absolute;\n  overflow: hidden;\n  top: 0;\n}\n@media screen and (min-width: 481px) {\n  .elLayouts-SlidingPanels-center {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out, 0.3s margin-right ease-in-out;\n  }\n  .elLayouts-SlidingPanels-leftWrapper {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out;\n  }\n  .elLayouts-SlidingPanels-leftWrapper_position_fixed {\n    position: fixed;\n  }\n  .elLayouts-SlidingPanels-leftWrapper_expanded_true {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out, height 0.3s, top 0.3s;\n  }\n  .elLayouts-SlidingPanels-leftContents {\n    position: relative;\n    height: 100%;\n    margin-right: 3.6rem;\n    width: 250px;\n    transition: 0.3s margin-left ease-in-out;\n    margin-left: calc(-250px - 3.6rem);\n  }\n  .elLayouts-SlidingPanels-rightWrapper {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out;\n    margin-left: 100%;\n    top: 0;\n  }\n  .elLayouts-SlidingPanels-rightWrapper_position_fixed {\n    position: fixed;\n    top: 77px;\n    left: -55px;\n  }\n  .elLayouts-SlidingPanels-rightWrapper_expanded_true {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out, height 0.3s, top 0.3s;\n  }\n  .elLayouts-SlidingPanels-rightContents {\n    position: relative;\n    height: 100%;\n    margin-left: 3.6rem;\n    width: 250px;\n    transition: 0.3s margin-right ease-in-out;\n    margin-right: calc(-250px - 3.6rem);\n  }\n  .elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-center,\n  .elLayouts-SlidingPanels_left_right .elLayouts-SlidingPanels-center {\n    width: calc(100% - 250px - 3.6rem);\n    margin-left: calc(250px + 3.6rem);\n  }\n  .elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-leftWrapper,\n  .elLayouts-SlidingPanels_left_right .elLayouts-SlidingPanels-leftWrapper {\n    width: calc(250px + 3.6rem);\n  }\n  .elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-leftContents,\n  .elLayouts-SlidingPanels_left_right .elLayouts-SlidingPanels-leftContents {\n    margin-left: 0;\n  }\n  .elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-center,\n  .elLayouts-SlidingPanels_right_left .elLayouts-SlidingPanels-center {\n    width: calc(100% - 250px - 3.6rem);\n  }\n  .elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-rightWrapper,\n  .elLayouts-SlidingPanels_right_left .elLayouts-SlidingPanels-rightWrapper {\n    width: calc(250px + 3.6rem);\n    margin-left: calc(100% - 250px - 3.6rem);\n  }\n  .elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-rightContents,\n  .elLayouts-SlidingPanels_right_left .elLayouts-SlidingPanels-rightContents {\n    margin-right: 0;\n  }\n  .elLayouts-SlidingPanels_left_right .elLayouts-SlidingPanels-center,\n  .elLayouts-SlidingPanels_right_left .elLayouts-SlidingPanels-center {\n    width: calc(100% - 250px - 3.6rem - 250px - 3.6rem);\n  }\n}\n@media screen and (max-width: 480px) {\n  .elLayouts-SlidingPanels-center,\n  .elLayouts-SlidingPanels-leftWrapper,\n  .elLayouts-SlidingPanels-rightWrapper {\n    width: 100%;\n    transition: 0.3s margin-left ease-in-out;\n    -webkit-transition: 0.3s margin-left ease-in-out;\n  }\n  .elLayouts-SlidingPanels-leftWrapper {\n    margin-left: calc(-100% - 3.6rem);\n  }\n  .elLayouts-SlidingPanels-rightWrapper {\n    margin-left: calc(100% + 3.6rem);\n  }\n  .elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-center,\n  .elLayouts-SlidingPanels_left_right.elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-center {\n    margin-left: calc(100% + 3.6rem);\n  }\n  .elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-leftWrapper,\n  .elLayouts-SlidingPanels_left_right.elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-leftWrapper {\n    margin-left: 0;\n  }\n  .elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-rightWrapper,\n  .elLayouts-SlidingPanels_left_right.elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-rightWrapper {\n    margin-left: calc(100% + 100% + 3.6rem + 3.6rem);\n  }\n  .elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-center,\n  .elLayouts-SlidingPanels_right_left.elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-center {\n    margin-left: calc(-100% - 3.6rem);\n  }\n  .elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-leftWrapper,\n  .elLayouts-SlidingPanels_right_left.elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-leftWrapper {\n    margin-left: calc(-100% - 100% - 3.6rem - 3.6rem);\n  }\n  .elLayouts-SlidingPanels_right .elLayouts-SlidingPanels-rightWrapper,\n  .elLayouts-SlidingPanels_right_left.elLayouts-SlidingPanels_left .elLayouts-SlidingPanels-rightWrapper {\n    margin-left: 0;\n  }\n  .elLayouts-SlidingPanels-leftContents,\n  .elLayouts-SlidingPanels-rightContents {\n    height: 100%;\n  }\n}\n';});

define('text!layouts/sliding_panels/slidingPanels.html',[],function () { return '<div class="elLayouts-SlidingPanels">\n    <div class="elLayouts-SlidingPanels-leftWrapper">\n        <div class="elLayouts-SlidingPanels-leftContents"></div>\n    </div>\n    <div class="elLayouts-SlidingPanels-center"></div>\n    <div class="elLayouts-SlidingPanels-rightWrapper">\n        <div class="elLayouts-SlidingPanels-rightContents"></div>\n    </div>\n</div>\n';});

define('layouts/sliding_panels/SlidingPanelsView',['jscore/core','text!./slidingPanels.html','styles!./slidingPanels.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return styles;
        },

        show: function (show) {
            this.getElement().setStyle('display', show ? 'block' : 'none');
        },

        getPlaceholder: function () {
            return this.getElement().find('.elLayouts-SlidingPanels-center');
        },

        getLeftPanelPlaceholder: function () {
            return this.getElement().find('.elLayouts-SlidingPanels-leftContents');
        },

        getLeftPanelWrapper: function () {
            return this.getElement().find('.elLayouts-SlidingPanels-leftWrapper');
        },

        getRightPanelPlaceholder: function () {
            return this.getElement().find('.elLayouts-SlidingPanels-rightContents');
        },

        getRightPanelWrapper: function () {
            return this.getElement().find('.elLayouts-SlidingPanels-rightWrapper');
        }

    });

});

define('layouts/sliding_panels/SlidingPanels',['jscore/core','./SlidingPanelsView','./panel_button/left/Left','./panel_button/right/Right','../ext/ext.dom'],function (core, View, LeftPanelButton, RightPanelButton, dom) {

    function attachUIElement(uiElement, parent) {
        if (uiElement instanceof core.Widget) {
            uiElement.attachTo(parent);
        } else if (uiElement instanceof core.Region) {
            uiElement.start(parent);
        }
    }

    /**
     * SlidingPanel provides a main panel and two side panels for user-defined content.
     *
     * <strong>Constructor:</strong>
     * SlidingPanel(Object options)
     * <strong>Events</strong>
     *      <ul>
     *          <li>'layouts:toggleleftpanel': toggles left panel, callback is triggered after panel animation finishes.</li>
     *          <li>'layouts:togglerightpanel': toggles right panel, callback is triggered after panel animation finishes.</li>
     *      </ul>
     *
     * <strong>Options:</strong>
     * <ul>
     *     <li>context: application context, required for sharing events</li>
     *     <li>main: object with the following properties:
     *         <ul>
     *             <li>label (optional): label of the main section displayed in the action bar. If not specified, button to navigate to main section will not appear in the action bar (in compact mode).</li>
     *             <li>contents: contents of the main section (can be either Widget or Region)</li>
     *         </ul>
     *     </li>
     *     <li>left: object with the following properties:
     *         <ul>
     *             <li>label (optional): label of the left panel displayed in the action bar. If not specified, button to toggle left panel (in full mode) or to navigate to left panel (in compact mode) will not appear in the action bar.</li>
     *             <li>contents: contents of the left panel (can be either Widget or Region)</li>
     *             <li>expanded (optional): flag determining whether the panel should be expanded on init</li>
     *         </ul>
     *     </li>
     *     <li>right: object with the following properties:
     *         <ul>
*             <li>label (optional): label of the right panel displayed in the action bar. If not specified, button to toggle right panel (in full mode) or to navigate to right panel (in compact mode) will not appear in the action bar.</li>
     *             <li>contents: contents of the right panel (can be either Widget or Region)</li>
     *             <li>expanded (optional): flag determining whether the panel should be expanded on init</li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @class SlidingPanel
     * @extends Widget
     */

    return core.Widget.extend({

        View: View,

        init: function (options) {
            this.options = options || {};

            this.leftButton = new LeftPanelButton({
                click: this.toggleLeftPanel.bind(this)
            });
            this.leftButton.hide();

            this.rightButton = new RightPanelButton({
                click: this.toggleRightPanel.bind(this)
            });
            this.rightButton.hide();

            this.previousButton = new LeftPanelButton({
                click: this.previous.bind(this)
            });
            this.previousButton.setLeftArrow();

            this.nextButton = new RightPanelButton({
                click: this.next.bind(this)
            });
            this.nextButton.setRightArrow();

            this.leftShown = false;
            this.rightShown = false;
        },

        onViewReady: function () {
            this.showMainPanelButtons();
            if (this.options.main) {
                this.setMainContents(this.options.main.contents);
            }
            if (this.options.left) {
                this.setLeftPane(this.options.left);
            }
            if (this.options.right) {
                this.setRightPane(this.options.right);
            }


            this.context.eventBus.subscribe('layout:scroll', this.resizeHandler.bind(this));
            core.Window.addEventHandler('resize', this.resizeHandler.bind(this));

            this.context.eventBus.subscribe('topsection:position', function (position, sectionHeight) {
                this.view.getLeftPanelWrapper().setModifier('position', position);
                this.view.getRightPanelWrapper().setModifier('position', position);
                this.changePosition(position, sectionHeight);
            }.bind(this));

            this.context.eventBus.subscribe('topsection:expandcomponent', function (position, sectionHeight) {
                this.view.getLeftPanelWrapper().setModifier('expanded', 'true');
                this.view.getRightPanelWrapper().setModifier('expanded', 'true');
                this.changePosition(position, sectionHeight);
                this.resizeHandler(sectionHeight + 45);


            }.bind(this));

            this.context.eventBus.subscribe('layouts:toggleleftpanel', this.toggleLeftPanel, this);
            this.context.eventBus.subscribe('layouts:togglerightpanel', this.toggleRightPanel, this);
        },
        changePosition: function (position, sectionHeight) {
            if (position === 'fixed') {
                this.setTopPosition(sectionHeight);
            } else {
                this.removeTopPosition();
            }
            this.resizeHandler();
        },
        resizeHandler: function (pos) {
            var top = (isNaN(pos)) ? dom.offset(this.view.getLeftPanelPlaceholder()).top : pos,
                dimensions = dom.getWindowDimensions(),
                windowHeight = dimensions.height,
                height = windowHeight - top - 24;
            this.view.getElement().setStyle('minHeight', height);
            this.view.getLeftPanelWrapper().setStyle('height', height);
            this.view.getRightPanelWrapper().setStyle('height', height);

            if (dimensions.width <= 480 && this.isMobile !== true) {
                this.setMobile();
            } else if (dimensions.width > 480 && this.isMobile !== false) {
                this.setDesktop();
            }
        },
        setTopPosition: function (pos) {
            var container = 45;
            this.view.getRightPanelWrapper().setStyle('top', pos + container);
            this.view.getLeftPanelWrapper().setStyle('top', pos + container);

            this.transitionEvent(this.view.getRightPanelWrapper(), function () {
                this.view.getRightPanelWrapper().setModifier('expanded', 'false');
            }.bind(this));

            this.transitionEvent(this.view.getLeftPanelWrapper(), function () {
                this.view.getLeftPanelWrapper().setModifier('expanded', 'false');
            }.bind(this));

        },
        removeTopPosition: function () {

            this.view.getLeftPanelWrapper().removeStyle('top');
            this.view.getRightPanelWrapper().removeStyle('top');
        },

        onDOMAttach: function () {
            this.resizeHandler();
        },

        setMobile: function () {
            this.context.eventBus.publish('topsection:left', this.previousButton);
            this.context.eventBus.publish('topsection:right', this.nextButton);
            this.isMobile = true;
        },

        setDesktop: function () {
            this.context.eventBus.publish('topsection:left', this.leftButton);
            this.context.eventBus.publish('topsection:right', this.rightButton);
            this.isMobile = false;
        },

        showMainPanelButtons: function () {
            if (this.options.left && this.options.left.label !== undefined) {
                this.previousButton.setLabel(this.options.left.label);
                this.previousButton.show();
            } else {
                this.previousButton.hide();
            }
            if (this.options.right && this.options.right.label !== undefined) {
                this.nextButton.setLabel(this.options.right.label);
                this.nextButton.show();
            } else {
                this.nextButton.hide();
            }
        },

        showLeftPanelButtons: function () {
            this.previousButton.hide();
            if (this.options.main.label) {
                this.nextButton.setLabel(this.options.main.label);
                this.nextButton.show();
            } else {
                this.nextButton.hide();
            }
        },

        showRightPanelButtons: function () {
            this.nextButton.hide();
            if (this.options.main.label) {
                this.previousButton.setLabel(this.options.main.label);
                this.previousButton.show();
            } else {
                this.previousButton.hide();
            }
        },

        previous: function () {
            if (this.rightShown) {
                this.showMainPanelButtons();
                this.toggleRightPanel();
            } else {
                this.showLeftPanelButtons();
                this.toggleLeftPanel();
            }
        },

        next: function () {
            if (this.leftShown) {
                this.showMainPanelButtons();
                this.toggleLeftPanel();
            } else {
                this.showRightPanelButtons();
                this.toggleRightPanel();
            }
        },

        setLeftPane: function (options) {
            this.setLeftPanelContents(options.contents);
            if (options.label) {
                this.leftButton.show();
                this.leftButton.setLabel(options.label);
            }

            if (options.expanded) {
                this.toggleLeftPanel();
                this.showLeftPanelButtons();
            } else {
                this.leftButton.setRightArrow();
            }
        },

        setRightPane: function (options) {
            this.setRightPanelContents(options.contents);
            if (options.label) {
                this.rightButton.show();
                this.rightButton.setLabel(options.label);
            }
            if (options.expanded) {
                this.toggleRightPanel();
                this.showRightPanelButtons();
            } else {
                this.rightButton.setLeftArrow();
            }
        },
        transitionEvent: function (el, cb) {
            var evt = el.addEventHandler(dom.transitionEventName, function () {
                cb();
                evt.destroy();
            });
        },
        toggleLeftPanel: function (cb) {
            var element = this.getElement();
            this.leftShown = !this.leftShown;
            if (element.hasModifier('left')) {
                element.removeModifier('left');
                if (element.hasModifier('right')) {
                    element.setModifier('right');
                }
                this.showMainPanelButtons();
                this.leftButton.setRightArrow();
                this.context.eventBus.publish('layouts:leftHide');

            } else {
                element.setModifier('left', element.hasModifier('right') ? 'right' : undefined);
                this.leftButton.setLeftArrow();
                this.showLeftPanelButtons();
                this.context.eventBus.publish('layouts:leftShow');
            }
            if (cb && typeof cb === "function") {
                this.transitionEvent(this.view.getLeftPanelPlaceholder(), cb);
            }
        },

        toggleRightPanel: function (cb) {
            var element = this.getElement();
            this.rightShown = !this.rightShown;
            if (element.hasModifier('right')) {
                element.removeModifier('right');
                if (element.hasModifier('left')) {
                    element.setModifier('left');
                }
                this.showMainPanelButtons();
                this.rightButton.setLeftArrow();
                this.context.eventBus.publish('layouts:rightHide');
            } else {
                element.setModifier('right', element.hasModifier('left') ? 'left' : undefined);
                this.rightButton.setRightArrow();
                this.showRightPanelButtons();
                this.context.eventBus.publish('layouts:rightShow');
            }
            if (cb && typeof cb === "function") {
                this.transitionEvent(this.view.getRightPanelPlaceholder(), cb);
            }
        },

        setMainContents: function (uiElement) {
            attachUIElement(uiElement, this.view.getPlaceholder());
        },

        setLeftPanelContents: function (uiElement) {
            attachUIElement(uiElement, this.view.getLeftPanelPlaceholder());
        },

        setRightPanelContents: function (uiElement) {
            attachUIElement(uiElement, this.view.getRightPanelPlaceholder());
        }

    });
});

define('layouts/SlidingPanels',['layouts/sliding_panels/SlidingPanels'],function (main) {
                        return main;
                    });

