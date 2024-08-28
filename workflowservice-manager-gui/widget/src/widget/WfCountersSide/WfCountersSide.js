define([
    'jscore/core',
    './WfCountersSideView',
    "3pps/wfs/WfCountsClient",
    'widget/WfCounter/WfCounter',
    "jscore/ext/locationController"
], function (core ,View , WfCountsClient, WfCounter,LocationController) {   	
    	return core.Widget.extend({

        View: View,
        
        onViewReady: function(){
        	
        		this.view.setHeader("<h2>Workflows Overview</h2>");
            	this.wfWorkflowDefinitionCounter = new WfCounter();
             	this.wfWorkflowInstancesCounter = new WfCounter();
             	this.wfWfActiveTasksCounter = new WfCounter();       	
             	this.wfWorkflowDefinitionCounter.setCount("WfDefinition");
             	this.wfWorkflowDefinitionCounter.attachTo(this.view.getWfWorkflowDefinitionCount());         	
             	this.wfWorkflowInstancesCounter.setCount("WfInstances");
             	this.wfWorkflowInstancesCounter.attachTo(this.view.getWfWorkflowInstancesCount());
             	this.wfWfActiveTasksCounter.setCount("WfActiveTasks");
             	this.wfWfActiveTasksCounter.attachTo(this.view.getWfActiveTasksCount());
             	
             	this.view.addAllWorkflowClickHandler(function(){
             		var locationController = new LocationController();
                	var url = 'workflowmanager/allworkflowinstances';
                	locationController.setLocation(url , false);
             	}.bind(this));
             	
             	this.view.addActiveTaskClickHandler(function(){
             		var locationController = new LocationController();
                	var url = 'workflowmanager/activetasks';
                	locationController.setLocation(url , false);
             	}.bind(this));  
        	
        }        
    });

});