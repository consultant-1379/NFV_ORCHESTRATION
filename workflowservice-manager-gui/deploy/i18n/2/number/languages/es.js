/* Copyright (c) Ericsson 2014 */
define('text',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('styles',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('template',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});define('json',{load:function(e){throw new Error('Dynamic load not allowed: '+e)}});
/*!
 * numeral.js language configuration
 * language : spanish
 * author : Hernan Garcia : https://github.com/hgarcia
 */
define("i18n/number/languages/es",[],function(){"use strict";return{delimiters:{thousands:".",decimal:","},abbreviations:{thousand:"k",million:"mm",billion:"b",trillion:"t"},ordinal:function(n){var i=n%10;return 1===i||3===i?"er":2===i?"do":7===i||0===i?"mo":8===i?"vo":9===i?"no":"to"},currency:{symbol:"$"}}});