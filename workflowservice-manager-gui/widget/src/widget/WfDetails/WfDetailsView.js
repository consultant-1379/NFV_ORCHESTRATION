define([
    "jscore/core",
    "text!./WfDetails.html",
    "styles!./WfDetails.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },
            
            addRefreshClickHandler: function(fn){
            	this.getElement().find(".eaNFE_automation_UI-WfDetails-buttonRefresh").addEventHandler("click", fn);
            },
            
            addZoomInClickHandler: function(fn){
            	this.getElement().find(".eaNFE_automation_UI-WfDetails-buttonZoomIn").addEventHandler("click", fn);
            },
            
            addZoomOutClickHandler: function(fn){
            	this.getElement().find(".eaNFE_automation_UI-WfDetails-buttonZoomOut").addEventHandler("click", fn);
            },
            
            getDetailsHeader: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfDetails-header");
            }
        });

    });