// Workflow instances table widget

define([
    "jscore/core",
    "jscore/ext/mvp",
    "./WfAllWorkflowTableView",
    'tablelib/Table',
    "widgets/Pagination",
    "3pps/wfs/WfDefinitionClient",
    "3pps/wfs/WfInstanceClient",
    "3pps/wfs/WfCountsClient",
    "../../WfBarPercentageCell/WfBarPercentageCell",
    "jscore/ext/locationController",
    'widgets/table/Row',
    "../../../widget/WfUsertasks/TaskTable/TaskActionsCell/TaskActionsCell",
    'tablelib/plugins/StickyHeader',
	  'tablelib/plugins/StickyScrollbar',
	  'tablelib/plugins/Selection',
	  'tablelib/plugins/SortableHeader',
	  'jscore/ext/locationController'
    ], function(core, mvp, View, Table,Pagination, WfDefinitionClient,  WfInstanceClient, WfCountsClient,WfBarPercentageCell, LocationController, Row,TaskActionsCell,StickyHeader,StickyScrollbar,Selection,SortableHeader,LocationController) {

       return core.Widget.extend({
    	   
    	   View: View,

            init: function() {
                this.offset = this.options.offset ? this.options.offset : 0;
                this.limit = this.options.limit ? this.options.limit : 20; //default limit 20
                this.sortBy = this.options.sortBy ? this.options.sortBy : "startTime"; //default to startTime
                this.sortOrder = this.options.sortOrder ? this.options.sortOrder : "desc"; //default to desc
                this.showActive = this.options.showActive;
                this.showCompleted = this.options.showCompleted;
                this.hidePercentage = this.options.hidePercentage;
                this.filter = this.options.filter ? this.options.filter : "all";
             },

            onViewReady: function() {
                // construct table immediately because parent may be asking for eventBus
               this.columns = [ 
                    {  	title: "Workflow",       		attribute: "workflowName",           width: "400px", sortable: true},
                    {  	title: "Version",        		attribute: "workflowVersion", sortable: true/*,           width: "100px"*/ },
                    {  	title: "Instance Id",    		attribute: "shortWorkflowInstanceId", sortable: true/*,   width: "300px"*/ },
                ];

                if (this.showActive) {
                    this.columns.push({title: "Start Time", attribute: "startTime", sortable: true/*, width: "150px"*/ })
                }

                if (this.showCompleted) {
                    this.columns.push({title: "End Time", attribute: "endTime", sortable: true/*, width: "150px"*/ })
                }                              

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
                    columns: this.columns,
                });
                this.renderInstanceTable(this.filter);
//                this.showPagination();
                
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
//                        type: "button",
//                        color: 'blue',
//                        name: "Start Instance",
//                        action: startInstance.bind(this)
//                    },{
//                		 type: 'separator'  
//              	   },{
//                        type: "link",
//                        icon: "tick",
//                        flat: true,
//                        name: "View Diagram",
//                        action: viewDiagram.bind(this)
                        
                    /*},{
                		 type: 'separator'  
              	   },{*/
                        type: "link",
                        icon: "tick",
                        flat: true,
                        name: "View Instance",
                        action: Drilldown.bind(this)
                    }]);
                }
                   
//                function viewDiagram(){
//                	this.table.getSelectedRows().forEach(function(row) {
//                        	this.options.regionEventBus.publish("showdiagram", row.options.model);
//                        }, this);
//                    leaveContext.call(this);               	
//                }
                                
//                function startInstance() {
//                    this.table.getSelectedRows().forEach(function(row) {
////                    	this.options.regionEventBus.publish("toggleHeadingOff", "toggleHeaderOff");
//                    	this.view.getHeadingContent().setStyle("display" , "none");
//                    	this.table.detach();
//              			var model = new mvp.Model(row.options.model);
//              			this.showForm(model);
//                    }.bind(this));
//                    leaveContext.call(this);
//                }
                
                function Drilldown() {
                    this.table.getSelectedRows().forEach(function (row) {
                    	var locationController = new LocationController();
                    	var url = 'workflowinstancen/' + row.options.model.workflowDefinitionId + "/" + row.options.model.workflowName.match("(\\.\\s)*(.*)")[2] + "/" 
                    	+ row.options.model.shortWorkflowInstanceId + "/" + row.options.model.startTime + "/" + row.options.model.workflowVersion;
                    	locationController.setLocation(url);
                    	//this.options.regionEventBus.publish("updateRegion", "updateRegion");
                    }.bind(this));
                    leaveContext.call(this);
                }
                
