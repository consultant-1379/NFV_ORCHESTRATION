/* Copyright (c) Ericsson 2014 */

define('styles!widgets/SystemBar/SystemBarButton/_systemBarButton.less',[],function () { return '.elWidgets-SystemBarButton {\n  float: right;\n  display: inline-block;\n  line-height: 40px;\n  vertical-align: middle;\n}\n.elWidgets-SystemBarButton-link {\n  color: #0066B3;\n  font-size: 1.2rem;\n  padding: 0 18px 0 40px;\n  background-color: transparent;\n  background-repeat: no-repeat;\n  background-position: 18px center;\n  border-left: solid 1px #D2D2D2;\n  display: block;\n  text-decoration: none;\n  cursor: pointer;\n}\n.elWidgets-SystemBarButton-link:hover {\n  text-decoration: underline;\n}\n.elWidgets-SystemBarButton:hover {\n  background: #e8e8e8;\n  background: -webkit-gradient(linear, left top, left bottom, color-stop(0, #f2f2f2), color-stop(1, #e8e8e8));\n  background: -ms-linear-gradient(top, #f2f2f2, #e8e8e8);\n  background: -o-linear-gradient(center top, #f2f2f2 0%, #e8e8e8 100%);\n  background: -webkit-linear-gradient(center top, #f2f2f2 0%, #e8e8e8 100%);\n  background: -moz-linear-gradient(center top, #f2f2f2 0%, #e8e8e8 100%);\n  background: linear-gradient(center top, #f2f2f2 0%, #e8e8e8 100%);\n}\n@media screen and (max-width: 640px) {\n  .elWidgets-SystemBarButton-link {\n    padding-left: 34px;\n    text-indent: -9999px;\n    width: 0;\n  }\n}\n';});

define('text!widgets/SystemBar/SystemBarButton/_systemBarButton.html',[],function () { return '<div class="elWidgets-SystemBarButton">\n    <a class="elWidgets-SystemBarButton-link"></a>\n</div>\n';});

define('widgets/SystemBar/SystemBarButton/SystemBarButtonView',['jscore/core','text!./_systemBarButton.html','styles!./_systemBarButton.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return styles;
        },

        getLink: function () {
            return this.getElement().find('.elWidgets-SystemBarButton-link');
        }

    });

});

define('widgets/SystemBar/SystemBarButton/SystemBarButton',['widgets/WidgetCore','./SystemBarButtonView'],function (WidgetCore, View) {
    'use strict';

    return WidgetCore.extend({

        View: View,

        onViewReady: function (options) {
            this.options = options;
            this.setCaption(this.options.caption || 'Default');
            if (this.options.image) {
                this.setImage(this.options.image);
            }

            if (this.options.url) {
                this.setUrl(this.options.url);
            }

            if (this.options.action) {
                this.addEventHandler('click', this.options.action);
            }

            this.view.getLink().addEventHandler('click', function (e) {
                this.trigger('click', e);
            }, this);
        },

        setCaption: function (text) {
            this.view.getLink().setText(text);
        },

        setImage: function (pathToImg) {
            this.view.getLink().setStyle('background-image', 'url("' + pathToImg + '")');
        },

        setUrl: function (url) {
            this.view.getLink().setAttribute('href', url);
        }
    });
});

