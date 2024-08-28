/* Copyright (c) Ericsson 2014 */

define('text!widgets/ContextMenu/_contextMenu.html',[],function () { return '<div class="ebContextMenu">\n    <div class="ebContextMenu-expandBtn" tabindex="1">\n        <i class="ebIcon ebIcon_medium ebIcon_menu ebIcon_interactive"></i>\n    </div>\n    <div class="ebContextMenu-body ebContextMenu-body_corner_default"></div>\n</div>';});

define('widgets/ContextMenu/ContextMenuView',['jscore/core','text!./_contextMenu.html'],function (core, template) {
    'use strict';

    var ContextMenuView = core.View.extend({
        /*jshint validthis:true*/

        // TODO: Should be added to core.View and executed after render()
        afterRender: function () {
            this.outerEl = this.getElement().find('.' + ContextMenuView.OUTER_CLASS);
            this.expandBtn = this.getElement().find('.' + ContextMenuView.EXPAND_BTN_CLASS);
            this.dropdown = this.getElement().find('.' + ContextMenuView.DROPDOWN_CLASS);
            this.expandBtnIcon = this.expandBtn.find('.' + ContextMenuView.EXPAND_BTN_ICON_CLASS);
        },

        getTemplate: function() {
            return template;
        },

        setTitle: function(caption) {
            this.outerEl.setText(caption);
            this.outerEl.setAttribute('title', caption);
        },

        getTitle: function() {
            return this.outerEl.getText();
        },

        getOuterEl: function() {
            return this.outerEl;
        },

        getExpandBtn: function() {
            return this.expandBtn;
        },

        getExpandBtnIcon: function() {
            return this.expandBtnIcon;
        },

        getDropdown: function() {
            return this.dropdown;
        },

        showDropdown: function(visible) {
            this.dropdown.setModifier('visible', '' + visible);
        },

        enableMenu: function(enable) {
            if (enable) {
                this.getExpandBtnIcon().removeModifier('disabled');
                this.getExpandBtnIcon().setModifier('interactive');
            } else {
                this.getExpandBtnIcon().removeModifier('interactive');
                this.getExpandBtnIcon().setModifier('disabled');
            }
        }

    }, {
        OUTER_CLASS: 'ebContextMenu',
        EXPAND_BTN_CLASS: 'ebContextMenu-expandBtn',
        EXPAND_BTN_ICON_CLASS: 'ebIcon',
        DROPDOWN_CLASS: 'ebContextMenu-body'
    });

    return ContextMenuView;
});

define('widgets/ContextMenu/ContextMenu',['widgets/ItemsControl','widgets/Tooltip','./ContextMenuView'],function (ItemsControl, Tooltip, View) {
    'use strict';

    /**
     * The ContextMenu class uses the Ericsson brand assets.<br>
     * The ContextMenu can be instantiated using the constructor ContextMenu.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>ContextMenu(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>change: this event is triggered when value is changed in the ContextMenu</li>
     *     <li>focus: this event is triggered when the ContextMenu is focused</li>
     *     <li>click: this event is triggered when user clicks on the ContextMenu</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>enabled: boolean indicating whether ContextMenu should be enabled. Default is true.</li>
     *       <li>corner: String that specifies which side of the icon the dropdown is aligned with.</li>
     *          <br>Possible values are default, topRight, bottomLeft and bottomRight.</li>
     *       <li>persistent: boolean indicating whether the ContextMenu should be persistent (ie will remain open if it loses focus). Default is false.</li>
     *       <li>actions: an object used as a list of available actions (<strong>Deprecated</strong>)<br>
     *         e.g. actions: <b>{</b> 'Message': function(){alert('Foo')} <b>}</b></li>
     *       <li>items: an array used as a list of available items in the Combobox
     *         e.g. items: <b>[</b> name: 'Message', action: function(){alert('Foo')} <b>]</b></li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class ContextMenu
     * @extends ItemsControl
     */
    return ItemsControl.extend({
        /*jshint validthis:true*/

        View: View,

        /**
         *   Sets the title, corner and items of the list
         */
        onControlReady: function () {
            if (this.options.title) {
                this.title = this.options.title;
                this.setTitle(this.title);
            }

            this.corner = this.options.corner || 'default';
            this.options.width = 'auto';

            this.options.items = this.options.items || [];

            if (this.options.actions) {
                for (var action in this.options.actions) {
                    if (this.options.actions.hasOwnProperty(action)) {
                        this.options.items.push({name: action, action: this.options.actions[action]});
                    }
                }
            }

            this.setItems(this.options.items || []);
        },

        /**
         *   Triggered when the component list displays
         */
        onListShow: function () {
            this.setCorner(this.corner);
        },

        /**
         * This method enables the ContextMenu
         */
        onEnable: function () {
            this.view.enableMenu(true);
        },

        /**
         * This method disables the ContextMenu
         */
        onDisable: function () {
            this.view.enableMenu(false);
        },


        /**
         * This method sets the position of the dropdown for the ContextMenu. Four values are possible: 'default', 'topRight', 'bottomLeft' and 'bottomRight'.
         * If 'default' is specified, the dropdown will be aligned with the left side of the icon.
         *
         * @method setCorner
         * @param {String} corner
         */
        setCorner: function (corner) {
            this.view.getDropdown().setModifier('corner', corner);

            if (this.componentList) {
                var top = 0;
                var left = 0;

                if (corner === 'bottomLeft' || corner === 'bottomRight') {
                    top = -this.componentList.getElement().getProperty('offsetHeight') - this.getElement().getProperty('offsetHeight') - 12;
                }

                if (corner === 'bottomRight' || corner === 'topRight') {
                    left = -this.componentList.getElement().getProperty('offsetWidth') + this.getElement().getProperty('offsetWidth');
                }

                this.componentList.setPositionOffsets({
                    top: top,
                    left: left
                });
            }
        },

        /**
         * This method sets the tooltip that will be displayed when the user hovers over the menu icon.<br>
         * If empty text is put as parameter, than exists tooltip will be destroyed.
         *
         * @method setTitle
         * @param {String} title
         */
        setTitle: function (title) {
            this.title = title;
            if (!this.tooltip) {
                this.tooltip = new Tooltip({
                    parent: this.view.getExpandBtn(),
                    contentText: this.title,
                    enabled: true
                });
                this.tooltip.attachTo(this.getElement());
            } else {
                this.tooltip.setContentText(this.title);
            }

            if (title === '' && this.tooltip) {
                this.tooltip.destroy();
                delete this.tooltip;
            }
        },

        /**
         * This method returns the string that is displayed on the menu icon tooltip.
         *
         * @method getTitle
         */
        getTitle: function () {
            if (this.tooltip) {
                return this.title;
            }
            return '';
        },

        /**
         * An event which is executed when an action is selected from componentList
         */
        onItemSelected: function (selectedVal) {
            selectedVal.action();
        },

        /**
         * Sets actions for the contextMenu. If actions object is empty then ContextMenu is disabled.<br>
         * For example syntax consult the actions option in the init method.
         *
         * @method setActions
         * @param {Object} actions
         * @deprecated
         */
        setActions: function (actions) {
            var keys = Object.keys(actions);
            var items = [];

            keys.forEach(function (key) {
                var itemObj = actions[key];
                items.push({
                    name: key,
                    action: itemObj
                });
            }, this);
            this.setItems(items);
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

define('widgets/ContextMenu',['widgets/ContextMenu/ContextMenu'],function (main) {
                        return main;
                    });

