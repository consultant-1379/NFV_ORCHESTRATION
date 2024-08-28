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

define('styles!layouts/multi_sliding_panels/panel_action_bar/panelActionBar.less',[],function () { return '.elLayouts-PanelActionBar {\n  margin-left: 5px;\n  margin-right: 5px;\n}\n.elLayouts-PanelActionBar-button {\n  min-width: 10px;\n}\n.elLayouts-PanelActionBar-button:not(.elLayouts-PanelActionBar-button_pressed) {\n  -webkit-box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0);\n  -moz-box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0);\n  -ms-box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0);\n  box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0);\n  border-color: transparent;\n  background: none;\n}\n.elLayouts-PanelActionBar-button:not(.elLayouts-PanelActionBar-button_pressed):hover {\n  -webkit-box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0.2);\n  -moz-box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0.2);\n  -ms-box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0.2);\n  box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0.2);\n}\n.elLayouts-PanelActionBar-button_pressed {\n  box-shadow: inset 0 1px 2px 0 rgba(0, 0, 0, 0.1);\n  background-color: #e1e1e1;\n  border-color: #c2c2c2;\n}\n.elLayouts-PanelActionBar-button-label {\n  display: none;\n  vertical-align: middle;\n}\n.elLayouts-PanelActionBar_showLabel .elLayouts-PanelActionBar-button-label {\n  display: inline;\n}\n.elLayouts-PanelActionBar-contextMenu {\n  display: inline-block;\n  margin-right: 5px;\n}\n';});

define('template!layouts/multi_sliding_panels/panel_action_bar/panelActionBar.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        ";
  stack1 = depth0.separator;
  stack2 = helpers['if'];
  tmp1 = self.program(2, program2, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    ";
  return buffer;}
function program2(depth0,data) {
  
  
  return "\n            <div class=\"ebLayout-HeadingCommands-separator\"></div>\n        ";}

function program4(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n    <button type=\"button\" class=\"elLayouts-PanelActionBar-button ebBtn  elLayouts-PanelActionBar-button_";
  stack1 = depth0.value;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this.value", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ";
  stack1 = depth0.type;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\"  title=\"";
  stack1 = depth0.label;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this.label", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">\n        ";
  stack1 = depth0.icon;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n            <span class=\"elLayouts-PanelActionBar-button-label\">";
  foundHelper = helpers.label;
  stack1 = foundHelper || depth0.label;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "label", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</span>\n    </button>\n    ";
  return buffer;}
function program5(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "elLayouts-PanelActionBar-button_";
  stack1 = depth0.type;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this.type", { hash: {} }); }
  buffer += escapeExpression(stack1);
  return buffer;}

function program7(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n        <i class=\"ebIcon ebIcon_";
  stack1 = depth0.icon;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this.icon", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ebIcon_interactive\"></i>\n        ";
  return buffer;}

function program9(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        ";
  stack1 = depth0.separator;
  stack2 = helpers['if'];
  tmp1 = self.program(10, program10, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    ";
  return buffer;}
function program10(depth0,data) {
  
  
  return "\n            <div class=\"ebLayout-HeadingCommands-separator\"></div>\n        ";}

function program12(depth0,data) {
  
  
  return "\n        <div class=\"elLayouts-PanelActionBar-contextMenu\"></div>\n    ";}

  buffer += "<div class=\"elLayouts-PanelActionBar\">\n    ";
  stack1 = depth0.right;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    ";
  stack1 = depth0.shownActions;
  stack2 = helpers.each;
  tmp1 = self.program(4, program4, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    ";
  stack1 = depth0.left;
  stack2 = helpers['if'];
  tmp1 = self.program(9, program9, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    ";
  stack1 = depth0.mobile;
  stack2 = helpers['if'];
  tmp1 = self.program(12, program12, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n</div>";
  return buffer;});});

define('layouts/multi_sliding_panels/panel_action_bar/PanelActionBarView',['jscore/core','template!./panelActionBar.html','styles!./panelActionBar.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function() {
            return template(this.options);
        },

        getStyle: function () {
            return styles;
        },

        show: function (show) {
            this.getElement().setStyle('display', show ? 'block' : 'none');
        },

        getContextMenuDiv: function () {
            return this.getElement().find('.elLayouts-PanelActionBar-contextMenu');
        }

    }, {
        "EL":"elLayouts-PanelActionBar",
        "EL_BUTTON":"elLayouts-PanelActionBar-button"
    });

});

