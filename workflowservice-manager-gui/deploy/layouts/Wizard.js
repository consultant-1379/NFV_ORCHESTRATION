define('text!layouts/wizard/stepHeader/stepHeader.html',[],function () { return '<li class="ebWizard-stepListItem ebWizard-step">\n  <span class="ebWizard-stepNumber"></span><span class="ebWizard-stepTitle"></span>\n</li>\n';});

define('layouts/wizard/stepHeader/StepHeaderView',['jscore/core','text!./stepHeader.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        setTitle: function (title) {
            this.getElement().find('.ebWizard-stepTitle').setText(title);
        },

        setEnabled: function (enabled) {
            if (enabled) {
                this.getElement().removeModifier('disabled', 'ebWizard-step');
            } else {
                this.getElement().setModifier('disabled', '', 'ebWizard-step');
            }
        },

        setCurrent: function (current) {
            if (current) {
                this.getElement().setModifier('current', '', 'ebWizard-step');
            } else {
                this.getElement().removeModifier('current', 'ebWizard-step');
            }
        },

        setValid: function (valid) {
            if (valid) {
                this.getElement().setModifier('valid', '', 'ebWizard-step');
            } else {
                this.getElement().removeModifier('valid', 'ebWizard-step');
            }
        },

        onClick: function (cb) {
            this.getElement().addEventHandler('click', cb);
        }

    });

});

define('layouts/wizard/stepHeader/StepHeader',['jscore/core','./StepHeaderView'],function (core, View) {

    return core.Widget.extend({

        View: View,

        init: function (options) {
            this.options = options || {};
        },

        onViewReady: function () {
            this.view.setTitle(this.options.title || '');
        },

        onClick: function (cb) {
            this.view.onClick(cb);
        },

        setIndex: function (index) {
            this._index = index;
        },

        setEnabled: function (enabled) {
            this._enabled = enabled;
            this.view.setEnabled(enabled);
        },

        isEnabled: function () {
            return this._enabled;
        },

        setValid: function (valid) {
            this.view.setValid(valid);
        },

        setCurrent: function (current) {
            this._current = current;
            this.view.setCurrent(current);
        },

        isCurrent: function () {
            return this._current;
        }

    });
});

define('text!layouts/wizard/wizard.html',[],function () { return '<div class="ebWizard">\n  <div class="ebWizard-headerStepSelect"><i class="ebWizard-headerStepSelectPrevious"></i><div class="ebWizard-step ebWizard-step_current"><span class="ebWizard-stepNumber"></span><span class="ebWizard-stepTitle"></span></div><i class="ebWizard-headerStepSelectDropBtn"></i><i class="ebWizard-headerStepSelectNext"></i></div>\n  <ul class="ebWizard-stepList eb_scrollbar"></ul>\n\n  <div class="ebWizard-bodyContainer eb_scrollbar"></div>\n\n  <footer class="ebWizard-footer">\n    <ul class="ebWizard-footerCommandList">\n      <li class="ebWizard-footerCommandListItem"><a href="#" class="ebWizard-footerBtnCancel">Cancel</a></li><li class="ebWizard-footerCommandListItem"><button type="button" class="ebWizard-footerBtnPrevious ebBtn eb_wMargin_side"><i class="ebIcon ebIcon_leftArrowLarge"></i> <span>Previous</span></button><button type="button" class="ebWizard-footerBtnNext ebBtn ebBtn_color_paleBlue eb_wMargin_side"><span>Next</span> <i class="ebIcon ebIcon_rightArrowLarge_white"></i></button><button type="button" class="ebWizard-footerBtnFinish ebBtn eb_wMargin_side ebBtn_color_darkBlue"> <span>Finish</span></button>\n      </li>\n    </ul>\n  </footer>\n</div>\n';});

