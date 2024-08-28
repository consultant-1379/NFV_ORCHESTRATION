/* Copyright (c) Ericsson 2014 */

define('styles!widgets/ComponentList/_componentList.less',[],function () { return '.elWidgets-ComponentList_animated {\n  opacity: 0;\n  transition: opacity .3s;\n}\n.elWidgets-ComponentList_show {\n  opacity: 1;\n}\n';});

define('template!widgets/ComponentList/_groupedComponentList.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n    <li class=\"ebComponentList-group\">\n        <div class=\"ebComponentList-group-header\">";
  foundHelper = helpers.header;
  stack1 = foundHelper || depth0.header;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "header", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</div>\n        <ul class=\"ebComponentList-inner\">\n          ";
  foundHelper = helpers.items;
  stack1 = foundHelper || depth0.items;
  stack2 = helpers.each;
  tmp1 = self.program(2, program2, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        </ul>\n    </li>\n";
  return buffer;}
function program2(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n              ";
  foundHelper = helpers._action;
  stack1 = foundHelper || depth0._action;
  stack2 = helpers['if'];
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n              ";
  foundHelper = helpers.link;
  stack1 = foundHelper || depth0.link;
  stack2 = helpers['if'];
  tmp1 = self.program(6, program6, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n              ";
  foundHelper = helpers._separator;
  stack1 = foundHelper || depth0._separator;
  stack2 = helpers['if'];
  tmp1 = self.program(9, program9, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n          ";
  return buffer;}
function program3(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "<li class=\"ebComponentList-item\" ";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  stack2 = helpers['if'];
  tmp1 = self.program(4, program4, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += ">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</li>";
  return buffer;}
function program4(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " title=\"";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"";
  return buffer;}

function program6(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "<li class=\"ebComponentList-item\" ";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "><a class=\"ebComponentList-link\" href=\"";
  foundHelper = helpers.link;
  stack1 = foundHelper || depth0.link;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "link", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</a></li>";
  return buffer;}
function program7(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " title=\"";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"";
  return buffer;}

function program9(depth0,data) {
  
  
  return "<li class=\"ebComponentList-separator\"></li>";}

  buffer += "<ul class=\"ebComponentList eb_scrollbar elWidgets-ComponentList\">\n";
  foundHelper = helpers.items;
  stack1 = foundHelper || depth0.items;
  stack2 = helpers.each;
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n<li class=\"ebComponentList-info\">Items not found.</li>\n<li class=\"ebComponentList-loader\"><div class=\"ebLoader\"><div class=\"ebLoader-Holder\"><span class=\"ebLoader-Dots\"></span></div></div></li>\n</ul>\n";
  return buffer;});});

define('template!widgets/ComponentList/_componentList.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n    ";
  foundHelper = helpers._action;
  stack1 = foundHelper || depth0._action;
  stack2 = helpers['if'];
  tmp1 = self.program(2, program2, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    ";
  foundHelper = helpers.link;
  stack1 = foundHelper || depth0.link;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    ";
  foundHelper = helpers._separator;
  stack1 = foundHelper || depth0._separator;
  stack2 = helpers['if'];
  tmp1 = self.program(8, program8, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n";
  return buffer;}
function program2(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "<li class=\"ebComponentList-item\" ";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  stack2 = helpers['if'];
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += ">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</li>";
  return buffer;}
function program3(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " title=\"";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"";
  return buffer;}

function program5(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "<li class=\"ebComponentList-item\" ";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  stack2 = helpers['if'];
  tmp1 = self.program(6, program6, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "><a class=\"ebComponentList-link\" href=\"";
  foundHelper = helpers.link;
  stack1 = foundHelper || depth0.link;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "link", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</a></li>";
  return buffer;}
function program6(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " title=\"";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"";
  return buffer;}

function program8(depth0,data) {
  
  
  return "<li class=\"ebComponentList-separator\"></li>";}

  buffer += "<ul class=\"ebComponentList eb_scrollbar elWidgets-ComponentList ebComponentList_focus_forced\">\n";
  foundHelper = helpers.items;
  stack1 = foundHelper || depth0.items;
  stack2 = helpers.each;
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n<li class=\"ebComponentList-info\">Items not found.</li>\n<li class=\"ebComponentList-loader\"><div class=\"ebLoader\"><div class=\"ebLoader-Holder\"><span class=\"ebLoader-Dots\"></span></div></div></li>\n</ul>\n";
  return buffer;});});

