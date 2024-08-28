/* Copyright (c) Ericsson 2014 */

define('styles!tablelib/plugins/selection/checkboxheadercell/CheckboxHeaderCell.less',[],function () { return '.elTablelib-CheckboxHeaderCell-wrap {\n  text-align: center;\n  width: 100%;\n  margin-left: -2px;\n}\n';});

define('text!tablelib/plugins/selection/checkboxheadercell/CheckboxHeaderCell.html',[],function () { return '<th style="box-sizing: border-box;" class="elTablelib-CheckboxHeaderCell">\n    <div class="elTablelib-CheckboxHeaderCell-wrap">\n        <input class="ebCheckbox" type="checkbox" value="1"/>\n        <span class="ebCheckbox-inputStatus"></span>\n    </div>\n</th>';});

define('tablelib/plugins/selection/checkboxheadercell/CheckboxHeaderCellView',['jscore/core','text!./CheckboxHeaderCell.html','styles!./CheckboxHeaderCell.less'],function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        },

        getCheckboxStatus: function() {
            return this.getElement().find(".ebCheckbox-inputStatus");
        }

    });

});

define('tablelib/plugins/selection/checkboxheadercell/CheckboxHeaderCell',['../checkboxcell/CheckboxCell','./CheckboxHeaderCellView'],function (CheckboxCell, View) {

    return CheckboxCell.extend({

        View: View,

        eventName: "checkheader",

        onCellReady: function() {
            this.getTable().setColumnWidth(this.getIndex(), "40px");
        },

        setTriple: function(isTriple) {
            if (isTriple) {
                this.view.getCheckboxStatus().setModifier("triple");
            } else {
                this.view.getCheckboxStatus().removeModifier("triple");
            }
        }

    });

});

define('styles!tablelib/plugins/selection/checkboxcell/CheckboxCell.less',[],function () { return '.elTablelib-CheckboxCell-wrap {\n  text-align: center;\n  width: 100%;\n  margin-left: -2px;\n}\n';});

define('text!tablelib/plugins/selection/checkboxcell/CheckboxCell.html',[],function () { return '<td class="elTablelib-CheckboxCell">\n    <div class="elTablelib-CheckboxCell-wrap">\n        <input class="ebCheckbox" type="checkbox" value="1"/>\n        <span class="ebCheckbox-inputStatus"></span>\n    </div>\n</td>';});

define('tablelib/plugins/selection/checkboxcell/CheckboxCellView',['jscore/core','text!./CheckboxCell.html','styles!./CheckboxCell.less'],function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        }
    });

});

define('tablelib/plugins/selection/checkboxcell/CheckboxCell',['tablelib/Cell','./CheckboxCellView'],function (Cell, View) {

    return Cell.extend({

        View: View,

        eventName: "check",

        onViewReady: function() {
            // If checking the checkbox itself, trigger the event, it will always change
            this.getElement().find(".ebCheckbox").addEventHandler("click", function(e) {
                e.stopPropagation();
                this.trigger();
            }.bind(this));

            // Clicking the cell should also change the state of the checkbox
            this.getElement().addEventHandler("click", function(e) {
                e.stopPropagation();
                this.check(!this.isChecked(), true);
            }.bind(this));
        },

        check: function(checked, trigger) {
            var cb = this.getElement().find(".ebCheckbox");
            if (cb.getProperty("checked") !== checked) {
                cb.setProperty("checked", checked);
                if (trigger) {
                    this.trigger();
                }
            }
        },

        setValue: function() {
            // Override the original setValue implementation
        },

        isChecked: function() {
            return this.getElement().find(".ebCheckbox").getProperty("checked");
        },

        trigger: function() {
            this.getTable().trigger(this.eventName, this.getRow(), this.isChecked());
        }

    });

});

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

