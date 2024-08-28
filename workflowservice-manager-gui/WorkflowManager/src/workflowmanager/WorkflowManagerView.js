define([
    'jscore/core',
    'text!./workflowManager.html',
    'styles!./workflowManager.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },

            getBreadcrumb: function(){
            	return this.getElement().find(".eaWorkflowManager-MainRegion-Breadcrumb");
            },
            
            getWfDefinitionContent: function() {
                return this.getElement().find(".eaWorkflowManager-MainRegion-contentWfDefinition-home");
            },
            
            getWfWorkflowDefinitionCount: function(){
            	return this.getElement().find(".eaWorkflowManager-MainRegion-WorkflowOverviewSection-WorkflowDefinitions");
            },
            
            getWfWorkflowInstancesCount: function(){
            	return this.getElement().find(".eaWorkflowManager-MainRegion-WorkflowOverviewSection-WorkflowInstance");
            },
            
            getWfActiveTasksCount: function(){
            	return this.getElement().find(".eaWorkflowManager-MainRegion-WorkflowOverviewSection-ActiveTasks");
            },
            
            addAllWorkflowClickHandler: function(fn) {
            	this.getElement().find(".eaWorkflowManager-MainRegion-WorkflowOverviewSection-WorkflowInstance").addEventHandler("click", fn);
            	this.getElement().find(".eaWorkflowManager-MainRegion-WorkflowOverviewSection-WorkflowInstanceName").addEventHandler("click", fn);
            },
            
            addActiveTaskClickHandler: function(fn) {
            	this.getElement().find(".eaWorkflowManager-MainRegion-WorkflowOverviewSection-ActiveTasks").addEventHandler("click", fn);
            	this.getElement().find(".eaWorkflowManager-MainRegion-WorkflowOverviewSection-ActiveTasksName").addEventHandler("click", fn);
            },
            
            getActionBar: function(){
            	return this.getElement().find(".eaWorkflowManager-MainRegion-Panel-iconHolder");
            }

        });

    });

