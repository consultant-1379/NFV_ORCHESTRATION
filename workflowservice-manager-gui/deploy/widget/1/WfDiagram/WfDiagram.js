/* Copyright (c) Ericsson 2015 */

define("styles!widget/WfDiagram/WfDiagram.less",function(){return'.eaNFE_automation_UI-WfDiagram {\n  white-space: nowrap;\n  overflow: hidden;\n  -webkit-touch-callout: none;\n  -webkit-user-select: none;\n  -khtml-user-select: none;\n  -moz-user-select: none;\n  -ms-user-select: none;\n  user-select: none;\n}\n.eaNFE_automation_UI-WfDiagram-content {\n  width: 100%;\n  height: 575px;\n  margin-bottom: 10px;\n  cursor: -webkit-grab;\n  z-index: 10000;\n  overflow: hidden;\n}\ndiv[class^="def-"],\ndiv[class*=" def-"] {\n  -webkit-touch-callout: none;\n  -webkit-user-select: none;\n  -khtml-user-select: none;\n  -moz-user-select: none;\n  -ms-user-select: none;\n  user-select: none;\n}\n.highlight-Started {\n  -webkit-box-shadow: 0px 0px 4px 4px #ffff00;\n  -moz-box-shadow: 0px 0px 4px 4px #ffff00;\n  box-shadow: 0px 0px 4px 4px #ffff00;\n  border-radius: 5px;\n  border-collapse: separate;\n}\n.highlight-Completed {\n  -webkit-box-shadow: 0px 0px 4px 4px #89ba17;\n  -moz-box-shadow: 0px 0px 4px 4px #89ba17;\n  box-shadow: 0px 0px 4px 4px #89ba17;\n  border-radius: 5px;\n  border-collapse: separate;\n}\n.highlight-Failed {\n  -webkit-box-shadow: 0px 0px 4px 4px #e32119;\n  -moz-box-shadow: 0px 0px 4px 4px #e32119;\n  box-shadow: 0px 0px 4px 4px #e32119;\n  border-radius: 5px;\n  border-collapse: separate;\n}\n.highlight-Created {\n  -webkit-box-shadow: 0px 0px 4px 4px #ffff00;\n  -moz-box-shadow: 0px 0px 4px 4px #ffff00;\n  box-shadow: 0px 0px 4px 4px #ffff00;\n  border-radius: 5px;\n  border-collapse: separate;\n}\n.badge-position {\n  margin-left: -1.1em;\n  margin-top: -0.75em;\n  top: 100%;\n  position: absolute;\n}\n.badge {\n  background-color: #d6d6d6;\n  color: white;\n  border-radius: 2em;\n  display: inline-block;\n  line-height: 1;\n  padding: 3px 5px 2px 5px;\n  text-align: center;\n}\n'}),define("template!widget/WfDiagram/WfDiagram.html",["jscore/handlebars/handlebars"],function(e){return e.template(function(e,n,t,i,a){t=t||e.helpers;var o,s,r="",d="function",l=t.helperMissing,h=void 0,g=this.escapeExpression;return r+='<div class="eaNFE_automation_UI-WfDiagram">\r\n    <div class="eaNFE_automation_UI-WfDiagram-content">\r\n    	<div id="',s=t.xid,o=s||n.xid,typeof o===d?o=o.call(n,{hash:{}}):o===h&&(o=l.call(n,"xid",{hash:{}})),r+=g(o)+"processDiagram_",s=t.wfDefinitionId,o=s||n.wfDefinitionId,typeof o===d?o=o.call(n,{hash:{}}):o===h&&(o=l.call(n,"wfDefinitionId",{hash:{}})),r+=g(o)+'" class ="eaNFE_automation_UI-WfDiagram-draggable"></div>  	\r\n   	</div>\r\n   	<div class="eaNFE_automation_UI-WfDiagram-bottomDivider"></div>\r\n</div>\r\n'})}),define("widget/WfDiagram/WfDiagramView",["jscore/core","template!./WfDiagram.html","styles!./WfDiagram.less"],function(e,n,t){return e.View.extend({getTemplate:function(){return n(this.options)},getStyle:function(){return t},getDiagramContent:function(){return this.getElement().find(".eaNFE_automation_UI-WfDiagram-content")},getDraggableContent:function(){return this.getElement().find(".eaNFE_automation_UI-WfDiagram-draggable")}})}),define("widget/WfDiagram/WfDiagram",["jscore/core","3pps/wfs/WfDefinitionClient","3pps/bpmn/Bpmn","3pps/bpmn/Transformer","./WfDiagramView","widgets/Switcher"],function(e,n,t,i,a,o){return e.Widget.extend({view:function(){return new a({xid:this.options.xid,wfDefinitionId:this.options.wfDefinitionId})},onViewReady:function(){this.xid=this.options.xid,this.workflowName=this.options.wfName,this.wfDefinitionId=this.options.wfDefinitionId,this.highlights=this.options.highlights,this.countBadges=this.options.countBadges,n.getWfDiagramXml({wfDefinitionId:this.wfDefinitionId,success:function(e){this.wfDiagramXmlModel=e,this.showDiagram(e)}.bind(this),error:function(e){console.log("Unable to retrieve diagram for "+this.wfDefinitionId+". "+e)}.bind(this)})},showDiagram:function(e){var n=e.getAttribute("bpmn20Xml");this.diagramContentElement=this.view.getDiagramContent(),this.trigger("addCalledWorkflows","addCalledWorkflows");try{var a=(new i).transform(n);this.bpmnDiagram&&(this.bpmnDiagram.destroy(),this.bpmnDiagram=null),this.bpmnDiagram=new t,this.bpmnDiagram.renderDiagram(a,{xid:this.xid,diagramElement:this.xid+"processDiagram_"+this.wfDefinitionId,width:1700,height:575}),this.setHighlights(this.highlights),this.setCountBadges(this.countBadges);var o=1;this.view.getDiagramContent().addEventHandler("mousewheel",function(e){e.originalEvent.wheelDelta>=0?this.bpmnDiagram.zoom(o+=.1):o>=.4&&this.bpmnDiagram.zoom(o-=.1)}.bind(this));var s=0,r=0,d=0,l=0,h=this.view.getDraggableContent();this.view.getDiagramContent().addEventHandler("mousedown",function(e){h=this.view.getDraggableContent(),s=e.originalEvent.clientX,r=e.originalEvent.clientY,d=h.element.offsetLeft,l=h.element.offsetTop;var n=this.view.getDiagramContent().addEventHandler("mousemove",function(e){null==h?(this.view.getDiagramContent().removeEventHandler(n),(null!=t||void 0!=t)&&this.view.getDiagramContent().removeEventHandler(t)):(h.element.style.left=d+e.originalEvent.clientX-s+"px",h.element.style.top=e.originalEvent.clientY-r+"px")}.bind(this)),t=this.view.getDiagramContent().addEventHandler("mouseleave",function(e){h=null}.bind(this))}.bind(this)),this.getElement().addEventHandler("mouseup",function(e){h=null}.bind(this))}catch(g){console.log(g),this.diagramContentElement.setText("Unable to render process diagram: "+g)}},setHighlights:function(e){if(e&&null!=e){if(this.highlights&&null!=this.highlights)for(var n=0;n<this.highlights.length;n++){var t=this.highlights[n];this.bpmnDiagram.annotation(t.nodeId).removeClasses([t.style])}for(var n=0;n<e.length;n++){var t=e[n];this.bpmnDiagram.annotation(t.nodeId).addClasses([t.style])}this.highlights=e}},setCountBadges:function(e){if(e&&null!=e){if(this.countBadges&&null!=this.countBadges)for(var n=0;n<this.countBadges.length;n++)var t=this.countBadges[n];for(var n=0;n<e.length;n++){var t=e[n];this.bpmnDiagram.annotation(t.nodeId).addDiv('<p class="badge">'+t.count+"</p>",["badge-position"])}this.countBadges=e}}})});