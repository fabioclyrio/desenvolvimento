'use strict';

/* Filters */

var Elo7AppFilters = angular.module('Elo7App.filters', []);

Elo7AppFilters.filter('interpolate', ['version', function (version) {
    return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    };
}]);