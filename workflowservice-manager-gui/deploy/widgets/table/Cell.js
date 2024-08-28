/* Copyright (c) Ericsson 2014 */

define('text!widgets/Table/table/Cell/_cell.html',[],function () { return '<td></td>';});

define('widgets/Table/table/Cell/CellView',['jscore/core','text!./_cell.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getBody: function () {
            return this.getElement();
        },

        setValue: function (value) {
            this.getBody().setText(value);
        },

        getValue: function () {
            return this.getBody().getText();
        }

    });

});

define('widgets/Table/table/Cell/Cell',['widgets/WidgetCore','./CellView'],function (WidgetCore, View) {
    'use strict';

    /**
     * Cell base for the table. Can be extended to provide custom functionality.
     *
     * @class table.Cell
     */
    var Cell = WidgetCore.extend({

        View: View,

        onViewReady: function () {
            var options = this.options,
                model = options.model,
                attribute = options.attribute;
            if (model !== undefined && attribute !== undefined) {
                var data = model.getAttribute(attribute);
                this.setValue(data);
            }

            this.onCellReady();
        },

        /**
         * Called once the Cell has been fully initialized
         *
         * @method onCellReady
         */
        onCellReady: function () {
        },

        /**
         * Returns the row this cell belongs to
         *
         * @method getRow
         * @return {Row} row
         */
        getRow: function () {
            return this.options.row;
        },

        /**
         * Gets the table instance associated with this cell
         *
         * @method getTable
         * @return {Table} table
         */
        getTable: function () {
            return this.options.table;
        },

        /**
         * Returns the column this cell belongs to
         *
         * @method getColumn
         * @return {Column} column
         */
        getColumn: function () {
            return this.options.column;
        },

        /**
         * The cell takes the model attribute value, and puts it into its view. This method should be overrided if the view is changed.
         *
         * @method setValue
         * @param {String} value
         */
        setValue: function (value) {
            value = value === ''? '\u200b' : value;
            if (this.view.setValue) {
                this.view.setValue(value);
            }
        },

        /**
         * The cell returns the value of the cell from its view. This method should be overrided if the view is changed.
         *
         * @method getValue
         * @return {String} value
         */
        getValue: function () {
            return this.view.getValue();
        },

        /**
         * Modifies the root of the cell to include a title for tooltips
         *
         * @method setTooltip
         * @private
         * @param {String} value
         */
        setTooltip: function (value) {
            this.view.getElement().setAttribute('title', value);
        },

        /**
         * Returns the event bus for the table that all components in the table can access and subscribe to
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

        destroy: function() {
            if (this._eventBus) {
                this._eventBus.eventHandlers.forEach(function(eventObj) {
                    this.options.eventBus.unsubscribe(eventObj.channel, eventObj.eventId);
                }.bind(this));
            }

            WidgetCore.prototype.destroy.call(this);
        }

        /**
         * To create an Cell child class call extend providing the class definition.
         *
         * @method extend
         * @static
         * @param {Object} definition
         * @return {Cell} cell
         * @example
         *   Cell.extend({
         *     onCellReady: function() { ... }
         *   });
         */

    });

    return Cell;

});

define('widgets/table/Cell',['widgets/Table/table/Cell/Cell'],function (main) {
                        return main;
                    });

