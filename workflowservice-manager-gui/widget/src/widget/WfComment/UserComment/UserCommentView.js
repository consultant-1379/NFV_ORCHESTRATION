define([
    "jscore/core",
    "template!./UserComment.html",
    "styles!./UserComment.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template(this.options);
            },

            getStyle: function() {
                return style;
            },
            
            getUserName: function(){
            	return this.getElement().find(".eaNFE_automation_UI-UserComment-username");
            },
            
            getTime: function(){
            	return this.getElement().find(".eaNFE_automation_UI-UserComment-time");
            },
            getComment: function(){
            	return this.getElement().find(".eaNFE_automation_UI-UserComment-comment");
            }
            
        });
});