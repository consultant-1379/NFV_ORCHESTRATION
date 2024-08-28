/* Copyright (c) Ericsson 2015 */

define("widget/WfUsertasks/TaskTable/CanBeNullCell/CanBeNullCell",["widgets/table/Cell"],function(e){return e.extend({setValue:function(e){null===e?this.getElement().setText(""):this.getElement().setText(e)}})});