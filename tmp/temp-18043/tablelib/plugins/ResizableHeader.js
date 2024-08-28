/* Copyright (c) Ericsson 2014 */

define('text!tablelib/plugins/resizableheader/ResizableHeader.html',[],function () { return '<div class="ebTable-headerResize"></div>';});

define('text!tablelib/plugins/WrappedHeader.html',[],function () { return '<th>\n    <div class="ebTable-header">\n      <span class="ebTable-headerText"></span>\n    </div>\n</th>';});

define('tablelib/plugins/Utils',['jscore/core','text!./WrappedHeader.html'],function (core, wrappedHeaderTemplate) {

    return {
        wrapHeaderContent: function(cell) {
            var cellRoot = cell.getElement()._getHTMLElement();
            var className = "elTables-wrappedHeader";

            if (!cellRoot.classList.contains(className)) {

                var newElement = core.Element.parse(wrappedHeaderTemplate);
                var newContent = newElement.find(".ebTable-headerText")._getHTMLElement();

                // Transfer content over from original cell to new content holder
                while (cellRoot.hasChildNodes()) {
                    newContent.appendChild(cellRoot.removeChild(cellRoot.firstChild));
                }

                // Transfer the content of newElement to cell
                newElement = newElement._getHTMLElement();
                while (newElement.hasChildNodes()) {
                    cellRoot.appendChild(newElement.removeChild(newElement.firstChild));
                }

                cellRoot.classList.add(className);

            }
        },

        preventDefault: function(e) {
            e.preventDefault();
        }
    };


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

define('tablelib/plugins/resizableheader/ResizableHeader',['jscore/core','../Plugin','../Utils','text!./ResizableHeader.html','jscore/ext/utils/base/underscore'],function (core, Plugin, Utils, template, _) {

    // This number should never be changing, so static is fine
    var scrollbarWidth = getScrollbarWidth();

    return Plugin.extend({

        injections: {
            after: {
                onTableReady: onTableReady
            }
        }
    });

    function onTableReady() {
        var noWidthCells = [];

        this.getTable().getHeaderRow().getCells().forEach(function (cell) {

            if (cell.getColumnDefinition().resizable) {
                Utils.wrapHeaderContent(cell);
                var el = core.Element.parse(template);
                cell.getElement().find(".ebTable-header").append(el);
                cell.getElement()._getHTMLElement().classList.add("ebTable-th_resizable");
                el.addEventHandler("mousedown", onMouseDown.bind(cell));
            }

            // This way is more reliable than using the getStyle method.
            // We want elements for which no CSS style has been applied
            if (!cell.getElement()._getHTMLElement().style.width) {
                noWidthCells.push(cell);
            }

        }.bind(this));

        // For each of the noWidth cells, we want their offset width, and to evenly subtract the scrollbar.
        // This means that all auto cells should still be equal in length.
        noWidthCells.forEach(function(cell) {
            var width = cell.getElement().getProperty("offsetWidth");
            this.getTable().setColumnWidth(cell.getIndex(), (width - Math.ceil(scrollbarWidth / noWidthCells.length)) + "px");
        }.bind(this));

        this.getTable().view.getWrapper().setStyle("max-width", "100%");
        this.getTable().setWidth("0"); // Hack needed, columns must have pre-defined widths
        calculateAndSetTableWidth.call(this);
    }

    function calculateAndSetTableWidth() {
        // We're setting the table wrapper to the width of the table + scrollbar
        // This allows fixed header to show a scrollbar without having a horizontal scrollbar
        var totalWidth = this.getTable().view.getTable().getProperty("offsetWidth") + scrollbarWidth;
        this.getTable().view.getWrapper().setStyle("width", totalWidth + "px");
    }

    function onMouseDown(e) {
        document.addEventListener("mousemove", Utils.preventDefault, false);
        this._startX = e.originalEvent.pageX;
        this._startW = this.getElement().getProperty("offsetWidth");
        this._mouseMoveEvent = core.Element.wrap(document.body).addEventHandler("mousemove", onMouseMove.bind(this));
        this._mouseUpEvent = core.Element.wrap(document.body).addEventHandler("mouseup mouseleave", onMouseEnd.bind(this));
    }

    function onMouseMove(e) {
        var width = Math.max(60, this._startW - (this._startX - e.originalEvent.pageX));
        this.getTable().setColumnWidth(this.getIndex(), width + "px");
        calculateAndSetTableWidth.call(this);
    }

    function onMouseEnd() {
        core.Element.wrap(document.body).removeEventHandler(this._mouseUpEvent);
        core.Element.wrap(document.body).removeEventHandler(this._mouseMoveEvent);
        document.removeEventListener("mousemove", Utils.preventDefault, false);

        // Cloning the event data because we don't want people modifying it
        var eventData = _.clone(this.getColumnDefinition());
        eventData.width = this.getElement().getProperty("offsetWidth") + "px";
        var table = this.getTable();

        // Need to skip frame so that changes get applied
        requestAnimationFrame(function() {
            requestAnimationFrame(function() {
                table.trigger("columnresize", eventData);
            });
        });

    }

    function getScrollbarWidth() {
        var outer = document.createElement("div");
        outer.style.visibility = "hidden";
        outer.style.width = "100px";
        document.body.appendChild(outer);
        var widthNoScroll = outer.offsetWidth;
        outer.setAttribute("class", "eb_scrollbar");
        outer.style.overflow = "scroll";
        var inner = document.createElement("div");
        inner.style.width = "100%";
        outer.appendChild(inner);
        var widthWithScroll = inner.offsetWidth;
        outer.parentNode.removeChild(outer);
        return widthNoScroll - widthWithScroll;
    }

});

define('tablelib/plugins/ResizableHeader',['tablelib/plugins/resizableheader/ResizableHeader'],function (main) {
                        return main;
                    });

