/* Copyright (c) Ericsson 2015 */

define("styles!widget/WfUsertasks/WfUsertasks.less",function(){return".eaNFE_automation_UI-Usertasks {\n  min-height: 288px;\n  width: 100%;\n}\n.eaNFE_automation_UI-Usertasks-bottomDivider {\n  border-bottom: 1px solid #666666 ! important;\n  width: 100% ! important;\n  padding-bottom: 5px ! important;\n}\n.eaNFE_automation_UI-Usertasks-handleBarButton-diagramName {\n  padding-top: 10px;\n  font-size: 2rem;\n  float: left;\n}\n.eaNFE_automation_UI-Usertasks-handleBarButton-closeDiagram {\n  display: none;\n  cursor: pointer;\n  float: right;\n}\n.eaNFE_automation_UI-Usertasks-contentTaskTable {\n  max-height: 750px;\n}\n.eaNFE_automation_UI-Usertasks-contentDiagram {\n  overflow: hidden;\n}\n.eaNFE_automation_UI-Usertasks-SectionHeading {\n  width: calc(100% - 0px);\n}\n.eaNFE_automation_UI-Usertasks-SectionHeading-Title {\n  float: left;\n}\n.eaNFE_automation_UI-Usertasks-SectionHeading-Filter {\n  padding-top: 10px;\n  float: right;\n}\n.eaNFE_automation_UI-Usertasks-contentTaskForm {\n  vertical-align: top;\n  z-index: 5;\n}\n.eaNFE_automation_UI-Usertasks-contentTable-buttonRefresh {\n  float: left;\n  vertical-align: bottom;\n  margin-left: 15px;\n  margin-top: 10px;\n  padding-top: 5px;\n  cursor: pointer;\n  cursor: hand;\n}\n.eaNFE_automation_UI-WfInstances-contentProgress {\n  float: left;\n  overflow: auto;\n  width: calc(27% - 15px);\n  margin-left: 10px;\n  vertical-align: top;\n  display: inline-block;\n  padding-left: 18px;\n}\n"}),define("text!widget/WfUsertasks/WfUsertasks.html",function(){return'<div class="eaNFE_automation_UI-Usertasks">\r\n    <div class="ebLayout-SectionHeading eaNFE_automation_UI-Usertasks-SectionHeading">\r\n    	<div class="eaNFE_automation_UI-Usertasks-SectionHeading-Title"><h2>All Tasks</h2>\r\n        	<div class="eaNFE_automation_UI-Usertasks-contentTable-buttonRefresh"><i class="ebIcon ebIcon_refresh"></i></div>\r\n    	</div>\r\n    </div> 	\r\n    <div class="eaNFE_automation_UI-Usertasks-handleBarButton">\r\n    	<div class="eaNFE_automation_UI-Usertasks-handleBarButton-diagramName"></div>\r\n    	<div class="eaNFE_automation_UI-Usertasks-handleBarButton-closeDiagram"><i class="ebIcon ebIcon_large ebIcon_close_red"></i>close diagram</div>\r\n   	</div>\r\n    <div class="eaNFE_automation_UI-Usertasks-contentTaskTable eb_scrollbar"></div>\r\n    <div class="eaNFE_automation_UI-Usertasks-contentDiagram"></div>\r\n    <div class="eaNFE_automation_UI-Usertasks-contentTaskTablePagination"></div>\r\n    <div class="eaNFE_automation_UI-Usertasks-bottomDivider"></div>	   	       \r\n</div>\r\n'}),define("widget/WfUsertasks/WfUsertasksView",["jscore/core","text!./WfUsertasks.html","styles!./WfUsertasks.less"],function(t,e,s){return t.View.extend({getTemplate:function(){return e},getStyle:function(){return s},getFilter:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-SectionHeading-Filter")},getTaskTableContent:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTaskTable")},getDiagramContent:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentDiagram")},getTaskFormContent:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTaskForm")},getTaskTablePagination:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTaskTablePagination")},getUsertasksToggle:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks")},getProgressContent:function(){return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentProgress")},getRefreshButton:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTable-buttonRefresh")},getDiagramName:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-handleBarButton-diagramName")},getCloseDiagram:function(){return this.getElement().find(".eaNFE_automation_UI-Usertasks-handleBarButton-closeDiagram")}})}),define("widget/WfUsertasks/WfUsertasks",["jscore/core","./WfUsertasksView","./TaskTable/TaskTable","widgets/Pagination","widgets/SelectBox","./TaskForm/TaskForm","../WfDiagram/WfDiagram","3pps/wfs/WfUsertaskClient","../WfProgress/WfProgress"],function(t,e,s,i,n,a,o,r,h){return t.Widget.extend({View:e,Update:function(){this.bpmnDiagram&&(this.bpmnDiagram.detach(),this.TaskTable.attach())},onViewReady:function(){this.regionEventBus=this.options.regionContext.eventBus,this.active=null,this.suspended=null,this.showingProgress=null!=this.options.showingProgress?this.options.showingProgress:!1,this.showingDiagram=null!=this.options.showingDiagram?this.options.showingDiagram:!0,this.selectBox=new n({value:{name:"Active",value:{liveView:!1}},items:[{name:"Active",value:{liveView:!1},title:"View all active usertasks"},{name:"Active Live (Last 20)",value:{liveView:!0},title:"View the 20 most recently active usertasks"}]}),this.selectBox.attachTo(this.view.getFilter()),this.selectBox.addEventHandler("change",function(){this.selectBoxChanged()}.bind(this)),this.view.getRefreshButton().addEventHandler("click",function(){this.trigger("updateServiceCallActiveInstance","updateActiveInstance"),this.bpmnDiagram||this.showTable(this.active,this.suspended),this.regionEventBus.publish("refreshCounters","refreshCounters"),this.regionEventBus.publish("instanceTable-refreshed","refresh")}.bind(this)),this.view.getCloseDiagram().addEventHandler("click",function(){this.bpmnDiagram&&(this.bpmnDiagram.detach(),this.bpmnDiagram.destroy(),this.bpmnDiagram=void 0),this.hideProgress(),this.hideForm(),this.showTable(this.active,this.suspended),this.toggleHandleBarView(),this.view.getCloseDiagram().setStyle("display","none")}.bind(this)),this.showTable(this.active,this.suspended)},update:function(){this.trigger("updateServiceCallActiveInstance","updateActiveInstance"),this.refreshButtonClicked(),this.regionEventBus.publish("instanceTable-refreshed","refresh")},selectBoxChanged:function(){this.hidePagination(),null==this.TaskForm&&this.showTable(this.active,this.suspended),!this.getFilterValue().liveView&&null==this.TaskForm&&this.allowPagination&&this.showPagination()},showTable:function(t,e){this.hideTable(),this.active=t,this.suspended=e,this.TaskTable=new s({offset:0,limit:this.getPageLimit(),active:t,suspended:e,regionEventBus:this.options.regionContext.eventBus}),this.getFilterValue().liveView&&this.TaskTable.subscribeForPushEvents(this.regionEventBus),this.TaskTable.attachTo(this.view.getTaskTableContent()),this.taskTableContentHeight&&this.view.getTaskTableContent().setStyle("height","auto"),this.view.getTaskTableContent().setStyle("visibility","visible"),this.TaskTable.options.regionEventBus.subscribe("executetask",function(t){this.executeTask(t)}.bind(this)),this.TaskTable.options.regionEventBus.subscribe("showdiagram",function(t){if(!this.bpmnDiagram){this.bpmnDiagram=new o({xid:"usr-",wfDefinitionId:t.workflowDefinitionId,wfName:t.workflowName,highlights:[{nodeId:t.definitionId,style:"highlight-Created"}]}),this.view.getDiagramName().setStyle("display","block"),this.view.getCloseDiagram().setStyle("display","block");var e=t.workflowName;this.view.getDiagramName().setText(e)}this.TaskTable.detach(),this.bpmnDiagram.attachTo(this.view.getDiagramContent())}.bind(this)),this.TaskTable.options.regionEventBus.subscribe("task-selected",function(t){this.progressIds=t,this.showingProgress&&this.showProgress(t)}.bind(this)),this.TaskTable.options.regionEventBus.subscribe("task-deselected",function(t){this.progressIds=t,this.showingProgress&&this.WfProgress.hideDiagram(),this.showingDiagram&&this.hideProgress()}.bind(this)),this.highlightRowForInstanceId()},showProgress:function(t){this.hideProgress(),this.WfProgress=new h({regionEventBus:this.regionEventBus,ids:t,doneCallback:this.progressClosed.bind(this)}),this.regionEventBus.publish("display-progress",["progress",this.WfProgress])},progressClosed:function(){this.progressIds.hasOwnProperty("childExecutionId")||this.progressIds.hasOwnProperty("subProcessId")?(delete this.progressIds.childExecutionId,delete this.progressIds.childDefinitionId,delete this.progressIds.subProcessId,this.hideProgress(),this.showProgress(this.progressIds,!1)):(this.hideProgress(),this.selectBox.enable(),this.showTable(this.active,this.suspended),!this.getFilterValue().liveView&&this.allowPagination&&this.showPagination())},hideProgress:function(){null!=this.WfProgress&&(this.WfProgress.destroy(),this.WfProgress=null)},hideTable:function(){null!=this.TaskTable&&(this.taskTableContentHeight=this.view.getTaskTableContent().getStyle("height"),this.TaskTable.destroy(),this.TaskTable=null,this.view.getTaskTableContent().setStyle("height","0px"))},hidePagination:function(){null!=this.pagination&&(this.pagination.destroy(),this.pagination=null)},showForm:function(t){this.TaskForm=new a({minHeight:this.taskTableContentHeight,wfUsertaskModel:t,taskHandledCallback:this.taskHandledCallback.bind(this),regionEventBus:this.options.regionContext.eventBus}),this.TaskForm.attachTo(this.view.getTaskFormContent())},hideForm:function(){null!=this.TaskForm&&(this.TaskForm.destroy(),this.TaskForm=null)},executeTask:function(t){this.selectBox.disable(),this.hideTable(),this.hidePagination(),this.showForm(t)},refreshButtonClicked:function(){this.TaskTable||this.showTable(this.active,this.suspended),this.hideProgress(),this.hideForm()},toggleHandleBarView:function(){this.view.getDiagramName().setStyle("display","none")},highlightRowForInstanceId:function(){var t=this;this.regionEventBus.subscribe("instance-selected",function(e){var s=e.workflowInstanceId.substring(0,8);null!=t.TaskTable.highlightedRow&&t.TaskTable.highlightedRow.highlight(!1);var i=t.TaskTable.table.getRows().filter(function(t){return t.options.data.attributes.shortWorkflowInstanceId==s});0!=i.length&&i[0].highlight(!0)}),this.regionEventBus.subscribe("instance-deselected",function(e){var s=e.workflowInstanceId.substring(0,8),i=t.TaskTable.table.getRows().filter(function(t){return t.options.data.attributes.shortWorkflowInstanceId==s});0!=i.length&&i[0].highlight(!1)}),this.regionEventBus.subscribe("instanceTable-refreshed",function(t){this.refreshButtonClicked()}.bind(this))},taskHandledCallback:function(t){this.hideForm(),this.selectBox.enable(),this.refreshButtonClicked(),!this.getFilterValue().liveView&&this.allowPagination&&this.showPagination(),1==t&&setTimeout(function(){this.regionEventBus.publish("something-changed","usertasks")}.bind(this),2e3)},getPageLimit:function(){return this.getFilterValue().liveView?500:50},getFilterValue:function(){return this.selectBox.getValue().value}})});