/* Copyright (c) Ericsson 2015 */

define("styles!widget/WfDefinitions/WfDefinitionsTable/filteroptions/FilterOptions.less",function(){return".eaFiltering-FilterOptions {\n  height: 100%;\n  font-size: 0px;\n  width: 26px;\n  min-width: 26px;\n  line-height: 32px;\n  background-repeat: no-repeat !important;\n  background-color: #eeeeee !important;\n  background: url('widget/1/resources/widget/WfDefinitions/WfDefinitionsTable/filteroptions/../../../../widget/WorkflowInstanceN/Filter_icon_grey2.svg');\n  background-size: 16px;\n  vertical-align: middle;\n  text-align: center;\n  background-position: 50% 60%;\n}\n"}),define("text!widget/WfDefinitions/WfDefinitionsTable/filteroptions/FilterOptions.html",function(){return'<div class="eaFiltering-FilterOptions"></div>'}),define("widget/WfDefinitions/WfDefinitionsTable/filteroptions/FilterOptionsView",["jscore/core","text!./FilterOptions.html","styles!./FilterOptions.less"],function(e,t,i){return e.View.extend({getTemplate:function(){return t},getStyle:function(){return i},setSelected:function(e){this.getElement().setText(e)},getSelected:function(){return this.getElement().getText()}})}),define("widget/WfDefinitions/WfDefinitionsTable/filteroptions/FilterOptions",["widgets/ItemsControl","./FilterOptionsView"],function(e,t){return e.extend({View:t,onControlReady:function(){this.options.width="auto",this.view.setSelected("=")},onItemSelected:function(e){this.view.setSelected(e.name),this.trigger("change")},getValue:function(){return this.view.getSelected()}})});