define('layouts/wizard/WizardView',['jscore/core','text!./wizard.html'],function (core, template) {
    'use strict';

    return core.View.extend({

        getTemplate: function () {
            return template;
        },

        setFirstStep: function () {
            this.getElement().setModifier('firstStep');
            this.getElement().removeModifier('lastStep');
        },

        setIntermediateStep: function () {
            this.getElement().removeModifier('firstStep');
            this.getElement().removeModifier('lastStep');
        },

        setLastStep: function () {
            this.getElement().removeModifier('firstStep');
            this.getElement().setModifier('lastStep');
        },

        getBody: function () {
            return ;
        },

        setStepContents: function (step) {
            var body = this.getElement().find('.ebWizard-bodyContainer');
            body.children().forEach(function (child) {
                child.detach();
            });
            step.attachTo(body);
        },

        getNextHeaderButton: function () {
            return this.getElement().find('.ebWizard-headerStepSelectNext');
        },

        getNextButton: function () {
            return this.getElement().find('.ebWizard-footerBtnNext');
        },

        getPreviousHeaderButton: function () {
            return this.getElement().find('.ebWizard-headerStepSelectPrevious');
        },

        getPreviousButton: function () {
            return this.getElement().find('.ebWizard-footerBtnPrevious');
        },

        getCancelButton: function () {
            return this.getElement().find('.ebWizard-footerBtnCancel');
        },

        setCancelLabel: function (label) {
            this.getCancelButton().setText(label);
        },

        setPreviousLabel: function (label) {
            this.getElement().find('.ebWizard-footerBtnPrevious span').setText(label);
        },

        setNextLabel: function (label) {
            this.getElement().find('.ebWizard-footerBtnNext span').setText(label);
        },

        getFinishButton: function () {
            return this.getElement().find('.ebWizard-footerBtnFinish');
        },

        setFinishLabel: function (label) {
            this.getFinishButton().find('span').setText(label);
        },

        getStepList: function () {
            return this.getElement().find('.ebWizard-stepList');
        },

        openStepList: function () {
            this.getStepList().setModifier('open');
        },

        closeStepList: function () {
            this.getStepList().removeModifier('open');
        },

        getStepListToggle: function () {
            return this.getElement().find('.ebWizard-headerStepSelectDropBtn');
        },

        addStepHeader: function (stepHeader) {
            stepHeader.attachTo(this.getElement().find('.ebWizard-stepList'));
        },

        setVertical: function (isVertical) {
            this.getElement()[!isVertical ? 'setModifier' : 'removeModifier']('horizontal');
            this.getElement()[isVertical ? 'setModifier' : 'removeModifier']('vertical');
        },

        setNextEnabled: function (enabled) {
            var btn = this.getElement().find('.ebWizard-footerBtnNext');
            btn[enabled ? 'removeAttribute' : 'setAttribute']('disabled', '');
            var icon = btn.find('i');
            if (enabled) {
                icon.setModifier('rightArrowLarge', 'white', 'ebIcon');
            } else {
                icon.setModifier('rightArrowLarge', '', 'ebIcon');
            }

            btn = this.getNextHeaderButton();
            btn[enabled ? 'removeModifier' : 'setModifier']('disabled', '');
        },

        setFinishEnabled: function (enabled) {
            var btn = this.getFinishButton();
            btn[enabled ? 'removeAttribute' : 'setAttribute']('disabled', '');
        },

        setCurrentStepTitle: function (text) {
            this.getElement().find('.ebWizard-stepTitle').setText(text);
        },

        setCurrentStepNumber: function (text) {
            this.getElement().find('.ebWizard-stepNumber').setText(text);
        }

    });

});

