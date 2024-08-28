define([
    "jscore/core",
    "text!./WfProgressDiagram.html",
    "styles!./WfProgressDiagram.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getBackButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-WfProgressDiagram-buttonBack");
            },

            getCalledContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfProgressDiagram-contentCalled");
            },

            getDiagramContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfProgressDiagram-contentDiagram");
            }
        });
    });