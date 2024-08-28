define('styles!layouts/dashboard/Item/item.less',[],function () { return '.elWidgets-Dashboard-item {\n  background-color: #ffffff;\n  border: 1px #ffffff solid;\n  border-radius: 3px;\n  padding: 8px;\n  padding-top: 0;\n  margin-bottom: 10px;\n  -webkit-animation: fadein 1s;\n  -moz-animation: fadein 1s;\n  -ms-animation: fadein 1s;\n  -o-animation: fadein 1s;\n  animation: fadein 1s;\n  /* Firefox < 16 */\n\n  /* Safari, Chrome and Opera > 12.1 */\n\n  /* Internet Explorer */\n\n  /* Opera < 12.1 */\n\n}\n@keyframes fadein {\n  from {\n    opacity: 0;\n  }\n  to {\n    opacity: 1;\n  }\n}\n@-moz-keyframes fadein {\n  from {\n    opacity: 0;\n  }\n  to {\n    opacity: 1;\n  }\n}\n@-webkit-keyframes fadein {\n  from {\n    opacity: 0;\n  }\n  to {\n    opacity: 1;\n  }\n}\n@-ms-keyframes fadein {\n  from {\n    opacity: 0;\n  }\n  to {\n    opacity: 1;\n  }\n}\n@-o-keyframes fadein {\n  from {\n    opacity: 0;\n  }\n  to {\n    opacity: 1;\n  }\n}\n.elWidgets-Dashboard-item_dragged {\n  -webkit-animation: none;\n  /* Safari, Chrome and Opera > 12.1 */\n\n  -moz-animation: none;\n  /* Firefox < 16 */\n\n  -ms-animation: none;\n  /* Internet Explorer */\n\n  -o-animation: none;\n  /* Opera < 12.1 */\n\n  animation: none;\n  border-color: #dfdfdf;\n}\n.elWidgets-Dashboard-item:last-child {\n  padding-bottom: 0;\n}\n.elWidgets-Dashboard-item-top {\n  padding-top: 0;\n  margin-top: 0;\n  border-bottom: 1px solid #dfdfdf;\n}\n.elWidgets-Dashboard-item-top:hover .elWidgets-Dashboard-item-top-actions {\n  opacity: 1;\n}\n.elWidgets-Dashboard-item-top-actions {\n  opacity: 0.5;\n}\n.elWidgets-Dashboard-item-top-actions_fullAlpha {\n  opacity: 1;\n}\n.elWidgets-Dashboard-item-header {\n  text-overflow: ellipsis;\n  white-space: nowrap;\n  overflow: hidden;\n  padding: 0;\n  border-bottom: 0;\n  position: absolute;\n  bottom: 0;\n  left: 0;\n  margin-top: 0;\n}\n.elWidgets-Dashboard-item-header_draggable {\n  cursor: move;\n}\n.elWidgets-Dashboard-item_dragged {\n  z-index: 1001;\n}\n.elWidgets-Dashboard-item-menu {\n  margin: 0;\n  display: none;\n}\n.elWidgets-Dashboard-item-menu_show {\n  display: inline-block;\n}\n.elWidgets-Dashboard-item-settings {\n  display: none;\n}\n.elWidgets-Dashboard-item-settings_show {\n  display: inline-block;\n}\n.elWidgets-Dashboard-item-separator {\n  display: none;\n}\n.elWidgets-Dashboard-item-separator_show {\n  display: inline-block;\n}\n.elWidgets-Dashboard_one-column > .elWidgets-Dashboard-column > .elWidgets-Dashboard-column-content > .elWidgets-Dashboard-item > .elWidgets-Dashboard-item-top > .ebLayout-HeadingCommands > .ebLayout-HeadingCommands-block > .elWidgets-Dashboard-item-maximize-minimize {\n  display: none;\n}\n';});

define('text!layouts/dashboard/Item/item.html',[],function () { return '<div class="elWidgets-Dashboard-item">\n    <div class="ebLayout-SectionHeading elWidgets-Dashboard-item-top">\n        <h3 class="elWidgets-Dashboard-item-header">Section Heading</h3>\n        <div class="ebLayout-HeadingCommands">\n            <div class="elWidgets-Dashboard-item-top-actions ebLayout-HeadingCommands-block">\n                <div class="ebLayout-HeadingCommands-iconHolder">\n                    <div class="elWidgets-Dashboard-item-menu"></div>\n                </div>\n                <div class="elWidgets-Dashboard-item-separator ebLayout-HeadingCommands-separator"></div>\n                <div class="ebLayout-HeadingCommands-iconHolder">\n                    <i class="elWidgets-Dashboard-item-settings ebIcon ebIcon_settings ebIcon_interactive" title="Settings"></i>\n                </div>\n            </div>\n        </div>\n        <div class="ebLayout-clearFix"></div>\n    </div>\n    <div class="elWidgets-Dashboard-item-content"></div>\n</div>';});

