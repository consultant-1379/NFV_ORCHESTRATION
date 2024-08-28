/* Copyright (c) Ericsson 2014 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
define("text!widgets/ContextMenu/_contextMenu.html",[],function(){return'<div class="ebContextMenu">\n    <div class="ebContextMenu-expandBtn" tabindex="1">\n        <i class="ebIcon ebIcon_medium ebIcon_menu ebIcon_interactive"></i>\n    </div>\n    <div class="ebContextMenu-body ebContextMenu-body_corner_default"></div>\n</div>'}),define("widgets/ContextMenu/ContextMenuView",["jscore/core","text!./_contextMenu.html"],function(t,e){"use strict";var n=t.View.extend({afterRender:function(){this.outerEl=this.getElement().find("."+n.OUTER_CLASS),this.expandBtn=this.getElement().find("."+n.EXPAND_BTN_CLASS),this.dropdown=this.getElement().find("."+n.DROPDOWN_CLASS),this.expandBtnIcon=this.expandBtn.find("."+n.EXPAND_BTN_ICON_CLASS)},getTemplate:function(){return e},setTitle:function(t){this.outerEl.setText(t),this.outerEl.setAttribute("title",t)},getTitle:function(){return this.outerEl.getText()},getOuterEl:function(){return this.outerEl},getExpandBtn:function(){return this.expandBtn},getExpandBtnIcon:function(){return this.expandBtnIcon},getDropdown:function(){return this.dropdown},showDropdown:function(t){this.dropdown.setModifier("visible",""+t)},enableMenu:function(t){t?(this.getExpandBtnIcon().removeModifier("disabled"),this.getExpandBtnIcon().setModifier("interactive")):(this.getExpandBtnIcon().removeModifier("interactive"),this.getExpandBtnIcon().setModifier("disabled"))}},{OUTER_CLASS:"ebContextMenu",EXPAND_BTN_CLASS:"ebContextMenu-expandBtn",EXPAND_BTN_ICON_CLASS:"ebIcon",DROPDOWN_CLASS:"ebContextMenu-body"});return n}),define("widgets/ContextMenu/ContextMenu",["widgets/ItemsControl","widgets/Tooltip","./ContextMenuView"],function(t,e,n){"use strict";return t.extend({View:n,onControlReady:function(){if(this.options.title&&(this.title=this.options.title,this.setTitle(this.title)),this.corner=this.options.corner||"default",this.options.width="auto",this.options.items=this.options.items||[],this.options.actions)for(var t in this.options.actions)this.options.actions.hasOwnProperty(t)&&this.options.items.push({name:t,action:this.options.actions[t]});this.setItems(this.options.items||[])},onListShow:function(){this.setCorner(this.corner)},onEnable:function(){this.view.enableMenu(!0)},onDisable:function(){this.view.enableMenu(!1)},setCorner:function(t){if(this.view.getDropdown().setModifier("corner",t),this.componentList){var e=0,n=0;("bottomLeft"===t||"bottomRight"===t)&&(e=-this.componentList.getElement().getProperty("offsetHeight")-this.getElement().getProperty("offsetHeight")-12),("bottomRight"===t||"topRight"===t)&&(n=-this.componentList.getElement().getProperty("offsetWidth")+this.getElement().getProperty("offsetWidth")),this.componentList.setPositionOffsets({top:e,left:n})}},setTitle:function(t){this.title=t,this.tooltip?this.tooltip.setContentText(this.title):(this.tooltip=new e({parent:this.view.getExpandBtn(),contentText:this.title,enabled:!0}),this.tooltip.attachTo(this.getElement())),""===t&&this.tooltip&&(this.tooltip.destroy(),delete this.tooltip)},getTitle:function(){return this.tooltip?this.title:""},onItemSelected:function(t){t.action()},setActions:function(t){var e=Object.keys(t),n=[];e.forEach(function(e){var i=t[e];n.push({name:e,action:i})},this),this.setItems(n)}})}),define("widgets/ContextMenu",["widgets/ContextMenu/ContextMenu"],function(t){return t});