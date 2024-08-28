/* Copyright (c) Ericsson 2014 */

define('template!widgets/Dialog/_dialog.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this;

function program1(depth0,data) {
  
  
  return "\n                <div class=\"ebDialogBox-icon\">\n                    <span class=\"ebDialog-topRightButton ebIcon ebIcon_close ebIcon_interactive\"></span>\n                </div>\n                ";}

function program3(depth0,data) {
  
  
  return "\n                <div class=\"ebDialogBox-primaryText\"></div>\n                <div class=\"ebDialogBox-secondaryText\"></div>\n                <div class=\"ebDialogBox-thirdText\"></div>\n                ";}

  buffer += "<div class=\"ebDialog\">\n    <div class=\"ebDialog-holder\">\n        <div class=\"ebDialogBox\">\n            <div class=\"ebDialogBox-contentBlock\">\n                ";
  foundHelper = helpers.topRightCloseBtn;
  stack1 = foundHelper || depth0.topRightCloseBtn;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                ";
  foundHelper = helpers.fullContent;
  stack1 = foundHelper || depth0.fullContent;
  stack2 = helpers.unless;
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n            </div>\n            <div class=\"ebDialogBox-actionBlock\">\n                <button class=\"ebBtn ebBtn_color_darkBlue ebDialog-primaryActionButton\"></button>\n                <button class=\"ebBtn ebDialog-secondaryActionButton\"></button>\n                <button class=\"ebBtn ebDialog-thirdActionButton\"></button>\n            </div>\n        </div>\n    </div>\n</div>\n";
  return buffer;});});

define('widgets/Dialog/DialogView',['jscore/core','template!./_dialog.html'],function (core, template) {
    'use strict';

    var DialogView = core.View.extend({

        afterRender: function () {
            this.contentBlock = this.getElement().find('.' + DialogView.EL_CONTENT_BLOCK);
            this.header = this.getElement().find('.' + DialogView.EL_HEADER);
            this.content = this.getElement().find('.' + DialogView.EL_CONTENT);
            this.optionalContent = this.getElement().find('.' + DialogView.EL_OPTIONAL_CONTENT);
            this.actionBlock = this.getElement().find('.' + DialogView.EL_ACTION_BLOCK);
            this.primaryBtn = this.getElement().find('.' + DialogView.EL_PRIMARY_BTN);
            this.secondaryBtn = this.getElement().find('.' + DialogView.EL_SECONDARY_BTN);
            this.thirdBtn = this.getElement().find('.' + DialogView.EL_THIRD_BTN);
            this.topRightBtn = this.getElement().find('.' + DialogView.EL_TOP_RIGHT_BTN);
        },

        getTemplate: function () {
            return template(this.options);
        },

        getRoot: function () {
            return this.getElement();
        },

        getContentBlock: function () {
            return this.contentBlock;
        },

        getHeader: function () {
            return this.header;
        },

        getContent: function () {
            return this.content;
        },

        getOptionalContent: function () {
            return this.optionalContent;
        },

        getActionBlock: function () {
            return this.actionBlock;
        },

        getPrimaryButton: function () {
            return this.primaryBtn;
        },

        getSecondaryButton: function () {
            return this.secondaryBtn;
        },

        getThirdButton: function () {
            return this.thirdBtn;
        },

        getTopRightCloseBtn: function () {
            return this.topRightBtn;
        }

    }, {
        EL_CONTENT_BLOCK: 'ebDialogBox-contentBlock',
        EL_HEADER: 'ebDialogBox-primaryText',
        EL_CONTENT: 'ebDialogBox-secondaryText',
        EL_OPTIONAL_CONTENT: 'ebDialogBox-thirdText',
        EL_ACTION_BLOCK: 'ebDialogBox-actionBlock',
        EL_PRIMARY_BTN: 'ebDialog-primaryActionButton',
        EL_SECONDARY_BTN: 'ebDialog-secondaryActionButton',
        EL_THIRD_BTN: 'ebDialog-thirdActionButton',
        EL_TOP_RIGHT_BTN: 'ebDialog-topRightButton'
    });

    return DialogView;

});

