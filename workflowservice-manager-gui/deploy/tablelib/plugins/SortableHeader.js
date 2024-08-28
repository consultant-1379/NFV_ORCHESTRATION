/* Copyright (c) Ericsson 2014 */

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

define('tablelib/plugins/sortableheader/SortableHeader',['jscore/core','../Utils','../Plugin'],function (core, Utils, Plugin) {

    var template = "<span class='ebTable-headerSort ebSort'>" +
                        "<i class='ebSort-arrow_up'></i>" +
                        "<i class='ebSort-arrow_down'></i>" +
                    "</span>";


    return Plugin.extend({
        injections: {
            after: {
                onViewReady: onViewReady
            },

            newMethods: {
                disableSort: disableSort,
                enableSort: enableSort,
                resetSort: resetSort
            }
        }
    });

    function onViewReady() {
        this._enabled = true;

        this.getTable().getHeaderRow().getCells().forEach(function (cell) {

            if (cell.getColumnDefinition().sortable) {
                Utils.wrapHeaderContent(cell);

                var el = core.Element.parse(template);
                cell.getElement().find(".ebTable-header").append(el);
                cell.getElement()._getHTMLElement().classList.add("ebTable-th_sortable");

                el.addEventHandler("click", clickHandler.bind(this, cell));

                if (cell.getColumnDefinition().initialSort) {
                    setSort(cell, cell.getColumnDefinition().initialSort);
                }
            }

        }.bind(this));
    }

    function setSort(cell, sortDir) {
        if (sortDir === "asc") {
            cell.getElement().find(".ebSort-arrow_up").setModifier("active");
            cell.getElement().find(".ebSort-arrow_down").removeModifier("active");
        } else {
            cell.getElement().find(".ebSort-arrow_up").removeModifier("active");
            cell.getElement().find(".ebSort-arrow_down").setModifier("active");
        }
    }

    function resetSort() {
        // Remove the sort status of other cells
        this.getTable().getHeaderRow().getCells().forEach(function(cell) {
            if (cell.getColumnDefinition().sortable) {
                cell.getElement().find(".ebSort-arrow_up").removeModifier("active");
                cell.getElement().find(".ebSort-arrow_down").removeModifier("active");
            }
        });
    }

    function clickHandler(cell) {
        if (this._enabled) {
            if (this._currentAttr !== cell.getColumnDefinition().attribute) {
                this._currentSort = "asc";
                this._currentAttr = cell.getColumnDefinition().attribute;
            } else {
                this._currentSort = this._currentSort === "asc"? "desc" : "asc";
            }

            resetSort.call(this);

            // Then set the sort status of the clicked cell
            setSort(cell, this._currentSort);
            this.getTable().trigger("sort", this._currentSort, this._currentAttr);
        }
    }

    function enableSort() {
        __enableSort.call(this, true);
    }

    function disableSort() {
        __enableSort.call(this, false);
    }

    function __enableSort(value) {
        this.getTable().getHeaderRow().getCells().forEach(function(cell) {
            if (cell.getColumnDefinition().sortable) {
                cell.getElement().find(".ebSort").setStyle({
                    "cursor": value? "inherit" : "not-allowed"
                });
                this._enabled = value;
            }
        }.bind(this));
    }

});

define('tablelib/plugins/SortableHeader',['tablelib/plugins/sortableheader/SortableHeader'],function (main) {
                        return main;
                    });

