/* Copyright (c) Ericsson 2014 */

define('i18n/main',['require'],function (require) {
    'use strict';

    var globalConfig = require.config ? require.config() : require.getConfig();

    /**
     * The Internationalization extension to provide loading locale files.
     * @return {Object}
     * @method constructor
     *
     * @example
     * define([
     *      "jscore/core",
     *      "template!./myApp.html",
     *      "i18n!myApp/myLangFile.json",
     *      ],
     *      function(core, template, dictionary) {
     *          return core.View.extend({
     *           getTemplate: function() {
     *              return template(dictionary);
     *          }
     * });
     *
     */
    return {
        version: '1.0.1',

        createXhr: function () {
            if (typeof XMLHttpRequest !== 'undefined') {
                return new XMLHttpRequest();
            } else {
                throw new Error('This browser is not supported');
            }
        },

        /**
         * Preferred locale, defined by configuration settings and browser settings.
         *
         * @attribute preferredLocale
         * @type String
         * @static
         * @readOnly
         *
         */
        get preferredLocale() {
            return globalConfig.i18n.locale || this.browserLocale;
        },

        /**
         * Locale defined in user's browser settings.
         *
         * @attribute browserLocale
         * @type String
         * @static
         * @return {String} locale
         *
         */
        get browserLocale() {
            return typeof navigator === 'undefined' ? 'root' :
                            ((navigator.languages? navigator.languages[0] : navigator.language) ||
                                navigator.userLanguage || 'root').toLowerCase();
        },

        /**
         * Locale currently in use. Might be different from user locale or locale specified in configuration options due to being unavailable.
         *
         * @attribute currentLocale
         * @type String
         * @static
         * @return {String} locale
         *
         */
        get currentLocale() {
            if (this._locale === undefined) {
                var locales = globalConfig.i18n.locales;
                if (locales !== undefined && locales.length > 0) {
                    this._locale = this.getBestMatchingLocale(this.preferredLocale, locales);
                } else {
                    this._locale = this.browserLocale;
//                    throw new Error('Available locales are not defined');
                }
            }
            return this._locale;
        },

        /**
         * Given preferred locale and a list of available locales, returns the best one available.
         *
         * @method getBestMatchingLocale
         * @static
         * @param {String} preferredLocale
         * @param {Array} availableLocales
         * @return {String} locale
         *
         */
        getBestMatchingLocale: function (preferredLocale, availableLocales) {
            var parts = preferredLocale.split('-'),
                exact = false,
                matched = false;

            availableLocales.forEach(function (language) {
                var lang = language.toLowerCase();
                if (preferredLocale.toLowerCase() === lang) {
                    exact = lang;
                }
                if (parts[0] === lang.split('-')[0] && matched === false) {
                    matched = lang;
                }
            });
            return exact || matched || availableLocales[0];
        },

        get: function (url, callback) {
            var xhr = this.createXhr();
            xhr.open('GET', url, true);

			xhr.addEventListener('load', function() {
                try{
                    callback(JSON.parse(xhr.responseText));
                } catch (e) {
                    console.error(e);
                    if (callback.error) {
                        callback.error(e);
                    }
                }
            });
            xhr.addEventListener('error', function(e) {
                if (callback.error) {
                    callback.error(e);
                }
            });

            xhr.send(null);
        },

        load: function (name, req, onLoad, config) {
            if (config.isBuild) {
                onLoad();
            } else {
                if (name.indexOf('.json') !== -1) {
                    var localesDir = globalConfig.i18n.localesDir || 'locales';
                    this.get(localesDir + '/' + this.currentLocale + '/' + name, onLoad);
                } else {
                    req([name + '/' + this.currentLocale], function (lang) {
                        onLoad(lang);
                    }, onLoad.error);
                }
            }
        }
    };
});

