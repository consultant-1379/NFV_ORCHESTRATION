/* Copyright (c) Ericsson 2014 */

define('text!widgets/Accordion/_accordion.html',[],function () { return '<div class="ebAccordion">\n    <div class="ebAccordion-header">\n        <div class="ebAccordion-button" title="Expand">\n            <i class="ebIcon ebIcon_small"></i>\n        </div>\n        <div class="ebAccordion-title"></div>\n    </div>\n\n    <div class="ebAccordion-body">&nbsp;</div>\n</div>';});

define('widgets/Accordion/AccordionView',['jscore/core','widgets/utils/domUtils','text!./_accordion.html'],function (core, domUtils, template) {
    'use strict';

    /*jshint validthis:true */
    var AccordionView = core.View.extend({

        // TODO: Should be added to core.View and executed after render()
        afterRender: function () {
            this.title = this.getElement().find('.' + AccordionView.EL_TITLE);
            this.button = this.getElement().find('.' + AccordionView.EL_BUTTON);
            this.content = this.getElement().find('.' + AccordionView.EL_BODY);
            this.header = this.getElement().find('.' + AccordionView.EL_HEADER);
            this._icon = this.getElement().find('.' + AccordionView.EL_ICON);
            this._icon.setModifier('downArrow', '10px');

            this.contentHeight = 0;
            this.contentAppended = false;
            this.contentItem = undefined;
        },

        getTemplate: function () {
            return template;
        },

        getTitle: function () {
            return this.title;
        },

        getButton: function () {
            return this.button;
        },

        getContent: function () {
            return this.content;
        },

        getHeader: function () {
            return this.header;
        },

        setContentItem: function (contentItem) {
            this.contentItem = contentItem;
        },

        getContentItem: function () {
            return this.contentItem;
        },

        foldBody: function () {
            this._icon.removeModifier('upArrow');
            this._icon.setModifier('downArrow', '10px');
            this.button.setAttribute('title', 'Expand');

            this.recalculateHeight();
            this.getContent().setStyle('max-height', this.contentHeight + 'px');

            this.bodyMaxHeightCloseTimeout = setTimeout(function () {
                this.getContent().setStyle('max-height', '0');
                this.bodyMaxHeightCloseTimeout = undefined;
            }.bind(this), 50);

            if (this.bodyMaxHeightOpenTimeout) {
                clearTimeout(this.bodyMaxHeightOpenTimeout);
                this.bodyMaxHeightOpenTimeout = undefined;
            }
        },

        replaceContent: function() {
            _replaceContent.call(this);
            animateExpansion.call(this);
        },

        expandBody: function () {
            this.replaceContent();
            this._icon.removeModifier('downArrow');
            this._icon.setModifier('upArrow', '10px');
            this.button.setAttribute('title', 'Collapse');
        },

        recalculateHeight: function () {
            this.contentHeight = domUtils.getOuterHeight(this.getContent().children()[0]);
        }

    }, {
        'EL_HEADER': 'ebAccordion-header',
        'EL_TITLE': 'ebAccordion-title',
        'EL_BUTTON': 'ebAccordion-button',
        'EL_ICON': 'ebIcon',
        'EL_BODY': 'ebAccordion-body'
    });


    function animateExpansion() {
        this.recalculateHeight();
        this.getContent().setStyle('max-height', this.contentHeight + 'px');

        this.bodyMaxHeightOpenTimeout = setTimeout(function () {
            this.getContent().setStyle('max-height', 'inherit');
            this.bodyMaxHeightOpenTimeout = undefined;
        }.bind(this), 500);

        if (this.bodyMaxHeightCloseTimeout) {
            clearTimeout(this.bodyMaxHeightCloseTimeout);
            this.bodyMaxHeightCloseTimeout = undefined;
        }
    }

    function _replaceContent() {
        var contentItem = this.getContentItem();
        if (!this.contentAppended && contentItem) {
            this.getContent().setText('');
            if (contentItem instanceof core.Element) {
                this.getContent().append(contentItem);
            } else {
                contentItem.attachTo(this.getContent());
            }
            this.contentAppended = true;
        }
    }

    return AccordionView;

});

