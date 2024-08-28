/* Copyright (c) Ericsson 2014 */

define('template!widgets/Tree/tree/Item/_item.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  
  return " ebTreeItem_expandable";}

function program3(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        <div class=\"ebTreeItem-checkbox\">\n            <input class=\"ebCheckbox\" type=\"checkbox\"/>\n            <span class=\"ebCheckbox-inputStatus ";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.checkbox);
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1._triple);
  stack2 = helpers['if'];
  tmp1 = self.program(4, program4, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\"></span>\n        </div>\n        ";
  return buffer;}
function program4(depth0,data) {
  
  
  return "ebCheckbox-inputStatus_triple";}

function program6(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n        <div class=\"ebTreeItem-icon\">\n            <i class=\"ebIcon ";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.icon);
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.prefix);
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "item.icon.prefix", { hash: {} }); }
  buffer += escapeExpression(stack1) + "_";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.icon);
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.name);
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "item.icon.name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\" title=\"";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.icon);
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.title);
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "item.icon.title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"></i>\n        </div>\n        ";
  return buffer;}

  buffer += "<li class=\"ebTreeItem";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.children);
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\">\n    <div class=\"ebTreeItem-content\">\n        <div class=\"ebTreeItem-expandButton\">\n            <i class=\"ebIcon ebIcon_rightArrow ebIcon_interactive\"></i>\n        </div>\n        ";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.checkbox);
  stack2 = helpers['if'];
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        ";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.icon);
  stack2 = helpers['if'];
  tmp1 = self.program(6, program6, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        <div class=\"ebTreeItem-label\" title=\"";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.label);
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "item.label", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.item;
  stack1 = foundHelper || depth0.item;
  stack1 = (stack1 === null || stack1 === undefined || stack1 === false ? stack1 : stack1.label);
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "item.label", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</div>\n    </div>\n</li>";
  return buffer;});});

define('widgets/Tree/tree/Item/ItemView',['jscore/core','template!./_item.html'],function (core, template ) {

    return core.View.extend({

        afterRender: function () {
            var element = this.getElement();
            this.expandBtn = element.find('.ebTreeItem-expandButton');
            if (this.expandBtn) {
                this.expandIcon = this.expandBtn.find('.ebIcon');
            }
            this.checkbox = element.find('.ebCheckbox');
            this.checkboxStatus = element.find('.ebCheckbox-inputStatus');
            this.contextMenuHolder = element.find('.ebTreeItem-menu');
            this.itemIcon = element.find('.ebTreeItem-icon');
            this.itemLabel = element.find('.ebTreeItem-label');
            this.itemContent = element.find('.ebTreeItem-content');
        },

        getTemplate: function () {
            return template(this.options.template);
        },

        getExpandBtn: function () {
            return this.expandBtn;
        },

        getExpandIcon: function () {
            return this.expandIcon;
        },

        getCheckbox: function () {
            return this.checkbox;
        },

        getCheckboxStatus: function () {
            return this.checkboxStatus;
        },

        getContextMenuHolder: function () {
            return this.contextMenuHolder;
        },

        getItemIcon: function () {
            return this.itemIcon;
        },

        getItemLabel: function () {
            return this.itemLabel;
        },

        getItemContent: function() {
            return this.itemContent;
        }

    });

});

