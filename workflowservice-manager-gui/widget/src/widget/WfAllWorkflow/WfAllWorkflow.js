// Workflow active widget.
// Provides a table of workflow instances and can show progress for specific instance

define([
    "jscore/core",
    "widgets/SelectBox",
    "widgets/Pagination",
    "../WfProgress/WfProgress",
    "./WfAllWorkflowTable/WfAllWorkflowTable",
    "./WfAllWorkflowView",
    "3pps/wfs/WfInstanceClient",
    "../WfUsertasks/TaskForm/TaskForm"
    ], function(core, SelectBox, Pagination, WfProgress, WfAllWorkflowTable, View, WfInstanceClient, TaskForm) {

       return core.Widget.extend({

            View: View,

            onViewReady: function() {
            	this.filter = this.options.filter ? this.options.filter : "all";
                this.regionEventBus = this.options.regionContext.eventBus;
                this.refreshButtonVisible = (this.options.refreshButtonVisible != null) ? this.options.refreshButtonVisible : true; //////binary true false
                this.filterValueActive = (this.options.filterValueActive !=null) ? this.options.filterValueActive : true; //////// selection to present desired view
                this.showingProgress = (this.options.showingProgress != null) ? this.options.showingProgress : true; /////////option to show progress diagram 
                this.showingDiagram = (this.options.showingDiagram != null) ? this.options.showingDiagram : true; /////////option to show progress diagram
                 // Register listener to refresh progress based on child work flow exection id.
                /* this.regionEventBus.subscribe("progress-refresh", function (refresh) {
                    if (refresh.subProcessId) {
                        this.progressIds.subProcessId = refresh.subProcessId;
                    } else if (refresh.childExecutionId) {
                        this.progressIds.childExecutionId = refresh.childExecutionId;
                        this.progressIds.childDefinitionId = refresh.childDefinitionId; 
                    } else {
                        this.progressIds.workflowInstanceId = refresh.workflowInstanceId;
                        this.progressIds.workflowDefinitionId = refresh.workflowDefinitionId; 
                    }
                 
                    this.hideProgress();
                    this.showProgress(this.progressIds, (refresh.showingDiagram && refresh.showingDiagram === true) ? true : false);
                }.bind(this));*/

                this.view.getRefreshButton().addEventHandler("click", function() {
                	this.trigger("updateServiceCallAllDefinitions", "updateAllDefinitions");   
                    this.refreshButtonClicked();
                	this.regionEventBus.publish("refreshCounters", "refreshCounters");
                    this.regionEventBus.publish("instanceTable-refreshed", "refresh");
                }.bind(this));                

                this.view.getRefreshButton().setStyle("visibility", (this.refreshButtonVisible == true) ? "visible" : "hidden"); 
                this.showTable(this.filter);
            }, 
            
            update: function(){
            	this.trigger("updateServiceCallAllDefinitions", "updateAllDefinitions");    
            	this.refreshButtonClicked();
            	this.regionEventBus.publish("refreshCounters", "refreshCounters");
		        this.regionEventBus.publish("instanceTable-refreshed", "refresh");
            },

            showTable: function (filter) {
                this.hideTable();
                this.filter = filter;
                this.WfAllWorkflowTable = new WfAllWorkflowTable({
                    offset:0, 
                    limit:19,
                    sortBy : "startTime",
                    sortOrder : "desc",
                    showActive : true,
                    showCompleted : false,
//                    hidePercentage : this.options.hidePercentage,
                    filter: this.filter,
                    regionEventBus: this.options.regionContext.eventBus
                });

                this.WfAllWorkflowTable.attachTo(this.view.getTableContent());
                if (this.tableContentHeight) {
                    this.view.getTableContent().setStyle("height", "auto");
                }
//                this.rowSelectionHandle();                                      ///// added later to handle row selection and de-selection
            },

////////////////////////////////////////// Add handler for instance selected
//            rowSelectionHandle: function () {
//                this.WfAllWorkflowTable.getEventBus().subscribe("instance-selected", function(ids) { //// catches the row selected event published by WfInstanceTable
//                    this.progressIds = ids;                    
//                    this.regionEventBus.publish("instance-selected", ids);                          ////////linkage between instance table row selection and corresponding user task row highlight
//                    
//                    if(this.showingProgress) this.showProgress(ids);
//                    if(this.showingDiagram) this.WfProgress.showDiagram();
//                }.bind(this));

//                this.WfAllWorkflowTable.getEventBus().subscribe("instance-deselected", function(ids) { //// catches the row de-selected event published by WfInstanceTable
//                    this.progressIds = ids;
//
//                    this.regionEventBus.publish("instance-deselected", ids);
//                    if(this.showingProgress) this.WfProgress.hideDiagram();
//                    if(this.showingDiagram) this.hideProgress();
//                }.bind(this));
//            }, 

            refreshButtonClicked: function() {
                this.showTable(this.filter);
                if(this.WfProgress != null)
                    this.WfProgress.hideDiagram();                
                this.hideProgress();                
            },

            hideTable: function () {
                if (this.WfAllWorkflowTable != null) {
                    this.tableContentHeight = this.view.getTableContent().getStyle("height");
                    this.WfAllWorkflowTable.destroy();
                    this.WfAllWorkflowTable = null;
                    this.view.getTableContent().setStyle("height", "0px");
                }
            },

//            showPagination: function() {
//                this.hidePagination();
//                // Get total number of workflow instances in order to calculate the required num pages
//                WfInstanceClient.getInstancesCount({
//                	processDefinitionId: this.processDefinitionId,
//                    active: this.getFilterValue().historicView ? false : true,
//                    completed: this.getFilterValue().historicView ? true : false,
//                    success: function(count) {                                
//                        var pages = Math.ceil(count / this.getPageLimit());
//                        // Create the pagination widget
//                        this.pagination = new Pagination({
//                            pages: pages,
//                            onPageChange: function(pageNumber) {
//                                var pageOffset = (pageNumber - 1) * this.getPageLimit();
//                                this.WfAllWorkflowTable.refresh({offset:pageOffset, limit:this.getPageLimit()});
//                                //this.rowSelectionHandle({showProgress: true, showDiagram: true}); ///////////////////////////
//                            }.bind(this)
//                        });
//                        this.pagination.attachTo(this.view.getPagination());
//                    }.bind(this)
//                });
//            },

            hidePagination: function() {
                if (this.pagination != null) {
                    this.pagination.destroy();
                    this.pagination = null;
                }
            },

            showProgress: function (ids, showingDiagram) {
                this.hideProgress();
                this.WfProgress = new WfProgress({	
                	regionEventBus: this.options.regionContext.eventBus,
                	ids: ids, 
                    showingDiagram: showingDiagram,
                    doneCallback: this.progressClosed.bind(this)
                    });

/////////////// most important step. instead of displaying progress in its own div, it publishes the object so that main region can subscribe and place accordingly                
                this.regionEventBus.publish("display-progress", ["progress", this.WfProgress]);

                if ( ids.hasOwnProperty('childExecutionId')) {
                    this.showExtra("Showing progress for child Id: " + ids.childExecutionId.substring(0,8));
                } else {
//                    this.showExtra("Showing progress for Id: " + ids.workflowInstanceId.substring(0,8));    
                }
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
                    this.showTable(this.filter);  
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
            }
        });
});