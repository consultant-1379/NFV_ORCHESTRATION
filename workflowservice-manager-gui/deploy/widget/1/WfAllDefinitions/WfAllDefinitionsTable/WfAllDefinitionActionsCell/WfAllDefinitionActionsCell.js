/* Copyright (c) Ericsson 2015 */

define("styles!widget/WfAllDefinitions/WfAllDefinitionsTable/WfAllDefinitionActionsCell/WfAllDefinitionActionsCell.less",function(){return".eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonExecute {\n  min-width: 60px;\n  width: 60px;\n}\n.eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonDiagram {\n  min-width: 30px;\n  width: 30px;\n}\n"}),define("text!widget/WfAllDefinitions/WfAllDefinitionsTable/WfAllDefinitionActionsCell/WfAllDefinitionActionsCell.html",function(){return'<td >\r\n	<div class="eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonDiagram"></div>\r\n	<div class="ebBtn ebBtn_color_paleBlue eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonExecute">Start</div>\r\n</td>'}),define("widget/WfAllDefinitions/WfAllDefinitionsTable/WfAllDefinitionActionsCell/WfAllDefinitionActionsCellView",["jscore/core","text!./WfAllDefinitionActionsCell.html","styles!./WfAllDefinitionActionsCell.less"],function(t,e,n){return t.View.extend({getTemplate:function(){return e},getStyle:function(){return n},getExecuteButton:function(){return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonExecute")},getDiagramButton:function(){return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonDiagram")}})}),define("widget/WfAllDefinitions/WfAllDefinitionsTable/WfAllDefinitionActionsCell/WfAllDefinitionActionsCell",["widgets/table/Cell","widgets/Tooltip","./WfAllDefinitionActionsCellView","widgets/Button"],function(t,e,n,i){return t.extend({View:n,onCellReady:function(){var t=this.view.getExecuteButton();t.addEventHandler("click",function(t){this.getEventBus().publish("createinstance",this.getRow().getData())},this);var n=new e({parent:t,enabled:!0,contentText:"Create workflow instance",modifiers:[{name:"size",value:"large"}]});n.attachTo(this.getElement());var i=this.view.getDiagramButton();i.addEventHandler("click",function(t){this.getEventBus().publish("showdiagram",this.getRow().getData()),locCtlr.setLocation("WorkflowInstanceN?")},this);var l=new e({parent:i,enabled:!0,contentText:"Show workflow diagram",modifiers:[{name:"size",value:"large"}]});l.attachTo(this.getElement())}})});