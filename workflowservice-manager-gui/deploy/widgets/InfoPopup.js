/* Copyright (c) Ericsson 2014 */

define('text!widgets/InfoPopup/_infoPopup.html',[],function () { return '<div class="ebInfoPopup" tabindex="1">\n\t<div class="ebInfoPopup-infoIcon"><i class="ebIcon ebIcon_interactive ebIcon_medium"></i></div>\n\t<div class="ebInfoPopup-content">\n        <div class="ebInfoPopup-closeButton"><i class="ebIcon ebIcon_close ebIcon_interactive"></i></div>\n\t\t<div class="ebInfoPopup-contentText"></div>\n\t\t<div class="ebInfoPopup-arrowShadow"></div>\n\t\t<div class="ebInfoPopup-arrowBorder"></div>\n\t</div>\n</div>\n';});

define('widgets/InfoPopup/InfoPopupView',['jscore/core','text!./_infoPopup.html'],function (core, template) {
    'use strict';

    var InfoPopupView = core.View.extend({

        afterRender: function() {
            this._contentText = this.getElement().find('.ebInfoPopup-contentText');
            this._content = this.getElement().find('.ebInfoPopup-content');
            this._infoIcon = this.getElement().find('.ebIcon');
            this._closeButton = this.getElement().find('.ebInfoPopup-closeButton');
        },

        getTemplate: function () {
            return template;
        },

        getContentText: function() {
            return this._contentText;
        },

        getContent: function() {
            return this._content;
        },

        getInfoIcon: function() {
            return this._infoIcon;
        },

        getCloseButton: function() {
            return this._closeButton;
        },

        setContentText: function(content) {
            this.getContentText().children().forEach(function(child) {
                child.detach();
            });

            this.getContentText().append(core.Element.parse('<span>'+content+'</span>'));
        }

    });

    return InfoPopupView;

});

