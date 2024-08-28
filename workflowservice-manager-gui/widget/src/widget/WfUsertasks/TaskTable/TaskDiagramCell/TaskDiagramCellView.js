define([
    "jscore/core",
    "text!./TaskDiagramCell.html",
    "styles!./TaskDiagramCell.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getExecuteButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-TaskTable-TaskDiagramCell-buttonExecute");
            },

            getDiagramButton: function() {
                return this.getElement().find(".eaNFE_automation_UI-TaskTable-TaskDiagramCell-buttonDiagram");
            }

        });

    });