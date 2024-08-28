define([
    'jscore/core',
    "jscore/ext/mvp",
    './AllWorkflowInstancesView',
    'widget/WfAllWorkflow/WfAllWorkflow',
    'widget/WfAllDefinitions/WfAllDefinitions',
    'layouts/SlidingPanels',
    "layouts/TopSection",
    "jscore/ext/locationController",
    'widget/WfAllWorkflow/WfAllWorkflowCounters/WfAllWorkflowCounters'
], function (core, mvp, View, WfAllWorkflow, WfAllDefinitions, SlidingPanels, TopSection, LocationController, WfAllWorkflowCounters) {   	
    	return core.App.extend({

        View: View,
        
        onAttach: function() {
        	if(this.wfAllWorkflow){
           	 this.wfAllWorkflow.update();   	 
            }  
        	if(this.WfAllDefinitions){
        		this.WfAllDefinitions.hideCreateInstanceForm();
        		this.WfAllDefinitions.detach();
        		this.wfAllWorkflow.attach();
        	}        	
		},    

        onStart: function () {
        	
        	this.filter = "all";
        	
        	var newBreadcrumb = this.options.breadcrumb;
	        
	        var crumb = [{name: "ENM", url: "#workflowmanager"}]
	        
	        for(i=1; i < newBreadcrumb.length; i++){
	        	crumb.push(newBreadcrumb[i]);
	        }	
        	
        	var parent = this;
        	this.layout = new TopSection({
         	    context: this.getContext(),
         	    title: 'All Workflow Instances',
//         	    breadcrumb: this.options.breadcrumb,
         	    breadcrumb: crumb,
         	   defaultActions: [{
                   type: "button",
                   color: 'blue',
                   name: 'Create New Instance',
                   link: '#',
                   action: function(){
                	   parent.wfAllWorkflow.detach();
                	   if(!parent.WfAllDefinitions){
                		   parent.WfAllDefinitions = new WfAllDefinitions({
                			   regionContext: parent.getContext(),
                			   showActionButtons: false
                			   });
                		   parent.WfAllDefinitions.attachTo(parent.view.getWfInstancesContent()); 	
                 		}
                   }
         	   },{
         		 type: 'separator'  
         	  /* },{
                	   type: "link",
                	   color: 'blue',
                	   name: 'All Workflow Instances',
                	   link: '#',
                	   action: function(){
                		   	var locationController = new LocationController();
                       		var url = 'workflowmanager/allworkflowinstances';
                       		locationController.setLocation(url , false);
                	   }
                },{*/
                	/*type: 'separator'  */
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
         		main: {label: 'Main Body', contents: parent.wfAllWorkflow = new WfAllWorkflow({
         			regionContext: this.getContext(),
         			showingProgress: true,
         			showingDiagram: true,
         			filter: this.filter
         		})},
         		
         		left: {label: "Overview", expanded: true, contents: new WfAllWorkflowCounters({
         			context: this.getContext()
         		})}
         	}));
         	
         	this.getContext().eventBus.subscribe("removeDefinitionTable", function(removeTable){
         		this.WfAllDefinitions.detach();
                this.WfAllDefinitions.destroy();
                this.WfAllDefinitions = null;
                this.filter = "all";
         		this.wfAllWorkflow.showTable(this.filter);
         		this.wfAllWorkflow.attach();
            }.bind(this));        	

         	
         	this.getContext().eventBus.subscribe("viewAllWorkflows", function(){
    	    	this.wfAllWorkflow.showTable("all");
         	}.bind(this));
         	
         	this.getContext().eventBus.subscribe("viewActiveWorkflows", function(){
    	    	this.wfAllWorkflow.showTable("active");
         	}.bind(this));
         	
         	this.getContext().eventBus.subscribe("viewSuspendedWorkflows", function(){
    	    	this.wfAllWorkflow.showTable("suspended");
         	}.bind(this));
         	
        }
        
    });

});
