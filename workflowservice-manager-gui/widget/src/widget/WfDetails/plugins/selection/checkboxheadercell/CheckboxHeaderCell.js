define([
    "../checkboxcell/CheckboxCell",
    "./CheckboxHeaderCellView"
], function (CheckboxCell, View) {

    return CheckboxCell.extend({

        View: View,

        eventName: "checkheader",

        onCellReady: function() {
            this.getTable().setColumnWidth(this.getIndex(), "40px");
        },

        setTriple: function(isTriple) {
            if (isTriple) {
                this.view.getCheckboxStatus().setModifier("triple");
            } else {
                this.view.getCheckboxStatus().removeModifier("triple");
            }
        }

    });

});