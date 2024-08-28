// Workflow definitions widget

define([ "jscore/core",
         "./WfDetailsView" 
         ], function(core, View) {

	return core.Widget.extend({

		View : View,

		updateURLParams : function() {
			
			var url = window.location.hash;
			var arguments = url.substring(url.indexOf('/') + 1).split('/');
			this.params = {
				description : null,
				id : arguments[0],
				key : arguments[0].slice(0, arguments[0].indexOf(":")),
				name : arguments[1],
				version : 1,
				shortWorkflowInstanceId : arguments[2],
				startTime : arguments[3]
			};
		},

		onViewReady : function() {
//			this.eventBus = this.options.regionEventBus.eventBus;
			this.updateURLParams();
			
			this.view.getDetailsHeader().setText(this.params.name);
			
			this.view.addZoomInClickHandler(function() {
				this.trigger("zoomIn", "zoomIn");
			}.bind(this));
			this.view.addZoomOutClickHandler(function() {
				this.trigger("zoomOut", "zoomOut");
			}.bind(this));

			this.trigger("addCalledWorkflows", "addCalledWorkflows");
			//refresh in details #TODO move it to wfDiagram
			this.view.addRefreshClickHandler(function() {
				this.trigger("refreshProgress", "refreshProgress");
				//this.trigger("addCalledWorkflows", "addCalledWorkflows"); 
			}.bind(this));
		},
		
		onStart: function(){
//			this.options.regionEventBus.eventBus.subscribe("UpdateDiagramHeader", function(UpdateDiagramHeader){
//				console.log("Here");
//			}.bind(this));
		},

		doDisplayElement : function(details) {
			var diagram = details[1];
			var content = this.view["get" + UsertaskDiagram + "Content"]();
			var children = content.children();

			if (children.length != 0) {
				children[0].remove();
			}
			diagram.attachTo(content);
		}
	});
});