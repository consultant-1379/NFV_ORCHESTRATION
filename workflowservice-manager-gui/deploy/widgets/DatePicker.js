/* Copyright (c) Ericsson 2014 */

define('template!widgets/DatePicker/daysrows/_daysRows.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "<tr>\n            ";
  foundHelper = helpers.days;
  stack1 = foundHelper || depth0.days;
  stack2 = helpers.each;
  tmp1 = self.program(2, program2, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        </tr>";
  return buffer;}
function program2(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "<td><span class=\"";
  foundHelper = helpers.classes;
  stack1 = foundHelper || depth0.classes;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "classes", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.day;
  stack1 = foundHelper || depth0.day;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "day", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</span></td>";
  return buffer;}

  buffer += "<table class=\"ebDatePicker-body\">\n    <thead>\n    <tr>\n        <th>Mo</th>\n        <th>Tu</th>\n        <th>We</th>\n        <th>Th</th>\n        <th>Fr</th>\n        <th>Sa</th>\n        <th>Su</th>\n    </tr>\n    </thead>\n    <tbody>\n        ";
  foundHelper = helpers.weeks;
  stack1 = foundHelper || depth0.weeks;
  stack2 = helpers.each;
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    </tbody>\n</table>";
  return buffer;});});

define('widgets/DatePicker/daysrows/DaysRowsView',['jscore/core','template!./_daysRows.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template(this.options.template.rows);
        }

    });

});

define('widgets/DatePicker/daysrows/DaysRows',['widgets/WidgetCore','./DaysRowsView','widgets/utils/datePickerUtils'],function (WidgetCore, View, datePickerUtils) {
    'use strict';

    /**
     * The DaysRows class uses the Ericsson brand assets.
     *
     * @class DaysRows
     * @extends WidgetCore
     * @private
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        view: function () {
            return new View({
                template: {rows: this.options.rows}
            });
        },

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * The following options are accepted:
         *   <ul>
         *       <li>days: an array used as initial date in the DatePicker.</li>
         *       <li>selected: a date used as selected date in the DatePicker.</li>
         *       <li>_visibleYear: an int used as visible year in the DatePicker.</li>
         *       <li>_visibleMonth: an int used as visible month in the DatePicker.</li>
         *   </ul>
         *
         * @method init
         * @param {Object} options
         */
        init: function (options) {
            options = options || {};

            var days = options.days;
            if (days === undefined) {
                throw new Error('Option "days" should be defined for DaysRows widget!');
            }
            if (!(days instanceof Array)) {
                throw new Error('Option "days" should be an array');
            }

            this.dayClass = 'ebDatePicker-day';

            options.rows = {weeks: []};

            // Do loop for 6 weeks, each week has 7 days. Array from 0 to 41
            var currentDay, dayObj, weekObj;
            for (var i = 0; i < 42; i++) {
                currentDay = days[i];
                dayObj = {};

                if (!weekObj) {
                    weekObj = {days: []};
                }

                if (currentDay !== undefined) {
                    dayObj.day = currentDay;
                    dayObj.classes = this.dayClass + getModifiers.call(this, options._visibleYear, options._visibleMonth, currentDay);
                } else {
                    dayObj.day = '';
                    dayObj.classes = this.dayClass + ' ' + this.dayClass + '_other';
                }
                weekObj.days.push(dayObj);

                if ((i+1) % 7 === 0) {
                    options.rows.weeks.push(weekObj);
                    weekObj = undefined;
                }
            }

            this.options = options;
        }
    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function getModifiers(year, month, day) {
        var modifierClasses = [];

        // Handle the case if disabled
        if (isDayDisabled.call(this, year, month, day)) {
            modifierClasses.push(' ');
            modifierClasses.push(this.dayClass);
            modifierClasses.push('_disabled');
        }

        // Handle the case when selected
        var selected = (this.options.selected || null);
        if (selected !== null && selected.getFullYear() === year && selected.getMonth() === month && selected.getDate() === day) {
            modifierClasses.push(' ');
            modifierClasses.push(this.dayClass);
            modifierClasses.push('_selected');
        }

        // Handle the case when is today
        if (datePickerUtils.isToday(year, month, day)) {
            modifierClasses.push(' ');
            modifierClasses.push(this.dayClass);
            modifierClasses.push('_today');
        }

        return modifierClasses.join('');
    }

    function isDayDisabled(year, month, day) {
        var result = false;

        var disableDayFn = this.options.disableDay;
        if (disableDayFn) {
            if (disableDayFn(year, month, day)) {
                result = result || true;
            }
        }

        var dateInRangeFn = this.options.dateInRange;
        if (dateInRangeFn) {
            if (!dateInRangeFn(year, month, day)) {
                result = result || true;
            }
        }

        return result;
    }

});

