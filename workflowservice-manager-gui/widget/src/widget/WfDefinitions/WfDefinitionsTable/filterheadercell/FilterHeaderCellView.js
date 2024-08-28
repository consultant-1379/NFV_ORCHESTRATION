define([
    "jscore/core",
    "text!./FilterHeaderCell.html",
    "styles!./FilterHeaderCell.less"
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