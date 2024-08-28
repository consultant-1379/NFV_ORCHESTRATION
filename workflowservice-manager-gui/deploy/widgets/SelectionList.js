/* Copyright (c) Ericsson 2014 */

define('template!widgets/SelectionList/Item/_item.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, foundHelper, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;


  buffer += "<div class=\"elWidgets-SelectionList-item\">\n    <label class=\"elWidgets-SelectionList-label\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</label>\n</div>";
  return buffer;});});

define('widgets/SelectionList/Item/ItemView',['jscore/core','template!./_item.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template(this.options);
        },

        getBody: function () {
            return this.getElement();
        },
        highlight: function () {
            this.getBody().setModifier('highlighted');
        },

        removeHighlight: function () {
            this.getBody().removeModifier('highlighted');
        }

    });

});

define('widgets/SelectionList/Item/Item',['widgets/WidgetCore','./ItemView'],function (WidgetCore, View) {
    'use strict';

    return WidgetCore.extend({

        view: function() {
            return new View(this.options.data);
        },

        init: function () {
            this._highlighted = false;
        },
        highlight: function(){
            this._highlighted = true;
            this.view.getElement().setModifier('highlighted');
        },
        unhighlight: function(){
            this._highlighted = false;
            this.view.getElement().removeModifier('highlighted');
        },
        isHighlighted: function(){
            return this._highlighted;
        }
    });

});

define('styles!widgets/SelectionList/_selectionList.less',[],function () { return '.elWidgets-SelectionList {\n  border-bottom: 1px solid;\n  -webkit-touch-callout: none;\n  -webkit-user-select: none;\n  -khtml-user-select: none;\n  -moz-user-select: none;\n  -ms-user-select: none;\n  user-select: none;\n}\n.elWidgets-SelectionList-header {\n  height: 100%;\n  float: left;\n  position: absolute;\n  width: calc(60% - 36px);\n}\n.elWidgets-SelectionList-header-icon {\n  vertical-align: initial;\n}\n.elWidgets-SelectionList-header-text {\n  padding-bottom: 0;\n  border-bottom: 0;\n  position: absolute;\n  bottom: 0;\n  left: 0;\n  width: 100%;\n  text-overflow: ellipsis;\n  white-space: nowrap;\n  overflow: hidden;\n}\n.elWidgets-SelectionList-actions {\n  padding-top: 10px;\n  padding-bottom: 10px;\n}\n.elWidgets-SelectionList-actions-label {\n  display: inline-block;\n  padding-right: 5px;\n}\n.elWidgets-SelectionList-actions-selectAll,\n.elWidgets-SelectionList-actions-deselectAll {\n  cursor: pointer;\n}\n.elWidgets-SelectionList-list {\n  position: relative;\n  list-style: none;\n  padding: 0;\n  margin: 0;\n}\n.elWidgets-SelectionList-item {\n  position: relative;\n  display: block;\n  border-bottom: #e6e6e6 solid 1px;\n  margin: 0;\n  padding: 0;\n  min-height: 32px;\n  line-height: 32px;\n  text-overflow: ellipsis;\n  vertical-align: middle;\n}\n.elWidgets-SelectionList-item:first-child {\n  border-top: #e6e6e6 solid 1px;\n}\n.elWidgets-SelectionList-item_highlighted {\n  background-color: rgba(0, 102, 179, 0.2);\n}\n.elWidgets-SelectionList-item_highlighted:hover {\n  background-color: rgba(0, 102, 179, 0.3);\n}\n.elWidgets-SelectionList-item:hover {\n  background-color: rgba(0, 102, 179, 0.1);\n}\n.elWidgets-SelectionList-label {\n  position: relative;\n  display: block;\n  cursor: pointer;\n  padding: 0 10px 0 10px;\n}\n';});

define('template!widgets/SelectionList/_selectionList.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var stack1;
  foundHelper = helpers.headerTagName;
  stack1 = foundHelper || depth0.headerTagName;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "headerTagName", { hash: {} }); }
  return escapeExpression(stack1);}

function program3(depth0,data) {
  
  
  return "h2";}

function program5(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "<i class=\"ebIcon ebIcon_";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "icon", { hash: {} }); }
  buffer += escapeExpression(stack1) + " elWidgets-SelectionList-header-icon\"></i> ";
  return buffer;}

