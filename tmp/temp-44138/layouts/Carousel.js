define('styles!layouts/carousel/carousel.less',[],function () { return '.elWidgets-Carousel {\n  position: relative;\n}\n.elWidgets-Carousel-main {\n  margin: 0 28px;\n  box-sizing: border-box;\n  overflow: hidden;\n}\n.elWidgets-Carousel-main_noArrows {\n  margin: 0;\n}\n.elWidgets-Carousel-holder {\n  transition: margin-left 0.3s ease-in-out;\n}\n.elWidgets-Carousel-widget {\n  float: left;\n}\n.elWidgets-Carousel-leftArrow {\n  background-image: url(\'layouts/resources/layouts/carousel/left_arrow_lrg.svg\');\n  position: absolute;\n  top: calc(50% - 16px);\n  left: 0;\n  width: 20px;\n  height: 32px;\n  cursor: pointer;\n  opacity: 0.6;\n}\n.elWidgets-Carousel-leftArrow:hover {\n  opacity: 1;\n}\n.elWidgets-Carousel-rightArrow {\n  background-image: url(\'layouts/resources/layouts/carousel/right_arrow_lrg.svg\');\n  position: absolute;\n  top: calc(50% - 16px);\n  right: 0;\n  width: 20px;\n  height: 32px;\n  cursor: pointer;\n  opacity: 0.6;\n}\n.elWidgets-Carousel-rightArrow:hover {\n  opacity: 1;\n}\n.elWidgets-Carousel-leftArrow,\n.elWidgets-Carousel-rightArrow {\n  -webkit-touch-callout: none;\n  -webkit-user-select: none;\n  -khtml-user-select: none;\n  -moz-user-select: none;\n  -ms-user-select: none;\n  user-select: none;\n}\n';});

define('text!layouts/carousel/carousel.html',[],function () { return '<div class="elWidgets-Carousel">\n\t<div class="elWidgets-Carousel-leftArrow"></div>\n\t<div class="elWidgets-Carousel-main">\n\t\t<div class="elWidgets-Carousel-holder">\n\t\t</div>\n\t</div>\n\t<div class="elWidgets-Carousel-rightArrow"></div>\n</div>\n';});

define('layouts/carousel/CarouselView',['jscore/core','text!./carousel.html','styles!./carousel.less'],function (core, template, style) {

    var CarouselView = core.View.extend({

        getTemplate: function() {
            return template;
        },

        getStyle: function() {
            return style;
        },

        getMain: function() {
            return this.element.find("." + CarouselView.EL + "-main");
        },

        getHolder: function() {
            return this.element.find("." + CarouselView.EL + "-holder");
        },

        getLeftArrow: function() {
            return this.element.find("." + CarouselView.EL + "-leftArrow");
        },

        getRightArrow: function() {
            return this.element.find("." + CarouselView.EL + "-rightArrow");
        },

        hideArrows: function () {
          this.getMain().setModifier('noArrows');
        },

        showArrows: function () {
          this.getMain().removeModifier('noArrows');
        }

    }, {
        "EL":"elWidgets-Carousel"
    });

    return CarouselView;

});

