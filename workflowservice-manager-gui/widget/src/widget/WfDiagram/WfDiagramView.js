define([
    "jscore/core",
    "template!./WfDiagram.html",
    "styles!./WfDiagram.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template(this.options);
            },

            getStyle: function() {
                return style;
            },

            getDiagramContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDiagram-content");
            },
            
            getDraggableContent: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfDiagram-draggable");
            },
            
//            getSVG: function(){
//            	return this.getElement().find("svg");
//            }
        });

    });