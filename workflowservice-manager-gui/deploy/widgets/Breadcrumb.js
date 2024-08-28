/* Copyright (c) Ericsson 2014 */

define('template!widgets/Breadcrumb/BreadcrumbItem/_breadcrumbItem.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        <span class=\"ebBreadcrumbs-arrow\" tabindex=\"1\">\n            <i class=\"ebIcon ebIcon_small ebIcon_interactive ebIcon_downArrow_10px\"></i>\n        </span>\n    <div class=\"ebBreadcrumbs-list\">\n        <ul class=\"ebComponentList\">\n            ";
  foundHelper = helpers.children;
  stack1 = foundHelper || depth0.children;
  stack2 = helpers.each;
  tmp1 = self.program(2, program2, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n        </ul>\n    </div>\n    ";
  return buffer;}
function program2(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n            <li class=\"ebComponentList-item";
  foundHelper = helpers.selected;
  stack1 = foundHelper || depth0.selected;
  stack2 = helpers['if'];
  tmp1 = self.program(3, program3, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\">\n                <a href=\"";
  foundHelper = helpers.url;
  stack1 = foundHelper || depth0.url;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "url", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\" class=\"ebComponentList-link\" title=\"";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</a>\n            </li>\n            ";
  return buffer;}
function program3(depth0,data) {
  
  
  return " ebComponentList-item_selected";}

  buffer += "<div class=\"ebBreadcrumbs-item\">\n    <a href=\"";
  foundHelper = helpers.url;
  stack1 = foundHelper || depth0.url;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "url", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\" class=\"ebBreadcrumbs-link\" title=\"";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">";
  foundHelper = helpers.name;
  stack1 = foundHelper || depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</a>\n    ";
  foundHelper = helpers.children;
  stack1 = foundHelper || depth0.children;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n</div>";
  return buffer;});});

define('widgets/Breadcrumb/BreadcrumbItem/BreadcrumbItemView',['jscore/core','template!./_breadcrumbItem.html'],function (core, template) {
    'use strict';

    var BreadcrumbItemView = core.View.extend({

        // TODO: Should be added to core.View and executed after render()
        afterRender: function () {
            this.link = this.getElement().find('.' + BreadcrumbItemView.EL_LINK);
            this.arrow = this.getElement().find('.' + BreadcrumbItemView.EL_ARROW);
        },

        getTemplate: function () {
            return template(this.options.template.breadcrumb);
        },

        getLink: function () {
            return this.link;
        },

        getArrow: function () {
            return this.arrow;
        }

    }, {
        EL_LINK: 'ebBreadcrumbs-link',
        EL_ARROW: 'ebBreadcrumbs-arrow'
    });

    return BreadcrumbItemView;

});

define('widgets/Breadcrumb/BreadcrumbItem/BreadcrumbItem',['widgets/WidgetCore','./BreadcrumbItemView','widgets/utils/domUtils'],function (WidgetCore, View, domUtils) {
    'use strict';

    /**
     * The BreadcrumbItem Widget is needed to create navigation parts for the Breadcrumb Widget.<br>
     * The BreadcrumbItem can be instantiated using the constructor BreadcrumbItem.
     *
     * The following options are accepted:
     *   <ul>
     *       <li>breadcrumb: an object used to create the Breadcrumb item.</li>
     *   </ul>
     *
     * @example
     *   var breadcrumbItem = new BreadcrumbItem({
     *     breadcrumb: {
     *       name: 'Level 1',
     *       url: '#url1',
     *       children: [
     *         {name: 'Item 1', url: '#item1', selected: true},
     *         {name: 'Item 2', url: '#item2', selected: false},
     *         {name: 'Item 3', url: '#item3'}
     *       ]
     *     }
     *   });
     *
     * @class BreadcrumbItem
     * @extends WidgetCore
     * @private
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param {Object} options
         * @private
         */
        init: function (options) {
            this.options = options || [];
            this.view = new View({
                template: {breadcrumb: options.breadcrumb}
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
            var arrow = this.view.getArrow();
            if (arrow) {
                var timeoutToOpen, timeoutToHide, isClicked = false, isOpened = false;
                this.getElement().addEventHandler('mouseover', function () {
                    clearTimeout(timeoutToHide);

                    if (!isOpened) {
                        timeoutToOpen = setTimeout(function () {
                            isOpened = true;
                            arrow.trigger('focus');
                        }, 50);
                    }
                }, this);

                this.getElement().addEventHandler('mouseout', function () {
                    clearTimeout(timeoutToOpen);

                    if (!isClicked && isOpened) {
                        timeoutToHide = setTimeout(function () {
                            isOpened = false;
                            arrow.trigger('blur');
                        }, 2000);
                    }
                }, this);

                arrow.addEventHandler('click', function () {
                    clearTimeout(timeoutToHide);

                    isClicked = true;
                    if (!isOpened) {
                        isOpened = true;
                        arrow.trigger('focus');
                    }
                }, this);

                arrow.addEventHandler('blur', function () {
                    isClicked = false;
                    isOpened = false;
                });
            }
        }

    });

});

define('text!widgets/Breadcrumb/_breadcrumb.html',[],function () { return '<div class="ebBreadcrumbs"></div>';});

define('widgets/Breadcrumb/BreadcrumbView',['jscore/core','text!./_breadcrumb.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        }

    });

});

