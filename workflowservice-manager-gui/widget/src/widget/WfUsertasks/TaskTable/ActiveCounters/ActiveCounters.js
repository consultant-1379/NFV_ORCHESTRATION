define([
    'jscore/core',
    './ActiveCountersView',
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
	    	this.wfTasksTotal.setCount("WfTasksTotal");
	    	this.wfTasksTotal.attachTo(this.view.getTasksTotal());
	    	this.wfTasksActive.setCount("WfTasksActive");
	    	this.wfTasksActive.attachTo(this.view.getTasksActive());
	    	this.wfTasksCancelled.setCount("WfTasksCancelled");
	    	this.wfTasksCancelled.attachTo(this.view.getTasksCancelled());
	      	
		    this.view.addAllTasksClickHandler(function(){
		    	this.eventBus.publish("viewAllTasks", "viewAllTasks");
		    }.bind(this));
		    
		    this.view.addActiveTaskClickHandler(function(){
		    	this.eventBus.publish("viewActiveTasks", "viewActiveTasks");
		    }.bind(this));
		
		    this.view.addSuspendedTaskClickHandler(function(){
		    	this.eventBus.publish("viewSuspendedTasks", "viewSuspendedTasks");
		    }.bind(this));
		    
		    this.eventBus.subscribe("refreshCounters", function(){
		    	this.refreshCounters();
		    }.bind(this));
	
        },
        
        
        
		refreshCounters: function(){
		
			this.wfTasksTotal.detach();
    		this.wfTasksTotal.setCount("WfTasksTotal");
    		this.wfTasksTotal.attachTo(this.view.getTasksTotal());
         	
    		this.wfTasksActive.detach();
    		this.wfTasksActive.setCount("WfTasksActive");
    		this.wfTasksActive.attachTo(this.view.getTasksActive());
         	
    		this.wfTasksCancelled.detach();
    		this.wfTasksCancelled.setCount("WfTasksCancelled");
    		this.wfTasksCancelled.attachTo(this.view.getTasksCancelled());
		}
    });

});