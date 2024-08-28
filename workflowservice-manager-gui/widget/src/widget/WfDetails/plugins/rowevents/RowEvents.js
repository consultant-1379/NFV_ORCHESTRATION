/**
 * Used for adding DOM events to all rows on the table. Every time the DOM event is triggered, 
 * it will trigger an event on the table with the prefix of "rowevents:". For example, if you add
 * an event for "dblclick", it will publish "rowevents:dblclick" when that event happens. "row" is
 * passed as an argument into the event callback.
 *
 * <strong>Constructor Options:</strong>
 * <ul>
 *   <li><i>Function</i> color: Callback function that receives a reference to the row widget it's included with. Must return a color string.</li>
 * </ul>
 * 
 * <pre><code>new RowEvents({
 *    events: ["dblclick", "click"]
 * });</code></pre>
 *
 * <pre><code>table.addEventHandler("rowevents:dblclick", function(row) {
 *    //do something with row   
 * });</code></pre>
 *
 * @class tablelib/plugins/RowEvents
 * @extends Plugin
 */
define([
    "jscore/core",
    "../Plugin"
], function (core, Plugin) {

    return Plugin.extend({
        injections: {
            after: {
                addRow: addRow
            }
        }
    });

    function addRow(obj, index) {
        var table = this.getTable();
        var row = table.getRows()[index !== undefined? index : table.getRows().length - 1];

        this.options.events.forEach(function(evt) {
            row.getElement().addEventHandler(evt, function () {
                table.trigger("rowevents:"+evt, row);
            });
        });        
    }

});