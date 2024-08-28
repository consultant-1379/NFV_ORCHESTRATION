/* Copyright (c) Ericsson 2014 */

define('widgets/utils/parserUtils',[],function () {
    'use strict';

    /**
     * The parserUtils used to parse strings.
     *
     * @class utils.parserUtils
     */
    return {
        /**
         * Parse string to integer param
         *
         * @method parseInt
         * @param {String} value String which requires parsing
         * @return {int} Parsed integer
         */
        parseInt: function (value) {
            // Since JS parseInt is not very restrictive, we have to make our own, as "0r" for example would be treated as 0
            // see https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/parseInt at the end

            if (/^\-?([0-9]+|Infinity)$/.test(value)) {
                return Number(value);
            }
            return NaN;
        }
    };
});

