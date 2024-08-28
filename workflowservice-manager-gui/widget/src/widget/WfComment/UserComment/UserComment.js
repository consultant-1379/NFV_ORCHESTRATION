define([
    "jscore/core",
    "jscore/ext/mvp",
    "./UserCommentView",
    "i18n/dateTime"
    ], function(core, mvp, View, dateTime) {

       return core.Widget.extend({

    	   View: View,

            onViewReady: function() {
            	
            	this.userId = this.options.userComment.getAttribute("userId");
            	this.comment = this.options.userComment.getAttribute("comment");
            	this.timeCreated = this.options.userComment.getAttribute("createdTime");
            	
            	var date = new Date(this.timeCreated);
            	
            	this.view.getUserName().setText(this.userId);
            	this.view.getComment().setText("\""+this.comment+"\"");
            	this.view.getTime().setAttribute('title', dateTime(date).format('LLLL'));
            	this.view.getTime().setText(dateTime(date).fromNow());
            }
       });
});