/* Copyright (c) Ericsson 2014 */

define('styles!tablelib/tablesettings/placeholderitem/PlaceHolderItem.less',[],function () { return '.elTablelib-PlaceHolderItem {\n  width: 100%;\n  height: 30px;\n  background-color: #ededed;\n}\n';});

define('text!tablelib/tablesettings/placeholderitem/PlaceHolderItem.html',[],function () { return '<li class="elTablelib-PlaceHolderItem">\n</li>';});

define('tablelib/tablesettings/placeholderitem/PlaceHolderItemView',['jscore/core','text!./PlaceHolderItem.html','styles!./PlaceHolderItem.less'],function (core, template, style) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return style;
        }

    });

});

define('tablelib/tablesettings/placeholderitem/PlaceHolderItem',['jscore/core','./PlaceHolderItemView'],function (core, View) {

    return core.Widget.extend({

        View: View

    });

});

define('styles!tablelib/tablesettings/tablesettingsitem/TableSettingsItem.less',[],function () { return '.elTablelib-TableSettingsItem {\n  background-color: white;\n  height: 30px;\n}\n.elTablelib-TableSettingsItem:first-child .elTablelib-TableSettingsItem-up,\n.elTablelib-TableSettingsItem:last-child .elTablelib-TableSettingsItem-down {\n  opacity: 0.3;\n  cursor: default;\n  pointer-events: none;\n}\n.elTablelib-TableSettingsItem-label {\n  margin: 5px 0;\n  width: calc(100% - 36px);\n  display: inline-block;\n}\n.elTablelib-TableSettingsItem-buttons {\n  padding: 15px 0;\n  border-bottom: 1px solid #E6E6E6;\n  text-align: right;\n}\n.elTablelib-TableSettingsItem-arrows {\n  border-bottom: 1px solid #E6E6E6;\n  padding: 5px 0;\n  width: 36px;\n  display: inline-block;\n}\n.elTablelib-TableSettingsItem-up,\n.elTablelib-TableSettingsItem-down {\n  cursor: pointer;\n}\n';});

define('template!tablelib/tablesettings/tablesettingsitem/TableSettingsItem.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  
  return "checked=\"true\"";}

  buffer += "<li class=\"elTablelib-TableSettingsItem\">\n    <label class=\"elTablelib-TableSettingsItem-label\">\n        <input class=\"ebCheckbox\" type=\"checkbox\" ";
  foundHelper = helpers.visible;
  stack1 = foundHelper || depth0.visible;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "/>\n\n        <span class=\"ebCheckbox-inputStatus\"></span>\n        <span class=\"ebCheckbox-label\">\n            <span class=\"elTablelib-TableSettingsItem-labelText ebCheckbox-label-text\">";
  foundHelper = helpers.title;
  stack1 = foundHelper || depth0.title;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "title", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</span>\n        </span>\n    </label><span class=\"elTablelib-TableSettingsItem-arrows\">\n        <i class=\"ebIcon ebIcon_upArrowLarge elTablelib-TableSettingsItem-up\"></i>\n        <i class=\"ebIcon ebIcon_downArrowLarge elTablelib-TableSettingsItem-down\"></i>\n    </span>\n</li>";
  return buffer;});});

define('tablelib/tablesettings/tablesettingsitem/TableSettingsItemView',['jscore/core','template!./TableSettingsItem.html','styles!./TableSettingsItem.less'],function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template(this.options);
        },

        getStyle: function() {
            return styles;
        },

        getCheckbox: function() {
            return this.getElement().find(".ebCheckbox");
        },

        getUp: function() {
            return this.getElement().find(".elTablelib-TableSettingsItem-up");
        },

        getDown: function() {
            return this.getElement().find(".elTablelib-TableSettingsItem-down");
        }

    });

});

