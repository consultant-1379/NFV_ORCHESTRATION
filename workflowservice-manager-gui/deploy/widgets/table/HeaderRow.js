/* Copyright (c) Ericsson 2014 */

define('text!widgets/Table/table/HeaderRow/_headerRow.html',[],function () { return '<thead>\n    <tr></tr>\n</thead>\n';});

define('widgets/Table/table/HeaderRow/HeaderRowView',['jscore/core','text!./_headerRow.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getBody: function () {
            return this.getElement().children()[0];
        }

    });

});

define('widgets/Table/table/HeaderRow/HeaderRow',['widgets/table/Row','./HeaderRowView'],function (Row, View) {
    'use strict';

    /**
     * Basic HeaderRow class
     *
     * @class table.HeaderRow
     * @extends table.Row
=     */
    return Row.extend({

        View: View

    });

});

define('widgets/table/HeaderRow',['widgets/Table/table/HeaderRow/HeaderRow'],function (main) {
                        return main;
                    });

