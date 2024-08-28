define([
    "jscore/core",
    "text!./wfBarPercentageCell.html",
    "styles!./wfBarPercentageCell.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },

            getBarPercentageCellGrey: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarPercentageCell-contentTable-BarPercentageCellGrey");
            },
            
            getBarPercentageCell: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarPercentageCell-contentTable-BarPercentageCell");
            },
            
            getPercentageIndicator: function() {
                return this.getElement().find(".eaNFE_automation_UI-WfBarPercentageCell-contentTable-PercentageIndicator");
            }

        });

    });