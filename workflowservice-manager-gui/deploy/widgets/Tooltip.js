/* Copyright (c) Ericsson 2014 */

define('text!widgets/Tooltip/_tooltip.html',[],function () { return '<div class="ebTooltip">\n    <div class="ebTooltip-outer">\n        <div class="ebTooltip-contentText"></div>\n    </div>\n</div>\n';});

define('widgets/Tooltip/TooltipView',['jscore/core','widgets/utils/domUtils','text!./_tooltip.html'],function (core, domUtils, template) {
    'use strict';

    var TooltipView =  core.View.extend({
        /*jshint validthis:true*/

        // TODO: Should be added to core.View and executed after render()
        afterRender: function () {
            var element = this.getElement();
            this.outerEl = element.find('.' + TooltipView.OUTER_CLASS);
            this.content = element.find('.' + TooltipView.CONTENT_CLASS);
        },

        getTemplate: function() {
            return template;
        },

		setVisible: function (isVisible) {
            this.getElement().setModifier('visible', '' + isVisible);
		},

        setFade: function(fades) {
            if (fades) {
                this.getElement().setModifier('fades');
            } else {
                this.getElement().removeModifier('fades');
            }
        },

		setCorner: function(input) {
            this.outerEl.setModifier('corner', input);
		},

        calcCorner: function(dimensions, offset) {
			//use dimensions and offset to determine which variation of tooltip to show
            var outerElDimensions = domUtils.getElementDimensions(this.outerEl),
                cornerName = '';

            cornerName += (dimensions.height - offset.top) < outerElDimensions.height + 30 ? 'bottom' : 'top';
            cornerName += (dimensions.width - offset.left) < outerElDimensions.width + 30 ? 'Right' : 'Left';

            if (cornerName === 'topLeft') {
                cornerName = 'default';
            }
            updateCorner.call(this, cornerName);
		},

        setDisplayType: function(displayType) {
            this.getElement().setStyle('display', displayType);
        },

        setContentText: function(caption) {
            this.content.setText(caption);
        },

        getContent: function() {
            return this.content;
        },

		setPosition: function(l, t) {
            this.getElement().setStyle({
                left: l,
                top: t
            });
		}

    }, {
        OUTER_CLASS: 'ebTooltip-outer',
        CONTENT_CLASS: 'ebTooltip-contentText'
    });

    return TooltipView;

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function updateCorner(cornerName) {
        if (this.currentCorner !== cornerName) {
            this.setCorner(cornerName);
            this.currentCorner = cornerName;
        }
    }

});

