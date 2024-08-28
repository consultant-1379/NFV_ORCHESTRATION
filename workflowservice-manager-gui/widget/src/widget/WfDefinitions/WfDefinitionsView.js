define([
    "jscore/core",
    "text!./WfDefinitions.html",
    "styles!./WfDefinitions.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getTableContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable");
            },

            getFormContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentForm");
            },

//            getRefreshButton: function(){
//                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable-buttonRefresh");
//            },
//            
            getHandleButton: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-handleBarButton");
            },
            
            
            getHandleBar: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-handleBarButton-handleBar");
            },
            
            getCloseDiagram: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-handleBarButton-closeDiagram");
            },
            getCloseLink: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-closelink");
            },
            
            getHeadingContent: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-SectionHeading");
            },
            
            getBottomDivider: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfDefinitions-contentTable-BottomDivider");
            }
            
        });

    });