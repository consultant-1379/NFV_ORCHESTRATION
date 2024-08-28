/* Copyright (c) Ericsson 2014 */

define('jscore/ext/net',['jscore/core','jscore/base/jquery','jscore/ext/utils/base/underscore'],function (core, jquery, _) {
    "use strict";
    /**
     * The ext.net package provides networking functionality for JSCore apps.
     *
     * @class ext.net
     */
    var net = {};

    /**
     * Creates an XMLHttpRequest. Default values are used where none is provided.
     *
     * The following options are accepted:
     *   <ul>
     *       <li>url (String): url for the request to be sent to.</li>
     *       <li>type (String): Type of request. GET [default], POST, DELETE, PUT.</li>
     *       <li>data (Object): Key value pairs that is encoded and sent to the url.</li>
     *       <li>dataType (String): Type of data being passed into success callback. text [default], json, xml.</li>
     *       <li>headers (Object): Additional key/value pair of headers to send with the request.</li>
     *       <li>contentType (String/Boolean): Type of data being sent to url. Default is "application/x-www-form-urlencoded; charset=UTF-8". Set to false to not append a content type header.</li>
     *       <li>statusCode (Object): Key is HTTP code, value is function. Functions execute depending on response.</li>
     *       <li>processData (Boolean): Convert data object to query string before sending to url. Default is true.</li>
     *       <li>cache (Boolean): If set to false, requested urls will not be cached for GET requests (appends a timestamp to the url to prevent caching). Default is true.</li>
     *       <li>success (Function): Callback with data response passed in.</li>
     *       <li>error (Function): Callback if error occurs with error message and XHR passed in.</li>
     *   </ul>
     *
     * @method ajax
     * @param options
     * @return {XHR} xhr
     * @example
     *     net.ajax({
     *         url: "/todos",
     *         type: "GET",
     *         dataType: "json",
     *         success: function(data) {
     *             console.log(data);
     *         },
     *         error: function(msg, xhr) {
     *             console.error("Error: " + msg);
     *         }
     *     })
     *
     *     net.ajax({
     *         url: "/todos",
     *         type: "POST",
     *         contentType: "application/json",
     *         data: JSON.stringify({message: "Hello World"})
     *     });
     *
           var data = new FormData();
     *     data.append(input.getProperty("files")[0]);
     *     net.ajax({
     *         url: "/upload.php",
     *         type: "POST",
     *         contentType: false,
     *         processData: false,
     *         data: data,
     *         success: function(data) {
     *             console.log(data);
     *         }
     *     });
     */
	net.ajax = function(options) {
        options = options || {};
		var wrappedSuccess = function(data) {
			if (options.success) {
				options.success.call(this, data);
			}
		};

		var wrappedError = function(jqXHR, textStatus, errorThrown) {
			if (options.error) {
				options.error.call(this, errorThrown, new core.XHR(jqXHR));
            }
		};

        var ajaxOptions = {
            success: wrappedSuccess,
            error: wrappedError,
            type: "GET",
            dataType: "text",
            url: window.location.host + window.location.pathname
        };

        _.extend(ajaxOptions,
            _.pick(options, "statusCode", "processData", "cache", "data", "contentType", "dataType", "type", "url", "headers"));

        var jqxhr = jquery.ajax(ajaxOptions);
        return new core.XHR(jqxhr);
    };

	return net;

});

