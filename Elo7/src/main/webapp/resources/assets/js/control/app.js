'use strict';

var Elo7App = {};

var App = angular.module('Elo7App', ['Elo7App.filters', 'Elo7App.services', 'Elo7App.directives']);

App.config(['$routeProvider', function ($routeProvider) {
	
	$routeProvider.when('/agendamento', {
		templateUrl: 'agendamento/layout',
		controller: AgendamentoTransferenciaController
	});
	
	$routeProvider.when('/agendamentolistagem', {
		templateUrl: 'agendamentolistagem/layout',
		controller: AgendamentoListController
	});
	
    $routeProvider.otherwise({redirectTo: '/agendamento'});
    
}]);