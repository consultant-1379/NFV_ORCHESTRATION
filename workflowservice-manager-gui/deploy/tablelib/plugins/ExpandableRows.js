/* Copyright (c) Ericsson 2014 */

define('text!tablelib/plugins/expandablerows/expandercell/ExpanderCell.html',[],function () { return '<td>\n\t<i class="ebIcon ebIcon_interactive ebIcon_rightArrow"></i>\n</td>';});

define('tablelib/plugins/expandablerows/expandercell/ExpanderCellView',['jscore/core','text!./ExpanderCell.html'],function (core, template) {

	return core.View.extend({

		getTemplate: function() {
			return template;
		},

		getIcon: function() {
			return this.getElement().find(".ebIcon");
		}

	});

});

define('tablelib/plugins/expandablerows/expandercell/ExpanderCell',['tablelib/Cell','./ExpanderCellView'],function (Cell, View) {

	return Cell.extend({

		View: View,

		onCellReady: function() {
			this._expanded = false;
			this.getElement().addEventHandler("click", this.toggle.bind(this));
		},

		setValue: function() {
			// Don't want it to be overridden
		},

		toggle: function(e) {
			e.stopPropagation();

			this._expanded = !this._expanded;
			var oldIcon = this._expanded? "rightArrow" : "downArrow";
			var newIcon = this._expanded? "downArrow" : "rightArrow";

			this.view.getIcon().removeModifier(oldIcon);
			this.view.getIcon().setModifier(newIcon);

			if (this._expanded && !this._content) {
				this.onExpand();
			} else if (this._expanded) {
				this.onExpand();
			} else {
				this.onCollapse();
			}

		},
	});

});

define('tablelib/plugins/Plugin',['jscore/core'],function (core) {

    var Plugin = function(options) {
        this.options = options || {};
        this.init.apply(this, arguments);
    };

    Plugin.prototype.init = function(){};
    Plugin.prototype.getOptions = function() {
        return this.options;
    };

    Plugin.prototype.getTable = function() {
        return this._table;
    };
    Plugin.prototype.injections = {
        before: {},
        after: {},
        newMethods: {}
    };

    Plugin.extend = core.extend;

    return Plugin;

});

define('tablelib/plugins/expandablerows/ExpandableRows',['jscore/core','../Plugin','./expandercell/ExpanderCell','tablelib/Row','jscore/ext/utils/base/underscore'],function (core, Plugin, ExpanderCell, Row, _) {

    var template = "<tr class='ebTable-expandableRow'>" +
                        "<td colspan='{{colspan}}'>" +
                            "<div></div>" +
                        "</td>" +
                    "</tr>";

    return Plugin.extend({

        injections: {

            before: {
                onViewReady: onViewReady
            },

            after: {
                addRow: addRow
            }

        }

    });

    function onViewReady() {
        this.getTable()._columns.unshift({
            cellType: ExpanderCell,
            width: "40px"
        });
    }

    function addRow(obj, index) {
        var table = this.getTable();
        var row = table.getRows()[index !== undefined? index : table.getRows().length - 1];

        // Create the expandable row element and attach it
        var expandableEl = core.Element.parse(template.replace("{{colspan}}", table._columns.length));
        // Implementing the attach method for the row, which is called when the row is attached itself
        expandableEl.attach = function() {
            var nativeRowEl = row.getElement()._getHTMLElement();
            nativeRowEl.parentNode.insertBefore(expandableEl._getHTMLElement(), nativeRowEl.nextSibling);
        };
        expandableEl.attach();

        // Subscribe to events on the expander cell
        var expanderCell = getExpanderCell(row);
        expanderCell.onExpand = expand.bind(this, row, expandableEl);
        expanderCell.onCollapse = collapse.bind(this, row, expandableEl);

        // Overriding methods for the purposes of sorting and setting the table with new data
        row.detach = function() {
            Row.prototype.detach.call(row);
            expandableEl.detach();
        };

        row.attach = function() {
            Row.prototype.attach.call(row);
            expandableEl.attach();
        };

        // TODO: Fix up this to __destroy
        row.destroy = function() {
            Row.prototype.destroy.call(row);
            expandableEl.remove();
        };
    }

    function expand(row, expandableRow) {
        var div = expandableRow.find("div");

        // If an animation is already occurring, we don't want it to conflict
        if (div._timeout) {
            div.removeEventHandler(div._timeout);
        }

        // Instantiate the content, passing the row data
        if (!div._contentInstance) {
            var args = _.clone(this.options.args || {});
            args.row = row;
            div._contentInstance = new this.options.content(args);
            div._contentInstance.attachTo(div);
        }

        // Display the row
        div._contentInstance.attach();
        expandableRow.setStyle("display", "table-row");
        expandableRow.setModifier("expanded");

        // Animate the height of the div which contains the content
        var height = div._contentInstance.getElement().getProperty("offsetHeight") + "px";
        div.setStyle("height", height);

        // When the animation is done, change the height to auto to allow for dynamic height changes
        div._timeout = div.addEventHandler("transitionend", function() {
            div.setStyle("height", "auto");
        }.bind(this));
    }

    function collapse(row, expandableRow) {
        var div = expandableRow.find("div");
        div.setStyle("height", div.getProperty("offsetHeight") + "px"); // needed as a start point for animation

         // Remove callback for existing animation if any
        if (div._timeout) {
            div.removeEventHandler(div._timeout);
        }

         // Need to skip a frame to allow the div change above to apply before animation
         requestAnimationFrame(function() {
            requestAnimationFrame(function() {
                div.setStyle("height", 0);
                div._timeout = div.addEventHandler("transitionend", function() {
                    div._contentInstance.detach();
                    expandableRow.setStyle("display", "none");
                    expandableRow.removeModifier("expanded");
                });
            });
        });
    }

    function getExpanderCell(row) {
        var cells = row.getCells();
        for (var i = 0; i < cells.length; i++) {
            if (cells[i] instanceof ExpanderCell) {
                return cells[i];
            }
        }
    }

});

define('tablelib/plugins/ExpandableRows',['tablelib/plugins/expandablerows/ExpandableRows'],function (main) {
                        return main;
                    });

