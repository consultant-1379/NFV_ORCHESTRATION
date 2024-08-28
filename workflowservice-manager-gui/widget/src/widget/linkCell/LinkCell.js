define([
    "widgets/table/Cell",
    './LinkCellView',
    "jscore/ext/locationController"
], function(Cell, View, LocationController) {

    return Cell.extend({
        
        View: View,
        
        onCellReady: function() {
            var link = this.view.getLink();
            link.addEventHandler('click', function () {
                var locCtlr = new LocationController();
                var value = this.value;
                var url = 'workflowsinstances/' + this.options.model.getAttribute("id") + "/" + this.options.model.getAttribute("name");
                locCtlr.setLocation(url);
            }.bind(this));
            
        },
        
        setValue: function(value) {
           this.value = value;
           this.view.getLink().setText(value);
        }

    });
});