/* Copyright (c) Ericsson 2014 */

define('text!widgets/Tabs/TabItem/_tabItem.html',[],function () { return '<div class="ebTabs-tabItem"></div>';});

define('widgets/Tabs/TabItem/TabItemView',['jscore/core','text!./_tabItem.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        setCaption: function (caption) {
            this.getElement().setText(caption);
        },

        setSelected: function (selected) {
            if (selected) {
                this.getElement().setModifier('selected', 'true');
            } else {
                this.getElement().removeModifier('selected');
            }
        }

    });

});

define('widgets/Tabs/TabItem/TabItem',['jscore/core','./TabItemView'],function (core, View) {
    'use strict';

    return core.Widget.extend({

        View: View,

        onViewReady: function () {
            this.view.setCaption(this.options.caption);
        },

        getContent: function () {
            return this.options.content;
        },

        setSelected: function (selected) {
            this.view.setSelected(selected);
        }

    });

});

define('text!widgets/Tabs/_tabs.html',[],function () { return '<div class="ebTabs">\n    <div class="ebTabs-top">\n        <div class="ebTabs-leftBtn">\n            <div class="ebTabs-leftBtnIcon">\n                <i class="ebIcon ebIcon_small ebIcon_leftArrow_10px"></i>\n            </div>\n        </div>\n        <div class="ebTabs-tabArea"></div>\n        <div class="ebTabs-rightBtn">\n            <div class="ebTabs-rightBtnIcon">\n                <i class="ebIcon ebIcon_small ebIcon_rightArrow_10px"></i>\n            </div>\n        </div>\n    </div>\n    <div class="ebTabs-contentDiv"></div>\n</div>';});

define('widgets/Tabs/TabsView',['jscore/core','text!./_tabs.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getLeftButton: function () {
            return this.getElement().find('.ebTabs-leftBtn');
        },

        getRightButton: function () {
            return this.getElement().find('.ebTabs-rightBtn');
        },

        setButtonsDisplayType: function (displayType) {
            this.getLeftButton().setStyle('display', displayType);
            this.getRightButton().setStyle('display', displayType);
        },

        getTabArea: function () {
            return this.getElement().find('.ebTabs-tabArea');
        },

        getContent: function () {
            return this.getElement().find('.ebTabs-contentDiv');
        }

    });

});

