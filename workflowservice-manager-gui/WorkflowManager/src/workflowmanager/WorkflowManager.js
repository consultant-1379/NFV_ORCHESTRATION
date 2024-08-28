define([
        'jscore/core',
        './WorkflowManagerView',
        'widget/WfDefinitions/WfDefinitions',
        'widget/WfCountersSide/WfCountersSide',  
        "jscore/ext/locationController",
        "layouts/TopSection",
        'layouts/SlidingPanels'
        ], function (core, View, WfDefinitions, WfCountersSide, LocationController, TopSection, SlidingPanels) {   	
	return core.App.extend({

		View: View,

		onStart: function () {        	
			this.regionEventBus1 = new core.EventBus();

			this.regionEventBus1.subscribe("definitionTable-refreshed", function() {
				this.refreshCounters();
			}.bind(this));

			var newBreadcrumb = this.options.breadcrumb;

			var crumb = [{name: "ENM", url: "#workflowmanager"}]

			for(i=1; i < newBreadcrumb.length; i++){
				crumb.push(newBreadcrumb[i]);
			}	

			this.layout = new TopSection({
				context: this.getContext(),
				title: 'Workflow Manager',
//				breadcrumb: this.options.breadcrumb,
				breadcrumb: crumb,
				defaultActions: [{
					type: "button",
					color: 'blue',
					name: 'Create New Definition',
					link: '#',
					action: function(){
						var host = 'http://'+window.location.host;
						var path = '/bpmnModeler/dist/';
						var url = host + path;
						window.open(url);
					}
				},{
					type: 'separator'  
				},{
					type: "link",
					color: 'blue',
					name: 'All Workflow Instances',
					link: '#',
					action: function(){
						var locationController = new LocationController();
						var url = 'workflowmanager/allworkflowinstances';
						locationController.setLocation(url , false);
					}
				},{
					type: 'separator'  
				},{		   
					type: "link",
					color: 'blue',
					name: 'All Active Tasks',
					link: '#',
					action: function(){
						var locationController = new LocationController();
						var url = 'workflowmanager/activetasks';
						locationController.setLocation(url , false);
					}
                	   },{
                       	type: 'separator'  
                	   },{		   
                    	   type: "link",
                    	   color: 'blue',
                    	   name: 'Workflow History',
                    	   link: '#',
                    	   action: function(){
                    		   	var locationController = new LocationController();
                           		var url = 'workflowmanager/workflowhistory';
                           		locationController.setLocation(url , false);
                    	   }
				}]
			});
			this.layout.attachTo(this.getElement());
			this.layout.setContent(new SlidingPanels({
				context: this.getContext(),
				main: {label: 'Main Body', contents: new WfDefinitions({
					regionContext: this.getContext(),
					showActionButtons: true
				})},

				left: {label: "Overview",expanded: true, contents: new WfCountersSide({
					context: this.getContext()
				})}
			}));
		}
	});
});