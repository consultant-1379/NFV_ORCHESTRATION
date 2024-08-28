/* Copyright (c) Ericsson 2015 */

define("styles!widget/WfComment/WfComment.less",function(){return".eaNFE_automation_UI-WfComment-comments {\n  max-height: 200px;\n  min-height: 200px;\n}\n.eaNFE_automation_UI-WfComment-CreateWfComment-buttonHolder {\n  margin-top: 10px;\n}\n.eaNFE_automation_UI-WfComment-CreateWfComment-textarea {\n  margin-top: 10px;\n  width: 250px;\n}\n.eaNFE_automation_UI-WfComment-Divider {\n  border-bottom: #cccccc solid 1px;\n  padding-bottom: 15px;\n}\n.elLayouts-SlidingPanels-rightContents {\n  padding-top: 5px ! important;\n}\n"}),define("template!widget/WfComment/WfComment.html",["jscore/handlebars/handlebars"],function(t){return t.template(function(t,e,n,o,i){n=n||t.helpers;return'<div>\r\n<div class="eaNFE_automation_UI-WfComment"></div>\r\n	<div class="ebLayout-SectionHeading">\r\n    	<h2 class="eaNFE_automation_UI-WfComment-header">Comments</h2>\r\n    	<div class="ebLayout-HeadingCommands">\r\n    		<div class="ebLayout-HeadingCommands-block">\r\n      			<div class="ebLayout-HeadingCommands-iconHolder">\r\n        			<i class="eaNFE_automation_UI-WfComment-closeButton ebIcon ebIcon_close ebIcon_interactive" title="Close Comments"></i>\r\n      			</div>\r\n    		</div>\r\n  		</div>\r\n  		<div class="ebLayout-clearFix"></div>\r\n	</div>\r\n    \r\n    <br />\r\n	<div class="eaNFE_automation_UI-WfComment-CreateWfCommentForm-ButtonsToggle">\r\n	 	<div class="eb_scrollbar eaNFE_automation_UI-WfComment-comments"></div>\r\n	 	<br />\r\n		<div class="eaNFE_automation_UI-WfComment-CreateWfComment">\r\n		 		<textarea rows="4" class="ebTextArea eaNFE_automation_UI-WfComment-CreateWfComment-textarea" placeholder="Add Comment Here.."></textarea>\r\n		 		<div class="eaNFE_automation_UI-WfComment-CreateWfComment-buttonHolder">\r\n		        	<button type="button" class="ebBtn eaNFE_automation_UI-WfComment-CreateWfComment-buttonSubmit" title="Add Comment" disabled>Add Comment</button>\r\n		        </div>\r\n	 	</div>\r\n 	</div>\r\n 	<div class="eaNFE_automation_UI-WfComment-Divider"></div>\r\n</div>'})}),define("widget/WfComment/WfCommentView",["jscore/core","template!./WfComment.html","styles!./WfComment.less"],function(t,e,n){return t.View.extend({getTemplate:function(){return e(this.options)},getStyle:function(){return n},getComment:function(){return this.getElement().find(".eaNFE_automation_UI-WfComment-CreateWfComment-textarea")},getComments:function(){return this.getElement().find(".eaNFE_automation_UI-WfComment-comments")},getSubmitButton:function(){return this.getElement().find(".eaNFE_automation_UI-WfComment-CreateWfComment-buttonSubmit")},getCloseButton:function(){return this.getElement().find(".eaNFE_automation_UI-WfComment-closeButton")},getAddCommentSection:function(){return this.getElement().find(".eaNFE_automation_UI-WfComment-CreateWfComment-buttonHolder")}})}),define("widget/WfComment/WfComment",["jscore/core","jscore/ext/mvp","3pps/wfs/WfUsertaskClient","../ErrorDialog/ErrorDialog","./WfCommentView","./UserComment/UserComment","widgets/Notification"],function(t,e,n,o,i,m,a){return t.Widget.extend({View:i,onViewReady:function(){this.taskId=this.options.taskId,this.shortWorkflowInstanceId=this.options.shortWorkflowInstanceId,this.eventBus=this.options.eventBus,this.submitButton=this.view.getSubmitButton(),this.submitButton.addEventHandler("click",function(){this.handleSubmit()}.bind(this)),this.view.getCloseButton().addEventHandler("click",function(){this.eventBus.publish("toggleCommentSection","toggleComments")}.bind(this));var t=this.view.getComment();t.addEventHandler("keyup",function(){null!=t.element.value&&""!=t.element.value?(this.submitButton.element.classList.add("ebBtn_color_darkBlue"),this.submitButton.element.disabled=!1):(this.submitButton.element.classList.remove("ebBtn_color_darkBlue"),this.submitButton.element.disabled=!0)}.bind(this)),n.getUsertasksComments({taskId:this.taskId,success:function(t){0==t._collection.models.length&&this.view.getComments().setText("No Comments have been added to this process..."),t.each(function(t){var e=new m({userComment:t});e.attachTo(this.view.getComments())}.bind(this))}.bind(this)})},handleSubmit:function(t){var e=this.view.getComment().element.value;if(e.indexOf("[")>-1||e.indexOf("]")>-1){var o=new a({label:'"[]" not allowed',icon:"error",color:"red",showCloseButton:!0,autoDismiss:!0,autoDismissDuration:3e3});o.attachTo(this.view.getAddCommentSection())}else n.addUsertaskCommentById({id:this.shortWorkflowInstanceId,taskId:this.taskId,userId:"ejamcudHC1234",createdTime:null,comment:e,success:function(){this.trigger("toggleContentDrilldown","toggledrilldown")}.bind(this),error:function(t){this.handleError("Unable to add comment for task: "+this.taskId,[t])}.bind(this)})},handleError:function(t,e){var n=" ";if(null!=e&&e.length>0)for(var i=0;i<e.length;i++)n+=e[i]+" ";new o({title:t,message:n}),this.view.getButtonsToggle().setStyle("display","none"),this.trigger("toggleContentDrilldown","toggledrilldown")}})});