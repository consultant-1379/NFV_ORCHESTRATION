define([
    'jscore/core',
    'text!./workflowInstanceN.html',
    'styles!./workflowInstanceN.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },  

            getStyle: function() {
                return style;
            },

            getBreadcrumb: function(){
            	return this.getElement().find(".eaWorkflowInstanceN-MainRegion-Breadcrumb");
            },
            
            getWfInstanceNContent: function() {
                return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-home");
            },
            
//            getContentDiagram: function(){
//            	return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-contentDiagram");
//            },
            
            getContentDiagram: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfComment");
            },
            
            getBackButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-WfDetails-buttonBack");
            },
            
            getWfInstanceNProgress: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDetails-progress");
            },
            
//            getExecuteButton: function() {
//                return this.getElement().find(".eaNFE_automation_UI-TaskTable-TaskActionsCell-buttonExecute");
//            },
            
            getBarCell: function(){
            	return this.getElement().find(".eaWorkflowHistory-MainRegion-contentWfInstanceN-home-StatusBar");
            },
            
            getTitle1: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-Title1");
			},
			
			getTitle2: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-Title2");
			},
			
			getPercentage: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-home-Percentage");
			},
			
			getStartTime: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-home-Started");
				
			},
			
			getBarPercentageCell: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarCell-contentTable-BarCell");
            },
			
			getUserTaskFormContent: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-home-UserTaskForm");
			},
			
			getUserActivityContent: function() {
				return this.getElement().find(".elLayouts-SlidingPanels-rightContents");
			},
			
			getUserActivityContent2: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-home-UserActivity2");
			},
			
			getRefreshButton: function(){
                return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-buttonRefresh");
            },
            
            addRefreshClickHandler: function(fn){
            	this.getElement().find(".eaNFE_automation_UI-WfDetails-buttonRefresh").addEventHandler("click", fn);
            },
            
            getContentDetails: function(){
            	return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-contentDetails");
            }
			
        });

    });