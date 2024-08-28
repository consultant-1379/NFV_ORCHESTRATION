define([
    'jscore/core',
    './WorkflowProgressSideView',
    "jscore/ext/mvp",
    'widget/WfDetails/WfDetails',
    "widget/WfBarCell/WfBarCell",
    "3pps/wfs/WfInstanceClient",
    'widget/WfUsertasks/TaskFormOnDrilldown/TaskFormOnDrilldown',
], function (core ,View ,mvp,WfDetails, WfBarCell,WfInstanceClient,TaskFormOnDrilldown) {   	
    	return core.Widget.extend({

        View: View,

        onViewReady: function(){   		    	    		    		       
        	var url = window.location.hash;
        	var arguments = url.substring(url.indexOf('/') + 1).split('/');
        	this.params = {
          		workflowDefinitionId : arguments[0],
          		workflowInstanceId : arguments[2],
          		workflowName : arguments[1],
          		startTime : arguments[3]
          };  
    		
        	this.options.regionEventBus.eventBus.subscribe("updatePercentage", function(updatePercentage){
        		this.view.getPercentage().setText(updatePercentage);
        		
        		if(updatePercentage === undefined || updatePercentage === null){
            		this.view.getPercentage().setText("N/A");	
            	}else{
            		this.view.getPercentage().setText(updatePercentage +"%");
            	}
                                  
                if(this.percentComplete == 0){
                	this.view.getBarPercentageCell().setStyle("width", "0px");
                }
                if(updatePercentage < 5 && updatePercentage > 0){
                	this.view.getBarPercentageCell().setStyle("width", "5px");                   
                }else{
                	this.view.getBarPercentageCell().setStyle("width", updatePercentage + "px");
                }
        	}.bind(this));
        	
        	this.options.regionEventBus.eventBus.subscribe("userTask", function(wfUsertaskModel2,taskHandledCallback,minHeight,toggleTaskForm){
        		if(!this.taskForm){
                	this.taskForm = new TaskFormOnDrilldown({wfUsertaskModel:wfUsertaskModel2, minHeight: minHeight, taskHandledCallback:taskHandledCallback});
                	this.taskForm.addEventHandler('toggleContentDrilldown', toggleTaskForm);
            	}
        		this.taskForm.attachTo(this.view.getUserTaskFormContent()); 
        	}.bind(this));
        	
        	this.options.regionEventBus.eventBus.subscribe("updateTimestamp", function(timeStamp){
        		this.view.getStartTime().setText(timeStamp);
        	}.bind(this));
        	
    		var startTime = this.params.startTime;
    		this.view.getStartTime().setText(startTime);
            
            this.activeWorkflowDefinitionId = this.params.workflowDefinitionId;
            this.activeWorkflowInstanceId = this.params.workflowInstanceId;
            this.showingChild = false;        	
        	
         	this.wfBarCell = new WfBarCell({regionEventBus: this.regionEventBus});
         	this.wfBarCell.attachTo(this.view.getBarCell());
         	
         	this.options.regionEventBus.eventBus.subscribe("hideForm", function(){
            	if (this.taskForm != null) {
	                this.taskForm.detach();
	                this.taskForm = null;
	            }
        	}.bind(this));
             	
            },	
            
            
            
//            hideForm: function() {
//	            if (this.taskForm != null) {
//	                this.taskForm.detach();
//	                this.taskForm = null;
//	            }
////	            if (this.addCommentform != null) {
////	                this.addCommentform.detach();
////	                this.addCommentform = null;
////	            }
//	    	},     
        
//        checkForUserTask: function(){
//        	if(this.wfComment){
//				this.wfComment.detach();
//                this.wfComment.destroy();
//            }  
//
//        	WfInstanceClient.getActiveTasks({
//    			processInstanceId : this.activeWorkflowInstanceId,
//    			unfinished : true,
//    			activityType : "userTask",
//                success: function(activities) {
//                	if(activities != null && activities.length > 0){
//                		this.view.getUserActivityContent().setStyle('display' ,"block");
//                		this.view.getUserActivityContent2().setStyle('display' ,"block");
//                		
//                			this.taskId = activities[0].taskId;
//             		           		
//                	}else{
//                		this.view.getUserActivityContent2().setStyle('display' ,"block");
//                		this.view.getUserActivityContent2().setStyle('display' ,"none");
//                	}
//                	
//
//	                if(this.taskId!==null){
//	                	
//                    	var wfUsertaskModel2 = new mvp.Model();
//                        wfUsertaskModel2.setAttribute("id", this.taskId);                      
//                        wfUsertaskModel2.setAttribute("name", activities[0].activityType);                    
//                        wfUsertaskModel2.setAttribute("workflowDefinitionId",activities[0].processDefinitionId);                     
//                        wfUsertaskModel2.setAttribute("workflowName", activities[0].activityName);                      
//                        wfUsertaskModel2.setAttribute("workflowVersion", "1");                      
//                        wfUsertaskModel2.setAttribute("shortWorkflowInstanceId", activities[0].processInstanceId);                      
//                        wfUsertaskModel2.setAttribute("workflowInstanceId", activities[0].executionId);
//                 	
//                        this.hideForm();
//                        if(!this.taskForm){
//                        	this.taskForm = new TaskFormOnDrilldown({wfUsertaskModel:wfUsertaskModel2, minHeight:"0px", k:this.taskHandledCallback.bind(this)});
//                        	this.taskForm.addEventHandler('toggleContentDrilldown', this.toggleContentDrilldown.bind(this));
//                    	}
//                        	
//                    	this.taskForm.attachTo(this.view.getUserTaskFormContent());              
//	                	
////	                	this.wfComment = new WfComment({taskId: this.taskId, shortWorkflowInstanceId: this.params.shortWorkflowInstanceId});
////	                	this.wfComment.addEventHandler('toggleContentDrilldown', this.toggleContentDrilldown.bind(this));
////	                	this.wfComment.attachTo(this.view.getUserActivityContent());
//
//	                }
//                      
//                }.bind(this)
//                  
//            });
//        	
//        },
        
        hideUserActivityContent: function(){
        	if(!this.view.getUserActivityContent2()){
        		this.view.getUserActivityContent2().setStyle('display' ,"block");
        	}
        	if(this.view.getUserActivityContent2()){
            	this.view.getUserActivityContent2().setStyle('display' ,"none");
            }else{
        		this.view.getUserActivityContent2().setStyle('display' ,"block");
        	}
        }
    });

});