define('widgets/InfoPopup/InfoPopup',['jscore/core','widgets/WidgetCore','widgets/utils/domUtils','./InfoPopupView'],function (core, WidgetCore, domUtils, View) {
    'use strict';

    /**
     * The InfoPopup class uses the Ericsson brand assets.<br>
     * The InfoPopup can be instantiated using the constructor InfoPopup.
     *
     * The following options are accepted:
     *   <ul>
     *       <li>content: the content displayed on the InfoPopup, may contain HTML or plain text. Default is 'InfoPopup'</li>
     *       <li>enabled: boolean indicating whether InfoPopup should be enabled. Default is true.</li>
     *       <li>persistent: boolean, if true, then clicking outside the InfoPopup will not close it. Default is false.</li>
     *       <li>width: CSS width for the info popup.</li>
     *       <li>corner: String which determines the corner of the InfoPopup.
     *          <br>Possible values are auto, default, topRight, bottomLeft and bottomRight. The auto option allows the InfoPopup to automatically determine the corner. Default is auto.</li>
     *       <li>icon (Optional): a string used to define an icon to show. You can use any of the icons from ebIcon. Icons should be declared as follows, icon: 'iconName'. The ebIcon_ prefix is not required in the declaration. Default is 'info'.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class InfoPopup
     * @extends WidgetCore
     * @baselined
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        View: View,

        /**
         * The init method is automatically called by the constructor when using the 'new' operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param {Object} options
         * @private
         */
        init: function (options) {
            this._isVisible = false;
            this._enabled = this.options.enabled !== undefined? this.options.enabled : true;
            this._corner = this.options.corner !== undefined? this.options.corner : 'auto';
            this._persistent = this.options.persistent !== undefined? this.options.persistent : false;
            this._icon = this.options.icon || 'info';
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.setContent(this.options.content || '');

            this.view.getInfoIcon().setModifier(this._icon);
            // Apply the click event onto the info icon to show/hide content
            this.view.getInfoIcon().addEventHandler('click', function(e) {
                if (this._enabled) {
                    toggle.call(this);
                }
            }.bind(this));

            // When the user clicks the close button, close the content
            this.view.getCloseButton().addEventHandler('click', function(){
                toggle.call(this, false);
            }.bind(this));

            // By default the widget is enabled, disable it if explicitly told to
            if (!this._enabled) {
                this.disable();
            }

            if (this.options.width) {
                this.view.getContent().setStyle('width', this.options.width);
            }

            // We don't want the content of the info popup in the DOM unless needed
            this.view.getContent().detach();

            this.view.getContent().setStyle({
                'position': 'fixed',
                'bottom': 'inherit',
                'right': 'inherit'
            });

        },

        onDOMAttach: function() {
            // If the developer modified the visibility of the widget prior to attached to the real DOM, trigger the visibility now
            this._onDOMAttachExecuted = true;
            if (this._onDOMAttachSetVisible !== undefined) {
                this.setVisible(this._onDOMAttachSetVisible);
            }
        },

        /**
         * Sets the contents of the InfoPopup
         * Can be plain text or HTML
         *
         * @method setContent
         * @param {String} content
         */
        setContent: function (content) {
            this.view.setContentText(content);
        },

        /**
         * This method sets the visibility of the InfoPopup
         *
         * @method setVisible
         * @param {Boolean} visible
         */
        setVisible: function (visible) {
            if (!this._onDOMAttachExecuted) {
                // Developers should be able to modify the visibility of the widget prior to it being attached to DOM
                this._onDOMAttachSetVisible = visible;
            } else {
                toggle.call(this, visible);
            }
        },

        /**
         * This method enables the InfoPopup
         *
         * @method enable
         */
        enable: function () {
            this._enabled = true;
            this.view.getInfoIcon().removeModifier('disabled');
            this.view.getInfoIcon().setModifier('interactive');
        },

        /**
         * This method disables the InfoPopup
         *
         * @method disable
         */
        disable: function () {
            this._enabled = false;
            this.view.getInfoIcon().removeModifier('interactive');
            this.view.getInfoIcon().setModifier('disabled');
            this.setVisible(false);
        }

    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    /**
     * Toggles the private visibility variable.
     * Attaches/Detaches.
     * Sets up or clears interval.
     *
     * @method toggle
     * @private
     * @param {Boolean} visible
     */
    function toggle(visible) {
        this._isVisible = visible !== undefined? visible : !this._isVisible;

        if (this._isVisible) {
            this.getElement().append(this.view.getContent());

            // If not persistent, then clicking any element outside this popup should close it
            if (!this._persistent) {
                // Apply the click event to the whole document
                var body = core.Element.wrap(document.documentElement);
                if (!this.bodyEventId) {
                    this.bodyEventId = body.addEventHandler('click', function(e) {
                        if (e && e.originalEvent) {
                            var target = core.Element.wrap(e.originalEvent.target);
                            // If this widget doesn't contain the target element, then hide the content
                            if (!this.getElement().contains(target)) {
                                body.removeEventHandler(this.bodyEventId);
                                delete this.bodyEventId;
                                toggle.call(this, false);
                            }
                        }

                    }.bind(this));
                }
            }

            applyPosition.call(this);
        } else {
            this.view.getContent().detach();
            if (this.posInterval) {
                clearInterval(this.posInterval);
                this.posInterval = undefined;
            }
        }
    }

    /**
     * Calculates new position, sets the position, and creates the interval.
     * If persistent, interval will change position of infopopup on pos change.
     * If not persistent, interval will set visibility to false of infopopup on pos change.
     *
     * @method applyPosition
     * @private
     */
    function applyPosition() {
        var pos = this.view.getInfoIcon().getPosition();
        var cornerDelta = calculateAndApplyCorner.call(this, pos);

        this.view.getContent().setStyle({
            top: pos.top + cornerDelta.top + 'px',
            left: pos.left + cornerDelta.left + 'px'
        });

        if (!this.posInterval) {
            this.position = pos;
            this.posInterval = setInterval(function() {
                var curPosition = this.view.getInfoIcon().getPosition();
                if (this.position.top !== curPosition.top || this.position.left !== curPosition.left) {
                    if (!this._persistent) {
                        this.setVisible(false);
                    } else {
                        this.view.getContent().setStyle({
                            top: curPosition.top + cornerDelta.top + 'px',
                            left: curPosition.left + cornerDelta.left + 'px'
                        });
                        this.position = curPosition;
                    }
                }
            }.bind(this), 1000 / 24);
        }
    }

    /**
     * Uses the dimensions of the elements in the widget and the screen to calculate the corner.
     * Returns the delta offset for the corner that's to be applied to the position.
     *
     * @method calculateAndApplyCorner
     * @private
     * @param {Object} position
     * @return {Object} cornerDelta
     */
    function calculateAndApplyCorner(position) {
        // Get the dimensions of everything
        var iconH = this.view.getInfoIcon().getProperty('offsetHeight'),
            iconW = this.view.getInfoIcon().getProperty('offsetWidth'),
            arrowH = 8,
            arrowOffsetW = 12,
            contentH = this.view.getContent().getProperty('offsetHeight'),
            contentW = this.view.getContent().getProperty('offsetWidth'),
            screenW = core.Window.getProperty('innerWidth'),
            screenH = core.Window.getProperty('innerHeight');

        var corner = this._corner;

        // Check if the content passes the right and bottom edges of the screen
        if (this._corner === 'auto') {
            var pastRight = position.left + contentW > screenW;
            var pastBottom = position.top + contentH > screenH;
            if (pastRight && !pastBottom) {
                corner = 'topRight';
            } else if (pastRight && pastBottom) {
                corner = 'bottomRight';
            } else if (!pastRight && pastBottom) {
                corner = 'bottomLeft';
            } else {
                corner = 'default';
            }
        }

        this.view.getContent().setModifier('corner', corner);

        var cornerDelta = {top:0, left:0};

        // Calculate the delta required for the corners
        if (corner === 'default') {
            cornerDelta.top += iconH + arrowH;
            cornerDelta.left += -arrowOffsetW;
        } else if (corner === 'topRight') {
            cornerDelta.top += iconH + arrowH;
            cornerDelta.left += -contentW + iconW + arrowOffsetW;
        } else if (corner === 'bottomLeft') {
            cornerDelta.top += -contentH - arrowH;
            cornerDelta.left += -arrowOffsetW;
        } else if (corner === 'bottomRight') {
            cornerDelta.top += -contentH - arrowH;
            cornerDelta.left += -contentW + iconW + arrowOffsetW;
        }

        return cornerDelta;
    }

});

define('widgets/InfoPopup',['widgets/InfoPopup/InfoPopup'],function (main) {
                        return main;
                    });

