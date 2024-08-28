// Workflow definitions table widget

define([
    "jscore/core",
    'jscore/ext/mvp',
    'tablelib/Table',
    "3pps/wfs/WfDefinitionClient",
    "./WfAllDefinitionActionsCell/WfAllDefinitionActionsCell",
    "jscore/ext/locationController",
    'widgets/table/Row',
    'tablelib/plugins/StickyHeader',
	 'tablelib/plugins/StickyScrollbar',
	 'tablelib/plugins/Selection',
	 'tablelib/plugins/SortableHeader'
    ], function(core, mvp, Table, WfDefinitionClient, WfAllDefinitionActionsCell, LocationController, Row,StickyHeader,StickyScrollbar,Selection,SortableHeader) {

       return core.Widget.extend({

               onViewReady: function() {
                   var showAllVersions = this.options.showAllVersions;
                   this.columns = [
                   	 {  title: "Workflow", attribute: "name", sortable: true },
                   	 {  title: "Version",  attribute: "version", width: "80px", sortable: true },
                   	 {	title: "Created", attribute: "createdOnStr", sortable: true },
                   	 {	title: "Category", attribute: "category", sortable: true, sortable: true },
                     {  title: "Description", width: "300px",   attribute: "description", }
                   	 ];
                   
//                   if(!this.options.showActionButtons){
//                   this.columns.push({title: "Actions", width: "90px",  cellType: WfAllDefinitionActionsCell });
//                   }
                   
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
                   	columns: this.columns
                   });  
                   this.renderDefinitionTable();
                   
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
                           name: "Start Instance",
                           action: startInstance.bind(this)
                       },{
                   		 type: 'separator'  
                 	   },{
                           type: "link",
                           icon: "tick",
                           flat: true,
                           name: "View Instance",
                           action: Drilldown.bind(this)
                       }]);
                   }
                                   
                   function startInstance() {
                       this.table.getSelectedRows().forEach(function(row) {
//                       	this.options.regionEventBus.publish("toggleHeadingOff", "toggleHeaderOff");
//                       	this.view.getHeadingContent().setStyle("display" , "none");
                    	   this.options.regionEventBus.publish("createinstance", row.options.model);
                    	   this.renderDefinitionTable();
                       }.bind(this));
                       leaveContext.call(this);
                   }
                   
                   function Drilldown() {
                       this.table.getSelectedRows().forEach(function (row) {
                       	var locationController = new LocationController();
                       	var url = 'workflowinstancen/' + row.options.model.workflowDefinitionId + "/" + row.options.model.workflowName + "/" 
                       	+ row.options.model.shortWorkflowInstanceId + "/" + row.options.model.startTime + "/" + row.options.model.workflowVersion;
                       	locationController.setLocation(url);
                       })
                       leaveContext.call(this);
                   }
               },
               
               getSortedData: function(sortMode, attribute){
               	this.wfDefinitionCollection.sort(attribute, sortMode);
               	return this.wfDefinitionCollection.toJSON();
               },
               
               renderDefinitionTable: function() { 
	                var workflowDeployments = {};  
	                var showAllVersions = this.options.showAllVersions;

		                // Get workflow definitions
		                WfDefinitionClient.getAllDefinitions({
		                    latest: !showAllVersions,       // get all or latest
		                    sortBy: "name",
		                    sortOrder: "asc",
		                    sortingMode: "asc",
		                    sortingAttribute: "category",
		                    success: function(serverCollection) {
		                       
		                	this.wfDefinitionCollection = new mvp.Collection();
			                serverCollection.each(function(serverModel) {
			                	var wfDefinitionModel = new mvp.Model();
			                    wfDefinitionModel.setAttribute("id", serverModel.getAttribute("id"));
			                    wfDefinitionModel.setAttribute("name", serverModel.getAttribute("name"));
			                    wfDefinitionModel.setAttribute("key", serverModel.getAttribute("key"));
			                    wfDefinitionModel.setAttribute("version", serverModel.getAttribute("version"));
			                    wfDefinitionModel.setAttribute("category", serverModel.getAttribute("category"));
			                    wfDefinitionModel.setAttribute("description", serverModel.getAttribute("description"));
			                    wfDefinitionModel.setAttribute("createdOnStr", serverModel.getAttribute("createdOnStr"));			                    
			                    this.wfDefinitionCollection.addModel(wfDefinitionModel);
			                    
//			                    this.table.addEventHandler("sort", function(attribute, sortingMode) {
//			                    	wfDefinitionCollection.sort(attribute, sortingMode);
//			                    });
			                }.bind(this));
		                    
		                		// Connect model to table
		                        this.table.setData(this.wfDefinitionCollection.toJSON());
		                        this.table.attachTo(this.getElement());
		                    }.bind(this)
		               });
           }               
        });
   });