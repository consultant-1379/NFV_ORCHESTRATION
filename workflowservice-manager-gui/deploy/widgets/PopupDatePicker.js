/* Copyright (c) Ericsson 2014 */

define('styles!widgets/PopupDatePicker/Popup/_popup.less',[],function () { return '.elWidgets-PopupDatePicker-popup {\n  position: fixed;\n  border: 1px solid #b3b3b3;\n  border-radius: 3px;\n  padding: 5px 5px 0 5px;\n  width: 211px;\n  margin: 0;\n  z-index: 99999;\n  background-color: #FFFFFF;\n}\n.elWidgets-PopupDatePicker-closeButton {\n  width: 100%;\n  height: 15px;\n}\n.elWidgets-PopupDatePicker-closeButtonIcon {\n  float: right;\n}\n.elWidgets-PopupDatePicker-actionBlock {\n  padding: 6px 0;\n  text-align: center;\n}\n.elWidgets-PopupDatePicker-actionBlockButton {\n  margin: 0 4px;\n}\n';});

define('template!widgets/PopupDatePicker/Popup/_popup.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n    <div class=\"elWidgets-PopupDatePicker-actionBlock\">\n        <button class=\"ebBtn elWidgets-PopupDatePicker-actionBlockButton elWidgets-PopupDatePicker-okButton\">";
  foundHelper = helpers.applyButtonLabel;
  stack1 = foundHelper || depth0.applyButtonLabel;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "applyButtonLabel", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</button>\n    </div>\n    ";
  return buffer;}

  buffer += "<div class=\"elWidgets-PopupDatePicker-popup\">\n    <div class=\"elWidgets-PopupDatePicker-closeButton\">\n        <i class=\"ebIcon ebIcon_close ebIcon_interactive elWidgets-PopupDatePicker-closeButtonIcon\"></i>\n    </div>\n    <div class=\"elWidgets-PopupDatePicker-datePicker\"></div>\n    ";
  foundHelper = helpers.showButtons;
  stack1 = foundHelper || depth0.showButtons;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n</div>";
  return buffer;});});

define('widgets/PopupDatePicker/Popup/PopupView',['jscore/core','template!./_popup.html','styles!./_popup.less'],function (core, template, style) {

    return core.View.extend({

        getTemplate: function() {
            return template(this.options);
        },

        getStyle: function() {
            return style;
        },

        getDatePicker: function() {
            return this.getElement().find(".elWidgets-PopupDatePicker-datePicker");
        },

        getCloseButton: function() {
            return this.getElement().find(".elWidgets-PopupDatePicker-closeButton");
        },

        getOkButton: function() {
            return this.getElement().find(".elWidgets-PopupDatePicker-okButton");
        }

    });

});

define('widgets/PopupDatePicker/Popup/Popup',['jscore/core','./PopupView'],function (core, View) {

    return core.Widget.extend({

        view: function () {
            return new View(this.options);
        }

    });

});

define('styles!widgets/PopupDatePicker/_popupDatePicker.less',[],function () { return '.elWidgets-PopupDatePicker {\n  position: relative;\n  width: 160px;\n  outline: none;\n  height: 24px;\n}\n.elWidgets-PopupDatePicker-input {\n  position: relative;\n  margin: 0;\n}\n.elWidgets-PopupDatePicker-cancelButton {\n  position: absolute;\n  top: 5px;\n  right: 25px;\n  display: none;\n}\n';});

define('text!widgets/PopupDatePicker/_popupDatePicker.html',[],function () { return '<div class="elWidgets-PopupDatePicker">\n    <div class="elWidgets-PopupDatePicker-input">\n\t\t<input type="text" readonly="readonly" class="ebInput" />\n\t\t<i class="ebIcon ebIcon_close elWidgets-PopupDatePicker-cancelButton ebIcon_interactive"></i>\n        <i class="ebIcon ebIcon_calendar ebIcon_interactive"></i>\n\t</div>\n</div>';});

