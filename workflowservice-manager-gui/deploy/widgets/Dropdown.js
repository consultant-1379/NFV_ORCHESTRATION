/* Copyright (c) Ericsson 2014 */

define('text!widgets/Dropdown/_dropdown.html',[],function () { return '<div class="ebDropdown">\n    <button type="button" class="ebDropdown-header">\n        <span class="ebDropdown-caption">Actions</span>\n        <span class="ebDropdown-iconHolder"><i class="ebIcon ebIcon_small ebIcon_downArrow_10px"></i></span>\n    </button>\n</div>';});

define('widgets/Dropdown/DropdownView',['jscore/core','text!./_dropdown.html'],function (core, template) {
    'use strict';

    var DropdownView = core.View.extend({

        afterRender: function () {
            this.caption = this.getElement().find('.' + DropdownView.EL_CAPTION);
            this.button = this.getElement().find('.' + DropdownView.EL_HEADER);
        },

        getTemplate: function () {
            return template;
        },

        getRoot: function () {
            return this.getElement();
        },

        getCaption: function () {
            return this.caption;
        },

        getButton: function () {
            return this.button;
        }

    }, {
        'EL_CAPTION': 'ebDropdown-caption',
        'EL_HEADER': 'ebDropdown-header'
    });

    return DropdownView;
});

define('widgets/Dropdown/Dropdown',['widgets/ItemsControl','./DropdownView'],function (ItemsControl, View) {
    'use strict';

    /**
     * The Dropdown class uses the Ericsson brand assets.<br>
     * The Dropdown can be instantiated using the constructor Dropdown.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>Dropdown(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>change: this event is triggered when value is changed in the Dropdown</li>
     *     <li>focus: this event is triggered when the Dropdown is focused</li>
     *     <li>click: this event is triggered when user clicks on the Dropdown</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>caption: a string used as a dropdown caption</li>
     *       <li>items: an array used as a list of actions in the Dropdown</li>
     *       <li>enabled: boolean indicating whether dropdown should be enabled. Default is true.</li>
     *       <li>modifiers: an array used to define modifiers for the Dropdown.
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
     * @class Dropdown
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
         */
        init: function () {
            this.options.width = 'auto';
        },

        /**
         * Overrides method from ItemControl.<br>
         * Executes every time, when added back to the screen.
         */
        onControlReady: function () {
            this.setCaption(this.options.caption || '');
        },

        /**
         * Sets caption for the Dropdown.
         *
         * @method setCaption
         * @param {String} caption
         */
        setCaption: function (caption) {
            this.view.getCaption().setText(caption);
        },

        /**
         * A method which is called when enable() method is called.
         */
        onEnable: function () {
            this.view.getButton().setAttribute('disabled', false);
        },

        /**
         * A method which is called when disable() method is called.
         */
        onDisable: function () {
            this.view.getButton().setAttribute('disabled', true);
        },

        /**
         * A method which is called when an item is selected
         *
         * @param {Object} selectedVal
         */
        onItemSelected: function (selectedVal) {
            selectedVal.action();
            this.getElement().trigger('change');
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

define('widgets/Dropdown',['widgets/Dropdown/Dropdown'],function (main) {
                        return main;
                    });

