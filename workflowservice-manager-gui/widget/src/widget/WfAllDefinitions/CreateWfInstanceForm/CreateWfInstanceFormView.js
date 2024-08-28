define([
    "jscore/core",
    "template!./CreateWfInstanceForm.html",
    "styles!./CreateWfInstanceForm.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template(this.options);
            },

            getStyle: function() {
                return style;
            },

            getFormContent: function () {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-CreateWfInstanceForm-FormContent");
            },
            
            getSubmitButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-CreateWfInstanceForm-Buttons-buttonSubmit");
            },
            
            getCancelButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-CreateWfInstanceForm-Buttons-buttonCancel");
            }
            
        });

    });