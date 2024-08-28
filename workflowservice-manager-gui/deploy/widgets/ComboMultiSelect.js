/* Copyright (c) Ericsson 2014 */

define('text!widgets/ComboMultiSelect/_comboMultiSelect.html',[],function () { return '<div class="ebComboMultiSelect">\n    <textarea class="ebComboMultiSelect-textarea ebTextArea" autocomplete="off"></textarea>\n    <button type="button" class="ebComboMultiSelect-helper">\n        <span class="ebComboMultiSelect-iconHolder">\n            <i class="ebIcon ebIcon_small ebIcon_downArrow_10px eb_noVertAlign"></i>\n        </span>\n    </button>\n    <div class="ebComboMultiSelect-listHolder"></div>\n\n    <div class="ebComboMultiSelect-body"></div>\n</div>';});

define('widgets/ComboMultiSelect/ComboMultiSelectView',['jscore/core','text!./_comboMultiSelect.html'],function (core, template) {
    'use strict';

    var prefix = '.ebComboMultiSelect-';

    return core.View.extend({

        afterRender: function () {
            this.textarea = this.getElement().find(prefix + 'textarea');
            this.helper = this.getElement().find(prefix + 'helper');
            this.listHolder = this.getElement().find(prefix + 'listHolder');
        },

        getTemplate: function () {
            return template;
        },

        getTextArea: function () {
            return this.textarea;
        },

        getHelper: function () {
            return this.helper;
        },

        getListHolder: function () {
            return this.listHolder;
        }
    });
});

define('widgets/utils/textUtils',[],function () {
    'use strict';

    var textUtils = {
        getTextWidth: function (text, font) {
            // use cached canvas for better performance or
            // create new canvas
            var canvas = textUtils.getTextWidth.canvas || (textUtils.getTextWidth.canvas = document.createElement("canvas")),
                context = canvas.getContext("2d");
            context.font = font;
            var metrics = context.measureText(text);
            return Math.round(metrics.width);
        }
    };

    return textUtils;
});

