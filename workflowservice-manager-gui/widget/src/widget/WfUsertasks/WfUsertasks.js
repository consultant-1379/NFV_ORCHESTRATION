// Usertasks widget

define([
    "jscore/core",
    "./WfUsertasksView",
    "./TaskTable/TaskTable",
    "widgets/Pagination",
    "widgets/SelectBox",
    "./TaskForm/TaskForm",
    "../WfDiagram/WfDiagram",  
    "3pps/wfs/WfUsertaskClient",
    "../WfProgress/WfProgress",
    ], function(core,View, TaskTable, Pagination, SelectBox, TaskForm, WfDiagram, WfUsertaskClient, WfProgress) {

       return core.Widget.extend({

            View: View, 
       		
       		Update: function(){
       			if(this.bpmnDiagram){
         		   this.bpmnDiagram.detach();
         		   this.TaskTable.attach();
       			}
       		},

            onViewReady: function() {
            	this.regionEventBus = this.options.regionContext.eventBus;
            	this.active = null;
            	this.suspended = null;        	
                this.showingProgress = (this.options.showingProgress != null) ? this.options.showingProgress : false;   /////////option to show progress diagram 
                this.showingDiagram = (this.options.showingDiagram != null) ? this.options.showingDiagram : true;       /////////option to show progress diagram
//                this.allowPagination = (this.options.allowPagination !=null) ? this.options.allowPagination : false;    //////// selection to turn pagination on off//currently off due to some contradiction table row selection
                // TODO: 'something-changed' is a hack/workaround for lack of UI push, and will be removed when UI push supported.
//               this.regionEventBus.subscribe("something-changed", function (whatChanged) {
//                if (whatChanged != "usertasks" && this.TaskTable !=null) {        // don't react to own events
//                    this.showTable(false, true);
//                	}
//                }.bind(this));

                this.selectBox = new SelectBox({
                  value: {name: 'Active', value: {liveView:false}},
                  items: [
                    {name: 'Active', value: {liveView:false}, title: 'View all active usertasks'},
                    {name: 'Active Live (Last 20)', value: {liveView:true}, title: 'View the 20 most recently active usertasks'}
                  ]
                });  

                this.selectBox.attachTo(this.view.getFilter());
                this.selectBox.addEventHandler("change", function() {
                    this.selectBoxChanged();
                }.bind(this));
            
                this.view.getRefreshButton().addEventHandler("click", function() {
                	this.trigger("updateServiceCallActiveInstance", "updateActiveInstance"); 
                	if(!this.bpmnDiagram){
                		this.showTable(this.active, this.suspended);
                	}
                	this.regionEventBus.publish("refreshCounters", "refreshCounters");
                	this.regionEventBus.publish("instanceTable-refreshed", "refresh");
                }.bind(this));
                
                this.view.getCloseDiagram().addEventHandler("click", function() {
                	if(this.bpmnDiagram){
                     	this.bpmnDiagram.detach();
                     	this.bpmnDiagram.destroy();
                     	this.bpmnDiagram = undefined;
                     	}
                	
                	this.hideProgress();
                    this.hideForm();
                    this.showTable(this.active, this.suspended);
                    this.toggleHandleBarView();
                    this.view.getCloseDiagram().setStyle('display' ,"none");
                }.bind(this));

                this.showTable(this.active, this.suspended);
                
            },
            
            update: function(){
            	this.trigger("updateServiceCallActiveInstance", "updateActiveInstance");    
            	this.refreshButtonClicked();
		        this.regionEventBus.publish("instanceTable-refreshed", "refresh");
            },
            
/////////// this function is disabled in the last line of showTable(false, true) as the showPagination() causes interference with "refresh"       
            selectBoxChanged: function() {
                this.hidePagination();

                // Redisplay table, only when form is not displayed
                if (this.TaskForm == null) {
                    this.showTable(this.active, this.suspended);
                }

                //Display pagination, only in non-live view and when form is not displayed
                if (!this.getFilterValue().liveView && this.TaskForm == null && this.allowPagination) {
                    this.showPagination();
                }            
            },

            showTable: function(act, susp) {
                this.hideTable();
                this.active = act;
                this.suspended = susp;
                // Build and display table
                this.TaskTable = new TaskTable({offset:0, limit:this.getPageLimit(), active: act, suspended: susp, regionEventBus: this.options.regionContext.eventBus });

                // only subscribe for events in live filter view
                if (this.getFilterValue().liveView) { 
                    this.TaskTable.subscribeForPushEvents(this.regionEventBus);
                }

                this.TaskTable.attachTo(this.view.getTaskTableContent());
                if (this.taskTableContentHeight) {
                    this.view.getTaskTableContent().setStyle("height", "auto");
                }
                this.view.getTaskTableContent().setStyle("visibility", "visible");
//                 Add handler for 'execute task' table action
                this.TaskTable.options.regionEventBus.subscribe("executetask", function(wfUsertaskModel) {
                    this.executeTask(wfUsertaskModel);
                }.bind(this));
//
//                // Add handler for 'show diagram' table action
                this.TaskTable.options.regionEventBus.subscribe("showdiagram", function(wfUsertaskModel) {
                	if(!this.bpmnDiagram){
                    this.bpmnDiagram = new WfDiagram({  xid: "usr-",
                                                        wfDefinitionId: wfUsertaskModel.workflowDefinitionId,
                                                        wfName: wfUsertaskModel.workflowName,
                                                        highlights: [{nodeId: wfUsertaskModel.definitionId, style: "highlight-Created"}]
                                                  });
                    
                    this.view.getDiagramName().setStyle('display' ,"block");
                    this.view.getCloseDiagram().setStyle('display' ,"block");
	                var diagramName = wfUsertaskModel.workflowName;
	                this.view.getDiagramName().setText(diagramName);
                	}
	                
                    this.TaskTable.detach();
                    this.bpmnDiagram.attachTo(this.view.getDiagramContent());
                    
//                    this.regionEventBus.publish("display-element", ["UsertaskDiagram", bpmnDiagram]);
                }.bind(this));

////////////////////// catches instance selected event and perform tasks ///////////////////////////

                this.TaskTable.options.regionEventBus.subscribe("task-selected", function(ids) {
                    this.progressIds = ids;
                    if(this.showingProgress) this.showProgress(ids);
                }.bind(this));

                this.TaskTable.options.regionEventBus.subscribe("task-deselected", function(ids) {
                    this.progressIds = ids;
                    if(this.showingProgress) this.WfProgress.hideDiagram();
                    if(this.showingDiagram) this.hideProgress();                    
                }.bind(this));

//                this.selectBox.disable();         /////////// this function is disabled in the last line of showTable(false, true) as the showPagination() causes interference with "refresh"        
                this.highlightRowForInstanceId(); /////////// linkage between instance table row selection and corresponding user task row highlight
            },

            showProgress: function (ids) {
                this.hideProgress();
                this.WfProgress = new WfProgress({regionEventBus: this.regionEventBus, ids: ids, doneCallback: this.progressClosed.bind(this)});
                this.regionEventBus.publish("display-progress", ["progress", this.WfProgress]);
            },

            progressClosed: function() {
                if ( this.progressIds.hasOwnProperty('childExecutionId') || this.progressIds.hasOwnProperty('subProcessId')) {
                    delete this.progressIds.childExecutionId;
                    delete this.progressIds.childDefinitionId;
                    delete this.progressIds.subProcessId;
                    this.hideProgress();
                    this.showProgress(this.progressIds, false);
                } else {
                    this.hideProgress();
                    this.selectBox.enable();
                    this.showTable(this.active, this.suspended);  
                    if (!this.getFilterValue().liveView && this.allowPagination) {
                        this.showPagination();
                    }  
                }  
            },
            hideProgress: function () {
                if (this.WfProgress != null) {
                   this.WfProgress.destroy();
                   this.WfProgress = null;
                }
            },
/////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            hideTable: function() {
                if (this.TaskTable != null) {
                    this.taskTableContentHeight = this.view.getTaskTableContent().getStyle("height");
                    this.TaskTable.destroy();
                    this.TaskTable = null;
                    this.view.getTaskTableContent().setStyle("height", "0px");
                }
            },

//            showPagination: function() {
//                // Get total number of usertasks in order to calculate the required num pages
//                WfUsertaskClient.getUsertasksCount({
//                    success: function(count) {                                
//                        var pages = Math.ceil(count / this.getPageLimit());
//                        // Create the pagination widget
//                        this.pagination = new Pagination({
//                            pages: pages,
//                            onPageChange: function(pageNumber) {
//                                var pageOffset = (pageNumber - 1) * this.getPageLimit();
//                                this.TaskTable.refresh({offset:pageOffset, limit:this.getPageLimit()});
//                            }.bind(this)
//                        });
//                        this.pagination.attachTo(this.view.getTaskTablePagination());
//                    }.bind(this)
//                });
//            },

            hidePagination: function() {
                if (this.pagination != null) {
                    this.pagination.destroy();
                    this.pagination = null;
                }
            },

            showForm: function(wfUsertaskModel) {
                // Build and display form
                this.TaskForm = new TaskForm({minHeight: this.taskTableContentHeight, wfUsertaskModel:wfUsertaskModel, taskHandledCallback:this.taskHandledCallback.bind(this), regionEventBus: this.options.regionContext.eventBus});
                this.TaskForm.attachTo(this.view.getTaskFormContent());
            },

            hideForm: function() {
                if (this.TaskForm != null) {
                    this.TaskForm.destroy();
                    this.TaskForm = null;
                }
            },
//////////////////////////////////////////////
            executeTask: function(wfUsertaskModel) {
                this.selectBox.disable();
                this.hideTable();
                this.hidePagination();
                this.showForm(wfUsertaskModel);
            },

            refreshButtonClicked: function() {
            	if(!this.TaskTable){
                 	this.showTable(this.active, this.suspended);
                 }
                this.hideProgress();
                this.hideForm();
            },
            
			toggleHandleBarView: function(){
				this.view.getDiagramName().setStyle('display' ,"none");
			},
//////////////linkage between instance table row selection and corresponding user task row highlight. 
//// it subscribes "instance-selected" event published by the WfInstance.js and highlight the corresponding usertask.
            highlightRowForInstanceId: function() {
                var that = this;
                this.regionEventBus.subscribe("instance-selected", function(ids) {                    
                    var instanceId = ids.workflowInstanceId.substring(0,8);
                     if (that.TaskTable.highlightedRow != null) 
                        that.TaskTable.highlightedRow.highlight(false);
                    var instanceUserTaskRow = that.TaskTable.table.getRows().filter(function(row){ return row.options.data.attributes.shortWorkflowInstanceId == instanceId;});
                    if(instanceUserTaskRow.length != 0) instanceUserTaskRow[0].highlight(true);                    
                });

                this.regionEventBus.subscribe("instance-deselected", function(ids) {                    
                    var instanceId = ids.workflowInstanceId.substring(0,8);
                    var instanceUserTaskRow = that.TaskTable.table.getRows().filter(function(row){ return row.options.data.attributes.shortWorkflowInstanceId == instanceId;});
                    if(instanceUserTaskRow.length != 0) instanceUserTaskRow[0].highlight(false) ;                    
                });
               this.regionEventBus.subscribe("instanceTable-refreshed", function(refresh) {  
                    this.refreshButtonClicked();
                }.bind(this));
            },
////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            taskHandledCallback: function(somethingChanged) {
                this.hideForm();
                this.selectBox.enable();
                this.refreshButtonClicked();            //// refresh table when cancel button pressed
              
                if (!this.getFilterValue().liveView && this.allowPagination) {
                    //only show pagination widget when not in live filter view
                    this.showPagination();
                }
                if (somethingChanged == true) {
                    setTimeout(function() {
                        this.regionEventBus.publish("something-changed", "usertasks");
                    }.bind(this), 2000);
                }
            },

            getPageLimit: function() {
                return  this.getFilterValue().liveView ? 500 : 50;
            },

            getFilterValue: function() {
                return this.selectBox.getValue().value;
            }
        });
});
