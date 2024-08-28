define([
    "jscore/core",
    "text!./WfUsertasks.html",
    "styles!./WfUsertasks.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getFilter: function() {
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-SectionHeading-Filter");
            },

            getTaskTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTaskTable");
            },
            
            getDiagramContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentDiagram");
            },

            getTaskFormContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTaskForm");
            },

            getTaskTablePagination: function() {
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTaskTablePagination");
            },
            
            getUsertasksToggle: function(){
                return this.getElement().find(".eaNFE_automation_UI-Usertasks");
            },

            getProgressContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentProgress");
            },
            getRefreshButton: function(){
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTable-buttonRefresh");
            },
            
            getDiagramName: function(){
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-handleBarButton-diagramName");
            },
            
            getCloseDiagram: function(){
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-handleBarButton-closeDiagram");
            }
        });

    });
