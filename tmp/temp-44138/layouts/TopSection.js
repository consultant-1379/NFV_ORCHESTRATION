define('layouts/ext/is_mobile',[],function () {

    var isMobile;

    return function () {
        if (isMobile === undefined) {
            var userAgent = window.navigator.userAgent || window.navigator.vendor || window.opera;
            isMobile = /(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(userAgent) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(userAgent.substr(0, 4));
        }
        return isMobile;
    };
});

define('styles!layouts/top_section/action_bar_breadcrumb/actionBarBreadcrumb.less',[],function () { return '.elLayouts-ActionBarBreadcrumb {\n  position: relative;\n  display: inline-block;\n  margin-left: 0;\n  height: 2.45rem;\n  width: 0;\n  float: left;\n  transition: none;\n}\n.elLayouts-ActionBarBreadcrumb_show_true {\n  width: 2.45rem;\n  margin-left: 0.4rem;\n  transition: margin-left 0.1s, width 0.1s;\n}\n.elLayouts-ActionBarBreadcrumb-button {\n  position: relative;\n  background: url("layouts/resources/layouts/top_section/action_bar_breadcrumb/breadcrumbDropdown.svg") no-repeat center;\n  width: 100%;\n  height: 100%;\n  cursor: pointer;\n  border-radius: 1.2rem;\n  opacity: .8;\n}\n.elLayouts-ActionBarBreadcrumb-button:hover {\n  opacity: 1;\n}\n';});

define('text!layouts/top_section/action_bar_breadcrumb/actionBarBreadcrumb.html',[],function () { return '<div class="elLayouts-ActionBarBreadcrumb">\n    <div class="elLayouts-ActionBarBreadcrumb-button"></div>\n</div>\n';});

define('layouts/top_section/action_bar_breadcrumb/ActionBarBreadcrumbView',['jscore/core','text!./actionBarBreadcrumb.html','styles!./actionBarBreadcrumb.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return styles;
        },

        getButton: function () {
            return this.getElement().find('.elLayouts-ActionBarBreadcrumb-button');
        },

        hide: function () {
            this.getElement().setModifier('show', 'false');
        },

        show: function () {
            this.getElement().setModifier('show', 'true');
        }

    });

});

define('layouts/top_section/action_bar_breadcrumb/ActionBarBreadcrumb',['jscore/core','widgets/ComponentList','./ActionBarBreadcrumbView'],function (core, ComponentList, View) {

    return core.Widget.extend({

        View: View,

        onViewReady: function () {
            this.componentList = new ComponentList({
                items: this.options.items,
                parent: this.getElement(),
                width: 'auto',
                animate: true
            });

            this.view.getButton().addEventHandler('click', function (e) {
                this.componentList.toggle();
                if (this.componentList.isShowing) {
                    if (this.hideTimeout) {
                        clearTimeout(this.hideTimeout);
                        delete this.hideTimeout;
                    }
                    this.hideTimeout = setTimeout(function () {
                        this.componentList.hide();
                        delete this.hideTimeout;
                    }.bind(this), 10000);
                }
            }.bind(this));
        },

        show: function () {
            this.view.show();
        },

        hide: function () {
            this.view.hide();
        }
    });
});

define('styles!layouts/top_section/action_bar_items_compact/actionBarItemsCompact.less',[],function () { return '';});

define('text!layouts/top_section/action_bar_items_compact/actionBarItemsCompact.html',[],function () { return '<select class="elLayouts-QuickActionBar-itemsCompact">\n</select>\n';});

define('layouts/top_section/action_bar_items_compact/ActionBarItemsCompactView',['jscore/core','text!./actionBarItemsCompact.html','styles!./actionBarItemsCompact.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },
        getStyle:function(){
            return styles;
        },

        getValue: function () {
            return this.getElement().getValue();
        },

        addOption: function (option) {
            this.getElement().append(option);
        },

        reset: function () {
            this.getElement().setValue('-1');
        }

    });

});

define('layouts/top_section/action_bar_items_compact/ActionBarItemsCompact',['jscore/core','../../ext/ext.dom','./ActionBarItemsCompactView'],function (core, dom, View) {

    return core.Widget.extend({

        View: View,

        onViewReady: function () {
            var actions = this.options.actions;
            this.getElement().addEventHandler('change', function (value) {
                var index = this.view.getValue();
                if (index === '-1') return;
                this.view.reset();
                var action = actions[index];
                if (action.link) {
                    window.location = action.link;
                } else {
                    action.action();
                }
            }.bind(this));

            this.view.addOption(core.Element.parse('<option value="-1">Actions</option>'));
            actions.forEach(function (action, index) {
                if (action.type === 'separator') {
                    this.view.addOption(core.Element.parse('<option disabled>————————</option>'));
                } else {
                    var option = core.Element.parse('<option></option>');
                    option.setAttribute('value', index);
                    option.setText(action.name);
                    this.view.addOption(option);
                }
            }.bind(this));
        }
    });
});

