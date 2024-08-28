/* Copyright (c) Ericsson 2014 */

define('styles!widgets/Table/table/CheckboxHeaderCell/_checkboxHeaderCell.less',[],function () { return '.elWidgets-TableCheckboxHeaderCell {\n  width: 12px;\n}\n.elWidgets-TableCheckboxHeaderCell-wrap {\n  text-align: center;\n  width: 100%;\n  margin-left: -4px;\n}\n';});

define('text!widgets/Table/table/CheckboxHeaderCell/_checkboxHeaderCell.html',[],function () { return '<th class="elWidgets-TableCheckboxHeaderCell">\n    <div class="elWidgets-TableCheckboxHeaderCell-wrap">\n        <input class="ebCheckbox" type="checkbox" value="1"/>\n        <span class="ebCheckbox-inputStatus"></span>\n    </div>\n</th>';});

define('widgets/Table/table/CheckboxHeaderCell/CheckboxHeaderCellView',['jscore/core','text!./_checkboxHeaderCell.html','styles!./_checkboxHeaderCell.less'],function (core, template, styles) {
    'use strict';

    return core.View.extend({

        afterRender: function () {
            this.checkbox = this.getElement().find('.ebCheckbox');
        },

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return styles;
        },

        getCheckbox: function () {
            return this.checkbox;
        }

    });


});

define('widgets/Table/table/CheckboxHeaderCell/CheckboxHeaderCell',['widgets/table/Cell','./CheckboxHeaderCellView'],function (Cell, View) {
    'use strict';

    /**
     * Provides a header cell with a checkbox. When the checkbox is checked, all of the checkboxes in the same column
     * will be checked. This cell should be used in conjunction with the CheckboxCell.
     *
     * @class table.CheckboxHeaderCell
     * @extends table.Cell
     * @beta
     */
    return Cell.extend({

        View: View,

        onCellReady: function () {
            this.getEventBus().subscribe('CheckboxHeaderCell:calculate', calculateCheckStatus.bind(this));

            this.view.getCheckbox()._getHTMLElement().addEventListener('click', function () {
                var checked = this.view.getCheckbox().getProperty('checked');
                this.check(checked);
            }.bind(this));

            this.getEventBus().subscribe("collection:add", resetHandler.bind(this));
            this.getEventBus().subscribe("collection:reset", resetHandler.bind(this));
            this.getEventBus().subscribe("collection:remove", calculateCheckStatus.bind(this));
        },

        /**
         * Change the check state of the checkbox header. This also checks/unchecks all other checkboxes.
         *
         * @method check
         * @param {Boolean} checked
         */
        check: function(checked) {
            this.view.getCheckbox().setProperty('checked', checked);
            this.getEventBus().publish('CheckboxCell:check', checked);
        }

    });

    /**
     * Adding a new collection means that a new unchecked row has been added.
     * Therefore, header cell should be unchecked. Same for reset
     *
     * @method resetHandler
     * @private
     */
    function resetHandler() {
        /*jshint validthis:true */
        this.view.getCheckbox().setProperty('checked', false);
    }

    /**
     *
     * @method calculateCheckStatus
     * @private
     */
    function calculateCheckStatus() {
        /*jshint validthis:true */
        var rows = this.getTable().getRows();
        var checked = 0;
        var columnIndex = this.getColumn().getIndex();
        for (var i = 0; i < rows.length; i++) {
            if (rows[i].getCells()[columnIndex].isChecked()) {
                checked++;
            }
        }

        this.view.getCheckbox().setProperty('checked', checked === rows.length);
    }

});

define('widgets/table/CheckboxHeaderCell',['widgets/Table/table/CheckboxHeaderCell/CheckboxHeaderCell'],function (main) {
                        return main;
                    });

