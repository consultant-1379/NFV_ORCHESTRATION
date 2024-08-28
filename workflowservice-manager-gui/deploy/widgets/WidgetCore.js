/* Copyright (c) Ericsson 2014 */

define('widgets/WidgetCore',['jscore/core'],function (jscore) {
    'use strict';

    var supportedVersions = [
        '1'
    ];

    var isCompatible = false;

    supportedVersions.forEach(function(version) {
        if (jscore.version.indexOf(version) === 0) {
            isCompatible = true;
        }
    });

    if (!isCompatible) {
        throw new Error('Incompatible version of JSCore');
    }

    /**
     * The WidgetCore class is the core class for all widgets. To create new widget this class should be extended.
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available. Available modifiers depend on the subclass that extends this class.</li>
     *  </ul>
     *
     * @class WidgetCore
     * @private
     */
    return jscore.Widget.extend({

        /**
         * Calls after the widget is initiated
         *
         * @method _afterInit
         * @param options
         * @private
         */
        _afterInit: function (options) {
            this._createElement();

            if (this.view && this.view.afterRender) {
                this.view.afterRender();
            }
            this.onViewReady.apply(this, arguments);
        },

        /**
         * Returns the parent property
         *
         * @method getParentProperty
         * @param {String} property Property name which should be taken from the parent
         * @returns {Any} Property value from the parent
         */
        getParentProperty: function (property) {
            return this._parent.getProperty(property);
        },

        /**
         * A methods, which allows to define a list of modifiers to the widget.<br>
         * <a href="#modifierAvailableList">see available modifiers</a>
         *
         * @method setModifiers
         * @param {Array} modifiers Contains objects {name: {String}[, value: {String}][, prefix: {String}][, element: {core.Element}]}
         */
        setModifiers: function (modifiers) {
            modifiers.forEach(function (modifier) {
                this.setModifier(modifier.name, modifier.value, modifier.prefix, modifier.element);
            }, this);
        },

        /**
         * Add a single modifier to the widget.<br>
         * <a href="#modifierAvailableList">see available modifiers</a>
         *
         * @method setModifier
         * @param {String} key
         * @param {String} value
         * @param {String} [prefix] optional
         * @param {core.Element} [element] optional
         */
        setModifier: function (key, value, prefix, element) {
            var modElement = element;
            if (!modElement || !modElement instanceof jscore.Element) {
                modElement = this.getElement();
            }
            modElement.setModifier(key, value, prefix);
        },

        /**
         * Remove the modifier 'key' from the widget modifiers.
         *
         * @method removeModifier
         * @param {String} key
         * @param {String} [prefix] optional
         * @param {core.Element} [element] optional
         */
        removeModifier: function (key, prefix, element) {
            var modElement = element;
            if (!modElement || !modElement instanceof jscore.Element) {
                modElement = this.getElement();
            }
            modElement.removeModifier(key, prefix);
        }


        // ----------------------------------------- //
        // Available methods from core.Widget
        /**
         * Bind an event handling function to an event.
         *
         * @method addEventHandler
         * @param {String} eventName
         * @param {Function} fn
         * @return {String} id
         *
         * @example
         *   widget.addEventHandler("widgetEvent", function() {
         *     console.log("Widget Event");
         *   });
         */

        /**
         * Unbind an event handling function from an event.
         *
         * @method removeEventHandler
         * @param {String} eventName
         * @param {String} id
         *
         * @example
         *   widget.removeEventHandler("widgetEvent", id);
         */

        /**
         * Adds the Widget's element to the new parent element.
         *
         * @method attachTo
         * @param {Element} parent
         *
         * @example
         *   widget.attachTo(this.getElement());
         */

        /**
         * Places the detached Widget back into the defined parent element.
         *
         * @method attach
         *
         * @example
         *   widget.attach();
         */

        /**
         * Removes the Widget from the parent element, but does not destroy the Widget. DOM events will still work when Widget is attached back.
         *
         * @method detach
         *
         * @example
         *   widget.detach();
         */

        /**
         * Removes the Widget root Element from the DOM.
         *
         * @method destroy
         *
         * @example
         *   widget.destroy();
         */

    });

});