define('styles!layouts/top_section/action_bar_separator/actionBarSeparator.less',[],function () { return '.elLayouts-actionBarSeparator {\n  display: inline;\n  white-space: nowrap;\n  vertical-align: middle;\n  border-left: 1px solid #CCCCCC;\n  margin: 0 1rem;\n  font-size: 2.4rem;\n}\n';});

define('text!layouts/top_section/action_bar_separator/actionBarSeparator.html',[],function () { return '<div class="elLayouts-actionBarSeparator"></div>\n';});

define('layouts/top_section/action_bar_separator/ActionBarSeparatorView',['jscore/core','text!./actionBarSeparator.html','styles!./actionBarSeparator.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },
        getStyle: function () {
            return styles;
        }

    });

});

define('layouts/top_section/action_bar_separator/ActionBarSeparator',['jscore/core','./ActionBarSeparatorView'],function (core, View) {

    return core.Widget.extend({

        View: View,

        init: function (options) {
        }

    });
});

define('styles!layouts/top_section/action_bar_button/actionBarButton.less',[],function () { return '.elLayouts-ActionBarButton {\n  margin: 0 1rem;\n  display: inline;\n  white-space: nowrap;\n}\n.elLayouts-ActionBarButtonFlat {\n  position: relative;\n  display: inline;\n  vertical-align: middle;\n  background-color: transparent;\n  border: none;\n  min-width: 60px;\n  -webkit-border-radius: 3px;\n  -moz-border-radius: 3px;\n  -ms-border-radius: 3px;\n  border-radius: 3px;\n  -webkit-box-sizing: border-box;\n  -moz-box-sizing: border-box;\n  -ms-box-sizing: border-box;\n  box-sizing: border-box;\n  padding: 0 6px;\n  height: 2.4rem;\n  font-size: 1.2rem;\n  text-decoration: none;\n  text-align: center;\n  cursor: pointer;\n  white-space: nowrap;\n  border: 1px solid transparent;\n}\n.elLayouts-ActionBarButtonFlat > * {\n  vertical-align: middle;\n}\n.elLayouts-ActionBarButtonFlat:hover {\n  border-color: #999;\n}\n.elLayouts-ActionBarButtonFlat-icon {\n  width: 14px !important;\n  height: 14px !important;\n  line-height: 14px !important;\n  margin-left: 5px;\n}\n';});

define('template!layouts/top_section/action_bar_button/actionBarButton.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "<button type=\"button\" class=\"elLayouts-ActionBarButtonFlat\">";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  stack2 = helpers['if'];
  tmp1 = self.program(2, program2, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "<span class=\"elLayouts-PanelButton-text\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</span></button>\n";
  return buffer;}
function program2(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "<i class=\"ebIcon ebIcon_";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "icon", { hash: {} }); }
  buffer += escapeExpression(stack1) + " elLayouts-ActionBarButtonFlat-icon\"></i> ";
  return buffer;}

function program4(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "<button type=\"button\" class=\"ebBtn ";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += " elLayouts-ActionBarButton\">";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "<span>";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</span></button>\n";
  return buffer;}
function program5(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "ebBtn_color_";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "color", { hash: {} }); }
  buffer += escapeExpression(stack1);
  return buffer;}

function program7(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "<i class=\"ebIcon ebIcon_";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "icon", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"></i> ";
  return buffer;}

  foundHelper = helpers.flat;
  stack1 = foundHelper || depth0.flat;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.program(4, program4, data);
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n";
  return buffer;});});

define('layouts/top_section/action_bar_button/ActionBarButtonView',['jscore/core','template!./actionBarButton.html','styles!./actionBarButton.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template(this.options);
        },

        getStyle: function () {
            return styles;
        }

    });

});

define('layouts/top_section/action_bar_button/ActionBarButton',['jscore/core','./ActionBarButtonView'],function (core, View) {

    return core.Widget.extend({

        init: function (options) {
            this.options = options;
            this.view = new View(options);
        },

        onViewReady: function () {
            this.getElement().addEventHandler('click', function (e) {
                e.preventDefault();
                this.options.action.apply(this.options.action, arguments);
            }.bind(this));
        }

    });
});

define('styles!layouts/top_section/action_bar_item/actionBarItem.less',[],function () { return '.elLayouts-ActionBarItem {\n  line-height: 2.4rem;\n  padding: 0 1rem;\n  display: inline;\n  white-space: nowrap;\n  vertical-align: middle;\n}\n';});

