/* Copyright (c) Ericsson 2014 */

define('template!widgets/ProgressBar/_progressBar.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " ebIcon_";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "icon", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ";
  return buffer;}

function program3(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " ";
  foundHelper = helpers.label;
  stack1 = foundHelper || depth0.label;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "label", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ";
  return buffer;}

function program5(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " ebProgressBar_color_";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "color", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ";
  return buffer;}

function program7(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " ebProgressBar_color_";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "color", { hash: {} }); }
  buffer += escapeExpression(stack1) + " ";
  return buffer;}

  buffer += "<div class=\"ebProgressBar\">\n	<div class=\"ebProgressBar-header\" style=\"display:none;\">\n		<span class=\"ebProgressBar-icon\">\n			<i class=\"ebIcon ";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\"></i>\n		</span>\n		<span class=\"ebProgressBar-label\">";
  foundHelper = helpers.label;
  stack1 = foundHelper || depth0.label;
  stack2 = helpers['if'];
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "</span>\n	</div>\n	<div class=\"ebProgressBar-content\">		\n		<progress class=\"ebProgressBar-bar ";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\" min=\"5\" max=\"100\"></progress>\n		<span class=\"ebProgressBar-value ";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\"></span>\n	</div>\n</div>";
  return buffer;});});

define('widgets/ProgressBar/ProgressBarView',['jscore/core','template!./_progressBar.html'],function (core, template) {
    'use strict';

    var ProgressBarView = core.View.extend({

        iconModifier:null,
        
        getTemplate: function () {
            return template(this.options);
        },

        getIconParent: function () {
            return this.getElement().find('.' + ProgressBarView.EL_ICON_PARENT);
        },

        getIcon: function() {
            var parentElem = this.getIconParent();
            return parentElem.find('.' + ProgressBarView.EL_ICON);
        },

        getLabel: function () {
            return this.getElement().find('.' + ProgressBarView.EL_LABEL);
        },

        getHeader: function () {
            return this.getElement().find('.' + ProgressBarView.EL_HEADER);
        },

        getBar: function () {
            return this.getElement().find('.' + ProgressBarView.EL_BAR);
        },

        getProgressValue: function () {
            return this.getElement().find('.' + ProgressBarView.EL_VALUE);
        },

        setValue: function(value) {
            var bar = this.getBar();
            var progressValue = this.getProgressValue();

            if (progressValue >= 100) {
                progressValue = 100;
            }
            else if (progressValue < 5) {
                progressValue = 5;
            }

            bar.setAttribute('value', value);
            progressValue.setText(value + "%");
        },

        setLabel: function(label) {
            var labelElem = this.getLabel();
            labelElem.setText(label);
            this.showHeader();
        },

        setIcon: function (icon) {
            var iconElem = this.getIcon();

            if(this.iconModifier) {
                iconElem.removeModifier(this.iconModifier);
            }
            
            this.iconModifier = icon;            
            iconElem.setModifier(icon);

            // if we are setting first icon than show the icon
            // the main reason for this while init we set it to
            // display none
            if(iconElem.getStyle("display") === 'none') {
                iconElem.setStyle("display", "inline-block");
            }

            this.showHeader();
        },

        showHeader: function () {
            var headerElem = this.getHeader();
            if(headerElem.getStyle("display") === 'none') {
                this.toggleElement(headerElem);    
            }
            
        },

        toggleElement: function (elem) {
            if(elem.getStyle("display") === 'none'){
                elem.setStyle("display", "block");
            }
            else {
                elem.setStyle("display", "none");
            }
        }

    }, {
        'EL_HEADER': 'ebProgressBar-header',
        'EL_ICON_PARENT': 'ebProgressBar-icon',
        'EL_LABEL': 'ebProgressBar-label',
        'EL_VALUE': 'ebProgressBar-value',
        'EL_BAR': 'ebProgressBar-bar',
        'EL_ICON': 'ebIcon'

    });

    return ProgressBarView;

});