define('layouts/multi_sliding_panels/panel_action_bar/PanelActionBar',['jscore/core','./PanelActionBarView','widgets/ContextMenu'],function (core, View, ContextMenu) {

    return core.Widget.extend({

        view: function() {
            return new View(this.options);
        },

        init: function (options) {

            if(options.side === 'left'){
                this.options.left = true;
            }else{
                this.options.right = true;
            }
            this.options.separator = (options.actions.length > 0);

            if(this.options.mobile){
                this.options.shownActions = (this.getMainAction())?[this.getMainAction()]:[];
                this.options.contextActions = this.options.actions;
                var mainAction = this.getMainAction();
                if(mainAction){
                    this.options.contextActions.splice(this.options.contextActions.indexOf(mainAction), 1);
                    this.options.showLabel = (!mainAction.icon);
                }
            }else{
                this.options.shownActions = this.options.actions;
            }

        },

        onViewReady: function () {
            this.options.shownActions.forEach(function(action){
                var element = this.view.getElement().find('.'+ View.EL_BUTTON + '_' + action.value);
                element.addEventHandler('click', function(){

                    this.onClick(action);

                }.bind(this));
            }.bind(this));

            if(this.options.mobile && this.options.contextActions.length > 0){
                this.initializeContextMenu();
            }

            if(this.options.showLabel){
                this.getElement().setModifier('showLabel');
            }

        },
        onClick: function(action){
            var element =  this.getActionsElement(action.value);
            if(!element){
                this.trigger('show', this, action.value);
                this.clearPressed();
            }else
            if(element && element.hasModifier('pressed')){
                // hide panel
                this.trigger('hide', this, action.value);
                this.clearPressed();
            }else{
                // show panel
                this.trigger('show', this, action.value);
                if(action.type !== 'external'){
                    this.clearPressed();
                    if(element && !this.options.mobile){
                        element.setModifier('pressed');
                    }
                }
            }

        },
        initializeContextMenu: function(){
            var items = [];
            this.options.actions.forEach(function(action){
                var item = {name: action.label, action: function(){
                        this.onClick(action);
                }.bind(this)};
                items.push(item);
            }.bind(this));
            this.contextMenu = new ContextMenu({
                items: items
            });
            this.contextMenu.attachTo(this.view.getContextMenuDiv());
        },
        getMainAction: function(){
            if(this.options.actions.length > 0){
                var mainAction = this.options.actions[0];
                    this.options.actions.forEach(function(action){
                        if(action.primary){
                            mainAction = action;
                        }
                    }.bind(this));
                return mainAction;
            }

        },
        clearPressed: function(){
            this.getActionsElements().forEach(function(element){
                element.removeModifier('pressed');
            }.bind(this));
        },
        getActionsElements: function(){
            var elements = [];
            this.options.shownActions.forEach(function(action){
                var element = this.view.getElement().find('.'+ View.EL_BUTTON + '_' + action.value);
                elements.push(element);
            }.bind(this));

            return elements;
        },
        getActionsElement: function(value){
            var element = false;
            this.options.shownActions.forEach(function(action){
                if(action.value === value){
                    element = this.view.getElement().find('.'+ View.EL_BUTTON + '_' + action.value);
                }
            }.bind(this));

            return element;
        },
        triggerAction: function(value){
            var element = this.getActionsElement(value);
            if(element){
                element.trigger('click');
            }

        }

    });

});

