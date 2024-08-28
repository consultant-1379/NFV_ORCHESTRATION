define([
    "jscore/core",
    "text!./WfHistory.html",
    "styles!./WfHistory.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfHistory-contentTable");
            },

            getRefreshButton: function(){
                return this.getElement().find(".eaNFE_automation_UI-WfHistory-contentTable-buttonRefresh");
            },
            
			getDiagramName: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfHistory-handleBarButton-diagramName");
            },
            
            getCloseDiagram: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfHistory-handleBarButton-closeDiagram");
            },
            
            getCloseLink: function(){
            	return this.getElement().find(".ebLayout-HeadingCommands");
            },
            
            getHeaderContent: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfHistory-handleBarButton");
            }
        });

    });