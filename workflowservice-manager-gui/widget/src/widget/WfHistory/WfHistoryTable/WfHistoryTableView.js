define([
    "jscore/core",
    "text!./WfHistoryTable.html",
    "styles!./WfHistoryTable.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },  

            getTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfHistoryTable-contentTable");
            },

            getPagination: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfHistoryTable-contentTable-Pagination");
            }
            
           
        });

    });