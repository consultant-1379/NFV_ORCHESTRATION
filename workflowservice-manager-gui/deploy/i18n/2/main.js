/* Copyright (c) Ericsson 2014 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
define("i18n/main",["require"],function(e){"use strict";var t=e.config?e.config():e.getConfig();return{version:"1.0.1",createXhr:function(){if("undefined"!=typeof XMLHttpRequest)return new XMLHttpRequest;throw new Error("This browser is not supported")},get preferredLocale(){return t.i18n.locale||this.browserLocale},get browserLocale(){return"undefined"==typeof navigator?"root":((navigator.languages?navigator.languages[0]:navigator.language)||navigator.userLanguage||"root").toLowerCase()},get currentLocale(){if(void 0===this._locale){var e=t.i18n.locales;this._locale=void 0!==e&&e.length>0?this.getBestMatchingLocale(this.preferredLocale,e):this.browserLocale}return this._locale},getBestMatchingLocale:function(e,t){var r=e.split("-"),o=!1,n=!1;return t.forEach(function(t){var i=t.toLowerCase();e.toLowerCase()===i&&(o=i),r[0]===i.split("-")[0]&&n===!1&&(n=i)}),o||n||t[0]},get:function(e,t){var r=this.createXhr();r.open("GET",e,!0),r.addEventListener("load",function(){try{t(JSON.parse(r.responseText))}catch(e){console.error(e),t.error&&t.error(e)}}),r.addEventListener("error",function(e){t.error&&t.error(e)}),r.send(null)},load:function(e,r,o,n){if(n.isBuild)o();else if(-1!==e.indexOf(".json")){var i=t.i18n.localesDir||"locales";this.get(i+"/"+this.currentLocale+"/"+e,o)}else r([e+"/"+this.currentLocale],function(e){o(e)},o.error)}}});