define('template!layouts/top_section/action_bar_item/actionBarItem.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, foundHelper, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;


  buffer += "<a class=\"elLayouts-ActionBarItem\" href=\"";
  foundHelper = helpers.link;
  stack1 = foundHelper || depth0.link;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "link", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\" title=\"";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</a>\n";
  return buffer;});});

define('layouts/top_section/action_bar_item/ActionBarItemView',['jscore/core','template!./actionBarItem.html','styles!./actionBarItem.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template(this.options);
        },

        getStyle: function () {
            return styles;
        }

    });

});

define('layouts/top_section/action_bar_item/ActionBarItem',['jscore/core','./ActionBarItemView'],function (core, View) {

    return core.Widget.extend({

        init: function (options) {
            this.options = options;
            this.view = new View(options);
        },

        onViewReady: function () {
            if (this.options.action) {
                this.getElement().addEventHandler('click', function (e) {
                    e.preventDefault();
                    this.options.action.apply(this.options.action, arguments);
                }.bind(this));
            }
        }

    });
});

define('styles!layouts/top_section/action_bar_items/actionBarItems.less',[],function () { return '.elLayouts-QuickActionBar-items {\n  position: relative;\n  left: -150%;\n  transition: .2s left;\n  white-space: nowrap;\n}\n.elLayouts-QuickActionBar-items_show_true {\n  left: 0;\n}\n';});

define('text!layouts/top_section/action_bar_items/actionBarItems.html',[],function () { return '<div class="elLayouts-QuickActionBar-items"></div>\n';});

define('layouts/top_section/action_bar_items/ActionBarItemsView',['jscore/core','text!./actionBarItems.html','styles!./actionBarItems.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },
        getStyle:function(){
            return styles;
        }

    });

});

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

define('layouts/top_section/action_bar_items/ActionBarItems',['jscore/core','../../ext/ext.dom','./ActionBarItemsView','../action_bar_item/ActionBarItem','../action_bar_button/ActionBarButton','../action_bar_separator/ActionBarSeparator'],function (core, dom, View, ActionBarItem, ActionBarButton, ActionBarSeparator) {

    var getLeftValue = function () {
        return window.getComputedStyle(this.getElement()._getHTMLElement()).left;
    };

    var destroyPreviousAnimationCallback = function () {
        if (this.evt) {
            this.evt.destroy();
            delete this.evt;
        }
    };

    var onAnimationStop = function (cb) {
        destroyPreviousAnimationCallback.call(this);
        this.evt = this.getElement().addEventHandler(dom.transitionEventName, function () {
            destroyPreviousAnimationCallback.call(this);
            if (cb) {
                cb();
            }
        }, this);
    };

    return core.Widget.extend({

        View: View,

        onDOMAttach: function () {
            this.show();
        },
        onViewReady: function () {
            var actions = this.options.actions;
            actions.forEach(function (action, index) {
                var widget;
                if (action.type === 'separator') {
                    widget = new ActionBarSeparator;
                } else if (action.type === 'button') {
                    widget = new ActionBarButton(action);
                } else {
                    widget = new ActionBarItem(action);
                }
                widget.attachTo(this.getElement());
            }.bind(this));

            core.Window.addEventHandler('resize', function () {
                this.show();
            }.bind(this));
        },

        show: function (cb) {
            if (!this.getElement().hasModifier('show', 'true')) {
                this.animationStartingValue = getLeftValue.call(this);
                onAnimationStop.call(this, cb);
                this.getElement().setModifier('show', 'true');
            }
        },

        hide: function (cb) {
            if (this.getElement().hasModifier('show', 'true')) {
                if (getLeftValue.call(this) === this.animationStartingValue) {
                    destroyPreviousAnimationCallback.call(this);
                    cb();
                } else {
                    onAnimationStop.call(this, cb);
                }
                this.getElement().setModifier('show', 'false');
            }
        }
    });
});

