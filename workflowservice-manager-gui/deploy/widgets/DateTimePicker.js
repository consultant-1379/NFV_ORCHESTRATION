/* Copyright (c) Ericsson 2014 */

define('text!widgets/DateTimePicker/_dateTimePicker.html',[],function () { return '<div data-namespace="ebDateTimePicker">\n    <div data-name="dateHolder"></div>\n    <div data-name="timeHolder"></div>\n</div>';});

define('widgets/DateTimePicker/DateTimePickerView',['jscore/core','text!./_dateTimePicker.html','widgets/utils/dataNameUtils'],function (core, template, dataNameUtils) {
    'use strict';

    var DateTimeView = core.View.extend({

        getTemplate: function () {
            return dataNameUtils.translate(null, template, this);
        },

        getDateHolder: function () {
            return this[DateTimeView.EL_DATE_HOLDER];
        },

        getTimeHolder: function () {
            return this[DateTimeView.EL_TIME_HOLDER];
        }

    }, {
        'EL_DATE_HOLDER': 'dateHolder',
        'EL_TIME_HOLDER': 'timeHolder'
    });

    return DateTimeView;

});

define('widgets/DateTimePicker/DateTimePicker',['widgets/WidgetCore','./DateTimePickerView','widgets/DatePicker','widgets/TimePicker'],function (WidgetCore, View, DatePicker, TimePicker) {
    'use strict';

    /**
     * The DateTimePicker class uses the Ericsson brand assets.<br>
     * The DateTimePicker can be instantiated using the constructor DateTimePicker.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>change: Triggers when a date has been changed. Passes the changed date as a parameter.</li>
     *       <li>dateselect: Triggers when a date has been selected. Passes the selected date as a parameter.</li>
     *   </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>value: a date used as initial date and time for DateTimePicker.</li>
     *       <li>dateInRange: a function used as date range for DateTimePicker. By default date range not defined.</li>
     *       <li>disableDay: a function used as a disabled day for DateTimePicker. By default a disabled day not defined.</li>
     *       <li>defaultDate: a function used as default date to show when just opening DateTimePicker.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class DateTimePicker
     * @extends WidgetCore
     * @baselined
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        View: View,

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param {Object} options
         * @private
         */
        init: function (options) {
            this.options = options || {};

            var dateTime = this.options.value;
            this.datePicker = new DatePicker({
                value: dateTime,
                dateInRange: this.options.dateInRange,
                disableDay: this.options.disableDay,
                defaultDate: this.options.defaultDate
            });

            var timeObj = getDefaultTime.call(this, dateTime);

            this.timePicker = new TimePicker(timeObj);
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.view.getDateHolder().append(this.datePicker.getElement());
            this.view.getTimeHolder().append(this.timePicker.getElement());

            //add handlers
            this.datePicker.addEventHandler('dateselect', function() {
                var date = this.getValue();

                if (date) {
                    this.trigger('dateselect', date);
                }
            }.bind(this));
            var triggerChangedFunction = function() {
                this.trigger('change', this.getValue());
            }.bind(this);
            this.datePicker.addEventHandler('change', triggerChangedFunction);
            this.timePicker.addEventHandler('change', triggerChangedFunction);
        },

        /**
         * Sets date in the DateTimePicker.
         *
         * @method setValue
         * @param {Date} date Date object to represent selected day in the DateTimePicker
         */
        setValue: function (date) {
            this.datePicker.setValue(date);

            var timeObj = getDefaultTime.call(this, date);

            this.timePicker.setValue(timeObj.hours, timeObj.minutes, timeObj.seconds);
        },

        /**
         * Returns selected date from the DateTimePicker.
         *
         * @method getValue
         * @return {Date} date Date representing value in the DateTimePicker or null if not selected.
         */
        getValue: function () {
            var date = this.datePicker.getValue();
            if (date === null) {
                return date;
            }

            var time = this.timePicker.getValue();
            date.setHours(time.hours);
            date.setMinutes(time.minutes);
            date.setSeconds(time.seconds);

            return date;
        }

    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    /**
     * Returns time objects which contain time data from date object or from given default date or from current date.
     *
     * @method getDefaultTime
     * @private
     * @param {Date} date The date object from where time should be taken
     * @returns {{}}
     */
    function getDefaultTime(date) {
        var timeObj = {};
        if (date instanceof Date) {
            timeObj.hours = date.getHours();
            timeObj.minutes = date.getMinutes();
            timeObj.seconds = date.getSeconds();
        } else {
            var defaultDate;
            if (this.options.defaultDate && this.options.defaultDate instanceof Date) {
                defaultDate = this.options.defaultDate;
            } else if (this.options.defaultDate && this.options.defaultDate instanceof Function) {
                defaultDate = this.options.defaultDate();
            } else {
                defaultDate = new Date();

                // Minutes & seconds by default are 0
                defaultDate.setMinutes(0);
                defaultDate.setSeconds(0);
                defaultDate.setMilliseconds(0);
            }

            timeObj.hours = defaultDate.getHours();
            timeObj.minutes = defaultDate.getMinutes();
            timeObj.seconds = defaultDate.getSeconds();
        }

        return timeObj;
    }

});

define('widgets/DateTimePicker',['widgets/DateTimePicker/DateTimePicker'],function (main) {
                        return main;
                    });