define('widgets/PopupDatePicker/PopupDatePickerView',['jscore/core','text!./_popupDatePicker.html','styles!./_popupDatePicker.less'],function (core, template, style) {
	'use strict';
	return core.View.extend({

		afterRender: function () {
			var element = this.getElement();
			this.input = element.find('.ebInput');
			this.calendarButton = element.find('.ebIcon_calendar');
			this.cancelButton = element.find('.elWidgets-PopupDatePicker-cancelButton');
		},

		getTemplate: function() {
			return template;
		},

		getStyle: function() {
			return style;
		},

		getOuterEl: function() {
			return this.element;
		},

		getInput: function() {
			return this.input;
		},

		setValue: function(value) {
			this.input.setValue(value);
		},

		getCancelButton: function() {
			return this.cancelButton;
		},


		getCalendarButton: function() {
			return this.calendarButton;
		}

	});

});

define('widgets/PopupDatePicker/PopupDatePicker',['widgets/WidgetCore','./PopupDatePickerView','widgets/DatePicker','widgets/DateTimePicker','./Popup/Popup','jscore/core'],function (WidgetCore, View, DatePicker, DateTimePicker, Popup, core) {

	/**
     * The PopupDatePicker class uses the Ericsson brand assets.<br>
     * The PopupDatePicker can be instantiated using the constructor PopupDatePicker.
     * Note that PopupDatePicker has the same constructor options as the <a href="DatePicker.html">DatePicker</a>.
     *
     * The following additional options are accepted:
     * <ul>
     * 	<li>showTime (Optional): a boolean to display time picker</li>
     * 	<li>applyButtonLabel (Optional): a string used to display apply button label, default value "OK" (only for showTime option true)</li>
     * </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>dateselect: Triggers when the date on the popup calendar is picked.</li>
     *       <li>dateclear: Triggers when the date in the input text field is cleared by clicking the cancel button at the right of the field.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @example
	 *   var popupDatePicker = new PopupDatePicker();
     *
     * @class PopupDatePicker
     * @extends WidgetCore
     */
	return WidgetCore.extend({

		View: View,

		/**
         * The init method is automatically called by the constructor when using the 'new' operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param {Object} options
         * @private
         */
		init: function (options) {
			this.options = options || {};
			this.popupHasMouse = false;
                        this.applyButtonLabelDefault = "OK";
		},

		/**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
		onViewReady: function() {

                    this.view.afterRender();

                    this.popup = new Popup({
                        showButtons: this.options.showTime,
                        applyButtonLabel: this.options.applyButtonLabel || this.applyButtonLabelDefault
                    });

                    // create DateTimePicker if showTime constructor option is true
                    if (this.options.showTime) {
                        this.datePicker = new DateTimePicker(this.options);
                    } else {
                        this.datePicker = new DatePicker(this.options);
                    }
                    this.datePicker.attachTo(this.popup.view.getDatePicker());

                    // Add handlers
                    this.setVisible(false);
                    addOpenHandler.call(this);
                    addCloseHandler.call(this);
                    addCancelHandler.call(this);
                    //add apply handler, that updates text field
                    if (this.options.showTime) {
                        addOkButtonHandler.call(this);
                    } else {
                        datePickerHandler.call(this);
                    }
		},

		/**
         * This method sets the visibility of the PopupDatePicker
         *
         * @method setVisible
         * @param {Boolean} isVisible
         */
		setVisible: function (isVisible) {
            if (isVisible) {
                this.popup.attachTo(core.Element.wrap(document.body));
            } else {
                this.popup.detach();
            }


			this.visible = isVisible;
		},

		/**
         * Gets value from the DatePicker.
         *
         * @method getValue
         * @return {Date}
         */
		getValue: function() {
			return this.datePicker.getValue();
		},

		/**
         * Gets value from the text field.
         *
         * @method getInput
         * @return {String}
         */
		getInput: function() {
			return this.view.getInput().getValue();
		},

        /**
         * Updates text field.
         *
         * @method setValue
         * @param {String} value
         */
        setValue: function(value) {
            var date = new Date(value); //Format value to date object

            //Check if valid date passed into function
            if (!isNaN(date.getMonth() && !isNaN(date.getDate()) + !isNaN(date.getFullYear()))) {
                //Format date
                this.view.setValue(formatDate.call(this, date));
            }
            else {
                this.view.setValue('');
            }

            this.datePicker.setValue(date); //Update DatePicker object with selected date

            this.view.getCancelButton().setStyle('display', 'block');   //Display cancel button in text box
        },

        onDestroy: function() {
            this.popup.destroy();
        }
	});

	/* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

        /**
        * Format date to DatePicker format month/day/year HH:mm:ss or
        * to DateTimePicker format month/day/year HH:mm:ss
        *
        * @private
        * @param {type} date
        * @return {String}
        */
       function formatDate(date) {
           var dateFormatTextBox;

           function addZero(n) {
               return n < 10 ? '0' + n : '' + n;
           }

           if (this.options.showTime) {
               dateFormatTextBox = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear() + " " + addZero(date.getHours()) +
                       ":" + addZero(date.getMinutes()) + ":" + addZero(date.getSeconds());
           } else {
               dateFormatTextBox = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
           }

           return dateFormatTextBox;
       }

        /**
         *    By pressing OK button gets the value of the date picker.
         *    Updates text field and displays the cancel button in the text field.
         *
         *    @method addOkButtonHandler
         *    @private
         */
        function addOkButtonHandler() {
            this.popup.view.getOkButton().addEventHandler('click', function() {
                var date = this.getValue();
                if (date) {
                    this.setValue(formatDate.call(this, date));
                    this.view.getCancelButton().setStyle('display', 'block');
                    this.setVisible(false);
                    this.trigger('dateselect');
                }
            }, this);
        }

	/**
     *    Gets the value of the date picker.
     *    Updates text field and displays the cancel button in the text field.
     *
     *    @method datePickerHandler
     *    @private
     */
	function datePickerHandler() {
		this.datePicker.addEventHandler('dateselect', function () {
			var date = this.getValue();
			if (date) {
				this.setValue((date.getMonth() + 1) +'/' + date.getDate() +'/'+  date.getFullYear());
				this.view.getCancelButton().setStyle('display', 'block');
				this.setVisible(false);
                this.trigger('dateselect');
			}
		}, this);
	}

	/**
     *    Hides the PopupDatePicker.
     *
     *    @method addCloseHandler
     *    @private
     */
	function addCloseHandler() {
		this.popup.view.getCloseButton().addEventHandler('click', function () {
			this.setVisible(false);
		}, this);
	}

	/**
     *    Clears the text field.
     *    Hides the cancel button in the text field.
     *
     *    @method addCancelHandler
     *    @private
     */
	function addCancelHandler() {
		this.view.getCancelButton().addEventHandler('click', function () {
			this.setValue('');
			this.view.getCancelButton().setStyle('display', 'none');
            this.trigger('dateclear');
		}, this);
	}

	/**
     *    Apply the click event onto the calendar icon to show/hide the popup area
     *    Sets up or clears interval.
	 *
     *    @method addOpenHandler
     *    @private
     */
	function addOpenHandler() {
		this.view.getCalendarButton().addEventHandler('click', function () {
			if (this.visible) {
				this.setVisible(false);
				if (this.posInterval) {
					clearInterval(this.posInterval);
					this.posInterval = undefined;
				}
			} else {
				this.setVisible(true);
				applyPosition.call(this);
			}
		}, this);

		this.popup.getElement().addEventHandler('mouseover', function () {
			this.popupHasMouse = true;
		}, this);

		this.popup.getElement().addEventHandler('mouseout', function () {
			this.popupHasMouse = false;
		}, this);
	}

	/**
     * Calculates new position, sets the position, and creates the interval.
     * Interval will change position of infopopup on pos change.
     *
     * @method applyPosition
     * @private
     */
	function applyPosition() {
        var pos = this.view.getInput().getPosition();

		if (!this._inputHeight) {
			this._inputHeight = this.view.getInput().getProperty('offsetHeight') + 3;
		}

        this.popup.getElement().setStyle({
            top: pos.top + this._inputHeight + 'px',
            left: pos.left + 'px'
        });

        if (!this.posInterval) {
            this.position = pos;
            this.posInterval = setInterval(function() {
                var curPosition = this.view.getInput().getPosition();
                if (curPosition.top === 0 && curPosition.right === 0 && curPosition.bottom === 0 && curPosition.left === 0) {
                    this.setVisible(false);
                } else if (this.position.top !== curPosition.top || this.position.left !== curPosition.left) {
                    this.popup.getElement().setStyle({
                        top: curPosition.top + this._inputHeight + 'px',
                        left: curPosition.left + 'px'
                    });
                    this.position = curPosition;

                }
            }.bind(this), 1000 / 24);
        }
    }

});

define('widgets/PopupDatePicker',['widgets/PopupDatePicker/PopupDatePicker'],function (main) {
                        return main;
                    });