define('styles!layouts/top_section/topSection.less',[],function () { return '.compactCommands .elLayouts-QuickActionBar-commandsBlock {\n  visibility: hidden;\n  position: absolute;\n}\n.compactCommands .elLayouts-QuickActionBar-commandsCompact {\n  display: block;\n}\n.elLayouts-clearFix {\n  clear: both;\n}\n.elLayouts-Wrapper {\n  position: relative;\n  width: 100%;\n  height: 100%;\n  overflow: auto;\n}\n.elLayouts-Wrapper-backToTop {\n  position: fixed;\n  border: solid 1px #ccc;\n  background: #efefef;\n  padding: 5px;\n  box-shadow: 0 0 3px #ccc;\n  border-radius: 5px;\n  bottom: -30px;\n  opacity: 0;\n  right: 55px;\n  cursor: pointer;\n  transition: bottom 0.5s, opacity 0.3s, right 0.6s ease-in-out;\n}\n.elLayouts-Wrapper-backToTop_show_true {\n  bottom: 10px;\n  opacity: 1;\n  display: block;\n}\n.elLayouts-Wrapper-backToTop_moveRight_true {\n  right: 345px;\n}\n.elLayouts-TopSection {\n  position: relative;\n  margin-left: 40px;\n  margin-right: 40px;\n}\n@media screen and (max-width: 768px) {\n  .elLayouts-TopSection {\n    margin-left: 2.4rem;\n    margin-right: 2.4rem;\n  }\n}\n@media screen and (max-width: 480px) {\n  .elLayouts-TopSection {\n    margin-left: 1.2rem;\n    margin-right: 1.2rem;\n  }\n}\n.elLayouts-TopSection-breadcrumb {\n  padding-top: 1.2rem;\n  margin-bottom: -1.2rem;\n}\n.elLayouts-TopSection-title {\n  padding: 0;\n  margin-top: 1.2rem;\n  margin-bottom: 1.2rem;\n}\n.elLayouts-QuickActionBar {\n  height: 32px;\n  transition: height 0.2s;\n  position: relative;\n}\n.elLayouts-QuickActionBar-contents {\n  padding: 0.4rem 0;\n  background-color: #ededed;\n  position: absolute;\n  width: 100%;\n  line-height: 0;\n  box-sizing: border-box;\n  -webkit-border-radius: 3px;\n  -moz-border-radius: 3px;\n  -ms-border-radius: 3px;\n  border-radius: 3px;\n}\n.elLayouts-QuickActionBar-contents_position_fixed {\n  position: fixed;\n  top: 45px;\n  left: 0;\n  right: 0;\n  z-index: 10;\n  width: 100%;\n  max-width: 100%;\n  -webkit-border-radius: 0px;\n  -moz-border-radius: 0px;\n  -ms-border-radius: 0px;\n  border-radius: 0px;\n  border-bottom: 1px solid #CCCCCC;\n}\n.elLayouts-QuickActionBar-iconHolder {\n  display: inline-block;\n  vertical-align: middle;\n  height: 1.6rem;\n}\n.elLayouts-QuickActionBar-separator {\n  display: inline-block;\n  vertical-align: middle;\n  border-left: 1px solid #CCCCCC;\n  margin: 0;\n  height: 2.4rem;\n}\n.elLayouts-QuickActionBar-breadcrumb {\n  height: 2.4rem;\n  float: left;\n}\n.elLayouts-QuickActionBar-commands {\n  display: inline-block;\n  margin: 0;\n  overflow: hidden;\n}\n.elLayouts-QuickActionBar-commands-separator {\n  display: inline-block;\n  vertical-align: middle;\n  border-left: 1px solid #CCCCCC;\n  margin: 0 8px;\n  height: 2.4rem;\n}\n.elLayouts-QuickActionBar-commandsBlock {\n  vertical-align: middle;\n  display: inline-block;\n}\n.elLayouts-QuickActionBar-commandsBlock > * {\n  margin-left: 0.4rem;\n  margin-right: 0.4rem;\n  vertical-align: middle;\n}\n.elLayouts-QuickActionBar-commandsBlock :last-child {\n  margin-right: 0;\n}\n.elLayouts-QuickActionBar-commandsCompact {\n  margin-left: 1.1rem;\n  margin-right: 0.4rem;\n  vertical-align: middle;\n  display: none;\n}\n.elLayouts-QuickActionBar-commands_compact .elLayouts-QuickActionBar-commandsBlock {\n  visibility: hidden;\n  position: absolute;\n}\n.elLayouts-QuickActionBar-commands_compact .elLayouts-QuickActionBar-commandsCompact {\n  display: block;\n}\n@media screen and (max-width: 480px) {\n  .elLayouts-QuickActionBar-commands .elLayouts-QuickActionBar-commandsBlock {\n    visibility: hidden;\n    position: absolute;\n  }\n  .elLayouts-QuickActionBar-commands .elLayouts-QuickActionBar-commandsCompact {\n    display: block;\n  }\n}\n.elLayouts-QuickActionBar-commands_show_false {\n  display: none;\n}\n.elLayouts-QuickActionBar-left {\n  float: left;\n  height: 100%;\n}\n.elLayouts-QuickActionBar-center {\n  float: left;\n  height: 100%;\n}\n.elLayouts-QuickActionBar-right {\n  float: right;\n  height: 100%;\n}\n.elLayouts-QuickActionBar-component {\n  height: 0;\n  overflow: hidden;\n  transition: height 0.2s, margin 0.2s;\n}\n.elLayouts-QuickActionBar-component_show_true {\n  margin-top: 0.4rem;\n  margin-bottom: calc(1px - 0.4rem);\n}\n.ebContainer-clearFix {\n  zoom: 1;\n}\n.ebContainer-clearFix:before,\n.ebContainer-clearFix:after {\n  content: "\\0020";\n  display: block;\n  height: 0;\n  overflow: hidden;\n}\n.ebContainer-clearFix:after {\n  clear: both;\n}\n';});