define('widgets/Tooltip/Tooltip',['widgets/WidgetCore','widgets/utils/domUtils','./TooltipView'],function (WidgetCore, domUtils, View) {
    'use strict';

    /**
     * The Tooltip class uses the Ericsson brand assets.<br>
     * The Tooltip can be instantiated using the constructor Tooltip.
     *
     * The following options are accepted:
     *   <ul>
     *       <li>contentText: the text displayed on the tooltip. Default is "Tooltip".</li>
     *       <li>enabled: boolean indicating whether tooltip should be enabled. Default is true.</li>
     *       <li>timeout: milliseconds, tooltip will disappear after this length of time if the user is still hovering over the element.</li>
     *       <li>delay: milliseconds, tooltip will appear after this length of time if the user is still hovering over the element.</li>
     *       <li>fade: true/false, if true, tooltip will have a fading effect. Default is true.</li>
     *       <li>width: number, custom size for the Tooltip.</li>
     *       <li>modifiers: an array used to define modifiers for the Tooltip. (Asset Library)
     *          <a href="#modifierAvailableList">see available modifiers</a>
     *          <br>E.g: modifiers:[{name: 'foo'}, {name: 'bar', value: 'barVal'}]
     *       </li>
     *       <li>parent: sets the parent of the tooltip.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>size: ('small' | 'large') size variation</li>
     *  </ul>
     *
     * @class Tooltip
     * @extends WidgetCore
     * @baselined
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        View: View,

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.setContentText(this.options.contentText || 'Tooltip');
            this.setModifiers(this.options.modifiers || []);
            this.setWidth(this.options.width);

            if (this.options.enabled !== undefined) {
                this.enabled = this.options.enabled;
            } else {
                this.enabled = true;
            }

            this.timeout = this.options.timeout || 5000;
            this.delay = this.options.delay || 500;
            this.fade = this.options.fade === undefined ? true : this.options.fade;
            this.view.setFade(this.fade);

            _setVisible.call(this, false);
            this.addHoverHandler(this.options.parent);

            // TODO: should be replaced to proper method from JSCore.
            setTimeout(function () {
                this.detach();
            }.bind(this), 1);
        },

        destroy: function()
        {
            this.parent.removeEventHandler(this._mouseLeaveEvent);
            this.parent.removeEventHandler(this._mouseEnterEvent);
            this.parent.removeEventHandler(this._mouseMoveEvent);
            this.parent = undefined;
        },


        /**
         * This method sets the contents of the Tooltip
         *
         * @method setContentText
         * @param {String} caption
         */
        setContentText: function (caption) {
            this.view.setContentText(caption);
        },

        /**
         * This method enables the Tooltip
         *
         * @method enable
         */
        enable: function () {
            this.enabled = true;
        },

        /**
         * This method disables the Tooltip
         *
         * @method disable
         */
        disable: function () {
            this.enabled = false;
            _setVisible.call(this, false);
        },

        /**
         * Sets custom size for the Tooltip. Rewrites used sizes by modifier.
         *
         * @method setWidth
         * @param {int} width
         */
        setWidth: function (width) {
            if (width) {
                this.getElement().setStyle('width', width);
            }
        },

        /**
         * This method adds the event listeners required to make the tooltip function.<br>
         * It accepts the parent element as input.<br>
         * This method is called automatically if the parent is passed into the constructor.
         *
         * @method addHoverHandler
         * @param {Object} parent
         */
        addHoverHandler: function (parent) {
            if (!parent) {
                return;
            }
            this.parent = parent;

            this._mouseEnterEvent = this.parent.addEventHandler('mouseenter', function (e) {
                if (this.enabled) {
                    domUtils.stopPropagation(e);
                    if (this.exitTransitionEventId) {
                        this.getElement().removeEventHandler(this.exitTransitionEventId);
                        delete this.exitTransitionEventId;
                    }

                    this.startTimeoutFunction = setTimeout(function () {
                        this.attach();

                        var mousePos = this.previousMousePos;
                        _setPosition.call(this, mousePos.left, mousePos.top);
                        _calcCorner.call(this, mousePos);
                        _setVisible.call(this, true);

                        this.timeoutFunction = setTimeout(function () {
                            _exitTransition.call(this);
                            clearTimeout(this.timeoutFunction);
                        }.bind(this), this.timeout);
                    }.bind(this), this.delay);
                }
            }.bind(this));

            this._mouseMoveEvent = this.parent.addEventHandler('mousemove', function (e) {
                if (this.enabled) {
                    domUtils.stopPropagation(e);
                    var mousePos = domUtils.getMousePosEvt(e);
                    _setPosition.call(this, mousePos.left, mousePos.top);
                    _calcCorner.call(this, mousePos);
                    this.previousMousePos = mousePos;
                }
            }.bind(this));

            this._mouseLeaveEvent = this.parent.addEventHandler('mouseleave', function (e) {
                if (this.enabled) {
                    domUtils.stopPropagation(e);
                    _exitTransition.call(this);

                    if (this.startTimeoutFunction) {
                        clearTimeout(this.startTimeoutFunction);
                    }

                    if (this.timeoutFunction) {
                        clearTimeout(this.timeoutFunction);
                    }
                }
            }.bind(this));
        }

    });


    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function _exitTransition() {
        _setVisible.call(this, false);

        if (this.fade) {
            this.exitTransitionEventId = this.getElement().addEventHandler('transitionend', function () {
                this.detach();
                this.getElement().removeEventHandler(this.exitTransitionEventId);
                delete this.exitTransitionEventId;
            }.bind(this));
        } else {
            this.detach();
        }
    }

    function _calcCorner(mousePos) {
        //pass dimensions and mouse position to calcCorner fn in view
        this.view.calcCorner(domUtils.getWindowDimensions(), mousePos);
    }

    function _setPosition(left, top) {
        this.view.setPosition(left, top);
    }

    function _setVisible(isVisible) {
        this.view.setVisible(isVisible);
    }

});

define('widgets/Tooltip',['widgets/Tooltip/Tooltip'],function (main) {
                        return main;
                    });