//                this.options.regionEventBus.subscribe("showHeaderActive",function(showHeaderActive){
//              	  this.view.getHeadingContent().setStyle("display" ,"inline-block");
//                  }.bind(this));
                
//                this.table.addEventHandler("rowclick", function(row, wfDefinitionModel, isSelected){
//                	var locationController = new LocationController(wfDefinitionModel.attributes);
//                	var url = 'workflowinstancen/' + wfDefinitionModel.getAttribute("workflowDefinitionId") + "/" + wfDefinitionModel.getAttribute("workflowName").match("(\\.\\s)*(.*)")[2] + "/" 
//                	+ wfDefinitionModel.getAttribute("shortWorkflowInstanceId") + "/" + wfDefinitionModel.getAttribute("startTime") + "/" + wfDefinitionModel.getAttribute("workflowVersion");
//                	
//                	locationController.setLocation(url , false);
//                    locationController.start();
//                }.bind(this));
                
            },
            
            getSortedData: function(sortMode, attribute){
            	this.tableInstances.sort(attribute, sortMode);
            	return this.tableInstances.toJSON();
            },

//            subscribeForPushEvents: function(regionEventBus) {                
//                this.regionEventBus = regionEventBus;
//                this.regionEventBus.subscribe("instance-event", function(pushEvent) {                
//                    this.handlePushEvent(pushEvent);
//                }.bind(this));
//            },

//            handlePushEvent: function(pushEvent) {
//                setTimeout(function () {            
//                        this.renderInstanceTable(this.filter);
//                    }.bind(this), 2000);
//            },

