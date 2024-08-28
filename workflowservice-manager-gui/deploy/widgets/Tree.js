/* Copyright (c) Ericsson 2014 */

define('text!widgets/Tree/_tree.html',[],function () { return '<ul class="ebTree"></ul>';});

define('widgets/Tree/TreeView',['jscore/core','text!./_tree.html'],function (core, template) {

    return core.View.extend({

        getTemplate: function () {
            return template;
        }

    });

});

define('widgets/Tree/Tree',['jscore/core','widgets/WidgetCore','./TreeView','widgets/tree/Item','widgets/utils/domUtils','widgets/tree/Resolver'],function (core, WidgetCore, View, TreeItem, domExt, Resolver) {

    /**
     * Tree widget with expandable children.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>itemselect: Triggers when an item has been selected in the tree. Passes the item widget.</li>
     *       <li>itemexpand: Triggers when an item has been expanded in the tree. Passes the item widget.</li>
     *       <li>itemcollapse: Triggers when an item has been collapsed in the tree. Passes the item widget.</li>
     *       <li>itemdestroy: Triggers when an item has been destroyed in the tree. Passes the item widget.</li>
     *   </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>items: List of items to add to the tree.
     *          <ul>
     *              <li>label: String</li>
     *              <li>icon: String specifying icon name, or object with "name" (String), "prefix" (String) and "opacity" (Float - max 1.0)</li>
     *              <li>checkbox: Object with "checked" (Boolean), and "value" (String)</li>
     *              <li>children: Array of items. They have the same options as this list as options.</li>
     *          </ul>
     *        </li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class Tree
     * @extends WidgetCore
     */
    var Tree = WidgetCore.extend({

        View: View,

        /**
         * Initialises variables and the local event bus
         *
         * @method init
         * @private
         * @param {Object} options
         */
        init: function (options) {
            if (options) {
                this._parentItem = options.parentItem !== undefined? options.parentItem : undefined;

                // This ensures that all widgets inside this tree use the exact same event bus
                if (options.eventBus) {
                    this._eventBus = options.eventBus;
                }
            }

            if (!this._eventBus) {
                this._eventBus = new core.EventBus();
            }
            this._itemWidgets = [];
        },


        /**
         * Sets up event handlers, and iterates over the top level items, creating a TreeItem for each one.
         * Each tree item receives the event bus, and a reference to the top level tree. If those TreeItems have
         * children, a new Tree is created by them.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {

            // Shortcut since it's used quite a bit
            var itemDefs = this.options.items;

            // If this tree belongs to a tree item, we don't want it to be displayed initially
            if (this._parentItem) {
                this.getElement().setStyle('display', 'none');
            }

            // Only the top level tree needs to take care of these things...
            if (this._parentItem === undefined) {

                // If something expanded anywhere in the tree, throw this event on the top level
                this._eventBus.subscribe('_itemexpand', function(item) {
                    this.trigger('itemexpand', item);
                }, this);

                // If something collapsed anywhere in the tree, throw this event on the top level
                this._eventBus.subscribe('_itemcollapse', function(item) {
                    this.trigger('itemcollapse', item);
                }, this);

                // If a tree item is selected anywhere, deselect the previously selected and trigger an event to the top level
                this._eventBus.subscribe('_itemselect', function (item) {
                    this.deselect();
                    item.view.getItemContent().setModifier('selected');
                    this._lastSelectedItem = item;
                    this.trigger('itemselect', item);
                }, this);

                // If a tree item is destroyed anywhere, throw this event at the top level
                this._eventBus.subscribe('_itemdestroy', function (item) {
                    this.trigger('itemdestroy', item);
                }, this);

                if (itemDefs) {
                    // Go over the item definitions and apply a private variable called _parent to each one
                    for (var i = 0; i < itemDefs.length; i++) {
                        applyParentVariable(undefined, itemDefs[i]);
                    }

                    // Figure out what the checkbox value of each item should be, change the item definition
                    for (var j = 0; j < itemDefs.length; j++) {
                        Resolver.resolveCheckbox(itemDefs[j]);
                    }
                }
            }



            // Iterate over the first level of the items, and create a TreeItem for each one.
            if (itemDefs) {
                itemDefs.forEach(function (itemObj) {
                    this.addItem(itemObj);
                }.bind(this));
            }
        },

        /**
         * Adds the provided item definition to the tree
         *
         * @method addItem
         * @param {Object} definition
         */
        addItem: function(definition) {
            var treeItem = new TreeItem({
                item: definition,
                Tree: Tree,
                parentTree: this,
                eventBus: this._eventBus,
                parentItem: this._parentItem
            });
            treeItem.attachTo(this.getElement());
            this._itemWidgets.push(treeItem);
        },

        /**
         * Returns the TreeItems belonging to this tree
         *
         * @method getItems
         * @return {Array<Widget>} children
         */
        getItems: function () {
            return this._itemWidgets;
        },

        /**
         * Returns the TreeItem at the index provided
         *
         * @method getItem
         * @param {Integer} index
         */
        getItem: function(index) {
            return this._itemWidgets[index];
        },

        /**
         * Removes and destroys the provided tree item from the tree
         *
         * @method removeItem
         * @param {TreeItem} item
         */
        removeItem: function(item) {
            for (var i = 0; i < this._itemWidgets.length; i++) {
                if (this._itemWidgets[i] === item) {
                    this._itemWidgets.splice(i, 1);
                    break;
                }
            }
            item.destroy();
        },

        /**
         * Expands the tree
         *
         * @method expand
         * @private
         */
        expand: function() {
            slide.call(this, 'Down', undefined, -5, 0);
        },

        /**
         * Collapses the tree
         *
         * @method collapse
         * @private
         */
        collapse: function() {
            slide.call(this, 'Up', undefined, 0, -5);
        },

        /**
         * Deselects any item that is currently selected.
         *
         * @method deselect
         */
        deselect: function() {
            if (this._lastSelectedItem) {
                this._lastSelectedItem.view.getItemContent().removeModifier('selected');
            }
            this._lastSelectedItem = undefined;
        }

    });


    /**
     * Expands/Collapses the tree based on parameters
     *
     * @method slide
     * @private
     * @param {String} direction (Up/Down)
     * @param {Function} completeCallback
     * @param {Integer} marginStart
     * @param {Integer} marginEnd
     */
    function slide(direction, completeCallback, marginStart, marginEnd) {
        this.getElement().setStyle('margin-top', marginStart + 'px');
        this.getElement().setStyle('margin-bottom', (-marginStart) + 'px');
        domExt['slide'+direction](this.getElement(), {
            duration: 300,
            complete: function() {
                this.getElement().setStyle('margin-top', 0);
                this.getElement().setStyle('margin-bottom', 0);
                if (completeCallback) {
                    completeCallback();
                }
            }.bind(this),
            start: function(anim) {
                anim.tweens.forEach(function(tween) {
                    if (tween.prop === 'marginTop') {
                        tween.start = marginStart;
                        tween.end = marginEnd;
                    } else if(tween.prop === 'marginBottom') {
                        tween.start = -marginStart;
                        tween.end = -marginEnd;
                    }
                });
            }
        });
    }

    /**
     * Sets the private variable _parent in the child definition to the specified parent.
     * It then iterates over the children and recursively performs the same operation.
     * This needs to be for checkboxes to be resolved correctly.
     *
     * @method applyParentVariable
     * @private
     * @param {Object} parent
     * @param {Object} child
     */
    function applyParentVariable(parent, child) {
        child._parent = parent;

        if (child.children) {
            for (var i = 0; i < child.children.length; i++) {
                applyParentVariable(child, child.children[i]);
            }
        }
    }

    return Tree;

});

define('widgets/Tree',['widgets/Tree/Tree'],function (main) {
                        return main;
                    });