define('layouts/carousel/Carousel',['jscore/core','widgets/utils/domUtils','widgets/WidgetCore','./CarouselView'],function (core, domUtils, widgetCore, View) {
    'use strict';

    /**
     * The Carousel class uses the Ericsson brand assets.<br>
     * The Carousel can be instantiated using the constructor Carousel.
     *
     * <strong>Constructor:</strong>
     *   <ul>
     *     <li>Carousel(Object options)</li>
     *   </ul>
     *
     * <strong>Options:</strong>
     *   <ul>
     *       <li>widgets: an array of widgets to be displayed</li>
     *       <li>widgetsPerRow: the amount of widgets to be displayed in each row. Default is 1.</li>
     *       <li>numberOfRows: the amount of rows displayed. Default is 1.</li>
     *       <li>resolutionLowerBounds (optional): Array containing the lower bounds of the medium and high resolutions. Defaults is [768, 992].</li>
     *       <li>resolutions (optional): an object with the different configurations depending on the screen resolution. Available resolutions are "low", "medium" and  "high".</li>
     *   </ul>
     *
     * @example
     * var CarouselWidget  = new Carousel({
     *      widgets: [
     *          new SomeWidget(),
     *          new AnotherWidget(),
     *          new AnotherWidget(),
     *       ],
     *       widgetsPerRow: 1,
     *       numberOfRows: 2,
     *       resolutions: {
     *               low: {
     *                   widgetsPerRow: 1,
     *                   numberOfRows: 1
     *               },
     *               medium: {
     *                   widgetsPerRow: 2,
     *                   numberOfRows: 1
     *               },
     *               high: {
     *                   widgetsPerRow: 3,
     *                   numberOfRows: 1
     *               }
     *           }
     * });
     *
     * @class Carousel
     * @extends WidgetCore
     */


    return widgetCore.extend({
        View: View,
        /**
         * Initializes members which are used in this widget
         *
         * @method init
         * @private
         */
        init: function (options) {
            this.widgets = options.widgets;
            this.widgetsPerRow = options.widgetsPerRow || 1;
            this.numberOfRows = options.numberOfRows || 1;
            this.screenCount = Math.ceil(this.widgets.length / (this.widgetsPerRow * this.numberOfRows));
            this.currentScreen = 1;


            this.resolutionLowerBounds = options.resolutionLowerBounds || [768, 992];

            this.resolutions = options.resolutions || false;

            this.defaultResolution = {
                widgetsPerRow: options.widgetsPerRow || 1,
                numberOfRows: options.numberOfRows || 1
            };

            this.bounds = ['low', 'medium', 'high'];

            core.Window.addEventHandler('resize', function(e){
                onResize.call(this);
            }.bind(this));
        },
        /**
         * Initializes holders and arrows
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.widgetHolders = this.widgets.map(function (widget) {
                var element = core.Element.parse("<div class='" + View.EL + "-widget'/>");
                element.append(widget.getElement());
                this.view.getHolder().append(element);
                return element;
            }.bind(this));

            this.view.getLeftArrow().addEventHandler("click", function () {
                this.goToScreen(this.currentScreen - 1);
            }.bind(this));
            this.view.getRightArrow().addEventHandler("click", function () {
                this.goToScreen(this.currentScreen + 1);
            }.bind(this));

            this.refresh();
        },
        /**
         * Applies provided options
         *
         * @method setOptions
         * @param {Object} options
         */
        setOptions: function (options) {
            var widgetIndex = this.widgetsPerRow * this.numberOfRows * (this.currentScreen - 1);
            if (options.widgetsPerRow) {
                this.widgetsPerRow = options.widgetsPerRow;
            }
            if (options.numberOfRows) {
                this.numberOfRows = options.numberOfRows;
            }
            if(options.resolutionLowerBounds){
                this.resolutionLowerBounds = options.resolutionLowerBounds;
            }
            if(options.resolutions){
                this.resolutions = options.resolutions;
            }
            this.currentScreen = Math.ceil((widgetIndex + 1) / (this.widgetsPerRow * this.numberOfRows));
            this.screenCount = Math.ceil(this.widgets.length / (this.widgetsPerRow * this.numberOfRows));
            this.refresh();
        },
        /**
         * Returns the number of widgets in carousel
         *
         * @method getNumberOfWidgets
         * @returns int
         */
        getNumberOfWidgets: function(){
            return this.widgets.length;
        },
        /**
         * Returns current options
         *
         * @method getCurrentOptions
         * @returns Object
         */
        getCurrentOptions: function(){
            var options = {
                numberOfRows: this.numberOfRows,
                widgetsPerRow: this.widgetsPerRow
            };
            return options;
        },
        /**
         * Returns current screen number
         *
         * @method getCurrentScreen
         * @returns int
         */
        getCurrentScreen: function(){
            return this.currentScreen;
        },
        /**
         * Returns total screen number
         *
         * @method getTotalScreens
         * @returns int
         */
        getTotalScreens: function(){
            return this.screenCount;
        },
        /**
         * Returns resolution mode. returns false if default resolution is been used.
         *
         * @method getCurrentResolution
         * @returns String || Boolean
         */
        getCurrentResolution: function(){
            return (this.currentResolution)? this.currentResolution: false;
        },
        /**
         * refreshes carousel
         *
         * @method refresh
         */
        refresh: function () {
            this.view.getHolder().setStyle("width", (100 * this.screenCount) + "%");
            this.widgetHolders.forEach(function (element) {
                element.setStyle("width", (100 / this.widgetsPerRow / this.screenCount) + "%");
            }.bind(this));
            this.goToScreen(this.currentScreen);
        },
        /**
         * Navigates to the provided Screen number.
         *
         * @method goToScreen
         * @param {Int} screenNumber
         */
        goToScreen: function (screenNumber) {
            screenNumber = parseInt(screenNumber);
            if(screenNumber <= 0 || screenNumber > this.getTotalScreens() || isNaN(screenNumber)){
                return;
            }
            this.currentScreen = screenNumber;
            this.view.getHolder().setStyle("margin-left", "-" + (100 * (this.currentScreen - 1)) + "%");
            if (this.currentScreen === 1) {
                this.view.getLeftArrow().setStyle("display", "none");
            } else {
                this.view.getLeftArrow().setStyle("display", "block");
            }
            if (this.currentScreen === this.screenCount) {
                this.view.getRightArrow().setStyle("display", "none");
            } else {
                this.view.getRightArrow().setStyle("display", "block");
            }
            if (this.currentScreen === 1 && this.currentScreen === this.screenCount) {
                this.view.hideArrows();
            } else {
                this.view.showArrows();
            }
        }

    });


    function onResize(){
        /*jshint validthis:true */
        var currentResolution = getResolution.call(this);
        if(currentResolution !== this.currentResolution){
            var resolutionToApply = (this.resolutions[currentResolution])? this.resolutions[currentResolution] : this.defaultResolution;
            this.setOptions(resolutionToApply);
            this.currentResolution = currentResolution;
        }
    }

    function getResolution(){
        /*jshint validthis:true */
        var currentWidth = domUtils.getWindowDimensions().width;
        var currentResolution = false;
        this.resolutionLowerBounds.forEach(function (resolution, index) {
            if (!currentResolution && currentWidth < resolution) {
                currentResolution = this.bounds[index];
            }
        }.bind(this));

        if(currentResolution === false){
            currentResolution = this.bounds[this.bounds.length - 1];
        }
        return currentResolution;
    }
});

define('layouts/Carousel',['layouts/carousel/Carousel'],function (main) {
                        return main;
                    });

