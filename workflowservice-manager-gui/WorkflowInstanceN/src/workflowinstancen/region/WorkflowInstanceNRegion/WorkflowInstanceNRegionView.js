define([
        'jscore/core',
        'text!./workflowInstanceNRegion.html',
        'styles!./workflowInstanceNRegion.less'
        ], function (core, template, style) {

	return core.View.extend({

		getTemplate: function() {
			return template;
		},  

		getStyle: function() {
			return style;
		},

		getBreadcrumb: function(){
			return this.getElement().find(".eaWorkflowInstanceNRegion-MainRegion-Breadcrumb");
		},

		getWfInstanceNContent: function() {
			return this.getElement().find(".eaWorkflowInstanceNRegion-MainRegion-contentWfInstanceN-home");
		},

		getContentDiagram: function(){
			return this.getElement().find(".eaWorkflowInstanceNRegion-MainRegion-contentWfInstanceN-home");
		},

		getBackButton: function () {
			return this.getElement().find(".eaNFE_automation_UI-WfDetails-buttonBack");
		},

		getWfInstanceNProgress: function() {
			return this.getElement().find(".eaNFE_automation_UI-WfDetails-progress");
		},

		getBarPercentageCell: function() {
			return this.getElement().find(".eaNFE_automation_UI-WfBarCell-contentTable-BarCell");
		},

		
		getRefreshButton: function(){
			return this.getElement().find(".eaWorkflowInstanceNRegion-MainRegion-contentWfInstanceN-buttonRefresh");
		},

		addRefreshClickHandler: function(fn){
			this.getElement().find(".eaNFE_automation_UI-WfDetails-buttonRefresh").addEventHandler("click", fn);
		},

		getContentDetails: function(){
			return this.getElement().find(".eaWorkflowInstanceNRegion-MainRegion-contentWfInstanceN-contentDetails");
		},

		getTableCrumbHolder: function(){
			return this.getElement().find(".eaDrilldown-TableCrumbs-crumbText");
		}

	});

});