define('text!layouts/top_section/topSection.html',[],function () { return '<div class="elLayouts-Wrapper">\n    <div class="elLayouts-TopSection">\n        <div class="elLayouts-TopSection-breadcrumb"></div>\n        <h1 class="elLayouts-TopSection-title"></h1>\n\n        <div class="elLayouts-QuickActionBar">\n            <div class="elLayouts-QuickActionBar-contents">\n                <div class="elLayouts-QuickActionBar-breadcrumb"></div>\n\n                <div class="elLayouts-QuickActionBar-left">\n                </div>\n                <div class="elLayouts-QuickActionBar-center">\n                    <div class="elLayouts-QuickActionBar-commands">\n                        <div class="elLayouts-QuickActionBar-commandsBlock">\n                        </div>\n                        <div class="elLayouts-QuickActionBar-commandsCompact">\n                        </div>\n                    </div>\n                </div>\n                <div class="elLayouts-QuickActionBar-right">\n                </div>\n                <div class="elLayouts-clearFix"></div>\n\n                <div class="elLayouts-QuickActionBar-component"></div>\n            </div>\n        </div>\n\n        <div class="elLayouts-TopSection-placeholder">\n\n        </div>\n    </div>\n    <div class="elLayouts-Wrapper-backToTop">\n        <i class="ebIcon ebIcon_upArrowLarge"></i>\n    </div>\n</div>\n';});

define('layouts/top_section/TopSectionView',['jscore/core','text!./topSection.html','styles!./topSection.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return styles;
        },

        setTitle: function (text) {
            this.getElement().find('.elLayouts-TopSection-title').setText(text);
        },
        getNavigationBar: function () {
            return this.getElement().find('.elLayouts-TopSection');
        },
        getBreadcrumb: function () {
            return this.getElement().find('.elLayouts-TopSection-breadcrumb');
        },

        getDropdown: function () {
            return this.getElement().find('.ebDropdown-body');
        },

        getActionBar: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-contents');
        },
        getActionBarBreadcrumb: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-breadcrumb')
        },

        getActionBarContainer: function () {
            return this.getElement().find('.elLayouts-QuickActionBar');
        },

        getCommands: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-commandsBlock');
        },

        getCommandsCompact: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-commandsCompact');
        },

        removeAllActions: function () {
            this.getFullViewActionContainer().children().forEach(function (child) {
                child.detach();
            });
        },

        getPlaceholder: function () {
            return this.getElement().find('.elLayouts-TopSection-placeholder');
        },

        getLeftPlaceholder: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-left');
        },

        getCommandsPlaceholder: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-commands');
        },

        getRightPlaceholder: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-right');
        },
        getBackToTop: function () {
            return this.getElement().find('.elLayouts-Wrapper-backToTop');
        },
        getTopComponent: function () {
            return this.getElement().find('.elLayouts-QuickActionBar-component');
        },
        showCommandsPlaceholder: function(){
            this.getCommandsPlaceholder().removeModifier('show');
        },
        hideCommandsPlaceholder: function(){
            this.getCommandsPlaceholder().setModifier('show', 'false');
        },
        actionBarSetHeight: function (topBarHeight, topBarComponentHeight) {
            var height = topBarHeight + topBarComponentHeight;
            if (topBarComponentHeight !== 0) {
                this.getTopComponent().setModifier('show', 'true');

            } else{
                this.getTopComponent().setModifier('show', 'false');
            }
            this.getTopComponent().setStyle({height: topBarComponentHeight})

            this.getActionBarContainer().setStyle({height: height});
            return height;
        }

    });

});