define('tablelib/plugins/selection/Selection',['jscore/core','../Plugin','./checkboxcell/CheckboxCell','./checkboxheadercell/CheckboxHeaderCell'],function (core, Plugin, CheckboxCell, CheckboxHeaderCell) {

    // These specs are useful reduce the amount of code
    var selectSpec = {
        prop: "_isSelected", // Property on the row that keeps track of state
        endEventName: "rowselectend", // This event is trigger after an action
        drawFn: __drawSelectedRow, // Rendering function that renders the current state
        mainFn: selectRow, // The function that handles the selection logic
        bindFn: checkRow, // The function to call if bind is enabled
        getterFn: getSelectedRows // Function that fetches everything with the matching state
    };

    var checkSpec = {
        prop: "_isChecked",
        endEventName: "checkend",
        drawFn: __drawCheckedRow,
        mainFn: checkRow,
        bindFn: selectRow,
        getterFn: getCheckedRows
    };

    return Plugin.extend({

        injections: {

            before: {
                onViewReady: onViewReady
            },

            after: {
                addRow: addRow,
                removeRow: calculateCheckboxHeaderStatus
            },

            newMethods: {
                getSelectedRows: getSelectedRows,
                unselectAllRows: unselectAllRows,
                selectRows: selectRows,
                getCheckedRows: getCheckedRows,
                checkRows: checkRows
            }

        }

    });

    /**
     * Adds new methods to the table.
     * Prepends checkbox cells to table.
     * Adds handlers to checkboxes.
     *
     * @method onViewReady
     * @private
     */
    function onViewReady() {
        var table = this.getTable();
        this._lastSelectedRow = undefined;

        if (this.options.checkboxes) {

            table._columns.unshift({
                cellType: CheckboxCell,
                headerCellType: CheckboxHeaderCell
            });

            table.addEventHandler("checkheader", function(row, checked) {
                table.getRows().forEach(function(row) {
                    checkRow.call(this, row, checked);
                }.bind(this));
            }.bind(this));

            table.addEventHandler("check", function(row, checked) {
                checkRow.call(this, row, checked);
            }.bind(this));
        }
    }

    /**
     * Gets the column index for checkboxes. There's no guarantee as to which
     * column checkboxes will fall under. It can change.
     *
     * @method getCheckboxColumnIndex
     * @private
     */
    function getCheckboxColumnIndex() {
        var hcs = this.getTable().getHeaderRow().getCells();
        for (var i = 0; i < hcs.length; i++) {
            if (hcs[i] instanceof CheckboxHeaderCell) {
                return i;
            }
        }
    }

    /**
     * Gets the index for a row. Row index can change dynamically.
     *
     * @method getIndexOfRow
     * @private
     */
    function getIndexOfRow(row) {
        var rows = this.getTable().getRows();
        for (var i = 0; i < rows.length; i++) {
            if (rows[i] === row) {
                return i;
            }
        }
    }

    /**
     * Defers a callback until after the current JavaScript execution.
     * If the same name is passed, that callback is cancelled and the new one is applied.
     *
     * @method defer
     * @private
     */
    function defer(name, callback) {
        if (!this["_timeout__" + name]) {
            this["_timeout__" + name] = setTimeout(function() {
                callback.call(this);
                delete this["_timeout__"+name];
            }.bind(this));
        }
    }

    /**
     * Implements the handlers for row selection.
     *
     * @method addRow
     * @private
     */
    function addRow(obj, index) {

        var table = this.getTable();
        var row = table.getRows()[index !== undefined? index : table.getRows().length - 1];

        if (this.options.selectableRows) {

            // Prevents browser default shift-click functionality
            row.getElement().addEventHandler('mousedown', function (e) {
                if (e.originalEvent.shiftKey) {
                    e.preventDefault();
                }
            });

            row.getElement().addEventHandler("click", function(e) {

                var selectedRows = getSelectedRows.call(this);

                // Deselect the previous row
                if (!this.options.multiselect || (!e.originalEvent.ctrlKey && !e.originalEvent.metaKey)) {
                    unselectAllRows.call(this);
                }

                if (row !== this._lastSelectedRow) {
                    if (this.options.multiselect && e.originalEvent.shiftKey) {
                        // Select all other rows apart from the one click
                        // That row will be added at the end of the function anyways
                        // Note we have to get the index dynamically, because it can change
                        var ci = getIndexOfRow.call(this, row);
                        var li = getIndexOfRow.call(this, this._lastSelectedRow);
                        for (var i = Math.min(ci, li); i <= Math.max(ci, li); i++) {
                            if (i !== ci) {
                                selectRow.call(this, table.getRows()[i], true);
                                table.trigger("rowselect", table.getRows()[i], table.getRows()[i]._isSelected);
                            }
                        }
                    }

                    selectRow.call(this, row, !row._isSelected);

                    // Don't change the pivot point
                    if (!e.originalEvent.shiftKey || selectedRows.length === 1) {
                        this._lastSelectedRow = row;
                    }
                } else {
                    // This is to allow the row to be selectable again after it has been unselected
                    this._lastSelectedRow = undefined;
                }

                table.trigger("rowselect", row, row._isSelected);

            }.bind(this));
        }
    }

    /**
     * Unselects all rows in the table.
     *
     * @method unselectAllRows
     * @private
     */
    function unselectAllRows() {
        var rows = getSelectedRows.call(this);
        rows.forEach(function (theRow) {
            selectRow.call(this, theRow, false);
        }.bind(this));
    }

    /**
     * Figures out whether or not to show tick, "triple", or nothing for the checkbox header
     *
     * @method calculateCheckboxHeaderStatus
     * @private
     */
    function calculateCheckboxHeaderStatus() {
        if (this.options.checkboxes) {
            // Using a timeout so that we're only checking once, useful for setData
            defer.call(this, "calculateCheckboxHeaderStatus", function() {
                var rows = this.getTable().getRows();
                var index = getCheckboxColumnIndex.call(this);
                var checked = 0;
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i]._isChecked) {
                        checked++;
                    }
                }

                var headerCheckbox = this.getTable().getHeaderRow().getCells()[index];

                if (checked === 0) {
                    headerCheckbox.check(false);
                } else if (checked < rows.length) {
                    headerCheckbox.check(true);
                    headerCheckbox.setTriple(true);
                } else {
                    headerCheckbox.check(true);
                    headerCheckbox.setTriple(false);
                }
            }.bind(this));
        }
    }


    /**
     * Selects a row based on the state passed.
     *
     * @method selectRow
     * @private
     */
    function selectRow(row, isSelected) {
        __rowAction.call(this, row, selectSpec, isSelected);
    }

    /**
     * Checks a row based on the state passed.
     *
     * @method checkRow
     * @private
     */
    function checkRow(row, isChecked) {
        __rowAction.call(this, row, checkSpec, isChecked);
    }

    /**
     * Implementation of selectRow and checkRow
     *
     * @method __rowAction
     * @private
     */
    function __rowAction(row, spec, state) {
        if (row[spec.prop] !== state) {
            row[spec.prop] = state;

            spec.drawFn.call(this, row);

            defer.call(this, spec.endEventName, function() {
                this.getTable().trigger(spec.endEventName, spec.getterFn.call(this));
            }.bind(this));

            if (this.options.bind) {
                spec.bindFn.call(this, row, state);
            }
        }
    }

    /**
     * Render selected row state.
     *
     * @method __drawSelectedRow
     * @private
     */
    function __drawSelectedRow(row) {
        if (row._isSelected) {
            row.getElement().setModifier("highlighted");
        } else {
            row.getElement().removeModifier("highlighted");
        }
    }

    /**
     * Render checked row state.
     *
     * @method __drawCheckedRow
     * @private
     */
    function __drawCheckedRow(row) {
        var colIndex = getCheckboxColumnIndex.call(this);
        row.getCells()[colIndex].check(row._isChecked);
        calculateCheckboxHeaderStatus.call(this);
    }

    /**
     * Gets checked rows in the table
     *
     * @method getCheckedRows
     * @private
     */
    function getCheckedRows() {
        return __getConditionalRows.call(this, checkSpec);
    }

    /**
     * Gets selected rows in the table
     *
     * @method getSelectedRows
     * @private
     */
    function getSelectedRows() {
        return __getConditionalRows.call(this, selectSpec);
    }

    /**
     * Implementation of getCheckedRows and getSelectedRows
     *
     * @method __getConditionalRows
     * @private
     */
    function __getConditionalRows(spec) {
        var result = [];

        this.getTable().getRows().forEach(function(row) {
            if (row[spec.prop]) {
                result.push(row);
            }
        }.bind(this));

        return result;
    }


    /**
     * Selects multiple rows based on the callback
     *
     * @method selectRows
     * @private
     */
    function selectRows(callback) {
        __rowComparatorAction.call(this, callback, selectSpec);
    }

    /**
     * Checks multiple rows based on the callback
     *
     * @method checkRows
     * @private
     */
    function checkRows(callback) {
        __rowComparatorAction.call(this, callback, checkSpec);
    }

    /**
     * Implementation for selectRows and checkRows
     *
     * @method __rowComparatorAction
     * @private
     */
    function __rowComparatorAction(callback, spec) {
        var rows = this.getTable().getRows();
        rows.forEach(function(row) {
            if (callback(row)) {
                spec.mainFn.call(this, row, true);

                if (this.options.bind) {
                    spec.bindFn.call(this, row, true);
                }
            }
        }.bind(this));
    }

});

define('tablelib/plugins/Selection',['tablelib/plugins/selection/Selection'],function (main) {
                        return main;
                    });

