define([
    "jscore/core",
    "template!./TaskFormOnDrilldown.html",
    "styles!./TaskFormOnDrilldown.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template(this.options);
            },

            getStyle: function() {
                return style;
            },

            getFormContent: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskFormOnDrilldown-Panel-FormContent");
            },
            
            getSubmitButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskFormOnDrilldown-buttonSubmit");
            },
            
            getCancelButton: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskFormOnDrilldown-buttonCancel");
            },

            getTaskNameHandleBar: function () {
                return this.getElement().find(".eaNFE_automation_UI-TaskFormOnDrilldown-Taskheading");
            },
            
            getUserActivityContent: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-home-UserActivity");
			},
			
			getUserActivityContent2: function() {
				return this.getElement().find(".eaWorkflowInstanceN-MainRegion-contentWfInstanceN-home-UserActivity2");
			},
			
			getButtonsToggle : function(){
				return this.getElement().find(".eaNFE_automation_UI-TaskFormOnDrilldown-ButtonsToggle");
			},
			
			getContentToggle: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfForm-contentToggle");
            }
            
        });

    });