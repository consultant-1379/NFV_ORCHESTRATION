// Workflow active widget.
// Provides a table of workflow instances and can show progress for specific instance

define([
    "jscore/core",
    "widgets/SelectBox",
    "widgets/Pagination",
    "../WfProgress/WfProgress",
    "./WfHistoryTable/WfHistoryTable",
    "./WfHistoryView",
    "3pps/wfs/WfInstanceClient",
    "../WfDiagram/WfDiagram",
    "../../widget/WfProgress/WfProgressModel"
    ], function(core, SelectBox, Pagination, WfProgress, WfHistoryTable, View, WfInstanceClient, WfDiagram, WfProgressModel) {

       return core.Widget.extend({

            View: View,
            
            onViewReady: function() {
            	this.regionEventBus = (this.options.regionContext.eventBus != null) ? this.options.regionContext.eventBus : null;
                this.refreshButtonVisible = (this.options.refreshButtonVisible != null) ? this.options.refreshButtonVisible : true; //////binary true false
                this.filterValueActive = (this.options.filterValueActive !=null) ? this.options.filterValueActive : true; //////// selection to present desired view
                this.showingProgress = (this.options.showingProgress != null) ? this.options.showingProgress : true; /////////option to show progress diagram 
                this.showingDiagram = (this.options.showingDiagram != null) ? this.options.showingDiagram : true; /////////option to show progress diagram
                
                this.view.getHeaderContent().setStyle('display' ,"none");
                
                this.view.getCloseDiagram().addEventHandler("click", function() {
                	if(this.wfDiagram){
	                     this.wfDiagram.detach();
	                     this.wfDiagram.destroy();
	                     this.wfDiagram = undefined;
                     }
                	
	               	if(!this.wfDiagram){
	                 		this.showTable();
	                } 
	                this.toggleHandleBarView();
//	                this.view.getCloseDiagram().setStyle('display' ,"none");
	                this.view.getHeaderContent().setStyle('display' ,"inline-block");
	                this.regionEventBus.publish("instanceTable-refreshed", "refresh");
                }.bind(this));
                
                this.view.getCloseLink().addEventHandler("click", function() {
                	if(this.wfDiagram){
	                     this.wfDiagram.detach();
	                     this.wfDiagram.destroy();
	                     this.wfDiagram = undefined;
                    }
               	
	               	if(!this.wfDiagram){
	                 		this.showTable();
	                } 
	                this.toggleHandleBarView();
	                this.view.getCloseLink().setStyle('display' ,"none");
	                this.view.getHeaderContent().setStyle('display' ,"none");
	                this.regionEventBus.publish("instanceTable-refreshed", "refresh");
               }.bind(this));
                
                this.view.getRefreshButton().setStyle("visibility", (this.refreshButtonVisible == true) ? "visible" : "hidden"); 
                this.showTable();
               
            },
            
            update: function(){
            	this.trigger("updateServiceCall", "update");    
            	this.refreshButtonClicked();
		        this.regionEventBus.publish("instanceTable-refreshed", "refresh");
            },
            
            Update: function(){
				if(this.wfDiagram){
				   this.wfDiagram.detach();
				   this.TaskTable.attach();
				}
			},            

            showTable: function () {
                this.hideTable();
                this.WfHistoryTable = new WfHistoryTable({
                    offset:0,
                    limit: 20,
                    sortBy :"endTime",
                    sortOrder : "desc",                
                    showActive : false,
                    showCompleted : true,
                    hidePercentage : this.options.hidePercentage,
                    regionEventBus: this.regionEventBus
                });


                this.WfHistoryTable.attachTo(this.view.getTableContent());
                if (this.tableContentHeight) {
                    this.view.getTableContent().setStyle("height", "auto");
                }
               
                // Add handler for 'show diagram' table action
                this.WfHistoryTable.options.regionEventBus.subscribe("showdiagram", function(wfUsertaskModel) {
				 
				                // Build progress models
				                WfProgressModel.build({
				                    workflowDefinitionId: wfUsertaskModel.workflowDefinitionId, 
				                    workflowInstanceId: wfUsertaskModel.workflowInstanceId,
				                    success: function(progressModels) {
				                        this.wfStructureModel = progressModels.wfStructureModel;
				                        this.wfProgressModel = progressModels.wfProgressModel;
				                        this.keyableStatus = progressModels.keyableStatus;
				                        this.keyableCount = progressModels.keyableCount;
				                        this.percentComplete = progressModels.percentComplete;
				                        this.calledWorkflows = progressModels.calledWorkflows;
				
				                        var overlays = this.buildOverlays(this.keyableStatus, this.keyableCount);
				                        if(!this.wfDiagram){
				                        this.wfDiagram = new WfDiagram({  xid: "prg-",
				                                                            wfDefinitionId: wfUsertaskModel.workflowDefinitionId,
				                                                            wfName: wfUsertaskModel.workflowName,
				                                                            highlights: overlays.highlights,
				                                                            countBadges: overlays.countBadges });
				                                                            
				                        this.view.getDiagramName().setStyle('display' ,"block");
//				                        this.view.getHeaderContent().setStyle('display' ,"none");
				                        this.view.getCloseLink().setStyle('display' ,"inline-block");
				                        this.view.getHeaderContent().setStyle('display' ,"inline-block");
				                        var diagramName = wfUsertaskModel.workflowName;
				                        this.view.getDiagramName().setText(diagramName);
				                        }
				                         
				                         this.WfHistoryTable.detach();
				                         this.wfDiagram.attachTo(this.view.getTableContent());

				                         
				                         
				                    }.bind(this),
				                    error: function(err) {
				                        console.log(err);       // TODO - recommended UI SDK logging ?
				                    }.bind(this)
				                });				            
                }.bind(this));
                
            },  
            
			buildOverlays: function(keyableStatus, keyableCount){
			    var highlights = [];
			    var countBadges = [];
			    for (var key in keyableStatus) {
			        var status = keyableStatus[key];
			        if (status && status != null && status !== "") {
			            highlights.push({nodeId: key, style: "highlight-"+status});
			            var count = keyableCount[key];
			            if (count && count > 0) {
			                if ((count > 1) || (count == 1 && status === "Started")) {
			                    countBadges.push({nodeId: key, count: count});
			                }
			            }
			        }
			    }
			    return {highlights: highlights, countBadges: countBadges};
			},

////////////////////////////////////////// Add handler for instance selected
            rowSelectionHandle: function () {
                this.WfHistoryTable.getEventBus().subscribe("instance-selected", function(ids) { //// catches the row selected event published by WfInstanceTable
                    this.progressIds = ids;                    
                    this.regionEventBus.publish("instance-selected", ids);                          ////////linkage between instance table row selection and corresponding user task row highlight
                    
                    if(this.showingProgress) this.showProgress(ids);
                    if(this.showingDiagram) this.WfProgress.showDiagram();
                }.bind(this));

                this.WfHistoryTable.getEventBus().subscribe("instance-deselected", function(ids) { //// catches the row de-selected event published by WfInstanceTable
                    this.progressIds = ids;

                    this.regionEventBus.publish("instance-deselected", ids);
                    if(this.showingProgress) this.WfProgress.hideDiagram();
                    if(this.showingDiagram) this.hideProgress();
                }.bind(this));
            }, 

            refreshButtonClicked: function() {
            	if(!this.WfHistoryTable){
            		this.showTable();
            	}
                
                if(this.WfProgress != null)
                    this.WfProgress.hideDiagram();                
                this.hideProgress();                
            },
////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            hideTable: function () {
                if (this.WfHistoryTable != null) {
                    this.tableContentHeight = this.view.getTableContent().getStyle("height");
                    this.WfHistoryTable.destroy();
                    this.WfHistoryTable = null;
                    this.view.getTableContent().setStyle("height", "0px");
                }
            },
            
			toggleHandleBarView: function(){
				this.view.getDiagramName().setStyle('display' ,"none");
			},

            showPagination: function() {
                this.hidePagination();
                // Get total number of workflow instances in order to calculate the required num pages
                WfInstanceClient.getInstancesCount({
                	processDefinitionId: this.processDefinitionId,
                    active: this.getFilterValue().historicView ? false : true,
                    completed: this.getFilterValue().historicView ? true : false,
                    success: function(count) {                                
                        var pages = Math.ceil(count / this.getPageLimit());
                        // Create the pagination widget
                        this.pagination = new Pagination({
                            pages: pages,
                            onPageChange: function(pageNumber) {
                                var pageOffset = (pageNumber - 1) * this.getPageLimit();
                                this.WfHistoryTable.refresh({offset:pageOffset, limit:this.getPageLimit()});
                            }.bind(this)
                        });
                        this.pagination.attachTo(this.view.getTableContent());
                    }.bind(this)
                });
            },

            hidePagination: function() {
                if (this.pagination != null) {
                    this.pagination.destroy();
                    this.pagination = null;
                }
            },

            showProgress: function (ids, showingDiagram) {
                this.hideProgress();
                this.WfProgress = new WfProgress({regionEventBus: this.regionEventBus, ids: ids, 
                                                    showingDiagram: showingDiagram,
                                                    doneCallback: this.progressClosed.bind(this)});

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
                    this.showTable();  
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

            getProcessDefinitionId: function() {
            	var url = window.location.hash;
                var arguments = url.substring(url.indexOf('/') + 1).split('/');
                return arguments[0];
            }
        });
});