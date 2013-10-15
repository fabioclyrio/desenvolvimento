'use strict';

var AgendamentoListController = function($scope, $http, $location, $window) {
	
	$scope.form = {};
	$scope.form.numeroConta = '';
	$scope.listAgendamentos = [];
	
	$scope.submitForm = function(){
		if($scope.form.numeroConta != ''){
			formataConta();
			$scope.submit();
		} else {
			alert("Preencha o número da conta!");
		}
	}
	
	$scope.submit = function(){
		$http.post('#/agendamentolistagem/listaAgendamentos', $scope.form).success(function(list) {
			$scope.listAgendamentos = list;
        }).error(function() {
        	resetValues();
        	alert("Nenhum agendamento encontrado!");
        });
    };
    
    function formataConta(){
		$scope.form.numeroConta = angular.element.find('#origem')[0].value;
	}
    
    function resetValues(){
    	$scope.form.numeroConta = '';
    	$scope.listAgendamentos = [];
    }
	
};