define('text!widgets/DatePicker/_datePicker.html',[],function () { return '<div data-namespace="ebDatePicker">\n    <div data-name="head">\n        <div data-name="prev">\n            <div data-name="prevYear">\n                <i class="ebIcon ebIcon_small ebIcon_prevArrow_10px ebIcon_interactive eb_noVertAlign"></i>\n            </div>\n            <div data-name="prevMonth">\n                <i class="ebIcon ebIcon_small ebIcon_leftArrow_10px ebIcon_interactive eb_noVertAlign"></i>\n            </div>\n        </div>\n        <div data-name="monthYear"></div>\n        <div data-name="next">\n            <div data-name="nextYear">\n                <i class="ebIcon ebIcon_small ebIcon_nextArrow_10px ebIcon_interactive eb_noVertAlign"></i>\n            </div>\n            <div data-name="nextMonth">\n                <i class="ebIcon ebIcon_small ebIcon_rightArrow_10px ebIcon_interactive eb_noVertAlign"></i>\n            </div>\n        </div>\n    </div>\n    <div data-name="tableHolder"></div>\n</div>';});

define('widgets/DatePicker/DatePickerView',['jscore/core','text!./_datePicker.html','widgets/utils/dataNameUtils'],function (core, template, dataNameUtils) {
    'use strict';

    var DatePickerView = core.View.extend({

        getTemplate: function() {
            return dataNameUtils.translate(null, template, this);
        },

        getRoot: function() {
            return this.getElement();
        },

        getTable: function() {
            return this[DatePickerView.EL_TABLE];
        },

        getDays: function () {
            return this[DatePickerView.EL_DAYS];
        },

        getPrevYearButton: function() {
            return this[DatePickerView.EL_PREV_YEAR];
        },

        getPrevMonthButton: function() {
            return this[DatePickerView.EL_PREV_MONTH];
        },

        getMonthYearTitle: function() {
            return this[DatePickerView.EL_MONTH_YEAR];
        },

        getNextMonthButton: function() {
            return this[DatePickerView.EL_NEXT_MONTH];
        },

        getNextYearButton: function() {
            return this[DatePickerView.EL_NEXT_YEAR];
        }

    }, {
        'EL_PREV_YEAR': 'prevYear',
        'EL_PREV_MONTH': 'prevMonth',
        'EL_MONTH_YEAR': 'monthYear',
        'EL_NEXT_MONTH': 'nextMonth',
        'EL_NEXT_YEAR': 'nextYear',
        'EL_TABLE': 'tableHolder',
        'EL_DAYS': 'days'
    });

    return DatePickerView;

});

