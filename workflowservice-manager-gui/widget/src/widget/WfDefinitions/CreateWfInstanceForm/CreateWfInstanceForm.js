//'Create workflow instance' form

define([
        "jscore/core",
        "3pps/wfs/WfDefinitionClient",
        "3pps/wfs/WfInstanceClient",
        "../../WfForm/WfForm",
        "../../ErrorDialog/ErrorDialog",
        "./CreateWfInstanceFormView",
        'jscore/ext/locationController',
        ], function(core, WfDefinitionClient, WfInstanceClient, WfForm, ErrorDialog, View, LocationController) {

	return core.Widget.extend({

		view: function() {
			this.workflowName = this.options.wfDefinitionModel.getAttribute("name");
			return new View({workflowName: this.workflowName});
		},

		onAttach: function() {
			this.createHandledCallback = this.options.createHandledCallback;
			this.wfDefinitionId = this.options.wfDefinitionModel.getAttribute("id");

			// Get and render start form - note form is optional
			WfDefinitionClient.getStartFormModelById({
				wfDefinitionId: this.wfDefinitionId,
				success: function(wfStartFormModel) {
					// Add 'standard' cancel and submit buttons and handlers
					var cancelButton = this.view.getCancelButton();
					cancelButton.addEventHandler("click", function() {
						this.handleCancel();
						if(this.options.regionEventBus){
							this.options.regionEventBus.publish("showHeader", "showHeader");
							this.options.regionEventBus.publish("showPagination", "showPagination");
						}
					}.bind(this));

					var submitButton = this.view.getSubmitButton();
					submitButton.addEventHandler("click", function() {
						this.handleSubmit(wfStartFormModel);
//						this.options.regionEventBus.publish("showHeader", "showHeader");
//						this.options.regionEventBus.publish("showPagination", "showPagination");
					}.bind(this));

					if (wfStartFormModel != null) { // form is optional
//						Override default button texts if defined in form model
//						if (wfStartFormModel.getAttribute("cancelText") != null) {
//						cancelButton.setText(wfStartFormModel.getAttribute("cancelText"));
//						}
//						if (wfStartFormModel.getAttribute("submitText") != null) {
//						submitButton.setText(wfStartFormModel.getAttribute("submitText"));
//						}

						try {
							// Create and append form
							this.form = new WfForm({formModel: wfStartFormModel, variableCollection: null});
							this.form.attachTo(this.view.getFormContent()); 
						}
						catch (err) {
							this.handleError("Unable to build form for " + this.wfDefinitionId, [err]);        
						}
					}

					if (this.options.minHeight) {
//						this.getElement().setStyle("min-height", this.options.minHeight);
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


					if(wfStartFormModel == null || !(wfStartFormModel.toString().indexOf("\n") > -1) && wfStartFormModel.attributes.formInfo.format == "json"){
						// Get variables from form
						if(this.form){
							variables = this.form.getVariables();
						}
					}
					variables['tor-user-id'] = torIdObject;

					//needs updating!
					if(variables != undefined && variables != null){    
						var locCtlr = new LocationController();
						var value = this.value;
						var url = 'workflowsinstances/' + this.options.wfDefinitionModel.getAttribute("id") + "/" + this.options.wfDefinitionModel.getAttribute("name");
						locCtlr.setLocation(url);

						if(this.options.regionEventBus){
							this.options.regionEventBus.publish("showHeader", "showHeader");
							this.options.regionEventBus.publish("showPagination", "showPagination");
						}
					}

					if(variables == null)
						return; 

					WfInstanceClient.startInstanceById({
						wfDefinitionId: this.wfDefinitionId,
						variables: variables,
						success: function() {
							var locCtlr = new LocationController();
							var value = this.value;
							var url = 'workflowsinstances/' + this.wfDefinitionId + "/" + this.workflowName;
							locCtlr.setLocation(url);

							if(this.options.regionEventBus){
								this.options.regionEventBus.publish("showHeader", "showHeader");
								this.options.regionEventBus.publish("showPagination", "showPagination");
							}
							this.createHandledCallback(true);   // something changed
						}.bind(this),
						error: function(msg) {
							this.handleError("Unable to start workflow instance for " + this.wfDefinitionId, [msg]);
						}.bind(this)
					});                     
				}.bind(this)
			});   	                         ///// exiting the function as validation fails
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