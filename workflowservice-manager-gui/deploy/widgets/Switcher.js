/* Copyright (c) Ericsson 2014 */

define('widgets/utils/textUtils',[],function () {
    'use strict';

    var textUtils = {
        getTextWidth: function (text, font) {
            // use cached canvas for better performance or
            // create new canvas
            var canvas = textUtils.getTextWidth.canvas || (textUtils.getTextWidth.canvas = document.createElement("canvas")),
                context = canvas.getContext("2d");
            context.font = font;
            var metrics = context.measureText(text);
            return Math.round(metrics.width);
        }
    };

    return textUtils;
});

define('template!widgets/Switcher/_switcher.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " style=\"width: ";
  foundHelper = helpers.width;
  stack1 = foundHelper || depth0.width;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "width", { hash: {} }); }
  buffer += escapeExpression(stack1) + ";\"";
  return buffer;}

function program3(depth0,data) {
  
  
  return "checked";}

function program5(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " ";
  foundHelper = helpers.onLabel;
  stack1 = foundHelper || depth0.onLabel;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "onLabel", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ";
  return buffer;}

function program7(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " ";
  foundHelper = helpers.offLabel;
  stack1 = foundHelper || depth0.offLabel;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "offLabel", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ";
  return buffer;}

  buffer += "<label class=\"ebSwitcher\" ";
  foundHelper = helpers.width;
  stack1 = foundHelper || depth0.width;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += ">\n    <input type=\"checkbox\" class=\"ebSwitcher-checkbox\" ";
  foundHelper = helpers.value;
  stack1 = foundHelper || depth0.value;
  stack2 = helpers['if'];
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "/>\n    <div class=\"ebSwitcher-body\">\n        <div class=\"ebSwitcher-onLabel\">";
  foundHelper = helpers.onLabel;
  stack1 = foundHelper || depth0.onLabel;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "</div>\n        <div class=\"ebSwitcher-switch\"></div>\n        <div class=\"ebSwitcher-offLabel\">";
  foundHelper = helpers.offLabel;
  stack1 = foundHelper || depth0.offLabel;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "</div>\n    </div>\n</label> ";
  return buffer;});});

define('widgets/Switcher/SwitcherView',['jscore/core','template!./_switcher.html'],function (core, template) {
	'use strict';

	var SwitcherView = core.View.extend({

		getTemplate: function() {
			return template(this.options);
		},

		getCheckbox: function() {
			return this.getElement().find('.' + SwitcherView.EL_CHECKBOX);
		},

		getOnLabel: function() {
			return this.getElement().find('.' + SwitcherView.EL_ONLABEL);
		},

		getOffLabel: function() {
			return this.getElement().find('.' + SwitcherView.EL_OFFLABEL);
		},

		getLabel: function() {
			var checkBoxElem = this.getCheckbox();
			var value=null;

			value = (checkBoxElem.getProperty('checked')) ?
					this.getOnLabel().getText() :
					this.getOffLabel().getText();

			return value;
		},

		getValue: function() {
			var checkBoxElem = this.getCheckbox();
			return (checkBoxElem.getProperty('checked')) ? true : false;
		},

		setValue: function(value) {
			var checkBox = this.getCheckbox();
				checkBox.setProperty("checked", value);
		},

		setWidth: function(value) {
			this.getElement().setStyle("width", value);
		},

		setColor: function(colorObject) {
			var onLabel = this.getOnLabel();
			var offLabel = this.getOffLabel();

			for (var key in colorObject) {
				var objValue = colorObject[key];
				switch(key) {
					case 'onColor': 
						if(objValue.indexOf("#") !== 0 ) {
							onLabel.setModifier(objValue, undefined, "ebBgColor");
						} else {
							onLabel.setStyle("background-color", objValue);
						}
						break;
					case 'offColor':
						if(objValue.indexOf("#") !== 0 ) {
							offLabel.setModifier(objValue, undefined, "ebBgColor");
						} else {
							offLabel.setStyle("background-color", objValue);
						}
						break;
					default:
						break;
				}
			}
		},

	}, {
		'EL_CHECKBOX': 'ebSwitcher-checkbox',
		'EL_ONLABEL': 'ebSwitcher-onLabel',
		'EL_OFFLABEL': 'ebSwitcher-offLabel'
	});

	return SwitcherView;

});

