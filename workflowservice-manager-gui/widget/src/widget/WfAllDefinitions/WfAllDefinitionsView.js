define([
    "jscore/core",
    "text!./WfAllDefinitions.html",
    "styles!./WfAllDefinitions.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getFilterLink: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-SectionHeading-Filter-link");
            },

            getTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-contentTable");
            },

            getFormContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-contentForm");
            },

            getRefreshButton: function(){
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonRefresh");
            }
        });

    });