define('layouts/top_section/TopSection',['jscore/core','./TopSectionView','widgets/Breadcrumb','widgets/Dropdown','./action_bar_items/ActionBarItems','./action_bar_items_compact/ActionBarItemsCompact','./action_bar_breadcrumb/ActionBarBreadcrumb','../ext/ext.dom','../ext/is_mobile','jscore/ext/utils/base/underscore'],function (core, View, Breadcrumb, Dropdown, ActionBarItems, ActionBarItemsCompact, ActionBarBreadcrumb, dom, isMobile, _) {

    function attachUIComponent(uiComponent, parent) {
        if (uiComponent instanceof core.Widget) {
            uiComponent.attachTo(parent);
        } else if (uiComponent instanceof core.Region) {
            uiComponent.start(parent);
        }
    }

    /**
     * TopSection provides breadcrumbs, application title and action bar, as well
     * as placeholder for the application main contents block.
     *
     * <strong>Constructor:</strong>
     * TopSection(Object options)
     *
     * <strong>Options:</strong>
     * <ul>
     *     <li>breadcrumb: breadcrumb data (as per Breadcrumb widget specs)</li>
     *     <li>context: application context, required for sharing events</li>
     *     <li>title: application title to be displayed in the title section</li>
     *     <li>showBackToTop: if true and action bar is sticky, a button on the bottom right appears to bring the user to the top of the page. (default true).</li>
     *     <li>actionsCaption: caption for the action dropdown, default is 'Actions'. Should be used for internationalization.</li>
     *     <li>defaultActions - defines a list of app-wide actions. An action is an object with the following fields:
     *     <ul>
     *         <li>name: string containing action name.</li>
     *         <li>link (only for links): URL link should be pointing to.</li>
     *         <li>action: a function to be executed when item is activated.</li>
     *         <li>type (optional): can be one of the following: button, link, separator. If not specified, link will be used as a default value.</li>
     *         <li>icon (optional, only for buttons): if specified, icon with corresponding name from the asset library will be placed inside the button.</li>
     *         <li>flat (optional, only for buttons): if set to true, button will be displayed without its chrome. Should be used for secondary buttons.</li>
     *     </ul>
     * Example:
     *  <pre>
     *   var topSection = new TopSection({
     *       context: this.getContext(),
     *       title: 'Navigation Bar',
     *       breadcrumb: this.options.breadcrumb,
     *       defaultActions: [
     *           {
     *               name: 'Say hello',
     *               action: function () {
     *                   console.log('Hello')
     *               }
     *           },
     *           {
     *               type: 'separator'
     *           },
     *           {
     *               name: 'Say world',
     *               action: function () {
     *                   console.log('World')
     *               }
     *           }
     *       ]
     *   );
     *   </pre>
     *     This will create a menu with two actions split by a separator.
     *     </li>
     * </ul>
     *
     * <strong>Events published:</strong>
     * <ul>
     *     <li>topsection:position - published when action bar changes its position from absolute to fixed or back.
     *     First argument will be either <strong>absolute</strong> or <strong>fixed</strong>.</li>
     *     <li>topsection:pagebottom - published when page has been scrolled to the bottom.</li>
     * </ul>
     *
     * <strong>Events listening to:</strong>
     * <ul>
     *     <li>topsection:contextactions - is used to define context-specific actions, e.g. when selection is performed, which will temporarily override actions defined in the constructor. Format is the same as described above.</li>
     *     <li>topsection:leavecontext - indicates that there is no currently active context (e.g selection), which will make actions defined via constructor options to appear again.</li>
     *     <li>topsection:left - attach a widget provided as a first argument to the left placeholder of an action bar.</li>
     *     <li>topsection:right - attach a widget provided as a first argument to the right placeholder of an action bar.</li>
     *     <li>topsection:scrolltotop - scroll page to the top. If a callback is passed as an argument, it will be exesued executed when scrolling is complete.</li>
     * </ul>
     * @class TopSection
     * @extends Widget
     */
    return core.Widget.extend({

        View: View,

        init: function (options) {
            this.eventBus = this.context.eventBus;
            this.defaultActions = [];
            this.actionBarPosition = 'absolute';
        },
        onDOMAttach: function () {
            this.setActionBar();
            this._parent.setStyle({
                position: 'relative',
                width: '100%',
                height: '100%'
            });
            this.checkActionsSize();
        },
        onViewReady: function () {
            this.view.setTitle(this.options.title);

            if (this.options && this.options.topComponent) {
                this.attachComponent();
            }

            this.setupBreadcrumbEvents();
            this.backToTop();

            this.eventBus.subscribe('topsection:left', function (widget) {
                this.view.getLeftPlaceholder().children().forEach(function (child) {
                    child.detach();
                });
                if (widget) {
                    widget.attachTo(this.view.getLeftPlaceholder());
                }
            }, this);

            this.eventBus.subscribe('topsection:right', function (widget) {
                this.view.getRightPlaceholder().children().forEach(function (child) {
                    child.detach();
                });
                if (widget) {
                    widget.attachTo(this.view.getRightPlaceholder());
                }
            }, this);

            if (this.options.defaultActions) {
                this.setDefaultActions(this.options.defaultActions);
            }

            this.eventBus.subscribe('topsection:contextactions', function (actions) {
                this.setCurrentActions(actions);
            }, this);

            this.eventBus.subscribe('topsection:leavecontext', function () {
                this.setCurrentActions(this.defaultActions);
            }, this);

            this.eventBus.subscribe('topsection:showcontextactions', function () {
                this.view.showCommandsPlaceholder();
            }, this);

            this.eventBus.subscribe('topsection:hidecontextactions', function () {
                this.view.hideCommandsPlaceholder();
            }, this);

            if (this.options.breadcrumb) {
                this.setBreadcrumb();
            }
            this.eventBus.subscribe('topsection:showcomponent', this.showComponent, this);

            var container = this.view.getElement();

            container.addEventHandler('scroll', function (e) {
                if (container.getPosition().bottom === this.view.getNavigationBar().getPosition().bottom) {
                    this.eventBus.publish('topsection:pagebottom', e);
                }

                this.eventBus.publish('layout:scroll', e, this.sectionHeight);
                this.actionBarPositionUpdate();
                this.checkActionsSize();
            }.bind(this));

            this.eventBus.subscribe('topsection:scrolltotop', this.scrollTop, this);

            addWindowEvtHandler.call(this);
        },

        /**
         * Overrides method from widget.
         * Executes before destroy, remove event handlers.
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function () {
            removeWindowEvtHandler.call(this);
        },

        scrollTop: function (cb, posTop) {
            var el = this.getElement(),
                top = (posTop || posTop === 0) ? posTop : 101;

            if (el._getHTMLElement().scrollTop > top) {
                dom.animate(el, { scrollTop: top }, 500, cb);
            } else if (cb) {
                cb();
            }
        },

        actionBarPositionUpdate: function () {
            var topsection = this.view.getActionBar(),
                actionBarContainer = this.view.getActionBarContainer(),
                navigationBarPosition = dom.position(this.view.getNavigationBar()).top,
                actionBarPosition = dom.position(actionBarContainer).top;

            if (actionBarPosition + navigationBarPosition < 0) {
                if (this.actionBarPosition !== 'fixed') {
                    this.actionBarPosition = 'fixed';
                    topsection.setModifier('position', 'fixed');
                    this.eventBus.publish('topsection:position', 'fixed', this.sectionHeight);
                }
            } else {
                if (this.actionBarPosition !== 'absolute') {
                    this.actionBarPosition = 'absolute';
                    topsection.setModifier('position', 'absolute');
                    this.eventBus.publish('topsection:position', 'absolute', this.sectionHeight);
                }
            }
        },

        setupBreadcrumbEvents: function() {
            this.eventBus.subscribe('topsection:position', function (pos) {
                if (this.actionBarBreadcrumb) {
                    if (pos === 'fixed') {
                        this.actionBarBreadcrumb.show();
                    } else {
                        this.actionBarBreadcrumb.hide();
                    }
                }
            }, this);
        },

        backToTop: function () {
            var button = this.view.getBackToTop();

            if (this.options.showBackToTop !== false) {
                this.eventBus.subscribe('topsection:position', function (pos) {
                    var show = pos === 'fixed'? 'true' : 'false';
                    button.setModifier('show', show);
                }, this);
                this.eventBus.subscribe('layouts:rightShow', function () {
                    button.setModifier('moveRight', 'true');
                }, this);
                this.eventBus.subscribe('layouts:rightHide', function () {
                    button.setModifier('moveRight', 'false');
                }, this);

                button.addEventHandler('click', function () {
                    this.scrollTop.call(this, false, 0);
                }, this);
            }
        },

        /**
         * Sets content of the area below the top section.
         *
         * @method setContent
         * @param {core.Widget|core.Region} options
         * @example
         *    topSection.setContent(new MyRegion({
         *        context: this.getContext()
         *    }));
         */
        setContent: function (uiElement) {
            if (this._uiElement === uiElement) {
                return;
            }
            if (this._uiElement) {
                this._uiElement.detach();
            }
            if (uiElement instanceof core.Widget) {
                uiElement.attachTo(this.view.getPlaceholder());
            } else if (uiElement instanceof core.Region) {
                if (uiElement._parent) {
                    uiElement.attach(this.view.getPlaceholder());
                } else {
                    uiElement.start(this.view.getPlaceholder());
                }
            }
            this._uiElement = uiElement;
        },

        setDefaultActions: function (actions) {
            this.defaultActions = actions;
            this.setCurrentActions(actions);
        },

        setCurrentActions: function (actions) {
            this._actionsToShow = actions;
            if (this.actionBarItems !== undefined) {
                this.actionBarItems.hide(function () {
                    this.actionBarItems.destroy();
                    delete this.actionBarItems;
                    this.actionBarItemsCompact.destroy();
                    delete this.actionBarItemsCompact;
                    this.setItems(this._actionsToShow);
                }.bind(this))
            } else {
                this.setItems(this._actionsToShow);
            }
        },

        checkActionsSize: function () {
            var containerSize = dom.outerWidth(this.view.getActionBar()),
                remainingSize = dom.outerWidth(this.view.getLeftPlaceholder(), true) +
                    dom.outerWidth(this.view.getRightPlaceholder(), true) +
                    dom.outerWidth(this.view.getActionBarBreadcrumb(), true);
            var commandsSize = dom.outerWidth(this.view.getCommands(), true);
            if (commandsSize + remainingSize > containerSize) {
                this.view.getCommandsPlaceholder().setModifier('compact');
            } else {
                this.view.getCommandsPlaceholder().removeModifier('compact');
            }
        },

        setItems: function (actions) {
            var oldFormat = actions.some(function (action) {
                return Array.isArray(action);
            });

            if (oldFormat) {
                actions = _.flatten(actions.map(function (actionGroup, index) {
                    var actions = _.clone(actionGroup);
                    if (index > 0) {
                        actions.unshift({type: 'separator'});
                    }
                    return actions;
                }), true)
            }

            this.actionBarItems = new ActionBarItems({actions: actions});
            this.actionBarItems.attachTo(this.view.getCommands());
            if (isMobile()) {
                this.actionBarItemsCompact = new ActionBarItemsCompact({actions: actions});
                this.actionBarItemsCompact.attachTo(this.view.getCommandsCompact());
            } else {
                var compactActions = actions.map(function (action) {
                    action = _.clone(action);
                    if (action.type !== 'separator') {
                        delete action.type;
                    }
                    if (action.link) {
                        action.action = function () {
                            window.location = action.link;
                        }
                    }
                    return action;
                });
                this.actionBarItemsCompact = new Dropdown({caption: this.options.actionsCaption || 'Actions', items: compactActions});
                this.actionBarItemsCompact.attachTo(this.view.getCommandsCompact());
            }
            this.checkActionsSize();
        },

        setBreadcrumb: function () {
            this.breadcrumb = new Breadcrumb({data: this.options.breadcrumb});
            this.breadcrumb.attachTo(this.view.getBreadcrumb());

            var breadcrumbOptions = {
                items: [{
                    header: this.options.title,
                    items: this.options.breadcrumb.slice(0, -1).map(function (item) {
                        return {
                            link: item.url,
                            name: item.name
                        };
                    })
                }]
            };
            this.actionBarBreadcrumb = new ActionBarBreadcrumb(breadcrumbOptions);
            this.actionBarBreadcrumb.attachTo(this.view.getActionBarBreadcrumb());
        },
        attachComponent: function () {
            attachUIComponent(this.options.topComponent, this.view.getTopComponent());
        },
        setHeights: function () {
            if (this.options && this.options.topComponent) {
                this.topComponentHeight = dom.getElementDimensions(this.options.topComponent.getElement()).height;
            } else {
                this.topComponentHeight = 0;
            }
            this.topBarHeight = dom.getElementDimensions(this.view.getActionBar()).height;


        },
        setActionBar: function () {
            this.setHeights();
            this.sectionHeight = this.view.actionBarSetHeight(this.topBarHeight, ((this.options && this.options.topComponentShow === true) ? this.topComponentHeight : 0));
        },
        showComponent: function (show) {
            var componentHeight;
            if (this.options && this.options.topComponent) {
                if (show === true) {
                    componentHeight = this.topComponentHeight;
                } else {
                    componentHeight = 0;
                }
                this.sectionHeight = this.view.actionBarSetHeight(this.topBarHeight, componentHeight);
                this.context.eventBus.publish('topsection:expandcomponent', this.actionBarPosition, this.sectionHeight);

            }
        }
    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function addWindowEvtHandler() {
        /*jshint validthis:true */
        if (!this._windowResizeForCompactEvtId) {
            this._windowResizeForCompactEvtId = core.Window.addEventHandler('resize', this.checkActionsSize.bind(this));
        }
        if (!this._windowResizeForActionEvtId) {
            this._windowResizeForActionEvtId = core.Window.addEventHandler('resize', this.actionBarPositionUpdate.bind(this));
        }
    }

    function removeWindowEvtHandler() {
        /*jshint validthis:true */
        if (this._windowResizeForActionEvtId) {
            core.Window.removeEventHandler(this._windowResizeForActionEvtId);
            delete this._windowResizeForActionEvtId;
        }
        if (this._windowResizeForCompactEvtId) {
            core.Window.removeEventHandler(this._windowResizeForCompactEvtId);
            delete this._windowResizeForCompactEvtId;
        }
    }
});

define('layouts/TopSection',['layouts/top_section/TopSection'],function (main) {
                        return main;
                    });

