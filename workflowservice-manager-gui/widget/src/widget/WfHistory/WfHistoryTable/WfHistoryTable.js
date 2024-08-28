// Workflow instances table widget

define([
    "jscore/core",
    "jscore/ext/mvp",
    "./WfHistoryTableView",
    'tablelib/Table',
    "widgets/Pagination",
    "3pps/wfs/WfDefinitionClient",
    "3pps/wfs/WfInstanceClient",
    "../../WfBarPercentageCell/WfBarPercentageCell",
    "jscore/ext/locationController",
    'widgets/table/Row',
    "../../../widget/WfUsertasks/TaskTable/TaskDiagramCell/TaskDiagramCell",
	  'tablelib/plugins/StickyHeader',
	  'tablelib/plugins/StickyScrollbar',
	  'tablelib/plugins/Selection',
	  'tablelib/plugins/SortableHeader'	  
    ], function(core, mvp, View, Table,Pagination, WfDefinitionClient, WfInstanceClient,WfBarPercentageCell, LocationController, Row,TaskDiagramCell,StickyHeader,StickyScrollbar,Selection,SortableHeader) {

       return core.Widget.extend({
    	   
    	   View: View,

            init: function() {
                this.offset = this.options.offset ? this.options.offset : 0;
                this.limit = this.options.limit ? this.options.limit : 20; //default limit 20
                this.sortBy = this.options.sortBy ? this.options.sortBy : "endTime"; //default to startTime
                this.sortOrder = this.options.sortOrder ? this.options.sortOrder : "desc"; //default to desc
                this.showActive = this.options.showActive;
                this.showCompleted = this.options.showCompleted;
                this.hidePercentage = this.options.hidePercentage;
                this.hideActionButtons = this.options.hideActionButtons;
                
             },

            onViewReady: function() {
                // construct table immediately because parent may be asking for eventBus
            	// make sortable
               this.columns = [ 
                    {  	title: "Workflow",       		attribute: "workflowName", sortable: true},
                    {  	title: "Version",        		attribute: "workflowVersion",           width: "100px", sortable: true},
                    {  	title: "Instance Id",    		attribute: "shortWorkflowInstanceId",   width: "100px", sortable: true },
                    ];

                if (this.showActive) {
                    this.columns.push({title: "Start Time", attribute: "startTime", width: "150px", sortable: true });
                }

                if (this.showCompleted) {
                    this.columns.push({title: "End Time", attribute: "endTime", width: "150px", sortable: true });
                }
                
//                if(!this.options.hideActionButtons){
//                	this.columns.push({ title: "Actions", width: "130px",  cellType: TaskDiagramCell });
//                }                           

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
     						// add sortable plugin
                             new SortableHeader()
                         ],
                         selectableRows: true,
                         columns: this.columns
                });              	
                this.renderInstanceTable();
                this.showPagination();
                
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
                        type: "link",
                        icon: "tick",
                        flat: true,
                        name: "View Diagram",
                        action: viewDiagram.bind(this)                  
                    }]);
                }
                   
                function viewDiagram(){
                	this.table.getSelectedRows().forEach(function(row) {
                        	this.options.regionEventBus.publish("showdiagram", row.options.model);
                        }, this);
                    leaveContext.call(this);               	
                }                        
            },
            
            //#TODO implement sorting method
            getSortedData: function(sortMode, attribute){
            	this.tableInstances.sort(attribute, sortMode);
            	return this.tableInstances.toJSON();
            },
            

            subscribeForPushEvents: function(regionEventBus) {                
                this.regionEventBus = regionEventBus;
                this.regionEventBus.subscribe("instance-event", function(pushEvent) {                
                    this.handlePushEvent(pushEvent);
                }.bind(this));
            },

            handlePushEvent: function(pushEvent) {
                setTimeout(function () {            
                        this.renderInstanceTable();
                    }.bind(this), 2000);
            },

            refresh: function(pageData) {
                this.limit = pageData.limit;
                this.offset = pageData.offset;
                this.renderInstanceTable();
            },

            renderInstanceTable: function() {
                // Get workflow definitions - need these for workflow 'name'
            	WfDefinitionClient.getDefinitionNames({
                    success: function(wfDefinitionCollection) {
                    	var processDefinitionId = this.getProcessDefinitionId();
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
                                    rowModel.setAttribute("workflowName", this.workflowDefinitions[definitionId].getAttribute("name"));
                                    rowModel.setAttribute("workflowVersion", this.workflowDefinitions[definitionId].getAttribute("version"));
                                    rowModel.setAttribute("shortWorkflowInstanceId", model.getAttribute("id").substring(0,8));       // short id
                                    rowModel.setAttribute("workflowInstanceId", model.getAttribute("id"));
                                    rowModel.setAttribute("workflowDefinitionId", definitionId);
                                    rowModel.setAttribute("startTime", model.getAttribute("startTime"));
                                    rowModel.setAttribute("endTime", model.getAttribute("endTime"));
                                    rowModel.setAttribute("startActivityId", model.getAttribute("startActivityId"));
                                    rowModel.setAttribute("superProcessInstanceId", model.getAttribute("superProcessInstanceId"));/////////////newly added on may 2014(already existing in the JSON)
                                    this.tableInstances.addModel(rowModel);
                                }.bind(this));
                                // TODO - reformat date/time                               
                                this.tableInstances = this.arrangeHierarchically(this.tableInstances); ///////////////////////////////////// to arrange rows according to hierarchy
                                this.tableInstances.sort("endTime", 'desc');
                                // Connect model to table
                                this.table.setData(this.tableInstances.toJSON());
                                var previous = "parent";
                                var stripped = true;
                                for(var rows in this.table.getRows()){                                   	
                                	var name = this.table.getRows()[rows].options.model.workflowName;
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
                                }
                                this.table.attachTo(this.view.getTableContent());                                
                            }.bind(this)
                        });

                        this.highlightedRow = null;
                        // Add row selected/deselected handlers - they publish events
                        this.table.addEventHandler("rowclick", function(row, model) {
                            if (!row.isHighlighted()) {
                                if (this.highlightedRow != null) {
                                    this.highlightedRow.highlight(false); 
                                    this.options.regionEventBus.publish("instance-deselected", this.highlightedRow.getData().attributes); /////
                                    this.highlightedRow = null;
                                }
                                row.highlight(true);
                                this.highlightedRow = row; 
                                this.options.regionEventBus.publish("instance-selected",
                                    { workflowDefinitionId: model.getAttribute("workflowDefinitionId"),
                                      workflowInstanceId: model.getAttribute("workflowInstanceId"),
                                      workflowName: model.getAttribute("workflowName")});
                            }
                            else {
                                row.highlight(false);
                                this.highlightedRow = null;
                                this.options.regionEventBus.publish("instance-deselected", row.getData().attributes);
                            }
                        }.bind(this));
                    }.bind(this)
                });
            },

            getProcessDefinitionId: function() {
            	var url = window.location.hash;
                var arguments = url.substring(url.indexOf('/') + 1).split('/');
                return arguments[0];
            },
            
            showPagination: function() {
                this.hidePagination();
                // Get total number of workflow instances in order to calculate the required num pages
                WfInstanceClient.getInstancesCount({
                	completed:true,
                    success: function(count) {                                
                    	var pages = Math.ceil(count / 20);
                        // Create the pagination widget
                        this.pagination = new Pagination({
                            pages: pages,
                            onPageChange: function(pageNumber) {
                            	var pageOffset = (pageNumber - 1) * 20;
                                this.refresh({offset:pageOffset, limit:20});
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
                    var children = rowsCollection1.searchMap(row.get("workflowInstanceId"), ["superProcessInstanceId"]);
                    if(children.size()){
                        rowsCollection2.addModel(row);
                        children.each(function (child) {
                        	if(rowsCollection1.searchMap(row.get("superProcessInstanceId"), ["workflowInstanceId"]).size() == 0){
                        		child.setAttribute("workflowName", ". . . ".concat(child.getAttribute("workflowName")));
                        	}
                        	else{
                        		child.setAttribute("workflowName", ". . . . . . ".concat(child.getAttribute("workflowName")));
                        	}
                        });
                        rowsCollection2.addModel(children._collection.models);
                    }
                    else if(rowsCollection1.searchMap(row.get("superProcessInstanceId"), ["workflowInstanceId"]).size() == 0){
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