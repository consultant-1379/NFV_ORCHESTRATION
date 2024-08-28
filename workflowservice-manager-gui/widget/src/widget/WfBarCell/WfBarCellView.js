define([
    "jscore/core",
    "text!./wfBarCell.html",
    "styles!./wfBarCell.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getBarPercentageCellGrey: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarCell-contentTable-BarCellGrey");
            },
            
            getBarPercentageCell: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarCell-contentTable-BarCell");
            }

        });

    });