define('tablelib/tablesettings/tablesettingsitem/TableSettingsItem',['jscore/core','./TableSettingsItemView'],function (core, View) {

    return core.Widget.extend({

        view: function() {
            return new View(this.options);
        },

        onViewReady: function() {
            this.column = this.options;

            this.view.getCheckbox().addEventHandler("click", function(e) {
                // If we're dragging, prevent this from executing and revert checkbox

                // Problem, drag end triggers before this does
                // Meaning that the checkbox will get checked
                if (this._isDragging) {
                    this.view.getCheckbox().setProperty("checked", this.column.visible);
                    return;
                }
                this.column.visible = this.view.getCheckbox().getProperty("checked");
            }.bind(this));

            this.view.getUp().addEventHandler("click", function() {
                this.trigger("up", this.column);
            }.bind(this));

            this.view.getDown().addEventHandler("click", function() {
                this.trigger("down", this.column);
            }.bind(this));
        }

    });

});

define('styles!tablelib/tablesettings/TableSettings.less',[],function () { return '.elTablelib-TableSettings {\n  margin: 10px 0;\n}\n';});

define('text!tablelib/tablesettings/TableSettings.html',[],function () { return '<ul class="ebList elTablelib-TableSettings">\n</ul>';});

define('tablelib/tablesettings/TableSettingsView',['jscore/core','text!./TableSettings.html','styles!./TableSettings.less'],function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        }

    });

});

