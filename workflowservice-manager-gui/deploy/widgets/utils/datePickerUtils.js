/* Copyright (c) Ericsson 2014 */

define('widgets/utils/datePickerUtils',[],function () {
    'use strict';

    var monthNames = [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December'
    ];

    /**
     * The datePickerUtils used to simplify work with date and time.
     *
     * @class utils.datePickerUtils
     */
    return {

        /**
         * Checks if year is leap year.
         *
         * @method isLeapYear
         * @param {int} year Year to be tested
         * @return {boolean} true if provided year is leap year or false otherwise
         */
        isLeapYear: function (year) {
            // solution by Matti Virkkunen: http://stackoverflow.com/a/4881951
            return year % 4 === 0 && year % 100 !== 0 || year % 400 === 0;
        },

        /**
         * Checks if entered date is today date.
         *
         * @method isToday
         * @param {int} year
         * @param {int} month
         * @param {int} day
         * @return {boolean} true if provided triplet of year, month, day represents today, false otherwise
         */
        isToday: function (year, month, day) {
            var today = new Date();
            return today.getFullYear() === year && today.getMonth() === month && today.getDate() === day;
        },

        /**
         * Returns month name by specified range {0..11}, where 0 - January.
         *
         * @method getMonthName
         * @param {int} month Month in range {0..11} to represent month of interest, where 0 - January
         * @param {int} shortness Optional argument to specify how short the month name should be (for example 3 woul lead to Jan for January)
         * @return {String} month name by specified range and shortness
         */
        getMonthName: function (month, shortness) {
            if (!shortness) {
                return monthNames[month];
            } else {
                return monthNames[month].substr(0, shortness);
            }
        },

        /**
         * Formats specified date to human readable view in format "DD MMM YYY HH:MM:SS" (for example 12 May 2013 21:42:11)
         *
         * @method formatToHumanReadable
         * @param {Date} date Date object representing date of interest
         * @return {String} in format "DD MMM YYY HH:MM:SS" (for example "12 May 2013 21:42:11")
         */
        formatToHumanReadable: function (date) {
            if (date === null) {
                return '';
            }

            var result = [];

            var currDate = date.getDate();
            if (currDate < 10) {
                currDate = '0' + currDate;
            }

            result.push(currDate);
            result.push(' ');
            result.push(this.getMonthName(date.getMonth(), 3));
            result.push(' ');
            result.push(date.getFullYear());
            result.push(' ');

            var currHour = date.getHours();
            if (currHour < 10) {
                currHour = '0' + currHour;
            }

            result.push(currHour);
            result.push(':');

            var currMin = date.getMinutes();
            if (currMin < 10) {
                currMin = '0' + currMin;
            }

            result.push(currMin);
            result.push(':');

            var currSec = date.getSeconds();
            if (currSec < 10) {
                currSec = '0' + currSec;
            }

            result.push(currSec);

            return result.join('');
        },

        /**
         * Returns days amount in provided month & year.
         *
         * @method getDaysInMonth
         * @param {int} year Year of interest
         * @param {int} month Month in range {0..11} of provided year to represent month of interest, where 0 - January
         * @return {int} how many days are in provided month & year
         */
        getDaysInMonth: function (year, month) {
            return [31, this.isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
        },

        /**
         * Utility needed for previous/next buttons, to figure out dates relatively, for example:
         *  <ul>
         *      <li>previous year relativeness is -12 in terms of months</li>
         *      <li>next year relativeness is 12 in terms of months</li>
         *  </ul>
         *
         * @method getMonthYearRelative
         * @param year Year of interest
         * @param month Month in range {0..11} of provided year to represent month of interest, where 0 - January
         * @param relative Relativeness in terms of months (can be also negative). For example (2012, 1, -13) would return { 2012, 0 }
         * @return {Object} {year: int, month: int}
         */
        getMonthYearRelative: function (year, month, relative) {
            var sum = month + relative;
            var absSum = Math.abs(sum);

            var candidateYear = year, candidateMonth;
            candidateYear += Math.floor(sum / 12);

            if (relative < 0 && sum < 0) {
                candidateMonth = sum % 12 + 12;

                if (candidateMonth === 12) {
                    candidateMonth = 0;
                }
            } else {
                candidateMonth = absSum % 12;
            }

            return {
                year: candidateYear,
                month: candidateMonth
            };
        },

        /**
         * Utility needed to figure out in which weekday does month start. For example:
         *  <ul>
         *      <li>(2013, 4) => 3 meaning that may in year 2013 starts on Wednesday</li>
         *  </ul>
         *
         * @method getWeekDayWhenMonthStarts
         * @param {int} year Year of interest
         * @param {int} month Month in range {0..11} of provided year to represent month of interest, where 0 - January
         * @return {int} number representing day of week in range {1..7} where 1 is Monday
         */
        getWeekDayWhenMonthStarts: function (year, month) {
            var day = new Date(year, month, 1).getDay();

            // Since getDay() return in range 0..6, where 0 is sunday, need to compensate for that
            if (day === 0) {
                return 7;
            } else {
                return day;
            }
        },

        /**
         * Specific utility to get representation of month days starting from Monday, what means that days that are
         * in previous month are replaces with *undefined* until days in month of interest start. For example:
         *  <ul>
         *      <li>(2013, 4) => [undefined, undefined, 1, 2, 3, ..., 31]</li>
         *  </ul>
         *
         * @method getDays
         * @param {int} year Year of interest
         * @param {int} month Month in range {0..11} of provided year to represent month of interest, where 0 - January
         * @return {Array} containing provided year + month day numbers prepended with undefined for the days of previous month
         */
        getDays: function (year, month) {
            var days = [];

            // To get the index of day (starting from 0) when month starts, we use -1 as weekdays are in range {1..7} and start with 1
            var startsIndex = this.getWeekDayWhenMonthStarts(year, month) - 1;
            var daysInMonth = this.getDaysInMonth(year, month);

            var dayIndex = 0;
            for (dayIndex; dayIndex < daysInMonth; dayIndex++) {
                var index = startsIndex + dayIndex;

                // As month days start with 1, not 0, we have to increment by 1 to get the actual day representation from index
                var dayValue = dayIndex + 1;

                days[index] = dayValue;
            }

            return days;
        },

        /**
         * Removes time from date
         *
         * @method removeTimeFromDate
         * @param {Date} date Date of interest
         * @return {Date} Date that has hours, minutes, seconds & milliseconds set to 0
         */
        removeTimeFromDate: function (date) {
            if (arguments.length === 0) {
                throw new Error('No object passed in for DatePickerUtils.removeTimeFromDate');
            }

            if (!(date instanceof Date)) {
                throw new Error('No date object passed in for DatePickerUtils.removeTimeFromDate');
            }

            var result = this.cloneDate(date);

            result.setHours(0);
            result.setMinutes(0);
            result.setSeconds(0);
            result.setMilliseconds(0);

            return result;
        },

        /**
         * Utility used to create new instance of provided Date object
         *
         * @method cloneDate
         * @param {Date} date
         * @return {Date} New instance of Date object representing the same date as provided
         */
        cloneDate: function (date) {
            if (date === null) {
                return date;
            }

            if (date instanceof Date) {
                // To make sure we do not change the object that was passed in
                return new Date(date.getTime());
            } else {
                throw new Error('No date object passed in for DatePickerUtils.cloneDate');
            }
        }
    };

});