define('widgets/ProgressBar/ProgressBar',['widgets/WidgetCore','./ProgressBarView','jscore/core'],function (WidgetCore, View, core) {
	'use strict';

	/**
	 * The ProgressBar class uses the Ericsson brand assets.<br>
	 * The ProgressBar can be instantiated using the constructor ProgressBar.
	 *
	 * <strong>Events:</strong>
	 *   <ul>
	 *       <li>No Events</li>
	 *   </ul>
	 *   The following options are accepted:
	 * <ul>
	 * 		<li>label (Optional): a string used as a progress bar caption.</li>
	 * 		<li>icon (Optional): a string used to define an icon to show next to label. You can use any of the icons from ebIcon. Icons should be declared as follows, icon: 'iconName'. The ebIcon_ prefix is not required in the declaration.</li>
	 * 		<li>value (Optional): a number used as start value for progress bar. If value is not supplied, default is set to 5.</li>
	 * 		<li>color (Optional): a string used as a color to progress bar. Available colors are:
	 *		      <ul style="padding-left: 15px;">
	 *		          <li>purple (Default color - if none passed)</li>
	 *		          <li>red</li>
	 *		          <li>orange</li>
	 *		          <li>yellow</li>
	 *		          <li>green</li>
	 *		          <li>darkGreen</li>
	 *		          <li>paleBlue</li>
	 *		      </ul>
	 * 		</li>
	 * </ul>    
	 * <a name="modifierAvailableList"></a>
	 * <strong>Modifiers:</strong>
	 *  <ul>
	 *      <li>No modifier available.</li>
	 *  </ul>
	 *
	 * @example
	 *   var ProgressBar = new ProgressBar();
	 *

	 * @beta
	 *
	 * @class ProgressBar
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
		},

		/**
		 * Overrides method from widget.
		 * Executes every time, when added back to the screen.
		 *
		 * @method onViewReady
		 * @private
		 */
		onViewReady: function () {
			this.setColor(this.options.color || "purple");
			this.setValue(this.options.value !== undefined? this.options.value : 5);

			this.view.iconModifier = this.options.icon;


		   // if we have label or icon we need to show Header section
		   if (this.options.label || this.options.icon) {
				this.view.showHeader();
			}

			if(!this.options.icon) {
				this.view.toggleElement(this.view.getIcon());
			}

		},

		/**
		 * set color for progress bar
		 * 
		 * @method setColor
		 * @param  {string} color Ex. paleBlue, green, darkGreen, red, orange
		 */
		setColor: function (color) {
			this.view.getBar().setModifier("color", color);
			this.view.getProgressValue().setModifier("color", color);
		},

		/**
		 * Updated the value of progress bar.
		 * 
		 * @method setValue
		 * @param  {number} value accepts all positive number upto 100
		 */
		setValue: function (value) {
			if (value && typeof value === 'number') {
				this.view.setValue(value);	
			}
			else {
				throw new Error ('You must specify value and it should be number.');
			}
		},

		/**
		 * Set/Update the label for progress bar.
		 * 
		 * @method setLabel
		 * @param  {string} label text for progress bar
		 */
		setLabel: function (label) {
			if (label && typeof label === 'string') {
				this.view.setLabel(label);	
			}
			else {
				throw new Error ('You must enter valid label.');
			}
		},

		/**
		 * Set icon for label and progress bar.
		 * 
		 * @method setIcon
		 * @param  {string} icon Ex. tick, close, etc.
		 */
		setIcon: function (icon) {
			if (icon && typeof icon === 'string') {
				this.view.setIcon(icon);	
			}
			else {
				throw new Error ('You must specify valid icon.');
			}
		}

	});

	/* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

});

define('widgets/ProgressBar',['widgets/ProgressBar/ProgressBar'],function (main) {
                        return main;
                    });

