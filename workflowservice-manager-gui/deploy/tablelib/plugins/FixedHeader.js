/* Copyright (c) Ericsson 2014 */

define('text!tablelib/plugins/fixedheader/FixedHeader.html',[],function () { return '<div>\n    <div style="overflow: hidden">\n        <table class=\'ebTable\' style=\'background-color:white;width:100%;position:relative;z-index:1000;overflow-y:hidden;\'>\n        </table>\n    </div>\n</div>';});

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

define('tablelib/plugins/fixedheader/FixedHeader',['jscore/core','../Plugin','tablelib/Row','tablelib/HeaderCell','text!./FixedHeader.html'],function (core, Plugin, Row, HeaderCell, template) {

    return Plugin.extend({

        injections: {
            before: {
                onTableReady: onTableReady,
                setColumnWidth: setColumnWidth,
                setWidth: setWidth,
                addRow: checkVertScrollbar,
                removeRow: checkVertScrollbar
            }
        }
    });

    function transferAndCloneHead(source, target) {
        var original_thead = source.find("thead")._getHTMLElement();
        var clone_thead = original_thead.cloneNode(true);
        target._getHTMLElement().appendChild(original_thead);
        source._getHTMLElement().insertBefore(clone_thead, source._getHTMLElement().firstChild);
        return core.Element.wrap(clone_thead);
    }

    function onTableReady() {
        var i;
        var table = this.getTable();
        this.fixedHeader = core.Element.parse(template);

        // This modification ensures that plugins like the fixed header will have their scrollbar on the edge of the table
        table.getElement().setStyle({
            "max-width": "100%",
            "display": "inline-block"
        });

        // Perform the clone
        this.cloneHeader = transferAndCloneHead(table.view.getTable(), this.fixedHeader.find("table"));
        table.view.getPreTable().append(this.fixedHeader);

        // Iterate over the clone headers and change the styles
        var cloneThs = this.cloneHeader._getHTMLElement().querySelectorAll("th");
        for (i = 0; i < cloneThs.length; i++) {
            var th = cloneThs[i];
            th.innerHTML = "";
            th.style.border = "none";
            th.style.paddingTop = 0;
            th.style.paddingBottom = 0;
        }

        // All of the <tr> tags need to be set to 0 after wiping their content above
        var cloneTrs = this.cloneHeader._getHTMLElement().querySelectorAll("tr");
        for (i = 0; i < cloneTrs.length; i++) {
            cloneTrs[i].style.height = 0;
        }

        // Fixed header tables need to have a height defined.
        table.view.getWrapper().setStyle({
            "overflow-y": "auto"
        });

        if (this.options.height) {
            table.view.getWrapper().setStyle({
                "height": this.options.height
            });
            setScrollbarStyle.call(this, true);
        }

        if (this.options.maxHeight) {
            table.view.getWrapper().setStyle({
                "max-height": this.options.maxHeight
            });
            checkVertScrollbar.call(this);
        }

        table.view.getWrapper().addEventHandler("scroll", function() {
            this.fixedHeader.find("div").setProperty("scrollLeft", table.view.getWrapper().getProperty("scrollLeft"));
        }.bind(this));

    }


    function checkVertScrollbar() {
        if (this.fixedHeader && this.options.maxHeight !== undefined) {
            if (this.getTable().view.getWrapper().getProperty("offsetHeight") >= parseInt(this.options.maxHeight)) {
                setScrollbarStyle.call(this, true);
            } else {
                setScrollbarStyle.call(this, false);
            }
        }

    }

    function setScrollbarStyle(value) {
        if (value) {
            this.fixedHeader.setStyle({
                width: "calc(100% - " + getScrollbarWidth() + "px)"
            });
        } else {
            this.fixedHeader.setStyle({
                width: "100%"
            });
        }

    }

    function getScrollbarWidth() {
        var outer = document.createElement("div");
        outer.style.visibility = "hidden";
        outer.style.width = "100px";
        outer.style.msOverflowStyle = "scrollbar"; // needed for WinJS apps
        document.body.appendChild(outer);
        var widthNoScroll = outer.offsetWidth;
        outer.setAttribute("class", "eb_scrollbar");
        outer.style.overflow = "scroll";
        var inner = document.createElement("div");
        inner.style.width = "100%";
        outer.appendChild(inner);
        var widthWithScroll = inner.offsetWidth;
        outer.parentNode.removeChild(outer);
        return widthNoScroll - widthWithScroll;
    }

    function setColumnWidth(index, width) {
        if (this.cloneHeader) {
            this.cloneHeader.find("tr").children()[index].setStyle("width", width);
        }
    }

    function setWidth(width) {
        this.getTable().view.getPreTable().find("table").setStyle("width", width);
    }

});

define('tablelib/plugins/FixedHeader',['tablelib/plugins/fixedheader/FixedHeader'],function (main) {
                        return main;
                    });

