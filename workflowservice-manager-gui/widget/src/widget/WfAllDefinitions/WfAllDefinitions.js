// Workflow definitions widget

define([
    "jscore/core",
    "./WfAllDefinitionsTable/WfAllDefinitionsTable",
    "./CreateWfInstanceForm/CreateWfInstanceForm",
    "../WfDiagram/WfDiagram",
    "./WfAllDefinitionsView"
    ], function(core, WfAllDefinitionsTable, CreateWfInstanceForm, WfDiagram, View) {

       return core.Widget.extend({

            View: View,

            onViewReady: function() {
            	this.regionEventBus = this.options.regionContext.eventBus;

//                this.regionEventBus.subscribe("something-changed", function (whatChanged) {
//                    // nothing to do for now
//                }.bind(this));

                this.currentFilterShowAll = false;

//                 this.view.getRefreshButton().addEventHandler("click", function() {
//                    this.refreshButtonClicked();
//                }.bind(this));
                            
                this.CreateWfInstanceForm = null;
                this.showTable();
            },
            
//            refreshButtonClicked: function() {
//            	if(this.wfAllDefinitions){
//            		this.wfAllDefinitions.detach();
//            	}
//            	
//                this.regionEventBus.publish("removeDefinitionTable", "removeTable");
//            },
            
            showTable: function() {
                this.hideTable();

                // Build and display table
                this.WfAllDefinitionsTable = new WfAllDefinitionsTable({
                	showAllVersions: this.currentFilterShowAll, 
                	showActionButtons: this.options.showActionButtons,
                	regionEventBus: this.options.regionContext.eventBus
                	});
                
                this.WfAllDefinitionsTable.attachTo(this.view.getTableContent());
                if (this.tableContentHeight) {
                    this.view.getTableContent().setStyle("height", this.tableContentHeight);
                }

                // Add handler for 'create instance' table action
                this.WfAllDefinitionsTable.options.regionEventBus.subscribe("createinstance", function(wfDefinitionModel) {
                    this.createInstance(wfDefinitionModel);
                }.bind(this));

                // Add handler for 'show diagram' table action
//                this.WfAllDefinitionsTable.getEventBus().subscribe("showdiagram", function(wfDefinitionModel) {
//                    this.bpmnDiagram = new WfDiagram({  xid: "def-",
//                                                        wfDefinitionId: wfDefinitionModel.getAttribute("id"),
//                                                        wfName: wfDefinitionModel.getAttribute("name") });
//                    this.regionEventBus.publish("display-element", ["DefinitionDiagram", this.bpmnDiagram]);
//                }.bind(this));
            },

            hideTable: function() {
                if (this.WfAllDefinitionsTable != null) {
                    this.tableContentHeight = this.view.getTableContent().getStyle("height");
                    this.WfAllDefinitionsTable.destroy();
                    this.WfAllDefinitionsTable = null;
                    this.view.getTableContent().setStyle("height", "0px");
                }
            },

            showCreateInstanceForm: function(wfDefinitionModel) {
                this.hideCreateInstanceForm();
                // Build and show 'create instance' form
                this.CreateWfInstanceForm = new CreateWfInstanceForm({minHeight: this.tableContentHeight, wfDefinitionModel: wfDefinitionModel, createHandledCallback:this.createHandledCallback.bind(this), regionEventBus: this.options.regionContext.eventBus});
                this.CreateWfInstanceForm.attachTo(this.view.getFormContent());
            },

            hideCreateInstanceForm: function() {
                if (this.CreateWfInstanceForm != null) {
                    this.CreateWfInstanceForm.destroy();
                }
                this.CreateWfInstanceForm = null;
            },

            createInstance: function(wfDefinitionModel) {
                this.hideTable();
                this.showCreateInstanceForm(wfDefinitionModel);
            },

            createHandledCallback: function(somethingChanged) {
                this.hideCreateInstanceForm();
                if(this.bpmnDiagram) this.bpmnDiagram.destroy();
                this.options.regionContext.eventBus.publish("removeDefinitionTable", "removeTable");
                if (somethingChanged == true) {
                    setTimeout(function() {
                    	this.options.regionContext.eventBus.publish("something-changed", "wfinstances");
                    }.bind(this), 2000);
                }
            }
        });
});