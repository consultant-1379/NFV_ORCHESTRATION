/* Copyright (c) Ericsson 2014 */

define('styles!tablelib/table/Table.less',[],function () { return '.elTablelib-Table {\n  position: relative;\n  overflow: hidden;\n}\n.elTablelib-Table-table {\n  width: 100%;\n}\n';});

define('text!tablelib/table/Table.html',[],function () { return '<div class="elTablelib-Table">\n    <div class="elTablelib-Table-pretable"></div>\n    <div class="elTablelib-Table-wrapper eb_scrollbar">\n        <table class="ebTable elTablelib-Table-table">\n            <thead class="elTablelib-Table-header"></thead>\n            <tbody class="elTablelib-Table-body"></tbody>\n        </table>\n    </div>\n    <div class="elTablelib-Table-posttable"></div>\n</div>';});

define('tablelib/table/TableView',['jscore/core','text!./Table.html','styles!./Table.less'],function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        },

        getHeader: function() {
            return this.getElement().find(".elTablelib-Table-header");
        },

        getBody: function() {
            return this.getElement().find(".elTablelib-Table-body");
        },

        getTable: function() {
            return this.getElement().find(".elTablelib-Table-table");
        },

        getPreTable: function() {
            return this.getElement().find(".elTablelib-Table-pretable");
        },

        getPostTable: function() {
            return this.getElement().find(".elTablelib-Table-posttable");
        },

        getWrapper: function() {
            return this.getElement().find(".elTablelib-Table-wrapper");
        }

    });

});