define('widgets/utils/keyboardUtils',[],function () {
    'use strict';

    /**
     * The keyboardUtils provides simple keyboard variables.
     *
     * @class utils.keyboardUtils
     */
    var kbUtils = {},
        constsKeyVals = {
            /**
             * Backspace key code
             * @attribute BACKSPACE
             * @readOnly
             * @for utils.keyboardUtils
             */
            BACKSPACE: 8,
            /**
             * Tab key code
             * @attribute TAB
             * @readOnly
             * @for utils.keyboardUtils
             */
            TAB: 9,
            /**
             * Enter key code
             * @attribute ENTER
             * @readOnly
             * @for utils.keyboardUtils
             */
            ENTER: 13,
            /**
             * Escape key code
             * @attribute ESCAPE
             * @readOnly
             * @for utils.keyboardUtils
             */
            ESCAPE: 27,
            //-----------------------------
            /**
             * White Space key code
             * @attribute SPACE
             * @readOnly
             * @for utils.keyboardUtils
             */
            SPACE: 32,
            //-----------------------------
            /**
             * End key code
             * @attribute END
             * @readOnly
             * @for utils.keyboardUtils
             */
            END: 35,
            /**
             * Home key code
             * @attribute HOME
             * @readOnly
             * @for utils.keyboardUtils
             */
            HOME: 36,
            //-----------------------------
            /**
             * Arrow Left key code
             * @attribute ARROW_LEFT
             * @readOnly
             * @for utils.keyboardUtils
             */
            ARROW_LEFT: 37,
            /**
             * Arrow Up key code
             * @attribute ARROW_UP
             * @readOnly
             * @for utils.keyboardUtils
             */
            ARROW_UP: 38,
            /**
             * Arrow Right key code
             * @attribute ARROW_RIGHT
             * @readOnly
             * @for utils.keyboardUtils
             */
            ARROW_RIGHT: 39,
            /**
             * Arrow Down key code
             * @attribute ARROW_DOWN
             * @readOnly
             * @for utils.keyboardUtils
             */
            ARROW_DOWN: 40,
            //-----------------------------
            /**
             * Delete key code
             * @attribute DELETE
             * @readOnly
             * @for utils.keyboardUtils
             */
            DELETE: 46
        },
        mapKeyCodeToCallBack = function (event, options) {
            // get the keycode of the typed char
            var keyCode = event.originalEvent.keyCode;

            // trigger the defined callbacks depending on keyCode, pass event as a param
            // return the value of the callback

            var clbk;

            // cannot use return directly
            // since we want to return callback value
            // and some returns true / false
            Object.keys(options).some(function (clbkName) {
                if (kbUtils[clbkName.toUpperCase()] === keyCode) {
                    clbk = options[clbkName];
                    return true;
                }
            });

            if (clbk) {
                return clbk(event);
            }
        },
        _addKeyDownHandler = function (elt, options) {
            if (!elt || !options) {
                throw new Error('Missing event argument');
            }

            return elt.addEventHandler('keydown', function (event) {
                return mapKeyCodeToCallBack(event, options);
            });
        },
        _removeKeyDownHandler = function (elt, evtId) {
            if (!evtId) {
                return;
            }
            elt.removeEventHandler(evtId);
        };

    // constsKeyVals:
    // define a list of constant to define
    // for each constant entry, define it as a read only attribute for keyboardUtils
    Object.keys(constsKeyVals).forEach(function (keyName) {
        Object.defineProperty(kbUtils, keyName, {
            value: constsKeyVals[keyName],
            writable: false // explicit
        });
    });

    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------

    /**
     * Bind the keypress event to the passed element. Trigger defined callback on arrow press.
     * Accepts "arrow_up", "arrow_down", "arrow_left", "arrow_right", "backspace", "tab", "enter", "escape", "end", "home", "delete".
     *
     * @example
     *   keyboardUtils.addKeyHandler(myElement, {
     *      arrow_up:     function(event){...do things},
     *      arrow_down:   function(event){...do things},
     *      arrow_left:   function(event){...do things},
     *      arrow_right:  function(event){...do things},
     *      backspace:  function(event){...do things},
     *      tab:        function(event){...do things},
     *      enter:      function(event){...do things},
     *      escape:     function(event){...do things},
     *      end:        function(event){...do things},
     *      home:       function(event){...do things},
     *      delete:     function(event){...do things}
     *   });
     *
     * @method addKeyHandler
     * @param {core.Element} element
     * @param {Object} callbackMap
     * @return eventId
     * @for utils.keyboardUtils
     */
    kbUtils.addKeyHandler = _addKeyDownHandler;

    /**
     * Unbind the event of the passed element.
     *
     * @method removeKeyHandler
     * @param {core.Element} element
     * @param {Object} evtId
     * @for utils.keyboardUtils
     */
    kbUtils.removeKeyHandler = _removeKeyDownHandler;

    return kbUtils;
});

define('text!widgets/ComboMultiSelect/selectList/_selectList.html',[],function () { return '<ul class="ebComboMultiSelectList"></ul>';});

define('widgets/ComboMultiSelect/selectList/SelectListView',['jscore/core','text!./_selectList.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        }
    });
});

define('template!widgets/ComboMultiSelect/selectList/item/_item.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, foundHelper, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;


  buffer += "<li class=\"ebComboMultiSelectList-item\">\n    <span class=\"ebComboMultiSelectList-itemTitle\" title=\"";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</span>\n    <i class=\"ebComboMultiSelectList-itemClose ebIcon ebIcon_close ebIcon_interactive\"></i>\n</li>";
  return buffer;});});

define('widgets/ComboMultiSelect/selectList/item/ItemView',['jscore/core','template!./_item.html'],function (core, template) {
    'use strict';

    var prefix = '.ebComboMultiSelectList-';

    return core.View.extend({

        getTemplate: function () {
            return template(this.options);
        },
        getCloseIcon: function () {
            return this.getElement().find(prefix + 'itemClose');
        }
    });
});

