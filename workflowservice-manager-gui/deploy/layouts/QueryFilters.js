define('layouts/query_filters/QueryFilters',['layouts/TopSection','layouts/SlidingPanels'],function (TopSection, SlidingPanels) {
    return TopSection.extend({
        onViewReady: function () {
            this.setContent(new SlidingPanels({
                context: this.context,
                main: this.options.main,
                left: this.options.left,
                right: this.options.right
            }));
        }

    });

});

define('layouts/QueryFilters',['layouts/query_filters/QueryFilters'],function (main) {
                        return main;
                    });