define('widgets/ComponentList/ComponentListView',['jscore/core','template!./_componentList.html','template!./_groupedComponentList.html','styles!./_componentList.less','widgets/utils/domUtils'],function (core, template, groupedTemplate, style, domUtils) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            var data = this.options.template;
            if (data.items && data.items[0] && data.items[0].items !== undefined) {
                data.items.forEach(function (group) {
                    this.prepareItemsData(group.items);
                }.bind(this));
                return groupedTemplate(data);
            } else {
                this.prepareItemsData(data.items);
                return template(data);
            }
        },

        getStyle: function () {
            return style;
        },

        prepareItemsData: function (items) {
            items.forEach(function (item) {
                if (item.type === 'separator') {
                    item._separator = true;
                } else if (!item.link) {
                    item._action = true;
                }
            });
        },

        getItems: function () {
            return domUtils.findAll('.ebComponentList-item, .ebComponentList-separator', this.getElement());
        },

        getSelectableItems: function () {
            return domUtils.findAll('.ebComponentList-item', this.getElement());
        },

        getMessageInfo: function () {
            return this.getElement().find('.ebComponentList-info');
        }
    });

});

define('widgets/ComponentList/ComponentList',['jscore/core','widgets/WidgetCore','./ComponentListView','jscore/ext/utils/base/underscore','../utils/domUtils'],function (core, WidgetCore, View, _, dom) {
    'use strict';

    /**
     * The ComponentList class uses the Ericsson brand assets.<br>
     * The ComponentList can be instantiated using the constructor ComponentList.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>ComponentList(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>itemSelected: this event is triggered when value is selected in the ComponentList</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *     <li>items: an array used as a list of available items in the ComponentList.</li>
     *   </ul>
     *
     * @class ComponentList
     * @extends WidgetCore
     * @private
     */
    return WidgetCore.extend({
        /*jshint validthis:true */

        // TODO : This class needs to use a proper means of accessing the parent.

        view: function () {
            return new View({
                template: {items: this.items}
            });
        },

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param {Object} options
         * @private
         */
        init: function (options) {
            this._userOffsets = {top: 0, left: 0};
            this.fixedOffset = 6;
            this.selectedItem = undefined;
            this.focusableItems = [];
            this.options = options || {};
            this.items = this.options.items || [];
            this.flatItems = _.flatten(this.items.map(function (group) {
                return group.items || [group];
            }), true);
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.flatItems.forEach(function (item) {
                // TODO: If a new type is introduced, then replace this
                if (!item.type || item.type !== 'separator') {
                    this.focusableItems.push(item);
                }
            }.bind(this));

            this.view.getSelectableItems().forEach(function (selectableItemElt, index) {
                selectableItemElt.addEventHandler('mousemove', function (evt) {
                    if (evt.originalEvent.pageX !== this.mousePageX || evt.originalEvent.pageY !== this.mousePageY) {
                        this.mousePageX = evt.originalEvent.pageX;
                        this.mousePageY = evt.originalEvent.pageY;
                        this.resetCurrentItemFocus();
                        this.setFocusedItem(index);
                    }
                }.bind(this));

                selectableItemElt.addEventHandler('click', function (evt) {
                    if (evt && evt.stopPropagation) {
                        evt.stopPropagation();
                    }
                    this.onListItemClicked.call(this, index);
                }.bind(this));
            }.bind(this));

            if (this.options.animate) {
                this.getElement().setModifier('animated', undefined, 'elWidgets-ComponentList');
            }

            if (this.focusableItems.length > 0) {
                this.setFocusedItem(0);
            }
        },

        onDestroy: function () {
            this.doHide();
        },

        /**
         * Gets values from the ComponentList
         *
         * @method getItems
         * @return {Array} items
         */
        getItems: function () {
            return this.items;
        },

        /**
         * Returns current selected item
         *
         * @method getSelectedValue
         * @return {Object}
         */
        getSelectedValue: function () {
            return this.selectedItem || {};
        },

        /**
         * Calculates the position of where the select box should be located relative to the screen.<br>
         * Sets an interval to check if the position changes, and if it does, it's hidden.<br>
         * A body event handler is added to ensure that clicking on the parent element doesn't hide
         * the component list immediately. This can be complex task for developers, so making it easier
         * by just putting it in this method.
         *
         * @method show
         */
        show: function () {
            this.deleteTransitionEvent();
            this.render();
            this.isShowing = true;

            // Create the interval. This interval will check frequently what the current position is suppose to be.
            // If the position changes at all, then the list will disappear.
            if (!this.posInterval) {
                this.posInterval = setInterval(function () {
                    var curPosition = calculatePosition.call(this);
                    if (this.position.top !== curPosition.top || this.position.left !== curPosition.left) {
                        this.hide();
                    }
                }.bind(this), 1000 / 24);
            }

            // Add an event handler to the html element.
            // This handler will check to see if the click target is a child of the parent or the parent itself.
            // If not, this component list is hidden.
            // StopPropagation won't work, because when you click on a different selectbox, you'd want the other
            // one to hide itself automatically. So this approach has to be taken.

            if (!this.options.persistent) {
                var body = core.Element.wrap(document.documentElement);
                if (!this.bodyEventId) {
                    this.bodyEventId = body.addEventHandler('click', function (e) {
                        if (!this.isClickTargetChildOfParent(e)) {
                            body.removeEventHandler(this.bodyEventId);
                            delete this.bodyEventId;
                            this.hide();
                        }
                    }.bind(this));
                }
            }

            if (this.options.animate) {
                this.getElement().setModifier('show', undefined, 'elWidgets-ComponentList');
            }
        },

        /**
         * Adds the offsets to the position of the list.
         *
         * @method setPositionOffsets
         * @param {Object} offsets (top/left)
         */
        setPositionOffsets: function (offsets) {
            this._userOffsets = offsets;
            this.render();
        },

        /**
         * Renders the component list to the screen, and performs some calculations to ensure that the list is not cut
         * off by the viewport.
         *
         * @method render
         */
        render: function () {
            this.getElement().detach();

            // Set the width of the context menu to the width of the parent, unless specified otherwise
            var width = this.options.width || (this.options.parent.getProperty('offsetWidth') + 'px');
            var height = this.options.height || '250px';

            // Calculate the position. We need to keep track of the position for delta purposes
            var position = calculatePosition.call(this);
            this.position = position;

            var normalTop = position.top + this.options.parent.getProperty('offsetHeight') + this.fixedOffset;

            // Set the style so that we can get the offsets
            this.getElement().setStyle({
                position: 'fixed',
                'display': 'block',
                'top': normalTop + 'px',
                'left': position.left + 'px',
                'width': width,
                'max-height': height
            });

            core.Element.wrap(document.body).append(this.getElement());

            // We need to see if the list goes outside screen boundaries. These variables will help.
            var offsetHeight = this.getElement().getProperty('offsetHeight');
            var offsetWidth = this.getElement().getProperty('offsetWidth');
            var screenHeight = window.innerHeight;
            var screenWidth = window.innerWidth;
            var parentHeight = this.options.parent.getProperty('offsetHeight');
            var parentWidth = this.options.parent.getProperty('offsetWidth');

            // If the list goes past the bottom of the screen, flip it to appear above the parent instead of below
            if (position.top + offsetHeight + parentHeight + this.fixedOffset > screenHeight) {
                var newTop = position.top - offsetHeight - this.fixedOffset;
                this.getElement().setStyle({
                    top: newTop + 'px'
                });

                if (newTop < 0) {
                    // Figure out which direction to appear in again
                    var upwardsHeight = position.top - this.fixedOffset * 2;
                    var downwardsHeight = screenHeight - normalTop - this.fixedOffset * 3;
                    var finalHeight = upwardsHeight > downwardsHeight ? upwardsHeight : downwardsHeight;
                    var finalTop = upwardsHeight > downwardsHeight ? this.fixedOffset : normalTop;

                    this.getElement().setStyle({
                        top: finalTop + 'px',
                        maxHeight: finalHeight + 'px'
                    });
                }
            }

            // If the list bypasses the right of the viewport, right align it to the parent instead
            if (position.left + offsetWidth > screenWidth) {
                this.getElement().setStyle({
                    left: position.left - (offsetWidth - parentWidth) + 'px'
                });
            }
        },

        /**
         * Iterates up the tree of elements from the target.<br>
         * If it hits the parent, then true is returned.<br>
         * If it runs out of parents, then false is returned.
         *
         * @method isClickTargetChildOfParent
         * @param {Object} e
         * @return {Boolean} isChild
         */
        isClickTargetChildOfParent: function (e) {
            if (e.originalEvent) {
                var obj = e.originalEvent.target || e.originalEvent.srcElement;

                var parent = this.options.parent._getHTMLElement();
                do {
                    if (obj === parent) {
                        return true;
                    }
                } while ((obj = obj.parentElement) !== null && (obj !== document.body));
            }
            return false;
        },

        /**
         * Shows/Hides the list.
         *
         * @method toggle
         */
        toggle: function () {
            this.isShowing = !this.isShowing;
            if (this.isShowing) {
                this.show();
            } else {
                this.hide();
            }
        },

        deleteTransitionEvent: function () {
            if (this.transitionEvent) {
                this.transitionEvent.destroy();
                delete this.transitionEvent;
            }
        },

        /**
         * Clears in the interval and detaches the component list from the DOM.
         *
         * @method hide
         */
        hide: function () {
            this.isShowing = false;
            if (this.options.animate) {
                this.getElement().removeModifier('show', 'elWidgets-ComponentList');
                this.deleteTransitionEvent();
                this.transitionEvent = this.getElement().addEventHandler(dom.transitionEventName, function () {
                    this.doHide();
                    this.deleteTransitionEvent();
                }.bind(this));
            } else {
                this.doHide();
            }
        },

        /**
         * Returns current visibility status of the list
         *
         * @method isVisible
         * @return {boolean}
         */
        isVisible: function () {
            return this.isShowing;
        },

        doHide: function () {
            clearInterval(this.posInterval);
            delete this.posInterval;
            this.getElement().detach();
        },

        //-----------------------------------------------------------------
        //-----------------------------------------------------------------
        /**
         * Show the message info
         *
         * @method showMessageInfo
         */
        showMessageInfo: function (message) {
            this.resetCurrentItemFocus();
            this.view.getMessageInfo().setText(message);
            this.getElement().setModifier('info', '', 'ebComponentList');
            this.selectedItem = undefined;
        },

        /**
         * Hide the message info
         *
         * @method hideMessageInfo
         */
        hideMessageInfo: function () {
            this.getElement().removeModifier('info', 'ebComponentList');
        },

        //-----------------------------------------------------------------
        //-----------------------------------------------------------------
        /**
         * Set focus selection modifier on the item at that index
         *
         * @method setFocusedItem
         */
        setFocusedItem: function (index) {
            index = (index % this.focusableItems.length);
            this._currentFocusedItem = this.view.getSelectableItems()[index];

            this._currentFocusedItem.setModifier('focused', '', 'ebComponentList-item');

            dom.scrollIntoView(this._currentFocusedItem, this.getElement());

            this._currentFocusedItem._indexInList = index;
            this.selectedItem = this.focusableItems[index];
        },

        /**
         * Set focus selection modifier on the previous item on the index
         *
         * @method focusPreviousItem
         */
        focusPreviousItem: function () {
            var index = this.resetCurrentItemFocus();
            this.setFocusedItem(Math.max(index - 1, 0));
        },

        /**
         * Set focus selection modifier on the next item on the index
         *
         * @method focusNextItem
         */
        focusNextItem: function () {
            var index = this.resetCurrentItemFocus();
            this.setFocusedItem(Math.min(index + 1, this.focusableItems.length - 1));
        },

        /**
         * Remove focus selection modifier on the current focused item
         *
         * @method resetCurrentItemFocus
         * @return {int} index of current focused item
         */
        resetCurrentItemFocus: function () {
            var index = -1;
            if (this._currentFocusedItem !== undefined) {
                this._currentFocusedItem.removeModifier('focused', 'ebComponentList-item');
                index = this._currentFocusedItem._indexInList;
                delete this._currentFocusedItem;
            }
            return index;
        },
        //-----------------------------------------------------------------
        //-----------------------------------------------------------------

        /**
         * An event which is executed when on the list item is clicked
         *
         * @method onListItemClicked
         * @param {int} index
         * @private
         */
        onListItemClicked: function (index) {
            this.selectedItem = this.flatItems[index];
            this.resetCurrentItemFocus();
            this.setFocusedItem(index);
            this.selectListItem();
        },


        /**
         * Action to submit selected item if defined
         *
         * @method selectListItem
         */
        selectListItem: function () {
            if (this.selectedItem !== undefined) {
                this.trigger('itemSelected');
                this.hide();
            }
        }

    });


    //-----------------------------------------------------------------
    //-----------------------------------------------------------------
    /**
     * Calculates where the element should appear using the bounding box of the parent element and applying user offsets.
     *
     * TODO: Reimplement this function using property accessors for parent.
     *
     * @method calculatePosition
     * @private
     * @return {Object} top, left
     */
    function calculatePosition() {
        /*jshint validthis:true*/
        var container = this.options.container || this.options.parent;
        // TODO: Change this to proper DOM function
        var clientRect = container._getHTMLElement().getBoundingClientRect();

        var userOffsets = this._userOffsets || {};
        userOffsets.top = userOffsets.top || 0;
        userOffsets.left = userOffsets.left || 0;

        return {
            top: clientRect.top + userOffsets.top,
            left: clientRect.left + userOffsets.left
        };
    }

});

define('widgets/ComponentList',['widgets/ComponentList/ComponentList'],function (main) {
                        return main;
                    });

