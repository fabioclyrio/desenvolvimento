'use strict';

var AgendamentoTransferenciaController = function($scope, $http, $location, $window) {
	
	//nav
	$scope.goBack = function() {
		$window.location.reload();
    };
    //nav
    
	//object form
	$scope.form = {};
	$scope.form.dataAgendamento = new Date();
	$scope.form.valorTransferencia = "0,00";
	$scope.form.contaOrigem = "";
	$scope.form.contaDestino = "";
	$scope.form.tipoOperacao = "";
	//object form
	
	//validate
	$scope.confirmaData = function(){
		if($scope.form.dataAgendamento < new Date()){
			$scope.form.dataAgendamento = new Date();
			alert("A data selecionada não pode ser inferior à data atual!");
			return false;
		} else {
			return true;
		}
	};
	$scope.submitForm = function(){
		formataContas();
		if($scope.form.contaOrigem != '' && $scope.form.contaDestino != ''){
			if($scope.form.contaOrigem == $scope.form.contaDestino){
				alert("A Contas são identicas, por favor verifique!");
				$scope.form.contaOrigem = "";
				$scope.form.contaDestino = "";
			} else if($scope.form.valorTransferencia == '0,00'){
				alert("Por favor verifique o valor da transferência!");
			} else {
				if($scope.form.tipoOperacao == ''){
					alert("Por favor selecione uma operação de transferência!");	
				} else {
					$scope.form.dataAgendamento = formatDate(new Date());
					$scope.submit();
				}
			}
		} else {
			alert("Por favor preencha o número das contas!");
		}
	};
	//validate
	
	function formataContas(){
		$scope.form.contaOrigem = angular.element.find('#origem')[0].value;
		$scope.form.contaDestino = angular.element.find('#destino')[0].value;
	}
	
	//reset
	function reset(){
		$scope.form = {};
		$scope.form.dataAgendamento = new Date();
		$scope.form.valorTransferencia = "0,00";
		$scope.form.contaOrigem = "";
		$scope.form.contaDestino = "";
		$scope.form.tipoOperacao = "";
	}
	//reset
	
	//post json
    $scope.showAgendamentoInput = false;
	$scope.submit = function(){
		$scope.showAccess = true;
		$scope.showAgendamentoInput = true;
		$http.post('#/agendamento/salvarAgendamento', $scope.form).success(function(e) {
			if(e != null){
				alert("Agendamento realizado com sucesso!");
				$scope.showAgendamentoInput = false;
				$scope.showAccess = false;
				reset();
			}
        }).error(function() {
        	$scope.showAgendamentoInput = false;
        	$scope.showAccess = false;
        	reset();
        	alert("Ocorreu um erro, por favor verifique se todos os valores foram preenchidos!");
        });
    };
    //post json
    
    function formatDate(value) {
		var dia = value.getDate();
		var mes = value.getMonth()+1;
		var ano = value.getFullYear();
		var data = dia + "/" + mes + "/" + ano;
		return data;
    }
    
};