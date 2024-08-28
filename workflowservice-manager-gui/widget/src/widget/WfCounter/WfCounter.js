define([
    'jscore/core',
    './WfCounterView',
    "3pps/wfs/WfCountsClient",
], function (core ,View , WfCountsClient) {   	
    	return core.Widget.extend({

        View: View,
        
        setCount: function(type){
        	
        	if(type === "WfDefinition"){
	        	WfCountsClient.getDefinitionCount({
	         		latest: true,
	                success: function(count) { 	
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	        	this.view.setCountLabel("Workflow</br>Definitions");
        	}
        	else if(type === "WfInstances"){
	        	WfCountsClient.getWfWorkflowInstancesCount({
	         		active : true,
	         		sortBy: 'instanceId',
	         		sortOrder : 'asc',
	                success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	        	this.view.setCountLabel("Workflow</br>Instances");
        	}
        	else if(type === "WfActiveTasks"){
	         	WfCountsClient.getWfActiveTasksCount({
	         		active : true,
	         		sortBy: 'instanceId',
	         		sortOrder : 'asc',
	                success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	         	this.view.setCountLabel("Active</br>Tasks");
        	}
        	else if(type === "WfInstance"){
	        	WfCountsClient.getWorkflowInstanceTotal({
	            	sortBy: 'instanceId',
	         		sortOrder : 'asc',
	                success: function(count) {               	
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	        	this.view.setCountLabel("Workflow</br>Instances");
        	}
        	else if(type === "WfInstanceActive"){            
	            WfCountsClient.getWorkflowInstanceActive({
	            	active: true,
	            	suspended: false,
	                success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	            this.view.setCountLabel("Active</br>Instances");
        	}
        	else if(type === "WfInstanceCancelled"){
	            WfCountsClient.getWorkflowInstanceCancelled({
	            	active: false,
	            	suspended: true,
	                success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	            this.view.setCountLabel("Suspended</br>Instances");
        	}
        	else if(type === "WfTasksTotal"){
	        	WfCountsClient.getTasksTotal({
	                success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	        	this.view.setCountLabel("Total</br>Tasks");
        	}
         	
        	else if(type === "WfTasksActive"){
	         	 WfCountsClient.getTasksActive({
	         		 active: true,
	            	 suspended: false,
	                 success: function(count) {
	                	 this.view.setCount(count);
		                	this.resize();
	                 }.bind(this)
	             });
	         	this.view.setCountLabel("Active</br>Tasks");
        	}
        	else if(type === "WfTasksCancelled"){
	         	WfCountsClient.getTasksCancelled({
	         		active: false,
	         		suspended : true,
	                success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                }.bind(this)
	            });
	         	this.view.setCountLabel("Suspended</br>Tasks");
        	}
        },
        
        setCountWithPid: function(type, proccessId){
        	if(type === "WfInstance"){
	        	WfCountsClient.getWorkflowInstanceTotal({
	             	processDefinitionId : proccessId,
	                 success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                 }.bind(this)
	             });
	        	this.view.setCountLabel("Workflow</br>Instances");
        	}
        	else if(type === "WfInstanceActive"){
	             WfCountsClient.getWorkflowInstanceActive({
	             	active: true,
	             	suspended: false,
	             	processDefinitionId : proccessId,
	                 success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                 }.bind(this)
	             });
	             this.view.setCountLabel("Active</br>Instances");
        	}
             else if(type === "WfInstanceCancelled"){
	             WfCountsClient.getWorkflowInstanceCancelled({
	             	active: false,
	             	suspended: true,
	             	processDefinitionId : proccessId,
	                 success: function(count) {
	                	this.view.setCount(count);
	                	this.resize();
	                 }.bind(this)
	             });
	             this.view.setCountLabel("Suspended</br>Instances");
             }
        },
        
        resize: function(){
        	if(parseInt(this.view.getCount()) > 999){
        		this.view.setFont(3.5);
        	}
        	else if(parseInt(this.view.getCount()) > 99){
        		this.view.setFont(5);
        	}      	
        }    	
    });

});