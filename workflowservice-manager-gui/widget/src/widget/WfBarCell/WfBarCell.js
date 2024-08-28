define([
    "widgets/table/Cell",
    "./WfBarCellView",
], function(Cell, View) {

    return Cell.extend({

        View: View,

        onCellReady: function () {

        },
        
        setValue: function(percentage) {
            this.getElement().setText(percentage);
            this.view.getBarPercentageCell().setStyle("width", percentage + "px");
        }
    });

});