'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');
app.controller('CrudOperationCtrl', function($scope, $location, $http,
		$rootScope, growl,globalService) {
	globalService.setInNewOperation();
	if ($rootScope.editingOperation) {
		var operationToEdit = $rootScope.operationToEdit;
		$scope.inputDate = operationToEdit.date;
		$scope.inputCode = operationToEdit.code;
		$scope.inputAmount = operationToEdit.amount;
		$scope.inputDescription = operationToEdit.description;
		$scope.inputPaymentType = operationToEdit.paymentType;
		$scope.inputCategory = operationToEdit.category;
		$scope.inputSubCategory = operationToEdit.subCategory;
		$scope.inputConcept = operationToEdit.concept;
		if($rootScope.outcomeOperation){
			$scope.titulo = "Editar egreso";
		}else{
			$scope.titulo = "Editar ingreso";
		}
	} else {
		if($rootScope.outcomeOperation){
			$scope.titulo = "Nuevo egreso";
		}else{
			$scope.titulo = "Nuevo ingreso";
		}
		
	};

	$scope.modoEdicion = function() {
		return $rootScope.editingOperation;
	}

	$scope.modoRegistro = function() {
		return $rootScope.editingOperation == false;
	}

	$scope.registerOperationOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Operacion registrada.");
			$location.path('/cargarDatos');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}

	$scope.updateOperationOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Operacion actualizada.");
			$location.path('/cargarDatos');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}

	$scope.populateParams = function() {
		var data = {
			code : $scope.inputCodigo,
			date : $scope.inputNombre,
			amount: $scope.inputRazonSocial,
			category : $scope.inputDireccion,
			subCategory : $scope.inputDescripcion,
			concept: $scope.inputConcept,
			paymentTyoe : $scope.inputPaymentType,
			description : $scope.descriptionType
		};
		return data;
	}

	$scope.registerOperation = function() {
		var data = $scope.populateParams();
		if(validate()){
			invokeRegisterOperation($http, data, $scope.registerOperationOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		}
		
	}
	
	function validate(){
		if ($scope.inputCode == null || $scope.inputCode == ""){
			return false;
		}
		return true;
	}
	
	function getErrorMessage(){

		if ($scope.inputCode == null || $scope.inputCode== ""){
			growl.error("Código numérico requerido");
		}

	}

	$scope.updateOperation= function() {
		if(validate()){
			var data = $scope.populateParams();
			data.id = $rootScope.operationToEdit.id;
			invokeUpdateOperation($http, data, $scope.updateOperationOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		
		}
	}

});
