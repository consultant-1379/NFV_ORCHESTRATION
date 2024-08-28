define([
    'jscore/core',
    './WorkflowInstanceNView',
    "jscore/ext/mvp",
    'widgets/Breadcrumb',
    'widget/WfUsertasks/WfUsertasks',
    'widget/WfUsertasks/TaskFormOnDrilldown/TaskFormOnDrilldown',
    'widget/WfDetails/WfDetails',
    "widget/WfUsertasks/TaskTable/TaskActionsCell/TaskActionsCellView",
    "widgets/table/Cell",
    'widget/WfDiagram/WfDiagram',
    "widget/WfProgress/WfProgressModel",
    "widget/WfBarCell/WfBarCell",
    "3pps/wfs/WfInstanceClient",
    'widgets/SelectBox',
    "jscore/ext/locationController",
    "widget/WfComment/WfComment",
    "3pps/wfs/WfUsertaskClient",
    "layouts/TopSection",
    'layouts/SlidingPanels',
    'widget/WorkflowProgressSide/WorkflowProgressSide',
    './region/WorkflowInstanceNRegion/WorkflowInstanceNRegion'
], function (core ,View ,mvp, Breadcrumb, WfUsertasks, TaskFormOnDrilldown , WfDetails, TaskActionsCellView, 
		Cell, WfDiagram, WfProgressModel, WfBarCell, WfInstanceClient, SelectBox, LocationController, WfComment, 
		WfUsertaskClient,TopSection,SlidingPanels,WorkflowProgressSide,WorkflowInstanceNRegion) {   	
	
	return core.App.extend({

        View: View,
        
        updateURLParams: function() {          	
    		var url = window.location.hash;
    		var arguments = url.substring(url.indexOf('/') + 1).split('/');
    		this.params = {
    				id : arguments[0],
    				name : arguments[1],				
    				shortWorkflowInstanceId : arguments[2],
    				startTime : arguments[3],
    				workflowVersion : arguments[4],
            }; 
    	},
    	
    	onAttach: function(){
    		this.updateAll();
            this.hideForm();              	
            this.view.getUserActivityContent().setStyle('display' ,"none");           
            this.workflowRegion.showDiagram(this.params);
    	},
    	
    	updateAll: function(){
    		this.updateURLParams();			
    		this.activeWorkflowDefinitionId = this.params.id;
            this.activeWorkflowInstanceId = this.params.shortWorkflowInstanceId;
            this.workflowName =  this.params.name;    		
    		this.checkForUserTask();		
    	},
    	  	
    	onStart: function () {       
	
    		this.updateAll();
    		
         	this.getContext().eventBus.subscribe("updateSidebars", function(activeWorkflowInstanceId){
         		this.activeWorkflowInstanceId = activeWorkflowInstanceId;
         		this.checkForUserTask();
         		//#TODO add updates for progress and comments
         	}.bind(this));
         	
         	this.getContext().eventBus.subscribe("redrawDiagram", function(){
         		this.workflowRegion.showDiagram(this.params);
         	}.bind(this));
         	
         	this.getContext().eventBus.subscribe("removeCommentSection", function(removeComments){
         		if(this.slidingPanels.rightShown){
         			this.slidingPanels.toggleRightPanel();
         		}
	     		this.slidingPanels.rightButton.hide();
	     	}.bind(this));
         	
         	this.getContext().eventBus.subscribe("toggleCommentSection", function(removeComments){
         		if(this.slidingPanels.rightShown){
         			this.slidingPanels.toggleRightPanel();
         		}
	     		//this.slidingPanels.rightButton.hide();
	     	}.bind(this));
         	
         	var newBreadcrumb = this.options.breadcrumb;
         	
         	var crumb = [{name: "ENM", url: "#workflowmanager"}]
	        
	        for(i=1; i < newBreadcrumb.length; i++){
	        	crumb.push(newBreadcrumb[i]);
	        }
	        
	        var breadcrumb = 
	        		{name: 'Workflow Manager', url: '#workflowmanager', children: [
	        		{name: 'All Workflow Instances', url: '#workflowmanager/allworkflowinstances'},
	        		{name: 'Active Tasks', url: '#workflowmanager/activetasks'},
	        		{name: 'Workflow History', url: '#workflowmanager/workflowhistory'}
	        	]};
	        crumb.splice(1, 0, breadcrumb);
         	        	
         	this.layout = new TopSection({
           	    context: this.getContext(),
           	    title: 'Workflow Instance',
           	    breadcrumb: crumb,
           	   defaultActions: [{
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
         	
         	this.workflowRegion = new WorkflowInstanceNRegion({
       			regionEventBus: this.getContext()
       		});
         	
         	this.wfComment = new WfComment({
	   			eventBus: this.getContext().eventBus
	   		});
         	
	     	this.workflowProgressSide = new WorkflowProgressSide({
	   			regionEventBus: this.getContext()
	   		});
	     	
	     	this.slidingPanels = new SlidingPanels({
           		context: this.getContext(),
           		main: {label: 'Main Body', contents: this.workflowRegion
           		},

           		right: {label: "Comments", expanded: false, contents: this.wfComment
       			},
           		
           		left: {label: "Overview", expanded: true, contents: this.workflowProgressSide
           			}
           	});
	     	
           	this.layout.attachTo(this.getElement());
           	this.layout.setContent(this.slidingPanels);
           	             	
            },
        
        checkForUserTask: function(){
        	if(this.wfComment){
				this.wfComment.detach();
                this.wfComment.destroy();
                this.wfComment = null;
            }  

        	WfInstanceClient.getActiveTasks({
    			processInstanceId : this.activeWorkflowInstanceId,
    			unfinished : true,
    			activityType : "userTask",
                success: function(activities) {
                	if(activities != null && activities.length > 0){
                		this.view.getUserActivityContent().setStyle('display' ,"block");
                		this.workflowProgressSide.view.getUserTaskFormContent().setStyle('display' ,"block");
                		this.slidingPanels.rightButton.show();
                		
                		
                			this.taskId = activities[0].taskId;
             		           		
                	}else{
                		this.workflowProgressSide.view.getUserTaskFormContent().setStyle('display' ,"none");
                		this.slidingPanels.rightButton.hide();
                		if(this.slidingPanels.rightShown){
                 			this.slidingPanels.toggleRightPanel();
                 		}
//                		this.wfComment.view.getComments().setText("No Comments have been added to this process...");
                	}                	

	                if(this.taskId!==null || this.taskId !==undefined){
	                	
                    	var wfUsertaskModel2 = new mvp.Model();
                    	if(activities.length != 0){
	                        wfUsertaskModel2.setAttribute("id", this.taskId);                      
	                        wfUsertaskModel2.setAttribute("name", activities[0].activityType);                    
	                        wfUsertaskModel2.setAttribute("workflowDefinitionId",activities[0].processDefinitionId);                     
	                        wfUsertaskModel2.setAttribute("workflowName", activities[0].activityName);                      
	                        wfUsertaskModel2.setAttribute("workflowVersion", "1");                      
	                        wfUsertaskModel2.setAttribute("shortWorkflowInstanceId", activities[0].processInstanceId);                      
	                        wfUsertaskModel2.setAttribute("workflowInstanceId", activities[0].executionId);
	                        
//	                        this.getContext().eventBus.publish("userTask", wfUsertaskModel2,this.taskHandledCallback.bind(this),"0px", this.toggleContentDrilldown.bind(this));
                 	
	                        this.hideForm();
	                        if(!this.taskForm){
	                        	this.taskForm = new TaskFormOnDrilldown({wfUsertaskModel:wfUsertaskModel2, minHeight:"0px", taskHandledCallback:this.taskHandledCallback.bind(this)});
	                        	this.taskForm.addEventHandler('toggleContentDrilldown', this.toggleContentDrilldown.bind(this));
	                    	}
	                        	              	
	                    	this.taskForm.attachTo(this.view.getUserTaskFormContent());
	                        this.wfComment = new WfComment({taskId: this.taskId, shortWorkflowInstanceId: this.params.shortWorkflowInstanceId, eventBus: this.getContext().eventBus});
		                	this.wfComment.addEventHandler('toggleContentDrilldown', this.toggleContentDrilldown.bind(this));
		                	this.wfComment.attachTo(this.view.getUserActivityContent());
                    	}else{
                    		if(!this.taskId){
                    			this.getContext().eventBus.publish("removeCommentSection", "removeComments");
	                    	}
                    	}
	                }                     
                }.bind(this)                 
            });        	
        },
    	
    	taskHandledCallback: function(somethingChanged) {
	           if(somethingChanged == true) {
	                setTimeout(function() {
	                	this.getContext().eventBus.publish("something-changed", "usertasks");
	                }.bind(this), 2000);
	            }
            
            var locationController = new LocationController();
        	var url = 'workflowsinstances/' + this.params.id + "/" + this.params.name;                        	                	              	
        	locationController.setLocation(url , false);
            locationController.start();
        },
    	
    	hideForm: function() {
            if (this.taskForm != null) {
                this.taskForm.detach();
                this.taskForm = null;
            }
            if (this.wfComment != null) {
                this.wfComment.detach();
                this.wfComment = null;
            }
    	},
    	
    	hideProgress: function () {
            if (this.WfProgress != null) {
               this.WfProgress.destroy();
               this.WfProgress = null;
            }
    	},

        buildOverlays: function(keyableStatus, keyableCount){
            var highlights = [];
            var countBadges = [];
            for (var key in keyableStatus) {
                var status = keyableStatus[key];
                if (status && status != null && status !== "") {
                    highlights.push({nodeId: key, style: "highlight-"+status});
                    var count = keyableCount[key];
                    if (count && count > 0) {
                        if ((count > 1) || (count == 1 && status === "Started")) {
                            countBadges.push({nodeId: key, count: count});
                        }
                    }
                }
            }
            return {highlights: highlights, countBadges: countBadges};
        },
        
        showBackButton: function() {
        	this.backButton.setStyle("display", "block");
        },
        
        toggleContentDrilldown: function(message){
        	this.checkForUserTask();
        },
        
        showFullDiagram: function(){
        	this.bpmnDiagram.bpmnDiagram.zoom(1);
        },
        
        showZoomed: function(){
        	this.bpmnDiagram.bpmnDiagram.zoom(1.5);
        }

    });

});
