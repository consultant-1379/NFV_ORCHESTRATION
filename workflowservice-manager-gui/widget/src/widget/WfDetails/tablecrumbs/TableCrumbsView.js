define([
        "jscore/core",
        "text!./TableCrumbs.html",
        "styles!./TableCrumbs.less"
        ], function (core, template, style) {

	return core.View.extend({

		getTemplate: function() {
			return template;
		},

		getStyle: function() {
			return style;
		},

		getArrow: function() {
			return this.getElement().find(".eaDrilldown-TableCrumbs-arrow");
		}

	});

});