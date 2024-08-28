define([ 
         'jscore/core',
         'jscore/ext/mvp',
         "./WfDefinitionsTableView",
         'tablelib/Table',
         "widgets/Pagination",
		  '3pps/wfs/WfDefinitionClient',
		  "3pps/wfs/WfCountsClient",
		  'jscore/ext/locationController',
		  'widgets/table/Row',
		  'tablelib/plugins/StickyHeader',
		  'tablelib/plugins/StickyScrollbar',
		  'tablelib/plugins/Selection',
		  'tablelib/plugins/SortableHeader',
		  'widget/WfDefinitions/CreateWfInstanceForm/CreateWfInstanceForm',
		  './filterheadercell/FilterHeaderCell',
		  './SecondHeader/SecondHeader'
	], function(core, mvp, View, Table, Pagination, WfDefinitionClient, WfCountsClient, LocationController, Row, StickyHeader, StickyScrollbar, Selection, SortableHeader,CreateWfInstanceForm,FilterHeaderCell,SecondHeader) {

	var filters = {};
	
       return core.Widget.extend({
    	   
    	   View: View,
    	   
    	   init: function() {
               this.offset = this.options.offset ? this.options.offset : 0;
               this.limit = this.options.limit ? this.options.limit : 100; //default limit 20
            },
         
            onViewReady: function() { 
            	            	            	
                var columns = [
                	 {title: "Workflow", attribute: "name", width: "300px", sortable: true, secondHeaderCellType: FilterHeaderCell},
                	 {title: "Version",  attribute: "version", width: "100px", sortable: true , secondHeaderCellType: FilterHeaderCell},
                	 {title: "Created", attribute: "createdOnStr" , width: "130px", sortable: true, secondHeaderCellType: FilterHeaderCell},              	  	 
                	 {title: "Category", attribute: "category", sortable: true , width: "100px", sortable: true, secondHeaderCellType: FilterHeaderCell},
                	 {title: "Description", width: "350px",   attribute: "description" ,secondHeaderCellType: FilterHeaderCell}  
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
                        
                        new SortableHeader(),
                        new SecondHeader()
                    ],
                	selectableRows: true,
                	modifiers: ["striped"],
                	columns: columns
                });
                
                this.table.addEventHandler("filter", function(attribute, value, comparator) {
                    filters[attribute] = {value: value, comparator: comparator};
                    this.table.setData(filterData());
                }.bind(this));
        
                this.renderDefinitionTable();
//                this.showPagination();
                
                var parent = this;
                function filterData() {
                	var results = [];
                	if(parent.filterTableData){
                		results = parent.filterTableData.toJSON();

                    for (var attr in filters) {
                        var filter = filters[attr];
                        results = results.filter(function(obj) {
                            if (filters[attr].value) {
                                switch (filter.comparator) {
                                    case "=":
                                        return obj[attr].indexOf(filters[attr].value) > -1 || obj[attr].toLowerCase().indexOf(filters[attr].value.toLowerCase()) > -1;
                                }
                            } else {
                                return true;
                            }
                        });
                    }
                }
                    return results;
                }
                
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
                        name: "View Diagram",
                        action: viewDiagram.bind(this)
                        
                    },{
                		 type: 'separator'  
              	   },{
                        type: "link",
                        icon: "tick",
                        flat: true,
                        name: "View Instance Table",
                        action: viewInstanceTable.bind(this)
                    }]);
                }
                   
                function viewDiagram(){
                	this.table.getSelectedRows().forEach(function(row) {
                        	this.options.regionEventBus.publish("showdiagram", row.options.model);
                        	this.options.regionEventBus.publish("toggleHeadingOn", "toggleHeaderOn");
                        }, this);
                    leaveContext.call(this);               	
                }
                                
                function startInstance() {
                    this.table.getSelectedRows().forEach(function(row) {
//                    	this.options.regionEventBus.publish("toggleHeadingOff", "toggleHeaderOff");
                    	this.hidePagination();
                    	this.view.getHeadingContent().setStyle("display" , "none");
                    	this.table.detach();
              			var model = new mvp.Model(row.options.model);
              			this.showCreateInstanceForm(model);
                    }.bind(this));
                    leaveContext.call(this);
                }

                function viewInstanceTable() {
                    this.table.getSelectedRows().forEach(function (row) {
                    	var locCtlr = new LocationController();
                        var value = this.value;
                        var url = 'workflowsinstances/' + row.options.model.id + "/" + row.options.model.name;
                        locCtlr.setLocation(url);
                    })
                    leaveContext.call(this);
                }
                
                this.options.regionEventBus.subscribe("showHeader",function(showHeader){
            	  this.view.getHeadingContent().setStyle("display" ,"inline-block");
                }.bind(this));
                
                this.options.regionEventBus.subscribe("showPagination",function(showPagination){
              	  this.showPagination();
                  }.bind(this));
              },
              
              getSortedData: function(sortMode, attribute){
                 	this.wfDefinitionCollection.sort(attribute, sortMode);
                 	return this.wfDefinitionCollection.toJSON();
              },
              
              showCreateInstanceForm: function(wfDefinitionModel) {
                  this.hideCreateInstanceForm();
                  this.options.regionEventBus.publish("hideBottomDivider", "hideBottomDivider");
                  // Build and show 'create instance' form                 
                  this.CreateWfInstanceForm = new CreateWfInstanceForm({minHeight: this.tableContentHeight, wfDefinitionModel:wfDefinitionModel, createHandledCallback:this.createHandledCallback.bind(this), regionEventBus: this.options.regionEventBus});
                  this.CreateWfInstanceForm.attachTo(this.view.getTableContent());                  
              },
              
              showHeader: function(message){
            	  this.view.getHeadingContent().setStyle("display" ,"block");
            	  
              },
              
              hideCreateInstanceForm: function() {
                  if (this.CreateWfInstanceForm != null) {
                      this.CreateWfInstanceForm.destroy();
                  }
                  this.CreateWfInstanceForm = null;
              },
              
              createHandledCallback: function(somethingChanged) {
                  this.hideCreateInstanceForm();
                  if(this.bpmnDiagram) this.bpmnDiagram.destroy();
                  if(this.table){
                	  this.renderDefinitionTable();
                  }else{         
                	  if (this.table != null) {
                          this.table.detach();
                      }	  
                  }                  
              },
           
              renderDefinitionTable: function() { 
	                var workflowDeployments = {};  
	                var showAllVersions = this.options.showAllVersions;
	                var parent = this;

		                // Get workflow definitions
		                WfDefinitionClient.getAllDefinitions({
		                    latest: !showAllVersions,// get all or latest		                    
		                    sortBy: "name",
		                    sortOrder: "asc",
		                    sortingMode: "asc",
		                    offset: this.offset,
                            limit: this.limit,
		                    sortingAttribute: "category",		                    
		                    success: function(serverCollection) {
		                       
		                	this.wfDefinitionCollection = new mvp.Collection();
			                serverCollection.each(function(serverModel) {
			                    var wfDefinitionModel = new mvp.Model();
			                    wfDefinitionModel.setAttribute("id", serverModel.getAttribute("id"));
			                    wfDefinitionModel.setAttribute("name", serverModel.getAttribute("name"));
			                    wfDefinitionModel.setAttribute("key", serverModel.getAttribute("key"));
			                    wfDefinitionModel.setAttribute("version", serverModel.getAttribute("version").toString());
			                    wfDefinitionModel.setAttribute("category", serverModel.getAttribute("category"));
			                    wfDefinitionModel.setAttribute("description", serverModel.getAttribute("description"));
			                    wfDefinitionModel.setAttribute("createdOnStr", serverModel.getAttribute("createdOnStr"));
			                    
			                    this.wfDefinitionCollection.addModel(wfDefinitionModel);
			                }.bind(this));
		                    
		                		// Connect model to table
			                	parent.filterTableData = this.wfDefinitionCollection;
		                        this.table.setData(this.wfDefinitionCollection.toJSON());
		                        this.table.attachTo(this.view.getTableContent());	
		                    }.bind(this)
		               });	                
            },
                        
            showPagination: function() {
                this.hidePagination();
                WfCountsClient.getDefinitionCount({
                	latest: true,
                    success: function(count) {                                
                    	var pages = Math.ceil(count / 22);
                        // Create the pagination widget
                        this.pagination = new Pagination({
                            pages: pages,
                            onPageChange: function(pageNumber) {
                            	var pageOffset = (pageNumber - 1) * 22;
                                this.refresh({offset: pageOffset, limit: 22});
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
            
            refresh: function(pageData) {
                    this.limit = pageData.limit;
                    this.offset = pageData.offset;
                    this.renderDefinitionTable();
            }         
        });
});