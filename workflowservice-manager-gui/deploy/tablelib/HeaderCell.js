/* Copyright (c) Ericsson 2014 */

define('tablelib/headercell/HeaderCell',['tablelib/Cell','jscore/core'],function (Cell, core) {

    return Cell.extend({

        View: core.View.extend({

            getTemplate: function() {
                return "<th style='box-sizing:border-box'></th>";
            }

        }),

        onCellReady: function() {
            if (this.getColumnDefinition().width) {
                this.getTable().setColumnWidth(this.getIndex(), this.getColumnDefinition().width);
            }
        }

    });

});

define('tablelib/HeaderCell',['tablelib/headercell/HeaderCell'],function (main) {
                        return main;
                    });

