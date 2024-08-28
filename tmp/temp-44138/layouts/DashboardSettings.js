define('styles!layouts/dashboard/Settings/settings.less',[],function () { return '.elWidgets-Dashboard-settings-layouts {\n  display: inline-block;\n  width: 150px;\n  margin-right: 5px;\n  margin-top: 10px;\n}\n.elWidgets-Dashboard-settings-layouts-layout {\n  width: 100%;\n  height: 100px;\n  clear: both;\n  cursor: pointer;\n  border: 1px solid transparent;\n}\n.elWidgets-Dashboard-settings-layouts-layout_selected {\n  border-color: #00a9d4;\n}\n.elWidgets-Dashboard-settings-layouts-layout-column {\n  background-color: #eff0f0;\n  float: left;\n  height: 100%;\n  margin: 0;\n  border: solid 1px #CCCCCC;\n  box-sizing: border-box;\n}\n.elWidgets-Dashboard-settings-layouts-layout-column-text {\n  text-align: center;\n  padding-top: 40px;\n}\n.elWidgets-Dashboard-settings-layouts-layout_onecolumn .elWidgets-Dashboard-settings-layouts-layout-column {\n  width: 100%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_twocolumns .elWidgets-Dashboard-settings-layouts-layout-column {\n  width: 50%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_twocolumns3070 .elWidgets-Dashboard-settings-layouts-layout-column {\n  width: 30%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_twocolumns3070 .elWidgets-Dashboard-settings-layouts-layout-column:nth-child(2) {\n  width: 70%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_twocolumns7030 .elWidgets-Dashboard-settings-layouts-layout-column {\n  width: 70%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_twocolumns7030 .elWidgets-Dashboard-settings-layouts-layout-column:nth-child(2) {\n  width: 30%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_threecolumns .elWidgets-Dashboard-settings-layouts-layout-column {\n  width: 33.33%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_threecolumns304030 .elWidgets-Dashboard-settings-layouts-layout-column {\n  width: 30%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_threecolumns304030 .elWidgets-Dashboard-settings-layouts-layout-column:nth-child(2) {\n  width: 40%;\n}\n.elWidgets-Dashboard-settings-layouts-layout_fourcolumns .elWidgets-Dashboard-settings-layouts-layout-column {\n  width: 25%;\n}\n';});

define('template!layouts/dashboard/Settings/settings.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n        <div class=\"elWidgets-Dashboard-settings-layouts elWidgets-Dashboard-settings-layouts_";
  stack1 = depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this.name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">\n            <h4 class=\"elWidgets-Dashboard-settings-layouts-layout-header\">";
  stack1 = depth0.name;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this.name", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</h4>\n            <div class=\"elWidgets-Dashboard-settings-layouts-layout elWidgets-Dashboard-settings-layouts-layout_";
  stack1 = depth0.modifier;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this.modifier", { hash: {} }); }
  buffer += escapeExpression(stack1) + "\">\n                ";
  stack1 = depth0.columns;
  stack2 = helpers.each;
  tmp1 = self.program(2, program2, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n            </div>\n        </div>\n        ";
  return buffer;}
function program2(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n                <div class=\"elWidgets-Dashboard-settings-layouts-layout-column\">\n                    <div class=\"elWidgets-Dashboard-settings-layouts-layout-column-text\">";
  stack1 = depth0;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "this", { hash: {} }); }
  buffer += escapeExpression(stack1) + "%</div></div>\n                ";
  return buffer;}

  buffer += "<div class=\"elWidgets-Dashboard-settings\">\n    <div>\n        ";
  foundHelper = helpers.layouts;
  stack1 = foundHelper || depth0.layouts;
  stack2 = helpers.each;
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    </div>\n</div>";
  return buffer;});});

define('layouts/dashboard/Settings/SettingsView',['jscore/core','template!./settings.html','styles!./settings.less'],function (core, template, style) {

    return core.View.extend({

        getTemplate: function() {
            return template(this.options);
        },

        getStyle: function() {
            return style;
        },
        getButton: function(){
            return this.getElement().find('.elWidgets-Dashboard-settings-button');
        }

    });

});

