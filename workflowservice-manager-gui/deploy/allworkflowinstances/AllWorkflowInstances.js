/* Copyright (c) Ericsson 2015 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
define("styles!allworkflowinstances/allWorkflowInstances.less",function(){return".eaAllWorkflowInstances-MainRegion {\n  padding-left: 45px;\n}\n.eaAllWorkflowInstances-MainRegion-Title {\n  font-size: 3rem;\n  padding-top: 8px;\n  padding-bottom: 5px;\n}\n.eaAllWorkflowInstances-MainRegion-Heading {\n  padding: 0px;\n}\n.eaAllWorkflowInstances-MainRegion-Breadcrumb {\n  margin-top: 1.2rem;\n}\n.eaAllWorkflowInstances-MainRegion-DividerBottom {\n  border-bottom: 1px solid #666666;\n  padding-top: 20px;\n}\n.eaAllWorkflowInstances-MainRegion-ActiveInstancesIcon {\n  display: inline-block;\n  vertical-align: middle;\n  background: url('allworkflowinstances/resources/allworkflowinstances/active_icon_24px.svg');\n  background-repeat: no-repeat;\n  height: 40px;\n  width: 25px;\n  padding-left: 22px;\n}\n.eaAllWorkflowInstances-MainRegion-CancelledInstancesIcon {\n  display: inline-block;\n  vertical-align: middle;\n  background: url('allworkflowinstances/resources/allworkflowinstances/cancelled_icon_24px.svg');\n  background-repeat: no-repeat;\n  height: 40px;\n  width: 25px;\n  padding-left: 22px;\n}\n.eaAllWorkflowInstances-MainRegion-WorkflowInstancesName {\n  padding-left: 110px;\n  padding-top: 15px;\n  cursor: pointer;\n}\n.eaAllWorkflowInstances-MainRegion-WorkflowInstances {\n  padding-left: 22px;\n  cursor: pointer;\n}\n.eaAllWorkflowInstances-MainRegion-ActiveInstancesName {\n  padding-left: 110px;\n  padding-top: 15px;\n  cursor: pointer;\n}\n.eaAllWorkflowInstances-MainRegion-ActiveInstances {\n  cursor: pointer;\n}\n.eaAllWorkflowInstances-MainRegion-InactiveInstancesName {\n  padding-left: 110px;\n  padding-top: 15px;\n}\n.eaAllWorkflowInstances-MainRegion-InactiveInstances {\n  padding-bottom: 30px;\n}\n.eaAllWorkflowInstances-MainRegion-PausedInstancesName {\n  padding-left: 110px;\n  padding-top: 15px;\n}\n.eaAllWorkflowInstances-MainRegion-PausedInstances {\n  padding-bottom: 30px;\n}\n.eaAllWorkflowInstances-MainRegion-CancelledInstancesName {\n  padding-left: 110px;\n  padding-top: 15px;\n  cursor: pointer;\n}\n.eaAllWorkflowInstances-MainRegion-CancelledInstances {\n  cursor: pointer;\n}\n.eaAllWorkflowInstances-MainRegion-WorkflowInstancesDivider {\n  border-bottom: #cccccc solid 1px;\n  padding-top: 58px;\n}\n.eaAllWorkflowInstances-MainRegion-ActiveInstancesDivider {\n  border-bottom: #cccccc solid 1px;\n  padding-top: 35px;\n}\n.eaAllWorkflowInstances-MainRegion-Panel {\n  display: inline-block;\n  padding-top: 5px;\n}\n.eaAllWorkflowInstances-MainRegion-Panel-iconHolder {\n  margin-top: 1.2rem;\n  padding: 0.8rem 0;\n  background-color: #E6E6E6;\n  width: 1555px;\n  border-radius: 3px;\n  padding-left: 10px;\n}\n.eaAllWorkflowInstances-MainRegion-Panel-iconHolder-CreateInstancesButtonHolder {\n  width: 150px;\n}\n.eaAllWorkflowInstances-MainRegion-Panel-CreateNewDefinition {\n  display: inline-block;\n  cursor: pointer;\n  cursor: hand;\n}\n.eaAllWorkflowInstances-MainRegion-contentWfInstances-home {\n  height: 750px;\n  padding-top: 10px;\n  padding-right: 45px;\n}\n.eaAllWorkflowInstances-MainRegion-contentWfInstances-home-Overview {\n  height: 50px;\n  float: left;\n  display: inline-block;\n  padding-right: 25px;\n}\n.eaNFE_automation_UI-WfInstances {\n  float: left;\n  display: inline-block;\n  width: 1372px;\n  padding-top: 0px;\n}\n.eaNFE_automation_UI-WfInstances-SectionHeading {\n  border-bottom: 1px solid #666666;\n}\n.eaNFE_automation_UI-WfInstances-SectionHeading-Filter {\n  padding-top: 10px;\n}\n.ebIcon_interactive {\n  vertical-align: text-top;\n}\n.elLayouts-TopSection {\n  margin-left: 0px !important;\n  margin-right: 70px !important;\n}\n.elLayouts-TopSection-breadcrumb {\n  padding-top: 2.2rem !important;\n}\n.elLayouts-Wrapper {\n  overflow: none !important;\n}\n.eaContainer-applicationHolder {\n  top: 24px !important;\n}\n"}),define("text!allworkflowinstances/allWorkflowInstances.html",function(){return'<div class="eaAllWorkflowInstances-MainRegion">\r\n	<div class="eaAllWorkflowInstances-MainRegion-Panel">\r\n	</div>\r\n</div>\r\n'}),define("allworkflowinstances/AllWorkflowInstancesView",["jscore/core","text!./allWorkflowInstances.html","styles!./allWorkflowInstances.less"],function(n,e,t){return n.View.extend({getTemplate:function(){return e},getStyle:function(){return t},getBreadcrumb:function(){return this.getElement().find(".eaAllWorkflowInstances-MainRegion-Breadcrumb")},getWfInstancesContent:function(){return this.getElement().find(".elLayouts-SlidingPanels-center")},getCreateInstanceButton:function(){return this.getElement().find(".eaAllWorkflowInstances-MainRegion-Panel-iconHolder-CreateInstancesButtonHolder")},getWorkflowInstanceTotal:function(){return this.getElement().find(".eaAllWorkflowInstances-MainRegion-WorkflowInstances")},getWorkflowInstanceActive:function(){return this.getElement().find(".eaAllWorkflowInstances-MainRegion-ActiveInstancesIcon")},getWorkflowInstanceCancelled:function(){return this.getElement().find(".eaAllWorkflowInstances-MainRegion-CancelledInstancesIcon")},addAllTaskClickHandler:function(n){this.getElement().find(".eaAllWorkflowInstances-MainRegion-WorkflowInstances").addEventHandler("click",n)},addActiveTaskClickHandler:function(n){this.getElement().find(".eaAllWorkflowInstances-MainRegion-ActiveInstances").addEventHandler("click",n)},addSuspendedTaskClickHandler:function(n){this.getElement().find(".eaAllWorkflowInstances-MainRegion-CancelledInstances").addEventHandler("click",n)}})}),define("allworkflowinstances/AllWorkflowInstances",["jscore/core","jscore/ext/mvp","./AllWorkflowInstancesView","widget/WfAllWorkflow/WfAllWorkflow","widget/WfAllDefinitions/WfAllDefinitions","layouts/SlidingPanels","layouts/TopSection","jscore/ext/locationController","widget/WfAllWorkflow/WfAllWorkflowCounters/WfAllWorkflowCounters"],function(n,e,t,o,l,a,s,r,c){return n.App.extend({View:t,onAttach:function(){this.wfAllWorkflow&&this.wfAllWorkflow.update(),this.WfAllDefinitions&&(this.WfAllDefinitions.hideCreateInstanceForm(),this.WfAllDefinitions.detach(),this.wfAllWorkflow.attach())},onStart:function(){this.filter="all";var n=this.options.breadcrumb,e=[{name:"ENM",url:"#workflowmanager"}];for(i=1;i<n.length;i++)e.push(n[i]);var t=this;this.layout=new s({context:this.getContext(),title:"All Workflow Instances",breadcrumb:e,defaultActions:[{type:"button",color:"blue",name:"Create New Instance",link:"#",action:function(){t.wfAllWorkflow.detach(),t.WfAllDefinitions||(t.WfAllDefinitions=new l({regionContext:t.getContext(),showActionButtons:!1}),t.WfAllDefinitions.attachTo(t.view.getWfInstancesContent()))}},{type:"separator"},{type:"link",color:"blue",name:"All Active Tasks",link:"#",action:function(){var n=new r,e="workflowmanager/activetasks";n.setLocation(e,!1)}},{type:"separator"},{type:"link",color:"blue",name:"Workflow History",link:"#",action:function(){var n=new r,e="workflowmanager/workflowhistory";n.setLocation(e,!1)}}]}),this.layout.attachTo(this.getElement()),this.layout.setContent(new a({context:this.getContext(),main:{label:"Main Body",contents:t.wfAllWorkflow=new o({regionContext:this.getContext(),showingProgress:!0,showingDiagram:!0,filter:this.filter})},left:{label:"Overview",expanded:!0,contents:new c({context:this.getContext()})}})),this.getContext().eventBus.subscribe("removeDefinitionTable",function(n){this.WfAllDefinitions.detach(),this.WfAllDefinitions.destroy(),this.WfAllDefinitions=null,this.filter="all",this.wfAllWorkflow.showTable(this.filter),this.wfAllWorkflow.attach()}.bind(this)),this.getContext().eventBus.subscribe("viewAllWorkflows",function(){this.wfAllWorkflow.showTable("all")}.bind(this)),this.getContext().eventBus.subscribe("viewActiveWorkflows",function(){this.wfAllWorkflow.showTable("active")}.bind(this)),this.getContext().eventBus.subscribe("viewSuspendedWorkflows",function(){this.wfAllWorkflow.showTable("suspended")}.bind(this))}})});