define('layouts/wizard/Wizard',['jscore/core','./WizardView','./stepHeader/StepHeader'],function (core, View, StepHeader) {

    /**
     * Wizard provides a way to split a task into a number of sequential steps.
     *
     * <strong>Constructor:</strong>
     * Wizard(Object options)
     *
     * **Options:**
     *  * steps: array of WizardStep instances.
     *  * labels: object containing button labels that applies to all steps. Can change 'cancel', 'finish', 'next', and 'previous'.
     *
     * **Events:**
     *  * finish: triggered when finish button is clicked.
     *  * cancel: triggered when cancel button is clicked.
     *  * stepchange: triggered when moving to a different step.
     *
     * @class Wizard
     * @extends Widget
     */
    return core.Widget.extend({

        View: View,

        init: function (options) {
            this.options = options = options || {};
            this.defaultLabels = {
                next: 'Next',
                previous: 'Previous',
                cancel: 'Cancel',
                finish: 'Finish'
            };

            this._steps = [];
            this._activeStep = 0;

        },

        onViewReady: function () {
            if (this.options.labels) {
                this.setLabels(this.options.labels);
            }

            if (this.options.steps) {
                this.options.steps.forEach(function (step) {
                    this.addStep(step);
                }.bind(this))
            }

            this.updateLabels(this.defaultLabels);

            this.view.getNextButton().addEventHandler('click', this.nextStep.bind(this));
            this.view.getNextHeaderButton().addEventHandler('click', this.nextStep.bind(this));
            this.view.getPreviousButton().addEventHandler('click', this.previousStep.bind(this));
            this.view.getPreviousHeaderButton().addEventHandler('click', this.previousStep.bind(this));

            this.view.getStepListToggle().addEventHandler('click', function () {
                if (this._stepListOpen) {
                    this.view.closeStepList();
                    this._stepListOpen = false;
                } else {
                    this.view.openStepList();
                    this._stepListOpen = true;
                }
            }.bind(this));

            this.view.getFinishButton().addEventHandler('click', function () {
                this.trigger('finish');
            }.bind(this));

            this.view.getCancelButton().addEventHandler('click', function (e) {
                this.trigger('cancel');
                e.preventDefault();
            }.bind(this));

            this.getElement().setStyle('margin-top', '10px');

            this.windowEvent = core.Window.addEventHandler('resize', this.resize.bind(this));
        },

        onDOMAttach: function() {
            this.resize();
        },

        onDestroy: function() {
            core.Window.removeEventHandler(this.windowEvent);
        },

        /**
         * Sets labels for Next, Previous, Finish and Cancel buttons.
         * All labels are optional, if not specified the default ones will be used instead.
         * This method should be mainly used for localizing the labels.
         * To define custom labels for individual steps, please check <a href="WizardStep.html#properties_labels">WizardStep.labels.</a>
         *
         * @method setLabels
         * @param {object} labels
         *
         *  * next: label of the Next button
         *  * previous: label of the Previous button
         *  * finish: label of the Finish button
         *  * cancel: label of the Cancel button
         *
         * @example
         *    wizard.setLabels({
         *        next: 'Weiter'
         *        previous: 'Zurück'
         *    });
         */
        setLabels: function (labels) {
            this.defaultLabels.next = labels.next || this.defaultLabels.next;
            this.defaultLabels.previous = labels.previous || this.defaultLabels.previous;
            this.defaultLabels.cancel = labels.cancel || this.defaultLabels.cancel;
            this.defaultLabels.finish = labels.finish || this.defaultLabels.finish;
            this.updateLabels(this.defaultLabels);
        },

        setStepLabels: function (labels) {
            labels.next = labels.next || this.defaultLabels.next;
            labels.previous = labels.previous || this.defaultLabels.previous;
            labels.cancel = labels.cancel || this.defaultLabels.cancel;
            labels.finish = labels.finish || this.defaultLabels.finish;
            this.updateLabels(labels);
        },

        updateLabels: function (labels) {
            this.view.setNextLabel(labels.next);
            this.view.setPreviousLabel(labels.previous);
            this.view.setCancelLabel(labels.cancel);
            this.view.setFinishLabel(labels.finish);
        },

        addStep: function (step) {
            step._setWizard(this);
            var stepHeader = new StepHeader({
                title: step.title
            });
            this._steps.push({
                header: stepHeader,
                body: step
            });
            step.addEventHandler('revalidate', function (isValid) {
                if (stepHeader.isCurrent()) {
                    var nextStep = this._steps[this._activeStep + 1];
                    if (nextStep) {
                        nextStep.header.setEnabled(isValid);
                    }
                    this.view.setNextEnabled(isValid);
                    this.view.setFinishEnabled(isValid);
                }
            }.bind(this));
            stepHeader.onClick(function () {
                this._steps.forEach(function (step, i) {
                    if (stepHeader === step.header && step.header.isEnabled()) {
                        this.setStep(i);
                    }
                }.bind(this));
            }.bind(this));
            this.view.addStepHeader(stepHeader);
            this.view.setVertical(this._steps.length > 5);
            if (this._steps.length === 1) {
                this.setStep(0);
            } else {
                stepHeader.setEnabled(false);
            }
            if (this._steps.length >= 2) {
                this._steps[this._steps.length - 2].body.revalidate();
            }
            step.revalidate();
        },

        setStep: function (stepIndex) {
            this._stepListOpen = false;
            this.view.closeStepList();
            this._activeStep = stepIndex;
            this.view.setStepContents(this._steps[stepIndex].body);
            this._steps.forEach(function (step, index) {
                step.header.setEnabled(index <= stepIndex);
                step.header.setCurrent(index === stepIndex);
                step.header.setValid(index < stepIndex);
            });
            var step = this._steps[stepIndex];
            step.body.revalidate();

            if (stepIndex === 0) {
                this.view.setFirstStep();
            } else if (stepIndex + 1 === this._steps.length) {
                this.view.setLastStep();
            } else {
                this.view.setIntermediateStep();
            }
            this.view.setCurrentStepTitle(step.body.title);
            this.view.setCurrentStepNumber(stepIndex + 1);
            this.setStepLabels(step.body.labels || {});
            step.body.trigger('activate');
            this.trigger('stepchange');
        },

        nextStep: function () {
            var step = this._steps[this._activeStep].body;
            if (step.isValid()) {
                this.setStep(this._activeStep + 1);
            }
        },

        previousStep: function () {
            this.setStep(this._activeStep - 1);
        },

        resize: function() {
            var top = this.getElement().getPosition().top;
            var height = core.Window.getProperty('innerHeight') - top;
            this.getElement().setStyle('height', height + 'px');
        }

    });

});

define('layouts/Wizard',['layouts/wizard/Wizard'],function (main) {
                        return main;
                    });

