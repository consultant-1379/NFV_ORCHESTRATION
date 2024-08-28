define([
    'jscore/core',
    'text!./allWorkflowInstances.html',
    'styles!./allWorkflowInstances.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },
            
            getBreadcrumb: function(){
            	return this.getElement().find(".eaAllWorkflowInstances-MainRegion-Breadcrumb");
            },

            getWfInstancesContent: function() {
                return this.getElement().find(".elLayouts-SlidingPanels-center");
            },
            
            getCreateInstanceButton: function(){
            	return this.getElement().find(".eaAllWorkflowInstances-MainRegion-Panel-iconHolder-CreateInstancesButtonHolder");
            },
            
            getWorkflowInstanceTotal: function(){
            	return this.getElement().find(".eaAllWorkflowInstances-MainRegion-WorkflowInstances");
            },
            
            getWorkflowInstanceActive: function(){
            	return this.getElement().find(".eaAllWorkflowInstances-MainRegion-ActiveInstancesIcon");
            },
            
//            getWorkflowInstanceInactive: function(){
//            	return this.getElement().find(".eaAllWorkflowInstances-MainRegion-InactiveInstancesIcon");
//            },
            
            getWorkflowInstanceCancelled: function(){
            	return this.getElement().find(".eaAllWorkflowInstances-MainRegion-CancelledInstancesIcon");
            },
            
            addAllTaskClickHandler: function(fn) {
            	this.getElement().find(".eaAllWorkflowInstances-MainRegion-WorkflowInstances").addEventHandler("click", fn);
            	//this.getElement().find(".eaAllWorkflowInstances-MainRegion-WorkflowInstancesName").addEventHandler("click", fn);
            },
            
            addActiveTaskClickHandler: function(fn) {
            	this.getElement().find(".eaAllWorkflowInstances-MainRegion-ActiveInstances").addEventHandler("click", fn);
            	//this.getElement().find(".eaAllWorkflowInstances-MainRegion-ActiveInstancesName").addEventHandler("click", fn);
            },
            
            addSuspendedTaskClickHandler: function(fn) {
            	this.getElement().find(".eaAllWorkflowInstances-MainRegion-CancelledInstances").addEventHandler("click", fn);
            	//this.getElement().find(".eaAllWorkflowInstances-MainRegion-CancelledInstancesName").addEventHandler("click", fn);
            }

        });

    });

