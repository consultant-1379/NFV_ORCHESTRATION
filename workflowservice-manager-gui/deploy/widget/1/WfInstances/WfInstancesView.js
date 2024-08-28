/* Copyright (c) Ericsson 2015 */

define("styles!widget/WfInstances/WfInstances.less",function(){return".eaNFE_automation_UI-WfInstances {\n  min-height: 288px;\n  width: 100%;\n  padding-top: 8px;\n}\n.eaNFE_automation_UI-WfInstances-SectionHeading-Title {\n  float: left;\n}\n.eaNFE_automation_UI-WfInstances-SectionHeading-Extra {\n  padding-top: 10px;\n  padding-bottom: 10px;\n}\n.eaNFE_automation_UI-WfInstances-SectionHeading-Filter {\n  padding-top: 10px;\n  float: right;\n}\n.eaNFE_automation_UI-WfInstances-contentTable {\n  min-height: auto;\n  max-height: 770px;\n  overflow: auto;\n  width: 100%;\n}\n.eaNFE_automation_UI-WfInstances-contentTable-buttonRefresh {\n  float: left;\n  vertical-align: bottom;\n  margin-left: 15px;\n  margin-top: 10px;\n  padding-top: 5px;\n  cursor: pointer;\n  cursor: hand;\n}\n.eaNFE_automation_UI-WfInstances-contentTable-BottomDivider {\n  border-bottom: 1px solid #666666;\n  width: 100%;\n  padding-bottom: 5px;\n}\n.eaNFE_automation_UI-WfInstances .ebNotification {\n  margin: 0;\n  position: absolute;\n  top: 50%;\n  left: 50%;\n  margin-right: -50%;\n  transform: translate(-50%, -50%);\n}\n"}),define("text!widget/WfInstances/WfInstances.html",function(){return'<div class="eaNFE_automation_UI-WfInstances">\r\n	<div\r\n		class="ebLayout-SectionHeading eaNFE_automation_UI-WfInstances-SectionHeading">\r\n		<div class="eaNFE_automation_UI-WfInstances-SectionHeading-Title">\r\n			<h2>Workflow Instances</h2>\r\n			<div\r\n				class="eaNFE_automation_UI-WfInstances-contentTable-buttonRefresh">\r\n				<i class="ebIcon ebIcon_refresh"></i>\r\n			</div>\r\n		</div>\r\n	</div>\r\n	<div class="eaNFE_automation_UI-WfInstances-contentTable eb_scrollbar"></div>\r\n	<div class="eaNFE_automation_UI-WfInstances-contentTable-BottomDivider"></div>\r\n	<div class="eaNFE_automation_UI-WfInstances-contentTablePagination"></div>\r\n	<div class="eaNFE_automation_UI-Usertasks-contentTaskForm"></div>\r\n</div>\r\n'}),define("widget/WfInstances/WfInstancesView",["jscore/core","text!./WfInstances.html","styles!./WfInstances.less"],function(n,t,e){return n.View.extend({getTemplate:function(){return t},getStyle:function(){return e},getSectionHeadingFilter:function(){return this.getElement().find(".eaNFE_automation_UI-WfInstances-SectionHeading-Filter")},getTableContent:function(){return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentTable")},getPagination:function(){return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentTable")},getRefreshButton:function(){return this.getElement().find(".eaNFE_automation_UI-WfInstances-contentTable-buttonRefresh")}})});