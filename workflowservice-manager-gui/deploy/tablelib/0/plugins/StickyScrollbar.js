/* Copyright (c) Ericsson 2014 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
define("styles!tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidget.less",[],function(){return".elTablelib-StickyScrollbarWidget {\n  position: fixed;\n  overflow-x: scroll;\n  bottom: 0;\n}\n.elTablelib-StickyScrollbarWidget-fakeContent {\n  height: 1px;\n}\n"}),define("text!tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidget.html",[],function(){return'<div class="elTablelib-StickyScrollbarWidget eb_scrollbar">\n    <div class="elTablelib-StickyScrollbarWidget-fakeContent"></div>\n</div>'}),define("tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidgetView",["jscore/core","text!./StickyScrollbarWidget.html","styles!./StickyScrollbarWidget.less"],function(t,e,i){return t.View.extend({getTemplate:function(){return e},getStyle:function(){return i},getFakeContent:function(){return this.getElement().find(".elTablelib-StickyScrollbarWidget-fakeContent")}})}),define("tablelib/plugins/stickyscrollbar/stickyscrollbarwidget/StickyScrollbarWidget",["jscore/core","./StickyScrollbarWidgetView"],function(t,e){return t.Widget.extend({View:e,onDOMAttach:function(){this.tg=this.options.element,this.el=this.getElement(),this.el.addEventHandler("scroll",function(){this.tg.setProperty("scrollLeft",this.el.getProperty("scrollLeft"))}.bind(this)),this.tg.addEventHandler("scroll",function(){this.el.setProperty("scrollLeft",this.tg.getProperty("scrollLeft"))}.bind(this)),requestAnimationFrame(this.update.bind(this))},update:function(){var t=this.tg.getPosition(),e=this.el.getPosition();t.bottom>window.innerHeight&&this.tg.getProperty("scrollWidth")>this.el.getProperty("offsetWidth")?("visible"!==this.el.getStyle("visibility")&&this.el.setStyle({visibility:"visible","pointer-events":"auto"}),e.left!==t.left&&this.el.setStyle("left",t.left+"px"),this.el.getProperty("offsetWidth")!==this.tg.getProperty("offsetWidth")&&this.el.setStyle("width",this.tg.getProperty("offsetWidth")+"px"),this.view.getFakeContent().getProperty("offsetWidth")!==this.tg.getProperty("scrollWidth")&&this.view.getFakeContent().setStyle("width",this.tg.getProperty("scrollWidth")+"px")):"visible"===this.el.getStyle("visibility")&&this.el.setStyle({visibility:"hidden","pointer-events":"none"}),requestAnimationFrame(this.update.bind(this))}})}),define("tablelib/plugins/Plugin",["jscore/core"],function(t){var e=function(t){this.options=t||{},this.init.apply(this,arguments)};return e.prototype.init=function(){},e.prototype.getOptions=function(){return this.options},e.prototype.getTable=function(){return this._table},e.prototype.injections={before:{},after:{},newMethods:{}},e.extend=t.extend,e}),define("tablelib/plugins/stickyscrollbar/StickyScrollbar",["../Plugin","./stickyscrollbarwidget/StickyScrollbarWidget"],function(t,e){function i(){var t=(this.getTable(),new e({element:this.getTable().getElement().find(".eb_scrollbar")}));t.attachTo(this.getTable().getElement())}return t.extend({injections:{before:{onViewReady:i}}})}),define("tablelib/plugins/StickyScrollbar",["tablelib/plugins/stickyscrollbar/StickyScrollbar"],function(t){return t});