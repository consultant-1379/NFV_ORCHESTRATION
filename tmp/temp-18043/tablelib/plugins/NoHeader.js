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

define('tablelib/plugins/noheader/NoHeader',['jscore/core','../Plugin'],function (core, Plugin) {

    return Plugin.extend({

        injections: {
            before: {
                onTableReady: onTableReady
            }
        }
    });


    function onTableReady() {
        var i;
        var table = this.getTable();

        // Iterate over the clone headers and change the styles
        var ths = table.getElement()._getHTMLElement().querySelectorAll("th");
        for (i = 0; i < ths.length; i++) {
            var th = ths[i];
            th.innerHTML = "";
            th.style.border = "none";
            th.style.paddingTop = 0;
            th.style.paddingBottom = 0;
        }

        // There's only a single TR that we care about
        var tr = table.getHeaderRow().getElement()._getHTMLElement();
        tr.style.height = 0;
    }

});

define('tablelib/plugins/NoHeader',['tablelib/plugins/noheader/NoHeader'],function (main) {
                        return main;
                    });

