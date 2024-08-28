/* Copyright (c) Ericsson 2014 */

define('text!widgets/Table/table/HeaderCell/_headerCell.html',[],function () { return '<th></th>';});

define('widgets/Table/table/HeaderCell/HeaderCellView',['jscore/core','text!./_headerCell.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getBody: function () {
            return this.getElement();
        },

        setValue: function (value) {
            this.getBody().setText(value);
        },

        getValue: function () {
            return this.getBody().getText();
        }

    });

});

define('widgets/Table/table/HeaderCell/HeaderCell',['widgets/table/Cell','./HeaderCellView'],function (Cell, View) {
    'use strict';

    /**
     * Basic HeaderCell class.
     *
     * @class table.HeaderCell
     * @extends table.Cell
     */
    return Cell.extend({

        View: View,

        onViewReady: function () {
            this.setValue(this.options.column.getTitle());
            this.onCellReady();
        }

    });

});

define('widgets/table/HeaderCell',['widgets/Table/table/HeaderCell/HeaderCell'],function (main) {
                        return main;
                    });

