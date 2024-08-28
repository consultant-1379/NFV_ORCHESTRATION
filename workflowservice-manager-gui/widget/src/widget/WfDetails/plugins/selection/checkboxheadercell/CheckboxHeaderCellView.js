define([
    "jscore/core",
    "text!./CheckboxHeaderCell.html",
    "styles!./CheckboxHeaderCell.less"
], function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        },

        getCheckboxStatus: function() {
            return this.getElement().find(".ebCheckbox-inputStatus");
        }

    });

});