define('widgets/Breadcrumb/Breadcrumb',['widgets/WidgetCore','./BreadcrumbView','./BreadcrumbItem/BreadcrumbItem','widgets/utils/domUtils','jscore/core'],function (WidgetCore, View, BreadcrumbItem, domUtils, core) {
    'use strict';

    // TODO: add to the description when will be part of container: "This widget is part of the container."
    /**
     * The Breadcrumb widget provides navigation through one app.<br>
     * The Breadcrumb can be instantiated using the constructor Breadcrumb.
     *
     * The following options are accepted:
     *   <ul>
     *       <li>breadcrumbs: an array of objects used to create the Breadcrumb widget.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @example
     *   var breadcrumb = new Breadcrumb({
     *     data: [
     *       {name: 'Level 1', url: '#level1'},
     *       {
     *         name: 'Level 2',
     *         url: '#level2',
     *         children: [
     *           {name: 'Item 1', url: '#item1'},
     *           {name: 'Item 2', url: '#item2'},
     *           {name: 'Item 3', url: '#item3'}
     *         ]
     *       },
     *       {name: 'Item 1', url: '#item1'}
     *     ]
     *   });
     *
     * @class Breadcrumb
     * @extends WidgetCore
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
            this.breadcrumbItems = [];
            this.dots = undefined;
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            var breadcrumbObjects = this.options.data || this.options.breadcrumbs;

            if (breadcrumbObjects && breadcrumbObjects.length > 0) {
                if (breadcrumbObjects.length > 1) {
                    var currentUrl = breadcrumbObjects[breadcrumbObjects.length - 1].url;
                    var penultimateItem = breadcrumbObjects[breadcrumbObjects.length - 2];
                    if (penultimateItem.children) {
                        penultimateItem.children.forEach(function (breadcrumbObj) {
                            if (breadcrumbObj.url === currentUrl) {
                                breadcrumbObj.selected = true;
                            }
                        });
                    }
                }

                breadcrumbObjects.forEach(function (breadcrumbObj) {
                    var breadcrumbItem = new BreadcrumbItem({
                        breadcrumb: breadcrumbObj
                    });

                    this.breadcrumbItems.push(breadcrumbItem);
                    breadcrumbItem.attachTo(this.getElement());
                }, this);
            } else {
                throw new Error('A breadcrumb array should be defined for the Breadcrumb widget!');
            }

            this._windowEvtId = core.Window.addEventHandler('resize', this.resize.bind(this));
        },

        /**
         * Overrides method from widget.
         * Executes before destroy, remove event handlers.
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function () {
            if (this._windowEvtId) {
                core.Window.removeEventHandler(this._windowEvtId);
            }
        },

        /**
         * Needed as the drawing mechanism is dependent on actual DOM dimensions.
         *
         * @method onDOMAttach
         * @private
         */
        onDOMAttach: function() {
            this.resize.call(this);
        },

        /**
         * Resizes breadcrumbs
         *
         * @method resize
         */
        resize: function (showAll) {
            // Get the widths of all breadcrumbs only for the first time
            if (!this.breadcrumbWidths) {
                this.breadcrumbWidths = [];
                this.totalWidth = 0;

                this.breadcrumbItems.forEach(function (breadcrumbItem) {
                    var width = breadcrumbItem.getElement().getProperty('offsetWidth');
                    this.breadcrumbWidths.push(width);
                    this.totalWidth += width;
                }, this);
            }

            // Get the width of the container that holds the breadcrumb widget
            var containerWidth = this.getElement().getProperty('offsetWidth');

            // Detach all of the breadcrumbs regardless
            this.breadcrumbItems.forEach(function (breadcrumbItem) {
                breadcrumbItem.detach();
            }, this);

            // Create and detach dots if it exists
            createDots.call(this);
            this.dots.detach();

            var i;

            // If the breadcrumb width is larger than its container holding it, we need to cut off some items
            if (containerWidth < this.totalWidth && !showAll) {
                 // Always attach the first breadcrumb anyways
                this.breadcrumbItems[0].attach();
                // Keep track of the width used for each item so far
                var totalWidthUsed = this.breadcrumbWidths[0];
                var endIndex = 0;

                // Figure out what breadcrumbs to hide with the ...
                for (i = this.breadcrumbWidths.length - 1; i > 0; i--) {
                    totalWidthUsed += this.breadcrumbWidths[i];
                    if (totalWidthUsed > containerWidth) {
                        endIndex = i;
                        break;
                    }
                }

                this.dots.attach();

                for (i = endIndex + 2; i < this.breadcrumbItems.length; i++) {
                    this.breadcrumbItems[i].attach();
                }

            } else {
                for (i = 0; i < this.breadcrumbItems.length; i++) {
                    this.breadcrumbItems[i].attach();
                }
            }
        }
    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function createDots() {
        if (!this.dots) {
            this.dots = new BreadcrumbItem({
                breadcrumb: {
                    name: '...',
                    url: '#'
                }
            });

            // Attach the dots
            this.dots.getElement().addEventHandler('click', function() {
                this.resize(true);
            }.bind(this));
            this.dots.attachTo(this.getElement());
            this.dotsWidth = this.dots.getElement().getProperty('offsetWidth');
        }

    }

});

define('widgets/Breadcrumb',['widgets/Breadcrumb/Breadcrumb'],function (main) {
                        return main;
                    });

