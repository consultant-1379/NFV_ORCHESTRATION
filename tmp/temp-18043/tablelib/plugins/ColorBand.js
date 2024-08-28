/* Copyright (c) Ericsson 2014 */

define('tablelib/plugins/colorband/colorbandheadercell/ColorBandHeaderCell',['tablelib/HeaderCell','jscore/core'],function (HeaderCell, core) {

    return HeaderCell.extend({

        View: core.View.extend({

            getTemplate: function() {
                return "<th style=' box-sizing: border-box; border-right: none; padding-left: 0; padding-right: 0; '></th>";
            }

        })

    });

});

define('tablelib/plugins/colorband/colorbandcell/ColorBandCell',['tablelib/Cell','jscore/core'],function (Cell, core) {

    return Cell.extend({

        View: core.View.extend({

            getTemplate: function() {
                return "<td style=' box-sizing: border-box; border-right: none; padding-left: 0; padding-right: 0; '></td>";
            }

        }),

        setColor: function(color) {
            this.getElement().setStyle("background-color", color);
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

define('tablelib/plugins/colorband/ColorBand',['../Plugin','jscore/core','./colorbandcell/ColorBandCell','./colorbandheadercell/ColorBandHeaderCell'],function (Plugin, core, ColorBandCell, ColorBandHeaderCell) {

    return Plugin.extend({

        injections: {
            before: {
                onViewReady: onViewReady
            },

            after: {
                addRow: addRow
            },

            newMethods: {
                updateColorBands: updateColorBands
            }
        }

    });

    function onViewReady() {
        this.getTable()._columns.unshift({
            cellType: ColorBandCell,
            headerCellType: ColorBandHeaderCell,
            width: "4px"
        });
    }

    function addRow(obj, index) {
        var rows = this.getTable().getRows();
        var row = rows[index !== undefined? index : rows.length - 1];
        row.getCells()[0].setColor(this.options.color(row));
    }

    function updateColorBands() {
        this.getTable().getRows().forEach(function (row) {
            row.getCells()[0].setColor(this.options.color(row));
        }.bind(this));
    }

});

define('tablelib/plugins/ColorBand',['tablelib/plugins/colorband/ColorBand'],function (main) {
                        return main;
                    });

