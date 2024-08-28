define([
    'jscore/core',
    'text!./WfAllWorkflowCounters.html',
    'styles!./WfAllWorkflowCounters.less'
], function (core, template, style) {

        return core.View.extend({

        	 getTemplate: function() {
                 return template;
             }, 

             getStyle: function() {
                 return style;
             },
             
             getBreadcrumb: function(){
             	return this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-Breadcrumb");
             },

             getWfUsertasksContent: function() {
                 return this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-contentWfInstances-home");
             },
             
             getTasksTotal: function() {
                 return this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-TotalTasks"); 
             },
             
             getTasksActive: function() {
               return this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-ActiveInstancesIcon"); 
             },
             
//             getTasksInactive: function() {
//                 return this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-InactiveInstancesIcon"); 
//             },
             
             getTasksCancelled: function(){
             	return this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-CancelledInstancesIcon");
             },
             
             addAllTasksClickHandler: function(fn) {
             	this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-TotalTasks").addEventHandler("click", fn);
             	this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-TotalTasksDivider").addEventHandler("click", fn);	
             },
             
             addActiveTaskClickHandler: function(fn) {
             	//this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-ActiveInstancesName").addEventHandler("click", fn);
             	this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-ActiveInstances").addEventHandler("click", fn);
             },
             
             addSuspendedTaskClickHandler: function(fn) {
             	//this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-CancelledInstancesName").addEventHandler("click", fn);
             	this.getElement().find(".eaWorkflowManager-WfAllWorkflowCounters-CancelledInstances").addEventHandler("click", fn);
             }
            
        });

    });
