define([
    'jscore/core',
    './ActiveTasksView',
    'widgets/Breadcrumb',
    'widget/WfInstances/WfInstances',
    'widget/WfProgress/WfProgress',
    'widget/WfDetails/WfDetails',
    'widget/WfUsertasks/WfUsertasks',
    "widget/WfCounter/WfCounter",
    'layouts/SlidingPanels',
    "layouts/TopSection",
    "jscore/ext/locationController",
    'widget/WfUsertasks/TaskTable/ActiveCounters/ActiveCounters'
], function (core, View, Breadcrumb, WfInstances, WfProgress, WfDetails, WfUsertasks, WfCounter, SlidingPanels, TopSection, LocationController, ActiveCounters) {   	
    	return core.App.extend({

        View: View,

        onStart: function () {
        	
        	this.regionEventBus2 = new core.EventBus();
        	
//        	this.wfUsertasks = new WfUsertasks({regionEventBus: this.regionEventBus2, filterValueActive: true, hidePercentage : true});
//        	this.wfUsertasks.addEventHandler('toggleActiveTaskContent', this.toggleActiveTask.bind(this));
//        	this.wfUsertasks.attachTo(this.view.getWfUsertasksContent());
        	
//        	this.regionEventBus2.subscribe("instanceTable-refreshed", function() {
//        		this.refreshCounters();
//        	}.bind(this));
        	
        	var newBreadcrumb = this.options.breadcrumb;
	        
	        var crumb = [{name: "ENM", url: "#workflowmanager"}]
	        
	        for(i=1; i < newBreadcrumb.length; i++){
	        	crumb.push(newBreadcrumb[i]);
	        }
        	
        	this.layout = new TopSection({
         	    context: this.getContext(),
         	    title: 'Active Tasks',
//         	    breadcrumb: this.options.breadcrumb,
         	    breadcrumb:crumb,
         	   defaultActions: [{
                   /*type: "button",
                   color: 'blue',
                   name: 'Create New Definition',
                   link: '#',
                   action: function(){
                       console.log('Create New Definition');
                   }*/
         	   /*},{
         		 type: 'separator'  */
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
		              	   name: 'Workflow History',
		              	   link: '#',
		              	   action: function(){
		              		   	var locationController = new LocationController();
		                     		var url = 'workflowmanager/workflowhistory';
		                     		locationController.setLocation(url , false);
		              	   }
                /*},{
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
                	   }*/
               }]
         	});
         	this.layout.attachTo(this.getElement());
         	
         	this.activeCounters = new ActiveCounters({
     									context: this.getContext()
         							});
         	
         	this.wfUsertasks = new WfUsertasks({
					     			regionContext: this.getContext(),
					     			filterValueActive: true,
					     			hidePercentage : true
     							});
         	
         	this.layout.setContent(new SlidingPanels({
         		context: this.getContext(),
         		main: {label: 'Main Body', contents: this.wfUsertasks},
         		
         		left: {label: "Overview", expanded: true, contents: this.activeCounters}
         	}));
         	
         	this.getContext().eventBus.subscribe("viewAllTasks", function(){
    	    	var active = null;
    	    	var suspended = null;
    	    	this.wfUsertasks.showTable(active, suspended);
         	}.bind(this));
         	
         	this.getContext().eventBus.subscribe("viewActiveTasks", function(){
    	    	var active = true;
    	    	var suspended = false;
    	    	this.wfUsertasks.showTable(active, suspended);
         	}.bind(this));
         	
         	this.getContext().eventBus.subscribe("viewSuspendedTasks", function(){
    	    	var active = false;
    	    	var suspended = true;
    	    	this.wfUsertasks.showTable(active, suspended);
         	}.bind(this));
         	
        },
        
//        onAttach: function() {
//        	if(this.wfUsertasks){
//           	 this.wfUsertasks.update();
//            }
//        	this.refreshCounters();
//		},
		
//		refreshCounters: function(){
//		
//			this.getContext().wfTasksTotal.detach();
//    		this.wfTasksTotal.setCount("WfTasksTotal");
//    		this.wfTasksTotal.attachTo(this.view.getTasksTotal());
//         	
//    		this.wfTasksActive.detach();
//    		this.wfTasksActive.setCount("WfTasksActive");
//    		this.wfTasksActive.attachTo(this.view.getTasksActive());
//         	
//    		this.wfTasksCancelled.detach();
//    		this.wfTasksCancelled.setCount("WfTasksCancelled");
//    		this.wfTasksCancelled.attachTo(this.view.getTasksCancelled());
//		}
		
        
//        toggleActiveTask: function(message){
//        	console.log("here");
//        	this.view.getUsertasksToggle().setStyle('display',"none");
//        }

    });

});