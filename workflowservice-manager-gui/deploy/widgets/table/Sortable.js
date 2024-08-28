/* Copyright (c) Ericsson 2014 */

define('styles!widgets/Table/table/Sortable/_sortable.less',[],function () { return '.elWidgets-TableSortable {\n  position: relative;\n}\n.elWidgets-TableSortable-sorter {\n  position: absolute;\n  right: 0px;\n  top: -16px;\n}\n.elWidgets-TableSortable-arrow {\n  display: block;\n  opacity: 0.2;\n}\n.elWidgets-TableSortable-arrow_active {\n  opacity: 1;\n}\n';});

define('text!widgets/Table/table/Sortable/_sortable.html',[],function () { return '<div class="elWidgets-TableSortable">\n    <div class="elWidgets-TableSortable-sorter">\n        <i class="elWidgets-TableSortable-arrow ebIcon ebIcon_small ebIcon_upArrow_10px"></i>\n        <i class="elWidgets-TableSortable-arrow ebIcon ebIcon_small ebIcon_downArrow_10px"></i>\n    </div>\n</div>';});

define('widgets/Table/table/Sortable/SortableView',['jscore/core','text!./_sortable.html','styles!./_sortable.less'],function (core, template, styles) {
    'use strict';

    var SortableView =  core.View.extend({

        // TODO: Should be added to core.View and executed after render()
        afterRender: function () {
            var element = this.getElement();
            this.upArrow = element.find('.' + SortableView.EL_UP_ARROW_CLASS);
            this.downArrow = element.find('.' + SortableView.EL_DOWN_ARROW_CLASS);
            this.content = element.find('.elWidget-TableSortable-content');
        },

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return styles;
        },

        getUpArrow: function () {
            return this.upArrow;
        },

        getDownArrow: function () {
            return this.downArrow;
        },

        getContent: function() {
            return this.content;
        },

        setBothArrowsInactive: function () {
            this.getUpArrow().removeModifier('active');
            this.getDownArrow().removeModifier('active');
        },

        setUpArrowActive: function () {
            this.getUpArrow().setModifier('active');
            this.getDownArrow().removeModifier('active');
        },

        setDownArrowActive: function () {
            this.getUpArrow().removeModifier('active');
            this.getDownArrow().setModifier('active');
        }

    }, {
        EL_UP_ARROW_CLASS: 'ebIcon_upArrow_10px',
        EL_DOWN_ARROW_CLASS: 'ebIcon_downArrow_10px'
    });

    return SortableView;

});

define('widgets/Table/table/Sortable/Sortable',['widgets/WidgetCore','./SortableView'],function (WidgetCore, View) {
    'use strict';

    /**
     * Provides functionality for sorting columns.
     *
     * @class table.Sortable
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        View: View,

        init: function () {
            this._sortingMode = '';
        },

        click: function () {
            this.trigger('reset');
            this.sortHandler();
        },

        reset: function () {
            this.view.setBothArrowsInactive();
            this._sortingMode = '';
        },

        sortHandler: function () {
            if (this._sortingMode === '' || this._sortingMode === 'desc') {
                this._sortingMode = 'asc';
                this.view.setUpArrowActive();
            } else {
                this._sortingMode = 'desc';
                this.view.setDownArrowActive();
            }
            this.trigger('sort', this._sortingMode);
        }

    });

});

define('widgets/table/Sortable',['widgets/Table/table/Sortable/Sortable'],function (main) {
                        return main;
                    });

