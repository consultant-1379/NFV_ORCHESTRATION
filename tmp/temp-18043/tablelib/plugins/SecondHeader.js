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

define('tablelib/plugins/secondheader/SecondHeader',['jscore/core','../Plugin','tablelib/HeaderCell','tablelib/Row'],function (core, Plugin, HeaderCell, Row) {

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

define('tablelib/plugins/SecondHeader',['tablelib/plugins/secondheader/SecondHeader'],function (main) {
                        return main;
                    });

