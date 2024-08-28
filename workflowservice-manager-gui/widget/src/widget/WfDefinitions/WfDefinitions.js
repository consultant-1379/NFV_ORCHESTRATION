// Workflow definitions widget

define([
    "jscore/core",
    "./WfDefinitionsView",
    "./WfDefinitionsTable/WfDefinitionsTable",
    "./CreateWfInstanceForm/CreateWfInstanceForm",
    "../WfDiagram/WfDiagram"
    ], function(core,View, WfDefinitionsTable, CreateWfInstanceForm, WfDiagram) {

       return core.Widget.extend({

            View: View,
            
			Update: function(){
					if(this.bpmnDiagram){
					   this.bpmnDiagram.detach();
					   this.WfDefinitionsTable.attach();
					}
				},

            onViewReady: function() {
                this.regionEventBus = this.options.regionContext.eventBus;
                
                this.view.getHeadingContent().setStyle('display' ,"none");

                // TODO: 'something-changed' is a hack/workaround for lack of UI push, and will be removed when UI push supported.
                this.regionEventBus.subscribe("something-changed", function (whatChanged) {
                    // nothing to do for now
                }.bind(this));
                 
                 this.view.getCloseDiagram().addEventHandler("click", function() {
                	 if(this.bpmnDiagram){
                     	this.bpmnDiagram.detach();
                     	this.bpmnDiagram.destroy();
                     	this.bpmnDiagram = undefined;
                     }
                	 
                	 if(!this.WfDefinitionsTable){
                 		this.showTable();
                 		}      	
                 	this.toggleHandleBarView();
                 	//this.view.getCloseDiagram().setStyle('display' ,"none");
                 	this.view.getHeadingContent().setStyle('display' ,"none");
                }.bind(this));
                this.view.getCloseLink().addEventHandler("click", function() {
	               	 if(this.bpmnDiagram){
	                  	this.bpmnDiagram.detach();
	                  	this.bpmnDiagram.destroy();
	                  	this.bpmnDiagram = undefined;
	                  }
	             	 
	             	 if(!this.WfDefinitionsTable){
	              		this.showTable();
	              		}      	
	              	this.toggleHandleBarView();
	              	//this.view.getCloseDiagram().setStyle('display' ,"none");
	              	this.view.getHeadingContent().setStyle('display' ,"none");
                }.bind(this));
                            
                this.CreateWfInstanceForm = null;
                this.showTable();
                this.toggleHandleBarView();   
                
                
                this.regionEventBus.subscribe("toggleHeadingOff", function(toggleHeaderOff){
                	this.view.getHeadingContent().setStyle("display","none");
                }.bind(this));
                
                this.regionEventBus.subscribe("toggleHeadingOn", function(toggleHeaderOn){
                	this.view.getHeadingContent().setStyle("display","inline-block");
                }.bind(this));
                
                this.regionEventBus.subscribe("hideBottomDivider", function(hideBottomDivider){
                	this.view.getBottomDivider().setStyle("display" ,"none");
                }.bind(this));
            },
            
            refreshButtonClicked: function() {
                this.showTable();            
            },
            
            showTable: function() {
            	if(this.WfDefinitionsTable){
            		this.WfDefinitionsTable.detach();
            		this.trigger("removeWfDefinitionsTable", "removeTable");
            		this.view.getHeadingContent().setStyle('display' ,"none");
            	}

                // Build and display table
            	if(!this.WfDefinitionsTable){
	                this.WfDefinitionsTable = new WfDefinitionsTable({showAllVersions: this.currentFilterShowAll, showActionButtons: this.options.showActionButtons, regionEventBus: this.regionEventBus});
	                this.WfDefinitionsTable.attachTo(this.view.getTableContent());
                }
            	
	                if (this.tableContentHeight) {
	                    this.view.getTableContent().setStyle("height", this.tableContentHeight);
	                }

                // Add handler for 'create instance' table action
                this.WfDefinitionsTable.options.regionEventBus.subscribe("createinstance", function(wfDefinitionModel) {
                    this.createInstance(wfDefinitionModel);
                }.bind(this));

                // Add handler for 'show diagram' table action
                this.WfDefinitionsTable.options.regionEventBus.subscribe("showdiagram", function(wfDefinitionModel) {
                	if(!this.bpmnDiagram){
                    this.bpmnDiagram = new WfDiagram({  xid: "def-",
                                                        wfDefinitionId: wfDefinitionModel.id,
                                                        wfName: wfDefinitionModel.name });
                	
                    
                    this.view.getHandleButton().setStyle('display' ,"block");
                    //this.view.getCloseDiagram().setStyle('display' ,"inline-block");
	                var diagramName = wfDefinitionModel.name;
	                this.view.getHandleBar().setText(diagramName);   
	                
                	}
	                                               
	                if(this.WfDefinitionsTable){
			            this.WfDefinitionsTable.detach();
			            this.WfDefinitionsTable.destroy();
			            this.WfDefinitionsTable = undefined;
		            }	                
		            this.bpmnDiagram.attachTo(this.view.getTableContent());
                }.bind(this));
            },
            
            toggleHandleBarView: function(){
            	this.view.getHandleButton().setStyle('display' ,"none");
            },

            hideTable: function() {
                if (this.WfDefinitionsTable != null) {
                    this.tableContentHeight = this.view.getTableContent().getStyle("height");
                    this.WfDefinitionsTable.detach();
                    this.WfDefinitionsTable = null;
                    this.view.getTableContent().setStyle("height", "0px");
                }
            },

            showCreateInstanceForm: function(wfDefinitionModel) {
                this.hideCreateInstanceForm();
                // Build and show 'create instance' form
                if(!this.CreateWfInstanceForm){
                	this.CreateWfInstanceForm = new CreateWfInstanceForm({/*minHeight: this.tableContentHeight,*/ wfDefinitionModel:wfDefinitionModel, createHandledCallback:this.createHandledCallback.bind(this), regionEventBus: this.options.regionContext.eventBus});
                	this.CreateWfInstanceForm.attachTo(this.view.getFormContent());
                }
            },

            hideCreateInstanceForm: function() {
                if (this.CreateWfInstanceForm != null) {
                    this.CreateWfInstanceForm.destroy();
                }
                this.CreateWfInstanceForm = null;
            },

            createInstance: function(wfDefinitionModel) {
                this.hideTable();
                if(this.showCreateInstanceForm){
                	this.showCreateInstanceForm.detach();
                }
                this.showCreateInstanceForm(wfDefinitionModel);
            },

            createHandledCallback: function(somethingChanged) {
                this.hideCreateInstanceForm();
                if(this.bpmnDiagram) this.bpmnDiagram.destroy();
                this.showTable();
                if (somethingChanged == true) {
                    setTimeout(function() {
                        this.regionEventBus.publish("something-changed", "wfinstances");
                    }.bind(this), 2000);
                }
            }
        });
});