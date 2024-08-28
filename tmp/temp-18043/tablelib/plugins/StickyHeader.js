/* Copyright (c) Ericsson 2014 */

define('text!tablelib/plugins/stickyheader/StickyHeader.html',[],function () { return '<table class=\'ebTable\' style=\'background-color:white;width:100%;position:absolute;z-index:1000;overflow-y:hidden;\'>\n</table>';});

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

define('tablelib/plugins/stickyheader/StickyHeader',['jscore/core','../Plugin','tablelib/Row','tablelib/HeaderCell','text!./StickyHeader.html'],function (core, Plugin, Row, HeaderCell, template) {

    return Plugin.extend({

        injections: {
            before: {
                onTableReady: onTableReady,
                setColumnWidth: setColumnWidth,
                setWidth: setWidth
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

        var table = this.getTable();
        var topOffset = this.getOptions().topOffset || 0;
        var stickyHeader = core.Element.parse(template);
        var pret = table.view.getPreTable();

        // Calling getHeaderRow() will return the original header row, but calling getCells() will return the cells that have been moved
        this.cloneHeader = transferAndCloneHead(table.view.getTable(), stickyHeader);
        table.view.getPreTable().append(stickyHeader);

        function checkHeaderPosition() {

            if (this.cloneHeader) {
                var fht = this.cloneHeader.getPosition().top;
                if (fht < 45 + topOffset) {
                    var top_delta = fht < 0? 45 + topOffset + Math.abs(fht) : (45 + topOffset) - Math.abs(fht);

                    if (!stickyHeader.isFloating) {
                        stickyHeader.isFloating = true;
                        stickyHeader.setStyle("top", top_delta + "px");
                    }

                    if (parseInt(stickyHeader.getStyle("top")) !== top_delta) {
                        stickyHeader.setStyle("top", top_delta + "px");
                    }

                } else {
                    stickyHeader.isFloating = false;
                    if (stickyHeader.getStyle("top") !== "0")
                        stickyHeader.setStyle("top", "0");
                }


                if (stickyHeader.getPosition().left !== this.cloneHeader.getPosition().left) {
                    stickyHeader.setStyle("left", this.cloneHeader.getPosition().left - pret.getPosition().left + "px");
                }

                // Resize header height if needed
                if (table.getHeaderRow().getElement().getProperty("offsetHeight") !== table.view.getPreTable().find("tr").getProperty("offsetHeight")) {
                    table.getHeaderRow().getElement().setStyle("height", table.view.getPreTable().find("tr").getProperty("offsetHeight") + "px");
                }
            }


            requestAnimationFrame(checkHeaderPosition.bind(this));
        }

        requestAnimationFrame(checkHeaderPosition.bind(this));


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

define('tablelib/plugins/StickyHeader',['tablelib/plugins/stickyheader/StickyHeader'],function (main) {
                        return main;
                    });

