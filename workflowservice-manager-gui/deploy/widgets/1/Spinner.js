/* Copyright (c) Ericsson 2014 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
define("text!widgets/Spinner/_spinner.html",[],function(){return'<table data-namespace="ebSpinner">\n    <tr>\n        <td data-name="label"></td>\n        <td class="ebSpinner-holder">\n            <div data-name="up" class="ebSpinner-iconUpHolder">\n                <i class="ebIcon ebIcon_upArrow ebIcon_interactive"></i>\n            </div>\n            <div class="ebSpinner-inputHolder">\n                <input type="text" data-name="input" class="ebInput ebInput_miniW ebInput_txtCenter eb_noMargin" value="20"/>\n            </div>\n            <div data-name="down" class="ebSpinner-iconDownHolder">\n                <i class="ebIcon ebIcon_downArrow ebIcon_interactive"></i>\n            </div>\n        </td>\n        <td data-name="value"></td>\n    </tr>\n</table>'}),define("widgets/Spinner/SpinnerView",["jscore/core","text!./_spinner.html","widgets/utils/dataNameUtils"],function(t,e,i){"use strict";var n=t.View.extend({getTemplate:function(){return i.translate(null,e,this)},getRoot:function(){return this.getElement()},getPrefix:function(){return this[n.EL_PREFIX]},getPostfix:function(){return this[n.EL_POSTFIX]},getUpButton:function(){return this[n.EL_UP_BUTTON]},getDownButton:function(){return this[n.EL_DOWN_BUTTON]},getInput:function(){return this[n.EL_INPUT]}},{EL_PREFIX:"label",EL_POSTFIX:"value",EL_UP_BUTTON:"up",EL_DOWN_BUTTON:"down",EL_INPUT:"input"});return n}),define("widgets/Spinner/Spinner",["widgets/WidgetCore","./SpinnerView","widgets/utils/parserUtils"],function(t,e,i){"use strict";function n(t){var e=void 0!==this.value&&this.value!==t;this.value=t,e&&this.trigger("change",t)}function s(t,e){var i=e.toString(),n=function(t,e){return 0===t?e:n(t-1,"0"+e)};return i.length<t?n(t-i.length,i):i}function o(t){var e=this.options.min,i=this.options.max;return void 0===i?t>=e:i>=t&&t>=e}function u(t){return t=t>this.options.max?this.options.max:t,t<this.options.min?this.options.min:t}function a(){this.view.getInput().element.select()}function l(t){var e=t.originalEvent.which,i=t.originalEvent.keyCode,n=-1!==E.indexOf(i),s=String.fromCharCode(e);(!n&&45>e||e>=112&&123>=e||t.originalEvent.ctrlKey)&&t.preventDefault();var o=/[0-9\-]/;n||o.test(s)||t.preventDefault()}function r(){var t=this.view.getInput().getValue(),e=0;""!==t&&(e=i.parseInt(t),o.call(this,e)&&e.toString()===t?(n.call(this,e),I.call(this)):(0===t.indexOf("0")&&n.call(this,e),b.call(this)))}function h(){var t=this.view.getInput().getValue();""===t&&b.call(this)}function d(){clearTimeout(this.autoIncrementTimeout),p.call(this)&&(this.autoIncrementDelay>=this.minDelay&&(this.autoIncrementDelay-=this.delayStep),this.autoIncrementTimeout=setTimeout(d.bind(this),this.autoIncrementDelay))}function v(){this.autoIncrementDelay=this.maxDelay,this.autoIncrementTimeout=setTimeout(d.bind(this),this.autoIncrementDelay)}function c(){this.autoIncrementTimeout&&(clearTimeout(this.autoIncrementTimeout),p.call(this),delete this.autoIncrementTimeout)}function p(){return o.call(this,this.value+1)?(n.call(this,this.value+1),b.call(this),!0):!1}function m(){clearTimeout(this.autoDecrementTimeout),g.call(this)&&(this.autoDecrementDelay>=this.minDelay&&(this.autoDecrementDelay-=this.delayStep),this.autoDecrementTimeout=setTimeout(m.bind(this),this.autoDecrementDelay))}function w(){this.autoDecrementDelay=this.maxDelay,this.autoDecrementTimeout=setTimeout(m.bind(this),this.autoDecrementDelay)}function f(){this.autoDecrementTimeout&&(clearTimeout(this.autoDecrementTimeout),g.call(this),delete this.autoDecrementTimeout)}function g(){return o.call(this,this.value-1)?(n.call(this,this.value-1),b.call(this),!0):!1}function b(){this.view.getInput().setValue(void 0!==this.options.minNumberDigitsFormat?s(this.options.minNumberDigitsFormat,this.value):this.value),I.call(this)}function I(){o.call(this,this.value+1)?this.view.getUpButton().find(".ebIcon").removeModifier("disabled"):this.view.getUpButton().find(".ebIcon").setModifier("disabled"),o.call(this,this.value-1)?this.view.getDownButton().find(".ebIcon").removeModifier("disabled"):this.view.getDownButton().find(".ebIcon").setModifier("disabled")}var E=[8,9,35,36,37,39,46,116];return t.extend({View:e,init:function(t){this.options=t||{},this.minDelay=100,this.maxDelay=400,this.delayStep=50},onViewReady:function(){void 0===this.options.min&&(this.options.min=0),void 0===this.options.value&&(this.options.value=this.options.min),this.enable()},setPrefix:function(t){if(t&&""!==t.trim()){var e=this.view.getPrefix();e.setStyle("display","table-cell"),e.setText(t)}},setPostfix:function(t){if(t&&""!==t.trim()){var e=this.view.getPostfix();e.setStyle("display","table-cell"),e.setText(t)}},setValue:function(t){if(void 0!==t&&null!==t)if(t instanceof String||"string"==typeof t){t instanceof String&&(t=t.toString());var e=""===t?0:i.parseInt(t);isNaN(e)||(n.call(this,u.call(this,e)),b.call(this))}else/[0-9]|-/.test(t)&&(n.call(this,u.call(this,t)),b.call(this))},getValue:function(){return this.value},setInputSize:function(t){["miniW","smallW","longW","xLongW"].indexOf(t)>-1&&(t=t.replace("W","")),this.view.getInput().setModifier("width",t)},enable:function(){this.upButtonMouseDownEvent=this.view.getUpButton().addEventHandler("mousedown",v,this),this.upButtonMouseUpEvent=this.view.getUpButton().addEventHandler("mouseup",c,this),this.upButtonMouseOutEvent=this.view.getUpButton().addEventHandler("mouseout",c,this),this.downButtonMouseDownEvent=this.view.getDownButton().addEventHandler("mousedown",w,this),this.downButtonMouseUpEvent=this.view.getDownButton().addEventHandler("mouseup",f,this),this.downButtonMouseOutEvent=this.view.getDownButton().addEventHandler("mouseout",f,this),this.inputClickEvent=this.view.getInput().addEventHandler("click",a,this),this.inputKeyPressEvent=this.view.getInput().addEventHandler("keypress",l,this),this.inputKeyUpEvent=this.view.getInput().addEventHandler("keyup",r,this),this.inputBlueEvent=this.view.getInput().addEventHandler("blur",h,this),this.setValue(void 0!==this.value?this.value:this.options.value),this.setPrefix(this.options.prefix||""),this.setPostfix(this.options.postfix||""),this.setInputSize(this.options.inputSize||"miniW"),this._enabled===!1&&(this.view.getUpButton().removeAttribute("title","Disabled"),this.view.getDownButton().removeAttribute("title","Disabled"),this.view.getInput().removeAttribute("title","Disabled"),this.view.getInput().removeAttribute("readonly"),this.view.getInput().removeModifier("disabled"),this.view.getUpButton().find(".ebIcon").removeModifier("disabled"),this.view.getDownButton().find(".ebIcon").removeModifier("disabled")),this._enabled=!0},disable:function(){this._enabled=!1,this.view.getUpButton().removeEventHandler(this.upButtonMouseDownEvent),this.view.getUpButton().removeEventHandler(this.upButtonMouseUpEvent),this.view.getUpButton().removeEventHandler(this.upButtonMouseOutEvent),this.view.getUpButton().removeEventHandler(this.downButtonMouseDownEvent),this.view.getUpButton().removeEventHandler(this.downButtonMouseUpEvent),this.view.getUpButton().removeEventHandler(this.downButtonMouseOutEvent),this.view.getInput().removeEventHandler(this.inputClickEvent),this.view.getInput().removeEventHandler(this.inputKeyPressEvent),this.view.getInput().removeEventHandler(this.inputKeyUpEvent),this.view.getInput().removeEventHandler(this.inputBlueEvent),this.view.getUpButton().setAttribute("title","Disabled"),this.view.getDownButton().setAttribute("title","Disabled"),this.view.getInput().setAttribute("title","Disabled"),this.view.getInput().setAttribute("readonly","readonly"),this.view.getInput().setModifier("disabled"),this.view.getDownButton().find(".ebIcon").setModifier("disabled"),this.view.getUpButton().find(".ebIcon").setModifier("disabled")}})}),define("widgets/Spinner",["widgets/Spinner/Spinner"],function(t){return t});