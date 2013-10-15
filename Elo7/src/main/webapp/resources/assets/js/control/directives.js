'use strict';

/* Directives */

var Elo7AppDirectives = angular.module('Elo7App.directives', ['$strap.directives']);

Elo7AppDirectives.directive('appVersion', ['version', function (version) {
    return function (scope, elm, attrs) {
        elm.text(version);
    };
}]);

Elo7AppDirectives.value('$strapConfig', {
	datepicker: {
		language: "pt-BR",
	    format: 'dd/mm/yyyy'
	}
});

Elo7AppDirectives.directive('numbersOnly', function(){
	   return {
	     require: 'ngModel',
	     link: function(scope, element, attrs, modelCtrl) {
	       modelCtrl.$parsers.push(function (inputValue) {
	           if (inputValue == undefined) return '' 
	           var transformedInput = inputValue.replace(/[^0-9-]/g, ''); 
	           if (transformedInput!=inputValue) {
	              modelCtrl.$setViewValue(transformedInput);
	              modelCtrl.$render();
	           }         
	           return transformedInput;         
	       });
	     }
	   };
	});

Elo7AppDirectives.directive('maskMoney', function() {
  return {
	    require: 'ngModel',
	    link: function(scope, element, attr, ngModel) {
	      element.maskMoney({thousands:'.', decimal:',', symbolStay: true});
	      element.bind('blur', function(){
	        ngModel.$setViewValue(element.val());
	      });
	    }
	  };
	});