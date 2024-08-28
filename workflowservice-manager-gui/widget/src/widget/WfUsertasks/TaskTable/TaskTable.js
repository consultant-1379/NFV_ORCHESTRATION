// Usertask table widget

define([
    "jscore/core",
    "./TaskTableView",
    "jscore/ext/mvp",
    'tablelib/Table',
    "3pps/wfs/WfDefinitionClient",    
    "3pps/wfs/WfUsertaskClient",
    "./TaskActionsCell/TaskActionsCell",
    "./TaskProgressCell/TaskProgressCell",
    "../../WfBarPercentageCell/WfBarPercentageCell",
	  'tablelib/plugins/StickyHeader',
	  'tablelib/plugins/StickyScrollbar',
	  'tablelib/plugins/Selection',
	  'tablelib/plugins/SortableHeader',
	  'jscore/ext/locationController',
	  'widget/WfUsertasks/TaskForm/TaskForm',
	  ], function(core,View, mvp, Table, WfDefinitionClient, WfUsertaskClient, TaskActionsCell,TaskProgressCell, WfBarPercentageCell,StickyHeader, StickyScrollbar,Selection,SortableHeader,LocationController,TaskForm) {

        return core.Widget.extend({
        	
        	View: View,

            init: function() {
                this.offset = this.options.offset ? this.options.offset : 0;
                this.limit = this.options.limit ? this.options.limit : 20; //default limit 20
                this.active = this.options.active ? this.options.active : null;//default, show all tasks
                this.suspended = this.options.suspended ? this.options.suspended : null;
            },

            onViewReady: function() {
                // construct table immediately because parent will be asking for eventBus
            	
            	var columns = [
                          { title: "Task", attribute: "name", width: "100px", sortable: true },
                          { title: "Version", attribute: "workflowVersion", width: "100px", sortable: true },
                          { title: "Instance Id", attribute: "shortWorkflowInstanceId", width: "150px", sortable: true },
                          { title: "Workflow", attribute: "workflowName", width: "100px", sortable: true },
                          { title: "Created", attribute: "created" , width: "100px", sortable: true }
                       ];

                this.table = new Table({
                	plugins: [               	          
      						new Selection({
      						    selectableRows: true,
      						    multiselect: true
      						}),
                      	          
                              new StickyScrollbar(),
                              new StickyHeader({
                                  topOffset: 34
                              }),
                              new SortableHeader()
                          ],
                    selectableRows: true,
                    modifiers: ["striped"],
                    columns: columns
                });
                this.renderUserTaskTable();
/*            },
            
            subscribeForPushEvents: function(regionEventBus) {                
                this.regionEventBus = regionEventBus;

                this.regionEventBus.subscribe("usertask-event", function(pushEvent) {
                    this.renderUserTaskTable();
                }.bind(this));*/
                
                this.table.addEventHandler("sort", function(sortMode, attribute) {
                    this.table.setData(this.getSortedData(sortMode, attribute));
                }.bind(this));
                
                this.table.addEventHandler("rowselectend", function(selectedRows) {
                    if (selectedRows.length > 0 && !this._inContext) {
                        enterContext.call(this);
                    } else if (selectedRows.length === 0) {
                        leaveContext.call(this);
                    }
                }.bind(this));
                
                function leaveContext() {
                    this._inContext = false;
                    this.options.regionEventBus.publish("topsection:leavecontext");
                }

                function enterContext() {
                    this._inContext = true;
                    this.options.regionEventBus.publish("topsection:contextactions", [{
                        type: "button",
                        color: 'blue',
                        name: "Start User Task",
                        action: startInstance.bind(this)
                    },{
                		 type: 'separator'  
              	   },{
                        type: "link",
                        icon: "tick",
                        flat: true,
                        name: "View Diagram",
                        action: viewDiagram.bind(this)
                        
                    /*},{
                		 type: 'separator'  
              	   },{
                        type: "link",
                        icon: "tick",
                        flat: true,
                        name: "View Instance Table",
                        action: viewInstanceTable.bind(this)*/
                    }]);
                }
                   
                function viewDiagram(){
                	this.table.getSelectedRows().forEach(function(row) {
                        	this.options.regionEventBus.publish("showdiagram", row.options.model);
                        }, this);
                    leaveContext.call(this);               	
                }
                                
                function startInstance() {
                    this.table.getSelectedRows().forEach(function(row) {
//                    	this.options.regionEventBus.publish("toggleHeadingOff", "toggleHeaderOff");
//                    	this.view.getHeadingContent().setStyle("display" , "none");
                    	this.table.detach();
              			var model = new mvp.Model(row.options.model);
              			this.showForm(model);
                    }.bind(this));
                    leaveContext.call(this);
                }
                
                this.options.regionEventBus.subscribe("showHeaderActive",function(showHeaderActive){
//              	  this.view.getHeadingContent().setStyle("display" ,"inline-block");
                  }.bind(this));

                /*function viewInstanceTable() {
                    this.table.getSelectedRows().forEach(function (row) {
                    	var locCtlr = new LocationController();
                        var value = this.value;
                        var url = 'workflowsinstances/' + row.options.model.id + "/" + row.options.model.name;
                        locCtlr.setLocation(url);
                    })
                    leaveContext.call(this);
                }*/
            },
            
            getSortedData: function(sortMode, attribute){
            	this.wfUsertaskCollection2.sort(attribute, sortMode);
            	return this.wfUsertaskCollection2.toJSON();
            },
            
            showForm: function(wfUsertaskModel) {
                // Build and display form
                this.TaskForm = new TaskForm({/*minHeight: this.taskTableContentHeight,*/ wfUsertaskModel:wfUsertaskModel, taskHandledCallback:this.taskHandledCallback.bind(this), regionEventBus: this.options.regionEventBus});
                this.TaskForm.attachTo(this.view.getTaskTableContent());
            },
            
            taskHandledCallback: function(somethingChanged) {
                this.hideForm();
//                this.selectBox.enable();
                this.refreshButtonClicked();            //// refresh table when cancel button pressed
              
//                if (!this.getFilterValue().liveView && this.allowPagination) {
//                    //only show pagination widget when not in live filter view
//                    this.showPagination();
//                }
                if (somethingChanged == true) {
                    setTimeout(function() {
                        this.options.regionEventBus.publish("something-changed", "usertasks");
                    }.bind(this), 2000);
                }
            },
            
            refreshButtonClicked: function() {
            	if(!this.TaskTable){
                 	this.renderUserTaskTable();
                 }
//                this.hideProgress();
                this.hideForm();
            },
            
            hideForm: function() {
                if (this.TaskForm != null) {
                    this.TaskForm.destroy();
                    this.TaskForm = null;
                }
            },

            getEventBus: function () {
                return this.table.getEventBus();
            },

            refresh: function(pageData) {
                this.limit = pageData.limit;
                this.offset = pageData.offset;
                this.renderUserTaskTable();
            },
            
            renderUserTaskTable: function() {    
                
            	WfDefinitionClient.getDefinitionNames({
                    success: function(wfDefinitionCollection) {
                        this.wfUsertaskCollection2 = new mvp.Collection();

                        this.workflowDefinitions = {};
                        this.TaskProgress = {};
                        // Build keyable workflow definitions
                        wfDefinitionCollection.each(function(wfDefinitionModel) {
                            this.workflowDefinitions[wfDefinitionModel.getAttribute("id")] = wfDefinitionModel;
                        }.bind(this));

                        // Get all active usertasks - TODO: filters
                        WfUsertaskClient.getUsertasks({
                            sortBy: "created",
                            sortOrder: "desc",
                            offset: this.offset,
                            limit: this.limit,
                            active: this.active,
                            suspended: this.suspended,
                            success: function(wfUsertaskCollection) {                                
                                // Build model for table
                                
                                wfUsertaskCollection.each(function(wfUsertaskModel) {
                                 
                                    wfUsertaskModel2 = new mvp.Model();

                                    wfUsertaskModel2.setAttribute("id", wfUsertaskModel.getAttribute("id"));
                                    wfUsertaskModel2.setAttribute("definitionId", wfUsertaskModel.getAttribute("taskDefinitionKey"));
                                    wfUsertaskModel2.setAttribute("name", wfUsertaskModel.getAttribute("name"));
                                    wfUsertaskModel2.setAttribute("created", wfUsertaskModel.getAttribute("created"));
                                    wfUsertaskModel2.setAttribute("workflowDefinitionId", wfUsertaskModel.getAttribute("processDefinitionId"));
                                    wfUsertaskModel2.setAttribute("workflowName", this.workflowDefinitions[wfUsertaskModel.getAttribute("processDefinitionId")].getAttribute("name"));
                                    wfUsertaskModel2.setAttribute("workflowVersion", this.workflowDefinitions[wfUsertaskModel.getAttribute("processDefinitionId")].getAttribute("version"));
                                    wfUsertaskModel2.setAttribute("shortWorkflowInstanceId", wfUsertaskModel.getAttribute("processInstanceId").substring(0,8)); // short id
                                    wfUsertaskModel2.setAttribute("workflowInstanceId", wfUsertaskModel.getAttribute("processInstanceId"));

                                    //Check if User task was already addded via push
                                    var model = this.wfUsertaskCollection2.search(wfUsertaskModel.getAttribute("id"), ['id']);

                                    if ( model.length == 0 ) {
                                    	this.wfUsertaskCollection2.addModel(wfUsertaskModel2);
                                    }
        
                                }.bind(this));

                                // Connect model to table
//                                this.table.sort("created", "desc");
                                this.table.setData(this.wfUsertaskCollection2.toJSON());
                                this.table.attachTo(this.getElement());                                
                                
                            }.bind(this)
                        });
                    }.bind(this)
                });
        }

        });
});