define('widgets/Accordion/Accordion',['jscore/core','widgets/WidgetCore','widgets/utils/domUtils','./AccordionView'],function (core, WidgetCore, domUtils, View) {
    'use strict';

    /**
     * The Accordion class wraps a menu entry and its sub-menu list in a Widget.<br>
     * The Accordion can be instantiated using the constructor Accordion.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>Accordion(Object options)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>click: this event is triggered when user clicks on the Accordion expand/collapse button</li>
     *     <li>expand: this event can be triggered by the Accordion widget to expand content</li>
     *     <li>beforeExpand: this event can be triggered by the Accordion widget before expand content</li>
     *     <li>collapse: this event can be triggered by the Accordion widget to collapse content</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>title: a string used for the accordion header.</li>
     *       <li>content: a content of String or core.Widget used as a accordion content.</li>
     *       <li>enabled: boolean indicating whether the accordion should be enabled. Default is true.</li>
     *       <li>expanded: boolean indicating whether the accordion should be expanded. Default is false.</li>
     *       <li>beforeExpand: callback function that can be passed to be called before the widget is expanded to support lazy loading accordion.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *   <ul>
     *       <li>disabled: Applying the modifier ebAccordion_disabled will show the disabled state.</li>
     *   </ul>
     *
     * @class Accordion
     * @extends WidgetCore
     */
    return WidgetCore.extend({
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
            this._expanded = false;
            this._empty = true;
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.setTitle(this.options.title || 'Accordion Header');
            this.setContent(this.options.content);

            if (this.options.enabled === false) {
                this.disable();
            }


            this.addEventHandler('click', _onButtonClick.bind(this));
            if (this.options.beforeExpand !== undefined) {
                this.addEventHandler('beforeExpand', this.options.beforeExpand);
            }
            this.addEventHandler('expand', _expandEvent.bind(this));
            this.addEventHandler('collapse', _collapseEvent.bind(this));

            if (this.options.expanded === true) {
                this.trigger('expand');
            }
        },

        /**
         * Sets title for the Accordion
         *
         * @method setTitle
         * @param {core.Widget|String} title Should be core.Widget or string
         */
        setTitle: function (title) {
            if (this._headerClickEventId) {
                this.view.getHeader().removeEventHandler(this._headerClickEventId);
                this._headerClickEventId = undefined;
            }
            if (this._buttonClickEventId) {
                this.view.getButton().removeEventHandler(this._buttonClickEventId);
                this._buttonClickEventId = undefined;
            }
            this.view.getHeader().removeModifier('noAction');

            if (typeof(title) === 'string' || title instanceof String) {
                this.view.getTitle().setText(title);

                this._headerClickEventId = this.view.getHeader().addEventHandler('click', function () {
                    this.trigger('click');
                }, this);
            } else if (title instanceof core.Widget) {
                this.view.getHeader().setModifier('noAction');
                title.attachTo(this.view.getTitle());

                this._buttonClickEventId = this.view.getButton().addEventHandler('click', function () {
                    this.trigger('click');
                }, this);
            } else {
                throw new Error('Title for Accordion should be core.Widget or String!');
            }
        },

        /**
         * Returns true if the accordion has any content set, otherwise returns false.
         *
         * @method isEmpty
         */
        isEmpty: function () {
            return this._empty;
        },

        /**
         * Sets content for the Accordion.
         *
         * @method setContent
         * @param {core.Widget|String} content Should be core.Widget or string
         */
        setContent: function (content) {
            var contentEl = this.view.getContent();
            contentEl.children().forEach(function (child) {
                child.detach();
            });

            // This error will only throw if content is defined, and it's not an acceptable type (eg. function)
            if (content !== undefined && !(content instanceof core.Widget) && typeof content !== 'string' && !(content instanceof String)) {
                throw new Error('Content for Accordion should be core.Widget or String!');
            }

            if (content && (typeof content === 'string' || content instanceof String)) {
                var el = new core.Element();
                el.setText(content);
                content = el;
            }

            this.view.setContentItem(content);
            this.view.contentAppended = false;

            if (this._expanded) {
                this.view.replaceContent();
            }

            // If there's no content defined, then by default the widget is disabled
            if (!content) {
                this.disable();
            }

            // Enable it if enabled is true or if there is content
            if (this.options.enabled === true || content) {
                this.enable();
            }
            if (content !== undefined) {
                this._empty = false;
            }
        },

        /**
         * Enables the accordion
         *
         * @method enable
         */
        enable: function () {
            this.enabled = true;
            this.getElement().removeModifier('disabled');
            this.view.getButton().setAttribute('title', 'Expand');
        },

        /**
         * Disables the accordion
         *
         * @method disable
         */
        disable: function () {
            this.enabled = false;
            this.getElement().setModifier('disabled');
            this.view.foldBody();
            this.view.getButton().setAttribute('title', 'Disabled');
        }

    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */
    /**
     * An event which is executed when clicked on the Accordion expand/collapse button
     *
     * @method _onButtonClick
     * @private
     */
    function _onButtonClick() {
        if (!this.enabled) {
            return;
        }

        if (this._expanded) {
            this.trigger('collapse');
        } else {
            this.trigger('beforeExpand');
            this.trigger('expand');
        }
    }

    /**
     * An event which is executed when the Accordion should expand
     *
     * @method _expandEvent
     * @private
     */
    function _expandEvent() {
        if (!this.enabled) {
            return;
        }

        this.view.expandBody();
        this._expanded = true;
    }

    /**
     * An event which is executed when the Accordion should collapse
     *
     * @method _collapseEvent
     * @private
     */
    function _collapseEvent() {
        this.view.foldBody();
        this._expanded = false;
    }

});

define('widgets/Accordion',['widgets/Accordion/Accordion'],function (main) {
                        return main;
                    });

