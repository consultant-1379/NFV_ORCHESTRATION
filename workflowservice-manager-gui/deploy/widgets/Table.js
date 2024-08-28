/* Copyright (c) Ericsson 2014 */

define('styles!widgets/Table/_table.less',[],function () { return '.elWidgets-Table {\n  position: relative;\n  width: 100%;\n  height: 100%;\n  overflow-y: hidden;\n  overflow-x: auto;\n  -webkit-overflow-scrolling: auto;\n}\n.elWidgets-Table-responsiveWrapper,\n.elWidgets-Table-body {\n  width: 100%;\n}\n';});

define('text!widgets/Table/_table.html',[],function () { return '<div class="elWidgets-Table eb_scrollbar">\n    <div class="elWidgets-Table-responsiveWrapper">\n        <table class="ebTable elWidgets-Table-body">\n            <colgroup class="ebTable-columns"></colgroup>\n        </table>\n    </div>\n</div>';});

define('widgets/Table/TableView',['jscore/core','text!./_table.html','styles!./_table.less'],function (core, template, style) {
    'use strict';

    var TableView = core.View.extend({

        afterRender: function () {
            var element = this.getElement();
            this.table = element.find('.' + TableView.EL_TABLE_CLASS);
            this.columnsEl = element.find('.' + TableView.EL_COLUMNS_CLASS);
            this.responsiveWrapper = element.find('.' + TableView.EL_RESPONSIVE_CLASS);
        },

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return style;
        },

        getBody: function () {
            return this.table;
        },

        getResponsiveWrapper: function () {
            return this.responsiveWrapper;
        },

        getColumns: function () {
            return this.columnsEl;
        }

    }, {
        EL_TABLE_CLASS: 'ebTable',
        EL_COLUMNS_CLASS: 'ebTable-columns',
        EL_RESPONSIVE_CLASS: 'elWidgets-Table-responsiveWrapper'
    });

    return TableView;

});

