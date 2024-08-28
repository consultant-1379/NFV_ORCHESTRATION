define([
    'jscore/core',
    'text!./WfCountersSide.html',
    'styles!./WfCountersSide.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },
            
            getWfDefinitionContent: function() {
                return this.getElement().find(".eaWorkflowManager-WfCountersSide-home");
            },
            
            setHeader: function(header){
            	this.getElement().find(".eaWorkflowManager-WfCountersSide-Heading").element.innerHTML = header;
            },
            
            getWfWorkflowDefinitionCount: function(){
            	return this.getElement().find(".eaWorkflowManager-WfCountersSide-WorkflowOverviewSection-WorkflowDefinitions");
            },
            
            getWfWorkflowInstancesCount: function(){
            	return this.getElement().find(".eaWorkflowManager-WfCountersSide-WorkflowOverviewSection-WorkflowInstance");
            },
            
            getWfActiveTasksCount: function(){
            	return this.getElement().find(".eaWorkflowManager-WfCountersSide-WorkflowOverviewSection-ActiveTasks");
            },
            
            addAllWorkflowClickHandler: function(fn) {
            	this.getElement().find(".eaWorkflowManager-WfCountersSide-WorkflowOverviewSection-WorkflowInstance").addEventHandler("click", fn);
            },
            
            addActiveTaskClickHandler: function(fn) {
            	this.getElement().find(".eaWorkflowManager-WfCountersSide-WorkflowOverviewSection-ActiveTasks").addEventHandler("click", fn);
            }
            
        });

});