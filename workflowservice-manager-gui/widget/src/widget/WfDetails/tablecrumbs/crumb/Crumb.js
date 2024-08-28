define([
	"jscore/core",
	"./CrumbView",
	"widgets/ComponentList/ComponentList" 
], function (core, View, ComponentList) {
		
	return core.Widget.extend({
		View: View,

		onViewReady: function() {
			this.getElement().find("p").setText(this.options.name);
			this.getElement().find("p").addEventHandler("click", this.options.action);
			
			this.items = [ {
				name : 'Create SGW',
				url : '#item11'
			}, {
				name : 'Create PGW',
				url : '#item12'
			}, {
				name : 'Create MME',
				url : '#item13'
			}, {
				name : 'Create SAPC',
				url : '#item13'
			} ];
			
			if(this.items.length == 1){
//				this.view.getDropDownArrow().setStyle("display", "none");
			}
			
//			if(this.items.length > 1) {
////				this.view.getDropDownArrow().setStyle("display", "inline-block");
//				this.view.getDropDownArrow().addEventHandler('click',
//						function() {
//							var combo = new ComponentList({
//								width : "100px",
//								container : this.view.getDropDownArrow(),
//								parent : this.view.getDropDownArrow(),
//								items : this.items
//							});
//							combo.show();
//						}, this);
//			}
		}

	});

});