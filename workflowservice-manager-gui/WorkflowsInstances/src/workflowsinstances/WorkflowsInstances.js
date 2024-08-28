define([
    'jscore/core',
    "jscore/ext/mvp",
    './WorkflowsInstancesView',
    'widget/WfInstances/WfInstances',
    'widget/WfDefinitions/CreateWfInstanceForm/CreateWfInstanceForm',
    'widget/WfInstances/WfInstancesTable/WfInstancesTable',
    'layouts/SlidingPanels',
    "layouts/TopSection",
    "jscore/ext/locationController",
    'widget/WfInstances/InstancesCounters/InstancesCounters'
], function (core, mvp, View, WfInstances, CreateWfInstanceForm, WfInstancesTable, SlidingPanels, TopSection, LocationController, InstancesCounters) {   	
    	return core.App.extend({

    	View: View,
        
         updateURLParams: function(){
        	 var url = window.location.hash;
             var arguments = url.substring(url.indexOf('/') + 1).split('/');
             this.params = {
             		id : arguments[0],
             		name : arguments[1],
             };    
         },
         
         onAttach: function() {                          
        	 if(this.wfInstances){
            	 this.wfInstances.update();
            	 this.hideCreateInstanceForm();
            	 this.showTable();
             }                      
		},
        
        onStart: function () {        	       	
	        var parent = this;
	        parent.updateURLParams();
	        
	        var newBreadcrumb = this.options.breadcrumb;
	        
	        var crumb = [{name: "ENM", url: "#workflowmanager"}]
	        
	        for(i=1; i < newBreadcrumb.length; i++){
	        	crumb.push(newBreadcrumb[i]);
	        }	       
	             
	        var breadcrumb = 
	        		{name: 'Workflow Manager', url: '#workflowmanager', children: [
	        		{name: 'All Workflow Instances', url: '#workflowmanager/allworkflowinstances'},
	        		{name: 'Active Tasks', url: '#workflowmanager/activetasks'},
	        		{name: 'Workflow History', url: '#workflowmanager/workflowhistory'}
	        		]};
	        
	        crumb.splice(1, 0, breadcrumb);
         	
	       	 this.layout = new TopSection({
	       	    context: this.getContext(),
	       	    title: 'Workflow Instances',
	       	    breadcrumb: crumb,
	       	   defaultActions: [{
	                 type: "button",
	                 color: 'blue',
	                 name: 'Create New Instance',
	                 link: '#',
	                 action: function(){
	                	parent.wfInstances.detach();
	                 	var model = new mvp.Model(parent.params);
	                 	parent.showCreateInstanceForm(model);
	                 }
	                 },{
	                	 type: 'separator'  
	                 },{
	              	   type: "link",
	              	   color: 'blue',
	              	   name: 'All Workflow Instances',
	              	   link: '#',
	              	   action: function(){
	              		   	var locationController = new LocationController();
	                     		var url = 'workflowmanager/allworkflowinstances';
	                     		locationController.setLocation(url , false);
	              	   }
	              },{
	              	type: 'separator'  
	          	   },{		   
	              	   type: "link",
	              	   color: 'blue',
	              	   name: 'All Active Tasks',
	              	   link: '#',
	              	   action: function(){
	              		   	var locationController = new LocationController();
	                     		var url = 'workflowmanager/activetasks';
	                     		locationController.setLocation(url , false);
	              	   }
		          	 },{
	                    	type: 'separator'  
	             	   },{		   
	                 	   type: "link",
	                 	   color: 'blue',
	                 	   name: 'Workflow History',
	                 	   link: '#',
	                 	   action: function(){
	                 		   	var locationController = new LocationController();
	                        		var url = 'workflowmanager/workflowhistory';
	                        		locationController.setLocation(url , false);
	                 	   }
	             }]
	       	}); 
	       	this.layout.attachTo(this.getElement());
	       	this.layout.setContent(new SlidingPanels({
	       		context: this.getContext(),
	       		main: {label: 'Main Body', contents: parent.wfInstances = new WfInstances({
	       			showingProgress: true,
	       			showingDiagram: true, 
	       			filterValueActive: true,
	       			hidePercentage : false,
	       			regionContext: this.getContext()
	       		})},
	       		
	       		left: {label: "Overview",expanded: true, contents: new InstancesCounters({
	       			context: this.getContext()
	       		})}
	       	}));
         	
        }, 
               
        hideTable: function() {
            if (this.WfDefinitionsTable != null) {
                this.tableContentHeight = this.view.getTableContent().getStyle("height");
                this.WfDefinitionsTable.destroy();
                this.WfDefinitionsTable = null;
                this.view.getTableContent().setStyle("height", "0px");
            }
        },
        
        showTable: function() {
            this.hideTable();            
            
            this.wfInstancesTable = new WfInstancesTable({regionEventBus: this.regionEventBus2, showingProgress: true, showingDiagram: true, filterValueActive: true});
         	this.wfInstances.attachTo(this.view.getWfInstancesContent());
            if (this.tableContentHeight) {
                this.view.getWfInstancesContent().setStyle("height", this.tableContentHeight);
            }

            // Add handler for 'create instance' table action
            this.wfInstancesTable.getEventBus().subscribe("createinstance", function(wfDefinitionModel) {
                this.createInstance(wfDefinitionModel);
            }.bind(this));

            // Add handler for 'show diagram' table action
            this.wfInstancesTable.getEventBus().subscribe("showdiagram", function(wfDefinitionModel) {
                this.bpmnDiagram = new WfDiagram({  xid: "def-",
                                                    wfDefinitionId: wfDefinitionModel.getAttribute("id"),
                                                    wfName: wfDefinitionModel.getAttribute("name") });
                this.regionEventBus.publish("display-element", ["DefinitionDiagram", this.bpmnDiagram]);
            }.bind(this));
        },
        
        showCreateInstanceForm: function(wfDefinitionModel) {
            this.hideCreateInstanceForm();
            // Build and show 'create instance' form
            this.CreateWfInstanceForm = new CreateWfInstanceForm({minHeight: this.tableContentHeight, wfDefinitionModel:wfDefinitionModel, createHandledCallback: this.createHandledCallback.bind(this), regionContext: this.getContext()});
            this.CreateWfInstanceForm.attachTo(this.view.getWfInstancesContent());
        },

        hideCreateInstanceForm: function() {
            if (this.CreateWfInstanceForm != null) {
                this.CreateWfInstanceForm.destroy();
            }
            this.CreateWfInstanceForm = null;
        },
        
        createInstance: function(wfDefinitionModel) {
            this.hideTable();
            this.showCreateInstanceForm(wfDefinitionModel);
            this.showTable();
        },

        createHandledCallback: function(somethingChanged) {
            this.hideCreateInstanceForm();
            if(this.bpmnDiagram) this.bpmnDiagram.destroy();
            this.showTable();
        }
    });
});