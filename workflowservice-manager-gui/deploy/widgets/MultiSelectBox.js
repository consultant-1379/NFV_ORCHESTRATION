/* Copyright (c) Ericsson 2014 */

define('template!widgets/MultiSelectBox/MultiList/_multiList.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  
  return "\n    <div class=\"elWidgets-MultiSelectBox-selectDeselectAllWrap\">\n        <div class=\"ebMultiSelectBox-selectDeselectAll\">\n    	   <span class='elWidgets-MultiSelectBox-selectAll ebMultiSelectBox-spanLink'>Select All</span><span class='elWidgets-MultiSelectBox-deselectAll ebMultiSelectBox-spanLink'>Deselect All</span>\n        </div>\n	    <div class=\"ebComponentList-separator\"></div>\n	</div>\n    ";}

function program3(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        ";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  stack2 = helpers['if'];
  tmp1 = self.program(4, program4, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        ";
  foundHelper = helpers.type;
  stack1 = foundHelper || depth0.type;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        ";
  return buffer;}
function program4(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        <div class=\"ebComponentList-item\">\n            <label>\n                <input class=\"ebCheckbox\" type=\"checkbox\" value=\"";
  foundHelper = helpers.value;
  stack1 = foundHelper || depth0.value;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "value", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"";
  foundHelper = helpers.checked;
  stack1 = foundHelper || depth0.checked;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += ">\n                <span class=\"ebCheckbox-inputStatus\"></span>\n                <span class=\"ebCheckbox-label\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</span>\n            </label>\n        </div>\n        ";
  return buffer;}
function program5(depth0,data) {
  
  
  return " checked";}

function program7(depth0,data) {
  
  
  return "\n        <div class=\"ebComponentList-separator\"></div>\n        ";}

  buffer += "<div class=\"ebComponentList ebComponentList_focus_forced eb_scrollbar\">\n    ";
  foundHelper = helpers.selectDeselectAll;
  stack1 = foundHelper || depth0.selectDeselectAll;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n\n    <div class=\"ebMultiSelectBox\">\n        ";
  foundHelper = helpers.items;
  stack1 = foundHelper || depth0.items;
  stack2 = helpers.each;
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n	</div>\n\n</div>";
  return buffer;});});

define('widgets/MultiSelectBox/MultiList/MultiListView',['jscore/core','template!./_multiList.html','widgets/utils/domUtils'],function (core, template,domUtils) {
    'use strict';

    var MultiListView = core.View.extend({

        getTemplate: function () {
            return template(this.options.template);
        },

        getSelectableItems: function () {
            return domUtils.findAll('.ebComponentList-item', this.getElement());
        },

        getItems: function () {
//            return domUtils.findAll('.ebComponentList-item', this.getElement());
            return  this.getElement().find('.ebMultiSelectBox').children();
        },
		getSelectAllLink : function() {
			return domUtils.findAll('.elWidgets-MultiSelectBox-selectAll', this.getElement())[0];
		},
		getDeSelectAllLink : function() {
			return domUtils.findAll('.elWidgets-MultiSelectBox-deselectAll', this.getElement())[0];
		},
		getSelectDeSelectDiv : function() {
			return domUtils.findAll('.elWidgets-MultiSelectBox-selectDeselectAllWrap', this.getElement())[0];
		}

    }, {
        EL_ITEM: 'ebComponentList-item',
        EL_CHECKBOX: 'ebCheckbox',
        EL_LABEL: 'ebCheckbox-label'
    });

    return MultiListView;

});

