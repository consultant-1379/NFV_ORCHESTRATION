define([
    "jscore/core",
    "text!./TaskActionsCell.html",
    "styles!./TaskActionsCell.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getExecuteButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-TaskTable-TaskActionsCell-buttonExecute");
            },

            getDiagramButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-TaskTable-TaskActionsCell-buttonDiagram");
            }

        });

    });