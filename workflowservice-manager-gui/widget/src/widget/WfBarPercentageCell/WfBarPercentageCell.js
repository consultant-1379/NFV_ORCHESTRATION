define([
    "widgets/table/Cell",
    "./WfBarPercentageCellView",
], function(Cell, View) {

    return Cell.extend({

        View: View,

        onCellReady: function (progressPercentage) {
        	
        },
        
        setValue: function(progressPercentage) {
        	if(progressPercentage === undefined || progressPercentage === null){
        		this.view.getPercentageIndicator().setText("N/A");	
        	}else{
        		this.view.getPercentageIndicator().setText(progressPercentage +"%");
        	}               
            if(progressPercentage == 0){
            	this.view.getBarPercentageCell().setStyle("width", "0px");
            }
            if(progressPercentage < 5 && progressPercentage > 0){
            	this.view.getBarPercentageCell().setStyle("width", "6px");                   
            }else{
            	this.view.getBarPercentageCell().setStyle("width", progressPercentage + "px");
            }
        } 
    });

});