define('widgets/MultiSelectBox/MultiList/MultiList',['widgets/ComponentList','./MultiListView'],function (ComponentList, View) {
    'use strict';

    /**
     * The MultiList class uses the Ericsson brand assets.<br>
     * The MultiList can be instantiated using the constructor MultiList.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>MultiList(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>itemChanged: this event is triggered when value is selected in the MultiList</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>items: an array used as list of available items in the MultiList</li>
     *   </ul>
     *
     * @private
     * @class MultiList
     * @extends ComponentList
     */
    return ComponentList.extend({
        /*jshint validthis:true*/

        view: function () {
            return new View({
                template: {
                    items: this.options.items,
                    selectDeselectAll: this.options.selectDeselectAll
                }
            });
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

                var checkbox = selectableItemElt.find('.' + View.EL_CHECKBOX);

                if (checkbox) {
                    checkbox.addEventHandler('change', function () {
                        this.onListItemClicked(index, checkbox);
                    }.bind(this));
                }

                selectableItemElt.addEventHandler('click', function (evt) {
                    if (evt && evt.stopPropagation) {
                        evt.stopPropagation();
                    }
                }.bind(this));
            }.bind(this));

            if (this.options.selectDeselectAll === true) {
                this.view.getSelectAllLink().addEventHandler('click', function (e) {
                    e.originalEvent.stopPropagation();
                    this.view.getItems().forEach(function (item, index) {
                        var checkbox = item.find('.' + View.EL_CHECKBOX);
                        if (checkbox && !checkbox.getProperty('checked')) {
                            checkbox.setProperty('checked', true);
                            this.onListItemClicked(index, checkbox, true);
                        }
                    }, this);
                    this.trigger('itemAllChanged');
                }, this);
                this.view.getDeSelectAllLink().addEventHandler('click', function (e) {
                    e.originalEvent.stopPropagation();
                    this.view.getItems().forEach(function (item, index) {
                        var checkbox = item.find('.' + View.EL_CHECKBOX);
                        if (checkbox && checkbox.getProperty('checked')) {
                            checkbox.setProperty('checked', false);
                            this.onListItemClicked(index, checkbox, true);
                        }
                    }, this);
                    this.trigger('itemAllChanged');
                }, this);
            }
        },

        /**
         * Returns array of selected items from the MultiList
         *
         * @method getSelectedValues
         * @return {Array} Selected items
         */
        getSelectedItems: function () {
            var selectedItems = [];
            this.options.items.forEach(function (item) {
                if (item.checked === true) {
                    selectedItems.push(item);
                }
            });
            return selectedItems;
        },

        /**
         * An event which is executed when on the list item is clicked
         *
         * @method _onListItemClicked
         * @param {int} index
         * @param {Element} checkbox
         * @param silent
         * @private
         */
        onListItemClicked: function (index, checkbox, silent) {
            this._selectedItem = this.options.items[index];
            this.selectListItem(index, checkbox, silent);
        },

        /**
         * Action to submit selected item if defined
         *
         * @method selectListItem
         */
        selectListItem: function (index, checkbox, silent) {
            var newCheckedStatus;

            if (arguments.length !== 0) {
                newCheckedStatus = checkbox.getProperty('checked');
            }
            else {
                var chkBx = this._currentFocusedItem.find('.' + View.EL_CHECKBOX);

                if (chkBx === undefined) {
                    return;
                }

                newCheckedStatus = !chkBx.getProperty('checked');
                chkBx.setProperty('checked', newCheckedStatus);
                this._selectedItem = this.focusableItems[this._currentFocusedItem._indexInList];
            }
            this._selectedItem.checked = newCheckedStatus;

            if (silent !== true) {
                this.trigger('itemChanged');
            }
        }

    });
});

