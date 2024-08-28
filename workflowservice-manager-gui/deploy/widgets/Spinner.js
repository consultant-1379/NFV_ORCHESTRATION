/* Copyright (c) Ericsson 2014 */

define('text!widgets/Spinner/_spinner.html',[],function () { return '<table data-namespace="ebSpinner">\n    <tr>\n        <td data-name="label"></td>\n        <td class="ebSpinner-holder">\n            <div data-name="up" class="ebSpinner-iconUpHolder">\n                <i class="ebIcon ebIcon_upArrow ebIcon_interactive"></i>\n            </div>\n            <div class="ebSpinner-inputHolder">\n                <input type="text" data-name="input" class="ebInput ebInput_miniW ebInput_txtCenter eb_noMargin" value="20"/>\n            </div>\n            <div data-name="down" class="ebSpinner-iconDownHolder">\n                <i class="ebIcon ebIcon_downArrow ebIcon_interactive"></i>\n            </div>\n        </td>\n        <td data-name="value"></td>\n    </tr>\n</table>';});

define('widgets/Spinner/SpinnerView',['jscore/core','text!./_spinner.html','widgets/utils/dataNameUtils'],function (core, template, dataNameUtils) {
    'use strict';

    var SpinnerView = core.View.extend({

        getTemplate: function () {
            return dataNameUtils.translate(null, template, this);
        },

        getRoot: function () {
            return this.getElement();
        },

        getPrefix: function () {
            return this[SpinnerView.EL_PREFIX];
        },

        getPostfix: function () {
            return this[SpinnerView.EL_POSTFIX];
        },

        getUpButton: function () {
            return this[SpinnerView.EL_UP_BUTTON];
        },

        getDownButton: function () {
            return this[SpinnerView.EL_DOWN_BUTTON];
        },

        getInput: function () {
            return this[SpinnerView.EL_INPUT];
        }

    }, {
        'EL_PREFIX': 'label',
        'EL_POSTFIX': 'value',
        'EL_UP_BUTTON': 'up',
        'EL_DOWN_BUTTON': 'down',
        'EL_INPUT': 'input'
    });

    return SpinnerView;

});