define('widgets/ComboMultiSelect/selectList/item/Item',['jscore/core','./ItemView'],function (core, View) {
    'use strict';

    return core.Widget.extend({

        ITEM_CLOSE: 'item:close',
        ITEM_SELECT: 'item:select',

        view: function () {
            return new View(this.options.data);
        },

        onViewReady: function () {
            // only keep values of the public API
            this.data = {
                name: this.options.data.name,
                value: this.options.data.value,
                title: this.options.data.title
            };

            // apply the disabled style
            if (this.options.enabled === false) {
                this.disable();
            } else {
                this.enable();
            }
        },

        getData: function () {
            return this.data;
        },

        getPosition: function () {
            return this.getElement().getPosition();
        },
        enable: function () {
            // bind the select event if not defined
            if (this._selectEvtId === undefined) {
                this._selectEvtId = this.getElement().addEventHandler('click', function () {
                    this.select();
                }, this);
            }

            var closeIcon = this.view.getCloseIcon();

            // bind the close event if not defined,
            // destroy handled by the parent list
            if (this._closeEvtId === undefined) {
                this._closeEvtId = closeIcon.addEventHandler('click', function () {
                    this.trigger(this.ITEM_CLOSE, this);
                }, this);
            }

            // when a selectList item is clicked, prevent propagation to parent to avoid list opening
            this.getElement().addEventHandler('click', function (evt) {
                if (evt && evt.stopPropagation) {
                    evt.stopPropagation();
                }
                this.select();
            }.bind(this));

            this.getElement().removeModifier('disabled');
            closeIcon.removeModifier('disabled', 'ebIcon');
        },
        disable: function () {
            // remove the event binding for select
            if (this._selectEvtId) {
                this._selectEvtId.destroy();
                delete this._selectEvtId;
            }

            var closeIcon = this.view.getCloseIcon();

            // remove the event binding for close
            if (this._closeEvtId) {
                this._closeEvtId.destroy();
                delete this._closeEvtId;
            }
            this.getElement().setModifier('disabled');
            closeIcon.setModifier('disabled', '', 'ebIcon');
        },
        select: function (options) {
            if (!options || options.silent !== true) {
                this.trigger(this.ITEM_SELECT, this);
            }
            this.getElement().setModifier('active');
        },
        unselect: function () {
            this.getElement().removeModifier('active');
        }
    });
});

