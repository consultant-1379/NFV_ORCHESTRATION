/* Copyright (c) Ericsson 2014 */

define('text!widgets/Table/table/EditableCell/_editableCell.html',[],function () { return '<td></td>';});

define('widgets/Table/table/EditableCell/EditableCellView',['jscore/core','text!./_editableCell.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        getBody: function () {
            return this.getElement();
        },

        setValue: function (value) {
            this.getBody().setText(value);
        },

        getValue: function () {
            return this.getBody().getText();
        }

    });

});

define('widgets/Table/table/EditableCell/EditableCell',['widgets/table/Cell','./EditableCellView'],function (Cell, View) {
    'use strict';

    /**
     * Custom Cell class to allow cells to be edited by the user. The model is automatically updated when the user focuses out of the cell.
     *
     * @class table.EditableCell
     * @extends table.Cell
     * @beta
     */
    return Cell.extend({

        View: View,

        /**
         *  Sets the cell to be editable, and when out of focus the model is modified and the "edit" event is thrown.
         *  Dragdrop and paste are also overrided to avoid images and HTML content being added.
         *
         *  @method onCellReady
         *  @private
         */
        onCellReady: function () {
            var options = this.options;
            var attribute = options.attribute;
            var model = options.model;

            this.view.getElement().setAttribute('contenteditable', 'true');

            this.view.getElement().addEventHandler('keydown', function(e) {
                if (e.originalEvent.keyCode === 13 && !e.originalEvent.shiftKey) {
                    this.view.getElement()._getHTMLElement().blur();
                }
            }, this);

            this.view.getElement().addEventHandler('blur', function (e) {
                var newValue = this.getValue();
                model.setAttribute(attribute, newValue);
            }, this);

            this.view.getElement().addEventHandler('dragover drop', function (e) {
                e.preventDefault();
                return false;
            });

            this.view.getElement().addEventHandler('paste', function (e) {
                e.preventDefault();
                var text = e.originalEvent.clipboardData.getData('text/plain');
                document.execCommand('insertHTML', false, text);
            });
        }

    });

});

define('widgets/table/EditableCell',['widgets/Table/table/EditableCell/EditableCell'],function (main) {
                        return main;
                    });

