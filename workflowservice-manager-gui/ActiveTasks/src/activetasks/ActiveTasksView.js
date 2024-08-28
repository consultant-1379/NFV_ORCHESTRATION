define([
    'jscore/core',
    'text!./activeTasks.html',
    'styles!./activeTasks.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },
            
            getBreadcrumb: function(){
            	return this.getElement().find(".eaActiveTasks-MainRegion-Breadcrumb");
            },

            getWfUsertasksContent: function() {
                return this.getElement().find(".eaActiveTasks-MainRegion-contentWfInstances-home");
            },
            
            getTasksTotal: function() {
                return this.getElement().find(".eaActiveTasks-MainRegion-TotalTasks"); 
            },
            
            getTasksActive: function() {
              return this.getElement().find(".eaActiveTasks-MainRegion-ActiveInstancesIcon"); 
            },
            
//            getTasksInactive: function() {
//                return this.getElement().find(".eaActiveTasks-MainRegion-InactiveInstancesIcon"); 
//            },
            
            getTasksCancelled: function(){
            	return this.getElement().find(".eaActiveTasks-MainRegion-CancelledInstancesIcon");
            },
            
            addAllTasksClickHandler: function(fn) {
            	this.getElement().find(".eaActiveTasks-MainRegion-TotalTasks").addEventHandler("click", fn);
            	//this.getElement().find(".eaActiveTasks-MainRegion-TotalTasksDivider").addEventHandler("click", fn);	
            },
            
            addActiveTaskClickHandler: function(fn) {
            	//this.getElement().find(".eaActiveTasks-MainRegion-ActiveInstancesName").addEventHandler("click", fn);
            	this.getElement().find(".eaActiveTasks-MainRegion-ActiveInstances").addEventHandler("click", fn);
            },
            
            addSuspendedTaskClickHandler: function(fn) {
            	//this.getElement().find(".eaActiveTasks-MainRegion-CancelledInstancesName").addEventHandler("click", fn);
            	this.getElement().find(".eaActiveTasks-MainRegion-CancelledInstances").addEventHandler("click", fn);
            }

        });

    });

