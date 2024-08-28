/*global define, describe, it, expect */
define([
    'activetasks/ActiveTasks'
], function (ActiveTasks) {
    'use strict';

    describe('ActiveTasks', function () {

        it('ActiveTasks should be defined', function () {
            expect(ActiveTasks).not.to.be.undefined;
        });

    });

});
