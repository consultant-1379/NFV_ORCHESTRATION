define([
        'jscore/core',
        "jscore/ext/mvp",
        './WorkflowInstanceNRegionView',
        "3pps/wfs/WfInstanceClient",
        "3pps/wfs/WfCountsClient",
        'widget/WfUsertasks/TaskFormOnDrilldown/TaskFormOnDrilldown',
        'widget/WfDetails/WfDetails',
        'widget/WfDiagram/WfDiagram',
        "widget/WfProgress/WfProgressModel",
        "jscore/ext/locationController"
        ], function (core,mvp, View, WfInstanceClient, WfCountsClient, TaskFormOnDrilldown, WfDetails, WfDiagram, WfProgressModel, LocationController) {

	return core.Region.extend({

		View: View,

		onStart: function (parent) {   
			this.updateURLParams();
			this.showDiagram(this.params);
		},

		onAttach: function (parent) {   
			this.updateURLParams();
			this.showDiagram(this.params);
		},

		init: function(){
			this.updateURLParams();
		},

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

		taskHandledCallback: function(somethingChanged) {
			this.options.regionEventBus.eventBus.publish("hideForm", "hideForm");
			if(somethingChanged == true) {
				setTimeout(function() {
					this.options.regionEventBus.eventBus.publish("something-changed", "usertasks");
				}.bind(this), 2000);
			}

			var locationController = new LocationController();
			var url = 'workflowsinstances/' + this.params.id + "/" + this.params.name;                        	                	              	
			locationController.setLocation(url , false);
			locationController.start();
		},

		refresh: function(){
			this.options.regionEventBus.eventBus.publish("hideForm", "hideForm");
			this.redrawDiagram({'id':this.activeWorkflowDefinitionId,
				'shortWorkflowInstanceId':this.activeWorkflowInstanceId,
				'name':this.workflowName});
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

		addCalledWorkflows: function() {		        	
			this.view.getWfInstanceNProgress().element.innerHTML = "";
			var calledElement;


			WfCountsClient.getAncestry({					
				processInstanceId: this.activeWorkflowInstanceId,
				success: function(wfInstanceCollection) {

					var html = "<table><tr>";
					wfInstanceCollection.each(function(models) { 
						if(this.activeWorkflowInstanceId == models.getAttribute("id")){
							html += "<td class='eaWorkflowInstanceN-MainRegion-contentWfInstanceN-progress'>";
							html += "<a href='javascript:void(0)' class='ebBreadcrumbs-link-current' title='" + models.getAttribute("workflowDefinitionName").replace("\r\n", " ") + "' onclick=\"prgdiagRenderCallActProgress(\'" + models.getAttribute("id") + "\','" + models.getAttribute("workflowDefinitionId") + "\','" + models.getAttribute("workflowDefinitionName").replace("\r\n", " ") + "\')\">";
							html += models.getAttribute("workflowDefinitionName");
							html += "</a>";
							html += "</td>";  
							html += "<td class='eaWorkflowInstanceN-MainRegion-contentWfInstanceN-progress-icon'></td>";
						}else{
							html += "<td class='eaWorkflowInstanceN-MainRegion-contentWfInstanceN-progress'>";
							html += "<a href='javascript:void(0)' class='ebBreadcrumbs-link-new' title='" + models.getAttribute("workflowDefinitionName").replace("\r\n", " ") + "' onclick=\"prgdiagRenderCallActProgress(\'" + models.getAttribute("id") + "\','" + models.getAttribute("workflowDefinitionId") + "\','" + models.getAttribute("workflowDefinitionName").replace("\r\n", " ") + "\')\">";
							html += models.getAttribute("workflowDefinitionName");
							html += "</a>";
							html += "</td>";  
							html += "<td class='eaWorkflowInstanceN-MainRegion-contentWfInstanceN-progress-icon'></td>";
						}						
					}.bind(this));	

					//removes last progress icon if no more children
					if(this.calledWorkflows.length == 0){
						html = html.substring(0,html.length - 81);
					}

					for (var i = 0; i < this.calledWorkflows.length; i++) {
						var called = this.calledWorkflows[i];					
						html+= "<td class='eaWorkflowInstanceN-MainRegion-contentWfInstanceN-progress'>";
						html += "<a href='javascript:void(0)' class='ebBreadcrumbs-link-new' title='" + called.activityName.replace("\r\n", " ") + "' onclick=\"prgdiagRenderCallActProgress(\'" + called.childExecutionId + "\','" + called.childDefinitionId + "\','" + called.activityName.replace("\r\n", " ") + "\')\">";
						html += called.activityName;
						html += "</a>";
						html += "</td>";

						if(i != this.calledWorkflows.length -1){
							html += "<td class='eaWorkflowInstanceN-MainRegion-contentWfInstanceN-progress-icon'></td>";
						}
					}

					html += "</tr></table>";
					calledElement = core.Element.parse(html);
					var calledContent = this.view.getWfInstanceNProgress();
					calledContent.append(calledElement);
				}.bind(this)
			});

			prgdiagRenderCallActProgress = function(childExecutionId, childDefinitionId, activityName) { 
				this.activeWorkflowDefinitionId = childDefinitionId; 
				this.activeWorkflowInstanceId = childExecutionId;
				this.workflowName = activityName;
				this.view.getWfInstanceNProgress().children()[0].remove();
				this.redrawDiagram({'id':childDefinitionId,'shortWorkflowInstanceId':childExecutionId,'name':activityName});

			}.bind(this);

		},

		redrawDiagram: function(inlineParams) {
			if (this.hasOwnProperty('bpmnDiagram')) {
				this.bpmnDiagram.destroy();
				delete this.bpmnDiagram;
				this.showDiagram(inlineParams);
			}
		}, 

		zoomIn: function(){
			this.bpmnDiagram.bpmnDiagram.zoom(this.factor += .1);
		},

		zoomOut: function(){
			if(this.factor >= .4){
				this.bpmnDiagram.bpmnDiagram.zoom(this.factor -= .1);
			}
		},

		showDiagram: function(params) {	
			if(this.bpmnDiagram){
				this.bpmnDiagram.detach();
				this.bpmnDiagram.destroy();
				this.bpmnDiagram = null;
			}      			
			this.activeWorkflowDefinitionId = params.id;
			this.activeWorkflowInstanceId = params.shortWorkflowInstanceId;

			// Build progress models
			WfProgressModel.build({
				workflowDefinitionId: params.id, 
				workflowInstanceId: params.shortWorkflowInstanceId,
				success: function(progressModels) {
					this.wfStructureModel = progressModels.wfStructureModel;
					this.wfProgressModel = progressModels.wfProgressModel;
					this.keyableStatus = progressModels.keyableStatus;
					this.keyableCount = progressModels.keyableCount;
					this.percentComplete = progressModels.percentComplete;
					this.calledWorkflows = progressModels.calledWorkflows;

					var overlays = this.buildOverlays(this.keyableStatus, this.keyableCount);

					this.options.regionEventBus.eventBus.publish("updatePercentage" , this.percentComplete);
					 
					var formattedTimestamp;
					try{
						var offset = new Date().getTimezoneOffset() * 60000;
						var wfTimestamp = new Date(this.wfProgressModel.attributes.nodeProgresses[0].events[0].time + offset);		
						formattedTimestamp = wfTimestamp.toISOString().split(".")[0];
					}catch(err){
						formattedTimestamp = this.wfProgressModel.attributes.nodeProgresses[0].events[0].time;
					}
					this.options.regionEventBus.eventBus.publish("updateTimestamp" , formattedTimestamp);

					if(!this.bpmnDiagram){
						this.bpmnDiagram = new WfDiagram({  xid: "prg-",
							wfDefinitionId: params.id,
							wfName: params.name,
							highlights: overlays.highlights,
							countBadges: overlays.countBadges });
						this.bpmnDiagram.addEventHandler('addCalledWorkflows', this.addCalledWorkflows.bind(this));
						this.bpmnDiagram.attachTo(this.view.getContentDiagram());
						this.options.regionEventBus.eventBus.publish("updateSidebars", params.shortWorkflowInstanceId);                
					}

					if(this.wfDetails){
						this.wfDetails.detach();
						this.wfDetails.destroy();
					}   
					this.wfDetails = new WfDetails({
//						regionEventBus: this.options.regionEventBus.eventBus
					});
					this.factor = 1;
					this.wfDetails.addEventHandler('zoomOut', this.zoomOut.bind(this));
					this.wfDetails.addEventHandler('zoomIn', this.zoomIn.bind(this));
					this.wfDetails.attachTo(this.view.getContentDetails());
					this.wfDetails.addEventHandler('refreshProgress', this.refresh.bind(this)); 	
				}.bind(this),
				error: function(err) {
					console.log(err);       // TODO - recommended UI SDK logging ?
				}.bind(this)
			});
		}

	});

});
