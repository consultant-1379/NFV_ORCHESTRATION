/* Copyright (c) Ericsson 2015 */

define("styles!widget/linkCell/linkCell.less",function(){return".LinkCell-link {\n  color: #0066B3;\n  background-color: transparent;\n  display: block;\n  text-decoration: none;\n  cursor: pointer;\n}\n.LinkCell-link:hover {\n  text-decoration: underline;\n}\n"}),define("text!widget/linkCell/linkCell.html",function(){return'<td>\r\n    <div class="LinkCell">\r\n        <span class="LinkCell-link"></span>\r\n    </div>\r\n</td>'}),define("widget/linkCell/LinkCellView",["jscore/core","text!./linkCell.html","styles!./linkCell.less"],function(e,n,t){return e.View.extend({getTemplate:function(){return n},getStyle:function(){return t},getRoot:function(){return this.getElement().find(".LinkCell")},getLink:function(){return this.getElement().find(".LinkCell-link")}})}),define("widget/linkCell/LinkCell",["widgets/table/Cell","./LinkCellView","jscore/ext/locationController"],function(e,n,t){return e.extend({View:n,onCellReady:function(){var e=this.view.getLink();e.addEventHandler("click",function(){var e=new t,n=(this.value,"workflowsinstances/"+this.options.model.getAttribute("id")+"/"+this.options.model.getAttribute("name"));e.setLocation(n)}.bind(this))},setValue:function(e){this.value=e,this.view.getLink().setText(e)}})});