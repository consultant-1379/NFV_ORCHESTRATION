define([
    "widgets/table/Cell",
    "./WfInstanceProcessIconView",
], function(Cell, View) {

    return Cell.extend({

        View: View,
        
        setStatus: function(status){
        	if(status === "active"){
        		this.activeStatus();
        	}
        	else if(status === "cancelled"){
        		this.cancelledStatus();
        	}
        	
        },
        
        activeStatus: function(){
        	this.view.showActiveIcon();
        },
        
        cancelledStatus: function(){
        	this.view.showCancelledIcon();
        }
    });

});