define('tablelib/table/Table',['jscore/core','./TableView','tablelib/Cell','tablelib/Row','tablelib/HeaderCell','jscore/ext/utils/base/underscore'],function (core, View, Cell, Row, HeaderCell, _) {

    return core.Widget.extend({

        View: View,

        /**
         *
         *
         * @method init
         * @private
         */
        init: function() {
            this._headerRow = undefined;
            this._rows = [];

            // This has to be cloned to avoid conflict with future widgets/plugins
            this._columns = _.map(this.options.columns, _.clone);

            resolvePlugins.call(this, this.options.plugins);
        },

        /**
         *
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function() {

            addHeaderRow.call(this);

            if (this.options.data) {
                this.setData(this.options.data);
            }

            if (this.options.modifiers) {
                this.options.modifiers.forEach(function(modifier) {
                    this.view.getTable().setModifier(modifier.name, modifier.value);
                }.bind(this));
            }

        },

        /**
         *
         *
         * @method onDOMAttach
         * @private
         */
        onDOMAttach: function() {
            if (!this._tableReadyCalled) {
                this.onTableReady();
                this._tableReadyCalled = true;
            }
        },

        /**
         *
         * @method onTableReady
         * @private
         */
        onTableReady: function() {
        },

        /**
         * Clears the table and replaces the data with the passed data
         *
         * @method setData
         * @param {Array<Object>} data
         */
        setData: function(data) {
            this.clear();

            data.forEach(function(obj) {
                this.addRow(obj);
            }.bind(this));
        },

        /**
         * Destroys all rows in the table.
         *
         * @method clear
         */
        clear: function() {
            this._rows.forEach(function(row) {
                row.destroy();
            }.bind(this));

            this._rows = [];
        },

        /**
         * Adds the passed data as a row. If no index is specified, row is added to the bottom of the table.
         *
         * @method addRow
         * @param {Object} data
         * @param {Integer} index
         */
        addRow: function(obj, index) {
            var row = new Row({table: this, model: obj});

            if (index === undefined) {
                row.attachTo(this.view.getBody());
                this._rows.push(row);
            } else {
                attachRowAt.call(this, row, index);
                this._rows.splice(index, 0, row);
            }

            row.onRowReady({
                cellTypeProp: "cellType",
                fallbackCellType: Cell,
                model: obj,
                valueExp: function(column, cellSpec) {
                    return cellSpec.model[column.attribute];
                }
            });
        },

        /**
         * Removes the row at the specified index.
         *
         * @method removeRow
         * @param {Integer} index
         */
        removeRow: function(index) {
            this.getRows()[index].destroy();
            this._rows.splice(index, 1);
        },

        /**
         * Sets the width of the specified column.
         *
         * @method setColumnWidth
         * @param {Integer} index
         * @param {String} width
         */
        setColumnWidth: function(index, width) {
            this.getHeaderRow().getCells()[index].getElement().setStyle("width", width);
        },

        /**
         * Sets the width of the table.
         *
         * @method setWidth
         * @param {String} width
         */
        setWidth: function(width) {
            this.view.getTable().setStyle("width", width);
        },

        /**
         * Returns the rows attached to the table.
         *
         * @method getRows
         * @return {Array<Row>} rows
         */
        getRows: function() {
            return this._rows;
        },

        /**
         * Returns the header row attached to the table.
         *
         * @method getHeaderRow
         * @return {Row} row
         */
        getHeaderRow: function() {
            return this._headerRow;
        },

        /**
         * Returns the object data for the table.
         *
         * @method getData
         * @return {Array<Object>} data
         */
        getData: function() {
            var data = [];
            this.getRows().forEach(function(row) {
                data.push(row.getData());
            });

            return data;
        },

        /**
         * Sorts the table by detaching and reattaching rows.
         *
         * @method sort
         * @param {String} sortMode (asc/desc)
         * @param {String} attr
         */
        sort: function(sortMode, attr) {
            var multiplier = sortMode === "asc"? 1 : -1;
            this._rows.sort(function(a, b) {
                if (a.getData()[attr] > b.getData()[attr])
                    return 1 * multiplier;
                if (a.getData()[attr] < b.getData()[attr])
                    return -1 * multiplier;
                return 0;
            });

            this._rows.forEach(function(row) {
                row.detach();
                row.attach();
            });
        }

    });



    /**
     *
     *
     * @method addHeaderRow
     * @private
     */
    function addHeaderRow(columns) {
        this._headerRow = new Row({table: this});
        this._headerRow.attachTo(this.view.getHeader());
        this._headerRow.onRowReady({
            cellTypeProp: "headerCellType",
            fallbackCellType: HeaderCell,
            valueExp: function(column, model) {
                return column.title;
            }
        });
    }

    /**
     *
     *
     * @method resolvePlugins
     * @private
     */
    function resolvePlugins(plugins) {

        function injectFn(pluginInstance, name, code, isAfter) {
            var fn1 = isAfter? this[name] : code;
            var fn2 = isAfter? code : this[name];
            var ctx1 = isAfter? this : pluginInstance;
            var ctx2 = isAfter? pluginInstance : this;

            this[name] = function() {
                var hr = fn1.apply(ctx1, arguments);
                if (hr !== undefined) {
                    return hr;
                }

                return fn2.apply(ctx2, arguments);
            }.bind(this);
        }

        if (plugins) {
            plugins.forEach(function(plugin) {
                var fn;

                plugin._table = this;

                if (plugin.injections && plugin.injections.before) {
                    for (fn in plugin.injections.before) {
                        injectFn.call(this, plugin, fn, plugin.injections.before[fn]);
                    }
                }

                if (plugin.injections && plugin.injections.after) {
                    for (fn in plugin.injections.after) {
                        injectFn.call(this, plugin, fn, plugin.injections.after[fn], true);
                    }
                }

                if (plugin.injections && plugin.injections.newMethods) {
                    for (fn in plugin.injections.newMethods) {
                        this[fn] = plugin.injections.newMethods[fn].bind(plugin);
                    }
                }

            }.bind(this));
        }

    }

    /**
     *
     *
     * @method attachRowAt
     * @private
     */
    function attachRowAt(row, index) {
        var el = row.getElement()._getHTMLElement();
        var other = this.getRows()[index];
        var table = this.view.getBody()._getHTMLElement();

        if (other) {
            other = other.getElement()._getHTMLElement();
            table.insertBefore(el, other);
        } else {
            row.attachTo(this.view.getBody());
        }
    }

});

define('tablelib/Table',['tablelib/table/Table'],function (main) {
                        return main;
                    });