//            refresh: function(pageData) {
//                this.limit = pageData.limit;
//                this.offset = pageData.offset;
//                this.renderInstanceTable(this.filter);
//            },

            renderInstanceTable: function(filter) {
            	//Refactored needed here!!
            	if(filter == "all"){
            		
            		// Get workflow definitions - need these for workflow 'name'
                	WfDefinitionClient.getDefinitionNames({
                        success: function(wfDefinitionCollection) {
                            this.workflowDefinitions = {};
                            // Build keyable workflow definitions
                            wfDefinitionCollection.each(function(model) {
                            	this.workflowDefinitions[model.getAttribute("id")] = model;
                            }.bind(this));
                                                       
                            // Get workflow instances
                            WfInstanceClient.getInstances({
                                sortBy: this.sortBy,
                                sortOrder: this.sortOrder,
                                offset: this.offset,
                                limit: this.limit,
                                completed: this.showCompleted,
                                active: this.showActive,
                                success: function(wfInstanceCollection) {
                                    // Build model for table
                                    this.tableInstances = new mvp.Collection();
                                    wfInstanceCollection.each(function(model) {
                                        var rowModel = new mvp.Model();
                                        
                                        var definitionId = model.getAttribute("definitionId");
                                        var wfName = "";
                                        if(this.workflowDefinitions[definitionId]){
                                        	wfName = this.workflowDefinitions[definitionId].getAttribute("name");
                                        }
                                        rowModel.setAttribute("workflowName", wfName);
                                        rowModel.setAttribute("workflowVersion", this.workflowDefinitions[definitionId].getAttribute("version"));
                                        rowModel.setAttribute("shortWorkflowInstanceId", model.getAttribute("id"));      
                                        rowModel.setAttribute("workflowInstanceId", model.getAttribute("id"));
                                        rowModel.setAttribute("workflowDefinitionId", definitionId);
                                        rowModel.setAttribute("startTime", model.getAttribute("startTime"));
                                        rowModel.setAttribute("endTime", model.getAttribute("endTime"));
                                        rowModel.setAttribute("superProcessInstanceId", model.getAttribute("superProcessInstanceId"));/////////////newly added on may 2014(already existing in the JSON)
                                        this.tableInstances.addModel(rowModel);
                                    }.bind(this));
                                    // TODO - reformat date/time                               
                                    this.tableInstances = this.arrangeHierarchically(this.tableInstances); ///////////////////////////////////// to arrange rows according to hierarchy
                                    // Connect model to table
//                                    tableInstances.sort("startTime", 'desc');
                                    this.table.setData(this.tableInstances.toJSON());
                                    /*var previous = "parent";
                                    var stripped = true;
                                    for(var rows in this.table.getRows()){                                   	
                                    	var name = this.table.getRows()[rows].options.data.getAttribute("workflowName");
                                    	if(name.indexOf(". . . ") != -1) {
                                    		previous = "child";
                                    	} else {
                                    		previous = "parent";
                                    	}
                                    	if(previous === "parent") {
                                    		stripped = !stripped ;
                                    	}
                                    	if(stripped) {
                                    		this.table.getRows()[rows].getElement().setStyle("background-color", "#f2f2f2");
                                    	}
                                    }*/
                                    this.table.attachTo(this.view.getTableContent());                                
                                }.bind(this)
                            });

                            this.highlightedRow = null;
                            // Add row selected/deselected handlers - they publish events
                            this.table.addEventHandler("rowclick", function(row, model) {
                                if (!row.isHighlighted()) {
                                    if (this.highlightedRow != null) {
                                        this.highlightedRow.highlight(false); 
                                        this.getEventBus().publish("instance-deselected", this.highlightedRow.getData().attributes); /////
                                        this.highlightedRow = null;
                                    }
                                    row.highlight(true);
                                    this.highlightedRow = row; 
                                    this.getEventBus().publish("instance-selected",
                                        { workflowDefinitionId: model.getAttribute("workflowDefinitionId"),
                                          workflowInstanceId: model.getAttribute("workflowInstanceId"),
                                          workflowName: model.getAttribute("workflowName")});
                                }
                                else {
                                    row.highlight(false);
                                    this.highlightedRow = null;
                                    this.getEventBus().publish("instance-deselected", row.getData().attributes);
                                }
                            }.bind(this));
                        }.bind(this)
                    });
            		
            	}
            	else if(filter =="active"){
            		
            		// Get workflow definitions - need these for workflow 'name'
                	WfDefinitionClient.getDefinitionNames({
                        success: function(wfDefinitionCollection) {
                            this.workflowDefinitions = {};
                            // Build keyable workflow definitions
                            wfDefinitionCollection.each(function(model) {
                            	this.workflowDefinitions[model.getAttribute("id")] = model;
                            }.bind(this));
                                                      
                            // Get workflow instances
                            WfInstanceClient.getSuspendedInstances({
                                active: true,
                                success: function(wfInstanceCollection) {
                                	
                                	WfInstanceClient.getSuspendedInstancesDetails({
                                		 sortBy: this.sortBy,
                                         sortOrder: this.sortOrder,
                                         offset: this.offset,
                                         limit: this.limit,
                                         completed: this.showCompleted,
                                         unfinished: this.showActive,
                                         wfInstanceCollection: wfInstanceCollection,
                                         success: function(wfInstanceCollection) {
                                             // Build model for table inner success
                                           var tableInstances = new mvp.Collection();
                                           wfInstanceCollection.each(function(model) {
                                               var rowModel = new mvp.Model();
                                               
                                               var definitionId = model.getAttribute("definitionId");
                                               var wfName = "";
                                               if(this.workflowDefinitions[definitionId]){
                                               	wfName = this.workflowDefinitions[definitionId].getAttribute("name");
                                               }
                                               rowModel.setAttribute("workflowName", wfName);
                                               rowModel.setAttribute("workflowVersion", this.workflowDefinitions[definitionId].getAttribute("version"));
                                               rowModel.setAttribute("shortWorkflowInstanceId", model.getAttribute("id"));      
                                               rowModel.setAttribute("workflowInstanceId", model.getAttribute("id"));
                                               rowModel.setAttribute("workflowDefinitionId", definitionId);
                                               rowModel.setAttribute("startTime", model.getAttribute("startTime"));
                                               rowModel.setAttribute("endTime", model.getAttribute("endTime"));
                                               rowModel.setAttribute("superProcessInstanceId", model.getAttribute("superProcessInstanceId"));/////////////newly added on may 2014(already existing in the JSON)
                                               tableInstances.addModel(rowModel);
                                           }.bind(this));
                                           // TODO - reformat date/time                               
                                           tableInstances = this.arrangeHierarchically(tableInstances); ///////////////////////////////////// to arrange rows according to hierarchy
                                           // Connect model to table
                                           this.table.setData(tableInstances.toJSON());                                
                                           this.table.attachTo(this.view.getTableContent());         
                                         }.bind(this)
                                	});
                           
                                }.bind(this)
                            });

                            this.highlightedRow = null;
                            // Add row selected/deselected handlers - they publish events
                            this.table.addEventHandler("rowclick", function(row, model) {
                                if (!row.isHighlighted()) {
                                    if (this.highlightedRow != null) {
                                        this.highlightedRow.highlight(false); 
                                        this.getEventBus().publish("instance-deselected", this.highlightedRow.getData().attributes); /////
                                        this.highlightedRow = null;
                                    }
                                    row.highlight(true);
                                    this.highlightedRow = row; 
                                    this.getEventBus().publish("instance-selected",
                                        { workflowDefinitionId: model.getAttribute("workflowDefinitionId"),
                                          workflowInstanceId: model.getAttribute("workflowInstanceId"),
                                          workflowName: model.getAttribute("workflowName")});
                                }
                                else {
                                    row.highlight(false);
                                    this.highlightedRow = null;
                                    this.getEventBus().publish("instance-deselected", row.getData().attributes);
                                }
                            }.bind(this));
                        }.bind(this)
                    });
            		
            	}
            	else if (filter == "suspended"){
            		
            		// Get workflow definitions - need these for workflow 'name'
                	WfDefinitionClient.getDefinitionNames({
                        success: function(wfDefinitionCollection) {
                            this.workflowDefinitions = {};
                            // Build keyable workflow definitions
                            wfDefinitionCollection.each(function(model) {
                            	this.workflowDefinitions[model.getAttribute("id")] = model;
                            }.bind(this));
                                                        
                            // Get workflow instances
                            WfInstanceClient.getSuspendedInstances({
                                suspended: true,
                                success: function(wfInstanceCollection) {
                                	
                                	WfInstanceClient.getSuspendedInstancesDetails({
                                		 sortBy: this.sortBy,
                                         sortOrder: this.sortOrder,
                                         offset: this.offset,
                                         limit: this.limit,
                                         completed: this.showCompleted,
                                         unfinished: this.showActive,
                                         wfInstanceCollection: wfInstanceCollection,
                                         success: function(wfInstanceCollection) {
                                             // Build model for table inner success
                                           var tableInstances = new mvp.Collection();
                                           wfInstanceCollection.each(function(model) {
                                               var rowModel = new mvp.Model();
                                               
                                               var definitionId = model.getAttribute("definitionId");
                                               var wfName = "";
                                               if(this.workflowDefinitions[definitionId]){
                                               	wfName = this.workflowDefinitions[definitionId].getAttribute("name");
                                               }
                                               rowModel.setAttribute("workflowName", wfName);
                                               rowModel.setAttribute("workflowVersion", this.workflowDefinitions[definitionId].getAttribute("version"));
                                               rowModel.setAttribute("shortWorkflowInstanceId", model.getAttribute("id"));      
                                               rowModel.setAttribute("workflowInstanceId", model.getAttribute("id"));
                                               rowModel.setAttribute("workflowDefinitionId", definitionId);
                                               rowModel.setAttribute("startTime", model.getAttribute("startTime"));
                                               rowModel.setAttribute("endTime", model.getAttribute("endTime"));
                                               rowModel.setAttribute("superProcessInstanceId", model.getAttribute("superProcessInstanceId"));/////////////newly added on may 2014(already existing in the JSON)
                                               tableInstances.addModel(rowModel);
                                           }.bind(this));
                                           // TODO - reformat date/time                               
                                           tableInstances = this.arrangeHierarchically(tableInstances); ///////////////////////////////////// to arrange rows according to hierarchy
                                           // Connect model to table
                                           this.table.setData(tableInstances.toJSON()); 
                                           this.table.attachTo(this.view.getTableContent());         
                                         }.bind(this)
                                	});
                           
                                }.bind(this)
                            });

                            this.highlightedRow = null;
                            // Add row selected/deselected handlers - they publish events
                            this.table.addEventHandler("rowclick", function(row, model) {
                                if (!row.isHighlighted()) {
                                    if (this.highlightedRow != null) {
                                        this.highlightedRow.highlight(false); 
                                        this.getEventBus().publish("instance-deselected", this.highlightedRow.getData().attributes); /////
                                        this.highlightedRow = null;
                                    }
                                    row.highlight(true);
                                    this.highlightedRow = row; 
                                    this.getEventBus().publish("instance-selected",
                                        { workflowDefinitionId: model.getAttribute("workflowDefinitionId"),
                                          workflowInstanceId: model.getAttribute("workflowInstanceId"),
                                          workflowName: model.getAttribute("workflowName")});
                                }
                                else {
                                    row.highlight(false);
                                    this.highlightedRow = null;
                                    this.getEventBus().publish("instance-deselected", row.getData().attributes);
                                }
                            }.bind(this));
                        }.bind(this)
                    });
            		
            	}                
            },

            getEventBus: function () {
                return this.table.getEventBus();
            },
            
            showPagination: function() {
                this.hidePagination();
                var active = null;
                var suspended = null;
                if(this.filter =="all"){
                	active = null;
                	suspended = null;
                }
                if(this.filter =="active"){
                	active = true;
                	suspended = false;
                }
                if(this.filter =="suspended"){
                	active = false;
                	suspended  = true;
                }
                // Get total number of workflow instances in order to calculate the required num pages
                WfCountsClient.getWorkflowInstanceActive({
                	active: active,
	            	suspended: suspended,
                    success: function(count) {                                
                    	var pages = Math.ceil(count / 19);
                        // Create the pagination widget
                        this.pagination = new Pagination({
                            pages: pages,
                            onPageChange: function(pageNumber) {
                            	var pageOffset = (pageNumber - 1) * 19;
                                this.refresh({offset:pageOffset, limit:19});
                            }.bind(this)
                        });
                        this.pagination.attachTo(this.view.getPagination());
                        
                    }.bind(this)
                });
            },
            
            hidePagination: function() {
                if (this.pagination != null) {
                    this.pagination.destroy();
                    this.pagination = null;
                }
            },

