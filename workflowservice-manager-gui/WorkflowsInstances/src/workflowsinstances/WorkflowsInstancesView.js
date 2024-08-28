define([
    'jscore/core',
    'text!./workflowsInstances.html',
    'styles!./workflowsInstances.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },
            
            getBreadcrumb: function(){
            	return this.getElement().find(".eaWorkflowsInstances-MainRegion-Breadcrumb");
            },

            getWfInstancesContent: function() {
                return this.getElement().find(".elLayouts-SlidingPanels-center");
            },
            
            getCreateInstanceButton: function(){
            	return this.getElement().find(".eaWorkflowsInstances-MainRegion-Panel-iconHolder-CreateInstancesButtonHolder");
            },
            
            getWorkflowInstanceTotal: function(){
            	return this.getElement().find(".eaWorkflowsInstances-MainRegion-WorkflowInstances");
            },
            
            getWorkflowInstanceActive: function(){
            	return this.getElement().find(".eaWorkflowsInstances-MainRegion-ActiveInstancesIcon");
            },
            
//            getWorkflowInstanceInactive: function(){
//            	return this.getElement().find(".eaWorkflowsInstances-MainRegion-InactiveInstancesIcon");
//            },
            
            getWorkflowInstanceCancelled: function(){
            	return this.getElement().find(".eaWorkflowsInstances-MainRegion-CancelledInstancesIcon");
            },
            
            getPageTitle: function(){
            	return this.getElement().find(".eaWorkflowsInstances-MainRegion-Title");
            },
            
            getPercentageIndicator: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarPercentageCell-contentTable-PercentageIndicator");
            },
            
            getActiveTasks: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarPercentageCell-contentTable-PercentageIndicator");
            }

            
        });

    });

