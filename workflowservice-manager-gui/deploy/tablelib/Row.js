/* Copyright (c) Ericsson 2014 */

define('tablelib/row/Row',['jscore/core','tablelib/Cell','tablelib/HeaderCell'],function (core, Cell, HeaderCell) {

    return core.Widget.extend({

        View: core.View.extend({

            getTemplate: function() {
                return "<tr class='ebTableRow'></tr>";
            }

        }),

        init: function() {
            this.cells = [];
        },

        onRowReady: function(cellSpec) {
            var columns = this.getTable()._columns;

            for (var i = 0; i < columns.length; i++) {
                var col = columns[i];
                if (col.visible !== false) {
                    addCell.call(this, cellSpec, col);
                }

            }
        },

        /**
         * Returns all of the cells inside the row
         *
         * @method getCells
         * @return {Array<Cell>} cells
         */
        getCells: function() {
            return this.cells;
        },

        /**
         * Returns the object data for the row
         *
         * @method getData
         * @return {Object} data
         */
        getData: function() {
            return this.options.model;
        },

        /**
         * Returns the table instance containing this row
         *
         * @method getTable
         * @return {Table} table
         */
        getTable: function() {
            return this.options.table;
        },

        /**
         * Returns the index of the row
         *
         * @method getIndex
         * @return {Integer} integer
         */
        getIndex: function() {
            // TODO : Figure out optimisation
            var tableRows = this.getTable().getRows();
            for (var i = 0; i < tableRows.length; i++) {
                if (tableRows[i] === this) {
                    return i;
                }
            }
        },

        /**
         * Removes the row safely from the table.
         *
         * @method remove
         */
        remove: function() {
            this.getTable().removeRow(this.getIndex());
        },

        onDestroy: function() {
            this.cells.forEach(function(cell) {
                cell.destroy();
            });

        }

    });

    /**
     *
     *
     * @method addCell
     * @private
     */
    function addCell(cellSpec, def) {
        var cellClass = def[cellSpec.cellTypeProp] || cellSpec.fallbackCellType;
        var c = new cellClass({column: def, index: this.cells.length, table: this.getTable(), row: this});
        c.attachTo(this.getElement());
        this.cells.push(c);
        c.onCellReady();

        if (cellSpec.valueExp) {
            var value = cellSpec.valueExp(def, cellSpec);
            c.setValue(value);

            if (this.getTable().options.tooltips) {
                c.setTooltip(value);
            }
        }


    }

});

define('tablelib/Row',['tablelib/row/Row'],function (main) {
                        return main;
                    });

