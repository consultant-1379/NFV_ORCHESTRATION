/* Copyright (c) Ericsson 2015 */

define("text!widget/WfUsertasks/Selector/Selector.html",function(){return'<div class="ebLayout-Content">\r\n 	<br>\r\n    TODO: selectors/filters\r\n</div>'}),define("widget/WfUsertasks/Selector/SelectorView",["jscore/core","text!./Selector.html"],function(e,t,r){return e.View.extend({getTemplate:function(){return t}})}),define("widget/WfUsertasks/Selector/Selector",["jscore/core","./SelectorView"],function(e,t){return e.Widget.extend({View:t,onViewReady:function(){}})});