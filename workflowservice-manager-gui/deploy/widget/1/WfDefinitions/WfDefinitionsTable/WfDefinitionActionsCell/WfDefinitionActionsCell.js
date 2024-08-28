/* Copyright (c) Ericsson 2015 */

define("styles!widget/WfDefinitions/WfDefinitionsTable/WfDefinitionActionsCell/WfDefinitionActionsCell.less",function(){return".eaNFE_automation_UI-WfDefinitions-contentTable-buttonExecute {\n  min-width: 60px;\n  width: 60px;\n}\n.eaNFE_automation_UI-WfDefinitions-contentTable-buttonDiagram {\n  line-height: 1.5rem;\n  cursor: pointer;\n}\n.eaNFE_automation_UI-WfDefinitions-contentTable-buttonIcon {\n  width: 20px;\n  height: 15px;\n  float: left;\n  display: inline-block;\n  vertical-align: middle;\n  background: url('widget/1/resources/widget/WfDefinitions/WfDefinitionsTable/WfDefinitionActionsCell/diagram_icon.svg');\n  background-repeat: no-repeat;\n  cursor: pointer;\n}\n"}),define("text!widget/WfDefinitions/WfDefinitionsTable/WfDefinitionActionsCell/WfDefinitionActionsCell.html",function(){return'<td >\r\n	<div class="eaNFE_automation_UI-WfDefinitions-contentTable-buttonDiagram">\r\n		<i class="ebIcon eaNFE_automation_UI-WfDefinitions-contentTable-buttonIcon"></i>View Workflow</div>\r\n</td>'}),define("widget/WfDefinitions/WfDefinitionsTable/WfDefinitionActionsCell/WfDefinitionActionsCellView",["jscore/core","text!./WfDefinitionActionsCell.html","styles!./WfDefinitionActionsCell.less"],function(n,t,i){return n.View.extend({getTemplate:function(){return t},getStyle:function(){return i},getExecuteButton:function(){return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable-buttonExecute")},getDiagramButton:function(){return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable-buttonDiagram")}})}),define("widget/WfDefinitions/WfDefinitionsTable/WfDefinitionActionsCell/WfDefinitionActionsCell",["widgets/table/Cell","widgets/Tooltip","./WfDefinitionActionsCellView","widgets/Button"],function(n,t,i,e){return n.extend({View:i,onCellReady:function(){var n=this.view.getDiagramButton();n.addEventHandler("click",function(n){this.getEventBus().publish("showdiagram",this.getRow().getData())},this);var i=new t({parent:n,enabled:!0,contentText:"Show workflow diagram",modifiers:[{name:"size",value:"large"}]});i.attachTo(this.getElement())}})});