define('widgets/Table/Table',['jscore/core','widgets/WidgetCore','jscore/ext/mvp','./TableView','widgets/table/Column','widgets/table/Row','widgets/table/HeaderCell','widgets/table/Cell','widgets/table/HeaderRow','widgets/table/Sortable'],function (core, WidgetCore, mvp, View, Column, Row, HeaderCell, Cell, HeaderRow, Sortable) {
    'use strict';

    /**
     * Table object which takes an array of objects and displays them with rows and cells.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>Table(Object options)</li>
     *     <li>Table(Object options, Array&lt;Object&gt; data)</li>
     *     <li>Table(Object options, Collection data)</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>sort: attribute and sort order are passed as arguments</li>
     *     <li>rowclick: row and model passed as arguments, triggers when a row is clicked</li>
     *     <li>rowselect: row, model, and isSelected passed as arguments, triggers when a selectableRows is enabled and a row is clicked, or when a CheckboxCell status is changed</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *     <li>modifiers: Array of modifiers that this table should use</li>
     *     <li>rowType: Reference to extended Row to use</li>
     *     <li>headerRowType: Reference to extended to use for the header</li>
     *     <li>cellType: Reference to extended Cell to use for all cells</li>
     *     <li>headerCellType: Reference to extended Cell to use for all header cells</li>
     *     <li>noHeader: if true, the table will not have a header</li>
     *     <li>width: String with a CSS fixed width</li>
     *     <li>minWidth: String with a CSS width</li>
     *     <li>maxWidth: String with a CSS width</li>
     *     <li>tooltips: if true, native browser tooltips are displayed when hovering over a cell</li>
     *     <li>selectableRows: If true, click to select row. If CTRL is held, multiple rows can be selected. If SHIFT is held, a range of rows is selected.</li>
     *     <li>columns: Array of objects which define the columns. Can have the following properties:
     *       <ul>
     *         <li>title: Title for the column.</li>
     *         <li>attribute: The model attribute that the column should look for.</li>
     *         <li>sortable: if true, columns headers can be clicked to trigger a sort event</li>
     *         <li>initialSortIcon: "desc" or "asc", it won't sort the data, but just change the icon</li>
     *         <li>width: String with a CSS fixed width</li>
     *         <li>cellType: Reference to extended Cell to use</li>
     *         <li>headerCellType: Reference to extended Cell to use for the header</li>
     *       </ul>
     *     </li>
     *   </ul>
     *
     * <strong>Custom Cells are Available:</strong>
     *   <ul>
     *     <li>EditableCell: "widgets/table/EditableCell" </li>
     *     <li>CheckboxCell: "widgets/table/CheckboxCell"</li>
     *     <li>CheckboxHeaderCell: "widgets/table/CheckboxHeaderCell"</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>striped: add stripes to the table rows (Asset Library).</li>
     *      <li>color: change table color (Asset Library).</li>
     *      <li>more... see Asset Library.</li>
     *  </ul>
     *
     * @class Table
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        View: View,

        // TODO: should be renamed: Remove "Type", and start with capital letter. This is class names!
        rowType: Row,
        headerRowType: HeaderRow,
        cellType: Cell,
        headerCellType: HeaderCell,

        /**
         * Called once the Table has been fully initialized
         *
         * @method onTableReady
         */
        onTableReady: function() {
        },

        /**
         * Initializes members which are used in this widget
         *
         * @method init
         * @private
         */
        init: function (options, data) {
            this.prepareOptions();
            this._eventBus = new core.EventBus();
            this.rowType = this.options.rowType;
            this.headerRowType = this.options.headerRowType;
            this.cellType = this.options.cellType;
            this.headerCellType = this.options.headerCellType;
            this._rows = [];
            this._modelRowBindings = {};
            this._events = {};
            this._columns = this.initializeColumns(this.options.columns);
            this._headerRow = this.initializeHeaderRow(this._columns);
            this._data = data instanceof mvp.Collection ? data : new mvp.Collection(data);
            this._previousSelectedRow = undefined;
            this._selectedRows = [];
            this.setupEvents();
        },

        /**
         * Creates a new options object, first using properties of the table, then merges in options from constructor
         *
         * @method prepareOptions
         * @private
         */
        prepareOptions: function() {
            /*jshint forin:false */
            var output = {};
            for (var prop in this) {
                output[prop] = this[prop];
            }

            for (var opProp in this.options) {
                if (this.options.hasOwnProperty(opProp)) {
                    output[opProp] = this.options[opProp];
                }
            }

            this.options = output;
        },

        /**
         * Creates default events that components can publish to
         *
         * @method setupEvents
         * @private
         */
        setupEvents: function () {
            this.getEventBus().subscribe('rowdestroy', function (row) {
                this._rows.splice(this._rows.indexOf(row), 1);
            }, this);
            this.getEventBus().subscribe('checkboxRowSelected', function (row) {
                this.trigger('rowselect', row, row.getData(), row.isHighlighted());
            }, this);
        },

        /**
         * Initializes columns, the headerRow, and the data
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this._columns.forEach(function (column) {
                column.attachTo(this.view.getColumns());
            }.bind(this));

            if (!this.options.noHeader) {
                this._headerRow.attachTo(this.view.getBody());
            }

            this.setData(this._data);

            if (this.options.modifiers) {
                for (var i = 0; i < this.options.modifiers.length; i++) {
                    var modifier = this.options.modifiers[i];
                    if (typeof modifier === 'string') {
                        this.view.getBody().setModifier(this.options.modifiers[i]);
                    } else {
                        this.view.getBody().setModifier(modifier.name, modifier.value);
                    }
                }
            }

            if (this.options.minWidth) {
                this.view.getResponsiveWrapper().setStyle('min-width', this.options.minWidth);
            }

            if (this.options.maxWidth) {
                this.view.getResponsiveWrapper().setStyle('max-width', this.options.maxWidth);
            }

            if (this.options.width) {
                this.view.getResponsiveWrapper().setStyle('width', this.options.width);
            }

            this.onTableReady();

        },

        /**
         * Adds the model to the collection and creates a row for it
         *
         * @method addData
         * @param {Object | Model} data
         */
        addData: function (data) {
            this._data.addModel(data);
        },

        /**
         * Creates the row for the model
         *
         * @method addRow
         * @private
         * @param {Model} data
         */
        addRow: function (data) {
            var row = new this.rowType({
                data: data,
                eventBus: this._eventBus,
                table: this
            });

            this._columns.forEach(function (column) {
                var attribute = column.getAttribute();

                var CellType = (column.getDefinition().cellType || this.cellType);
                var cell = new CellType({
                    row: row,
                    column: column,
                    eventBus: this._eventBus,
                    model: data,
                    attribute: attribute,
                    table: this
                });


                if (attribute) {
                    // Binding cells to the model attributes
                    if (row.tableCellBindEvents === undefined) {
                        row.tableCellBindEvents = {};
                    }

                    // FIXME
                    // Prevent memory leak or handlers being left over
                    if (row.tableCellBindEvents[attribute]) {
                        data.removeEventHandler('change:' + attribute, row.tableCellBindEvents[attribute]);
                    }

                    row.tableCellBindEvents[attribute] = data.addEventHandler('change:' + attribute, function (model) {
                        cell.setValue(model.getAttribute(attribute));
                    });

                    if (this.options.tooltips) {
                        cell.setTooltip(data.getAttribute(column.getAttribute()));
                    }
                }

                row.attachCell(cell);
            }.bind(this));

            // Attach rowclick event
            var rowBody = row.view.getBody? row.view.getBody() : row.getElement().find('tr');

            rowBody.addEventHandler('click', function () {
                this.trigger('rowclick', row, data);
            }.bind(this));

            // Attach selectable events
            if (this.options.selectableRows) {
                this.applySelectableHandlers(row);
            }

            this._modelRowBindings[data.cid] = row;
            row.attachTo(this.view.getBody());

            //TODO: Remove this
            this._rows.push(row);
        },

        /**
         * Gets the current index of the row
         *
         * @method getRowIndex
         * @return {int}
         */
        getRowIndex: function (row) {
            return this.getRows().indexOf(row);
        },

        /**
         * Applies a click handler to the row to allow it to be clicked to highlight.
         * If "selectableRows" is equal to Table.CTRL, user must hold CTRL while clicking a row to highlight it.
         * A user can hold shift to highlight multiple rows.
         * A "rowhighlight" event is thrown for each row changed.
         *
         * @method applySelectableHandlers
         * @private
         * @param {Row} row
         */
        applySelectableHandlers: function (row) {
            var rowBody = row.view.getBody? row.view.getBody() : row.getElement().find('tr');

            // Prevent default shift-click event that browsers might do
            rowBody.addEventHandler('mousedown', function (e) {
                if (e.originalEvent.shiftKey) {
                    e.preventDefault();
                }
            });
             //TODO: Need to move in to custom row
            rowBody.addEventHandler('click', function (e) {

                if (e.originalEvent.shiftKey && this._previousSelectedRow && this._previousSelectedRow !== row) {
                    var prevIndex = this.getRowIndex(this._previousSelectedRow);
                    var nextIndex = this.getRowIndex(row);
                    var start = (prevIndex < nextIndex) ? prevIndex : nextIndex;
                    var end = (prevIndex > nextIndex) ? prevIndex : nextIndex;
                    for (var i = 0; i < this.getRows().length; i++) {
                        this.selectRow(this.getRows()[i], i >= start && i <= end);
                    }
                } else {

                    if (row.isHighlighted() || e.originalEvent.ctrlKey) {
                        this.selectRow(row, !row.isHighlighted());
                    } else {


                        for (var j = 0; j < this._selectedRows.length; j++) {
                            if (this._selectedRows[j] !== row) {
                                this.selectRow(this._selectedRows[j], false, false);
                            }
                        }
                        this._selectedRows = [];
                        this.selectRow(row, !row.isHighlighted());


                    }
                    this._previousSelectedRow = row.isHighlighted() ? row : undefined;
                }

            }, this);
        },

        selectRow: function (row, highlight, splice) {
            splice = !!(splice);

            row.highlight(highlight);
            this.trigger('rowselect', row, row.getData(), row.isHighlighted());

            if (row.isHighlighted() && this._selectedRows.indexOf(row) === -1) {
                this._selectedRows.push(row);
            } else if (!row.isHighlighted() && this._selectedRows.indexOf(row) !== -1 && splice) {
                this._selectedRows.splice(this._selectedRows.indexOf(row), 1);
            }
        },


        /**
         * Clears the table and adds rows for the passed collection
         *
         * @method setData
         * @param {Array<Object> | Collection} collection
         */
        setData: function (collection) {
            this._selectedRows = [];

            if (collection) {
                this.clear();

                this.getEventBus().publish('collection:reset');

                collection = collection instanceof mvp.Collection ? collection : new mvp.Collection(collection);

                this._data = collection;

                collection.each(function (model) {
                    this.addRow(model);
                }.bind(this));

                this._events.add = this._data.addEventHandler('add', function (model) {
                    this.addRow(model);
                    this.getEventBus().publish('collection:add', model);
                }, this);

                this._events.remove = this._data.addEventHandler('remove', function (model) {
                    this.removeRow(model);
                    this.getEventBus().publish('collection:remove', model);
                }, this);

                this._events.reset = this._data.addEventHandler('reset', function () {
                    this.setData(this._data);
                    this.getEventBus().publish('collection:reset');
                }.bind(this));

                this._events.sort = this._data.addEventHandler('sort', function () {
                    this.sortRender();
                }, this);
            }
        },

        /**
         * Removes the model from the collection and destroys the row for it
         *
         * @method removeData
         * @param {Model} model
         */
        removeData: function (model) {
            this._data.removeModel(model);
        },

        /**
         * Removes the row for the model
         *
         * @method removeRow
         * @private
         * @param {Model} model
         */
        removeRow: function (model) {
            this._modelRowBindings[model.cid].destroy();
            delete this._modelRowBindings[model.cid];
        },

        /**
         * Clears all data rows in the table and empties the collection
         *
         * @method clear
         */
        clear: function () {
            for (var i = this._rows.length - 1; i >= 0; i--) {
                this._rows[i].destroy();
            }

            this._rows = [];

            for (var event in this._events) {
                if (this._events.hasOwnProperty(event)) {
                    this._data.removeEventHandler(event, this._events[event]);
                }
            }
            this._data = new mvp.Collection([]);
            this._modelRowBindings = {};
        },

        /**
         * Sorts the data and re-renders the rows. See <a href="../../../../jscore/latest/api/classes/ext.mvp.Collection.html#methods_sort">Collection Sort</a> for more information.
         *
         * @method sort
         * @param {Object} comparator
         * @param {String} sortingMode
         */
        sort: function (comparator, sortingMode) {
            this._data.sort(comparator, sortingMode); // check for sort event from collection
        },

        /**
         * Detaches all rows and reattached them based on the collection order
         *
         * @method sortRender
         * @private
         */
        sortRender: function () {
            for (var i = 0; i < this.getRows().length; i++) {
                this.getRows()[i].detach();
            }

            this._rows = [];

            this._data.each(function (model) {
                var row = this._modelRowBindings[model.cid];
                row.attach();
                this._rows.push(row);
            }.bind(this));
        },


        /**
         * Creates and returns an array of Columns using the supplied column definitions
         *
         * @method initializeColumns
         * @private
         * @param {Array<Object>} columnDefinitions
         * @return {Array<Column>} columns
         */
        initializeColumns: function (columnDefinitions) {
            var columns = [];


            for (var i = 0; i < columnDefinitions.length; i++) {
                var column = new Column({
                    index: i,
                    definition: columnDefinitions[i]
                });

                columns.push(column);
            }

            return columns;
        },

        /**
         * Creates and returns an array of Columns using the supplied column definitions
         *
         * @method initializeHeaderRow
         * @private
         * @param {Array<Column>} columns
         * @return {Row} headerRow
         */
        initializeHeaderRow: function (columns) {
            var table = this;
            var headerRow = new this.headerRowType({
                eventBus: this._eventBus,
                table: this
            });
            var sortables = [];
            columns.forEach(function (column, i) {
                var CellType = (column.getDefinition().headerCellType || this.headerCellType);
                var cell = new CellType({
                    row: headerRow,
                    eventBus: this._eventBus,
                    column: columns[i],
                    table: this
                });

                if (this.options.tooltips) {
                    cell.setTooltip(columns[i].getTitle());
                }

                if (columns[i].getDefinition().sortable) {
                    var sortable = new Sortable();

                    var initialSortIcon = columns[i].getDefinition().initialSortIcon;
                    if (initialSortIcon) {
                        if (initialSortIcon === 'desc') {
                            sortable.view.setDownArrowActive();
                        } else {
                            sortable.view.setUpArrowActive();
                        }
                        sortable._sortingMode = initialSortIcon;
                    }

                    var cellElement = cell.view.getBody();

                    sortable.attachTo(cellElement);
                    cellElement.addEventHandler('click', sortable.click, sortable);
                    sortable.addEventHandler('reset', function () {
                        sortables.forEach(function (current) {
                            if (this !== current) {
                                current.reset();
                            }
                        }, this);
                    }, sortable);

                    sortable.addEventHandler('sort', function (sortingMode) {
                        table.trigger('sort', this.getColumn().getAttribute(), sortingMode);
                    }, cell);

                    sortables[i] = sortable;
                }

                headerRow.attachCell(cell);
            }, this);

            return headerRow;
        },

        /**
         * Returns the collection for the table
         *
         * @method getData
         * @return {Collection} data
         */
        getData: function () {
            return this._data;
        },

        /**
         * Returns the header row for the table
         *
         * @method getHeaderRow
         * @return {HeaderRow} headerRow
         */
        getHeaderRow: function () {
            return this._headerRow;
        },

        /**
         * Returns the body rows for the table
         *
         * @method getRows
         * @return {Array<Row>} rows
         */
        getRows: function () {
            return this._rows;
        },

        /**
         * Returns the columns for the table
         *
         * @method getHeaderRow
         * @return {Array<Column>} columns
         */
        getColumns: function () {
            return this._columns;
        },

        /**
         * Returns the event bus for the table that all components in the table can access
         *
         * @method getEventBus
         * @return {EventBus} eventBus
         */
        getEventBus: function () {
            return this._eventBus;
        },

        /**
         * Removes the Widget root Element from the DOM.
         *
         * @method destroy
         *
         * @example
         *   table.destroy();
         */
        destroy: function () {
            this.clear();
            core.Widget.prototype.destroy.call(this);
        }

        /**
         * Extending the table allows you to specify options that are common amongst multiple instances of the table, preventing duplicate code. Options can override properties defined in the extended table.
         *
         * @method extend
         * @static
         * @param {Object} definition
         * @return {Table} table
         * @example
         *   Table.extend({
         *     columns:[],
         *     width: "100px",
         *     selectableRows: true
         *   });
         */

    });

});

define('widgets/Table',['widgets/Table/Table'],function (main) {
                        return main;
                    });

