define([
    "jscore/core",
    "template!./WfComment.html",
    "styles!./WfComment.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template(this.options);
            },

            getStyle: function() {
                return style;
            },
            
            getComment: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfComment-CreateWfComment-textarea");
            },
            
            getComments: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfComment-comments");
            },
         
            getSubmitButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-WfComment-CreateWfComment-buttonSubmit");
            },
            
            getCloseButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-WfComment-closeButton");
            },
            
            getAddCommentSection: function(){
            	return this.getElement().find(".eaNFE_automation_UI-WfComment-CreateWfComment-buttonHolder");
            }
  
        });

    });