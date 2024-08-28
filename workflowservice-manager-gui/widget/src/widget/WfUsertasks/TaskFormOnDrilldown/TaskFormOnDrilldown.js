//Usertask form widget

define([
        "jscore/core",
        "./TaskFormOnDrilldownView",
        "3pps/wfs/WfUsertaskClient",
        "3pps/wfs/WfInstanceClient",
        "../../WfForm/WfForm",
        "../../ErrorDialog/ErrorDialog",
        "jscore/ext/locationController"
        ], function(core,View, WfUsertaskClient, WfInstanceClient, WfForm, ErrorDialog, LocationController) {

	return core.Widget.extend({

		View: View,

		onAttach: function(){
			// expose 'taskName' for handlebars
			var taskName = this.options.wfUsertaskModel.getAttribute("workflowName");
			this.view.getTaskNameHandleBar().setText(taskName);
		},

		onViewReady: function() {
			this.wfUsertaskModel = this.options.wfUsertaskModel;

			this.taskHandledCallback = this.options.taskHandledCallback;
			this.usertaskId = this.options.wfUsertaskModel.getAttribute("id");
			this.usertaskName = this.wfUsertaskModel.getAttribute("name");
			this.workflowInstanceId = this.options.wfUsertaskModel.getAttribute("workflowInstanceId");          

			// [TODO - claim task]
			this.getAndRenderForm(this.usertaskId,this.workflowInstanceId);
			if (this.options.minHeight) {
				this.getElement().setStyle("min-height", this.options.minHeight);
			}

		},

		getAndRenderForm: function(usertaskId, workflowInstanceId) {
			// Get and render form - note form is optional
			WfUsertaskClient.getFormModelByUsertaskId({
				usertaskId: usertaskId,
				success: function(wfUsertaskFormModel) {
					// Add 'standard' cancel and submit buttons and handlers
					var cancelButton = this.view.getCancelButton();
					cancelButton.addEventHandler("click", function() {
						this.view.getButtonsToggle().setStyle('display',"none");                        	
						this.trigger("toggleContentDrilldown", "toggledrilldown");                      	
					}.bind(this));

					var submitButton = this.view.getSubmitButton();
					submitButton.addEventHandler("click", function() {
						this.taskHandledCallback = this.options.taskHandledCallback;
						this.handleSubmit(wfUsertaskFormModel);
					}.bind(this));

					if (wfUsertaskFormModel != null) {  // form is optional
							if(wfUsertaskFormModel == null || !(wfUsertaskFormModel.toString().indexOf("\n") > -1) && wfUsertaskFormModel.attributes.formInfo.format == "json"){
							// Override default button texts if defined in form model
							if (wfUsertaskFormModel.getAttribute("cancelText") != null) {
								cancelButton.setText(wfUsertaskFormModel.getAttribute("cancelText"));
							}
							if (wfUsertaskFormModel.getAttribute("submitText") != null) {
								submitButton.setText(wfUsertaskFormModel.getAttribute("submitText"));
							}
							this.getVariablesAndRenderForm(wfUsertaskFormModel);
						}else if(wfUsertaskFormModel.toString().indexOf("\n") > -1 && wfUsertaskFormModel.indexOf("") >= 0){
							this.newFormElement = core.Element.parse(wfUsertaskFormModel);
							this.view.getFormContent().append(this.newFormElement);					
					}	
				}

			}.bind(this),
			error: // WfUsertaskClient.getFormModelByUsertaskId
				function(msg) {
				this.handleError("Unable to retrieve form for \"" + this.usertaskName + "\"", [msg]);
			}.bind(this)
		});
	},

	getVariablesAndRenderForm: function(wfUsertaskFormModel) {
		// Get variables for workflow instance and use in form creation
		WfInstanceClient.getVariables({
			wfInstanceId: this.workflowInstanceId,
			success: function(wfVariableCollection) {
				try {
					// Create and append form
					this.form = new WfForm({formModel: wfUsertaskFormModel, variableCollection: wfVariableCollection});
					this.form.attachTo(this.view.getFormContent());             
				}
				catch (err) {
					this.handleError("Unable to build form for " + this.usertaskName, [err]);        
				}
			}.bind(this),
			error: // WfInstanceClient.getVariables
				function(msg) {
				this.handleError("Unable to retrieve variables for \"" + this.usertaskName + "\"", [msg]);
			}.bind(this)
		});
	},

	handleSubmit: function(wfUsertaskFormModel) {
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
				// complete task
				WfUsertaskClient.completeUsertaskById({
					usertaskId: this.usertaskId,
					variables: variables,
					success: function() {
						this.taskHandledCallback(true); // something changed
					}.bind(this),
					error: function(msg) {
						this.handleError("Unable to complete task  \"" + this.usertaskName + "\"", [msg]);
					}.bind(this)
				});
			}.bind(this)
		});
	},

	handleCancel: function() {
		// [TODO - release task]
		this.taskHandledCallback(false);       // nothing changed
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
		this.taskHandledCallback(false);
	}

});
});