define('styles!layouts/multi_sliding_panels/multiSlidingPanels.less',[],function () { return '.elLayouts-MultiSlidingPanels {\n  overflow: hidden;\n  position: relative;\n  min-height: 100%;\n}\n.elLayouts-MultiSlidingPanels-center {\n  width: 100%;\n  position: relative;\n  padding-top: 6px;\n}\n.elLayouts-MultiSlidingPanels-leftWrapper,\n.elLayouts-MultiSlidingPanels-rightWrapper {\n  width: 0;\n  position: absolute;\n  overflow: hidden;\n  top: 0;\n  height: 100%;\n}\n.elLayouts-MultiSlidingPanels-leftTop,\n.elLayouts-MultiSlidingPanels-leftContents,\n.elLayouts-MultiSlidingPanels-rightTop,\n.elLayouts-MultiSlidingPanels-rightContents {\n  width: 250px;\n}\n.elLayouts-MultiSlidingPanels-leftContents,\n.elLayouts-MultiSlidingPanels-rightContents {\n  height: calc(100% - 40px);\n}\n.elLayouts-MultiSlidingPanels-leftHeader,\n.elLayouts-MultiSlidingPanels-rightHeader {\n  text-overflow: ellipsis;\n  white-space: nowrap;\n  overflow: hidden;\n  width: calc(100% - 20px);\n}\n@media screen and (min-width: 481px) {\n  .elLayouts-MultiSlidingPanels-leftWrapper,\n  .elLayouts-MultiSlidingPanels-rightWrapper {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out;\n  }\n  .elLayouts-MultiSlidingPanels-leftWrapper_position_fixed,\n  .elLayouts-MultiSlidingPanels-rightWrapper_position_fixed {\n    position: fixed;\n  }\n  .elLayouts-MultiSlidingPanels-leftWrapper_expanded.elLayouts-MultiSlidingPanels-leftWrapper_true,\n  .elLayouts-MultiSlidingPanels-leftWrapper_expanded.elLayouts-MultiSlidingPanels-rightWrapper_true,\n  .elLayouts-MultiSlidingPanels-rightWrapper_expanded.elLayouts-MultiSlidingPanels-leftWrapper_true,\n  .elLayouts-MultiSlidingPanels-rightWrapper_expanded.elLayouts-MultiSlidingPanels-rightWrapper_true {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out, height 0.3s, top 0.3s;\n  }\n  .elLayouts-MultiSlidingPanels-center {\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out, 0.3s margin-right ease-in-out;\n  }\n  .elLayouts-MultiSlidingPanels-leftWrapper {\n    margin-left: 0;\n  }\n  .elLayouts-MultiSlidingPanels-rightWrapper {\n    margin-left: 100%;\n  }\n  .elLayouts-MultiSlidingPanels-rightWrapper_position_fixed {\n    top: 77px;\n    left: -55px;\n  }\n  .elLayouts-MultiSlidingPanels-rightTop,\n  .elLayouts-MultiSlidingPanels-rightContents {\n    margin-left: 3.6rem;\n    transition: 0.3s margin-right ease-in-out;\n    margin-right: calc(-250px - 3.6rem);\n  }\n  .elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-center,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-center {\n    width: calc(100% - 250px - 3.6rem);\n    margin-left: calc(250px + 3.6rem);\n  }\n  .elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftWrapper,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftWrapper {\n    width: calc(250px + 3.6rem);\n  }\n  .elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftContents,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftContents,\n  .elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftTop,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftTop {\n    margin-left: 0;\n  }\n  .elLayouts-MultiSlidingPanels_right .elLayouts-MultiSlidingPanels-center,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-center {\n    width: calc(100% - 250px - 3.6rem);\n  }\n  .elLayouts-MultiSlidingPanels_right .elLayouts-MultiSlidingPanels-rightWrapper,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-rightWrapper {\n    width: calc(250px + 3.6rem);\n    margin-left: calc(100% - 250px - 3.6rem);\n  }\n  .elLayouts-MultiSlidingPanels_right .elLayouts-MultiSlidingPanels-rightContents,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-rightContents {\n    margin-right: 0;\n  }\n  .elLayouts-MultiSlidingPanels_left.elLayouts-MultiSlidingPanels_right .elLayouts-MultiSlidingPanels-center {\n    width: calc(100% - 250px - 3.6rem - 250px - 3.6rem);\n  }\n}\n@media screen and (max-width: 480px) {\n  .elLayouts-MultiSlidingPanels-center,\n  .elLayouts-MultiSlidingPanels-leftWrapper,\n  .elLayouts-MultiSlidingPanels-rightWrapper {\n    width: 100%;\n    transition: 0.3s margin-left ease-in-out;\n  }\n  .elLayouts-MultiSlidingPanels-leftWrapper {\n    margin-left: calc(-100% - 3.6rem);\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out;\n  }\n  .elLayouts-MultiSlidingPanels-rightWrapper {\n    margin-left: calc(100% + 3.6rem);\n    transition: 0.3s width ease-in-out, 0.3s margin-left ease-in-out;\n  }\n  .elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-center,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-center {\n    margin-left: calc(100% + 3.6rem);\n  }\n  .elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftWrapper,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-leftWrapper {\n    margin-left: 0;\n  }\n  .elLayouts-MultiSlidingPanels_right .elLayouts-MultiSlidingPanels-center,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-center {\n    margin-left: calc(-100% - 3.6rem);\n  }\n  .elLayouts-MultiSlidingPanels_right .elLayouts-MultiSlidingPanels-rightWrapper,\n  .elLayouts-MultiSlidingPanels_right.elLayouts-MultiSlidingPanels_left .elLayouts-MultiSlidingPanels-rightWrapper {\n    margin-left: 0;\n  }\n  .elLayouts-MultiSlidingPanels-leftTop,\n  .elLayouts-MultiSlidingPanels-leftContents,\n  .elLayouts-MultiSlidingPanels-rightTop,\n  .elLayouts-MultiSlidingPanels-rightContents {\n    min-width: 100%;\n    margin: 0;\n  }\n  .elLayouts-MultiSlidingPanels-leftContents,\n  .elLayouts-MultiSlidingPanels-rightContents {\n    overflow: auto;\n  }\n}\n';});

