define([
    'jscore/core',
    'text!./workflowHistory.html',
    'styles!./workflowHistory.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },

            getBreadcrumb: function(){
            	return this.getElement().find(".eaWorkflowHistory-MainRegion-Breadcrumb");
            },
            
            getWfHistoryContent: function() {
                return this.getElement().find(".eaWorkflowHistory-MainRegion-contentWfHistory-home");
            },
            
			getDiagramName: function() {
			    return this.getElement().find(".eaNFE_automation_UI-WfHistory-DiagramName");
			},
			
			getDiagramName: function() {
			    return this.getElement().find(".eaWorkflowHistory-MainRegion-contentWfHistory-diagramName");
			} 

        });

    });

