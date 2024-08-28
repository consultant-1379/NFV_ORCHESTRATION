define([
    "jscore/core",
    "./Plugin",
    'tablelib/HeaderCell',
    'tablelib/Row'
], function (core, Plugin, HeaderCell, Row) {

    return Plugin.extend({
        injections: {
            after: {
                onViewReady: onViewReady
            }
        }
    });

    function onViewReady() {

        var row = new Row({table: this.getTable()});
        row.onRowReady({
            cellTypeProp: "secondHeaderCellType",
            fallbackCellType: HeaderCell,
            valueExp: function(column, cellSpec) {
                return "";
            }
        });

        row.attachTo(this.getTable().getElement().find("thead"));
    }

});