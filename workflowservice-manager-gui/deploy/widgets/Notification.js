/* Copyright (c) Ericsson 2014 */

define('template!widgets/Notification/_notification.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1;
  buffer += " ebNotification_closed ebNotification_color_";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "color", { hash: {} }); }
  buffer += escapeExpression(stack1);
  return buffer;}

function program3(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n        <span class=\"ebNotification-icon\">\n            <i class=\"ebIcon ebIcon_";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "icon", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\"></i>\n        </span>\n        ";
  return buffer;}

function program5(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n        <label class=\"ebNotification-label\">";
  foundHelper = helpers.label;
  stack1 = foundHelper || depth0.label;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "label", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</label>\n        ";
  return buffer;}

function program7(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        <span class=\"ebNotification-close\">\n            <i class=\"ebIcon ebIcon_close";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  stack2 = helpers['if'];
  tmp1 = self.program(8, program8, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += " ebIcon_interactive\"></i>\n        </span>\n        ";
  return buffer;}
function program8(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "_";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "color", { hash: {} }); }
  buffer += escapeExpression(stack1);
  return buffer;}

  buffer += "<div class=\"ebNotification";
  foundHelper = helpers.color;
  stack1 = foundHelper || depth0.color;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\">\n    <div class=\"ebNotification-content\">\n        ";
  foundHelper = helpers.icon;
  stack1 = foundHelper || depth0.icon;
  stack2 = helpers['if'];
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        ";
  foundHelper = helpers.label;
  stack1 = foundHelper || depth0.label;
  stack2 = helpers['if'];
  tmp1 = self.program(5, program5, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        ";
  foundHelper = helpers.showCloseButton;
  stack1 = foundHelper || depth0.showCloseButton;
  stack2 = helpers['if'];
  tmp1 = self.program(7, program7, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    </div>\n</div>";
  return buffer;});});

define('widgets/Notification/NotificationView',['jscore/core','template!./_notification.html'],function (core, template) {
    'use strict';
    
    var NotificationView = core.View.extend({

        // TODO: Should be added to core.View and executed after render()
        afterRender: function () {
            var element = this.getElement();
            this.icon = element.find('.' + NotificationView.EL_ICON);
            this.label = element.find('.' + NotificationView.EL_LABEL);
            this.close = element.find('.' + NotificationView.EL_CLOSE);
        },

        getTemplate: function () {
            return template(this.options);
        },

        getIcon: function () {
            return this.icon;
        },

        getLabel: function () {
            return this.label;
        },

        getCloseButton: function () {
            return this.close;
        },

        hide: function () {
            this.closed = true;
            this.getElement().setModifier(NotificationView.MOD_CLOSED);
        },

        show: function () {
            this.closed = false;
            this.getElement().removeModifier(NotificationView.MOD_CLOSED);
        },

        isClosed: function () {
            return this.closed;
        }

    }, {
        'EL_ICON': 'ebNotification-icon',
        'EL_LABEL': 'ebNotification-label',
        'EL_CLOSE': 'ebNotification-close',
        'MOD_CLOSED': 'closed'
    });

    return NotificationView;

});

define('widgets/Notification/Notification',['widgets/WidgetCore','./NotificationView'],function (WidgetCore, View) {
    'use strict';

    /**
     * The Notification class wraps the Ericsson brand assets notification in a Widget.<br>
     * The Notification can be instantiated using the constructor Notification.
     *
     * <strong>Events:</strong>
     * <ul>
     *     <li>close - Triggers when the notification is closed, either by user input or auto-dismissal.</li>
     * </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>label (Optional): a string used as a notification caption.</li>
     *       <li>color (Optional): a string used as a notification type attribute. Available types are:
     *           <ul style="padding-left: 15px;">
     *               <li>paleBlue</li>
     *               <li>green</li>
     *               <li>yellow</li>
     *               <li>red</li>
     *           </ul>
     *       </li>
     *       <li>icon (Optional): a string used to define an icon to show. You can use any of the icons from ebIcon. Icons should be declared as follows, icon: 'iconName'. The ebIcon_ prefix is not required in the declaration. </li>
     *       <li>showCloseButton (Optional): a boolean used to determine whether a close button should be displayed. Default is false.</li>
     *       <li>showAsToast (Optional): a boolean used to determine whether the notification should be displayed at the top center of its parent. Default is false.</li>
     *       <li>autoDismiss (Optional): a boolean used to determine whether the notification should disappear after 10 seconds. Default is true.</li>
     *       <li>autoDismissDuration (Optional): an integer used to specify how long, in milliseconds, an auto-dismissing notification should display. Default is 10000.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class Notification
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        view: function () {
            return new View(this.options);
        },

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @private
         * @param {Object} options
         */
        init: function(options) {
            this.detachTimeout = null;
            this.options = options;
            this.options.autoDismiss = this.options.autoDismiss !== undefined ? this.options.autoDismiss : true;

            if ((!options.label || options.label === '') && !options.icon) {
                throw new Error('You must specify a label if you have no icon.');
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
            if (this.options.showCloseButton) {
                this.view.getCloseButton().addEventHandler('click', this.close, this);
            }

            if (this.options.showAsToast) {
                this.view.getElement().setModifier('toast');
            }

            if (this.options.autoDismiss) {
                var duration = parseInt(this.options.autoDismissDuration, 10) || 10000;
                setTimeout(this.close.bind(this), duration + 600);
            }
        },

        onDOMAttach: function() {
            this.view.show();
        },

        /**
         * Sets the notification label.
         *
         * @method setLabel
         * @param {String} label The new label for the Notification
         */
        setLabel: function (label) {
            this.view.getLabel().setText(label);
        },

        /**
         * Closes the notification and detach it. Triggers a close event.
         *
         * @method close
         * @param {Object} options Object containing options for method. Available options are:
         *                         animate (Optional): boolean defining whether the notification should fade out
         *                         (default is true)
         */
        close: function (options) {
            var animate = options ? (options.animate !== false) : true;
            if (this.detachTimeout) {
                clearTimeout(this.detachTimeout);
            }

            if (animate && !this.view.isClosed()) {
                this.detachTimeout = setTimeout(function () {
                    this.detach();
                }.bind(this), 700);
            } else {
                this.detach();
            }
            this.view.hide();
            this.trigger('close');
        }

    });
});

define('widgets/Notification',['widgets/Notification/Notification'],function (main) {
                        return main;
                    });

