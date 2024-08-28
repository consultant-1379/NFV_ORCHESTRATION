/* Copyright (c) Ericsson 2014 */

define('text!widgets/Table/table/Row/_row.html',[],function () { return '<tr class="ebTableRow"></tr>\n';});

define('widgets/Table/table/Row/RowView',['jscore/core','text!./_row.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getBody: function () {
            return this.getElement();
        },
        highlight: function () {
            this.getBody().setModifier('highlighted');
        },

        removeHighlight: function () {
            this.getBody().removeModifier('highlighted');
        }

    });

});

define('widgets/Table/table/Row/Row',['widgets/WidgetCore','./RowView'],function (WidgetCore, View) {
    'use strict';

    /**
     * Row base for the table. Can be extended to provide custom functionality.
     *
     * @class table.Row
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        View: View,

        init: function () {
            this._cells = [];
            this._highlighted = false;
        },

        onViewReady: function () {
            this.onRowReady(this.options);
        },

        /**
         * Called once the Row has been fully initialized
         *
         * @method onRowReady
         */
        onRowReady: function () {},

        /**
         * Gets the cell instances associated with this row
         *
         * @method getCells
         * @return {Array<Cell>} cells
         */
        getCells: function () {
            return this._cells;
        },

        /**
         * Gets the table instance associated with this row
         *
         * @method getTable
         * @return {Table} table
         */
        getTable: function () {
            return this.options.table;
        },

        /**
         * Gets the model instance associated with this row
         *
         * @method getData
         * @return {Model} model
         */
        getData: function () {
            return this.options.data;
        },

        /**
         * Highlights the row, unless the highlight variable passed is set to false.
         *
         * @method highlight
         * @param {Boolean} [highlight]
         */
        highlight: function (highlight) {
            highlight = (highlight === undefined) ? true : highlight;
            this._highlighted = highlight;
            if (highlight) {
                this.view.highlight();
            } else {
                this.removeHighlight();
            }
        },

        /**
         * Removes the highlight from the row.
         *
         * @method unhighlight
         * @deprecated
         */
        unhighlight: function () {
            this.removeHighlight();
        },

        /**
         * Removes the highlight from the row.
         *
         * @method removeHighlight
         */
        removeHighlight: function () {
            this._highlighted = false;
            this.view.removeHighlight();
        },

        /**
         * Returns true if the row is highlighted, false otherwise.
         *
         * @method isHighlighted
         * @return {Boolean} highlighted
         */
        isHighlighted: function () {
            return this._highlighted;
        },

        /**
         * Returns the event bus for the table that all components in the table can access
         *
         * @method getEventBus
         * @return {EventBus} eventBus
         */
        getEventBus: function () {
            if (!this._eventBus) {
                var eventBus = this.options.eventBus;
                this._eventBus = {
                    eventHandlers: [],
                    subscribe: function(channel, fn, context) {
                        var eventId = eventBus.subscribe.call(eventBus, channel, fn, context);
                        this.eventHandlers.push({channel: channel, eventId: eventId});
                        return eventId;
                    },
                    unsubscribe: function() {
                        return eventBus.unsubscribe.apply(eventBus, arguments);
                    },
                    publish: function() {
                        return eventBus.publish.apply(eventBus, arguments);
                    }
                };
            }

            return this._eventBus;
        },

        /**
         * Destroys the row widget. By default the model for the row will be removed
         * from the collection unless removeData is set to false.
         *
         * @method destroy
         * @param {Boolean} [removeData]
         */
        destroy: function () {
            for (var i = 0; i < this._cells.length; i++) {
                this._cells[i].destroy();
                this._cells[i] = undefined;
            }

            for (var event in this.tableCellBindEvents) {
                if (this.tableCellBindEvents.hasOwnProperty(event)) {
                    this.options.data.removeEventHandler('change:' + event, this.tableCellBindEvents[event]);
                }
            }

            if (this._eventBus) {
                this._eventBus.eventHandlers.forEach(function(eventObj) {
                    this.options.eventBus.unsubscribe(eventObj.channel, eventObj.eventId);
                }.bind(this));
            }

            WidgetCore.prototype.destroy.call(this);
            this.getEventBus().publish('rowdestroy', this);
        },

        /**
         * Attaches the passed cell to this row
         *
         * @method attachCell
         * @param {Cell} cellWidget
         * @param {Element} [parent]
         */
        attachCell: function (cellWidget, parent) {
            if (!cellWidget.options.row) {
                cellWidget.options.row = this;
            }

            if (!cellWidget.options.table) {
                cellWidget.options.table = this.getTable();
            }

            if (!cellWidget.options.eventBus) {
                cellWidget.options.eventBus = this.getEventBus();
            }

            var rowBody = this.view.getBody? this.view.getBody() : this.getElement().find('tr');
            cellWidget.attachTo(parent || rowBody);
            this._cells.push(cellWidget);
        }

        /**
         * To create an Row child class call extend providing the class definition.
         *
         * @method extend
         * @static
         * @param {Object} definition
         * @return {Row} row
         *
         * @example
         *   Row.extend({
         *     onRowReady: function() {
         *     }
         *   });
         */

    });

});

define('widgets/table/Row',['widgets/Table/table/Row/Row'],function (main) {
                        return main;
                    });

