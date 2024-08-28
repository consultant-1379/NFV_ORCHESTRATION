define([
    "jscore/core",
    "text!./WfDefinitionActionsCell.html",
    "styles!./WfDefinitionActionsCell.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getExecuteButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable-buttonExecute");
            },

            getDiagramButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable-buttonDiagram");
            }
        });
    });