define([
    "widgets/ItemsControl",
    "./FilterOptionsView"
], function (ItemsControl, View) {

    return ItemsControl.extend({

        View: View,

        onControlReady: function() {

            // Hard coding the options
            this.options.width = "auto";

/*            this.setItems([
                {name: "="}
                ,
                {name: ">"},
                {name: "<"},
                {name: "!="}
            ]);*/

            // Set the default option
            this.view.setSelected("=");
        },

        onItemSelected: function(selectedValue) {
        		this.view.setSelected(selectedValue.name);
        		this.trigger("change");
        },

        getValue: function() {
            return this.view.getSelected();
        }

    })

})