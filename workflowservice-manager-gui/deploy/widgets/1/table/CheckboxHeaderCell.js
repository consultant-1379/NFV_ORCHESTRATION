/* Copyright (c) Ericsson 2014 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
define("styles!widgets/Table/table/CheckboxHeaderCell/_checkboxHeaderCell.less",[],function(){return".elWidgets-TableCheckboxHeaderCell {\n  width: 12px;\n}\n.elWidgets-TableCheckboxHeaderCell-wrap {\n  text-align: center;\n  width: 100%;\n  margin-left: -4px;\n}\n"}),define("text!widgets/Table/table/CheckboxHeaderCell/_checkboxHeaderCell.html",[],function(){return'<th class="elWidgets-TableCheckboxHeaderCell">\n    <div class="elWidgets-TableCheckboxHeaderCell-wrap">\n        <input class="ebCheckbox" type="checkbox" value="1"/>\n        <span class="ebCheckbox-inputStatus"></span>\n    </div>\n</th>'}),define("widgets/Table/table/CheckboxHeaderCell/CheckboxHeaderCellView",["jscore/core","text!./_checkboxHeaderCell.html","styles!./_checkboxHeaderCell.less"],function(e,t,l){"use strict";return e.View.extend({afterRender:function(){this.checkbox=this.getElement().find(".ebCheckbox")},getTemplate:function(){return t},getStyle:function(){return l},getCheckbox:function(){return this.checkbox}})}),define("widgets/Table/table/CheckboxHeaderCell/CheckboxHeaderCell",["widgets/table/Cell","./CheckboxHeaderCellView"],function(e,t){"use strict";function l(){this.view.getCheckbox().setProperty("checked",!1)}function c(){for(var e=this.getTable().getRows(),t=0,l=this.getColumn().getIndex(),c=0;c<e.length;c++)e[c].getCells()[l].isChecked()&&t++;this.view.getCheckbox().setProperty("checked",t===e.length)}return e.extend({View:t,onCellReady:function(){this.getEventBus().subscribe("CheckboxHeaderCell:calculate",c.bind(this)),this.view.getCheckbox()._getHTMLElement().addEventListener("click",function(){var e=this.view.getCheckbox().getProperty("checked");this.check(e)}.bind(this)),this.getEventBus().subscribe("collection:add",l.bind(this)),this.getEventBus().subscribe("collection:reset",l.bind(this)),this.getEventBus().subscribe("collection:remove",c.bind(this))},check:function(e){this.view.getCheckbox().setProperty("checked",e),this.getEventBus().publish("CheckboxCell:check",e)}})}),define("widgets/table/CheckboxHeaderCell",["widgets/Table/table/CheckboxHeaderCell/CheckboxHeaderCell"],function(e){return e});