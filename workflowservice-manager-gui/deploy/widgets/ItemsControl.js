/* Copyright (c) Ericsson 2014 */

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

define('widgets/ItemsControl/ItemsControl',['widgets/WidgetCore','widgets/ComponentList','../utils/keyboardUtils'],function (WidgetCore, ComponentList, keyboardUtils) {
    'use strict';

    /**
     * An abstract class for widgets to extend from. Supply a custom view. When any element in that view is clicked, a list of items will be displayed.
     * There are functions such as onControlReady and onItemSelected that should be overrided for custom behaviour. Please see the tutorial <a href="../../example-itemscontrol.html">here</a> for more information.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>ItemsControl(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>focus: this event is triggered when the ItemsControl is focused</li>
     *     <li>click: this event is triggered when user clicks on the ItemsControl</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>items: list of available items in the ItemsControl, see description below.</li>
     *       <li>enabled: boolean indicating whether the ItemsControl should be enabled. Default is true.</li>
     *       <li>modifiers: an array used to define modifiers for the ItemsControl.</li>
     *       <li>animate: boolean indicationg whether list of elements should be animated.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available. Available modifiers depend on the subclass that extends this class.</li>
     *  </ul>
     *
     * <strong>Items:</strong>
     *  <em>items</em> option can be in one of two different formats:
     *  <ol>
     *   <li>
     *    <strong>Non-grouped component list options</strong>:
     *      items is an array of objects with the following properties:
     *      <ul>
     *       <li>name: the text for the item that appears in the dropdown. Must be defined unless type is separator.</li>
     *       <li>type: optional parameter. if set to "separator", a horizontal separator will be shown.</li>
     *       <li>title: optional parameter. The text to display in the native tooltip when hovering over the item.</li>
     *       <li>link: optional parameter. If defined, item will be rendered as a link with the value of this option used as URL.</li>
     *      </ul>
     *   </li>
     *
     *   <li>
     *    <strong>Grouped component list options</strong>:
     *      items is an array of objects with the following properties:
     *      <ul>
     *       <li>header: text to display as a group header.</li>
     *       <li>items: items to display in the group, follows format from above.</li>
     *      </ul>
     *   </li>
     *  </ol>
     *
     * @class ItemsControl
     * @extends WidgetCore
     * @beta
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            var options = this.options;

            this.autoComplete = {};
            // support for more in the future
            this.autoComplete.message = {};
            var defaultNotFoundMessage = 'Items not found.';

            if (options.autoComplete === undefined) {
                // autoComplete is optional param, focusable element MUST implement the getValue function
                this.autoComplete.enabled = this.getFocusableElement().getValue !== undefined;
                // notFound message should be provided for internationalization purposes, if not defined use default.
                this.autoComplete.message.notFound = defaultNotFoundMessage;
            } else {
                //
                this.autoComplete.enabled = options.autoComplete.enabled !== false;
                var customMessageDefined = options.autoComplete.message !== undefined && options.autoComplete.message.notFound !== undefined;
                this.autoComplete.message.notFound = customMessageDefined ? options.autoComplete.message.notFound : defaultNotFoundMessage;
            }

            if (options.enabled === false) {
                this.disable();
            } else {
                this.enable();
            }

            this.setItems(options.items || []);
            this.setModifiers(options.modifiers || []);

            var elt = this.getElement();

            elt.addEventHandler('change', function () {
                this.trigger('change');
            }, this);

            elt.addEventHandler('focus', function () {
                this.trigger('focus');
            }, this);

            elt.addEventHandler('click', function (e) {
                this.getFocusableElement().focus();
                this.trigger('click');
                this.toggle();
            }, this);

            defineDefaultKeyBoardControls.call(this);

            this.onControlReady();

            // child class can override the keyboard controls during on onControlReady
            if (this.enabled !== false) {
                this.enableKeyboardControl();
                this._keyNavigationActive = true;
            }
        },

        /**
         * Returns the Class reference to ComponentList
         *
         * @method getComponentListClass
         * @protected
         * @return {ComponentList} ComponentListClass
         */
        getComponentListClass: function () {
            return ComponentList;
        },

        /**
         * Returns the element that get the focus
         *
         * @method getFocusableElement
         * @protected
         */
        getFocusableElement: function () {
            return this.getElement();
        },

        /**
         * Called when the focusable element receives input.
         *
         * @method onFocusableElementInput
         * @protected
         */
        onFocusableElementInput: function () {
        },

        /**
         * Called after view is ready.
         *
         * @method onControlReady
         * @protected
         */
        onControlReady: function () {
        },

        /**
         * Sets the width of the list. Accepts a CSS style.
         *
         * @method setWidth
         * @param {String} widthCSS
         */
        setWidth: function (widthCSS) {
            this.options.width = widthCSS;

            if (this.componentList) {
                this.componentList.options.width = widthCSS;
            }
        },

        /**
         * Sets the max height of the list. Accepts a CSS style.
         *
         * @method setHeight
         * @param {String} heightCSS
         */
        setHeight: function (heightCSS) {
            this.options.height = heightCSS;

            if (this.componentList) {
                this.componentList.options.height = heightCSS;
            }
        },

        /**
         * Sets items for the ItemsControl. If items array is empty then field is disabled.
         *
         * @method setItems
         * @param {Array} items
         */
        setItems: function (items) {
            this.options.items = items;
            // This is to prevent calling toLowerCase on all names on every keypress.
            if (this.options.autoComplete && this.options.autoComplete.caseInsensitive) {
                for (var i = 0; i < items.length; i++) {
                    if (items[i].name) {
                        items[i]._lowerCaseName = items[i].name.toLowerCase();
                    }
                    if (items[i].title) {
                        items[i]._lowerCaseTitle = items[i].title.toLowerCase();
                    }
                }
            }
            this.setDisplayedItems(items);
        },

        /**
         * Sets items that will be displayed in the ItemsControl. If items array is empty then field is disabled.
         *
         * @method setItems
         * @param {Array} items
         * @private
         */
        setDisplayedItems: function (items) {
            if (this.componentList) {
                this.componentList.destroy();
                delete this.componentList;
            }

            if (items.length > 0) {
                if (this.componentList === undefined) {
                    var Klass = this.getComponentListClass();
                    this.componentList = new Klass({
                        items: items,
                        parent: this.getElement(),
                        persistent: this.options.persistent,
                        width: this.options.width,
                        height: this.options.height,
                        selectDeselectAll: (this.options.selectDeselectAll === true),
                        animate: this.options.animate
                    });
                    this.componentList.addEventHandler('itemSelected', this.onComponentListClick, this);

                    if (this.enabled === false) {
                        this.disable();
                    } else {
                        this.enable();
                    }
                }
            } else {
                this.getElement().setModifier('disabled');
                this.onDisable();
            }

            this.items = items;
            this.onItemsSet();
        },

        /**
         * Called when the items have been set on the list. Override this.
         *
         * @method onItemsSet
         * @protected
         */
        onItemsSet: function () {
        },

        /**
         * Called when the component list shows itself. Override this.
         *
         * @method onListShow
         * @protected
         */
        onListShow: function () {
        },

        /**
         * Called when the component list hides itself. Override this.
         *
         * @method onListHide
         * @protected
         */
        onListHide: function () {
        },

        //-----------------------------------------------------------------
        //-----------------------------------------------------------------
        /**
         * Show the message info
         *
         * @method showMessageInfo
         * @protected
         */
        showMessageInfo: function (message) {
            if (this.componentList && this.enabled) {
                this.componentList.showMessageInfo(message);
                this._keyNavigationActive = false;
            }
        },

        /**
         * Hide the message info
         *
         * @method hideMessageInfo
         * @protected
         */
        hideMessageInfo: function () {
            if (this.componentList && this.enabled) {
                this.componentList.hideMessageInfo();
                this._keyNavigationActive = true;
            }
        },

        //-----------------------------------------------------------------
        //-----------------------------------------------------------------
        /**
         * Re render the list is showing, allows to adapt the position if needed
         *
         * @method reRenderListIfNeeded
         * @protected
         */
        reRenderListIfNeeded: function () {
            if (this.componentList && this.enabled && this.componentList.isVisible()) {
                this.componentList.show();
            }
        },

        /**
         * Show the component list manually.
         *
         * @method showList
         */
        showList: function () {
            if (this.componentList && this.enabled) {
                this.componentList.show();
            }
        },

        /**
         * Hide the component list manually.
         *
         * @method hideList
         */
        hideList: function () {
            if (this.componentList && this.enabled) {
                this.componentList.hide();
            }
        },

        /**
         * Toggles the component list manually.
         *
         * @method toggle
         */
        toggle: function () {
            if (this.componentList && this.enabled) {
                this.componentList.toggle();

                if (this.componentList.isShowing) {
                    this.onListShow();
                } else {
                    this.onListHide();
                }
            }
        },

        //-----------------------------------------------------------------
        //-----------------------------------------------------------------
        /**
         * Enables the ItemsControl.
         *
         * @method enable
         */
        enable: function () {
            this.enabled = true;
            this.getElement().removeModifier('disabled');
            this.getElement().addEventHandler('click', this.onParentClick, this);

            if (this._acInputEvtId === undefined && this.autoComplete.enabled === true) {
                this._acInputEvtId = this.getFocusableElement().addEventHandler('input', onInputAutoComplete, this);
            }

            this.onEnable();
        },

        /**
         * Called when the component list is enabled. Override this.
         *
         * @method onEnable
         * @protected
         */
        onEnable: function () {
        },


        /**
         * Disables the ItemsControl.
         *
         * @method disable
         */
        disable: function () {
            this.enabled = false;
            this.getElement().setModifier('disabled');

            if (this._acInputEvtId) {
                this.getFocusableElement().removeEventHandler(this._acInputEvtId);
                delete this._acInputEvtId;
            }

            this.onDisable();
        },

        /**
         * Called when the component list is disabled. Override this.
         *
         * @method onDisable
         * @protected
         */
        onDisable: function () {
        },

        //-----------------------------------------------------------------
        //-----------------------------------------------------------------
        /**
         * Enable keyboard control
         *
         * @method enableKeyboardControl
         * @protected
         */
        enableKeyboardControl: function () {
            this.keyboardControlEnabled = true;

            if (this._kbCtrlEvtId === undefined) {
                this._kbCtrlEvtId = keyboardUtils.addKeyHandler(this.getFocusableElement(), this.keyBoardControl);
            }
        },

        /**
         * Disable keyboard control
         *
         * @method disableKeyboardControl
         * @protected
         */
        disableKeyboardControl: function () {
            this.keyboardControlEnabled = false;

            keyboardUtils.removeKeyHandler(this.getFocusableElement(), this._kbCtrlEvtId);
            delete this._kbCtrlEvtId;
        },

        /**
         * Get keyboard control status
         *
         * @method isKeyboardControlled
         * @protected
         */
        isKeyboardControlled: function () {
            return this.keyboardControlEnabled;
        },

        //-----------------------------------------------------------------
        //-----------------------------------------------------------------

        /**
         * An event which is executed when clicked on the ItemsControl
         *
         * @method onItemsControlClick
         * @private
         */
        onParentClick: function () {
            this.getElement().trigger('focus');
        },

        /**
         * An event which is executed when a value is selected from the ComponentList
         *
         * @method onComponentListClick
         * @private
         */
        onComponentListClick: function () {
            var selectedVal = this.componentList.getSelectedValue();
            if (selectedVal) {
                this.onItemSelected(selectedVal);
                this.getFocusableElement().focus();
            }
        },

        /**
         * Called when an item on the component list is selected. Override this.
         *
         * @method onItemSelected
         * @protected
         * @param {Object} selectedValue
         */
        onItemSelected: function (selectedValue) {
        }
    });

    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------

    function defineDefaultKeyBoardControls() {
        /*jshint validthis:true*/
        this.keyBoardControl = {
            arrow_up: onArrowUp.bind(this),
            arrow_down: onArrowDown.bind(this),
            enter: onEnter.bind(this),
            tab: onTab.bind(this),
            escape: onEscape.bind(this)
        };
    }

    //-----------------------------------------------------------------
    function onInputAutoComplete(evt) {
        /*jshint validthis:true*/
        var value = this.getFocusableElement().getValue(),
            filterStr = cleanNewLineChar(value);

        if (value !== filterStr) {
            this.getFocusableElement().setValue(filterStr);
        }

        if (this.options.autoComplete && this.options.autoComplete.caseInsensitive) {
            filterStr = filterStr.toLowerCase();
        }
        this.onFocusableElementInput();

        var separatorAfterFirstItem = false,
            filteredItems = this.options.items.filter(function (item) {
                // if the filtered list starts with a separator, skip
                if (item.type === 'separator' && separatorAfterFirstItem) {
                    return true;
                } else {
                    var itemInFilterSelection;
                    if (this.options.autoComplete && this.options.autoComplete.caseInsensitive) {
                        itemInFilterSelection = (item._lowerCaseName && item._lowerCaseName.indexOf(filterStr) !== -1) || (item._lowerCaseTitle && item._lowerCaseTitle.indexOf(filterStr) !== -1);
                    } else {
                        itemInFilterSelection = (item.name && item.name.indexOf(filterStr) !== -1) || (item.title && item.title.indexOf(filterStr) !== -1);
                    }

                    if (itemInFilterSelection) {
                        separatorAfterFirstItem = true;
                    }

                    return itemInFilterSelection;
                }
            }.bind(this));

        if (filteredItems.length === 0) {
            this.showMessageInfo(this.autoComplete.message.notFound);
        } else {
            var lastItem = filteredItems[filteredItems.length - 1];
            if (lastItem.type === 'separator') {
                filteredItems.pop();
            }

            this.hideMessageInfo();
            this.setDisplayedItems(filteredItems);
        }
        this.showList();
    }

    function cleanNewLineChar(str) {
        return str.replace(/\r?\n/g, '');
    }

    //-----------------------------------------------------------------
    function onArrowDown(evt) {
        /*jshint validthis:true*/
        if (this._keyNavigationActive === true) {
            if (this.componentList.isVisible()) {
                this.componentList.focusNextItem();
            }
            else {
                this.showList();
            }
            evt.preventDefault();
        }
    }

    //-----------------------------------------------------------------
    function onArrowUp(evt) {
        /*jshint validthis:true*/
        if (this._keyNavigationActive === true) {
            if (this.componentList.isVisible()) {
                this.componentList.focusPreviousItem();
            }
            evt.preventDefault();
        }
    }

    //-----------------------------------------------------------------
    function onEnter(evt) {
        /*jshint validthis:true*/
        if (this._keyNavigationActive === true) {
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
        if (this._keyNavigationActive === true && this.componentList.isVisible()) {
            this.componentList.selectListItem();
        }
        this.hideList();
    }

    //-----------------------------------------------------------------
    function onEscape() {
        /*jshint validthis:true*/
        this.hideList();
    }

});

define('widgets/ItemsControl',['widgets/ItemsControl/ItemsControl'],function (main) {
                        return main;
                    });