////////////////////////////////// function to arrange the children processes indented wrt parent
//// takes each workflow and search for its children based on "superProcessInstanceId"
//// puts the parent on top of a new collection and each child indented with ". . ."
//// if no child available, simply add the work flow in new collection
            arrangeHierarchically: function (rowsCollection) { 
            	var stripped = true;
                var rowsCollection1 = rowsCollection;
                var rowsCollection2 = new mvp.Collection();
                rowsCollection1.each(function(row) {
//                	row.setAttribute("stripped", stripped);
                    var children = rowsCollection1.searchMap(row.get("workflowInstanceId"), ["superProcessInstanceId"]);
                    if(children.size()){
                        rowsCollection2.addModel(row);
                        children.each(function (child) {
                        	if(rowsCollection1.searchMap(row.get("superProcessInstanceId"), ["workflowInstanceId"]).size() == 0){
//                        		child.setAttribute("stripped", stripped);
                        		child.setAttribute("workflowName", ". . . ".concat(child.getAttribute("workflowName")));
                        	}
                        	else{
//                        		child.setAttribute("stripped", stripped);
                        		child.setAttribute("workflowName", ". . . . . . ".concat(child.getAttribute("workflowName")));
                        	}
                        });
                        rowsCollection2.addModel(children._collection.models);
                    }
                    else if(rowsCollection1.searchMap(row.get("superProcessInstanceId"), ["workflowInstanceId"]).size() == 0) {
                    	
                        rowsCollection2.addModel(row);
                    }
                    stripped = !stripped;
                });
//                console.log(rowsCollection2);
//                for(var r in rowsCollection2._collection.models) {
//                	console.log(rowsCollection2._collection.models[r].getAttribute("workflowName") + " : " + rowsCollection2._collection.models[r].getAttribute("stripped"));
//                }
                return rowsCollection2;              
            }.bind(this)    
        });
});