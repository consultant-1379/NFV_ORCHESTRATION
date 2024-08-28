define([
	"jscore/core",
	"text!./Crumb.html"
], function (core, template) {
	
	return core.View.extend({

		getTemplate: function() {
			return template;
		},
		
		getDropDownArrow: function(){
			return this.getElement().find(".ebBreadcrumb-arrow");
		},
		
		getParent: function(){
			return this.getElement().find(".eaDrilldown-TableCrumbs-crumb");
		}

	});

});