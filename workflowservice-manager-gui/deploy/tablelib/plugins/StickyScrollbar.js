/* Copyright (c) Ericsson 2014 */

define('styles!tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidget.less',[],function () { return '.elTablelib-StickyScrollbarWidget {\n  position: fixed;\n  overflow-x: scroll;\n  bottom: 0;\n}\n.elTablelib-StickyScrollbarWidget-fakeContent {\n  height: 1px;\n}\n';});

define('text!tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidget.html',[],function () { return '<div class="elTablelib-StickyScrollbarWidget eb_scrollbar">\n    <div class="elTablelib-StickyScrollbarWidget-fakeContent"></div>\n</div>';});

define('tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidgetView',['jscore/core','text!./StickyScrollbarWidget.html','styles!./StickyScrollbarWidget.less'],function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        },

        getFakeContent: function() {
            return this.getElement().find(".elTablelib-StickyScrollbarWidget-fakeContent");
        }

    });

});

define('tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidget',['jscore/core','./StickyScrollbarWidgetView'],function (core, View) {

    return core.Widget.extend({

        View: View,

        onDOMAttach: function() {

            // Shortcuts (tg for target)
            this.tg = this.options.element;
            this.el = this.getElement();

            // Set bi-directional scroll events to keep the real bar in sync.
            // The original scrollbar should not be disabled because of touch devices.
            this.el.addEventHandler("scroll", function() {
                this.tg.setProperty("scrollLeft", this.el.getProperty("scrollLeft"));
            }.bind(this));

            this.tg.addEventHandler("scroll", function() {
                this.el.setProperty("scrollLeft", this.tg.getProperty("scrollLeft"));
            }.bind(this));

            // Start the animation loop
            requestAnimationFrame(this.update.bind(this));
        },

        update: function() {

            var tgPos = this.tg.getPosition();
            var elPos = this.el.getPosition();

            // This logic only applies if it's actually visible on screen and required
            if (tgPos.bottom > window.innerHeight && this.tg.getProperty("scrollWidth") > this.el.getProperty("offsetWidth")) {

                // Show it if appropriate
                if (this.el.getStyle("visibility") !== "visible") {
                    this.el.setStyle({
                        "visibility": "visible",
                        "pointer-events": "auto"
                    });
                }

                // If a panel or something similar was to move in from the left, the fixed position will be wrong
                if (elPos.left !== tgPos.left) {
                    this.el.setStyle("left", tgPos.left + "px");
                }

                 // If a panel was to come in from the right, the width of the element may change, therefore update this element
                if (this.el.getProperty("offsetWidth") !== this.tg.getProperty("offsetWidth")) {
                    this.el.setStyle("width", this.tg.getProperty("offsetWidth") + "px");
                }

                // If the element we're tracking was for some reason to get wider (Resizable columns), then update the fake content width
                if (this.view.getFakeContent().getProperty("offsetWidth") !== this.tg.getProperty("scrollWidth")) {
                    this.view.getFakeContent().setStyle("width", this.tg.getProperty("scrollWidth") + "px");
                }

            } else {

                // Not required, hide it. It's important to use visibility here over display none
                // because display none will make the above condition true resulting in a loop.
                if (this.el.getStyle("visibility") === "visible") {
                    this.el.setStyle({
                        "visibility": "hidden",
                        "pointer-events": "none"
                    });
                }
            }

            requestAnimationFrame(this.update.bind(this));
        }


    });

});

define('tablelib/plugins/Plugin',['jscore/core'],function (core) {

    var Plugin = function(options) {
        this.options = options || {};
        this.init.apply(this, arguments);
    };

    Plugin.prototype.init = function(){};
    Plugin.prototype.getOptions = function() {
        return this.options;
    };

    Plugin.prototype.getTable = function() {
        return this._table;
    };
    Plugin.prototype.injections = {
        before: {},
        after: {},
        newMethods: {}
    };

    Plugin.extend = core.extend;

    return Plugin;

});

define('tablelib/plugins/stickyscrollbar/StickyScrollbar',['../Plugin','./stickyscrollbarwidget/StickyScrollbarWidget'],function (Plugin, StickyScrollbarWidget) {

    return Plugin.extend({

        injections: {
            before: {
                onViewReady: onViewReady
            }
        }

    });

    function onViewReady() {
        var table = this.getTable();

        var scrollbar = new StickyScrollbarWidget({
            element: this.getTable().getElement().find(".eb_scrollbar")
        });
        scrollbar.attachTo(this.getTable().getElement());


    }

});

define('tablelib/plugins/StickyScrollbar',['tablelib/plugins/stickyscrollbar/StickyScrollbar'],function (main) {
                        return main;
                    });