define('styles!widgets/SystemBar/_systemBar.less',[],function () { return '.elWidgets-SystemBar {\n  font-family: Arial, Helvetica, sans-serif;\n  font-size: 1.2rem;\n  position: relative;\n  width: 100%;\n  height: 40px;\n  z-index: 100;\n  border-top: 4px solid #0066b3;\n  background: #f0f0f0;\n  background: -webkit-gradient(linear, left top, left bottom, color-stop(0, #ffffff), color-stop(1, #f0f0f0));\n  background: -ms-linear-gradient(top, #ffffff, #f0f0f0);\n  background: -o-linear-gradient(center top, #ffffff 0%, #f0f0f0 100%);\n  background: -webkit-linear-gradient(center top, #ffffff 0%, #f0f0f0 100%);\n  background: -moz-linear-gradient(center top, #ffffff 0%, #f0f0f0 100%);\n  background: linear-gradient(center top, #ffffff 0%, #f0f0f0 100%);\n  -webkit-box-shadow: 0 2px 2px #D2D2D2;\n  -moz-box-shadow: 0 2px 2px #D2D2D2;\n  box-shadow: 0 2px 2px #D2D2D2;\n}\n.elWidgets-SystemBar::before {\n  content: "";\n  position: absolute;\n  width: 100%;\n  height: 4px;\n  font-size: 0;\n  top: -4px;\n  left: 0;\n  background: #0066b3;\n  background: -o-linear-gradient(left, #a2c517 10%, #009046 30%, #0082b6 50%, #151f77 70%, #db0050 100%);\n  background: -moz-linear-gradient(left, #a2c517 10%, #009046 30%, #0082b6 50%, #151f77 70%, #db0050 100%);\n  background: -webkit-linear-gradient(left, #a2c517 10%, #009046 30%, #0082b6 50%, #151f77 70%, #db0050 100%);\n  background: -ms-linear-gradient(left, #a2c517 10%, #009046 30%, #0082b6 50%, #151f77 70%, #db0050 100%);\n  background: -webkit-gradient(linear, left top, right top, color-stop(0.1, #a2c517), color-stop(0.3, #009046), color-stop(0.5, #0082b6), color-stop(0.7, #151f77), color-stop(1, #db0050));\n  background: linear-gradient(left, #a2c517 10%, #009046 30%, #0082b6 50%, #151f77 70%, #db0050 100%);\n}\n.elWidgets-SystemBar-logo {\n  position: absolute;\n  top: 0;\n  bottom: 0;\n  left: 0;\n  background: url(\'widgets/resources/widgets/SystemBar/econ01.svg\') no-repeat center center transparent;\n  width: 50px;\n  background-size: 20px 20px;\n}\n.elWidgets-SystemBar-name {\n  display: inline-block;\n  padding: 0 5px 0 45px;\n  margin: 0;\n  font-size: 1.4em;\n  line-height: 40px;\n  color: #58585A;\n}\n.elWidgets-SystemBar-topMenu {\n  position: absolute;\n  top: 0;\n  bottom: 0;\n  left: 50px;\n  list-style: none;\n  height: 40px;\n  margin: 0;\n  padding: 0;\n}\n';});

define('text!widgets/SystemBar/_systemBar.html',[],function () { return '<div class="elWidgets-SystemBar">\n    <div class="elWidgets-SystemBar-logo"></div>\n    <div class="elWidgets-SystemBar-name">OSS</div>\n</div>\n';});

define('widgets/SystemBar/SystemBarView',['jscore/core','text!./_systemBar.html','styles!./_systemBar.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        },

        setName: function(name) {
            this.getElement().find('.elWidgets-SystemBar-name').setText(name);
        }

    });

});

define('widgets/SystemBar/SystemBar',['widgets/WidgetCore','./SystemBarView','./SystemBarButton/SystemBarButton'],function (WidgetCore, View, SystemBarButton) {
    'use strict';

    /**
     * System Bar is fixed-height widget containing econ logo, configurable
     * system name and Ericsson branded colored stripe.
     * Please note that if your application is supposed to run within JSCore
     * Container, you shouldn't use the SystemBar, as it is provided by the
     * container. Otherwise, include it at the top of a web page. As it should
     * always be on the screen, make sure it doesn't scroll with the rest of
     * the page.
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @private
     * @class SystemBar
     * @extends WidgetCore
     * @baselined
     */
    return WidgetCore.extend({

        View: View,

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         * The following options are accepted:
         *   <ul>
         *       <li>name: a string used as a system name. Defaults to 'OSS'.</li>
         *   </ul>
         *
         * @method init
         * @param {Object} options
         */
        init: function (options) {
            this.config = options;
        },

        onViewReady: function () {
            if (this.config && this.config.name) {
                this.view.setName(this.config.name);
            }
        },

        /**
         * Adds a new button to the system bar. The button will appear on the right
         * side of the system bar. Returns the button created.
         *
         * The following options are accepted:
         *   <ul>
         *       <li>caption: a string used as a button caption.</li>
         *       <li>image: an image to be shown on the left side of the button.</li>
         *       <li>action: a function to be called when the button is clicked. N/A if url is used.</li>
         *       <li>url: URL where the button should point to. N/A if action is used.</li>
         *   </ul>
         *
         * @method addButton
         * @param {Object} options
         * @return {Object} button
         */
        addButton: function (options) {
            var button = new SystemBarButton(options);
            button.attachTo(this.getElement());
            return button;
        },

        /**
         * Sets system name to be displayed in the system bar.
         *
         * @method setName
         * @param {String} name
         */
        setName: function (name) {
            this.view.setName(name);
        }

    });
});

define('widgets/SystemBar',['widgets/SystemBar/SystemBar'],function (main) {
                        return main;
                    });