function program7(depth0,data) {
  
  var stack1;
  foundHelper = helpers.headerTagName;
  stack1 = foundHelper || depth0.headerTagName;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "headerTagName", { hash: {} }); }
  return escapeExpression(stack1);}

function program9(depth0,data) {
  
  
  return "h2";}

function program11(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n    <div class=\"elWidgets-SelectionList-actions\">\n        <div class=\"elWidgets-SelectionList-actions-label\">";
  foundHelper = helpers.selectLabel;
  stack1 = foundHelper || depth0.selectLabel;
  stack2 = helpers['if'];
  tmp1 = self.program(12, program12, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.program(14, program14, data);
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += " </div>\n        <a class=\"elWidgets-SelectionList-actions-selectAll\">";
  foundHelper = helpers.selectAllLabel;
  stack1 = foundHelper || depth0.selectAllLabel;
  stack2 = helpers['if'];
  tmp1 = self.program(16, program16, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.program(18, program18, data);
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "</a>\n        <div class=\"ebLayout-HeadingCommands-separator\"></div>\n        <a class=\"elWidgets-SelectionList-actions-deselectAll\">";
  foundHelper = helpers.selectNoneLabel;
  stack1 = foundHelper || depth0.selectNoneLabel;
  stack2 = helpers['if'];
  tmp1 = self.program(20, program20, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.program(22, program22, data);
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "</a>\n    </div>\n    ";
  return buffer;}
function program12(depth0,data) {
  
  var stack1;
  foundHelper = helpers.selectLabel;
  stack1 = foundHelper || depth0.selectLabel;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "selectLabel", { hash: {} }); }
  return escapeExpression(stack1);}

function program14(depth0,data) {
  
  
  return "Select:";}

function program16(depth0,data) {
  
  var stack1;
  foundHelper = helpers.selectAllLabel;
  stack1 = foundHelper || depth0.selectAllLabel;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "selectAllLabel", { hash: {} }); }
  return escapeExpression(stack1);}

function program18(depth0,data) {
  
  
  return "All";}

function program20(depth0,data) {
  
  var stack1;
  foundHelper = helpers.selectNoneLabel;
  stack1 = foundHelper || depth0.selectNoneLabel;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "selectNoneLabel", { hash: {} }); }
  return escapeExpression(stack1);}

function program22(depth0,data) {
  
  
  return "None";}

  buffer += "<div class=\"elWidgets-SelectionList\">\n    <div class=\"ebLayout-SectionHeading\">\n        <div class=\"elWidgets-SelectionList-header\">\n            <";
  foundHelper = helpers.headerTagName;
  stack1 = foundHelper || depth0.headerTagName;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.program(3, program3, data);
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += " class=\"elWidgets-SelectionList-header-text\">";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "<span class=\"elWidgets-SelectionList-header-text-span\"></span></";
  foundHelper = helpers.headerTagName;
  stack1 = foundHelper || depth0.headerTagName;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.program(9, program9, data);
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += ">\n    </div>\n    <div class=\"ebLayout-HeadingCommands\">\n            <div class=\"ebLayout-HeadingCommands-block\">\n                <div class=\"elWidgets-SelectionList-dropdown\"></div>\n            </div>\n        </div>\n        <div class=\"ebLayout-clearFix\"></div>\n\n    </div>\n    ";
  foundHelper = helpers.showSelectActions;
  stack1 = foundHelper || depth0.showSelectActions;
  stack2 = helpers['if'];
  tmp1 = self.program(11, program11, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    <div class=\"elWidgets-SelectionList-content\"></div>\n</div>";
  return buffer;});});

