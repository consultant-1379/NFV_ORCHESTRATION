/* Copyright (c) Ericsson 2014 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
define("styles!widgets/Table/_table.less",[],function(){return".elWidgets-Table {\n  position: relative;\n  width: 100%;\n  height: 100%;\n  overflow-y: hidden;\n  overflow-x: auto;\n  -webkit-overflow-scrolling: auto;\n}\n.elWidgets-Table-responsiveWrapper,\n.elWidgets-Table-body {\n  width: 100%;\n}\n"}),define("text!widgets/Table/_table.html",[],function(){return'<div class="elWidgets-Table eb_scrollbar">\n    <div class="elWidgets-Table-responsiveWrapper">\n        <table class="ebTable elWidgets-Table-body">\n            <colgroup class="ebTable-columns"></colgroup>\n        </table>\n    </div>\n</div>'}),define("widgets/Table/TableView",["jscore/core","text!./_table.html","styles!./_table.less"],function(e,t,i){"use strict";var s=e.View.extend({afterRender:function(){var e=this.getElement();this.table=e.find("."+s.EL_TABLE_CLASS),this.columnsEl=e.find("."+s.EL_COLUMNS_CLASS),this.responsiveWrapper=e.find("."+s.EL_RESPONSIVE_CLASS)},getTemplate:function(){return t},getStyle:function(){return i},getBody:function(){return this.table},getResponsiveWrapper:function(){return this.responsiveWrapper},getColumns:function(){return this.columnsEl}},{EL_TABLE_CLASS:"ebTable",EL_COLUMNS_CLASS:"ebTable-columns",EL_RESPONSIVE_CLASS:"elWidgets-Table-responsiveWrapper"});return s}),define("widgets/Table/Table",["jscore/core","widgets/WidgetCore","jscore/ext/mvp","./TableView","widgets/table/Column","widgets/table/Row","widgets/table/HeaderCell","widgets/table/Cell","widgets/table/HeaderRow","widgets/table/Sortable"],function(e,t,i,s,n,o,l,h,r,a){"use strict";return t.extend({View:s,rowType:o,headerRowType:r,cellType:h,headerCellType:l,onTableReady:function(){},init:function(t,s){this.prepareOptions(),this._eventBus=new e.EventBus,this.rowType=this.options.rowType,this.headerRowType=this.options.headerRowType,this.cellType=this.options.cellType,this.headerCellType=this.options.headerCellType,this._rows=[],this._modelRowBindings={},this._events={},this._columns=this.initializeColumns(this.options.columns),this._headerRow=this.initializeHeaderRow(this._columns),this._data=s instanceof i.Collection?s:new i.Collection(s),this._previousSelectedRow=void 0,this._selectedRows=[],this.setupEvents()},prepareOptions:function(){var e={};for(var t in this)e[t]=this[t];for(var i in this.options)this.options.hasOwnProperty(i)&&(e[i]=this.options[i]);this.options=e},setupEvents:function(){this.getEventBus().subscribe("rowdestroy",function(e){this._rows.splice(this._rows.indexOf(e),1)},this),this.getEventBus().subscribe("checkboxRowSelected",function(e){this.trigger("rowselect",e,e.getData(),e.isHighlighted())},this)},onViewReady:function(){if(this._columns.forEach(function(e){e.attachTo(this.view.getColumns())}.bind(this)),this.options.noHeader||this._headerRow.attachTo(this.view.getBody()),this.setData(this._data),this.options.modifiers)for(var e=0;e<this.options.modifiers.length;e++){var t=this.options.modifiers[e];"string"==typeof t?this.view.getBody().setModifier(this.options.modifiers[e]):this.view.getBody().setModifier(t.name,t.value)}this.options.minWidth&&this.view.getResponsiveWrapper().setStyle("min-width",this.options.minWidth),this.options.maxWidth&&this.view.getResponsiveWrapper().setStyle("max-width",this.options.maxWidth),this.options.width&&this.view.getResponsiveWrapper().setStyle("width",this.options.width),this.onTableReady()},addData:function(e){this._data.addModel(e)},addRow:function(e){var t=new this.rowType({data:e,eventBus:this._eventBus,table:this});this._columns.forEach(function(i){var s=i.getAttribute(),n=i.getDefinition().cellType||this.cellType,o=new n({row:t,column:i,eventBus:this._eventBus,model:e,attribute:s,table:this});s&&(void 0===t.tableCellBindEvents&&(t.tableCellBindEvents={}),t.tableCellBindEvents[s]&&e.removeEventHandler("change:"+s,t.tableCellBindEvents[s]),t.tableCellBindEvents[s]=e.addEventHandler("change:"+s,function(e){o.setValue(e.getAttribute(s))}),this.options.tooltips&&o.setTooltip(e.getAttribute(i.getAttribute()))),t.attachCell(o)}.bind(this));var i=t.view.getBody?t.view.getBody():t.getElement().find("tr");i.addEventHandler("click",function(){this.trigger("rowclick",t,e)}.bind(this)),this.options.selectableRows&&this.applySelectableHandlers(t),this._modelRowBindings[e.cid]=t,t.attachTo(this.view.getBody()),this._rows.push(t)},getRowIndex:function(e){return this.getRows().indexOf(e)},applySelectableHandlers:function(e){var t=e.view.getBody?e.view.getBody():e.getElement().find("tr");t.addEventHandler("mousedown",function(e){e.originalEvent.shiftKey&&e.preventDefault()}),t.addEventHandler("click",function(t){if(t.originalEvent.shiftKey&&this._previousSelectedRow&&this._previousSelectedRow!==e)for(var i=this.getRowIndex(this._previousSelectedRow),s=this.getRowIndex(e),n=s>i?i:s,o=i>s?i:s,l=0;l<this.getRows().length;l++)this.selectRow(this.getRows()[l],l>=n&&o>=l);else{if(e.isHighlighted()||t.originalEvent.ctrlKey)this.selectRow(e,!e.isHighlighted());else{for(var h=0;h<this._selectedRows.length;h++)this._selectedRows[h]!==e&&this.selectRow(this._selectedRows[h],!1,!1);this._selectedRows=[],this.selectRow(e,!e.isHighlighted())}this._previousSelectedRow=e.isHighlighted()?e:void 0}},this)},selectRow:function(e,t,i){i=!!i,e.highlight(t),this.trigger("rowselect",e,e.getData(),e.isHighlighted()),e.isHighlighted()&&-1===this._selectedRows.indexOf(e)?this._selectedRows.push(e):!e.isHighlighted()&&-1!==this._selectedRows.indexOf(e)&&i&&this._selectedRows.splice(this._selectedRows.indexOf(e),1)},setData:function(e){this._selectedRows=[],e&&(this.clear(),this.getEventBus().publish("collection:reset"),e=e instanceof i.Collection?e:new i.Collection(e),this._data=e,e.each(function(e){this.addRow(e)}.bind(this)),this._events.add=this._data.addEventHandler("add",function(e){this.addRow(e),this.getEventBus().publish("collection:add",e)},this),this._events.remove=this._data.addEventHandler("remove",function(e){this.removeRow(e),this.getEventBus().publish("collection:remove",e)},this),this._events.reset=this._data.addEventHandler("reset",function(){this.setData(this._data),this.getEventBus().publish("collection:reset")}.bind(this)),this._events.sort=this._data.addEventHandler("sort",function(){this.sortRender()},this))},removeData:function(e){this._data.removeModel(e)},removeRow:function(e){this._modelRowBindings[e.cid].destroy(),delete this._modelRowBindings[e.cid]},clear:function(){for(var e=this._rows.length-1;e>=0;e--)this._rows[e].destroy();this._rows=[];for(var t in this._events)this._events.hasOwnProperty(t)&&this._data.removeEventHandler(t,this._events[t]);this._data=new i.Collection([]),this._modelRowBindings={}},sort:function(e,t){this._data.sort(e,t)},sortRender:function(){for(var e=0;e<this.getRows().length;e++)this.getRows()[e].detach();this._rows=[],this._data.each(function(e){var t=this._modelRowBindings[e.cid];t.attach(),this._rows.push(t)}.bind(this))},initializeColumns:function(e){for(var t=[],i=0;i<e.length;i++){var s=new n({index:i,definition:e[i]});t.push(s)}return t},initializeHeaderRow:function(e){var t=this,i=new this.headerRowType({eventBus:this._eventBus,table:this}),s=[];return e.forEach(function(n,o){var l=n.getDefinition().headerCellType||this.headerCellType,h=new l({row:i,eventBus:this._eventBus,column:e[o],table:this});if(this.options.tooltips&&h.setTooltip(e[o].getTitle()),e[o].getDefinition().sortable){var r=new a,d=e[o].getDefinition().initialSortIcon;d&&("desc"===d?r.view.setDownArrowActive():r.view.setUpArrowActive(),r._sortingMode=d);var c=h.view.getBody();r.attachTo(c),c.addEventHandler("click",r.click,r),r.addEventHandler("reset",function(){s.forEach(function(e){this!==e&&e.reset()},this)},r),r.addEventHandler("sort",function(e){t.trigger("sort",this.getColumn().getAttribute(),e)},h),s[o]=r}i.attachCell(h)},this),i},getData:function(){return this._data},getHeaderRow:function(){return this._headerRow},getRows:function(){return this._rows},getColumns:function(){return this._columns},getEventBus:function(){return this._eventBus},destroy:function(){this.clear(),e.Widget.prototype.destroy.call(this)}})}),define("widgets/Table",["widgets/Table/Table"],function(e){return e});