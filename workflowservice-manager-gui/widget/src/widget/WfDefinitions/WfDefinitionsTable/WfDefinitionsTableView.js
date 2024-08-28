define([
    "jscore/core",
    "text!./WfDefinitionsTable.html",
    "styles!./WfDefinitionsTable.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },  

            getTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitionsTable-contentTable");
            },

            getPagination: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitionsTable-contentTable-Pagination");
            },
            
            getDiagramButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable-buttonDiagram");
            },
            
            getRefreshButton: function(){
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitionsTable-contentTable-buttonRefresh");
            },
            
            getHeadingContent: function(){
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitionsTable-SectionHeading");
            }                       
        });

    });