define('widgets/Spinner/Spinner',['widgets/WidgetCore','./SpinnerView','widgets/utils/parserUtils'],function (WidgetCore, View, parserUtils) {
    'use strict';

    // 8: Backspace
    // 9: Tab
    // 35/36: Home/End
    // 37/39: Arrow left / right
    // 46: Delete
    // 116: refresh
    var allowedControlKeysArr = [8, 9, 35, 36, 37, 39, 46, 116];


    /**
     * The Spinner class uses the Ericsson brand assets.<br>
     * The Spinner can be instantiated using the constructor Spinner.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>change: Triggers when the Spinner value has changed. Passes the changed value as a parameter.</li>
     *   </ul>
     *   
     * The following options are accepted:
     *   <ul>
     *       <li>value:     an integer used as a default value for the Spinner. Default is min value or 0.</li>
     *       <li>min:       an integer used as a min value for the Spinner. Default is 0.</li>
     *       <li>max:       an integer used as a max value for the Spinner.</li>
     *       <li>prefix:    a string used as a label for the Spinner.</li>
     *       <li>postfix:   a string used as a value type for the Spinner.</li>
     *       <li>inputSize: a string used to define size for the Spinner input field.</li>
     *       <li>minNumberDigitsFormat: format the displayed value as with minNumberDigitsFormat number of digits. (minNumberDigitsFormat set to 4 would produce 0000)</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class Spinner
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
         * @private
         *
         * @param {Object} options
         */
        init: function (options) {
            this.options = options || {};

            this.minDelay = 100;
            this.maxDelay = 400;
            this.delayStep = 50;
        },

        /**
         * Overrides method from widget.<br>
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {

            // Always start with minimal value
            if (this.options.min === undefined) {
                this.options.min = 0;
            }
            // If not defined default value, start with minimum value
            if (this.options.value === undefined) {
                this.options.value = this.options.min;
            }

            this.enable();
        },

        /**
         * Sets the text that appears in front of Spinner input
         *
         * @method setPrefix
         * @param {String} text Text in front of Spinner input
         */
        setPrefix: function (text) {
            if (text && text.trim() !== '') {
                var prefix = this.view.getPrefix();
                prefix.setStyle('display', 'table-cell');
                prefix.setText(text);
            }
        },

        /**
         * Sets the text that appears in behind of Spinner input
         *
         * @method setPostfix
         * @param {String} text Text behind Spinner input
         */
        setPostfix: function (text) {
            if (text && text.trim() !== '') {
                var postfix = this.view.getPostfix();
                postfix.setStyle('display', 'table-cell');
                postfix.setText(text);
            }
        },

        /**
         * Sets the value that will be visible in Spinner
         *
         * @method setValue
         * @param {int|String} value Integer or String containing Integer
         */
        setValue: function (value) {
            if (value !== undefined && value !== null) {
                if (value instanceof String || typeof(value) === 'string') {
                    if (value instanceof String) {
                        value = value.toString();
                    }
                    var num = value === '' ? 0 : parserUtils.parseInt(value);
                    if (!isNaN(num)) {
                        updateValue.call(this, getInRange.call(this, num));
                        update.call(this);
                    }
                } else if (/[0-9]|-/.test(value)) {
                    updateValue.call(this, getInRange.call(this, value));
                    update.call(this);
                }
            }
        },

        /**
         * Returns the value that is presented in Spinner input field
         *
         * @method getValue
         * @return {int}
         */
        getValue: function () {
            return this.value;
        },

        /**
         * A method which allows to define input field size.
         *
         * @method setInputSize
         * @param {string} inputSize Can be selected from available sizes: ['mini', 'small', 'long', 'xLong']<br>
         *   ['miniW', 'smallW', 'longW', 'xLongW'] - deprecated names for input field sizes
         */
        setInputSize: function (inputSize) {
            // TODO: should be removed
            if (['miniW', 'smallW', 'longW', 'xLongW'].indexOf(inputSize) > -1) {
                inputSize = inputSize.replace('W', '');
            }
            this.view.getInput().setModifier('width', inputSize);
        },
        /**
         * Enables the spinner
         *
         * @method enable
         */
        enable: function () {
            this.upButtonMouseDownEvent = this.view.getUpButton().addEventHandler('mousedown', startAutoIncrement, this);
            this.upButtonMouseUpEvent = this.view.getUpButton().addEventHandler('mouseup', endAutoIncrement, this);
            this.upButtonMouseOutEvent = this.view.getUpButton().addEventHandler('mouseout', endAutoIncrement, this);
            this.downButtonMouseDownEvent = this.view.getDownButton().addEventHandler('mousedown', startAutoDecrement, this);
            this.downButtonMouseUpEvent = this.view.getDownButton().addEventHandler('mouseup', endAutoDecrement, this);
            this.downButtonMouseOutEvent = this.view.getDownButton().addEventHandler('mouseout', endAutoDecrement, this);

            this.inputClickEvent = this.view.getInput().addEventHandler('click', selectAllInput, this);
            this.inputKeyPressEvent = this.view.getInput().addEventHandler('keypress', handleKeyPress, this);
            this.inputKeyUpEvent = this.view.getInput().addEventHandler('keyup', handleKeyUp, this);
            this.inputBlueEvent = this.view.getInput().addEventHandler('blur', handleInputBlur, this);

            this.setValue(this.value !== undefined ? this.value : this.options.value);
            this.setPrefix(this.options.prefix || '');
            this.setPostfix(this.options.postfix || '');
            this.setInputSize(this.options.inputSize || 'miniW');

            if (this._enabled === false) {
                //Remove titles if this.disable() called previously
                this.view.getUpButton().removeAttribute('title', 'Disabled');
                this.view.getDownButton().removeAttribute('title', 'Disabled');
                this.view.getInput().removeAttribute('title', 'Disabled');

                //Remove attributes if this.disable() called previously
                this.view.getInput().removeAttribute('readonly');
                this.view.getInput().removeModifier('disabled');
                this.view.getUpButton().find('.ebIcon').removeModifier('disabled');
                this.view.getDownButton().find('.ebIcon').removeModifier('disabled');
            }
            this._enabled = true;
        },
        /**
         * Disables the spinner
         *
         * @method disable
         */
        disable: function () {
            this._enabled = false;

            //Remove event handlers for up/down button
            this.view.getUpButton().removeEventHandler(this.upButtonMouseDownEvent);
            this.view.getUpButton().removeEventHandler(this.upButtonMouseUpEvent);
            this.view.getUpButton().removeEventHandler(this.upButtonMouseOutEvent);
            this.view.getUpButton().removeEventHandler(this.downButtonMouseDownEvent);
            this.view.getUpButton().removeEventHandler(this.downButtonMouseUpEvent);
            this.view.getUpButton().removeEventHandler(this.downButtonMouseOutEvent);

            //Remove event handlers for input
            this.view.getInput().removeEventHandler(this.inputClickEvent);
            this.view.getInput().removeEventHandler(this.inputKeyPressEvent);
            this.view.getInput().removeEventHandler(this.inputKeyUpEvent);
            this.view.getInput().removeEventHandler(this.inputBlueEvent);

            //Set titles to Disabled
            this.view.getUpButton().setAttribute('title', 'Disabled');
            this.view.getDownButton().setAttribute('title', 'Disabled');
            this.view.getInput().setAttribute('title', 'Disabled');

            //Set modifier to disabled
            this.view.getInput().setAttribute('readonly', 'readonly');
            this.view.getInput().setModifier('disabled');
            this.view.getDownButton().find('.ebIcon').setModifier('disabled');
            this.view.getUpButton().find('.ebIcon').setModifier('disabled');
        }
    });


    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function updateValue(value){
        var hasChanged = this.value !== undefined && this.value !== value;
        this.value = value;
        if (hasChanged) {
            this.trigger("change", value);
        }
    }

    function convertToXDigitsFormat(nbrDigits, value) {
        var valStr = value.toString(),
            zeroStrGen = function (nbr, str) {
                return nbr === 0 ? str : zeroStrGen(nbr -1, ('0' + str));
            };
        return valStr.length < nbrDigits ? zeroStrGen(nbrDigits - valStr.length, valStr) : valStr;
    }

    function inRange(value) {
        var min = this.options.min;
        var max = this.options.max;

        if (max === undefined) {
            return value >= min;
        } else {
            return value <= max && value >= min;
        }
    }

    function getInRange(value) {
        value = value > this.options.max ? this.options.max : value;
        return value < this.options.min ? this.options.min : value;
    }

    function selectAllInput() {
        this.view.getInput().element.select();
    }

    function handleKeyPress(event) {
        // event.which
        // Returns the numeric keyCode of the key pressed, or the character code (charCode) for an alphanumeric key pressed.
        var whichKey = event.originalEvent.which,
            keyCode = event.originalEvent.keyCode,
            isAllowedControlKey = allowedControlKeysArr.indexOf(keyCode) !== -1;

        var key = String.fromCharCode(whichKey);
        if (!isAllowedControlKey && whichKey < 45 || (whichKey >= 112 && whichKey <= 123) || event.originalEvent.ctrlKey) {
            // Since Firefox is very restrictive on these keys, we have to manually allow for special keys like backspace
            // Here is good reading about madness about keycodes http://unixpapa.com/js/key.html
            // + more info on keycodes http://www.webonweboff.com/tips/js/event_key_codes.aspx
            event.preventDefault();
        }

        // Regular expression to test if provided value is integer (can be also negative)
        var isInteger = /[0-9\-]/;
        if (!isAllowedControlKey && !isInteger.test(key)) {
            event.preventDefault();
        }
    }

    function handleKeyUp() {
        var inputValue = this.view.getInput().getValue(),
            value = 0;
        if (inputValue === '') {
            return;
        } else {
            value = parserUtils.parseInt(inputValue);
        }

        if (!inRange.call(this, value) || value.toString() !== inputValue) {
            if (inputValue.indexOf('0') === 0) {
                updateValue.call(this, value);
            }

            // Roll back value to old one if this.value is not changed
            update.call(this);
        } else {
            updateValue.call(this, value);
            applyModifiers.call(this);
        }
    }

    function handleInputBlur() {
        var inputValue = this.view.getInput().getValue();
        if (inputValue === '') {
            update.call(this);
        }
    }

    function autoIncrement() {
        clearTimeout(this.autoIncrementTimeout);

        if (increment.call(this)) {
            if (this.autoIncrementDelay >= this.minDelay) {
                this.autoIncrementDelay -= this.delayStep;
            }

            this.autoIncrementTimeout = setTimeout(
                autoIncrement.bind(this),
                this.autoIncrementDelay
            );
        }
    }

    function startAutoIncrement() {
        this.autoIncrementDelay = this.maxDelay;
        this.autoIncrementTimeout = setTimeout(
            autoIncrement.bind(this),
            this.autoIncrementDelay
        );
    }

    function endAutoIncrement() {
        if (this.autoIncrementTimeout) {
            clearTimeout(this.autoIncrementTimeout);
            increment.call(this);

            // this.autoIncrementTimeout should be removed fully, otherwise onMouseHover will continue to increase value
            delete this.autoIncrementTimeout;
        }
    }

    function increment() {
        if (inRange.call(this, this.value + 1)) {
            updateValue.call(this, this.value + 1);
            update.call(this);
            return true;
        }
        return false;
    }

    function autoDecrement() {
        clearTimeout(this.autoDecrementTimeout);

        if (decrement.call(this)) {
            if (this.autoDecrementDelay >= this.minDelay) {
                this.autoDecrementDelay -= this.delayStep;
            }

            this.autoDecrementTimeout = setTimeout(
                autoDecrement.bind(this),
                this.autoDecrementDelay
            );
        }
    }

    function startAutoDecrement() {
        this.autoDecrementDelay = this.maxDelay;
        this.autoDecrementTimeout = setTimeout(
            autoDecrement.bind(this),
            this.autoDecrementDelay
        );
    }

    function endAutoDecrement() {
        if (this.autoDecrementTimeout) {
            clearTimeout(this.autoDecrementTimeout);
            decrement.call(this);

            // this.autoDecrementTimeout should be removed fully, otherwise onMouseHover will continue to decrease value
            delete this.autoDecrementTimeout;
        }
    }

    function decrement() {
        if (inRange.call(this, this.value - 1)) {
            updateValue.call(this, this.value - 1);
            update.call(this);
            return true;
        }
        return false;
    }

    function update() {
        // Set input value
        this.view.getInput().setValue(this.options.minNumberDigitsFormat !== undefined ? convertToXDigitsFormat(this.options.minNumberDigitsFormat, this.value) : this.value);

        // Enable/Disable up/down buttons
        applyModifiers.call(this);
    }

    function applyModifiers() {
        // Up button
        if (inRange.call(this, this.value + 1)) {
            this.view.getUpButton().find('.ebIcon').removeModifier('disabled');
        } else {
            this.view.getUpButton().find('.ebIcon').setModifier('disabled');
        }

        // Down button
        if (inRange.call(this, this.value - 1)) {
            this.view.getDownButton().find('.ebIcon').removeModifier('disabled');
        } else {
            this.view.getDownButton().find('.ebIcon').setModifier('disabled');
        }
    }
});

define('widgets/Spinner',['widgets/Spinner/Spinner'],function (main) {
                        return main;
                    });

