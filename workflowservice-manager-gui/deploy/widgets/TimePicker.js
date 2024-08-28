/* Copyright (c) Ericsson 2014 */

define('text!widgets/TimePicker/_timePicker.html',[],function () { return '<div data-namespace="ebTimePicker"></div>';});

define('widgets/TimePicker/TimePickerView',['jscore/core','text!./_timePicker.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getRoot: function () {
            return this.getElement();
        }

    });

});

define('widgets/TimePicker/TimePicker',['widgets/WidgetCore','./TimePickerView','widgets/Spinner'],function (WidgetCore, View, Spinner) {
    'use strict';

    /**
     * The TimePicker class uses the Ericsson brand assets.<br>
     * The TimePicker can be instantiated using the constructor TimePicker.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>change: Triggers when a time has been changed. Passes the changed time as a parameter.</li>
     *   </ul>
     *   
     * The following options are accepted:
     *   <ul>
     *       <li>hours: an integer used as a defined hours for TimePicker. Default is 0.</li>
     *       <li>minutes: an integer used as a defined minutes for TimePicker. Default is 0.</li>
     *       <li>seconds: an integer used as a defined seconds for TimePicker. Default is 0.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class TimePicker
     * @extends WidgetCore
     * @baselined
     */
    return WidgetCore.extend({

        View: View,

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @private
         * @param {Object} options
         */
        init: function (options) {
            options = options || {};

            this.hoursSpinner = new Spinner({
                value: options.hours || 0,
                min: 0,
                max: 23,
                postfix: 'h',
                minNumberDigitsFormat: 2
            });

            this.minutesSpinner = new Spinner({
                value: options.minutes || 0,
                min: 0,
                max: 59,
                postfix: 'm',
                minNumberDigitsFormat: 2
            });

            this.secondsSpinner = new Spinner({
                value: options.seconds || 0,
                min: 0,
                max: 59,
                postfix: 's',
                minNumberDigitsFormat: 2
            });
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.getElement().append(this.hoursSpinner.getElement());
            this.getElement().append(this.minutesSpinner.getElement());
            this.getElement().append(this.secondsSpinner.getElement());
            var triggerChangeFunction = function() {
                this.trigger('change', this.getValue());
            }.bind(this);
            this.hoursSpinner.addEventHandler('change', triggerChangeFunction);
            this.minutesSpinner.addEventHandler('change', triggerChangeFunction);
            this.secondsSpinner.addEventHandler('change', triggerChangeFunction);
        },

        /**
         * Sets value to the TimePicker
         *
         * @method setValue
         * @param {int} hours
         * @param {int} minutes
         * @param {int} seconds
         */
        setValue: function (hours, minutes, seconds) {
            this.hoursSpinner.setValue(hours);
            this.minutesSpinner.setValue(minutes);
            this.secondsSpinner.setValue(seconds);
        },

        /**
         * Returns the value that is presented in the TimePicker
         *
         * @method getValue
         * @return {object} Object representing time in the TimePicker {hours: int, minutes: int, seconds: int}
         */
        getValue: function () {
            return {
                hours: this.hoursSpinner.getValue(),
                minutes: this.minutesSpinner.getValue(),
                seconds: this.secondsSpinner.getValue()
            };
        }

    });

});

define('widgets/TimePicker',['widgets/TimePicker/TimePicker'],function (main) {
                        return main;
                    });