define('text!layouts/multi_sliding_panels/multiSlidingPanels.html',[],function () { return '<div class="elLayouts-MultiSlidingPanels">\n    <div class="elLayouts-MultiSlidingPanels-leftWrapper">\n        <div class="ebLayout-SectionHeading elLayouts-MultiSlidingPanels-leftTop">\n            <h2 class="elLayouts-MultiSlidingPanels-leftHeader"></h2>\n            <div class="ebLayout-HeadingCommands">\n                <div class="elLayouts-MultiSlidingPanels-top-actions ebLayout-HeadingCommands-block">\n                    <div class="ebLayout-HeadingCommands-iconHolder">\n                        <i class="elLayouts-MultiSlidingPanels-close-left ebIcon ebIcon_close ebIcon_interactive" title="Close"></i>\n                    </div>\n                </div>\n            </div>\n            <div class="ebLayout-clearFix"></div>\n        </div>\n        <div class="elLayouts-MultiSlidingPanels-leftContents eb_scrollbar"></div>\n    </div>\n    <div class="elLayouts-MultiSlidingPanels-center"></div>\n    <div class="elLayouts-MultiSlidingPanels-rightWrapper">\n        <div class="ebLayout-SectionHeading elLayouts-MultiSlidingPanels-rightTop">\n            <h2 class="elLayouts-MultiSlidingPanels-rightHeader"></h2>\n            <div class="ebLayout-HeadingCommands">\n                <div class="elLayouts-MultiSlidingPanels-top-actions ebLayout-HeadingCommands-block">\n                    <div class="ebLayout-HeadingCommands-iconHolder">\n                        <i class="elLayouts-MultiSlidingPanels-close-right ebIcon ebIcon_close ebIcon_interactive" title="Close"></i>\n                    </div>\n                </div>\n            </div>\n            <div class="ebLayout-clearFix"></div>\n        </div>\n        <div class="elLayouts-MultiSlidingPanels-rightContents eb_scrollbar"></div>\n    </div>\n</div>\n';});

define('layouts/multi_sliding_panels/MultiSlidingPanelsView',['jscore/core','text!./multiSlidingPanels.html','styles!./multiSlidingPanels.less'],function (core, template, styles) {
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

        showClosebuttons: function (show) {
            var style = show ? 'block' : 'none';
            this.getCloseLeft().setStyle('display', style);
            this.getCloseRight().setStyle('display', style);
        },

        getPlaceholder: function () {
            return this.getElement().find('.elLayouts-MultiSlidingPanels-center');
        },

        getCloseLeft: function () {
            return this.getElement().find('.elLayouts-MultiSlidingPanels-close-left');
        },
        getCloseRight: function () {
            return this.getElement().find('.elLayouts-MultiSlidingPanels-close-right');
        },
        getLeftPanelPlaceholder: function () {
            return this.getElement().find('.elLayouts-MultiSlidingPanels-leftContents');
        },

        getLeftPanelWrapper: function () {
            return this.getElement().find('.elLayouts-MultiSlidingPanels-leftWrapper');
        },

        getRightPanelPlaceholder: function () {
            return this.getElement().find('.elLayouts-MultiSlidingPanels-rightContents');
        },

        getRightPanelWrapper: function () {
            return this.getElement().find('.elLayouts-MultiSlidingPanels-rightWrapper');
        },
        getLeftHeader: function(){
            return this.getElement().find('.elLayouts-MultiSlidingPanels-leftHeader');
        },
        setLeftHeader: function(text){
            this.getLeftHeader().setText(text);
        },
        getRightHeader: function(){
            return this.getElement().find('.elLayouts-MultiSlidingPanels-rightHeader');
        },
        setRightHeader: function(text){
            this.getRightHeader().setText(text);
        }

    });

});

