define([
    "widgets/table/Cell",
    "widgets/Tooltip",
    "./WfDefinitionActionsCellView",
    "widgets/Button",
], function(Cell, Tooltip, View, Button) {

    return Cell.extend({

        View: View,

        onCellReady: function () {
            var diagramButton = this.view.getDiagramButton();
            diagramButton.addEventHandler("click", function (e) {
                 this.getEventBus().publish("showdiagram", this.getRow().getData());
            }, this);

            var diagramButtonTooltip = new Tooltip({
                parent: diagramButton,
                enabled: true,
                contentText: 'Show workflow diagram',
                modifiers: [{name: 'size', value: 'large'}]
            });
            diagramButtonTooltip.attachTo(this.getElement());
        }
    });
});