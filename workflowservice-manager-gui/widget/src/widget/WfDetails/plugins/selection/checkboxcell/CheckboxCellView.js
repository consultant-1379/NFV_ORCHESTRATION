define([
    "jscore/core",
    "text!./CheckboxCell.html",
    "styles!./CheckboxCell.less"
], function (core, template, styles) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return styles;
        }
    });

});