define('layouts/dashboard/Settings/Settings',['jscore/core','./SettingsView'],function (core, View) {

    /**
     * The Dashboard class uses the Ericsson brand assets.<br>
     * The DashboardSettings can be instantiated using the constructor Dashboard.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>DashboardSettings(Object options)</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li><strong>availableLayouts:</strong> An array with all the layout that will be shown. All layout are shown by default.  Available dashboard layout are: "one-column", "two-columns", "two-columns-70-30", "two-columns-30-70", "three-columns", "three-columns-30-40-30" and "four-columns"</li>
     *       <li><strong>selected:</strong> Layout selected by default.  Available dashboard layout are: "one-column", "two-columns", "two-columns-70-30", "two-columns-30-70", "three-columns", "three-columns-30-40-30" and "four-columns"</li>
     *   </ul>
     * <strong>Events:</strong>
     *   <ul>
     *       <li><strong>change:</strong> triggered when user selects a layout. Selected layout is passed. </li>
     *   </ul>
     *
     * <strong>Example:</strong>
     * <pre>
     *  var dashboardSettings = new DashboardSettings();
     *  dashboardSettings.addEventHandler('change', function(newLayout){
     *      dashboard.changeLayout(newLayout);
     *  }.bind(this));
     * </pre>
     *
     *
     * @class DashboardSettings
     * @extends WidgetCore
     * @beta
     */

    return core.Widget.extend({
        view: function() {
            return new View(this.options);
        },
        init: function (options){
            this.options.defaultLayouts = [
                {name: 'one-column', modifier: 'onecolumn', columns: [100]},
                {name: 'two-columns', modifier: 'twocolumns', columns: [50, 50]},
                {name: 'two-columns-30-70', modifier: 'twocolumns3070', columns: [30,70]},
                {name: 'two-columns-70-30', modifier: 'twocolumns7030', columns: [30,70]},
                {name: 'three-columns', modifier: 'threecolumns', columns: [33,33,33]},
                {name: 'three-columns-30-40-30',modifier: 'threecolumns304030', columns: [30,40,30]},
                {name: 'four-columns', modifier: 'fourcolumns', columns: [25,25,25,25]}
            ];
            if(options && options.availableLayouts && options.availableLayouts.length > 0){
                this.options.layouts = [];
                options.availableLayouts.forEach(function(layout){
                    this.options.defaultLayouts.forEach(function(defaultLayout){
                        if(defaultLayout.name === layout){
                            this.options.layouts.push(defaultLayout);
                        }
                    }.bind(this));
                }.bind(this));
            }else{
                this.options.layouts = this.options.defaultLayouts;
            }
        },
        onViewReady: function(){
            this.options.layouts.forEach(function(layout){
                var element = this.view.getElement().find('.elWidgets-Dashboard-settings-layouts-layout_' + layout.modifier);
                element.addEventHandler('click', function(){
                    this.select(layout.name);
                }.bind(this));
            }.bind(this));

            if(this.options.selected){
                this.select(this.options.selected, true);
            }
        },
        onDOMAttach: function(){

        },
        /**
         * Clears selection.
         *
         * @method clearSelected
         * @returns {string} selected
         */
        clearSelected: function(){
            this.options.layouts.forEach(function(layout){
                var element = this.view.getElement().find('.elWidgets-Dashboard-settings-layouts-layout_' + layout.modifier);
                element.removeModifier('selected');
            }.bind(this));
            this.selected = undefined;
        },
        /**
         * Selects layout. Pass silent true in order to avoid triggering the change event.
         *
         * @method select
         * @param {string} layout
         * @param {Boolean} silent
         */
        select: function(layout, silent){
            var selected = getLayoutElement.call(this, layout);
            if(selected){
                this.clearSelected();
                selected.setModifier('selected');
                this.selected = layout;
                if(!silent){
                    this.trigger('change', layout);
                }
            }
        },
        /**
         * Returns the selected layout.
         *
         * @method getSelected
         * @returns {string} selected
         */
        getSelected: function(){
            return this.selected;
        }

    });

    function getLayoutElement(layoutname){
        var element = false;
        this.options.layouts.forEach(function(layout){
            if(layout.name == layoutname){
                element = this.view.getElement().find('.elWidgets-Dashboard-settings-layouts-layout_' + layout.modifier);
            }
        }.bind(this));
        return element;
    }
});

define('layouts/DashboardSettings',['layouts/dashboard/Settings/Settings'],function (main) {
                        return main;
                    });