define('widgets/Switcher/Switcher',['widgets/WidgetCore','./SwitcherView','../utils/textUtils'],function (WidgetCore, View, textUtils) {
    'use strict';

    /**
     * The Switcher class uses the Ericsson brand assets.<br>
     * The Switcher can be instantiated using the constructor Switcher.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>change - Triggers when the checkbox value has changed.</li>
     *   </ul>
     *   The following options are accepted:
     * <ul>
     *        <li>value (Optional): a boolean to set default position of switch.</li>
     *        <li>onLabel (Required): a string used display text when switch is on.</li>
     *        <li>offLabel (Required): a string used display text when switch is off.</li>
     *        <li>onColor (Optional): a string used display background color when switch is on.</li>
     *        <li>offColor (Optional): a string used display background color when switch is off.</li>
     * </ul>
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @example
     *   var switcherWidget = new Switcher({
	 *   	"value": true,
	 *    	"onLabel" : "good",
	 *    	"offLabel" : "bad",
	 *    	"onColor" " "purple",
	 *    	"offColor" : "grey"  
	 *   
	 *   });
     *
     * @beta
     *
     * @class Switcher
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        view: function () {
            return new View(this.options);
        },

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

            if ((!options.onLabel || options.onLabel === '') && (!options.offLabel || options.offLabel === '')) {
                throw new Error('You must specify a on/off label');
            }
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {

            if (this.options.onColor || this.options.offColor) {
                this.setColor(this.options);
            }

            this.getElement().addEventHandler('change', function () {
                this.trigger('change');
            }, this);

        },

        /**
         * Set width for Switcher
         *
         * @method onDOMAttach
         * @private
         */
        onDOMAttach: function () {
            this.setWidth();
        },

        /**
         * setWidth of switcher container
         * depending on length of off/on Label test
         *
         * @method setWidth
         * @private
         */
        setWidth: function () {
            var elem = this.getElement();
            var font = elem.getStyle("font-size") + " " + elem.getStyle("font-family");
            var onLabelWidth, offLabelWidth;
            var width;

            onLabelWidth = textUtils.getTextWidth(this.options.offLabel, font);
            offLabelWidth = textUtils.getTextWidth(this.options.onLabel, font);

            width = (onLabelWidth > offLabelWidth) ? onLabelWidth : offLabelWidth;
            //width is width of longest text
            //24 is width of swtich
            //14 is rest width of label + input, etc
            width = width + 24 + 14;

            this.view.setWidth(width);
        },

        /**
         * get the value of checkbox
         *
         * @method getValue
         * @return {boolean} returns true/false based on checkbox is checked/unchecked
         */
        getValue: function () {
            return this.view.getValue();
        },

        /**
         * get the Label for checkbox
         *
         * @method getLabel
         * @return {string} return on/off labels
         */
        getLabel: function () {
            return this.view.getLabel();
        },

        /**
         * set checkbox to checked/uncheck
         *
         * @method setValue
         * @param  {boolean} value true/false to set checked/unchecked box
         */
        setValue: function (value) {
            if (typeof value === 'boolean') {
                this.view.setValue(value);
            }
            else {
                throw new Error("Value need to be true or false.");
            }
        },

        /**
         * set on/off background color
         *
         * @method setColor
         * @param  {object} colorObject contains onColor/offColor values
         */
        setColor: function (colorObject) {
            if (colorObject && typeof colorObject === 'object') {
                this.view.setColor(colorObject);
            }
            else {
                throw new Error("Colors need to be specify.");
            }
        }
    });

});

define('widgets/Switcher',['widgets/Switcher/Switcher'],function (main) {
                        return main;
                    });

