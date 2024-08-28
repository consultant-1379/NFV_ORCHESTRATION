define([
    "tablelib/Cell",
    "./CheckboxCellView"
], function (Cell, View) {

    return Cell.extend({

        View: View,

        eventName: "check",

        onViewReady: function() {
            // If checking the checkbox itself, trigger the event, it will always change
            this.getElement().find(".ebCheckbox").addEventHandler("click", function(e) {
                e.stopPropagation();
                this.trigger();
            }.bind(this));

            // Clicking the cell should also change the state of the checkbox
            this.getElement().addEventHandler("click", function(e) {
                e.stopPropagation();
                this.check(!this.isChecked(), true);
            }.bind(this));
        },

        check: function(checked, trigger) {
            var cb = this.getElement().find(".ebCheckbox");
            if (cb.getProperty("checked") !== checked) {
                cb.setProperty("checked", checked);
                if (trigger) {
                    this.trigger();
                }
            }
        },

        setValue: function() {
            // Override the original setValue implementation
        },

        isChecked: function() {
            return this.getElement().find(".ebCheckbox").getProperty("checked");
        },

        trigger: function() {
            this.getTable().trigger(this.eventName, this.getRow(), this.isChecked());
        }

    });

});