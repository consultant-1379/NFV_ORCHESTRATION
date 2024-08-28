/* Copyright (c) Ericsson 2014 */

define('styles!widgets/Table/table/CheckboxCell/_checkboxCell.less',[],function () { return '.elWidgets-TableCheckboxCell {\n  width: 12px;\n}\n.elWidgets-TableCheckboxCell-wrap {\n  text-align: center;\n  width: 100%;\n  margin-left: -4px;\n}\n';});

define('text!widgets/Table/table/CheckboxCell/_checkboxCell.html',[],function () { return '<td class="elWidgets-TableCheckboxCell">\n    <div class="elWidgets-TableCheckboxCell-wrap">\n        <input class="ebCheckbox" type="checkbox" value="1"/>\n        <span class="ebCheckbox-inputStatus"></span>\n    </div>\n</td>';});

define('widgets/Table/table/CheckboxCell/CheckboxCellView',['jscore/core','text!./_checkboxCell.html','styles!./_checkboxCell.less'],function (core, template, styles) {
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

define('widgets/Table/table/CheckboxCell/CheckboxCell',['widgets/table/Cell','./CheckboxCellView'],function (Cell, View) {
    'use strict';

    /**
     * Provides a cell with a checkbox. When the checkbox is checked, the row will be highlighted. This cell should be
     * used in conjunction with the CheckboxHeaderCell.
     *
     * @class table.CheckboxCell
     * @extends table.Cell
     */
    return Cell.extend({

        View: View,

        onCellReady: function () {
            this.view.getCheckbox()._getHTMLElement().addEventListener('click', function (e) {
                if (e && e.stopPropagation) {
                    e.stopPropagation();
                }
                this.check(this.view.getCheckbox().getProperty('checked'));
            }.bind(this));

            this.getEventBus().subscribe('CheckboxCell:check', function (checked) {
                if (this.getRow().isHighlighted() !== checked) {
                    this.view.getCheckbox().setProperty('checked', checked);
                    this.getRow().highlight(checked);
                    this.getEventBus().publish('checkboxRowSelected', this.getRow());
                }
            }, this);
        },

        /**
         * Change the check state of the checkbox. This also unchecks the header cell and triggers the rowselect event.
         *
         * @method check
         * @param {Boolean} checked
         */
        check: function (checked) {
            this.view.getCheckbox().setProperty('checked', checked);
            this.getEventBus().publish('CheckboxHeaderCell:calculate');
            this.getRow().highlight(checked);
            this.getEventBus().publish('checkboxRowSelected', this.getRow());
        },

        isChecked: function() {
            return this.view.getCheckbox().getProperty('checked');
        }

    });

});

define('widgets/table/CheckboxCell',['widgets/Table/table/CheckboxCell/CheckboxCell'],function (main) {
                        return main;
                    });