define('widgets/Dialog/Dialog',['jscore/core','widgets/WidgetCore','./DialogView','widgets/Button'],function (core, WidgetCore, View, Button) {
    'use strict';

    /**
     * The Dialog class uses the Ericsson brand assets.<br>
     * The Dialog can be used as message window or popup window with action.<br>
     * The Dialog can be instantiated using the constructor Dialog.
     *
     * The following options are accepted:
     *   <ul>
     *       <li>header: a string used as a Dialog header. Default is 'Header'.</li>
     *       <li>content: a Widget or string used as a Dialog content. <b>Element is deprecated for content</b>. Default is 'Content'.</li>
     *       <li>optionalContent: a Widget or string used as a Dialog content. Default is undefined and not shown.</li>
     *       <li>visible: a boolean indicating whether a Dialog should be visible. Default is false.</li>
     *       <li>showPrimaryButton: a boolean indicating whether a Dialog should have primary action button. Default is true. <b>Deprecated</b>.</li>
     *       <li>primaryButtonCaption: a string used as primary action button caption. Default is 'Save'. <b>Deprecated</b>.</li>
     *       <li>secondaryButtonCaption: a string used as secondary action button caption. Default is 'Cancel'. <b>Deprecated</b>.</li>
     *       <li>type: a string used to define the Dialog type.</li>
     *       <li>fullContent: boolean to indicate that the content provided will take full dialog content space. Default is false.</li>
     *       <li>topRightCloseBtn: boolean to show the dialog close icon on the top right corner. Default is false.</li>
     *       <li>buttons: an array used to define list of buttons.<br>
     *           buttons: [
     *             {caption: 'Save', action: callback, color: 'red'},
     *             {caption: 'Discard', action: callback},
     *             {caption: 'Cancel', action: callback}
     *           ]
     *       </li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class Dialog
     */
    // !!! DO NOT ADD info that Dialog extends WidgetCore.
    return WidgetCore.extend({

        view: function () {
            return new View(this.options);
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            if (this.options.fullContent !== true) {
                this.setHeader(this.options.header || 'Header');
                this.setOptionalContent(this.options.optionalContent);
            }
            this.setContent(this.options.content || 'Content');


            if (this.options.buttons) {
                this.view.getPrimaryButton().remove();
                this.view.getSecondaryButton().remove();
                this.view.getThirdButton().remove();

                this.setButtons(this.options.buttons || [
                    {name: 'Close', action: function () {
                        this.hide();
                    }.bind(this)}
                ]);
            } else {
                // TODO: should be removed with new MAJOR version.
                this.setPrimaryButtonCaption(this.options.primaryButtonCaption || 'Save');
                this.setSecondaryButtonCaption(this.options.secondaryButtonCaption || 'Cancel');
                this.setThirdButtonCaption(this.options.thirdButtonCaption || 'Other');
            }

            this.setDialogType(this.options.type || 'default');

            if (this.options.showPrimaryButton === false) {
                this.hidePrimaryButton();
            }
            if (this.options.showThirdButton === undefined || this.options.showThirdButton === false) {
                this.hideThirdButton();
            }

            this.visible = false;
            if (this.options.visible === true) {
                this.show();
            }

            if (this.options.topRightCloseBtn === true) {
                this.view.getTopRightCloseBtn().addEventHandler('click', function () {
                    this.hide();
                }, this);
            }

            addWindowHashChangeHandler.call(this);
        },

        /**
         * Overrides method from widget.
         * Executes before destroy, remove event handlers.
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function () {
            removeWindowHashChangeHandler.call(this);
        },

        /**
         * Sets header for the dialog.
         *
         * @method setHeader
         * @param {String} headerText
         */
        setHeader: function (headerText) {
            this.view.getHeader().setText(headerText);
        },

        /**
         * Sets content for the Dialog.
         *
         * @method setContent
         * @param {Widget|Element|String} content Element type is deprecated here!
         */
        setContent: function (content) {
            // TODO: "content instanceof core.Element" should be removed with new MAJOR version
            if (typeof(content) === 'string' || content instanceof core.Widget || content instanceof core.Element) {
                var contentContainer = (this.options.fullContent !== true) ? this.view.getContent() : this.view.getContentBlock();

                var children = contentContainer.children();
                if (children.length > 0) {
                    children.forEach(function (child) {
                        child.detach();
                    });
                }
                contentContainer.setText('');

                if (typeof(content) === 'string' || content instanceof core.Element) {
                    var contentElement = content;
                    if (typeof(content) === 'string') {
                        contentElement = core.Element.parse('<p></p>');
                        contentElement.setText(content);
                    }
                    contentContainer.append(contentElement);
                } else {
                    content.attachTo(contentContainer);
                }
            } else {
                throw new Error('Content for Dialog should be Widget or String!');
            }
        },

        /**
         * Sets optional content for the Dialog.
         *
         * @method setOptionalContent
         * @param {Widget|String} optionalContent
         */
        setOptionalContent: function (optionalContent) {
            if (optionalContent === undefined) {
                this.view.getOptionalContent().detach();
                return;
            }

            if (typeof(optionalContent) === 'string' || optionalContent instanceof core.Widget) {
                var children = this.view.getOptionalContent().children();
                if (children.length > 0) {
                    children.forEach(function (child) {
                        child.detach();
                    });
                }
                this.view.getOptionalContent().setText('');

                if (typeof(optionalContent) === 'string') {
                    var contentElement = core.Element.parse('<p></p>');
                    contentElement.setText(optionalContent);
                    this.view.getOptionalContent().append(contentElement);
                } else {
                    optionalContent.attachTo(this.view.getOptionalContent());
                }
            } else {
                throw new Error('Content for Dialog should be Widget or String!');
            }
        },

        /**
         * Sets buttons with actions to the Dialog's action block
         *
         * @method setButtons
         * @param {Array} buttons An array used to define list of buttons.
         *
         * @example
         *   buttons: [
         *     {caption: 'Save', action: callback, color: 'red'},
         *     {caption: 'Discard', action: callback},
         *     {caption: 'Cancel', action: callback}
         *   ]
         */
        setButtons: function (buttons) {
            var $buttonsHolder = this.view.getActionBlock();
            $buttonsHolder.children().forEach(function ($button) {
                $button.detach();
            });
            if (this.buttons) {
                this.buttons.forEach(function (button) {
                    button.destroy();
                });
            }

            var firstButton = buttons[0];
            if (!firstButton.color) {
                firstButton.color = 'darkBlue';
            }

            this.buttons = [];
            buttons.forEach(function (buttonObj) {
                var button = new Button({
                    caption: buttonObj.caption,
                    modifiers: []
                });

                if (buttonObj.color) {
                    button.setModifier('color', buttonObj.color);
                }
                button.addEventHandler('click', buttonObj.action);

                button.attachTo(this.view.getActionBlock());
                this.buttons.push(button);
            }.bind(this));
        },

        /**
         * Returns array of all buttons from the Dialog.
         *
         * @method getButtons
         * @returns {Array}
         */
        getButtons: function () {
            return this.buttons;
        },

        /**
         * Sets the dialog type.<br>
         *
         * @method setDialogType
         * @param {String} type Available values: default, confirmation, information, warning and error.
         */
        setDialogType: function (type) {
            var possibleTypes = ['warning', 'error', 'confirmation', 'information'];
            if (possibleTypes.indexOf(type) !== -1) {
                this.view.getContentBlock().setModifier('type', type);
            } else {
                this.view.getContentBlock().removeModifier('type');
            }
        },

        /**
         * Shows the Dialog.
         *
         * @method show
         */
        show: function () {
            this.visible = true;
            addWindowHashChangeHandler.call(this);

            var body = core.Element.wrap(document.body);
            body.append(this.getElement());
        },

        /**
         * Hides the Dialog.
         *
         * @method hide
         */
        hide: function () {
            if (this.isVisible()) {
                this.visible = false;
                this.getElement().detach();
                removeWindowHashChangeHandler.call(this);
            }
        },

        /**
         * Returns boolean of the Dialog visible state.
         *
         * @method isVisible
         * @return {boolean}
         */
        isVisible: function () {
            return this.visible;
        },

        /**
         * Sets primary button caption for the Dialog.
         *
         * @method setPrimaryButtonCaption
         * @deprecated
         *
         * @param {String} buttonCaption
         */
        setPrimaryButtonCaption: function (buttonCaption) {
            this.getPrimaryButton().setText(buttonCaption);
        },

        /**
         * Sets secondary button caption for the Dialog.
         *
         * @method setSecondaryButtonCaption
         * @deprecated
         *
         * @param {String} buttonCaption
         */
        setSecondaryButtonCaption: function (buttonCaption) {
            this.getSecondaryButton().setText(buttonCaption);
        },

        /**
         * Sets third button caption for the Dialog.
         *
         * @method setThirdButtonCaption
         * @deprecated
         *
         * @param {String} buttonCaption
         */
        setThirdButtonCaption: function (buttonCaption) {
            this.getThirdButton().setText(buttonCaption);
        },

        /**
         * Hides primary button for the Dialog.
         *
         * @method hidePrimaryButton
         * @deprecated
         */
        hidePrimaryButton: function () {
            this.getPrimaryButton().detach();
        },

        /**
         * Hides secondary button for the Dialog.
         *
         * @method hideSecondaryButton
         * @deprecated
         */
        hideSecondaryButton: function () {
            this.getSecondaryButton().detach();
        },

        /**
         * Hides third button for the Dialog.
         *
         * @method hideThirdButton
         * @deprecated
         */
        hideThirdButton: function () {
            this.getThirdButton().detach();
        },

        /**
         * Shows primary button for the Dialog.
         *
         * @method showPrimaryButton
         * @deprecated
         */
        showPrimaryButton: function () {
            this.getPrimaryButton().detach();
            this.getSecondaryButton().detach();
            this.getThirdButton().detach();

            this.view.getActionBlock().append(this.getPrimaryButton());
            this.view.getActionBlock().append(this.getSecondaryButton());
            this.view.getActionBlock().append(this.getThirdButton());
        },

        /**
         * A shorthand for dialog.view.getPrimaryButton().
         *
         * @method getPrimaryButton
         * @deprecated
         *
         * @return {Element}
         */
        getPrimaryButton: function () {
            return this.view.getPrimaryButton();
        },

        /**
         * A shorthand for dialog.view.getSecondaryButton().
         *
         * @method getSecondaryButton
         * @deprecated
         *
         * @return {Element}
         */
        getSecondaryButton: function () {
            return this.view.getSecondaryButton();
        },

        /**
         * A shorthand for dialog.view.getThirdButton().
         *
         * @method getThirdButton
         * @deprecated
         *
         * @return {Element}
         */
        getThirdButton: function () {
            return this.view.getThirdButton();
        }

    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function addWindowHashChangeHandler() {
        /*jshint validthis:true */
        if (this._windowEvtId) {
            return;
        }
        this._windowEvtId = core.Window.addEventHandler('hashchange', this.hide.bind(this));
    }

    function removeWindowHashChangeHandler() {
        /*jshint validthis:true */
        if (this._windowEvtId) {
            core.Window.removeEventHandler(this._windowEvtId);
            this._windowEvtId = undefined;
        }
    }
});

define('widgets/Dialog',['widgets/Dialog/Dialog'],function (main) {
                        return main;
                    });