define('layouts/dashboard/Item/ItemView',['jscore/core','text!./item.html','styles!./item.less'],function (core, template, style) {

    var ItemView = core.View.extend({

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return style;
        },
        setHeader: function (value) {
            this.getElement().find('.' + ItemView.EL_HEADER).setText(value);
        },
        getContentDiv: function () {
            return this.getElement().find('.' + ItemView.EL_CONTENT);
        },
        getHeader: function () {
            return this.getElement().find('.' + ItemView.EL_HEADER);
        },

        setHeaderWidth: function () {
            var topActionsWidth = this.getTopActions().getProperty('offsetWidth');
            this.getHeader().setStyle('width', 'calc(100% - ' + (topActionsWidth + 5) + 'px)');
        },
        getTop: function () {
            return this.getElement().find('.' + ItemView.EL_TOP);
        },
        getTopActions: function () {
            return this.getElement().find('.' + ItemView.EL_TOP_ACTIONS);
        },
        setPosition: function (x, y) {
            this.getElement().setStyle({
                left: x + 'px',
                top: y + 'px'
            });
        },
        getMenu: function () {
            return this.getElement().find('.' + ItemView.EL_MENU);
        },
        getSeparator: function () {
            return this.getElement().find('.' + ItemView.EL_SEPARATOR);
        },
        showSeparator: function () {
            this.getSeparator().setModifier('show');
        },
        getSettings: function () {
            return this.getElement().find('.' + ItemView.EL_SETTINGS);
        },
        showSettings: function () {
            this.getSettings().setModifier('show');
        },
        showMenu: function () {
            this.getMenu().setModifier('show');
        },
        hideMenu: function () {
            this.getMenu().removeModifier('show');
        }

    }, {
        "EL": "elWidgets-Dashboard-item",
        "EL_HEADER": "elWidgets-Dashboard-item-header",
        "EL_TOP": "elWidgets-Dashboard-item-top",
        "EL_TOP_ACTIONS": "elWidgets-Dashboard-item-top-actions",
        "EL_CONTENT": "elWidgets-Dashboard-item-content",
        "EL_SETTINGS": "elWidgets-Dashboard-item-settings",
        "EL_SEPARATOR": "elWidgets-Dashboard-item-separator",
        "EL_MENU": "elWidgets-Dashboard-item-menu",
        "EL_MAXIMIZE": "elWidgets-Dashboard-item-maximize",
        "EL_MINIMIZE": "elWidgets-Dashboard-item-minimize",
        "EL_MAXIMIZE_MINIMIZE": "elWidgets-Dashboard-item-maximize-minimize",
        "EL_CLOSE": "elWidgets-Dashboard-item-close"
    });

    return ItemView;

});

