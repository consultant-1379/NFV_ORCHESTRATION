// 'Create workflow instance' form

define([
    "jscore/core",
    "3pps/wfs/WfDefinitionClient",
    "3pps/wfs/WfInstanceClient",
    "../../WfForm/WfForm",
    "../../ErrorDialog/ErrorDialog",
    "./CreateWfInstanceFormView"
    ], function(core, WfDefinitionClient, WfInstanceClient, WfForm, ErrorDialog, View) {

       return core.Widget.extend({

            view: function() {
                // provide 'workflowName' to handlebars
                var workflowName = this.options.wfDefinitionModel.name;
                return new View({workflowName: workflowName});
            },

            onViewReady: function() {
                this.createHandledCallback = this.options.createHandledCallback;
                this.wfDefinitionId = this.options.wfDefinitionModel.id;

              	// Get and render start form - note form is optional
                WfDefinitionClient.getStartFormModelById({
                    wfDefinitionId: this.wfDefinitionId,
            		success: function(wfStartFormModel) {
                        // Add 'standard' cancel and submit buttons and handlers
                        var cancelButton = this.view.getCancelButton();
                        cancelButton.addEventHandler("click", function() {
                            this.handleCancel();
                        }.bind(this));

                        var submitButton = this.view.getSubmitButton();
                        submitButton.addEventHandler("click", function() {
                            this.handleSubmit(wfStartFormModel);
                        }.bind(this));

                        if (wfStartFormModel != null) { // form is optional
                            // Override default button texts if defined in form model
                            if (wfStartFormModel.getAttribute("cancelText") != null) {
                                cancelButton.setText(wfStartFormModel.getAttribute("cancelText"));
                            }
                            if (wfStartFormModel.getAttribute("submitText") != null) {
                                submitButton.setText(wfStartFormModel.getAttribute("submitText"));
                            }

                            try {
                                if(this.form){
                                	this.form.detach();
                                }
                                this.form = new WfForm({formModel: wfStartFormModel, variableCollection: null});
                                this.form.attachTo(this.view.getFormContent()); 
                            }
                            catch (err) {
                                this.handleError("Unable to build form for " + this.wfDefinitionId, [err]);        
                            }
                        }

                        if (this.options.minHeight) {
//                            this.getElement().setStyle("min-height", this.options.minHeight);
                        	this.getElement().setStyle("min-height", "300px;");
                        }

            		}.bind(this),
                    error: function(msg) {
                        this.handleError("Unable to retrieve form for " + this.wfDefinitionId, [msg]);
                    }.bind(this)
            	});
            },

            handleSubmit: function(wfStartFormModel) {
                var variables = Object.create(null);
                var torUserId = "";
                
                WfInstanceClient.getTORUserId({
                    success: function(data) {
                    	torUserId = data;
                    	
                    	var torIdObject = Object.create(null);
                    	torIdObject['value'] = torUserId;
                    	torIdObject['required'] = true;
                    
                    // Get variables from form
                    	if(this.form){
                    		variables = this.form.getVariables();
                    	}
    	                variables['tor-user-id'] = torIdObject;
    	                
                    if(variables == null)
                        return;                                     ///// exiting the function as validation fails

                // Start workflow instance
                WfInstanceClient.startInstanceById({
                    wfDefinitionId: this.wfDefinitionId,
                    variables: variables,
                    success: function() {
                        this.createHandledCallback(true);   // something changed
                    }.bind(this),
                    error: function(msg) {
                        this.handleError("Unable to start workflow instance for " + this.wfDefinitionId, [msg]);
                    }.bind(this)
                });
                }.bind(this)
            }); 
            },

            handleCancel: function() {
            	this.createHandledCallback(false);     // nothing changed
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
                this.createHandledCallback(false);
            }

        });
});