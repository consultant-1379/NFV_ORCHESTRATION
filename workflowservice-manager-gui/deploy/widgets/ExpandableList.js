/* Copyright (c) Ericsson 2014 */

define('styles!widgets/ExpandableList/ExpandableListItem/_expandableListItem.less',[],function () { return '.elWidgets-ExpandableListItem-link {\n  display: block;\n  color: #333;\n  font-size: 1.2rem;\n  line-height: 1.4rem;\n  padding: 6px 0;\n}\n';});

define('template!widgets/ExpandableList/ExpandableListItem/_expandableListItem.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, foundHelper, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;


  buffer += "<li class=\"elWidgets-ExpandableListItem\">\n    <a class=\"elWidgets-ExpandableListItem-link\" href=\"#\">";
  foundHelper = helpers.label;
  stack1 = foundHelper || depth0.label;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "label", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</a>\n</li>";
  return buffer;});});

define('widgets/ExpandableList/ExpandableListItem/ExpandableListItemView',['jscore/core','template!./_expandableListItem.html','styles!./_expandableListItem.less'],function (core, template, styles) {
    'use strict';

    var ExpandableListItemView = core.View.extend({

        afterRender: function () {
            this.link = this.getElement().find('.' + ExpandableListItemView.EL_ITEM_LINK);
        },

        getTemplate: function () {
            return template(this.options);
        },
        
        getStyle: function () {
            return styles;
        },

        getLink: function () {
            return this.link;
        }
        
    }, {
        EL_ITEM_LINK: 'elWidgets-ExpandableListItem-link'
    });

    return ExpandableListItemView;
});

define('widgets/ExpandableList/ExpandableListItem/ExpandableListItem',['widgets/WidgetCore','./ExpandableListItemView'],function (WidgetCore, View) {
    'use strict';

    return WidgetCore.extend({

        init: function (options) {
            this.options = options;
            this.view = new View(this.options);
        },

        onViewReady: function () {
            this.view.getLink().addEventHandler('click', this.triggerCallback, this);
        },

        triggerCallback: function (e) {
            e.preventDefault();
            this.options.callback(this.options.model);
        }

    });
});

define('styles!widgets/ExpandableList/_expandableList.less',[],function () { return '.elWidgets-ExpandableList-topList,\n.elWidgets-ExpandableList-moreList {\n  list-style-type: none;\n  padding: 0 20px;\n  margin: 0;\n}\n.elWidgets-ExpandableList-moreList {\n  margin-top: 0;\n  overflow: hidden;\n  max-height: 0;\n}\n.elWidgets-ExpandableList-showMore {\n  display: block;\n  margin: 6px 20px;\n}\n.elWidgets-ExpandableList-showMore_hidden {\n  display: none;\n}\n';});

define('template!widgets/ExpandableList/_expandableList.html',['jscore/handlebars/handlebars'],function (Handlebars) { return Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  helpers = helpers || Handlebars.helpers;
  var buffer = "", stack1, stack2, foundHelper, tmp1, self=this, functionType="function", helperMissing=helpers.helperMissing, undef=void 0, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "<h4 class=\"elWidgets-ExpandableList-header\">";
  foundHelper = helpers.header;
  stack1 = foundHelper || depth0.header;
  if(typeof stack1 === functionType) { stack1 = stack1.call(depth0, { hash: {} }); }
  else if(stack1=== undef) { stack1 = helperMissing.call(depth0, "header", { hash: {} }); }
  buffer += escapeExpression(stack1) + "</h4>";
  return buffer;}

  buffer += "<div class=\"elWidgets-ExpandableList\">\n    ";
  foundHelper = helpers.header;
  stack1 = foundHelper || depth0.header;
  stack2 = helpers['if'];
  tmp1 = self.program(1, program1, data);
  tmp1.hash = {};
  tmp1.fn = tmp1;
  tmp1.inverse = self.noop;
  stack1 = stack2.call(depth0, stack1, tmp1);
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n    <ul class=\"elWidgets-ExpandableList-topList\"></ul>\n    <ul class=\"elWidgets-ExpandableList-moreList\"></ul>\n    <a href=\"#\" class=\"elWidgets-ExpandableList-showMore elWidgets-ExpandableList-showMore_hidden\">More</a>\n</div>";
  return buffer;});});

