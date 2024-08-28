/* Copyright (c) Ericsson 2014 */

define('tablelib/cell/Cell',['jscore/core'],function (core) {

    return core.Widget.extend({

        View: core.View.extend({
            getTemplate: function() {
                return "<td></td>";
            }
        }),

        /**
         * Called when the Cell is instantiated. Can be overrided.
         *
         * @method onCellReady
         */
        onCellReady: function() {
        },

        /**
         * Called when the Cell is destroyed. Can be overrided.
         *
         * @method onCellDestroy
         */
        onCellDestroy: function() {
        },

        /**
         * Called when the Cell is instantiated. Can be overrided.
         *
         * @method setValue
         * @param value
         */
        setValue: function(value) {
            this.getElement().setText(value);
        },

        /**
         * Called when the Cell is instantiated if tooltips are enabled. Can be overrided.
         *
         * @method setTooltip
         * @param value
         */
        setTooltip: function(value) {
            this.getElement().setAttribute("title", value);
        },


        /**
         * Returns reference to the table that holds this cell
         *
         * @method getTable
         * @return {Table} table
         */
        getTable: function() {
            return this.options.table;
        },

        /**
         * Returns reference to the row that holds this cell
         *
         * @method getRow
         * @return {Row} row
         */
        getRow: function() {
            return this.options.row;
        },

        /**
         * Returns the column index for the cell
         *
         * @method getIndex
         * @return {Integer} index
         */
        getIndex: function() {
            return this.options.index;
        },

        /**
         * Returns the column definition for the column that holds this cell
         *
         * @method getColumnDefinition
         * @return {Object} columnDefinition
         */
        getColumnDefinition: function() {
            return this.options.column;
        },

        onDestroy: function() {
            this.onCellDestroy();
        }

    });

});

define('tablelib/Cell',['tablelib/cell/Cell'],function (main) {
                        return main;
                    });