define('tablelib/tablesettings/TableSettings',['jscore/core','./TableSettingsView','./tablesettingsitem/TableSettingsItem','jscore/ext/utils/base/underscore','./placeholderitem/PlaceHolderItem'],function (core, View, Item, _, PlaceHolderItem) {

    return core.Widget.extend({

        View: View,

        onViewReady: function() {
            // Cloning because no changes should be applied until the user explicitly says so
            // Table settings may in future modify something that doesn't have a title.
            this.columns = _.map(this.options.columns, _.clone);

            this.items = [];
            this.getElement().setStyle("user-select", "none");

            this.columns.forEach(function (column) {
                if (column.visible === undefined) {
                    column.visible = true;
                }

                if (column.title !== undefined) {
                    var item = new Item(column);

                    item.addEventHandler("up", function() {
                        up.call(this, item, column);
                    }.bind(this));

                    item.addEventHandler("down", function() {
                        down.call(this, item, column);
                    }.bind(this));

                    item.attachTo(this.getElement());
                    this.items.push(item);

                    item.getElement().setStyle("user-select", "none");

                    item.getElement().addEventHandler("mousedown", function(e) {
                        // TODO : add check to prevent dragging of multiple rows at once
                        document.addEventListener("mousedown", preventDefault, true);
                        this._dragTimeout = setTimeout(function() {
                            startDrag.call(this, e, item);
                            item.getElement().removeEventHandler(this._mouseUpBeforeTimeoutEvent);
                        }.bind(this), 100);

                        this._mouseUpBeforeTimeoutEvent = item.getElement().addEventHandler("mouseup mouseleave", function() {
                            clearTimeout(this._dragTimeout);
                            document.removeEventListener("mousedown", preventDefault, true);
                            item.getElement().removeEventHandler(this._mouseUpBeforeTimeoutEvent);
                        }.bind(this));
                    }.bind(this));

                }
            }.bind(this));
        },

        /**
         * Gets the updated column data.
         *
         * @method getUpdatedColumns
         * @return {Array<Object>} columns
         */
        getUpdatedColumns: function() {
            return this.columns;
        },
        /**
         * Selects or unselects all items depending in value provided.
         *
         * @method setCheckboxes
         * @param {Boolean} checked
         */
        setCheckboxes: function(checked){
            this.items.forEach(function(item){
                var checkbox = item.view.getCheckbox();
                if(checkbox.getProperty('checked') !== checked){
                    checkbox.trigger('click');
                }
            });
        }

    });



    function up(item, column) {
        move.call(this, item, column, -1);
    }

    function down(item, column) {
        move.call(this, item, column, 1);
    }

    function move(item, column, delta) {
        var colIndex = getColumnIndex.call(this, column);
        var itemIndex = getItemIndex.call(this, item);

        var newColIndex = colIndex + delta;
        var newItemIndex = itemIndex + delta;

        this.items.splice(itemIndex, 1);
        this.columns.splice(colIndex, 1);
        item.detach();

        var elAtIndex = this.getElement()._getHTMLElement().children[newItemIndex];
        this.getElement()._getHTMLElement().insertBefore(item.getElement()._getHTMLElement(), elAtIndex);
        this.items.splice(newItemIndex, 0, item);
        this.columns.splice(newColIndex, 0, column);
    }

    function getColumnIndex(column) {
        for (var i = 0; i < this.columns.length; i++) {
            if (this.columns[i] === column) {
                return i;
            }
        }
    }

    function getItemIndex(item) {
        for (var i = 0; i < this.items.length; i++) {
            if (this.items[i] === item) {
                return i;
            }
        }
    }

    function startDrag(e, item) {
        if (e.originalEvent.type === "mousedown" && e.originalEvent.which !== 1) {
            return;
        }

        this._mouseMoveEvent = core.Element.wrap(document.body).addEventHandler("mousemove", updateDrag.bind(this, item));
        this._mouseUpEvent = core.Element.wrap(document.body).addEventHandler("mouseup mouseleave", endDrag.bind(this, item));
        
        this._listPos = this.getElement().getPosition();
        this._dragDeltaX = item.getElement().getPosition().left - e.originalEvent.clientX;
        this._dragDeltaY = item.getElement().getPosition().top - e.originalEvent.clientY;
        this._startDragIndex = this._currentDragIndex = this.items.indexOf(item);
    }

    function findListIndexFromCoordinates(e) {
        var itemHeight = 30;
        var distanceFromListTop = e.originalEvent.clientY - this._listPos.top;
        var index = Math.min(Math.max(0, parseInt(distanceFromListTop / itemHeight)), this.items.length);
        return index;
    }

    function updateDrag(item, e) {
        if (!item._isDragging) {
            item._isDragging = true;

            this._placeHolder = new PlaceHolderItem();
            attachAt.call(this, this._placeHolder, this._startDragIndex);

            item.getElement().setStyle({
                position: "fixed",
                zIndex: 100,
                width: item.getElement().getProperty("offsetWidth") + "px",
                top: e.originalEvent.clientY + this._dragDeltaY + "px",
                left: e.originalEvent.clientX + this._dragDeltaX + "px"
            });
        }

        item.getElement().setStyle({
            top: e.originalEvent.clientY + this._dragDeltaY + "px",
            left: e.originalEvent.clientX + this._dragDeltaX + "px"
        });

        this._currentDragIndex = findListIndexFromCoordinates.call(this, e);

        var visualDragIndex = this._currentDragIndex;
        if (visualDragIndex > this._startDragIndex) {
            visualDragIndex++; // dragged element needs to be taken into account
        }
        attachAt.call(this, this._placeHolder, visualDragIndex );
   }

    function attachAt(widget, index) {
        var el = widget.getElement()._getHTMLElement();
        var other = this.items[index];
        var table = this.getElement()._getHTMLElement();

        if (other) {
            other = other.getElement()._getHTMLElement();
            table.insertBefore(el, other);
        } else {
            widget.detach();
            widget.attachTo(this.getElement());
        }
    }


    function endDrag(item, e) {
        if (item._isDragging) {
            item._isDragging = false;
            item.getElement().setStyle({
                width: "inherit",
                position: "relative",
                top: "0",
                left: "0",
                zIndex: "inherit"
            });

            this._placeHolder.destroy();
            attachAt.call(this, item, this._currentDragIndex);
            move.call(this, item, this.columns[this._startDragIndex], this._currentDragIndex - this._startDragIndex);
        }
       
        this._mouseMoveEvent.destroy();
        this._mouseUpEvent.destroy();
        document.removeEventListener("mousedown", preventDefault, true);
    }

    function preventDefault(e) {
        e.preventDefault();
    }
});

define('tablelib/TableSettings',['tablelib/tablesettings/TableSettings'],function (main) {
                        return main;
                    });

