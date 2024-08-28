define([
    'jscore/core',
    './WfAllWorkflowCountersView',
    "3pps/wfs/WfCountsClient",
    'widget/WfCounter/WfCounter',
    "jscore/ext/locationController"
], function (core ,View , WfCountsClient, WfCounter,LocationController) {   	
    	return core.Widget.extend({

        View: View,
        
	    onViewReady: function(){
	        	
	        this.eventBus = this.context.eventBus;
	        	        	
	    	this.wfTasksTotal = new WfCounter();
	    	this.wfTasksActive = new WfCounter();
	    	this.wfTasksCancelled = new WfCounter();
	    	this.wfTasksTotal.setCount("WfInstance");
	    	this.wfTasksTotal.attachTo(this.view.getTasksTotal());
	    	this.wfTasksActive.setCount("WfInstanceActive");
	    	this.wfTasksActive.attachTo(this.view.getTasksActive());
	    	this.wfTasksCancelled.setCount("WfInstanceCancelled");
	    	this.wfTasksCancelled.attachTo(this.view.getTasksCancelled());
	      	
		    this.view.addAllTasksClickHandler(function(){
		    	this.eventBus.publish("viewAllWorkflows", "viewAllWorkflows");
		    }.bind(this));
		    
		    this.view.addActiveTaskClickHandler(function(){
		    	this.eventBus.publish("viewActiveWorkflows", "viewActiveWorkflows");
		    }.bind(this));
		
		    this.view.addSuspendedTaskClickHandler(function(){
		    	this.eventBus.publish("viewSuspendedWorkflows", "viewSuspendedWorkflows");
		    }.bind(this));
		    
		    this.eventBus.subscribe("refreshCounters", function(){
		    	this.refreshCounters();
		    }.bind(this));
	
        },
        
        refreshCounters: function(){
        	//console.log("all wf refresh");
        	this.wfTasksTotal.detach();
        	this.wfTasksTotal.setCount("WfInstance");
        	this.wfTasksTotal.attachTo(this.view.getTasksTotal());
         	
        	this.wfTasksActive.detach();
        	this.wfTasksActive.setCount("WfInstanceActive");
        	this.wfTasksActive.attachTo(this.view.getTasksActive());
         	
        	this.wfTasksCancelled.detach();
        	this.wfTasksCancelled.setCount("WfInstanceCancelled");
        	this.wfTasksCancelled.attachTo(this.view.getTasksCancelled());
        }
    });

});