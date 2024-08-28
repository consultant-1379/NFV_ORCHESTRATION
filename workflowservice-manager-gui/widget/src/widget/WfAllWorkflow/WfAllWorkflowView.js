define([
    "jscore/core",
    "text!./WfAllWorkflow.html",
    "styles!./WfAllWorkflow.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllWorkflow-contentTable");
            },

            getPagination: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllWorkflow-contentTable");
            },
            
            getTaskFormContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllWorkflow-contentTaskForm");
            },

            getRefreshButton: function(){
                return this.getElement().find(".eaNFE_automation_UI-WfAllWorkflow-contentTable-buttonRefresh");
            }
        });

    });