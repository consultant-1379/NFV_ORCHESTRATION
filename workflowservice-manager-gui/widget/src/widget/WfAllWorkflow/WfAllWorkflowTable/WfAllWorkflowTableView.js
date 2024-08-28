define([
    "jscore/core",
    "text!./WfAllWorkflowTable.html",
    "styles!./WfAllWorkflowTable.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },  

            getTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllWorkflowTable-contentTable");
            },

            getPagination: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllWorkflowTable-contentTable-Pagination");
            }
            
           
        });

    });