define('widgets/SelectionList/SelectionListView',['jscore/core','template!./_selectionList.html','styles!./_selectionList.less'],function (core, template, Style) {
    'use strict';

    var selectionListView = core.View.extend({

        getTemplate: function() {
            return template(this.options);
        },
        getStyle: function () {
            return Style;
        },

        getBody: function () {
            return this.getElement();
        },
        getContent: function () {
            return this.getElement().find('.'+selectionListView.EL_CONTENT);
        },
        getDropdownDiv: function(){
            return this.getElement().find('.'+selectionListView.EL_DROPDOWN);
        },
        getActionsDiv: function(){
            return this.getElement().find('.'+selectionListView.EL_ACTIONS);
        },
        getActionsSelectAll: function(){
            return this.getElement().find('.'+selectionListView.EL_ACTIONS_SELECTALL);
        },
        getActionsClearSelection: function(){
            return this.getElement().find('.'+selectionListView.EL_ACTIONS_DESELECTALL);
        },
        setHeader: function(value){
            this.getElement().find('.'+selectionListView.EL_HEADER_SPAN).setText(value);
        },
        setHeaderWidth: function(){
            var dropdownWidth = this.getDropdownDiv().getProperty('offsetWidth');
            this.getElement().find('.'+selectionListView.EL_HEADERDIV).setStyle('width', 'calc(100% - ' + (dropdownWidth + 10) + 'px)');
        }

    }, {
        'EL_CONTENT': 'elWidgets-SelectionList-content',
        'EL_HEADERDIV': 'elWidgets-SelectionList-header',
        'EL_HEADER': 'elWidgets-SelectionList-header-text',
        'EL_HEADER_SPAN': 'elWidgets-SelectionList-header-text-span',
        'EL_DROPDOWN': 'elWidgets-SelectionList-dropdown',
        'EL_ACTIONS': 'elWidgets-SelectionList-actions',
        'EL_ACTIONS_SELECTALL': 'elWidgets-SelectionList-actions-selectAll',
        'EL_ACTIONS_DESELECTALL': 'elWidgets-SelectionList-actions-deselectAll'
    });

    return selectionListView;

});

