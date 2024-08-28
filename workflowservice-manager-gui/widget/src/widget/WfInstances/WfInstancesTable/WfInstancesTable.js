//Workflow instances table widget

define([
        "jscore/core",
        "jscore/ext/mvp",
        "../WfInstancesView",
        "widgets/Table",
        "3pps/wfs/WfDefinitionClient",
        "3pps/wfs/WfCountsClient",
        "../../WfBarPercentageCell/WfBarPercentageCell",
        "../../WfInstanceProcessIcon/WfInstanceProcessIcon",
        "jscore/ext/locationController",
        'widgets/table/Row',
        "widgets/Notification",
        'tablelib/plugins/StickyHeader',
        'tablelib/plugins/StickyScrollbar',
        'tablelib/plugins/Selection',
        'tablelib/plugins/SortableHeader',
        ], function(core, mvp, View, Table, WfDefinitionClient, WfCountsClient,WfBarPercentageCell,WfInstanceProcessIcon, LocationController, Row, Notification,StickyHeader,StickyScrollbar,Selection,SortableHeader) {

	return core.Widget.extend({

		init: function() {
			this.offset = this.options.offset ? this.options.offset : 0;
			this.limit = this.options.limit ? this.options.limit : 20; //default limit 20
			this.sortBy = this.options.sortBy ? this.options.sortBy : "startTime"; //default to startTime
			this.sortOrder = this.options.sortOrder ? this.options.sortOrder : "desc"; //default to desc
			this.showActive = this.options.showActive;
			this.showCompleted = this.options.showCompleted;
			this.hidePercentage = this.options.hidePercentage; 
		},

		onViewReady: function() {
			this.columns = [ 
			                {  	title: "Workflow",       		attribute: "workflowName", sortable: true},
			                {  	title: "Version",        		attribute: "workflowVersion",           width: "100px", sortable: true },
			                {  	title: "Instance Id",    		attribute: "shortWorkflowInstanceId",   width: "300px", sortable: true },
			                ];

			if (this.showActive) {
				this.columns.push({title: "Start Time", attribute: "startTime", width: "150px", sortable: true });
			}

			if (this.showCompleted) {
				this.columns.push({title: "End Time", attribute: "endTime", width: "150px", sortable: true });
			}

			if (!this.options.hidePercentage){
				this.columns.push({title: "Percentage Complete", attribute: "progressPercentage", width: "160px",  cellType: WfBarPercentageCell });
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
//				          modifiers: ["striped"],
				          columns: this.columns
			});
			this.renderInstanceTable(); 

			this.table.addEventHandler("sort", function(sortMode, attribute) {
				this.table.setData(this.getSortedData(sortMode, attribute));
			}.bind(this));

			this.table.addEventHandler("rowclick", function(row, wfDefinitionModel, isSelected){
				var locationController = new LocationController(wfDefinitionModel.attributes);
				var url = 'workflowinstancen/' + wfDefinitionModel.getAttribute("workflowDefinitionId") + "/" + wfDefinitionModel.getAttribute("workflowName").match("(\\.\\s)*(.*)")[2] + "/" 
				+ wfDefinitionModel.getAttribute("shortWorkflowInstanceId") + "/" + wfDefinitionModel.getAttribute("startTime") + "/" + wfDefinitionModel.getAttribute("workflowVersion");
				locationController.setLocation(url , false);
				locationController.start();
			}.bind(this));                 
		},

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
					var workflowDefinitionId = this.getProcessDefinitionId();
					this.workflowDefinitions = {};
					// Build keyable workflow definitions
					wfDefinitionCollection.each(function(model) {
						this.workflowDefinitions[model.getAttribute("id")] = model;
					}.bind(this));             

					WfCountsClient.getInstances2({
						workflowDefinitionId : workflowDefinitionId,
						success: function(wfInstanceCollection) {   
							this.tableInstances = new mvp.Collection();
							wfInstanceCollection.each(function(model) {                           
								var rowModel = new mvp.Model();
								var wrkDefId =  model.getAttribute("workflowDefinitionId");
								rowModel.setAttribute("workflowStatus", "active");
								rowModel.setAttribute("workflowDefinitionId", wrkDefId);
								rowModel.setAttribute("workflowName", this.workflowDefinitions[wrkDefId].getAttribute("name"));
								rowModel.setAttribute("workflowVersion", this.workflowDefinitions[wrkDefId].getAttribute("version"));
								rowModel.setAttribute("shortWorkflowInstanceId", model.getAttribute("id")); 
								rowModel.setAttribute("startTime", model.getAttribute("startTime"));
								rowModel.setAttribute("progressPercentage", model.getAttribute("progressPercentage"));
								rowModel.setAttribute("workflowInstanceId", model.getAttribute("id"));
								rowModel.setAttribute("superProcessInstanceId", model.getAttribute("superProcessInstanceId"));/////////////newly added on may 2014(already existing in the JSON)
								this.tableInstances.addModel(rowModel);
							}.bind(this));

							this.tableInstances = this.arrangeHierarchically(this.tableInstances); ///////////////////////////////////// to arrange rows according to hierarchy
//							// Connect model to table
							this.table.setData(this.tableInstances); 
//							tableInstances.sort("startTime", 'desc');
							var previous = "parent";
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
							}
							this.table.attachTo(this.getElement());                              
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
								workflowName: model.getAttribute("workflowName").match("(\\.\\s)*(.*)")[2]});
						}
						else {
							row.highlight(false);
							this.highlightedRow = null;
							this.getEventBus().publish("instance-deselected", row.getData().attributes);
						}
					}.bind(this));
				}.bind(this)
			});
		},

		getEventBus: function () {
			return this.table.getEventBus();
		},

		getProcessDefinitionId: function() {
			var url = window.location.hash;
			var arguments = url.substring(url.indexOf('/') + 1).split('/');
			return arguments[0];
		},

		getInstanceName: function() {
			var url = window.location.hash;
			var arguments = url.substring(url.indexOf('/') + 1).split('/');
			return arguments[1];
		},

//////////////////////////////////function to arrange the children processes indented wrt parent
////		takes each workflow and search for its children based on "superProcessInstanceId"
////		puts the parent on top of a new collection and each child indented with ". . ."
////		if no child available, simply add the work flow in new collection
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
			return rowsCollection2;              
		}.bind(this)  
	});
});
