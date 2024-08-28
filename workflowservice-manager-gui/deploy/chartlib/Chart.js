/* Copyright (c) Ericsson 2014 */

define('chartlib/Chart',['jscore/core','chartlib/base/d3'],function (core, d3) {

    return core.Widget.extend({

        View: core.View.extend({
            getTemplate: function() {
                // cross-browser approach of getting scaling working
                return "<div><svg width='100%' height='100%'></svg></div>";
            }
        }),

        /**
         * Sets up resize event, parses width and height if given, and calls onChartReady
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function() {

            if (!this.redraw) {
                throw new Error("redraw() method must be implemented");
            }

            this.resizeEvent = core.Window.addEventHandler("resize", this.resize.bind(this));

            this._width = this.options.width;
            this._height = this.options.height;
            this.onChartReady(this.options);
        },

        /**
         * Triggers a resize
         *
         * @method onDOMAttach
         * @private
         */
        onDOMAttach: function() {
            this._attachedToDOM = true;
            this.resize();
        },

        /**
         * Removes the event handler added by the onViewReady method
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function() {
            core.Window.removeEventHandler(this.resizeEvent);
        },

        /**
         * Returns a wrapped SVG tag as a d3 object.
         *
         * @method getD3Element
         * @return {D3Element} d3Element
         */
        getD3Element: function() {
            if (!this._svg) {
                this._svg = this.getElement().find("svg")._getHTMLElement();
            }
            return d3.select(this._svg);
        },

        /**
         * Returns an object with the real dimensions of the chart
         *
         * @method getSize
         * @return {Object} width/height
         */
        getSize: function() {
            return {
                width: this.getElement().getProperty("offsetWidth"),
                height: this.getElement().getProperty("offsetHeight")
            };
        },

        /**
         * Changes the dimensions of the chart to the specified width/height. Starting value is set to 100% for both.
         * Calls redraw if there are new dimensions set.
         *
         * @method resize
         * @param {String} width
         * @param {String} height
         */
        resize: function(width, height) {
            if (width !== undefined) {
                this._width = width;
            }

            if (height !== undefined) {
                this._height = height;
            }

            this.getElement().setStyle({
                width: this._width || "100%",
                height: this._height || "100%"
            });

            var cs = this.getSize();
            var ps = this._previousSize;

            if ((!ps || ps.width !== cs.width || ps.height !== cs.height) && this._attachedToDOM) {
                this.redraw();
                this._previousSize = cs;
            }


        },

        /**
         * Called when the chart is instantiated. Can be overrided.
         *
         * @method onChartReady
         */
        onChartReady: function() {
        },

        /**
         * Called when the chart needs to be drawed. Must be overrided.
         *
         * @method redraw
         */
         // Do not implement this method

        /**
         * Call to extend the chart with custom functions.
         *
         * @method extend
         * @static
         * @param {Object} definition
         * @return {Object} extendedClass
         */

        /**
         * Bind an event handling function to an event.
         *
         * @method addEventHandler
         * @param {String} eventName
         * @param {Function} fn
         * @return {String} id
         *
         * @example
         *   widget.addEventHandler("widgetEvent", function() {
         *     console.log("Widget Event");
         *   });
         */

        /**
         * Unbind an event handling function from an event.
         *
         * @method removeEventHandler
         * @param {String} eventName
         * @param {String} id
         *
         * @example
         *   widget.removeEventHandler("widgetEvent", id);
         */

        /**
         * Adds the Widget's element to the new parent element.
         *
         * @method attachTo
         * @param {Element} parent
         *
         * @example
         *   widget.attachTo(this.getElement());
         */

        /**
         * Places the detached Widget back into the defined parent element.
         *
         * @method attach
         *
         * @example
         *   widget.attach();
         */

        /**
         * Removes the Widget from the parent element, but does not destroy the Widget. DOM events will still work when Widget is attached back.
         *
         * @method detach
         *
         * @example
         *   widget.detach();
         */

        /**
         * Removes the Widget root Element from the DOM.
         *
         * @method destroy
         *
         * @example
         *   widget.destroy();
         */
    });

});

