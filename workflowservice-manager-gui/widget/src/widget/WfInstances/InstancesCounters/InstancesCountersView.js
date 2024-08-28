define([
    'jscore/core',
    'text!./InstancesCounters.html',
    'styles!./InstancesCounters.less'
], function (core, template, style) {

        return core.View.extend({

        	getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },
            
            getBreadcrumb: function(){
            	return this.getElement().find(".eaWorkflowsInstances-InstancesCounters-Breadcrumb");
            },

            getWfInstancesContent: function() {
                return this.getElement().find(".eaWorkflowsInstances-InstancesCounters-contentWfInstances-home");
            },
            
            getCreateInstanceButton: function(){
            	return this.getElement().find(".eaWorkflowsInstances-InstancesCounters-Panel-iconHolder-CreateInstancesButtonHolder");
            },
            
            getWorkflowInstanceTotal: function(){
            	return this.getElement().find(".eaWorkflowsInstances-InstancesCounters-WorkflowInstances");
            },
            
            getWorkflowInstanceActive: function(){
            	return this.getElement().find(".eaWorkflowsInstances-InstancesCounters-ActiveInstancesIcon");
            },
            
            getWorkflowInstanceCancelled: function(){
            	return this.getElement().find(".eaWorkflowsInstances-InstancesCounters-CancelledInstancesIcon");
            },
            
            addAllInstancesClickHandler: function(fn){
            	this.getElement().find(".eaWorkflowsInstances-InstancesCounters-WorkflowInstances").addEventHandler("click", fn);
            },
            
            addActiveInstancesClickHandler: function(fn){
            	this.getElement().find(".eaWorkflowsInstances-InstancesCounters-ActiveInstances").addEventHandler("click", fn);
            },
            
            addSuspendedInstancesClickHandler: function(fn){
            	this.getElement().find(".eaWorkflowsInstances-InstancesCounters-CancelledInstances").addEventHandler("click", fn);
            }
            
            
        });

    });
