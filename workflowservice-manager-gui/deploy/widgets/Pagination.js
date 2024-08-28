/* Copyright (c) Ericsson 2014 */

define('text!widgets/Pagination/_pagination.html',[],function () { return '<ul class="ebPagination">\n\t<li class="ebPagination-previous">\n\t\t<a class="ebPagination-previousAnchor" href="#"></a>\n\t</li>\n\t<li class="ebPagination-pagesContainer">\n\t\t<ul class="ebPagination ebPagination-pages">\n\t\t</ul>\n\t</li>\n\t<li class="ebPagination-next">\n\t\t<a class="ebPagination-nextAnchor" href="#"></a>\n\t</li>\n</ul>\n';});

define('widgets/Pagination/PaginationView',['jscore/core','text!./_pagination.html'],function (core, template) {

	var PaginationView =  core.View.extend({

        afterRender: function () {
            var element = this.getElement();
            this.previous = element.find('.' + PaginationView.EL_PREVIOUS_CLASS);
            this.previousAnchor = element.find('.' + PaginationView.EL_PREVIOUS_ANCHOR_CLASS);
            this.nextAnchor = element.find('.' + PaginationView.EL_NEXT_ANCHOR_CLASS);
            this.pages = element.find('.' + PaginationView.EL_PAGES_CLASS);
        },

		getTemplate: function() {
			return template;
		},

		getPreviousLi: function() {
			return this.previous;
		},

		getPrevious: function() {
			return this.previousAnchor;
		},

		getWrapper: function() {
			return this.getElement();
		},

		getPages: function() {
			return this.pages;
		},

		getNext: function() {
			return this.nextAnchor;
		}

	}, {
        EL_PAGES_CLASS: 'ebPagination-pages',
        EL_PREVIOUS_CLASS: 'ebPagination-previous',
        EL_PREVIOUS_ANCHOR_CLASS: 'ebPagination-previousAnchor',
        EL_NEXT_ANCHOR_CLASS: 'ebPagination-nextAnchor'
    });

    return PaginationView;

});