define('widgets/ExpandableList/ExpandableListView',['jscore/core','template!./_expandableList.html','styles!./_expandableList.less'],function (core, template, styles) {
    'use strict';

    var ExpandableListView = core.View.extend({

        afterRender: function () {
            var element = this.getElement();
            this.header = element.find('.' + ExpandableListView.EL_HEADER_CLASS);
            this.topList = element.find('.' + ExpandableListView.EL_TOP_LIST_CLASS);
            this.moreList = element.find('.' + ExpandableListView.EL_MORE_LIST_CLASS);
            this.showMore = element.find('.' + ExpandableListView.EL_SHOW_MORE_CLASS);
        },

        init: function () {
            this.moreListHeight = 0;
        },

        getTemplate: function () {
            return template(this.options);
        },

        getStyle: function () {
            return styles;
        },

        getHeader: function () {
            return this.header;
        },

        getTopList: function () {
            return this.topList;
        },

        getMoreList: function () {
            return this.moreList;
        },

        getShowMoreLink: function () {
            return this.showMore;
        },

        hideShowMoreLink: function () {
            this.getShowMoreLink().setModifier('hidden');
        },

        showShowMoreLink: function () {
            this.getShowMoreLink().removeModifier('hidden');
        },

        animateCollapseMoreList: function () {
            this.getMoreList().setStyle('transition', 'max-height 0.3s ease-in-out');
            this.getMoreList().setStyle('max-height', '0');
            this.getShowMoreLink().setText('More');
            setTimeout(function () {
                this.getMoreList().setStyle('transition', 'none');
            }.bind(this), 300);
        },

        animateExpandMoreList: function () {
            this.getMoreList().setStyle('transition', 'max-height 0.3s ease-in-out');
            this.getMoreList().setStyle('max-height', this.moreListHeight + 'px');
            this.getShowMoreLink().setText('Less');
            setTimeout(function () {
                this.getMoreList().setStyle('transition', 'none');
            }.bind(this), 300);
        },

        collapseMoreList: function () {
            this.getMoreList().setStyle('max-height', '0');
            this.getShowMoreLink().setText('More');
        },

        expandMoreList: function () {
            this.getMoreList().setStyle('max-height', this.moreListHeight + 'px');
            this.getShowMoreLink().setText('Less');
        },

        recalculateMoreListHeight: function () {
            this.moreListHeight = this.getMoreList().getProperty('scrollHeight');
        }
        
    }, {
        EL_HEADER_CLASS: 'elWidgets-ExpandableList-header',
        EL_TOP_LIST_CLASS: 'elWidgets-ExpandableList-topList',
        EL_MORE_LIST_CLASS: 'elWidgets-ExpandableList-moreList',
        EL_SHOW_MORE_CLASS: 'elWidgets-ExpandableList-showMore'
    });

    return ExpandableListView;
});

