/* Copyright (c) Ericsson 2014 */

define('text!widgets/Button/_button.html',[],function () { return '<button class="ebBtn">\n    <span class="ebBtn-caption"></span>\n</button>';});

define('widgets/Button/ButtonView',['jscore/core','text!./_button.html'],function (core, template) {
    'use strict';

    var ButtonView = core.View.extend({

        init: function () {
            // used for the icon, icon depends on button size
            this.size = 'medium';
        },

        setSize: function (size) {
            if (size) {
                this.size = size;
                var icnElt = this.getIcon();
                if (icnElt && this.size !== 'medium') {
                    icnElt.setModifier(this.size);
                }
            }
        },

        getTemplate: function () {
            return template;
        },

        getCaption: function () {
            return this.getElement().find('.' + ButtonView.BTN_CAPTION);
        },

        getIcon: function () {
            return this.getElement().find('.' + ButtonView.EB_ICON);
        },

        setIcon: function (options) {
            var iconElt = this.getIcon();
            var caption = this.getCaption();

            if (options) {
                if (!iconElt) {
                    iconElt = core.Element.parse('<i></i>');
                    iconElt.setAttribute('class', ButtonView.EB_ICON);

                    var position = options.position;
                    var captionText = caption.getText().trim();

                    if (position && position === 'left') {
                        caption.detach(); // no prepend in JSCore
                        caption.setText(' ' + captionText);
                        this.getElement().append(iconElt);
                        this.getElement().append(caption);
                    } else {
                        this.getElement().append(iconElt);
                        caption.setText(captionText + ' ');
                    }
                }

                iconElt.setModifier(options.name);
                this.setSize(this.size);
            } else {
                // remove the icon elt if options is null
                if (iconElt) {
                    iconElt.remove();
                }
            }
        }

    }, {
        BTN_CAPTION: 'ebBtn-caption',
        EB_ICON: 'ebIcon'
    });
    return ButtonView;
});

define('widgets/Button/Button',['widgets/WidgetCore','./ButtonView'],function (WidgetCore, View) {
    'use strict';

    /**
     * The Button class wraps the Ericsson brand assets button in a Widget.<br>
     * The Button can be instantiated using the constructor Button.
     *
     * <strong>Events:</strong>
     * <ul>
     *     <li>click - Triggers when the button has been clicked.</li>
     * </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>caption: a string used as a button caption.</li>
     *       <li>enabled: boolean indicating whether button should be enabled. Default is true.</li>
     *       <li>type: button|submit|reset, button is default.</li>
     *       <li>modifiers: an array used to define modifiers for the Button. (Asset Library)
     *          <a href="#modifierAvailableList">see available modifiers</a>
     *          <br>E.g: modifiers:[{name: 'foo'}, {name: 'bar', value:'barVal'}]
     *       </li>
     *       <li>type: a string used as a button type attribute. Default is 'button'.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *   <ul>
     *      <li>"disabled" - disabled (equiv DOM attribute disabled) (Asset Library)</li>
     *      <li>Width modifiers:<br/>'small', 'large'<br/>E.g: modifiers:[{name: 'large'}]</li>
     *      <li>Color modifiers:<br/>'color_darkGreen', 'color_green', 'color_orange', 'color_red', 'color_purple', 'color_paleBlue'<br/>E.g: modifiers:[{name: 'color', value:'green'}]<br/>(List of colors defined in the Asset Library)</li>
     *   </ul>
     *
     * @class Button
     * @extends WidgetCore
     * @baselined
     */
    return WidgetCore.extend({

        View: View,

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.setCaption(this.options.caption || 'Button');
            this.setModifiers(this.options.modifiers || []);
            this.setType(this.options.type || 'button');

            if (this.options.enabled === false) {
                this.disable();
            }

            if (this.options.icon){
                this.setIcon(this.options.icon);
            }

            this.getElement().addEventHandler('click', function () {
                this.trigger('click');
            }, this);
        },

        /**
         * Sets caption for the button.
         *
         * @method setCaption
         * @param {String} caption
         */
        setCaption: function (caption) {
            this.view.getCaption().setText(caption);
        },

        /**
         * Sets icon for the button.<br>
         * position: left, right (default)<br>
         * name: icon name<br>
         *
         * @method setIcon
         * @param {object} icon null to remove
         * @example
         *   button.setIcon({
         *       position: 'left',
         *       name: 'refresh'
         *   })
         *
         */
        setIcon: function (icon) {
            this.view.setIcon(icon);
        },

        /**
         * Sets 'type' attribute for the button.<br>
         * types: submit, button (default), reset
         *
         * @method setType
         * @param type
         * @example
         *   button.setType('submit');
         */
        setType: function (type) {
            this.getElement().setAttribute('type', type);
        },

        /**
         * Enables the button.
         *
         * @method enable
         */
        enable: function () {
            this.getElement().setAttribute('disabled', null);
            this.removeModifier('disabled');
        },

        /**
         * Disables the button.
         *
         * @method disable
         */
        disable: function () {
            this.getElement().setAttribute('disabled', 'disabled');
            this.setModifier('disabled');
        }

    });

});

define('widgets/Button',['widgets/Button/Button'],function (main) {
                        return main;
                    });