define('layouts/dashboard/Item/Item',['jscore/core','widgets/utils/domUtils','widgets/WidgetCore','./ItemView','widgets/ContextMenu','../../ext/utils'],function (core, domUtils, widgetCore, View, ContextMenu, utils) {
    'use strict';

    return widgetCore.extend({
        View: View,
        /**
         * Initializes members which are used in this widget
         *
         * @method init
         * @private
         */
        init: function (options) {

            this.header = options.header || 'default header text';
            this.content = options.content;
            this.iid = options.iid;
            this.maximizable = options.maximizable || false;
            this.maximizeText = options.maximizeText || 'Maximize';
            this.minimizeText = options.minimizeText || 'Minimize';
            this.closeText = options.closeText || 'Close';
            this.settings = (options.settings === false) ? options.settings : true;
            this.closable = (options.closable !== undefined) ? options.closable : true;
            this.customActions = options.customActions || [];

        },
        /**
         * Initializes stuff
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.view.setHeader(this.header);
//            this.content.attachTo(this.view.getContentDiv());
            utils.attachUIElement(this.content, this.view.getContentDiv());

            this.bindDrag();
            this.setActions();
            this.contextMenu = new ContextMenu({
                items: this.actions
            });

            if (this.settings) {

                this.view.showSettings();
                this.view.getSettings().addEventHandler("click", function () {
                    this.trigger('settings', this);
                }.bind(this));
            }

            if (this.actions.length > 0 && this.settings) {
                this.view.showSeparator();
            }

            this.contextMenu.attachTo(this.view.getMenu());

        },
        onDOMAttach: function () {
            this.view.setHeaderWidth();
        },
        setActions: function () {
            this.actions = [];

            if (this.closable) {
                this.actions.push({name: this.closeText, action: this.close.bind(this)});
            }

            if (this.maximizable && !this.maximized) {
                this.actions.push({name: this.maximizeText, action: this.maximize.bind(this)});
            } else if (this.maximizable && this.maximized) {
                this.actions.push({name: this.minimizeText, action: this.minimize.bind(this)});
            }

            if(this.content.actions && this.content.actions.length > 0){
                this.content.actions.forEach(function(action){
                    this.actions.push({name: action.name, action: action.action.bind(this.content)});
                }.bind(this));
            }

            if (this.actions.length > 0) {
                this.view.showMenu();
            } else {
                this.view.hideMenu();
            }

        },
        changeActions: function () {
            this.setActions();
            this.contextMenu.setItems(this.actions);
        },
        close: function () {
            this.trigger('close', this);
        },
        maximize: function () {
            this.trigger('maximize', this);
            this.maximized = true;
            this.unbindDrag();
            this.changeActions();
        },
        minimize: function () {
            this.trigger('minimize', this);
            this.maximized = false;
            this.bindDrag();
            this.changeActions();
        },
        bindDrag: function () {
            this.mouseDownEventId = this.view.getHeader().addEventHandler('mousedown', this.move.bind(this));
            this.touchStartEventId = this.view.getHeader().addEventHandler('touchstart', this.move.bind(this));
            this.view.getHeader().setModifier('draggable');
        },
        unbindDrag: function () {
            if (this.mouseDownEventId) {
                this.view.getHeader().removeEventHandler(this.mouseDownEventId);
            }
            if (this.touchStartEventId) {
                this.view.getHeader().removeEventHandler(this.touchStartEventId);
            }
            this.view.getHeader().removeModifier('draggable');
        },
        move: function (e) {
            // Right click: no nothing
            if (e.originalEvent.type === 'mousedown' && e.originalEvent.which !== 1) {
                return;
            }
            this.getElement().setModifier('dragged');
            e.originalEvent.stopPropagation();
            e.originalEvent.preventDefault();
            var originalWidth = this.view.getElement().getProperty('offsetWidth');

            this.view.getElement().setStyle({
                position: 'absolute',
                width: originalWidth + 'px'
            });

            // Add placeholder
            this.originalTop = parseInt(this.view.getElement().getProperty('offsetTop'));
            this.originalLeft = parseInt(this.view.getElement().getProperty('offsetLeft'));

            if (e.originalEvent.type === 'mousedown') {
                this.mouseDownX = e.originalEvent.clientX;
                this.mouseDownY = e.originalEvent.clientY;

                this.mouseUpEventId = core.Window.addEventHandler('mouseup', this.drop.bind(this));
                this.mouseMoveEventId = core.Window.addEventHandler('mousemove', this.drag.bind(this));
            } else if (e.originalEvent.type === 'touchstart') {

                this.mouseDownX = e.originalEvent.touches[0].clientX;
                this.mouseDownY = e.originalEvent.touches[0].clientY;

                this.mouseUpEventId = core.Window.addEventHandler('touchend', this.drop.bind(this));
                this.mouseMoveEventId = core.Window.addEventHandler('touchmove', this.drag.bind(this));

            }
            this.view.setPosition(this.originalLeft, this.originalTop);
            this.trigger('pickedUp', e, this);
        },
        drag: function (e) {
            e.originalEvent.stopPropagation();
            this.getCurrentCoordinates(e);
            this.view.setPosition((this.originalLeft + this.currentMouseDownX) - this.mouseDownX, (this.originalTop + this.currentMouseDownY) - this.mouseDownY);
            this.view.getElement().setStyle('display', 'none');
            this.targetElement = document.elementFromPoint(this.currentMouseDownX, this.currentMouseDownY);
            this.view.getElement().removeStyle('display');
            this.view.getElement().setModifier('dragged');
            this.trigger('dragged', e, this, this.targetElement);
        },
        getCurrentCoordinates: function (e) {
            if (e.originalEvent.type === 'mousemove') {
                this.currentMouseDownX = e.originalEvent.clientX;
                this.currentMouseDownY = e.originalEvent.clientY;
            } else if (e.originalEvent.type === 'touchmove') {
                this.currentMouseDownX = parseInt(e.originalEvent.touches[0].clientX);
                this.currentMouseDownY = parseInt(e.originalEvent.touches[0].clientY);
            }
        },
        drop: function (e) {
            e.originalEvent.stopPropagation();
            this.getCurrentCoordinates(e);

            this.getElement().removeModifier('dragged');

            if (this.mouseUpEventId) {
                core.Window.removeEventHandler(this.mouseUpEventId);
                delete this.mouseUpEventId;
            }
            if (this.mouseMoveEventId) {
                core.Window.removeEventHandler(this.mouseMoveEventId);
                delete this.mouseMoveEventId;
            }
            this.view.getElement().removeModifier('dragged');
            this.revertMovement();
            this.trigger('dropped', e, this, this.targetElement);
            delete this.targetElement;

        },
        revertMovement: function () {
            this.view.getElement().removeStyle('position');
            this.view.getElement().removeStyle('padding');
            this.view.getElement().removeStyle('width', '100%');
            this.view.getElement().removeStyle('top');
            this.view.getElement().removeStyle('left');
        },
        onDestroy: function () {
            this.content.destroy();
        },
        toJSON: function () {
            var result = {
                "header": this.header,
                "type": this.content.id,
                "maximizable": this.maximizable,
                "maximize": this.maximized,
                "closable": this.closable,
                "iid": this.iid,
                "maximizeText": this.maximizeText,
                "minimizeText": this.minimizeText,
                "closeText": this.closeText,
                "settings": this.settings,
                "config": (this.content.toJSON) ? this.content.toJSON() : this.content.options
            };
            return result;
        }
    });
});

define('layouts/ext/utils',['jscore/core'],function (core) {
    'use strict';

    return {
        attachUIElement: function (uiElement, parent) {
            if (uiElement instanceof core.Widget) {
                uiElement.attachTo(parent);
            } else if (uiElement instanceof core.Region) {
                uiElement.start(parent);
            }
        },
        detachUIElement: function (uiElement) {
            if (uiElement instanceof core.Widget) {
                uiElement.detach();
            } else if (uiElement instanceof core.Region) {
                uiElement.stop();
            }
        }

    }
});

define('styles!layouts/dashboard/PlaceHolder/placeHolder.less',[],function () { return '.elWidgets-Dashboard-placeHolder {\n  border: 1px dashed #dfdfdf;\n  border-radius: 3px;\n  background: #eff0f0;\n  z-index: 1;\n  text-align: center;\n}\n.elWidgets-Dashboard-placeHolder-header {\n  margin: 0;\n  padding: 0;\n  border: 0;\n  padding-top: 10px;\n  padding-bottom: 10px;\n}\n';});

define('text!layouts/dashboard/PlaceHolder/placeHolder.html',[],function () { return '<div class="elWidgets-Dashboard-placeHolder">\n    <h4 class="elWidgets-Dashboard-placeHolder-header"></h4>\n</div>';});

define('layouts/dashboard/PlaceHolder/PlaceHolderView',['jscore/core','text!./placeHolder.html','styles!./placeHolder.less'],function (core, template, style) {

    var PlaceHolderView = core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return style;
        },
        setHeader: function(value){
            this.getElement().find('.' + PlaceHolderView.EL_HEADER).setText(value);
        },
        getHeader: function(){
            return this.getElement().find('.' + PlaceHolderView.EL_HEADER);
        },
        setHeight: function(height){
            this.getElement().setStyle({height: height});
        }

    }, {
        "EL":"elWidgets-Dashboard-placeHolder",
        "EL_HEADER":"elWidgets-Dashboard-placeHolder-header"
    });

    return PlaceHolderView;

});

define('layouts/dashboard/PlaceHolder/PlaceHolder',['jscore/core','widgets/utils/domUtils','widgets/WidgetCore','./PlaceHolderView'],function (core, domUtils, widgetCore, View) {
    'use strict';

    return widgetCore.extend({
        View: View,
        /**
         * Initializes members which are used in this widget
         *
         * @method init
         * @private
         */
        init: function (options) {

            this.height = options.height;
            this.origin = options.origin;
            this.header = options.header || '';

        },
        /**
         * Initializes stuff
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            if(this.height){
                this.view.setHeight(this.height);
            }
            if(this.origin){
                this.view.getElement().setModifier('origin');
            }else{
                this.view.getElement().setModifier('target');
            }

            this.view.setHeader(this.header);
        }


    });

});

define('styles!layouts/dashboard/Column/column.less',[],function () { return '.elWidgets-Dashboard-column {\n  vertical-align: top;\n}\n.elWidgets-Dashboard-column-footer {\n  height: 70px;\n}\n';});

define('text!layouts/dashboard/Column/column.html',[],function () { return '<div class="elWidgets-Dashboard-column">\n    <div class="elWidgets-Dashboard-column-content"></div>\n    <div class="elWidgets-Dashboard-column-footer"></div>\n</div>';});

define('layouts/dashboard/Column/ColumnView',['jscore/core','text!./column.html','styles!./column.less'],function (core, template, style) {

    var ColumnView = core.View.extend({

        getTemplate: function () {
            return template;
        },

        getStyle: function () {
            return style;
        },
        getContentDiv: function () {
            return this.getElement().find('.' + ColumnView.EL_CONTENT);
        }

    }, {
        "EL": "elWidgets-Dashboard-column",
        "EL_CONTENT": "elWidgets-Dashboard-column-content",
        "EL_FOOTER": "elWidgets-Dashboard-column-footer"
    });

    return ColumnView;

});

define('layouts/dashboard/Column/Column',['jscore/core','widgets/utils/domUtils','widgets/WidgetCore','./ColumnView','../PlaceHolder/PlaceHolder','../../ext/utils'],function (core, domUtils, widgetCore, View, PlaceHolder, utils) {
    'use strict';

    return widgetCore.extend({
        View: View,
        /**
         * Initializes members which are used in this widget
         *
         * @method init
         * @private
         */
        init: function (options) {
            this.items = options.items || [];
            this._items = [];
        },
        /**
         * Initializes stuff
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {

        },
        getItems: function () {
            return this._items;
        },
        addOriginPlaceHolder: function (index) {
            var height = this._items[index].getElement().getProperty('offsetHeight');
            this.originPlaceHolder = new PlaceHolder({height: height, origin: true});
            attachElementAt.call(this, this.originPlaceHolder, index);
            this.originPlaceHolderPosition = index;
        },
        addTargetPlaceHolder: function (index, header) {
            if ((index !== this.originPlaceHolderPosition && index !== this.originPlaceHolderPosition + 1)) {
                if (this.targetPlaceHolder) {
                    this.clearTargetPlaceHolder();
                }
                this.targetPlaceHolder = new PlaceHolder({target: true, header: header});
                attachElementAt.call(this, this.targetPlaceHolder, index);
                this.targetPlaceHolderPosition = index;
            }

        },
        clearTargetPlaceHolder: function () {
            if (this.targetPlaceHolder) {
                this.targetPlaceHolder.destroy();
                delete this.targetPlaceHolderPosition;
            }
        },
        clearOriginPlaceHolders: function () {
            if (this.originPlaceHolder) {
                this.originPlaceHolder.destroy();
                delete this.originPlaceHolderPosition;
            }
            this.clearTargetPlaceHolder();
        },
        extract: function (index) {
            var item = this._items[index];
            if (item) {
                this._items.splice(index, 1);
//                item.detach();
                utils.detachUIElement(item);
                return item;
            }
        },
        extractAll: function(){
            this.getItems().forEach(function(item){
//                item.detach();
                utils.detachUIElement(item);
            }.bind(this));
            var result = this.getItems();
            this._items = [];
            return result;
        },
        remove: function (index) {
            var item = this.extract(index);
            if (item) {
                item.destroy();
            }
        },
        add: function (item, index) {
            if (index >= 0 && index < this._items.length) {
                attachElementAt.call(this, item, index);
            } else {
//                item.attachTo(this.view.getContentDiv());
                utils.attachUIElement(item, this.view.getContentDiv());
            }
            this._items.splice(index, 0, item);
        },
        move: function (item, index) {

            var oldIndex = this._items.indexOf(item);

            if (oldIndex !== -1) {
                item = this.extract(oldIndex);
                this.add(item, index);
            }

        },
        onDestroy: function () {
            this.getItems().forEach(function (item) {
                item.destroy();
            });
        },
        toJSON: function () {
            var result = [];
            this.getItems().forEach(function (item) {
                result.push(item.toJSON());
            });
            return result;
        },
        get: function (iid) {
            var items = this.getItems();
            for (var i = 0; i <= items.length - 1; i++) {
                if (items[i].iid === iid) {
                    return items[i].content;
                }
            }
        }
    });

    /**
     *
     *
     * @method
     * @private
     */
    function attachElementAt(item, index) {
        var el = item.getElement()._getHTMLElement();
        var other = this._items[index];
        var column = this.view.getContentDiv()._getHTMLElement();
        item._parent = this.view.getContentDiv();
        if (other) {
            other = other.getElement()._getHTMLElement();
            column.insertBefore(el, other);
        } else {
//            item.attachTo(this.view.getContentDiv());
            utils.attachUIElement(item, this.view.getContentDiv());
        }


    }

});

define('styles!layouts/dashboard/dashboard.less',[],function () { return '.elWidgets-Dashboard {\n  margin-top: 20px;\n  height: 100%;\n}\n.elWidgets-Dashboard-column {\n  display: inline-block;\n}\n.elWidgets-Dashboard-content_three-columns-30-40-30 > .elWidgets-Dashboard-column {\n  width: calc(30% - 24px);\n  margin-right: 36px;\n}\n.elWidgets-Dashboard-content_three-columns-30-40-30 > .elWidgets-Dashboard-column:nth-child(2) {\n  width: calc(40% - 24px);\n}\n.elWidgets-Dashboard-content_three-columns-30-40-30 > .elWidgets-Dashboard-column:last-child {\n  margin-right: 0;\n}\n.elWidgets-Dashboard-content_three-columns > .elWidgets-Dashboard-column {\n  width: calc(33.33% - 24px);\n  margin-right: 36px;\n}\n.elWidgets-Dashboard-content_three-columns > .elWidgets-Dashboard-column:last-child {\n  margin-right: 0;\n}\n.elWidgets-Dashboard-content_four-columns > .elWidgets-Dashboard-column {\n  width: calc(25% - 27px);\n  margin-right: 36px;\n}\n.elWidgets-Dashboard-content_four-columns > .elWidgets-Dashboard-column:last-child {\n  margin-right: 0;\n}\n.elWidgets-Dashboard-content_two-columns > .elWidgets-Dashboard-column {\n  width: calc(50% - 18px);\n  margin-right: 36px;\n}\n.elWidgets-Dashboard-content_two-columns > .elWidgets-Dashboard-column:last-child {\n  margin-right: 0;\n}\n.elWidgets-Dashboard-content_two-columns-30-70 > .elWidgets-Dashboard-column {\n  width: calc(30% - 18px);\n  margin-right: 36px;\n}\n.elWidgets-Dashboard-content_two-columns-30-70 > .elWidgets-Dashboard-column:last-child {\n  width: calc(70% - 18px);\n  margin-right: 0;\n}\n.elWidgets-Dashboard-content_two-columns-70-30 > .elWidgets-Dashboard-column {\n  width: calc(70% - 18px);\n  margin-right: 36px;\n}\n.elWidgets-Dashboard-content_two-columns-70-30 > .elWidgets-Dashboard-column:last-child {\n  width: calc(30% - 18px);\n  margin-right: 0;\n}\n.elWidgets-Dashboard-content_one-column > .elWidgets-Dashboard-column {\n  width: 100%;\n}\n@media (max-width: 600px) {\n  .elWidgets-Dashboard-column {\n    min-height: none;\n  }\n  .elWidgets-Dashboard-content_two-columns > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_two-columns-70-30 > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_two-columns-30-70 > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_three-columns > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_three-columns-30-40-30 > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_three-columns-30-40-30 > .elWidgets-Dashboard-column:nth-child(2),\n  .elWidgets-Dashboard-content_four-columns > .elWidgets-Dashboard-column {\n    width: 100%;\n    margin: 0;\n    height: auto;\n  }\n  .elWidgets-Dashboard-content_two-columns-70-30 > .elWidgets-Dashboard-column:last-child,\n  .elWidgets-Dashboard-content_two-columns-30-70 > .elWidgets-Dashboard-column:last-child {\n    margin-right: 0;\n    width: 100%;\n    height: auto;\n  }\n  .elWidgets-Dashboard-column-footer {\n    height: 0;\n  }\n}\n@media (min-width: 600px) and (max-width: 992px) {\n  .elWidgets-Dashboard-column {\n    min-height: none;\n  }\n  .elWidgets-Dashboard-content_two-columns-70-30 > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_two-columns-30-70 > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_three-columns > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_three-columns-30-40-30 > .elWidgets-Dashboard-column,\n  .elWidgets-Dashboard-content_four-columns > .elWidgets-Dashboard-column {\n    width: calc(50% - 18px);\n    margin-right: 36px;\n  }\n  .elWidgets-Dashboard-content_two-columns-70-30 > .elWidgets-Dashboard-column:nth-child(even),\n  .elWidgets-Dashboard-content_two-columns-30-70 > .elWidgets-Dashboard-column:nth-child(even),\n  .elWidgets-Dashboard-content_three-columns > .elWidgets-Dashboard-column:nth-child(even),\n  .elWidgets-Dashboard-content_three-columns-30-40-30 > .elWidgets-Dashboard-column:nth-child(even),\n  .elWidgets-Dashboard-content_four-columns > .elWidgets-Dashboard-column:nth-child(even) {\n    margin-right: 0;\n    width: calc(50% - 18px);\n  }\n}\n';});

define('text!layouts/dashboard/dashboard.html',[],function () { return '<div class="elWidgets-Dashboard">\n    <div class="elWidgets-Dashboard-top"></div>\n    <div class="elWidgets-Dashboard-content"></div>\n</div>';});

define('layouts/dashboard/DashboardView',['jscore/core','text!./dashboard.html','styles!./dashboard.less'],function (core, template, style) {

    var DashboardView = core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return style;
        },
        getTop: function(){
            return this.getElement().find('.' + DashboardView.EL_TOP);
        },
        getContent: function(){
            return this.getElement().find('.' + DashboardView.EL_CONTENT);
        }

    }, {
        "EL":"elWidgets-Dashboard",
        "EL_TOP":"elWidgets-Dashboard-top",
        "EL_CONTENT":"elWidgets-Dashboard-content"
    });

    return DashboardView;

});

