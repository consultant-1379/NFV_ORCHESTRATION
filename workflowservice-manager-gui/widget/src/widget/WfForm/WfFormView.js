define([
        "jscore/core",
        "text!./WfForm.html",
        "styles!./WfForm.less"    
        ], function(core, template, style) {

	return core.View.extend({

		getTemplate: function() {
			return template;
		},

		getStyle: function() {
			return style;
		},

		getFormContent: function() {
			return this.getElement().find(".eaNFE_automation_UI-WfForm-content");
		},

		getRequired: function(id){
			return this.getElement().find(".validation-notification-"+id);
		},

		getToolTip: function(id){
			return this.getElement().find(".ebToolTip-"+id);
		}

	});

});