define('widgets/ComboMultiSelect/selectList/SelectList',['jscore/core','./item/Item','widgets/utils/domUtils','./SelectListView'],function (core, Item, domUtils, View) {
    'use strict';

    return core.Widget.extend({

        LIST_CHANGE: 'list:change',
        LIST_SELECT: 'list:select',

        View: View,

        init: function (options) {
            this._childItems = [];
        },

        onViewReady: function () {
            this.setSelectCount(this.options.selectCount);
            // Set the select list content
            this.setItems(this.options.items);
        },

        getItems: function () {
            var itemsData = [];
            this._childItems.forEach(function (cItem) {
                itemsData.push(cItem.getData());
            });
            return itemsData;
        },

        getLastItemPosition: function () {
            return lastItemPosition.call(this);
        },
        getLastItem: function () {
            return this._childItems[this._childItems.length - 1];
        },

        emptyList: function () {
            // if there is any child element destroy all to empty the list
            this._childItems.forEach(function (cItem) {
                cItem.destroy();
            });
            this._childItems = [];
        },

        getSelectCount: function () {
            return this.selectCount;
        },
        setSelectCount: function (selectCount) {
            this.selectCount = selectCount;
            if (this.selectCount === 1) {
                this.getElement().setModifier('select', 'single');
            } else {
                this.getElement().removeModifier('select', 'single');
            }
        },

        setItems: function (items) {
            // All items are removed and replaced
            this.emptyList();

            // supports items not defined to clear the list
            items = items || [];

            // add the children (if any) one by one to the list
            items.forEach(this.addItem.bind(this));
        },

        addItem: function (itemData) {
            if (itemExistsInList.call(this, itemData)) {
                return;
            }
            // child is just display and constrol, does not hold value
            // it allows to keep the reference of the items array passed
            var item = new Item({
                data: itemData,
                enabled: this.isEnabled()
            });

            // bind the close event
            item.addEventHandler(Item.prototype.ITEM_SELECT, this.setSelectedItem, this);
            item.addEventHandler(Item.prototype.ITEM_CLOSE, removeChildItem, this);

            // If selection limit reached, replace last item
            if (this.getSelectCount() === this._childItems.length) {
                this.removeItem(this.getLastItem());
            }

            // add to the child list
            this._childItems.push(item);

            item.attachTo(this.getElement());

            // communicate change
            this.trigger(this.LIST_CHANGE);
        },
        isEnabled: function () {
            return this.enabled;
        },
        enable: function () {
            this.enabled = true;
            // enable all the children
            this._childItems.forEach(function (item) {
                item.enable();
            });
        },
        disable: function () {
            this.enabled = false;
            // disable all the children
            this._childItems.forEach(function (item) {
                item.disable();
            });
        },
        selectPreviousItem: function () {
            var len = this._childItems.length;
            if (len === 0) {
                return;
            }
            if (this.selectedItem === undefined) {
                this.getLastItem().select();
                return;
            }
            var index = this._childItems.indexOf(this.selectedItem) - 1,
                item = this._childItems[index > 0 ? index : 0];
            item.select();
        },
        selectNextItem: function () {
            var len = this._childItems.length;
            if (len === 0) {
                return;
            }
            var index = this._childItems.indexOf(this.selectedItem) + 1,
                item = this._childItems[index !== len ? index : len - 1];
            item.select();
        },
        getSelectedItem: function () {
            return this.selectedItem;
        },
        setSelectedItem: function (selectedItem) {
            if (this.selectedItem !== undefined) {
                // reset modifier for previous selected
                this.selectedItem.unselect();
            }
            this.selectedItem = selectedItem;
            this.trigger(this.LIST_SELECT);
        },
        removeItem: function (item) {
            if (item !== undefined) {
                if (item === this.getSelectedItem()) {
                    this.setSelectedItem(undefined);
                }
                removeChildItem.call(this, item);
            }
        }
    });

    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------

    function removeChildItem(itemToDestroy) {
        /*jshint validthis:true*/

        // get the index of the child to destroy
        var destroyedIndex = this._childItems.indexOf(itemToDestroy);
        // update the array of children
        if (destroyedIndex !== -1) {
            this._childItems.splice(destroyedIndex, 1);
        }

        // destroy children
        itemToDestroy.destroy();

        // communicate the change
        this.trigger(this.LIST_CHANGE);
    }

    //-----------------------------------------------------------------------------
    function itemExistsInList(itemData) {
        /*jshint validthis:true*/

        // use of some to avoid useless looping once found
        return this._childItems.some(function (child) {
            return itemEquals(itemData, child.getData());
        });
    }

    //-----------------------------------------------------------------------------
    function itemEquals(a, b) {
        /*jshint validthis:true*/

        // custom equals to compare on value / title / name
        return a.name === b.name && a.title === b.title && a.value === b.value;
    }

    //-----------------------------------------------------------------------------
    function lastItemPosition() {
        /*jshint validthis:true*/

        // get the position of the last item on the list,
        // allows to adjust the indent and place the text right after it
        var listPos = this.getElement().getPosition(),
            lastItem = this._childItems[this._childItems.length - 1];

        return {
            x: lastItem ? Math.floor(lastItem.getPosition().right - listPos.left) : 0,
            y: lastItem ? Math.floor(lastItem.getPosition().top - listPos.top) : 0
        };
    }
});

