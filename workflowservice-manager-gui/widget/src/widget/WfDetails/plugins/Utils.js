define([
    "jscore/core",
    "text!./WrappedHeader.html"
], function(core, wrappedHeaderTemplate) {

    return {
        wrapHeaderContent: function(cell) {
            var cellRoot = cell.getElement()._getHTMLElement();
            var className = "elTables-wrappedHeader";

            if (!cellRoot.classList.contains(className)) {

                var newElement = core.Element.parse(wrappedHeaderTemplate);
                var newContent = newElement.find(".ebTable-headerText")._getHTMLElement();

                // Transfer content over from original cell to new content holder
                while (cellRoot.hasChildNodes()) {
                    newContent.appendChild(cellRoot.removeChild(cellRoot.firstChild));
                }

                // Transfer the content of newElement to cell
                newElement = newElement._getHTMLElement();
                while (newElement.hasChildNodes()) {
                    cellRoot.appendChild(newElement.removeChild(newElement.firstChild));
                }

                cellRoot.classList.add(className);

            }
        },

        preventDefault: function(e) {
            e.preventDefault();
        }
    };


});