define('layouts/wizard/step/Step',['jscore/core'],function (core) {

    /**
     * WizardStep is a base class for creating Wizard steps.
     *
     * <strong>Properties:</strong>
     *   <ul>
     *     <li>title: Title for the step that appears in the step list.</li>
     *     <li>labels: Object with labels for different elements of the wizard step. Can change 'cancel', 'finish', 'next', and 'previous'.</li>
     *   </ul>
     *
     * <strong>Events:</strong>
     *   <ul>
     *     <li>activate: triggered when the step is activated.</li>
     *   </ul>
     *
     * @class WizardStep
     * @extends Widget
     */
    return core.Widget.extend({

        _setWizard: function (wizard) {
            this._wizard = wizard;
        },

        _setIndex: function (index) {
            this._index = index;
        },

        /**
         * Returns whether the step is valid or not. Defaut implementation returns false,
         * you have to override this method. Normally there is no need to call this method,
         * as it is called internally by the wizard.
         *
         * @method isValid
         * @example
         *    isValid: function () {
         *        return this.view.getInputFieldLength() > 0;
         *    }
         */
        isValid: function () {
            return false;
        },

        /**
         * Returns instance of the wizard this step is attached to.
         *
         * @method getWizard
         */
        getWizard: function () {
            return this._wizard;
        },

        /**
         *
         * Forces wizard to revalidate current step. Do not reimplement.
         *
         * @method revalidate
         */
        revalidate: function () {
            this._isValid = this.isValid();
            this.trigger('revalidate', this._isValid);
        }

    });

});

define('layouts/WizardStep',['layouts/wizard/step/Step'],function (main) {
                        return main;
                    });

