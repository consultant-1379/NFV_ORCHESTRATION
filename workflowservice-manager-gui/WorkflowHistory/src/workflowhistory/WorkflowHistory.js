define([
    'jscore/core',
    './WorkflowHistoryView',
    'widget/WfHistory/WfHistory',
    "layouts/TopSection",
    'layouts/SlidingPanels',
    "jscore/ext/locationController",
], function (core, View, WfHistory, TopSection, SlidingPanels, LocationController) {   	
    	return core.App.extend({

        View: View,

        onStart: function () {
        	
        var newBreadcrumb = this.options.breadcrumb;
 	        
 	        var crumb = [{name: "ENM", url: "#workflowmanager"}]
 	        
 	        for(i=1; i < newBreadcrumb.length; i++){
 	        	crumb.push(newBreadcrumb[i]);
 	        }	  	
         	
         	this.layout = new TopSection({
         	    context: this.getContext(),
         	    title: 'Workflow History',
//         	    breadcrumb: this.options.breadcrumb,
         	    breadcrumb: crumb,
         	    defaultActions: [
         	   {
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
               }]
         	});
         	this.layout.attachTo(this.getElement());
         	this.layout.setContent(new SlidingPanels({
         		context: this.getContext(),
         		main:{
         				label: 'Main Body',
         				contents: new WfHistory({
         					regionContext: this.getContext(),
         					filterValueActive: false,
         					hidePercentage : true
         				})
         			}
         	}));
            
        }

    });

});