define('widgets/ComboMultiSelect/ComboMultiSelect',['jscore/core','widgets/ItemsControl','./selectList/SelectList','../utils/keyboardUtils','widgets/utils/domUtils','../utils/textUtils','./ComboMultiSelectView'],function (core, ItemsControl, SelectList, kbUtils, domUtils, textUtils, View) {
    'use strict';

    /**
     * The ComboMultiSelect class uses the Ericsson brand assets.<br>
     * The ComboMultiSelect can be instantiated using the constructor ComboMultiSelect.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>ComboMultiSelect(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>change: this event is triggered when value is changed in the ComboMultiSelect</li>
     *     <li>focus: this event is triggered when the ComboMultiSelect is focused</li>
     *     <li>click: this event is triggered when user clicks on the ComboMultiSelect button</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>placeholder: a string used as a default name of the ComboMultiSelect</li>
     *       <li>value: an array of object listing selected items of the ComboMultiSelect</li>
     *       <li>items: an array used as a list of available items in the ComboMultiSelect</li>
     *       <li>enabled: boolean indicating whether the ComboMultiSelect should be enabled. Default is true.</li>
     *       <li>modifiers: an array used to define modifiers for the ComboMultiSelect.  (Asset Library)
     *         <a href="#modifierAvailableList">see available modifiers</a>
     *         <br>E.g: modifiers:[{name: 'foo'}, {name: 'bar', value:'barVal'}]
     *       </li>
     *       <li>autoComplete: (optional)
     *          <ul>
     *              <li>enabled: boolean enabling the autoComplete feature. default true.</li>
     *              <li>message: (optional)
     *                  <ul>
     *                      <li>notFound: Message displayed when no item matches the criteria. Override with internationalization value.</li>
     *                  </ul>
     *              </li>
     *          </ul>
     *       </li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>disabled: disabled (Asset Library)</li>
     *      <li>width: available sizes: ['small', 'long', 'xLong', 'full'] (Asset Library)</li>
     *  </ul>
     *
     * @example
     *     var widget = new ComboMultiSelect({
     *         value: [
     *             {name: 'Value 1', title: 'Title 1', value: 1}
     *         ],
     *         items: [
     *             {name: 'Value 1', title: 'Title 1', value: 1},
     *             {name: 'Value 2 long', title: 'Title 2 long', value: 2},
     *             {name: 'Value 3 longer', title: 'Title 3 longer', value: 3}
     *         ],
     *         autoComplete: {
     *             message: {notFound: 'Aucun resultat'}
     *         },
     *         modifiers:[{name: 'width', value:'full'}]
     *     });
     *
     * @class ComboMultiSelect
     * @extends ItemsControl
     */
    return ItemsControl.extend({

        View: View,

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @private
         * @param {Object} options
         */
        init: function (options) {
            this.options = options || {};
            // create the list of selected items
            this._selectList = new SelectList({
                selectCount: options ? options.selectCount : undefined,
                items: this.options.value
            });

            this._selectList.addEventHandler(SelectList.prototype.LIST_CHANGE, function () {
                // adaptTextAreaHeight returns true if the text area height has changed
                if (adaptTextAreaHeight.call(this)) {
                    this.reRenderListIfNeeded();
                }
                this.trigger('change');
            }, this);

            this._selectList.addEventHandler(SelectList.prototype.LIST_SELECT, function () {
                this.getFocusableElement().focus();
            }, this);
        },

        /**
         * Overrides method from ItemControl.<br>
         * Executes every time, when added back to the screen.
         */
        onControlReady: function () {
            // Define and override component key controls
            defineComponentSpecificKeyHandler.call(this);

            this._selectList.attachTo(this.view.getListHolder());

            var placeHolder = (this.options.value && this.options.value.length !== 0) ? '' : this.options.placeholder;
            this.setPlaceholder(placeHolder);

            this._windowEvtId = core.Window.addEventHandler('resize', this.resize.bind(this));
        },

        /**
         * Excecuted when the component is attached to the DOM.
         *
         * @method onDOMAttach
         * @private
         */
        onDOMAttach: function () {
            adaptTextAreaHeight.call(this);
        },

        /**
         * Overrides method from widget.
         * Executes before destroy, remove event handlers.
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function () {
            if (this._windowEvtId) {
                core.Window.removeEventHandler(this._windowEvtId);
                delete this._windowEvtId;
            }
        },

        /**
         * Resizes the ComboMultiSelect and adapt the list items with editable area
         *
         * @method resize
         */
        resize: function () {
            if (adaptTextAreaHeight.call(this, true)) {
                this.reRenderListIfNeeded();
            }
        },

        /**
         * Sets selected values for the ComboMultiSelect.
         *
         * @method setValue
         * @param {Array} items
         * @examples
         *   comboMultiSelect.setValue([
         *      {name: 'Value 1', title: 'Title 1', value: 1}
         *      {name: 'Value 2', title: 'Title 2', value: 2}
         *   ]);
         */
        setValue: function (items) {
            if (items === undefined) {
                return;
            }

            this._selectList.setItems(items);
        },

        /**
         * Returns the selected items object from the ComboMultiSelect.
         *
         * @method getValue
         * @return {Array} value
         */
        getValue: function () {
            return this._selectList.getItems();
        },

        /**
         * Returns the element that get the focus
         */
        getFocusableElement: function () {
            return this.view.getTextArea();
        },

        /**
         * Called when the focusable element receives input.
         */
        onFocusableElementInput: function () {
            adaptTextAreaHeight.call(this);
        },

        /**
         * Sets placeholder for the ComboMultiSelect.
         *
         * @method setPlaceholder
         * @param {String} placeholder
         */
        setPlaceholder: function (placeholder) {
            this.view.getTextArea().setAttribute('placeholder', placeholder || '');
        },

        /**
         * A method which is called when enable() method is called.
         */
        onEnable: function () {
            if (this._selectList.getSelectCount() === 1 && this._keyDownEvtId === undefined) {
                // if the selection limit is 1
                // on keydown to prevent invalid input before it is rendered

                this._keyDownEvtId = this.getFocusableElement().addEventHandler('keydown', function (evt) {
                    // When the component is on single selection mode, no further text input is allowed
                    if (evt.originalEvent.keyCode !== kbUtils.TAB && this._selectList.getItems().length === 1) {
                        evt.preventDefault();
                    }
                }, this);
            }

            // Enable the selected item list
            this._selectList.enable();

            this.view.getTextArea().setAttribute('disabled', false);
            this.view.getHelper().setAttribute('disabled', false);
        },

        /**
         * A method which is called when disable() method is called.
         */
        onDisable: function () {
            // Disable the selected item list
            this._selectList.disable();

            // remove event if defined
            if (this._keyDownEvtId !== undefined) {
                this.getFocusableElement().removeEventHandler(this._keyDownEvtId);
                delete this._keyDownEvtId;
            }

            this.getFocusableElement().setAttribute('disabled', true);
            this.view.getHelper().setAttribute('disabled', true);
        },

        /**
         * A method which is called when an item is selected.
         */
        onItemSelected: function (selectedVal) {
            this.getFocusableElement().setValue('');
            this.getFocusableElement().focus();

            // restore the component list with the full list of choice
            this.setItems(this.options.items);

            // update the list with the new item
            this._selectList.addItem(selectedVal);

            // clear the current list selection
            this._selectList.setSelectedItem(undefined);
        }



        /**
         * @method disableKeyboardControl
         * @private
         */
        /**
         * @method enableKeyboardControl
         * @private
         */
        /**
         * @method getComponentListClass
         * @private
         */
        /**
         * @method getFocusableElement
         * @private
         */
        /**
         * @method hideMessageInfo
         * @private
         */
        /**
         * @method isKeyboardControlled
         * @private
         */
        /**
         * @method onControlReady
         * @private
         */
        /**
         * @method onDisable
         * @private
         */
        /**
         * @method onEnable
         * @private
         */
        /**
         * @method onFocusableElementInput
         * @private
         */
        /**
         * @method onItemSelected
         * @private
         */
        /**
         * @method onItemsSet
         * @private
         */
        /**
         * @method onListHide
         * @private
         */
        /**
         * @method onListShow
         * @private
         */
        /**
         * @method reRenderListIfNeeded
         * @private
         */
        /**
         * @method showList
         * @private
         */
        /**
         * @method showMessageInfo
         * @private
         */
    });

    //-----------------------------------------------------------------
    //-----------------------------------------------------------------

    function defineComponentSpecificKeyHandler() {
        /*jshint validthis:true*/
        this.keyBoardControl.backspace = onBackSpace.bind(this);
        this.keyBoardControl.delete = onDelete.bind(this);
        this.keyBoardControl.arrow_left = onArrowLeft.bind(this);
        this.keyBoardControl.arrow_right = onArrowRight.bind(this);
    }

    //-----------------------------------------------------------------
    function onBackSpace() {
        /*jshint validthis:true*/
        var cursorPos = getTextCursorPosition.call(this);

        // if the cursor is at index 0
        if (cursorPos.start === 0 && cursorPos.end === 0) {
            var selectedItem = this._selectList.getSelectedItem();

            // if an item is selected, remove it,
            // else, select the last from the list
            if (selectedItem !== undefined) {
                this._selectList.removeItem(selectedItem);
            } else {
                this._selectList.setSelectedItem(this._selectList.getLastItem());
            }
        }
    }

    //-----------------------------------------------------------------
    function onDelete(evt) {
        /*jshint validthis:true*/
        var selectedItem = this._selectList.getSelectedItem();

        if (selectedItem !== undefined) {
            // if the list has an item selected
            this._selectList.removeItem(selectedItem);
            evt.preventDefault();
        }
    }

    //-----------------------------------------------------------------
    function onArrowLeft(evt) {
        /*jshint validthis:true*/
        var cursorPos = getTextCursorPosition.call(this);

        // if the cursor is at index 0
        if (cursorPos.start === 0 && cursorPos.end === 0) {
            // Select previous list item
            this._selectList.selectPreviousItem();
        }
    }

    //-----------------------------------------------------------------
    function onArrowRight(evt) {
        /*jshint validthis:true*/
        var cursorPos = getTextCursorPosition.call(this);

        // if the cursor is at index 0
        if (cursorPos.start === 0 && cursorPos.end === 0) {

            var list = this._selectList,
                selectedItem = list.getSelectedItem(),
                lastItem = list.getLastItem();

            // return if no list items are selected or list is empty
            if (selectedItem === undefined || lastItem === undefined) {
                return;
            }

            // if the list item selected is the last item,
            // release the selection
            if (selectedItem === lastItem) {
                this._selectList.setSelectedItem(undefined);
            }
            else {
                list.selectNextItem();
            }

            evt.preventDefault();
        }
    }

    //-----------------------------------------------------------------
    //-----------------------------------------------------------------
    function adaptTextAreaHeight(widthUpdated) {
        /*jshint validthis:true*/
        var textArea = this.getFocusableElement(),
            textAreaDim = domUtils.getElementDimensions(textArea),
        // get the list last item position
            listBottomRightPos = this._selectList.getLastItemPosition(),
        // last item top position
            fromTop = listBottomRightPos.y || 4,
        // last item right position
            fromLeft = listBottomRightPos.x,
        // height is top position of last item + item height + padging
            newHeight = fromTop + 22;

        if (widthUpdated === true || !this._textAreaInnerWidth) {
            var paddingLeft = parseInt(textArea.getStyle('padding-left')),
                paddingRight = parseInt(textArea.getStyle('padding-right'));

            // cache the textarea width to avoid recalculate everytime
            // innerWidth = outerWidth - padding - border
            this._textAreaInnerWidth = textAreaDim.width - paddingLeft - paddingRight - 2;
        }

        var txtValueWidth = textUtils.getTextWidth(textArea.getValue() || textArea.getAttribute('placeholder'), textArea.getStyle('font')),
        // determine if the text fits between the last list item and the right border
            placeLeftOnLine = this._textAreaInnerWidth - listBottomRightPos.x,
        // apply a virtual 20% margin for esthetics's purposes
            carryOnNewLine = placeLeftOnLine < this._textAreaInnerWidth * 0.20;

        // if the text overflows on a new line
        if (carryOnNewLine && this._selectList.getSelectCount() !== 1) {
            //create new line
            fromLeft = 0;
            fromTop += 16;
            newHeight += 16;
            placeLeftOnLine = 0;
        }

        // number of additional lines taken by the text
        var additionalTextLinesCnt = Math.ceil((txtValueWidth - placeLeftOnLine) / this._textAreaInnerWidth);
        // remove one if carried in new line already
        additionalTextLinesCnt += carryOnNewLine ? -1 : 0;

        if (additionalTextLinesCnt > 0) {
            // line height 16px
            newHeight += additionalTextLinesCnt * 16;
        }
        var sizeChanged = textAreaDim.height !== newHeight;

        textArea.setStyle({
            'padding-top': fromTop + 'px',
            height: newHeight + 'px',
            'text-indent': fromLeft + 'px'
        });
        return sizeChanged;
    }

    //-----------------------------------------------------------------
    function getTextCursorPosition() {
        /*jshint validthis:true*/
        var textArea = this.getFocusableElement();
        return {
            start: textArea.getProperty('selectionStart'),
            end: textArea.getProperty('selectionEnd')
        };
    }
});

define('widgets/ComboMultiSelect',['widgets/ComboMultiSelect/ComboMultiSelect'],function (main) {
                        return main;
                    });