define('widgets/Pagination/Pagination',['jscore/core','widgets/WidgetCore','./PaginationView','widgets/utils/domUtils'],function (core, WidgetCore, View, domUtils) {
    'use strict';

    /**
     * This widget uses the pagination brand asset to create a widget which will produce clickable page numbers which developers can react to.<br>
     * The InfoPopup can be instantiated using the constructor InfoPopup.
     *
     * <strong>Events:</strong>
     *   <ul>
     *       <li>pagechange: Triggers when the highlighed page has changed. Passes the page number as a parameter.</li>
     *   </ul>
     *
     * The following options are accepted:
     *   <ul>
     *       <li>pages: number of page links to create</li>
     *       <li>selectedPage: the number of the page to be highlighted as selected/li>
     *       <li>onPageChange: Function callback, triggers when a page has been clicked.</li>
     *       <li>url: If string, the href for each page link will be "#&lt;url&gt;/&lt;pageNumber&gt;". If function, a string is expected to be returned (the page number is passed into the function). If no url is specified, then the location will not change.</li>
     *       <li>maxWidth: The maximum width the pagination widget will take. Default is 500px.</li>
     *   </ul>
     *
     * <a name="modifierAvailableList"></a>
     * <strong>Modifiers:</strong>
     *  <ul>
     *      <li>No modifier available.</li>
     *  </ul>
     *
     * @class Pagination
     * @extends WidgetCore
     */
    return WidgetCore.extend({

        View: View,

        /**
         * Initialises events and creates the links
         *
         * @method onViewReady
         * @private
         */
        onViewReady: function () {
            this.totalPages = this.options.pages || 0;
            this.selectedPage = this.options.selectedPage || 1;
            this.url = this.options.url;

            this.view.getPrevious().addEventHandler('click', this.previousPageClickHandler, this);
            this.view.getNext().addEventHandler('click', this.nextPageClickHandler,this);

            this._windowEvtId = core.Window.addEventHandler('resize', this.redraw.bind(this));

            this.view.getElement().setStyle('max-width', this.options.maxWidth || '500px');
        },

        /**
         * Overrides method from widget.
         * Executes before destroy, remove event handlers.
         *
         * @method onDestroy
         * @private
         */
        onDestroy: function () {
            if (this._windowEvtId) {
                core.Window.removeEventHandler(this._windowEvtId);
            }
        },

        onDOMAttach: function() {
            this.setPage(this.selectedPage);
        },

        /**
         * Click handler for the previous page button
         *
         * @method previousPageClickHandler
         * @private
         * @param {Event} e
         */
        previousPageClickHandler: function (e) {
            this.previousPage();
            this.view.getPrevious().setAttribute('href', '#' + this.getUrl(this.selectedPage));
            if (!this.url) {
                e.preventDefault();
            }
        },

        /**
         * Click handler for the next page button
         *
         * @method nextPageClickHandler
         * @private
         * @param {Event} e
         */
        nextPageClickHandler: function (e) {
            this.nextPage();
            this.view.getNext().setAttribute('href', '#' + this.getUrl(this.selectedPage));
            if (!this.url) {
                e.preventDefault();
            }
        },

        /**
         * Click handler for page buttons
         *
         * @method pageClickHandler
         * @private
         * @param {int} pageNumber
         * @param {Event} e
         */
        pageClickHandler: function (pageNumber, e) {
            this.setPage(pageNumber);
            if (!this.url) {
                e.preventDefault();
            }
        },

        /**
         * Calculates the amount of free space remaining for pages (width - previousBtn - nextBtn)
         *
         * @method calculateFreeSpace
         * @private
         * @return {int} freeSpace
         */
        calculateFreeSpace: function () {
            var parentWidth = this.view.getWrapper().getProperty('offsetWidth') - this._pageMargin; // 2px margin
            return Math.floor((parentWidth - this._minWidth * 2));
        },

        /**
         * Gets the width of the page by calculating the number of characters, then adding padding + margin. OffsetWidth is far too slow to use.
         *
         * @method getPageWidth
         * @private
         * @param {int} pageNumber
         * @return {int} pageWidth
         */
        getPageWidth: function (pageNumber) {
            return Math.max((pageNumber === 0 ? 1 : pageNumber.toString().length) * this._numberWidth + this._pageMargin + this._pagePadding, this._minWidth);
        },

        /**
         * Calculates the minimum pixel width including margin of a page element
         *
         * @method calculateMinPageWidth
         * @private
         */
        calculateMinPageWidth: function () {
            var computedStyle = window.getComputedStyle(this.view.getPreviousLi()._getHTMLElement(), null);
            var marginLeft = parseInt(computedStyle.getPropertyValue('margin-left'), 10);
            var marginRight = parseInt(computedStyle.getPropertyValue('margin-right'), 10);
            this._pagePadding = 8;
            this._pageMargin = marginLeft + marginRight;
            this._minWidth = this.view.getPrevious().getProperty('offsetWidth') + this._pageMargin;
        },

        /**
         * Calculates the pixel size of the number. This is used by getPageWidth to figure out how wide it the page is.
         *
         * @method calculateNumberWidth
         * @private
         * @param {int} number
         */
        calculateNumberWidth: function (number) {
            // TODO: should be changed to use templates
            var li = core.Element.parse('<li class="ebPagination-entry"><a style="width:auto" class="ebPagination-entryAnchor ebPagination-entryAnchor_current" href="#">' + number + '</a></li>');
            this.view.getPages().append(li);
            this._numberWidth = li.children()[0].getProperty('offsetWidth') - 8;
            li.remove();
        },

        /**
         * Clears and renders the widget
         *
         * @method redraw
         */
        redraw: function () {
            this.removePages();
            this.calculateNumberWidth(1);
            this.calculateMinPageWidth();

            this._freeSpace = this.calculateFreeSpace();
            var leftIndex = this.selectedPage - 1;
            var rightIndex = this.selectedPage + 1;

            var firstPage = insertPage.call(this, 1);

            if (this.selectedPage !== 1 && this.selectedPage !== this.totalPages) {
                insertPage.call(this, this.selectedPage);
            }

            var lastPage;
            if (this.totalPages !== 1) {
                lastPage = insertPage.call(this, this.totalPages);
            }

            var leftElem = this._currPage;
            var rightElem = this._currPage;
            while (this._freeSpace > 0) {
                if ((rightIndex < this.totalPages) && (this._freeSpace - this.getPageWidth(rightIndex) >= 0)) {
                    rightElem = insertPage.call(this, rightIndex, insertAfterChild, rightElem);
                    rightIndex++;
                }

                if ((leftIndex > 1) && (this._freeSpace - this.getPageWidth(leftIndex) >= 0)) {
                    leftElem = insertPage.call(this, leftIndex, insertBeforeChild, leftElem);
                    leftIndex--;
                }

                if ((leftIndex <= 1 && rightIndex >= this.totalPages) || (this._freeSpace - this.getPageWidth(leftIndex) < 0 && this._freeSpace - this.getPageWidth(rightIndex) < 0)) {
                    if (leftIndex > 1) {
                        this.view.getPages().children()[1].remove();
                        insertAfterChild(this.createDots(), firstPage);
                    }
                    if (rightIndex < this.totalPages) {
                        this.view.getPages().children()[this.view.getPages().children().length - 2].remove();
                        insertBeforeChild(this.createDots(), lastPage);
                    }
                    break;
                }
            }

            this.view.getPrevious().removeModifier('disabled');
            this.view.getNext().removeModifier('disabled');

            if (this.selectedPage === 1) {
                this.view.getPrevious().setModifier('disabled');
            }

            if (this.selectedPage === this.totalPages) {
                this.view.getNext().setModifier('disabled');
            }
        },

        /**
         * Creates a "..." element
         *
         * @method createDots
         * @private
         */
        createDots: function () {
            // TODO: should be changed to use templates
            var dotsElem = core.Element.parse('<li class="ebPagination-entry"><a class="ebPagination-entryAnchor ebPagination-entryAnchor_dots" href="#">...</a></li>');

            dotsElem.children()[0].addEventHandler('click', function (e) {
                e.preventDefault();
            }.bind(this));

            return dotsElem;
        },

        /**
         * Selects the previous page and triggers callback
         *
         * @method previousPage
         */
        previousPage: function () {
            if (this.selectedPage > 1) {
                this.selectedPage--;
                this.setPage(this.selectedPage);
            }
        },

        /**
         * Selects the next page and triggers callback
         *
         * @method nextPage
         */
        nextPage: function () {
            if (this.selectedPage < this.totalPages) {
                this.selectedPage++;
                this.setPage(this.selectedPage);
            }
        },

        /**
         * Creates a page element with the specified page number to the list of pages. If selected is true, it will be highlighted.
         *
         * @method createPage
         * @private
         * @param {int} pageNumber
         * @param {Boolean} selected
         */
        createPage: function (pageNumber, selected) {
            // TODO: should be changed to use templates
            var pageElem = core.Element.parse('<li class="ebPagination-entry"><a class="ebPagination-entryAnchor" href="#' + this.getUrl(pageNumber) + '">' + pageNumber + '</a></li>');

            pageElem.children()[0].addEventHandler('click', function (e) {
                this.pageClickHandler(pageNumber, e);
            }.bind(this));

            if (selected) {
                pageElem.children()[0].setModifier('current');
            }

            return pageElem;
        },

        /**
         * If the url option is a string, it will return <url>/<pageNumber>. If the url is a function, it will pass the page number into that function and expect a string to be returned.
         *
         * @method getUrl
         * @private
         * @param {int} pageNumber
         * @return {String} url
         */
        getUrl: function (pageNumber) {
            if (this.url && typeof this.url === 'string') {
                return this.url + '/' + pageNumber;
            } else if (this.url && typeof this.url === 'function') {
                return this.url(pageNumber);
            } else {
                return '';
            }
        },

        /**
         * Triggers the callback passing the pageNumber as a parameter
         *
         * @method triggerCallback
         * @private
         * @param {int} pageNumber
         */
        triggerCallback: function (pageNumber) {
            if (this.options.onPageChange) {
                this.options.onPageChange(pageNumber);
            }
            this.trigger('pagechange', pageNumber);
        },

        /**
         * Empties out the list of pages
         *
         * @method removePages
         * @private
         */
        removePages: function () {
            var pages = this.view.getPages().children();
            var len = pages.length;
            while (len--) {
                pages[len].remove();
            }
        },

        /**
         * Selects the page and forces a draw.
         *
         * @method setPage
         * @param {int} pageNumber
         */
        setPage: function (pageNumber) {
            if (pageNumber > 0 && pageNumber <= this.totalPages) {
                this.selectedPage = pageNumber;
                this.redraw();
                this.triggerCallback(pageNumber);
            }
        },

        /**
         * Sets the total number of pages.
         * @method setPages
         * @param {int} numberOfPages
         */
        setPages: function (numberOfPages) {
            if (numberOfPages > 0) {
                this.totalPages = numberOfPages;
                if (this.selectedPage > numberOfPages) {
                    this.setPage(numberOfPages);
                } else {
                    this.redraw();
                }
            }
        }

    });

    /* ++++++++++++++++++++++++++++++++++++++++++ PRIVATE METHODS ++++++++++++++++++++++++++++++++++++++++++ */

    function insertBeforeChild(page, child) {
        child._getHTMLElement().parentNode.insertBefore(page._getHTMLElement(), child._getHTMLElement());
    }

    function insertAfterChild(page, child) {
        child._getHTMLElement().parentNode.insertBefore(page._getHTMLElement(), child._getHTMLElement().nextSibling);
    }

    function insertPage(number, whereFn, child) {
        /*jshint validthis:true */
        var page = this.createPage(number, this.selectedPage === number);
        if (whereFn) {
            whereFn.call(this, page, child);
        } else {
            this.view.getPages().append(page);
        }
        this._freeSpace -= this.getPageWidth(number);
        this._currPage = this.selectedPage === number ? page : this._currPage;
        return page;
    }

});

define('widgets/Pagination',['widgets/Pagination/Pagination'],function (main) {
                        return main;
                    });

