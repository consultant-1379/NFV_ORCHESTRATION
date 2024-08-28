define([
    "jscore/core",
    "text!./WfAllDefinitionActionsCell.html",
    "styles!./WfAllDefinitionActionsCell.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getExecuteButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonExecute");
            },

            getDiagramButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfAllDefinitions-contentTable-buttonDiagram");
            }

        });

    });