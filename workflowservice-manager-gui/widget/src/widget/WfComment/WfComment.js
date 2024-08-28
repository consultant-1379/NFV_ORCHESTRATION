// 'Create workflow instance' form

define([
    "jscore/core",
    "jscore/ext/mvp",
    "3pps/wfs/WfUsertaskClient",
    "../ErrorDialog/ErrorDialog",
    "./WfCommentView",
    "./UserComment/UserComment",
    "widgets/Notification"
    ], function(core, mvp, WfUsertaskClient, ErrorDialog, View, UserComment, Notification) {

       return core.Widget.extend({

    	   View: View,

            onViewReady: function() {
            	    	
            	this.taskId = this.options.taskId;
            	this.shortWorkflowInstanceId = this.options.shortWorkflowInstanceId;
            	this.eventBus = this.options.eventBus;

                this.submitButton = this.view.getSubmitButton();
                this.submitButton.addEventHandler("click", function() {
                    this.handleSubmit();
                }.bind(this));
                
                
                this.view.getCloseButton().addEventHandler("click", function() {
                	this.eventBus.publish("toggleCommentSection", "toggleComments");
                }.bind(this));
                
                var textArea = this.view.getComment();
            	textArea.addEventHandler("keyup", function(){
            		if(textArea.element.value != null && textArea.element.value != ""){
            			this.submitButton.element.classList.add("ebBtn_color_darkBlue");
            			this.submitButton.element.disabled= false;
            		}else{
            			this.submitButton.element.classList.remove("ebBtn_color_darkBlue");
            			this.submitButton.element.disabled = true;
            		}
            		
            	}.bind(this));
                
                WfUsertaskClient.getUsertasksComments({
                	taskId: this.taskId,
                	success: function(commentCollection){
                		if(commentCollection._collection.models.length == 0){
                			this.view.getComments().setText("No Comments have been added to this process...");
                		}
                        commentCollection.each(function(commentModel) {
                        	var userComment = new UserComment({userComment: commentModel});
                        	userComment.attachTo(this.view.getComments());
                        }.bind(this));
                        
                	}.bind(this)
                });
                	
            },
            
            handleSubmit: function(wfAddCommentFormModel) {
               
                // Get comment from textarea
            	var comment = this.view.getComment().element.value;
            	
            	if(comment.indexOf("[") > -1 || comment.indexOf("]") > -1){
           		
            		var notification = new Notification({
            			  label: '\"[]\" not allowed',
            			  icon: 'error',
            			  color: 'red',
            			  showCloseButton: true,
            			  autoDismiss: true,
            			  autoDismissDuration: 3000
            			});
            		notification.attachTo(this.view.getAddCommentSection());
            	}else{
            	
	                WfUsertaskClient.addUsertaskCommentById({
	                    id: this.shortWorkflowInstanceId,
	                	taskId: this.taskId,
	                    userId: "ejamcudHC1234",
	                    createdTime: null,
	                    comment: comment,
	                    success: function() {    
	                    	this.trigger("toggleContentDrilldown", "toggledrilldown");   // something changed
	                    }.bind(this),
	                    error: function(msg) {
	                        this.handleError("Unable to add comment for task: " + this.taskId, [msg]);
	                    }.bind(this)
	                });
            	}
            },

            handleError: function(title, messages) {
                // Build displayable message
                var message = " ";
                if (messages != null && messages.length > 0) {
                    for (var i = 0; i < messages.length; i++) {
                        message += messages[i] + " ";
                    }
                }

                new ErrorDialog({title: title, message: message});
                this.view.getButtonsToggle().setStyle('display',"none");     
            	this.trigger("toggleContentDrilldown", "toggledrilldown");
            }

        });
});