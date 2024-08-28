define([
        "jscore/core",
        "text!./WfInstances.html",
        "styles!./WfInstances.less"
        ], function(core, template, style) {

	return core.View.extend({

		getTemplate: function() {
			return template;
		},

		getStyle: function() {
			return style;
		},

		getSectionHeadingFilter: function() {
			return this.getElement().find(".eaNFE_automation_UI-WfInstances-SectionHeading-Filter");
		},

		getTableContent: function() {
			return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentTable");
		},

		getPagination: function() {
			return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentTable");
		},

		getRefreshButton: function(){
			return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentTable-buttonRefresh");
		}
	});

});