define('widgets/DatePicker/DatePicker',['widgets/WidgetCore','./DatePickerView','./daysrows/DaysRows','widgets/utils/datePickerUtils','widgets/utils/parserUtils','widgets/utils/domUtils'],function (WidgetCore, View, DaysRows, datePickerUtils, parserUtils, domUtils) {
    'use strict';

    /**
     * The DatePicker class uses the Ericsson brand assets.<br>
     * The DatePicker can be instantiated using the constructor DatePicker.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>change: Triggers when a date has been changed. Passes the changed date as a parameter.</li>
     *       <li>dateselect: Triggers when a date has been selected. Passes the selected date as a parameter.</li>
     *   </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>value: a date used as initial date for DatePicker.</li>
     *       <li>dateInRange: a function used as date range for DatePicker. By default date range not defined.</li>
     *       <li>disableDay: a function used as a disabled day for DatePicker. By default a disabled day not defined.</li>
     *       <li>defaultDate: a function used as default date to show when just opening DatePicker.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * <strong>How to disable dates outside specified range?</strong><br/>
     * dateInRange must return true if allowed, and false if not allowed. For example, to allow dates after today's date:
     *  <pre class="code prettyprint prettyprinted"><code>
     *  var datePicker = new DatePicker({
     *     dateInRange: function (year, month, day) {
     *       var against = new Date(year, month, day);
     *       var compare = new Date();
     *       return against.getTime() > compare.getTime();
     *     }.bind(this)
     *   });
     *  </code></pre>
     *
     * @class DatePicker
     * @extends WidgetCore
     * @baselined
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        View: View,

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            // Set default values
            this._selectedDate = null;

            var view = this.view;

            // Add handlers
            view.getPrevYearButton().addEventHandler('click', gotoPrevYear, this);
            view.getPrevMonthButton().addEventHandler('click', gotoPrevMonth, this);
            view.getNextMonthButton().addEventHandler('click', gotoNextMonth, this);
            view.getNextYearButton().addEventHandler('click', gotoNextYear, this);

            domUtils.delegate(view.getTable(), 'span:not(.ebDatePicker-day_other):not(.ebDatePicker-day_disabled)', 'click', function (event) {
                var day = parserUtils.parseInt(event.currentTarget.textContent);
                if (day && !isNaN(day)) {
                    var currentDate = datePickerUtils.cloneDate(this._selectedDate);
                    this._selectedDate = new Date(this._visibleYear, this._visibleMonth, day, 0, 0, 0, 0);
                    render.call(this);
                    this.trigger('dateselect', this.getValue());
                    if (!currentDate || 
                            currentDate.getFullYear() !== this._selectedDate.getFullYear() || 
                            currentDate.getDate() !== this._selectedDate.getDate() || 
                            currentDate.getDay() !== this._selectedDate.getDay())
                    {
                        this.trigger('change', this.getValue());
                    }
                }
            }, this);

            this.setValue(this.options.value || null);
        },

        /**
         * Gets value from the DatePicker.
         *
         * @method getValue
         * @return {Date} Date object representing value in the DatePicker or null if not selected
         */
        getValue: function () {
            if (this._selectedDate !== null) {
                return datePickerUtils.cloneDate(this._selectedDate);
            }
            return this._selectedDate;
        },

        /**
         * Sets date in the DatePicker.
         *
         * @method setValue
         * @param {Date} date Date object to represent selected day in the DatePicker
         */
        setValue: function (date) {
            // To make sure we do not change the object that was passed in
            date = datePickerUtils.cloneDate(date);

            if (!(date instanceof Date)) {
                date = getDefaultDate.call(this);
            }

            if (date instanceof Date) {
                this._selectedDate = date;
            }

            // Set visible month/year so that redraw draws provided month
            this._visibleMonth = date.getMonth();
            this._visibleYear = date.getFullYear();

            // Update month, if month/year has changed
            this.redraw();
        },

        /**
         * Since DatePicker.js is only the calendar part, it is lazy and you manually have to call when you need to redraw it.
         *
         * Mainly used by DatePickerInput.js to redraw calendar part prior to showing DatePicker popup.
         *
         * @method redraw
         */
        redraw: function () {
            if (!this._visibleMonth && !this._visibleYear) {
                var today = getDefaultDate.call(this);
                redraw.call(this, today.getFullYear(), today.getMonth());
            } else {
                redraw.call(this, this._visibleYear, this._visibleMonth);
            }
        }

    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function getDefaultDate() {
        var date;
        if (this.options.defaultDate && this.options.defaultDate instanceof Date) {
            date = this.options.defaultDate;
        } else if (this.options.defaultDate instanceof Function) {
            date = this.options.defaultDate();
        } else {
            date = new Date();

            // Minutes & seconds by default are 0
            date.setMinutes(0);
            date.setSeconds(0);
            date.setMilliseconds(0);
        }

        return date;
    }

    function redraw(year, month) {
        this._visibleMonth = month;
        this._visibleYear = year;

        var prevYear = datePickerUtils.getMonthYearRelative(year, month, -12);
        var prevMonth = datePickerUtils.getMonthYearRelative(year, month, -1);
        var nextMonth = datePickerUtils.getMonthYearRelative(year, month, 1);
        var nextYear = datePickerUtils.getMonthYearRelative(year, month, 12);

        // Handle disabling/enabling of previous month button
        var isPrevYearDisabled = isYearDisabled.call(this, prevYear.year);
        applyButtonState(this.view.getPrevYearButton(), isPrevYearDisabled);

        // Handle disabling/enabling of previous month button
        var isPrevMonthDisabled = isMonthDisabled.call(this, prevMonth.year, prevMonth.month);
        applyButtonState(this.view.getPrevMonthButton(), isPrevMonthDisabled);

        // Handle disabling/enabling of next month button
        var isNextMonthDisabled = isMonthDisabled.call(this, nextMonth.year, nextMonth.month);
        applyButtonState(this.view.getNextMonthButton(), isNextMonthDisabled);

        // Handle disabling/enabling of next month button
        var isNextYearDisabled = isYearDisabled.call(this, nextYear.year);
        applyButtonState(this.view.getNextYearButton(), isNextYearDisabled);

        render.call(this);
    }

    function applyButtonState(btnElement, isDisabled) {
        btnElement.setAttribute('disabled', isDisabled);
        if (isDisabled) {
            btnElement.find('.ebIcon').setModifier('disabled');
        } else {
            btnElement.find('.ebIcon').removeModifier('disabled');
        }
    }

    function render() {
        // Get days to display and update table
        var days = datePickerUtils.getDays(this._visibleYear, this._visibleMonth);

        // Clear data from days holder
        var children = this.view.getTable().children();
        if (children.length > 0) {
            children.forEach(function (child) {
                child.detach();
            });
        }

        // Create widget with days rows
        var daysRows = new DaysRows({
            days: days,
            _visibleYear: this._visibleYear,
            _visibleMonth: this._visibleMonth,
            dateInRange: this.options.dateInRange,
            disableDay: this.options.disableDay,
            selected: this.getValue()
        });
        this.view.getTable().append(daysRows.getElement());

        // Update month title
        var title = datePickerUtils.getMonthName(this._visibleMonth) + ' ' + this._visibleYear;
        this.view.getMonthYearTitle().setText(title);
    }

    function gotoPrevYear() {
        // Check that it is allowed to go to previous month
        var result = datePickerUtils.getMonthYearRelative(this._visibleYear, this._visibleMonth, -12);

        if (!isYearDisabled.call(this, result.year)) {
            redraw.call(this, result.year, result.month);
        }
    }

    function gotoPrevMonth() {
        // Check that it is allowed to go to previous month
        var result = datePickerUtils.getMonthYearRelative(this._visibleYear, this._visibleMonth, -1);

        if (!isMonthDisabled.call(this, result.year, result.month)) {
            redraw.call(this, result.year, result.month);
        }
    }

    function gotoNextMonth() {
        // Check that it is allowed to go to next month
        var result = datePickerUtils.getMonthYearRelative(this._visibleYear, this._visibleMonth, 1);

        if (!isMonthDisabled.call(this, result.year, result.month)) {
            redraw.call(this, result.year, result.month);
        }
    }

    function gotoNextYear() {
        // Check that it is allowed to go to next year
        var result = datePickerUtils.getMonthYearRelative(this._visibleYear, this._visibleMonth, 12);

        if (!isYearDisabled.call(this, result.year)) {
            redraw.call(this, result.year, result.month);
        }
    }

    function isYearDisabled(year) {
        var dateInRangeFn = this.options.dateInRange;
        if (dateInRangeFn) {
            if (!dateInRangeFn(year, this._visibleMonth, datePickerUtils.getDaysInMonth(year, this._visibleMonth)) && !dateInRangeFn(year, this._visibleMonth, 1)) {
                return true;
            }
        }
        return false;
    }

    function isMonthDisabled(year, month) {
        var dateInRangeFn = this.options.dateInRange;
        if (dateInRangeFn) {
            if (!dateInRangeFn(year, month, datePickerUtils.getDaysInMonth(year, month)) && !dateInRangeFn(year, month, 1)) {
                return true;
            }
        }
        return false;
    }

});

define('widgets/DatePicker',['widgets/DatePicker/DatePicker'],function (main) {
                        return main;
                    });

