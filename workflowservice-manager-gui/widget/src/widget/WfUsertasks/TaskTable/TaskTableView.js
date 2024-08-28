define([
    "jscore/core",
    "text!./TaskTable.html",
    "styles!./TaskTable.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getTaskTableContent: function() {
                return this.getElement().find(".eaNFE_automation_TaskTable-contentTaskTable");
            },
            
            getHeadingContent: function(){
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-SectionHeading");
            }  
        });

    });