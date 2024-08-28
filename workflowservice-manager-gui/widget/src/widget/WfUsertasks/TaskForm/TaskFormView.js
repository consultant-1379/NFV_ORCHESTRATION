define([
    "jscore/core",
    "template!./TaskForm.html",
    "styles!./TaskForm.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template(this.options);
            },

            getStyle: function() {
                return style;
            },

            getFormContent: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskForm-Panel-FormContent");
            },
            
            getSubmitButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskForm-buttonSubmit");
            },
            
            getCancelButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskForm-buttonCancel");
            },

            getInfoPanel: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskForm-InfoPanel-Map");
            },
            
            getTaskNameHandleBar: function () {
                return this.getElement().find(".ebLayout-SectionSubheading");
            },
            
            getTaskFormContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-Usertasks-contentTaskForm");
            }
            
        });

    });