define([
	"jscore/core",
	"./TableCrumbsView",
	"./crumb/Crumb"
], function (core, View, Crumb) {
	
	return core.Widget.extend({

		View: View,

		init: function() {
			this._arrow = new Crumb({
				name: "..."
			});
			this._crumbs = [];
		},

		onViewReady: function() {
			core.Window.addEventHandler("resize", this.draw.bind(this));
		},

		onDOMAttach: function() {
			this.draw();
		},

		add: function(data) {
			var crumb = new Crumb(data);
			this._crumbs.push(crumb);
			crumb.getElement().find("p").addEventHandler("click", crumbClicked.bind(this, this._crumbs.length - 1));
			this.draw();
		},

		draw: function() {
			var ul = this.getElement().find("ul");
			var max_width = this.getElement().getProperty("offsetWidth");
			
			this.detachReattachAll();

			var list_width = ul.getProperty("offsetWidth") + 16;
			if (list_width > max_width && max_width !== 0) {

				// Attach the arrow
				var arrowEl = this._arrow.getElement()._getHTMLElement();
				this._arrow.attachTo(ul);
				ul._getHTMLElement().insertBefore(arrowEl, ul._getHTMLElement().firstChild);


				var arrowCrumbIndex = 0;
				var arrowClick = function() {
					this._crumbs[arrowCrumbIndex].options.action();
					crumbClicked.call(this, arrowCrumbIndex);
				};
				arrowEl.onclick = arrowClick.bind(this);

				// Start detaching crumbs
				for (var j = 0; j < this._crumbs.length; j++) {
					if (ul.getProperty("offsetWidth") + 16 > max_width) {
						this._crumbs[j].detach();
					} else {
						arrowCrumbIndex = j - 1;
						break;
					}
				}
			}
		},

		detachReattachAll: function() {
			// Detach everything
			var ul = this.getElement().find("ul");
			this._crumbs.forEach(function(crumb) {
				crumb.detach();
			});
			this._arrow.detach();
			for (var i = 0; i < this._crumbs.length; i++) {
				this._crumbs[i].attachTo(ul);
			}
		}
	});


	function crumbClicked(index) {
		for (var i = 0; i < this._crumbs.length; i++) {
			if (i > index) {
				this._crumbs[i].destroy();
			}
		}

		this._crumbs.splice(index + 1);
		this.draw();
	}

});