define('widgets/ExpandableList/ExpandableList',['widgets/WidgetCore','jscore/ext/mvp','./ExpandableListView','./ExpandableListItem/ExpandableListItem'],function (WidgetCore, mvp, View, ExpandableListItem) {
    'use strict';

    /**
     * The ExpandableList widget displays a number of items in a list from a collection, and provides a button for expanding and collapsing the remaining list entries.<br>
     * The Notification can be instantiated using the constructor Notification.
     *
     * <strong>Events:</strong>
     * <ul>
     *     <li>itemClick - Triggers when a list item has been clicked and passes a model corresonding to the clicked item.</li>
     * </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>items (Required): an mvp.Collection or array containing models for each list entry.</li>
     *       <li>labelKey (Optional): a string denoting the location of the text to be used for the list entry within the model in dot notation. Default is name.
     *           <ul>
     *               <li>
     *                   For example, in the JSON:<br />
     *                   {<br />
     *                   &nbsp;&nbsp;"id": 1,<br />
     *                   &nbsp;&nbsp;"attributes": {<br />
     *                   &nbsp;&nbsp;&nbsp;&nbsp;"name": "Sample"<br />
     *                   &nbsp;&nbsp;}<br />
     *                   }<br />
     *                   a labelKey of 'attributes.name' would display "Sample" as the label.
     *               </li>
     *           </ul>
     *       </li>
     *       <li>header (Optional): a string used to define a header for the list.</li>
     *       <li>showAll (Optional): a boolean used to determine whether all list items should be displayed by default, with no More/Less button. Default is false.</li>
     *       <li>shownItems (Optional): a number to specify how many list items will be shown when collapsed. Ignored if showAll is true. Default is 5.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class ExpandableList
     * @extends WidgetCore
     */
    return WidgetCore.extend({
        /*jshint validthis:true*/

        /**
         * The init method is automatically called by the constructor when using the "new" operator. If an object with
         * key/value pairs was passed into the constructor then the options variable will have those key/value pairs.
         *
         * @method init
         * @param {Object} options
         * @private
         */
        init: function (options) {
            if (!options.items) {
                throw new Error('ExpandableList requires an items attribute');
            }
            this.view = new View(options);
            this.options = options;
            this.itemWidgets = [];
            this.moreListExpanded = false;
            this.shownItems = 5;
            this.showAll = false;
            this.collection = this.options.items instanceof mvp.Collection ? this.options.items : new mvp.Collection(this.options.items);
            this.options.labelKey = this.options.labelKey || 'name';
            this.shownItems = this.options.shownItems || this.shownItems;
            this.showAll = this.options.showAll || this.showAll;
        },

        /**
         * Overrides method from widget.
         * Executes every time, when added back to the screen.
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.collection.addEventHandler('add', renderList, this);
            this.collection.addEventHandler('remove', renderList, this);
            this.collection.addEventHandler('reset', renderList, this);
            renderList.call(this);
            this.view.getShowMoreLink().addEventHandler('click', this.toggleListExpansion, this);
        },

        /**
         * Toggles the expansion of the list.
         *
         * @method toggleListExpansion
         */
        toggleListExpansion: function (e) {
            if (e) {
                e.preventDefault();
            }
            if (this.moreListExpanded) {
                this.view.animateCollapseMoreList();
                this.moreListExpanded = false;
            } else {
                this.view.animateExpandMoreList();
                this.moreListExpanded = true;
            }
        }

    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function renderList() {
        clearList.call(this);
        var i = 0;
        this.collection.each(function (model) {
            var labelKeyParts = this.options.labelKey.split('.');
            var label = model.get(labelKeyParts[0]);
            for (var j = 1; j < labelKeyParts.length; j++) {
                label = label[labelKeyParts[j]];
            }
            var expandableListItem = new ExpandableListItem({
                label: label,
                callback: handleItemClick.bind(this),
                model: model
            });
            this.itemWidgets.push(expandableListItem);
            if (this.showAll || i < this.shownItems) {
                expandableListItem.attachTo(this.view.getTopList());
            } else {
                expandableListItem.attachTo(this.view.getMoreList());
            }
            i++;
        }.bind(this));
        if (this.collection.size() >= this.shownItems && !this.showAll) {
            this.view.showShowMoreLink();
        }
        setTimeout(function () {
            if (this.moreListExpanded) {
                this.view.expandMoreList();
            }
            this.view.recalculateMoreListHeight();
        }.bind(this), 1);
    }

    function clearList() {
        for (var i = 0; i < this.itemWidgets.length; i++) {
            this.itemWidgets[i].destroy();
        }
    }

    function handleItemClick(model) {
        this.trigger('itemClick', model);
    }
});

define('widgets/ExpandableList',['widgets/ExpandableList/ExpandableList'],function (main) {
                        return main;
                    });