define('layouts/dashboard/Dashboard',['jscore/core','widgets/utils/domUtils','widgets/WidgetCore','./DashboardView','./Column/Column','./Item/Item','../ext/utils'],function (core, domUtils, widgetCore, View, Column, Item, utils) {
    'use strict';

    /**
     * The Dashboard class uses the Ericsson brand assets.<br>
     * The Dashboard can be instantiated using the constructor Dashboard.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>Dashboard(Object options)</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li><strong>context:</strong> pass the context</li>
     *       <li><strong>layout:</strong> the layout configuration for the dashboard. Available options are: "one-column", "two-columns", "two-columns-70-30", "two-columns-30-70", "three-columns", "three-columns-30-40-30" and "four-columns"</li>
     *       <li><strong>references:</strong> an array with all the available references.</li>
     *       <li><strong>closeText:</strong> the string of the close action. Default is "Close"</li>
     *       <li><strong>maximizeText:</strong> the string of the maximize action. Default is "Maximize"</li>
     *       <li><strong>minimizeText:</strong> the string of the maximize action. Default is "Minimize"</li>
     *       <li><strong>items:</strong> An Array with the columns with the items (widgets/gadgets) inside. This array should have as many elements as dashboard has columns <br/>
     *          <strong>Item object:</strong>
     *          <ul>
     *              <li><strong>header:</strong> Text that will be displayed at the top of the item.</li>
     *              <li><strong>type:</strong> the class of the item. Can be a function or a reference string.</li>
     *              <li><strong>config:</strong> configuration object of the item. This is the object that is passed to the constructor when creating the instance.</li>
     *              <li><strong>maximizable:</strong> whether or not the item will have the maximize/minimize functionality. Default is false.</li>
     *              <li><strong>closable:</strong> whether or not the item will have the closable functionality. Default is true.</li>
     *              <li><strong>settings:</strong> whether or not the item will have settings action. Default is true.</li>
     *              <li><strong>maximize:</strong> whether or not the item will be maximized. Default is false. If multiple items have this option set to true, only the last one will be maximized.</li>
     *              <li><strong>iid:</strong> an string that can be used to have quick access to the item instance using the get method.</li>
     *          </ul>
     *          </li>
     *   </ul>
     *
     * <strong>Example:</strong>
     * <pre>
     *  var dashboard  = new Dashboard({
     *      context: this.getContext(),
     *      items: [
     *          // first column
     *          [
     *              {header: 'Header text', type: "SampleWidget", config: {some: 'value'}, maximizable: true},
     *              {header: 'Another text', type: "AnotherWidget", config: {some: 'value'}, closable: false}
     *          ],
     *          // second column
     *          [
     *              {header: 'Header text', type: "PreDefinedWidget", config: {}, settings: false,  maximize: true},
     *              {header: 'Header text', type: "OtherWidget", config:{}, iid: 'otherWidgetInstance'}
     *          ]
     *       ],
     *       layout: 'two-columns',
     *       references: [SampleWidget, AnotherWidget, PreDefinedWidget, OtherWidget]
     *  });
     * </pre>
     *
     *<strong>Please note that all classes will need to implement the following:</strong>
     * <ul>
     *     <li><strong>id:</strong> a string which identifies the class</li>
     *     <li><strong>onSettings:</strong> a method that will be executed when user clicks the settings action. (optional)</li>
     *     <li><strong>actions:</strong> an array with custom actions that will be displayed in the context menu. (optional)</li>
     *     <li><strong>toJSON:</strong> a method that should return the widget configuration in order to persist current state. If not implement then the "options" property will be taken instead. This is the object that will be passed to the constructor when recreating the instance. (optional)</li>
     * </ul>
     *     <strong>Example:</strong>
     *     <pre>
     *  var sampleWidget = core.Widget.extend({
     *      id: 'SampleWidget',
     *      actions: [
     *          {name: 'Refresh', action: function(){ alert('Refresh');}},
     *          {name: 'Another action', action: function(){ alert('Another action');}}
     *          ],
     *      onSettings: function(){
     *          alert('Show settings');
     *      },
     *      toJSON: function(){
     *          // returns current configuration
     *          return {
     *          content: this.content,
     *          backgroundColor: this.backgroundColor
     *          };
     *      }
     *  });
     *     </pre>
     *
     *
     * @class Dashboard
     * @extends WidgetCore
     * @beta
     */

    var Dashboard = widgetCore.extend({
        View: View,
        id: 'Dashboard',
        /**
         * Initializes members which are used in this widget
         *
         * @method init
         * @private
         */
        init: function (options) {
            this.eventBus = this.context.eventBus;
            this._availableLayouts = {
                'one-column': [
                    {width: 100}
                ],
                'two-columns': [
                    {width: 50},
                    {width: 50}
                ],
                'two-columns-30-70': [
                    {width: 30},
                    {width: 70}
                ],
                'two-columns-70-30': [
                    {width: 70},
                    {width: 30}
                ],
                'three-columns': [
                    {width: 33},
                    {width: 33},
                    {width: 33}
                ],
                'three-columns-30-40-30': [
                    {width: 30},
                    {width: 40},
                    {width: 30}
                ],
                'four-columns': [
                    {width: 25},
                    {width: 25},
                    {width: 25},
                    {width: 25}
                ]
            };
            this._layout = (Object.keys(this._availableLayouts).indexOf(options.layout) !== -1) ? options.layout : 'four-columns';

            this._items = options.items;

            this._references = {};

            // map references to the _references object for easy access
            if(options.references && options.references.length > 0){
                setReferences.call(this, options.references);
            }

            this._references['Dashboard'] = Dashboard;

        },

        getColumns: function () {
            return this._columns;
        },
        /**
         * Returns the number of columns of dashboard.
         *
         * @method getNumberOfColumns
         */
        getNumberOfColumns: function(){
            return this.getColumns().length;
        },
        /**
         * Returns current layout of dashboard.
         *
         * @method getNumberOfColumns
         * @returns {string} layout
         */
        getCurrentLayout: function(){
            return this._layout;
        },
        /**
         * Adds new references to dashboard on the fly.
         *
         * @method addReferences
         * @param {array} referencesArray
         */
        addReferences: function(referencesArray){
            setReferences.call(this, referencesArray);
        },
        /**
         * Adds item to dashboard. Please note that column indexes and item indexes are zero based.
         *
         * @method addItem
         * @param {object} itemConfig
         * @param {int} columnIndex
         * @param {int} itemIndex
         */
        addItem: function(itemConfig, columnIndex, itemIndex){
            // create item instance and add it to column and index specified
            var item = setItem.call(this, itemConfig);
            var column = this.getColumns()[columnIndex];
            if(column){
                column.add(item, itemIndex);
                item.onDOMAttach();
            }
        },
        /**
         * Removes an item from dashboard. Please note that column indexes and item indexes are zero based.
         *
         * @method removeItem
         * @param {int} columnIndex
         * @param {int} itemIndex
         */
        removeItem: function(columnIndex, itemIndex){
            // extract item from column and destroy it
            var column = this.getColumns()[columnIndex];
            if(column){
                var item = column.extract(itemIndex);
                if(item){
                    item.destroy();
                }
            }
        },
        /**
         * Returns an array with available layout configurations.
         *
         * @method getAvailableLayouts
         * @return Array
         */
        getAvailableLayouts: function(){
            return Object.keys(this._availableLayouts);
        },
        /**
         * Changes the layout of the dashboard.
         *
         * @method changeLayout
         * @param {string} layout
         */
        changeLayout: function(layout){

            // if the string is not a valid layout then return
            if(Object.keys(this._availableLayouts).indexOf(layout) === -1){
                return;
            }

            // store all existing item in an array
            var items = [];
            this.getColumns().forEach(function(column){
                items = items.concat(column.extractAll());
                column.destroy();
            }.bind(this));

            // change modifier on the content div so styles would be applied
            this.view.getContent().removeModifier(this._layout);

            this._layout = layout;

            this._items = [];

            setColumns.call(this);

            this.onDOMAttach();
            var index = 0;
            function next(limit){
                if(index >= limit){
                    index = 0
                }
                return index++;
            }

            // rearrange items in new layout
            items.forEach(function(item, i){
                var nextIndex = next(this.getColumns().length);
                this.getColumns()[nextIndex].add(item, 0);
            }.bind(this));

            // if there is a maximized item then set this.maximizedItemColumn to the first column for when it is minimized
            if(this.maximizedItem){
                this.maximizedItemColumn = this.getColumns()[0];
            }

            triggerChange.call(this);
            core.Window.trigger('resize');

        },
        /**
         * Initializes view stuff
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            setColumns.call(this);
        },
        /**
         * Initializes stuff
         *
         * @method onViewReady
         * @private
         */
        onDOMAttach: function () {
            renderColumns.call(this);
            // add modifier to the content div so styles would be applied
            this.view.getContent().setModifier(this._layout);
        },
        /**
         * onDestroy override
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function () {
            this.getColumns().forEach(function (column) {
                column.destroy();
            });
        },
        /**
         * Exports dashboard configuration as json. Use this to persist dashboard state.
         *
         * @method toJSON
         * @return json
         */
        toJSON: function () {
            var result = [];
            this.getColumns().forEach(function (column) {
                result.push(column.toJSON());
            }.bind(this));

            // if the is a maximized item then add it's location to the configuration
            if(this.maximizedItem){
                var columnIndex = this.getColumns().indexOf(this.maximizedItemColumn);
                if(columnIndex !== -1){
                    result[columnIndex].splice(this.maximizedItemIndex, 0, this.maximizedItem.toJSON());
                }
            }
            return {
                "type": 'Dashboard',
                "layout": this._layout,
                "items": result,
                "maximizeText": this.options.maximizeText,
                "minimizeText": this.options.minimizeText,
                "closeText": this.options.closeText
            };
        },
        /**
         * Get an item instance by column index and item index. Please note that column indexes and item indexes are zero based.
         *
         * @method getItem
         * @return item
         */
        getItem: function (columnIndex, itemIndex) {
            return this.getColumns()[columnIndex].getItems()[itemIndex].content;
        },
        /**
         * Get an item instance by the iid provided in the item config object.
         *
         * @method get
         * @return item
         */
        get: function (iid) {
            var columns = this.getColumns();
            for (var i = 0; i <= columns.length - 1; i++) {
                var item = columns[i].get(iid);
                if (item !== undefined) {
                    return item;
                }
            }

        }
    });

    return Dashboard;


    function setReferences(referenceArray){
        referenceArray.forEach(function(reference){
            if(reference.prototype.id !== undefined){
                this._references[reference.prototype.id] = reference;
            }
        }.bind(this));
    }
    function setColumnOptions() {
        this._layoutOptions = this._availableLayouts[this._layout];
    }
    function triggerChange() {
        this.eventBus.publish('dashboard:change', this);
    }

    function renderColumns() {
        this.getColumns().forEach(function (column) {
            column.attachTo(this.view.getContent());
        }.bind(this));
    }

    function setColumns(){
        // create columns depending on the selected layout
        this._columns = [];
        setColumnOptions.call(this);
        var maximizeItem = false;
        this._layoutOptions.forEach(function (columnOption, index) {
            var column = new Column({
                context: this.context,
                width: columnOption.width
            });

            // create the items instances
            if(this._items[index]){
                this._items[index].forEach(function (itemConfig, i) {
                    var item = setItem.call(this, itemConfig);
//                    item.attachTo(column.view.getContentDiv());
                    utils.attachUIElement(item, column.view.getContentDiv());
                    column._items.push(item);
                    // if the item should be maximized
                    if(item.options.maximize){
                        maximizeItem = item;
                    }
                }.bind(this));
            }
            this._columns.push(column);

        }.bind(this));
        // maximize item if any
        if(maximizeItem){
            maximizeItem.maximize();
        }
    }
    function setItem(itemConfig){
        // create item instance
        if (itemConfig.type === 'Dashboard') {
            itemConfig.config.references = this._references;
        }
        // replace string with reference if exists
        if (typeof itemConfig.type === 'string' && this._references[itemConfig.type] !== undefined) {
            itemConfig.type = this._references[itemConfig.type];
        }

        itemConfig.config.context = this.context;

        var item = new Item({
            header: itemConfig.header,
            content: new itemConfig.type(itemConfig.config || {}),
            maximizable: itemConfig.maximizable,
            maximize: itemConfig.maximize,
            iid: itemConfig.iid,
            closable: itemConfig.closable,
            closeText: this.options.closeText,
            maximizeText: this.options.maximizeText,
            minimizeText: this.options.minimizeText,
            settings: itemConfig.settings
        });

        // listen to items events
        item.addEventHandler('dropped', onDrop.bind(this));
        item.addEventHandler('pickedUp', onPickUp.bind(this));
        item.addEventHandler('dragged', onDrag.bind(this));
        item.addEventHandler('close', onClose.bind(this));
        item.addEventHandler('maximize', onMaximize.bind(this));
        item.addEventHandler('minimize', onMinimize.bind(this));
        item.addEventHandler('settings', onSettings.bind(this));

        return item;

    }

    function isTargetPlaceHolder(item) {
        return (this._targetColumn.targetPlaceHolder.getElement() === item || this._targetColumn.targetPlaceHolder.getElement()._getHTMLElement().contains(item));
    }
    function isOriginPlaceHolder(item) {
        return (this._originalColumn && (this._originalColumn.originPlaceHolder.getElement() === item || this._originalColumn.originPlaceHolder.getElement()._getHTMLElement().contains(item)));
    }


    function resetMaximizedElement() {
        delete this.maximizedItemColumn;
        delete this.maximizedItemIndex;
        delete this.maximizedItem;
    }

    function getColumnByItem(item) {
        var column;
        this.getColumns().forEach(function (col, index) {
            if (col.getItems().indexOf(item) !== -1) {
                column = col;
            }
        }.bind(this));
        return column
    }

    function onPickUp(e, draggedElement) {
        // add origin PlaceHolder
        this._originalColumn = getColumnByItem.call(this, draggedElement);

        this._originalColumn.addOriginPlaceHolder(this._originalColumn.getItems().indexOf(draggedElement));
    }

    function onDrag(e, draggedElement, targetElement) {
        getTargetColumnAndPosition.call(this, targetElement);
        // if there is no target column
        if (!this._targetColumn) {
            // remove target placeholder
            return clearTargetPlaceHolders.call(this);
        }
        // if the targetElement is the target placeholder then do nothing
        if (this._targetColumn && this._targetColumn.targetPlaceHolder && isTargetPlaceHolder.call(this, targetElement)) {
            return;
        }

        // it is not the origin placeholder then add a target placeholder
        if (!isOriginPlaceHolder.call(this, targetElement)) {
            this._targetColumn.addTargetPlaceHolder(this._targetPosition, draggedElement.header);
        } else {
            // clear target placeholders
            this._targetColumn.clearTargetPlaceHolder();
        }
    }

    function onDrop(e, draggedElement, target) {
        // if there is no target column
        if (!this._targetColumn) {
            // Do nothing
        // if the target is the origin placeholder
        } else if (isOriginPlaceHolder.call(this, target)) {
            // Do nothing
        // if target elements is a target placeholder then move item to the index of the place holder
        } else if (isTargetPlaceHolder.call(this, target)) {
            this._originalColumn.extract(this._originalColumn.getItems().indexOf(draggedElement));
            this._targetColumn.add(draggedElement,  this._targetColumn.targetPlaceHolderPosition);
            triggerChange.call(this);
        // if target column is the equal to original column then move items positio
        } else if (this._targetColumn == this._originalColumn) {
            this._originalColumn.move(draggedElement, this._targetPosition);
            triggerChange.call(this);
        // else move item to target column and position
        } else {
            this._originalColumn.extract(this._originalColumn.getItems().indexOf(draggedElement));
            this._targetColumn.add(draggedElement, this._targetPosition);
            triggerChange.call(this);
        }

        clearOriginPlaceHolders.call(this);
        delete this._originalColumn;
        delete this._targetColumn;
        delete this._targetPosition;
        core.Window.trigger('resize');
    }

    function onClose(item) {
        // get column
        var column = false;
        this.getColumns().forEach(function (col, index) {
            if (col._items.indexOf(item) !== -1) {
                column = col;
            }
        }.bind(this));
        if (column) {
            column.remove(column.getItems().indexOf(item));
        } else if (this.maximizedItem) {
            this.maximizedItem.destroy();
            resetMaximizedElement.call(this);
        }

        triggerChange.call(this);
    }

    function onMaximize(item) {
        // if there is a maximized item then minimize it
        if (this.maximizedItem) {
            this.maximizedItem.minimize();
        }
        this.maximizedItemColumn = getColumnByItem.call(this, item);
        this.maximizedItemIndex = this.maximizedItemColumn.getItems().indexOf(item);
        this.maximizedItem = this.maximizedItemColumn.extract(this.maximizedItemIndex);
//        this.maximizedItem.attachTo(this.view.getTop());
        utils.attachUIElement(this.maximizedItem, this.view.getTop());
        core.Window.trigger('resize');
    }


    function onMinimize(item) {
        if(this.maximizedItemColumn){
            this.maximizedItemColumn.add(this.maximizedItem, this.maximizedItemIndex);
        }else{
            this.getColumns()[0].add(this.maximizedItem, this.maximizedItemIndex);
        }
        core.Window.trigger('resize');
        resetMaximizedElement.call(this);
    }

    function onSettings(item) {

        // call the onSettings method of the widget if it exists, if not trigger 'dashboard:settings' on the event bus
        if (item.content.onSettings) {
            item.content.onSettings.call(item.content);
        } else {
            this.eventBus.publish('dashboard:settings', item.content, getColumnByItem.call(this, item));
        }
    }


    function getTargetColumnAndPosition(target) {
        this._targetColumn = false;
        this.getColumns().forEach(function (col, index) {
            if (col.getElement()._getHTMLElement().contains(target)) {
                this._targetColumn = col;
            }
        }.bind(this));

        if (this._targetColumn) {
            this._targetPosition = this._targetColumn.getItems().length;
            this._targetColumn.getItems().forEach(function (item, index) {
                if (item.getElement()._getHTMLElement() == target || item.getElement()._getHTMLElement().contains(target)) {
                    this._targetPosition = index;
                }
            }.bind(this));
        } else {
            this._targetPosition = false;
        }
    }


    function clearOriginPlaceHolders() {
        this.getColumns().forEach(function (col) {
            col.clearOriginPlaceHolders();
        });
    }


    function clearTargetPlaceHolders() {
        this.getColumns().forEach(function (col) {
            col.clearTargetPlaceHolder();
        });
    }
});

define('layouts/Dashboard',['layouts/dashboard/Dashboard'],function (main) {
                        return main;
                    });

