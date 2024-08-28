/* Copyright (c) Ericsson 2014 */

define('text!widgets/SelectBox/_selectBox.html',[],function () { return '<div class="ebSelect" tabindex="0">\n    <button type="button" class="ebSelect-header">\n        <span class="ebSelect-value"></span>\n        <span class="ebSelect-iconHolder"><i class="ebIcon ebIcon_small ebIcon_downArrow_10px"></i></span>\n    </button>\n</div>';});

define('widgets/SelectBox/SelectBoxView',['jscore/core','text!./_selectBox.html'],function (core, template) {
    'use strict';

    var SelectBoxView = core.View.extend({

        // TODO: Should be added to core.View and executed after render()
        afterRender: function () {
            this.value = this.getElement().find('.' + SelectBoxView.EL_VALUE);
            this.button = this.getElement().find('.' + SelectBoxView.EL_HEADER);
        },

        getTemplate: function () {
            return template;
        },

        getRoot: function () {
            return this.getElement();
        },

        getValueEl: function () {
            return this.value;
        },

        getButton: function () {
            return this.button;
        }

    }, {
        'EL_VALUE': 'ebSelect-value',
        'EL_HEADER': 'ebSelect-header'
    });

    return SelectBoxView;

});

define('widgets/SelectBox/SelectBox',['widgets/ItemsControl','./SelectBoxView'],function (ItemsControl, View) {
    'use strict';

    /**
     * The SelectBox class uses the Ericsson brand assets.<br>
     * The SelectBox can be instantiated using the constructor SelectBox.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>SelectBox(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>change: this event is triggered when value is changed in the SelectBox</li>
     *     <li>focus: this event is triggered when the SelectBox is focused</li>
     *     <li>click: this event is triggered when user clicks on the SelectBox</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>value: an object used as a selected item of the SelectBox</li>
     *       <li>items: an array of objects used as a list of available items in the SelectBox. Accepts the following properties:
     *          <ul>
     *              <li>name: the text for the item that appears in the dropdown. Must be defined.</li>
     *              <li>value: the real value for the item, usually sent to server. Note that this is used for comparing against objects and must be defined.</li>
     *              <li>title: optional parameter. The text to display in the native tooltip when hovering over the item.</li>
     *          </ul>
     *       </li>
     *       <li>enabled: boolean indicating whether the SelectBox should be enabled. Default is true.</li>
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
     *      <li>width: [mini, small, long, xLong, full] (Asset Library)</li>
     *  </ul>
	 *
	 * @example
     * var selectBoxWidget = new SelectBox({
     *      value: {name: 'Name 1', value: 1, title: 'Title 1'},
     *      items: [
     *          {name: 'Name 1', value: 1, title: 'Title 1'},
     *          {type: 'separator'},
     *          {name: 'Name 2', value: 2}
     *       ]
     * });
     *
     * @class SelectBox
     * @extends ItemsControl
     */
    return ItemsControl.extend({
        /*jshint validthis:true*/

        View: View,

        /**
         * Overrides method from ItemControl.<br>
         * Executes every time, when added back to the screen.
         */
        onControlReady: function () {
            this.setValue(this.options.value || {});
        },

        /**
         * A method which is called when enable() method is called.
         */
        onEnable: function() {
            this.view.getButton().setAttribute('disabled', false);
        },

        /**
         * A method which is called when disable() method is called.
         */
        onDisable: function() {
            this.view.getButton().setAttribute('disabled', true);
        },

        /**
         * Sets value for the SelectBox.
         *
         * @method setValue
         * @param {Object} value
         * @example
         * selectBox.setValue({name: '123', value: 123, title: 'Title for 123'});
         */
        setValue: function (value) {
            var valueName = '';
            if (value !== {}) {
                valueName = value.name;
            }
            this.selectedItem = value;
            this.view.getValueEl().setText(valueName);
        },

        /**
         * Returns selected item object from the SelectBox.
         *
         * @method getValue
         * @return {Object} value
         */
        getValue: function () {
            return this.selectedItem;
        },

        /**
         * A method which is called when an item is selected.
         *
         * @param {Object} selectedVal
         */
        onItemSelected: function (selectedVal) {
            if (selectedVal.value !== undefined) {
                if (selectedVal.value !== this.getValue().value) {
                    this.setValue(selectedVal);
                    this.getElement().trigger('change');
                }
            } else if (selectedVal.name !== undefined) {
                if (selectedVal.name !== this.getValue().name) {
                    this.setValue(selectedVal);
                    this.getElement().trigger('change');
                }
            }
        },

        /**
         * Sets the width for the box (not the list).
         *
         * @method setBoxSize
         * @param {string} wModifier Can be selected from available sizes: ['mini', 'small', 'long', 'xLong', 'full']<br>
         *     null to reset to default<br>
         *     ['miniW', 'smallW', 'longW', 'xLongW'] - deprecated names for input field sizes
         */
        setBoxSize: function (wModifier) {
            // TODO: should be removed
            if (['miniW', 'smallW', 'longW', 'xLongW'].indexOf(wModifier) > -1) {
                wModifier = wModifier.replace('W', '');
            }

            // check if the modifier is a size modifier
            var possible = ['mini', 'small', 'long', 'xLong', 'full'];

            if (possible.indexOf(wModifier) !== -1) {
                this.view.getRoot().setModifier('width', wModifier);
                if (wModifier === 'full') {
                    this.getElement().setModifier('width', 'full');
                }
                this.currentInputSize = wModifier;
            } else if (this.currentInputSize) {
                this.view.getRoot().removeModifier('width');
                this.getElement().removeModifier('width');
                this.currentInputSize = '';
            }
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

define('widgets/SelectBox',['widgets/SelectBox/SelectBox'],function (main) {
                        return main;
                    });

