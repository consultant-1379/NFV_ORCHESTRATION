define([
    'jscore/core',
    'text!./ActiveCounters.html',
    'styles!./ActiveCounters.less'
], function (core, template, style) {

        return core.View.extend({

        	 getTemplate: function() {
                 return template;
             }, 

             getStyle: function() {
                 return style;
             },
             
             getBreadcrumb: function(){
             	return this.getElement().find(".eaWorkflowManager-ActiveCounters-Breadcrumb");
             },

             getWfUsertasksContent: function() {
                 return this.getElement().find(".eaWorkflowManager-ActiveCounters-contentWfInstances-home");
             },
             
             getTasksTotal: function() {
                 return this.getElement().find(".eaWorkflowManager-ActiveCounters-TotalTasks"); 
             },
             
             getTasksActive: function() {
               return this.getElement().find(".eaWorkflowManager-ActiveCounters-ActiveInstancesIcon"); 
             },
             
//             getTasksInactive: function() {
//                 return this.getElement().find(".eaWorkflowManager-ActiveCounters-InactiveInstancesIcon"); 
//             },
             
             getTasksCancelled: function(){
             	return this.getElement().find(".eaWorkflowManager-ActiveCounters-CancelledInstancesIcon");
             },
             
             addAllTasksClickHandler: function(fn) {
             	this.getElement().find(".eaWorkflowManager-ActiveCounters-TotalTasks").addEventHandler("click", fn);
             	this.getElement().find(".eaWorkflowManager-ActiveCounters-TotalTasksDivider").addEventHandler("click", fn);	
             },
             
             addActiveTaskClickHandler: function(fn) {
             	//this.getElement().find(".eaWorkflowManager-ActiveCounters-ActiveInstancesName").addEventHandler("click", fn);
             	this.getElement().find(".eaWorkflowManager-ActiveCounters-ActiveInstances").addEventHandler("click", fn);
             },
             
             addSuspendedTaskClickHandler: function(fn) {
             	//this.getElement().find(".eaWorkflowManager-ActiveCounters-CancelledInstancesName").addEventHandler("click", fn);
             	this.getElement().find(".eaWorkflowManager-ActiveCounters-CancelledInstances").addEventHandler("click", fn);
             }
            
        });

    });
