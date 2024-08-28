// Workflow diagram

define([
    "jscore/core",
    "3pps/wfs/WfDefinitionClient",
    "3pps/bpmn/Bpmn",
    "3pps/bpmn/Transformer",
    "./WfDiagramView",
    "widgets/Switcher"
    ], function(core, WfDefinitionClient, Bpmn, Transformer, View, Switcher) {

       return core.Widget.extend({

            view: function() {
                return new View({xid: this.options.xid, wfDefinitionId: this.options.wfDefinitionId});
            },
                  
            onViewReady: function() {
            	
            	this.xid = this.options.xid;
                this.workflowName = this.options.wfName;
                this.wfDefinitionId = this.options.wfDefinitionId;
                this.highlights = this.options.highlights;
                this.countBadges = this.options.countBadges;

              	// Get and render start form - note form is optional
                WfDefinitionClient.getWfDiagramXml({
                    wfDefinitionId: this.wfDefinitionId,
                    success: function(wfDiagramXmlModel) {
                    	this.wfDiagramXmlModel = wfDiagramXmlModel;
                        this.showDiagram(wfDiagramXmlModel);
                	}.bind(this),
                    error: function(msg) {
                        console.log("Unable to retrieve diagram for " + this.wfDefinitionId + ". " + msg);
                    }.bind(this)
                });                       
	       },

            showDiagram: function(wfDiagramXmlModel) {
 
                var bpmn20Xml = wfDiagramXmlModel.getAttribute("bpmn20Xml");
                this.diagramContentElement = this.view.getDiagramContent();
                this.trigger("addCalledWorkflows", "addCalledWorkflows");
                try {
                    var semantic = new Transformer().transform(bpmn20Xml);
                    
                    if(this.bpmnDiagram){
	                    this.bpmnDiagram.destroy();
	                    this.bpmnDiagram = null;
                    }
                    
                    this.bpmnDiagram = new Bpmn();
                    this.bpmnDiagram.renderDiagram(semantic, {
                        xid: this.xid,
                        diagramElement : this.xid + "processDiagram_" + this.wfDefinitionId,
                        width : 1700,            // TODO - read from element and set via css
                        height : 575,
                    });
                    this.setHighlights(this.highlights);
                    this.setCountBadges(this.countBadges);
                    
                    //scroll zoom
                    var factor = 1;
                    this.view.getDiagramContent().addEventHandler("mousewheel", function(event){
                    	if (event.originalEvent.wheelDelta >= 0) {
                            this.bpmnDiagram.zoom(factor+=.1);
                        }
                        else {
                            if(factor >= .4){
                            	this.bpmnDiagram.zoom(factor-=.1);
                            }
                        }
                    	
                    }.bind(this));
                    
                   //click and drag to move diagram
                    var startX = 0;
                    var startY = 0;
                    var offsetX = 0;           
                    var offsetY = 0;
                    var dragElement = this.view.getDraggableContent();
                    //this.moveEventId;
                    
                    this.view.getDiagramContent().addEventHandler("mousedown", function(event){
                    	//console.log("mouseClICKED!!!!!!!!!!!!!!!!!!!!");
                    	dragElement = this.view.getDraggableContent(); 
                    	
                    	startX = event.originalEvent.clientX;
                        startY = event.originalEvent.clientY;
                        offsetX = dragElement.element.offsetLeft;
                        offsetY = dragElement.element.offsetTop;
                    	
                    	var moveEventId = this.view.getDiagramContent().addEventHandler("mousemove", function(event){
                        	if(dragElement == null){
                        		//console.log("mouse moving stopped...");
                        		this.view.getDiagramContent().removeEventHandler(moveEventId);
                        		if(leaveEventId != null || leaveEventId != undefined){
                        			this.view.getDiagramContent().removeEventHandler(leaveEventId);
                        		}
                        		//this.trigger("mouseup", "mouseup");
                        	}else{
                        		//console.log("mouse moving");
	                        	dragElement.element.style.left = (offsetX + event.originalEvent.clientX - startX) + 'px';
	                        	dragElement.element.style.top = (/*offsetY + */event.originalEvent.clientY - startY) + 'px';
                    		}	
                    	}.bind(this));
                    	
                    	var leaveEventId = this.view.getDiagramContent().addEventHandler("mouseleave", function(event){
                        	//console.log("mouseOut");
                        	//this.view.getDiagramContent().removeEventHandler(this.moveEventId);
                        	//console.log(this.moveEventId);
                        	dragElement=null;
                        }.bind(this));

                    }.bind(this));
                                       
                    this.getElement().addEventHandler("mouseup", function(event){
                    	//console.log("mouseup==================");
                    	//this.view.getDiagramContent().removeEventHandler(this.moveEventId);
                    	dragElement=null;
                    }.bind(this));
  
                } catch (err) {
                	console.log(err);
                    this.diagramContentElement.setText("Unable to render process diagram: " + err);
                }
            },
            
            

            setHighlights: function(newHighlights) {
                if (newHighlights && newHighlights != null) {
                    // remove existing highlights
                    if (this.highlights && this.highlights != null) {
                        for (var i = 0; i < this.highlights.length; i++) {
                            var highlight = this.highlights[i];
                            this.bpmnDiagram.annotation(highlight.nodeId).removeClasses([highlight.style]);
                        }
                    }

                    // add new highlights
                    for (var i = 0; i < newHighlights.length; i++) {
                        var highlight = newHighlights[i];
                        this.bpmnDiagram.annotation(highlight.nodeId).addClasses([highlight.style]);
                    }
                    this.highlights = newHighlights;
                }
            },

            setCountBadges: function(newCountBadges) {
                if (newCountBadges && newCountBadges != null) {
                    // remove existing badges
                    if (this.countBadges && this.countBadges != null) {
                        for (var i = 0; i < this.countBadges.length; i++) {
                            var countBadge = this.countBadges[i];
                            // TODO - remove div
                        }
                    }

                    // add new badges
                    for (var i = 0; i < newCountBadges.length; i++) {
                        var countBadge = newCountBadges[i];
                        this.bpmnDiagram.annotation(countBadge.nodeId).
                            addDiv('<p class="badge">' + countBadge.count + '</p>', ['badge-position']);
                    }
                    this.countBadges = newCountBadges;
                }
            }
        });
});