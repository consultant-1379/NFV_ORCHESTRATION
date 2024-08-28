define([
    'jscore/core',
    'text!./WfCounter.html',
    'styles!./WfCounter.less'
], function (core, template, style) {

        return core.View.extend({

            getTemplate: function() {
                return template;
            }, 

            getStyle: function() {
                return style;
            },
            
            setCount: function(count){
            	this.getElement().find(".eaWorkflowManager-WfCounter-count").setText(count);
            },
            
            setCountLabel: function(countLabel){
            	this.getElement().find(".eaWorkflowManager-WfCounter-countLabel").element.innerHTML = countLabel;
            },
            
            getSpan: function(){
            	return this.getElement().find(".eaWorkflowManager-WfCounter-count");
            },
            
            getFont: function(){
            	var el = this.getElement().find(".eaWorkflowManager-WfCounter-count");
            	var style = window.getComputedStyle(el.element, null).getPropertyValue('font-size');
            	var fontSize = parseFloat(style); 
            	return fontSize;
            },
            
            setFont: function(size){
            	var el = this.getElement().find(".eaWorkflowManager-WfCounter-count");
            	el.element.style.setProperty('font-size', size+"rem", null);
            },
                       
            getCount: function(){
            	return this.getElement().find(".eaWorkflowManager-WfCounter-count").getText();
            },
            
            getCountLabel: function(){
            	return this.getElement().find(".eaWorkflowManager-WfCounter-countLabel");
            }
            
        });

});