define('widgets/MultiSelectBox/MultiSelectBox',['widgets/SelectBox','./MultiList/MultiList'],function (SelectBox, MultiList) {
    'use strict';

    /**
     * The MultiSelectBox class uses the Ericsson brand assets.<br>
     * The MultiSelectBox can be instantiated using the constructor MultiSelectBox.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>MultiSelectBox(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>change: this event is triggered when value is changed in the MultiSelectBox</li>
     *     <li>focus: this event is triggered when the MultiSelectBox is focused</li>
     *     <li>click: this event is triggered when user clicks on the MultiSelectBox</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>items: an array used as a list of available items in the selectBox</li>
     *       <li>selectDeselectAll : By default this field is set to false. If set to "true" Select
     *            All and Deselect All will be displayed in the list</li>
     *       <li>enabled: boolean indicating whether selectBox should be enabled. Default is true.</li>
     *       <li>modifiers: an array used to define modifiers for the SelectBox.
     *          <a href="#modifierAvailableList">see available modifiers</a>
     *          <br>E.g: modifiers:[{name: 'foo'}, {name: 'bar', value:'barVal'}]
     *       </li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>disabled: disabled (Asset Library)</li>
     *  </ul>
     *
     * @example
     * var multiSelectBoxWidget  = new MultiSelectBox({
     *      items: [
     *          {name: 'Name 1', value: 1, checked: true},
     *          {type: 'separator'},
     *          {name: 'Name 2', value: 2, checked: false}
     *       ]
     * });
     *
     * @class MultiSelectBox
     * @extends SelectBox
     */
    return SelectBox.extend({
        /*jshint validthis:true*/

        /**
         * Overrides method from ItemControl.<br>
         * Executes every time, when added back to the screen.
         *
         * @method onControlReady
         * @private
         */
        onControlReady: function () {
            // Define and override component key controls
            defineComponentSpecificKeyHandler.call(this);
            this.onItemSelected.call(this);
        },

        /**
         * Sets value for the MultiSelectBox.
         *
         * @method setValue
         * @private
         *
         * @param {object} selectedItems
         */
        setValue: function (selectedItems) {
        },

        /**
         * Returns value for the MultiSelectBox.
         *
         * @method getValue
         */
        getValue: function () {
            return this.getSelectedItems();
        },

        /**
         * Returns a reference to the ComponentList class.
         *
         * @method getComponentListClass
         * @private
         * @return {ComponentList} ComponentListClass
         */
        getComponentListClass: function () {
            return MultiList;
        },

        /**
         * A method which is called when setItems() method is called.
         *
         * @method onItemsSet
         * @private
         */
        onItemsSet: function () {
            if (this.componentList) {
                this.componentList.addEventHandler('itemChanged', this.onItemSelected.bind(this));
                this.componentList.addEventHandler('itemAllChanged', this.onItemSelected.bind(this));
            }

            this._selectableItemsCount = 0;

            if (this.items) {
                for (var i = 0; i < this.items.length; i++) {
                    if (this.items[i].type !== 'separator') {
                        this._selectableItemsCount++;
                    }
                }
            }

            displayNumberSelectedItem.call(this);
        },

        /**
         * Returns array of selected items from the MultiSelectBox.
         *
         * @method getSelectedItems
         * @returns {Array} Selected items
         */
        getSelectedItems: function () {
            return (this.componentList ? this.componentList.getSelectedItems() : []);
        },

        /**
         * A method which is called when an item is selected.
         *
         * @method onItemSelected
         * @private
         */
        onItemSelected: function () {
            displayNumberSelectedItem.call(this);
            this.trigger('change');
            this.getFocusableElement().focus();
        }

    });

    //-----------------------------------------------------------------
    //-----------------------------------------------------------------

    function defineComponentSpecificKeyHandler() {
        /*jshint validthis:true*/
        this.keyBoardControl.tab = onTab.bind(this);
        this.keyBoardControl.space = onSpace.bind(this);
        this.keyBoardControl.enter = onEnter.bind(this);
    }

    //-----------------------------------------------------------------
    function onSpace(evt) {
        /*jshint validthis:true*/
        if (this.isKeyboardControlled()) {
            if (this.componentList.isVisible()) {
                this.componentList.selectListItem();
            } else {
                this.showList();
            }
        }
        evt.preventDefault();
    }

    //-----------------------------------------------------------------
    function onTab() {
        /*jshint validthis:true*/
        this.hideList();
    }

    //-----------------------------------------------------------------
    function onEnter(evt) {
        /*jshint validthis:true*/
        this.toggle();
        evt.preventDefault();
    }


    //-----------------------------------------------------------------
    function displayNumberSelectedItem() {
        /*jshint validthis:true */
        var selectedItemsCount = this.componentList ? this.componentList.getSelectedItems().length : 0;

        if (this.items && this._selectableItemsCount === selectedItemsCount && selectedItemsCount !== 0) {
            this.view.getValueEl().setText('All Selected');
        }
        else {
            this.view.getValueEl().setText(selectedItemsCount + ' Selected');
        }
    }
});

define('widgets/MultiSelectBox',['widgets/MultiSelectBox/MultiSelectBox'],function (main) {
                        return main;
                    });

