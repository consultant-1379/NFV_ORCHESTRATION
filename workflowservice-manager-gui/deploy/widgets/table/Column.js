/* Copyright (c) Ericsson 2014 */

define('styles!widgets/Table/table/Column/_column.less',[],function () { return '.elWidgets-TableColumn {\n  -webkit-box-sizing: border-box;\n  -moz-box-sizing: border-box;\n  box-sizing: border-box;\n  width: auto;\n}\n';});

define('text!widgets/Table/table/Column/_column.html',[],function () { return '<col class="elWidgets-TableColumn"/>';});

define('widgets/Table/table/Column/ColumnView',['jscore/core','text!./_column.html','styles!./_column.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return styles;
        }

    });

});

define('widgets/Table/table/Column/Column',['widgets/WidgetCore','./ColumnView'],function (WidgetCore, View) {
    'use strict';

    /**
     * Column class. Contains functions that provide information about the column.
     *
     * @class table.Column
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        View: View,

        onViewReady: function () {
            if (this.options.definition.width) {
                this.view.getElement().setStyle('width', this.options.definition.width);
            }
        },

        /**
         * Returns the title of the column
         *
         * @method getTitle
         * @return {String} title
         */
        getTitle: function () {
            return this.options.definition.title || '';
        },

        /**
         * Returns the index of the column
         *
         * @method getIndex
         * @return {Integer} index
         */
        getIndex: function () {
            return this.options.index;
        },

        /**
         * Returns the model attribute of the column
         *
         * @method getAttribute
         * @return {String} attribute
         */
        getAttribute: function () {
            return this.options.definition.attribute;
        },

        /**
         * Returns the original definition the developer specified for the column
         *
         * @method getDefinition
         * @return {Object} definition
         */
        getDefinition: function () {
            return this.options.definition;
        }

    });

});

define('widgets/table/Column',['widgets/Table/table/Column/Column'],function (main) {
                        return main;
                    });

