define([
    "jscore/core",
    "text!./wfInstanceProcessIcon.html",
    "styles!./wfInstanceProcessIcon.less"
    ], function(core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            },

            getStyle: function() {
                return style;
            },
            
            showActiveIcon: function(){
            	this.getIconCancelled().element.style.setProperty('display', "none", null);
            	this.getIconActive().element.style.setProperty('display', "block", null);
            },
            
            showCancelledIcon: function(){
            	this.getIconActive().element.style.setProperty('display', "none", null);
            	this.getIconCancelled().element.style.setProperty('display', "block", null);
            }
        });

    });