define('widgets/Tabs/Tabs',['jscore/core','./TabsView','./TabItem/TabItem'],function (core, View, TabItem) {
    'use strict';

    /**
     * The Tabs class uses the Ericsson brand assets.<br>
     * The Spinner can be instantiated using the constructor Spinner.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>tabselect: Triggers when a tab has been clicked and selected. Passes the title and index of the tab as arguments.</li>
     *   </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>tabs: an array containing objects that may be used to create tabs. Options contain the following: title (string), content (string/widget)</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class Tabs
     * @extends WidgetCore
     */
    return core.Widget.extend({
        /*jshint validthis:true */

        View: View,

        /**
         * Loads the tabs and creates click event handlers for the buttons.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.view.setButtonsDisplayType('none');
            this.options.tabs = this.options.tabs || [];
            this.tabs = [];

            for (var i = 0; i < this.options.tabs.length; i++) {
                addTabImpl.call(this, this.options.tabs[i].title, this.options.tabs[i].content, i);
            }

            this._windowEvtId = core.Window.addEventHandler('resize', render.bind(this));

            var eventStart = !!('ontouchstart' in window) ? 'touchstart' : 'mousedown';
            var eventEnd = !!('ontouchstart' in window) ? 'touchend' : 'mouseup';


            this.view.getRightButton().addEventHandler(eventStart, function () {
                startButton.call(this, 5);
            }.bind(this));
            this.view.getRightButton().addEventHandler(eventEnd, endButton.bind(this));

            this.view.getLeftButton().addEventHandler(eventStart, function () {
                startButton.call(this, -5);
            }.bind(this));
            this.view.getLeftButton().addEventHandler(eventEnd, endButton.bind(this));
        },

        /**
         * Overrides method from widget.
         * Executes before destroy, remove event handlers.
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function () {
            if (this._windowEvtId) {
                core.Window.removeEventHandler(this._windowEvtId);
            }
        },

        /**
         * Re-renders the widget when it's actually attached to DOM
         *
         * @method onDOMAttach
         * @private
         */
        onDOMAttach: function () {
            render.call(this);

            if (this._previousTab === undefined && this.tabs.length > 0) {
                this.setSelectedTab(0);
            }
        },

        /**
         * This method adds a tab to the Tabs widget
         *
         * @method addTab
         *
         * @param {Object} title
         * @param {Object} content
         */
        addTab: function (title, content) {
            addTabImpl.call(this, title, content, this.tabs.length);
            render.call(this);
        },

        /**
         * This method clears all tabs stored in the Tabs widget
         *
         * @method clearTabs
         */
        clearTabs: function () {
            for (var i = 0; i < this.tabs.length; i++) {
                this.tabs[i].destroy();
            }
            this.tabs = [];
            if (this._previousContent) {
                this._previousContent.detach();
            }
            render.call(this);
        },

        /**
         * Backwards compatibility support.
         *
         * @method disable
         * @private
         */
        disable: function () {

        },

        /**
         * Backwards compatibility support.
         *
         * @method enable
         * @private
         */
        enable: function () {

        },

        /**
         * This method removes the last tab stored in the Tabs widget
         *
         * @method removeLastTab
         */
        removeLastTab: function () {
            this.removeTab(this.tabs.length - 1);
        },

        /**
         * This method removes the tab stored at the specified index in the Tabs widget
         *
         * @method removeTab
         * @param {int} tabIndex
         */
        removeTab: function (tabIndex) {
            var tab = this.tabs[tabIndex];

            if (tab === this._previousTab) {
                this._previousContent.detach();
                if (this.tabs.length > 1) {
                    this.setSelectedTab(tabIndex - 1);
                }
            }

            tab.destroy();
            this.tabs.splice(tabIndex, 1);
            render.call(this);
        },

        /**
         * This method sets the selected tab in the Tabs widget.
         *
         * @method setSelectedTab
         * @param {int} tabIndex
         */
        setSelectedTab: function (tabIndex) {
            var tab = tabIndex instanceof TabItem ? tabIndex : this.tabs[tabIndex];
            if (this._previousTab) {
                this._previousContent.detach();
                this._previousTab.setSelected(false);
            }
            if (tab.getContent() instanceof core.Element) {
                this.view.getContent().append(tab.getContent());
            } else {
                tab.getContent().attachTo(this.view.getContent());
            }

            tab.setSelected(true);
            this._previousTab = tab;
            this._previousContent = tab.getContent();
        }

    });

    /**
     * Instantiates the tab and gives it a click event
     *
     * @method addTabImpl
     * @private
     *
     * @param {String} title
     * @param {Object} content
     * @param {int} index
     */
    function addTabImpl(title, content, index) {
        if (typeof content === 'string') {
            content = core.Element.parse(content);
        }

        var tab = new TabItem({
            caption: title,
            content: content
        });

        tab.getElement().addEventHandler('click', function () {
            this.setSelectedTab(tab);
            this.trigger('tabselect', title, index);
        }.bind(this));

        tab.attachTo(this.view.getTabArea());

        this.tabs.push(tab);
    }

    /**
     * When mousedown the left/right button, this function is called, and starts an interval
     *
     * @method startButton
     * @private
     *
     * @param {int} delta
     */
    function startButton(delta) {
        if (this.buttonInterval) {
            endButton.call(this);
        }
        this.buttonInterval = setInterval(function () {
            buttonAction.call(this, delta);
        }.bind(this), 1000 / 60);
    }

    /**
     * When mouseup the left/right button, this function is called, and ends an interval
     *
     * @method endButton
     * @private
     */
    function endButton() {
        clearInterval(this.buttonInterval);
        delete this.buttonInterval;
    }

    /**
     * The implementation for the left/right button. Scrolls the tab area.
     *
     * @method buttonAction
     * @private
     *
     * @param {int} delta
     */
    function buttonAction(delta) {
        var oldScrollLeft = this.view.getTabArea().getProperty('scrollLeft');
        this.view.getTabArea().setProperty('scrollLeft', oldScrollLeft + delta);
        enableButtonsViaScroll.call(this);
    }

    /**
     * Checks the scrollLeft of the tab area. If at an edge, it will enable/disable buttons.
     *
     * @method enableButtonsViaScroll
     * @private
     */
    function enableButtonsViaScroll() {
        var newScrollLeft = this.view.getTabArea().getProperty('scrollLeft');
        var scrollWidth = this.view.getTabArea().getProperty('scrollWidth');
        var tabAreaWidth = this.view.getTabArea().getProperty('clientWidth');
        if (newScrollLeft <= 0) {
            this.view.getLeftButton().setModifier('enabled', 'false');
            this.view.getRightButton().removeModifier('enabled');
        } else if (newScrollLeft >= scrollWidth - tabAreaWidth) {
            this.view.getRightButton().setModifier('enabled', 'false');
            this.view.getLeftButton().removeModifier('enabled');
        } else {
            this.view.getLeftButton().removeModifier('enabled');
            this.view.getRightButton().removeModifier('enabled');
        }
    }

    /**
     * Calculates the total width of the tab and determines whether or not to show buttons
     *
     * @method render
     * @private
     */
    function render() {
        var visibleWidth = this.getElement().getProperty('offsetWidth');
        var tabEls = this.view.getTabArea().children();

        if (tabEls.length > 0) {
            tabEls[0].removeModifier('responsive');
            tabEls[Math.max(0, tabEls.length - 2)].removeModifier('responsive');
            tabEls[Math.max(0, tabEls.length - 1)].removeModifier('responsive');
        }

        var totalTabWidth = 0;
        for (var i = 0; i < this.tabs.length; i++) {
            totalTabWidth += this.tabs[i].getElement().getProperty('offsetWidth');
        }

        if (totalTabWidth > visibleWidth) {
            this.view.setButtonsDisplayType('block');
            this.view.getTabArea().setModifier('responsive');

            if (tabEls.length > 0) {
                tabEls[0].setModifier('responsive');
                tabEls[tabEls.length - 1].setModifier('responsive');
            }

        } else {
            this.view.setButtonsDisplayType('none');
            this.view.getTabArea().removeModifier('responsive');
        }

        enableButtonsViaScroll.call(this);
    }

});

define('widgets/Tabs',['widgets/Tabs/Tabs'],function (main) {
                        return main;
                    });

