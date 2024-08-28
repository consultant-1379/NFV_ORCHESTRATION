define([
    'jscore/core',
    'text!./linkCell.html',
    "styles!./linkCell.less"
], function (core, template, style) {

    return core.View.extend({

        getTemplate: function() {
            return template;
        },
        
        getStyle: function() {
            return style;
        },
        
        getRoot: function() {
            return this.getElement().find(".LinkCell");
        },
        
        getLink: function() {
            return this.getElement().find(".LinkCell-link");
        }
        
    });

});
