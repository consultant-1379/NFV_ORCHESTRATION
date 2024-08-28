/* Copyright (c) Ericsson 2014 */

define('text!widgets/Combobox/_combobox.html',[],function () { return '<div class="ebCombobox">\n    <input type="text" class="ebInput ebInput_noRightSide"/>\n    <button type="button" class="ebCombobox-helper">\n        <span class="ebCombobox-iconHolder">\n            <i class="ebIcon ebIcon_small ebIcon_downArrow_10px eb_noVertAlign"></i>\n        </span>\n    </button>\n</div>';});

define('widgets/Combobox/ComboboxView',['jscore/core','text!./_combobox.html'],function (core, template) {
    'use strict';

    var ComboboxView = core.View.extend({

        afterRender: function () {
            this.input = this.getElement().find('.' + ComboboxView.EL_INPUT);
            this.helper = this.getElement().find('.' + ComboboxView.EL_HELPER);
        },

        getTemplate: function () {
            return template;
        },

        getRoot: function () {
            return this.getElement();
        },

        getInput: function () {
            return this.input;
        },

        getHelper: function () {
            return this.helper;
        }

    }, {
        'EL_INPUT': 'ebInput',
        'EL_HELPER': 'ebCombobox-helper'
    });

    return ComboboxView;

});

define('widgets/Combobox/Combobox',['widgets/ItemsControl','./ComboboxView'],function (ItemsControl, View) {
    'use strict';

    /**
     * The Combobox class uses the Ericsson brand assets.<br>
     * The Combobox can be instantiated using the constructor Combobox.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>Combobox(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>change: this event is triggered when value is changed in the Combobox</li>
     *     <li>focus: this event is triggered when the Combobox is focused</li>
     *     <li>click: this event is triggered when user clicks on the Combobox button</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>placeholder: a string used as a default name of the Combobox</li>
     *       <li>value: an object used as a selected item of the Combobox</li>
     *       <li>items: an array used as a list of available items in the Combobox</li>
     *       <li>enabled: boolean indicating whether the Combobox should be enabled. Default is true.</li>
     *       <li>modifiers: an array used to define modifiers for the Combobox.  (Asset Library)
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
     *              <li>caseInsensitive: (optional) boolean enabling case-insensitivity when using auto-complete. default false.</li>
     *          </ul>
     *       </li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>disabled: disabled (Asset Library)</li>
     *  </ul>
     *
     * @class Combobox
     * @extends ItemsControl
     */
    return ItemsControl.extend({
        /*jshint validthis:true */

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
            this.selectedItem = {};
        },

        /**
         * Overrides method from ItemControl.<br>
         * Executes every time, when added back to the screen.
         */
        onControlReady: function () {
            this.setValue(this.options.value || {});
            this.setPlaceholder(this.options.placeholder || '');
        },

        /**
         * Sets value for the Combobox.
         *
         * @method setValue
         * @param {Object} value
         */
        setValue: function (value) {
            var valueName = '';
            if (value !== {}) {
                valueName = value.name;
            }
            this.selectedItem = value;
            this.view.getInput().setValue(valueName);
        },

        /**
         * Returns selected item object from the Combobox.
         *
         * @method getValue
         * @return {Object} value
         */
        getValue: function () {
            var inputValue = this.view.getInput().getValue();
            for (var i = 0; i < this.items.length; i++) {
                var item = this.items[i];
                if (item.name === inputValue || item.value === inputValue) {
                    return item;
                }
            }

            return {
                name: inputValue,
                value: inputValue
            };
        },

        /**
         * Sets placeholder for the Combobox.
         *
         * @method setPlaceholder
         * @param {String} placeholder
         */
        setPlaceholder: function (placeholder) {
            this.view.getInput().setAttribute('placeholder', placeholder);
        },

        /**
         * Sets input field width for the Combobox.
         *
         * @method setInputSize
         * @param {string} wModifier Can be selected from available sizes: ['mini', 'small', 'long', 'xLong', 'full']<br>
         *     null to reset to default<br>
         *     ['miniW', 'smallW', 'longW', 'xLongW'] - deprecated names for input field sizes
         */
        setInputSize: function (wModifier) {
            // TODO: should be removed
            if (['miniW', 'smallW', 'longW', 'xLongW'].indexOf(wModifier) > -1) {
                wModifier = wModifier.replace('W', '');
            }

            // check if the modifier is a size modifier
            var possible = ['mini', 'small', 'long', 'xLong', 'full'];

            if (possible.indexOf(wModifier) !== -1) {
                this.view.getInput().setModifier('width', wModifier);
                if (wModifier === 'full') {
                    this.getElement().setModifier('width', 'full');
                }
                this.currentInputSize = wModifier;
            } else if (this.currentInputSize) {
                this.view.getInput().removeModifier('width');
                this.getElement().removeModifier('width');
                this.currentInputSize = '';
            }
        },

        /**
         * A method which is called when enable() method is called.
         */
        onEnable: function () {
            this.view.getInput().setAttribute('disabled', false);
            this.view.getHelper().setAttribute('disabled', false);
        },

        /**
         * A method which is called when disable() method is called.
         */
        onDisable: function () {
            this.view.getInput().setAttribute('disabled', true);
            this.view.getHelper().setAttribute('disabled', true);
        },

        /**
         * A method which is called when an item is selected.
         *
         * @param {Object} selectedVal
         */
        onItemSelected: function (selectedVal) {
            if (selectedVal !== this.getValue()) {
                this.setValue(selectedVal);
                this.view.getInput().trigger('change');
            }
        },
        /**
         * Returns the element that get the focus
         */
        getFocusableElement: function () {
            return this.view.getInput();
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

});

define('widgets/Combobox',['widgets/Combobox/Combobox'],function (main) {
                        return main;
                    });