define('layouts/multi_sliding_panels/MultiSlidingPanels',['jscore/core','./MultiSlidingPanelsView','./panel_action_bar/PanelActionBar','../ext/ext.dom','widgets/Button'],function (core, View, PanelActionBar, dom, Button) {

    /**
     * MultiSlidingPanels provides a main panel and two side panels for user-defined content.
     *
     * <strong>Constructor:</strong>
     * <br/>
     * MultiSlidingPanels(Object options)
     * <br/>
     * <br/>
     * <strong>Events to trigger:</strong>
     *      <ul>
     *          <li><strong>'layouts:closeleftpanel'</strong>: closes left panel, callback is triggered after panel animation finishes or immediately if panel is already closed.</li>
     *          <li><strong>'layouts:closerightpanel'</strong>: closes right panel, callback is triggered after panel animation finishes or immediately if panel is already closed.</li>
     *          <li><strong>'layouts:showpanel'</strong>: Takes an object as a parameter. The object can have the following properties:.</li>
     *          <ul>
     *              <li><strong>header</strong>: the text to be displayed in the header.</li>
     *              <li><strong>content</strong>: Region or Widget to be displayed.</li>
     *              <li><strong>side</strong>: ('left'/'right') panel side. Default is left.</li>
     *              <li><strong>callback</strong>: callback is triggered after panel animation finishes or immediately if panel is already opened.</li>
     *          </ul>
     *      </ul>
     * <strong>Events to listen to:</strong>
     *      <ul>
     *          <li><strong>'layouts:panelaction'</strong>: triggered when a panel action is clicked. It passes the value of the clicked action.</li>
     *      </ul>
     *
     * <strong>Options:</strong>
     * <ul>
     *     <li><strong>context</strong>: application context, required for sharing events</li>
     *     <li><strong>main</strong>: object with the following properties:
     *         <ul>
     *             <li><strong>label</strong> (optional): label of the main section displayed in the action bar. If not specified, button to navigate to main section will not appear in the action bar (in compact mode).</li>
     *             <li><strong>content</strong>: content of the main section (can be either Widget or Region)</li>
     *         </ul>
     *     </li>
     *     <li><strong>left</strong>: array of panel action objects.</li>
     *     <li><strong>right</strong>: array of panel action objects.</li>
     *     <li><strong>showLabel</strong>: Whether or not to show the label of each one of the actions. Default is false.</li>
     * </ul>
     * <strong>Panel Action object:</strong>
     * <ul>
     *     <li><strong>label</strong> (optional): label of the panel displayed in the action bar.</li>
     *     <li><strong>value</strong>: String to identify the action.</li>
     *     <li><strong>icon</strong> (optional): icon displayed in the action bar.</li>
     *     <li><strong>content</strong>: content of the panel (can be either Widget or Region)</li>
     *     <li><strong>expanded</strong> (optional): flag determining whether the panel should be expanded on init</li>
     *     <li><strong>type</strong> (optional): String, pass 'external' if the action is other than showing panel, then listen to the eventBus for 'layouts:panelaction'.</li>
     *     <li><strong>primary</strong> (optional): Boolean, set to true if you want the action to be the one displayed in mobile mode. Only one action will be displayed, the rest are shown in a context menu.</li>
     * </ul>
     * @class MultiSlidingPanels
     * @extends Widget
     * @beta
     */

    return core.Widget.extend({

        View: View,
        /**
         * Initializes members which are used in this widget
         *
         * @method init
         * @private
         */
        init: function (options) {
            this.options = options || {};
            this.options.left = this.options.left || [];
            this.options.right = this.options.right || [];

            // create left action bar
            var leftConfig = {
                actions: this.options.left,
                side: 'left',
                showLabel: this.options.showLabel || false
            };
            this.leftBar = new PanelActionBar(leftConfig);
            this.leftBar.addEventHandler('show', onActionShow.bind(this));
            this.leftBar.addEventHandler('hide', onActionHide.bind(this));

            // create right action bar
            var rightConfig = {
                actions: this.options.right,
                side: 'right',
                showLabel: this.options.showLabel || false

            };
            this.rightBar = new PanelActionBar(rightConfig);
            this.rightBar.addEventHandler('show', onActionShow.bind(this));
            this.rightBar.addEventHandler('hide', onActionHide.bind(this));

            // create mobile action bar
            this.mobileBar = new PanelActionBar({
                actions: this.options.left.concat(this.options.right),
                side: 'right',
                mobile: true,
                showLabel: this.options.showLabel || false
            });
            // proxy actions to maintain consistency of pressed states in action bars
            this.mobileBar.addEventHandler('show', proxyAction.bind(this));
            this.mobileBar.addEventHandler('hide', proxyAction.bind(this));

            // initial state of shown variables
            this.leftShown = false;
            this.rightShown = false;

        },
        /**
         * Initializes view stuff
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            // show main content
            if (this.options.main) {
                setMainContents.call(this, this.options.main.content);
            }

            // add close button event handler
            this.view.getCloseLeft().addEventHandler('click', function () {
                if (this.leftBar && this.leftBar.clearPressed) {
                    this.leftBar.clearPressed();
                    onActionHide.call(this, this.leftBar);
                }
            }.bind(this));

            // add close button event handler
            this.view.getCloseRight().addEventHandler('click', function () {
                if (this.rightBar && this.rightBar.clearPressed) {
                    this.rightBar.clearPressed();
                    onActionHide.call(this, this.rightBar);
                }
            }.bind(this));

            // listen for the scroll and resize events
            this.context.eventBus.subscribe('layout:scroll', resizeHandler.bind(this));
            core.Window.addEventHandler('resize', resizeHandler.bind(this));

            this.context.eventBus.subscribe('topsection:position', function (position, sectionHeight) {
                this.view.getLeftPanelWrapper().setModifier('position', position);
                this.view.getRightPanelWrapper().setModifier('position', position);
                changePosition.call(this, position, sectionHeight);
            }.bind(this));

            this.context.eventBus.subscribe('topsection:expandcomponent', function (position, sectionHeight) {
                this.view.getLeftPanelWrapper().setModifier('expanded', 'true');
                this.view.getRightPanelWrapper().setModifier('expanded', 'true');
                changePosition.call(this, position, sectionHeight);
                resizeHandler.call(this, sectionHeight + 45);
            }.bind(this));

            // listen to custom events in the eventBus
            this.context.eventBus.subscribe('layouts:closeleftpanel', function (cb) {
                closePanel.call(this, 'left', cb)
            }.bind(this));
            this.context.eventBus.subscribe('layouts:closerightpanel', function (cb) {
                closePanel.call(this, 'right', cb)
            }.bind(this));
            this.context.eventBus.subscribe('layouts:showpanel', function (data) {
                var side = data.side || 'left';
                showContent.call(this, side, data.header, data.content, data.callback);
                getBar.call(this, side).clearPressed();
            }.bind(this));

            // Expand panel if 'expanded' isa true
            setExpandedPanels.call(this);

        },
        /**
         * Initializes stuff
         *
         * @method onViewReady
         * @private
         */
        onDOMAttach: function () {
            resizeHandler.call(this);
        },
        /**
         * Returns true if panel is opened.
         *
         * @method isShown
         * @param {string} side 'left' or 'right'
         */
        isShown: function (side) {
            return (side === 'left') ? this.leftShown : this.rightShown;
        }


    });

    function attachUIElement(uiElement, parent) {
        if (uiElement instanceof core.Widget) {
            uiElement.attachTo(parent);
        } else if (uiElement instanceof core.Region) {
            uiElement.start(parent);
        }
    }

    function detachUIElement(uiElement, parent) {
        if (uiElement instanceof core.Widget) {
            uiElement.detach(parent);
        } else if (uiElement instanceof core.Region) {
            uiElement.stop(parent);
        }
    }

    function setExpandedPanels() {
        var expanded;
        if (this.options.left && this.options.left.length > 0 && this.leftBar) {
            expanded = false;
            this.options.left.forEach(function (action) {
                if (action.expanded) {
                    this.leftBar.triggerAction(action.value);
                }
            }.bind(this));
        }
        if (this.options.right && this.options.right.length > 0 && this.rightBar) {
            expanded = false;
            this.options.right.forEach(function (action) {
                if (action.expanded) {
                    this.rightBar.triggerAction(action.value);
                }
            }.bind(this));
        }
    }

    function changePosition(position, sectionHeight) {
        if (position === 'fixed') {
            setTopPosition.call(this, sectionHeight);
        } else {
            removeTopPosition.call(this);
        }
        resizeHandler.call(this);
    }

    function setHeader(side, text) {
        if (side === 'left') {
            this.view.setLeftHeader(text);
        } else {
            this.view.setRightHeader(text);
        }
    }

    function setMobile() {

        this.view.showClosebuttons(false);
        // close secondary panel if opened
        var secondaryPanelSide = getOtherSide.call(this, this.lastPanelSide);
        var secondaryBar = getBar.call(this, secondaryPanelSide);
        if (this.isShown(secondaryPanelSide)) {
            closePanel.call(this, secondaryPanelSide);
            if (secondaryBar) {
                secondaryBar.clearPressed();
            }
        }
        showMobileActions.call(this);
        this.isMobile = true;
    }

    function setDesktop() {
        this.view.showClosebuttons(true);
        this.context.eventBus.publish('topsection:left', this.leftBar);
        this.context.eventBus.publish('topsection:right', this.rightBar);
        this.context.eventBus.publish('topsection:showcontextactions');
        this.isMobile = false;
    }

    function resizeHandler(pos) {
        var top = (isNaN(pos)) ? dom.offset(this.view.getLeftPanelWrapper()).top : pos,
            dimensions = dom.getWindowDimensions(),
            windowHeight = dimensions.height,
            height = windowHeight - top - 24;
        this.view.getElement().setStyle('minHeight', height);
        this.view.getLeftPanelWrapper().setStyle('height', height);
        this.view.getRightPanelWrapper().setStyle('height', height);

        if (dimensions.width <= 480 && this.isMobile !== true) {
            setMobile.call(this);
            this.view.getLeftPanelWrapper().removeStyle('height');
            this.view.getRightPanelWrapper().removeStyle('height');
        } else if (dimensions.width > 480 && this.isMobile !== false) {
            setDesktop.call(this);
        }
    }

    function setPanelContents(side, uiElement) {
        var placeHolder = (side === 'left') ? this.view.getLeftPanelPlaceholder() : this.view.getRightPanelPlaceholder();
        if (getContent.call(this, side) && getContent.call(this, side) !== uiElement) {
            detachUIElement(getContent.call(this, side));
        }
        attachUIElement(uiElement, placeHolder);
        setContent.call(this, side, uiElement);
    }

    function setMainContents(uiElement) {
        attachUIElement(uiElement, this.view.getPlaceholder());
    }

    function removeTopPosition() {
        this.view.getLeftPanelWrapper().removeStyle('top');
        this.view.getRightPanelWrapper().removeStyle('top');
    }

    function setTopPosition(pos) {
        var container = 45;
        this.view.getRightPanelWrapper().setStyle('top', pos + container);
        this.view.getLeftPanelWrapper().setStyle('top', pos + container);

        transitionEvent.call(this, this.view.getRightPanelWrapper(), function () {
            this.view.getRightPanelWrapper().setModifier('expanded', 'false');
        }.bind(this));

        transitionEvent.call(this, this.view.getLeftPanelWrapper(), function () {
            this.view.getLeftPanelWrapper().setModifier('expanded', 'false');
        }.bind(this));

    }

    function showMobileActions() {
        if (this.lastPanelSide && this.isShown(this.lastPanelSide)) {
            showBackButton.call(this);
            this.context.eventBus.publish('topsection:hidecontextactions');
        } else {
            this.context.eventBus.publish('topsection:showcontextactions');

            this.context.eventBus.publish('topsection:right', this.mobileBar);
        }
        this.context.eventBus.publish('topsection:left');
    }

    function transitionEvent(el, cb) {
        var evt = el.addEventHandler(dom.transitionEventName, function () {
            cb();
            evt.destroy();
        });
    }

    function showBackButton(side) {
        this.context.eventBus.publish('topsection:right', createBackButton.call(this, side || this.lastPanelSide));
    }

    function createBackButton(side) {
        var icon = (side === 'left') ? 'rightArrowLarge' : 'leftArrowLarge';
        var position = (side === 'left') ? 'right' : 'left';

        if (this.backButton) {
            this.backButton.destroy();
        }
        this.backButton = new Button({
            caption: this.options.main.label
        });
        this.backButton.addEventHandler('click', function () {
            closeAll.call(this);
            showMobileActions.call(this);
        }.bind(this));
        this.backButton.setIcon({
            position: position,
            name: icon
        });

        return this.backButton;
    }

    function getAction(actions, action) {
        for (var i = 0; i < actions.length; i++) {
            if (actions[i].value === action) {
                return actions[i];
            }
        }
    }

    function togglePanel(side, cb) {
        if (this.isShown(side)) {
            closePanel.call(this, side, cb);
        } else {
            openPanel.call(this, side, cb);
        }

    }

    function proxyAction(actionBar, actionValue) {
        // get the original action bar (left or right), based on the value and trigger the action to maintain consistency of pressed states
        getActionBarByAction.call(this, actionValue).triggerAction(actionValue);
    }

    function setContent(side, content) {
        if (side === 'left') {
            this.leftContents = content;
        } else {
            this.rightContents = content;
        }
    }

    function getContent(side) {
        return (side === 'left') ? this.leftContents : this.rightContents;
    }

    function setShown(side, boolean) {
        if (side === 'left') {
            this.leftShown = boolean;
        } else {
            this.rightShown = boolean;
        }
    }

    function getOtherSide(side) {
        return (side === 'left') ? 'right' : 'left';
    }

    function getBar(side) {
        return (side === 'left') ? this.leftBar : this.rightBar;
    }

    function getActionBarByAction(value) {
        var actionBar = false;
        if (this.options.left.length > 0) {
            this.options.left.forEach(function (action) {
                if (action.value === value) {
                    actionBar = this.leftBar;
                }
            }.bind(this));
        }
        if (!actionBar && this.options.right.length > 0) {
            this.options.right.forEach(function (action) {
                if (action.value === value) {
                    actionBar = this.rightBar;
                }
            }.bind(this));
        }

        return actionBar;

    }

    function onActionHide(actionBar, actionValue) {
        var side = actionBar.options.side;
        if (this.isShown(side)) {
            togglePanel.call(this, side);
        }
    }

    function onActionShow(actionBar, actionValue) {
        var side = actionBar.options.side;
        var actions = this.options[side];
        // get action based on the value
        var action = getAction.call(this, actions, actionValue);
        if (action.content) {
            showContent.call(this, side, action.label, action.content);
        }
        this.context.eventBus.publish('layouts:panelaction', action.value);
    }

    function openPanel(side, cb) {
        if (!this.isShown(side)) {
            if (!this.getElement().hasModifier(side)) {
                this.getElement().setModifier(side);
                this.context.eventBus.publish('layouts:' + side + 'Show');
                setShown.call(this, side, true);
                var wrapper = (side === 'left') ? this.view.getLeftPanelWrapper() : this.view.getRightPanelWrapper();
                if (cb && typeof cb === "function") {
                    transitionEvent.call(this, wrapper, cb);
                }
            }
        } else {
            if (cb && typeof cb === "function") {
                cb();
            }
        }
        this.lastPanelSide = side;
    }

    function closePanel(side, cb) {
        if (this.isShown(side)) {
            if (this.getElement().hasModifier(side)) {
                this.getElement().removeModifier(side);
                this.context.eventBus.publish('layouts:' + side + 'Hide');
                setShown.call(this, side, false);
                var wrapper = (side === 'left') ? this.view.getLeftPanelWrapper() : this.view.getRightPanelWrapper();
                if (cb && typeof cb === "function") {
                    transitionEvent.call(this, wrapper, cb);
                }
            }
        } else {
            if (cb && typeof cb === "function") {
                cb();
            }
        }
    }

    function closeAll() {
        closePanel.call(this, 'left');
        if (this.leftBar) {
            this.leftBar.clearPressed();
        }
        closePanel.call(this, 'right');
        if (this.rightBar) {
            this.rightBar.clearPressed();
        }
    }

    function showContent(side, header, content, cb) {
        var otherSide = getOtherSide.call(this, side);

        if (this.isMobile && this.isShown(otherSide)) {
            // hide the other
            closePanel.call(this, otherSide);
            if (getBar.call(this, otherSide)) {
                getBar.call(this, otherSide).clearPressed();
            }
        }
        if (this.isMobile) {
            showBackButton.call(this, side);
            // scroll to top
            this.context.eventBus.publish('topsection:scrolltotop');
            this.context.eventBus.publish('topsection:hidecontextactions');
        } else {
            this.context.eventBus.publish('topsection:showcontextactions');
        }
        setHeader.call(this, side, header);
        setPanelContents.call(this, side, content);
        openPanel.call(this, side, cb);
    }
});

define('layouts/MultiSlidingPanels',['layouts/multi_sliding_panels/MultiSlidingPanels'],function (main) {
                        return main;
                    });