define('widgets/Tree/tree/Item/Item',['widgets/WidgetCore','jscore/core','./ItemView','widgets/tree/Resolver'],function (WidgetCore, core, View, Resolver) {

    /**
     * Item for the tree widget. Instances of these Widgets are used by the Tree.
     *
     * @class tree.Item
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        /**
         * Initialises variables and the local event bus
         *
         * @method init
         * @private
         * @param {Object} options
         */
        init: function (options) {
            this.itemsLoaded = false;
            this.itemsExpanded = false;
            this._parentItem = options.parentItem;
            this._eventBus = options.eventBus;
            this._parentTree = options.parentTree;

        },

        /**
         * Loads the view with the items option from the constructor
         *
         * @method view
         * @private
         */
        view: function () {
            this.definition = this.options.item;

            // If the icon is just a string, convert it to an object
            if (typeof this.definition.icon === 'string') {
                this.definition.icon = {
                    name: this.definition.icon
                };
            }

            // Provide a default prefix if no prefix is defined
            if (this.definition.icon && !this.definition.icon.prefix) {
                this.definition.icon.prefix = 'ebIcon';
            }

            return new View({
                template: {item: this.definition}
            });
        },

        /**
         * Adds the provided definition to the nested tree.
         *
         * @method addItem
         * @param {Object} definition
         */
        addItem: function(definition) {
            // If there's no children in the definition, create it, and load the expander
            if (this.definition.children === undefined) {
                this.definition.children = [];
                this.getElement().setModifier('expandable');
                loadExpander.call(this);
            }

            // Push the definition in
            this.definition.children.push(definition);

            // If the nested tree has already been lazy loaded, then add the definition as a widget
            if (this._nestedTree) {
                if (this.itemsExpanded) {
                    this._nestedTree.getElement().setStyle('display', 'none');
                }
                this._nestedTree.addItem(definition);
                if (this.itemsExpanded) {
                    this._nestedTree.expand();
                }
            }
        },

        /**
         * Gets the nested tree items
         *
         * @method getItems
         * @return {Array<Widget>} items
         */
        getItems: function() {
            loadItems.call(this);
            return this._nestedTree.getItems();
        },

        /**
         * Gets the nested tree item at the specified index
         *
         * @method getItem
         * @param {Integer} index
         * @return {Widget} item
         */
        getItem: function(index) {
            loadItems.call(this);
            return this._itemWidgets[index];
        },

        /**
         * Gets the definition for the tree item
         *
         * @method getDefinition
         * @return {Object} definition
         */
        getDefinition: function() {
            return this.definition;
        },

        /**
         * Expands the nested tree.
         *
         * @method expand
         */
        expand: function() {
            loadItems.call(this);

            var expandIcon = this.view.getExpandIcon();
            expandIcon.removeModifier('rightArrow');
            expandIcon.setModifier('downArrow');
            if (this._nestedTree) {
                this._eventBus.publish('_itemexpand', this);
                this._nestedTree.expand();
            }

            this.itemsExpanded = true;
        },

        /**
         * Collapses the nested tree.
         *
         * @method collapse
         */
        collapse: function() {
            var expandIcon = this.view.getExpandIcon();
            expandIcon.removeModifier('downArrow');
            expandIcon.setModifier('rightArrow');
            if (this._nestedTree) {
                this._eventBus.publish('_itemcollapse', this);
                this._nestedTree.collapse();
            }

            this.itemsExpanded = false;
        },

        /**
         * Destroys the item.
         *
         * @method destroy
         */
        destroy: function() {
            if (!this._destroyed) {
                this._destroyed = true;
                this._eventBus.publish('_itemdestroy', this);
                if (this._parentTree) {
                    this._parentTree.removeItem(this);
                }
                core.Widget.prototype.destroy.call(this);
            }
        },

        /**
         *
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            /*if (this.definition.disabled) {
                this.disable();
            }*/

            if (this.definition.children || this.definition.expandable) {
                this.getElement().setModifier('expandable');
            }

            //Set icon opacity if icon and iconOpacity keys set in constructor
            if(this.options.item.icon !== undefined) {
                if (this.options.item.icon.opacity !== undefined) {
                    this.view.getItemIcon().setStyle('opacity', this.options.item.icon.opacity);
                }
            }

            loadExpander.call(this);
            loadCheckbox.call(this);

            this.view.getItemContent().addEventHandler('click', this.select.bind(this));
        },

        /**
         * Prevents the item from being selected and being checked
         *
         * @method disable
         * @private
         */
        /*disable: function() {
            this.getElement().setModifier('disabled');
            this.definition.disabled = true;

            if (this.definition.checkbox) {
                this.view.getCheckbox().setProperty('disabled', true);
                this.definition.checkbox.disabled = true;
            }
        },*/

        /**
         * Allows the item to be selected and checked
         *
         * @method disable
         * @private
         */
       /* enable: function() {

            this.getElement().removeModifier('disabled');
            this.definition.disabled = false;

            if (this.definition.checkbox) {
                this.view.getCheckbox().setProperty('disabled', false);
                this.definition.checkbox.disabled = false;
            }
        },*/

        /**
         * Selects the item. This will trigger "itemselect" event on the tree.
         *
         * @method select
         */
        select: function() {
            this._eventBus.publish("_itemselect", this);
        }

    });


    /**
     * If the nested tree of items is not loaded, it will instantiate the tree widget with those item definitions.
     *
     * @method loadItems
     * @private
     */
    function loadItems() {
        if (!this.itemsLoaded) {
            var items = this.definition.children;
            this._nestedTree = new this.options.Tree({
                items: items,
                eventBus: this._eventBus,
                parentItem: this
            });

            this._nestedTree.attachTo(this.getElement());
            this.itemsLoaded = true;
        }

    }


    /**
     * If there are children, the expander button has its event handlers added
     *
     * @method loadExpander
     * @private
     */
    function loadExpander() {
        if (this.definition.children) {
            this.view.getExpandBtn().addEventHandler('click', function (e) {
                if (e && e.originalEvent) {
                    e.originalEvent.stopPropagation();
                }

                if (this.itemsExpanded) {
                    this.collapse();
                } else {
                    this.expand();
                }
            }, this);
        }
    }

    /**
     *
     *
     * @method loadCheckbox
     * @private
     */
    function loadCheckbox() {
        var checkboxObj = this.definition.checkbox;
        if (checkboxObj) {

            // Set up shortcut variables
            var checkboxEl = this.view.getCheckbox(),
                checkboxStatusEl = this.view.getCheckboxStatus();

            // Initialise the checkbox for the first time
            /*if (checkboxObj.disabled) {
                checkboxEl.setProperty('disabled', true);
            }*/
            if (checkboxObj.checked) {
                checkboxEl.setProperty('checked', true);
            }
            if (checkboxObj.triple) {
                checkboxStatusEl.setModifier('triple');
            }
            checkboxEl.setValue(checkboxObj.value);

            // 'Binding' to make code logic easier
            // First the private variables
            checkboxObj._checked = checkboxObj.checked;
            checkboxObj._element = checkboxEl;
            checkboxObj._status = checkboxStatusEl;
            checkboxObj._triple = checkboxObj.triple;

            // When the checked property in the definition changes, the actual checkbox element will change too
            Object.defineProperty(checkboxObj, 'checked', {
                get: function() {
                    return this._checked;
                },
                set: function(checked) {
                    this._checked = checked;
                    this._element.setProperty('checked', this._checked);
                }
            });

            // When the triple boolean is set, the modifier is applied
            Object.defineProperty(checkboxObj, 'triple', {
                get: function() {
                    return this._triple;
                },
                set: function(value) {
                    this._triple = value;
                    if (this._triple) {
                        this._status.setModifier('triple');
                    } else {
                        this._status.removeModifier('triple');
                    }

                }
            });

            // When clicked, remove triple, set checkbox, and resolve
            checkboxEl.addEventHandler('click', function(e) {
                e.originalEvent.stopPropagation();
                this.definition.checkbox.checked = this.view.getCheckbox().getProperty('checked');
                Resolver.resolveCheckbox(this.definition, this.definition.checkbox.checked);
            }, this);
        }
    }

});

define('widgets/tree/Item',['widgets/Tree/tree/Item/Item'],function (main) {
                        return main;
                    });