define('widgets/SelectionList/SelectionList',['widgets/WidgetCore','./SelectionListView','./Item/Item','widgets/Dropdown'],function (WidgetCore, View, Item, Dropdown) {
    'use strict';

    /**
     * The SelectionList class uses the Ericsson brand assets.<br>
     * The SelectionList can be instantiated using the constructor SelectionList.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>SelectionList(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>change: this event is triggered when value is change in the SelectionList</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>items: an array used as a list of available items in the selectBox</li>
     *       <li>showSelectActions: a boolean, if false the select all and deselect all actions are not shown. Default is true</li>
     *       <li>dropdown: an object with dropdown options. Refer to the Dropdown widget documentation</li>
     *       <li>header: the header text.</li>
     *       <li>icon: icon to be placed beside the header. "ebIcon_" does not need to be specified.</li>
     *       <li>headerTagName: the tag name of the header. Default is h2.</li>
     *       <li>selectLabel: Select label text. Default is "Select:"</li>
     *       <li>selectAllLabel: Select label text. Default is "All"</li>
     *       <li>selectNoneLabel: Select label text. Default is "None"</li>
     *   </ul>
     *
     * @example
     * var SelectionListWidget  = new SelectionList({
     *      header: 'header text',
     *      items: [
     *          {name: 'Name 1', value: 1, selected: true},
     *          {type: 'separator'},
     *          {name: 'Name 2', value: 2, selected: false}
     *       ],
     *       showSelectActions: true,
     *       dropdown: {
     *          items: [
     *          {name: 'Some action', action: function () { alert('Some action'); }},
     *          {name: 'Other action', action: function () { alert('Other action'); }}
     *          ]
     *       }
     * });
     *
     * @class SelectionList
     * @extends WidgetCore
     */

    return WidgetCore.extend({
        view: function() {
            if(this.options.headerTagName && !/(h|H)[2-4]/.test(this.options.headerTagName)){
                this.options.headerTagName = undefined;
            }
            return new View(this.options);
        },
        init: function (options) {
            if(options){
                this._data = options.items || [];
                this._items = [];
                this._selectedItems = [];
                if (options.dropdown) {
                    this._hasDropdown = true;
                    this._dropdownData = options.dropdown;
                }
                if(options.showSelectActions){
                    this._showSelectActions = true;
                }
                if(options.header){
                    this._header = options.header;
                }
            }
        },
        /**
         * Sets header text
         *
         * @method setHeader
         * @param {string} text
         */
        setHeader: function(text){
            this.view.setHeader(text);
            this.view.setHeaderWidth();
        },

        onViewReady: function () {
            if (this._data) {
                initializeItems.call(this);
            }
            if (this._hasDropdown) {
                setDropdown.call(this);
            }
            if(this._showSelectActions){
                setSelectDeselectActions.call(this);
            }
            if(this._header){
                this.setHeader(this._header);
            }

        },

        onDOMAttach: function(){
            this.view.setHeaderWidth();
        },

        /**
         * Returns array of all items
         *
         * @method getItems
         * @return {Array}
         */
        getItems: function () {
            return this._items;
        },
        /**
         * Removes item in provided index
         *
         * @method removeItem
         * @param {int} index
         */
        removeItem: function (index) {
            var item = this._items[index];
            if (this._selectedItems.indexOf(item) !== -1) {
                this._selectedItems.splice(this._selectedItems.indexOf(item), 1);
            }
            if (this._items[index]) {
                this._items.splice(index, 1);
            }
            item.destroy();
        },
        /**
         * DeSelects all items in the list
         *
         * @method clearSelection
         */
        clearSelection: function (silent) {
            this._items.forEach(function (item, index) {
                if (item.isHighlighted()) {
                    selectItem.call(this,item, true, true);
                }
            }.bind(this));
            if(!silent){
                this.trigger('change');
            }
        },
        /**
         * Selects all items in the list
         *
         * @method selectAll
         */
        selectAll: function (silent) {
            this._items.forEach(function (item, index) {
                if (!item.isHighlighted()) {
                    selectItem.call(this,item, false, true);
                }

            }.bind(this));
            if(silent !== true){
                this.trigger('change');
            }
        },
        selectItem: function(index){
            index = parseInt(index);
            if(index >= 0 && index < this._items.length){
                selectItem.call(this, this._items[index]);
            }
        },
        /**
         * Returns array of all items
         *
         * @method getSelected
         * @return {Array}
         */
        getSelected: function () {
            var selected = [];
            this._selectedItems.forEach(function (item, index) {
                var obj = item.options.data;
                obj.index = getIndex.call(this, item);
                selected.push(obj);
            }.bind(this));

            return selected;
        },
        /**
         * Adds an item to the list in the provided index
         *
         * @method addItem
         * @param {Object} itemData
         * @param {int} index
         */
        addItem: function (itemData, index) {
        var item = new Item({
            data: itemData,
            list: this
        });
        if (index === undefined) {
            this._items.push(item);
            item.attachTo(this.view.getContent());
        } else {
            attachItemAt.call(this, item, index);
            this._items.splice(index, 0, item);
        }

        item.view.getBody().addEventHandler('click', function (e) {
            e.originalEvent.preventDefault();
            if (e.originalEvent.shiftKey && this._previouslyHightlighted && this._previouslyHightlighted !== item) {
                // check if there is a prev selection and select everything from prev to current selection
                e.preventDefault();
                var prevIndex = getIndex.call(this, this._previouslyHightlighted);
                var currentIndex = getIndex.call(this, item);
                var max = Math.max(prevIndex, currentIndex);
                var min = Math.min(prevIndex, currentIndex);
                var selectionLength = this._selectedItems.length;
                var selectedIndexes = getSelectedIndexes.call(this);
                var listMin = Math.min.apply(this, selectedIndexes);
                var listMax = Math.max.apply(this, selectedIndexes);
                var consecutiveSelection = hasConsecutiveValues(selectedIndexes);


                if(selectionLength > 1 && consecutiveSelection){
                    if( (currentIndex < listMin && prevIndex === listMax) || (currentIndex > listMin && prevIndex === listMin)){
                        selectItem.call(this, this._items[prevIndex], true, true);
                    }
                }
                for (var i = min; i <= max; i++) {
                    if(i !== currentIndex && i !== prevIndex){
                        if(consecutiveSelection === true || !this._items[i].isHighlighted()){
                            selectItem.call(this, this._items[i], true, true);
                        }
                    }
                }
                selectItem.call(this, item, true, true);
                this.trigger('change');

            } else if (e.originalEvent.ctrlKey && this._selectedItems.length > 0) {
                // add item to selection
                selectItem.call(this,item, true);
            } else {
                // add selection
                if (this._selectedItems.length > 0 && this._selectedItems.indexOf(item) === -1) {
                    this.clearSelection(true);
                }
                selectItem.call(this, item);
            }

        }.bind(this));

        if (itemData.selected === true) {
            selectItem.call(this,item);
        }
    },
        /**
         * Resets List items
         *
         * @method setItems
         * @param {Array} data
         */
        setItems: function (data) {
            this.clear();
            data.forEach(function (itemData) {
                this.addItem(itemData);
            }.bind(this));
        },
        /**
         * Empties the list
         *
         * @method clear
         */
        clear: function () {
            this._items.forEach(function (item) {
                item.destroy();
            }.bind(this));
            this._items = [];
            this._selectedItems = [];
        },
        getDropdown: function(){
            return this.dropdown;
        },
        /**
         * Removes the Widget root Element from the DOM.
         *
         * @method destroy
         *
         * @example
         *   selectionList.destroy();
         */
        destroy: function () {
            this.clear();
            WidgetCore.prototype.destroy.call(this);
        }
    });
    /**
     * initializes all the items
     *
     * @method initializeItems
     * @private
     */
    function initializeItems(){
        /*jshint validthis:true */
        this._data.forEach(function (item, index) {
            this.addItem(item);
        }.bind(this));
    }
    /**
     * Creates dropdown
     *
     * @method setDropdown
     * @private
     */
    function setDropdown(){
        /*jshint validthis:true */
        this.dropdown = new Dropdown(this._dropdownData);
        this.dropdown.attachTo(this.view.getDropdownDiv());

        this.addEventHandler('change', enableDisableDropdown.bind(this));
        enableDisableDropdown.call(this);
    }
    /**
     * Toggles enable and disable of the dropdown
     *
     * @method enableDisableDropdown
     * @private
     */
    function enableDisableDropdown() {
        /*jshint validthis:true */
        if (this._selectedItems.length > 0) {
            this.dropdown.enable();
        } else {
            this.dropdown.disable();
        }
    }
    /**
     * Adds click handle for select and deselect links
     *
     * @method setSelectDeselectActions
     * @private
     */
    function setSelectDeselectActions(){
        /*jshint validthis:true */
        this.view.getActionsSelectAll().addEventHandler('click', function(){
            this.selectAll();
        }.bind(this));
        this.view.getActionsClearSelection().addEventHandler('click', function(){
            this.clearSelection();
        }.bind(this));
    }

    /**
     * Selects item
     *
     * @method selectItem
     * @private
     * @param {Item} item
     * @param {Boolean} multiple
     * @param {Boolean} silent
     */
    function selectItem(item, multiple, silent) {
        /*jshint validthis:true */
        if (item.isHighlighted()) {
            item.unhighlight();
            if (this._selectedItems.indexOf(item) !== -1) {
                this._selectedItems.splice(this._selectedItems.indexOf(item), 1);
            }
            if (!multiple) {
                this.clearSelection(true);
            }
            item.options.data.selected = false;

        } else {
            item.highlight();
            this._selectedItems.push(item);
            this._previouslyHightlighted = item;
            item.options.data.selected = true;
        }
        if (!silent) {
            this.trigger('change');
        }
    }

    /**
     * Returns index of the item in the _items array
     *
     * @method getIndex
     * @private
     * @return {int}
     */
    function getIndex(item) {
        /*jshint validthis:true */
        return this._items.indexOf(item);
    }
    /**
     * Returns an array of indexes
     *
     * @method getSelectedIndexes
     * @private
     * @return {Array} int
     */
    function getSelectedIndexes(){
        /*jshint validthis:true */
        var indexes = [];
        this._selectedItems.forEach(function(value){
            indexes.push(getIndex.call(this, value));
        }.bind(this));
        return indexes;
    }

    function hasConsecutiveValues(array){
        /*jshint validthis:true */
        var result = true;
        var value = false;
        array.sort(function(a, b){return a-b;});
        array.forEach(function(index, i){
            if(value !== false && value !== index - 1 && i!== array.length -1){
                result = false;
            }
            value = index;
        }.bind(this));

        return result;
    }
    /**
     * Attaches item to specified index
     *
     * @method attachItemAt
     * @private
     */
    function attachItemAt(item, index) {
        /*jshint validthis:true */
        var el = item.getElement()._getHTMLElement();
        var other = this.getItems()[index];
        var list = this.view.getContent()._getHTMLElement();

        if (other) {
            other = other.getElement()._getHTMLElement();
            list.insertBefore(el, other);
        } else {
            row.attachTo(this.view.getBody());
        }
    }
});

define('widgets/SelectionList',['widgets/SelectionList/SelectionList'],function (main) {
                        return main;
                    });

