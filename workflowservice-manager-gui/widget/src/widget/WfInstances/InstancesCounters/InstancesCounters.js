define([
    'jscore/core',
    './InstancesCountersView',
    "3pps/wfs/WfCountsClient",
    'widget/WfCounter/WfCounter',
    "jscore/ext/locationController"
], function (core ,View , WfCountsClient, WfCounter,LocationController) {   	
    	return core.Widget.extend({

        View: View,
        
        onViewReady: function(){
        	
        	this.eventBus = this.context.eventBus;
	        this.proccessId = this.getProcessDefinitionId();
	        	
	      	this.wfWorkflowInstanceTotal = new WfCounter();
	      	this.wfWorkflowInstanceTotal.setCountWithPid("WfInstance", this.proccessId);
	      	this.wfWorkflowInstanceTotal.attachTo(this.view.getWorkflowInstanceTotal());
	
	      	this.wfWorkflowInstanceActive = new WfCounter();
	      	this.wfWorkflowInstanceActive.setCountWithPid("WfInstanceActive", this.proccessId);
	      	this.wfWorkflowInstanceActive.attachTo(this.view.getWorkflowInstanceActive());
	     	
	      	this.wfWorkflowInstanceCancelled = new WfCounter();
	      	this.wfWorkflowInstanceCancelled.setCountWithPid("WfInstanceCancelled", this.proccessId);
	      	this.wfWorkflowInstanceCancelled.attachTo(this.view.getWorkflowInstanceCancelled());
         	
//	      	this.view.addAllInstancesClickHandler(function(){
//	 	    	this.eventBus.publish("viewAllInstance", "viewAllInstance");
//	 	    }.bind(this));
//	 	    
//	 	    this.view.addActiveInstancesClickHandler(function(){
//	 	    	this.eventBus.publish("viewActiveInstance", "viewActiveInstance");
//	 	    }.bind(this));
//	 	
//	 	    this.view.addSuspendedInstancesClickHandler(function(){
//	 	    	this.eventBus.publish("viewSuspendedInstance", "viewSuspendedInstance");
//	 	    }.bind(this));
	      	
	      	this.eventBus.subscribe("refreshCounters", function(){
		    	this.refreshCounters();
		    }.bind(this));
	 	    
        },
        
        getProcessDefinitionId: function() {
	    	var url = window.location.hash;
	        var arguments = url.substring(url.indexOf('/') + 1).split('/');
	        return arguments[0];
	     },
	     
	     
	        refreshCounters: function(){
	        	
	        	this.wfWorkflowInstanceTotal.detach();
	     		this.wfWorkflowInstanceTotal.setCountWithPid("WfInstance", this.proccessId);
	     		this.wfWorkflowInstanceTotal.attachTo(this.view.getWorkflowInstanceTotal());
	         	
	     		this.wfWorkflowInstanceActive.detach();
	     		this.wfWorkflowInstanceActive.setCountWithPid("WfInstanceActive", this.proccessId);
	     		this.wfWorkflowInstanceActive.attachTo(this.view.getWorkflowInstanceActive());
	         	
	     		this.wfWorkflowInstanceCancelled.detach();
	     		this.wfWorkflowInstanceCancelled.setCountWithPid("WfInstanceCancelled", this.proccessId);
	     		this.wfWorkflowInstanceCancelled.attachTo(this.view.getWorkflowInstanceCancelled());
	        }, 
    });

});