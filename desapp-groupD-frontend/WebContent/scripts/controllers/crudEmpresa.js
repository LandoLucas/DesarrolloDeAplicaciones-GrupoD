'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');
app.controller('CrudEmpresaCtrl', function($scope, $location, $http,
		$rootScope, growl) {

	if ($rootScope.editandoEmpresa) {
		var empresaToEdit = $rootScope.empresaToEdit;
		$scope.inputCodigo = empresaToEdit.codigo;
		$scope.inputRazonSocial = empresaToEdit.razonSocial;
		$scope.inputDireccion = empresaToEdit.direccion;
		$scope.inputDescripcion = empresaToEdit.descripcion;
		$scope.titulo = "Editar empresa";
	} else {
		$scope.titulo = "Nueva empresa";
	}
	;

	$scope.modoEdicion = function() {
		return $rootScope.editandoEmpresa;
	}

	$scope.modoRegistro = function() {
		return $rootScope.editandoEmpresa == false;
	}

	$scope.registrarEmpresaOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Empresa registrada.");
			$location.path('/empresas');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}

	$scope.actualizarEmpresaOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Empresa actualizada.");
			$location.path('/empresas');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}

	$scope.populateParams = function() {
		var data = {
			codigo : $scope.inputCodigo,
			nombre : $scope.inputNombre,
			razon_social : $scope.inputRazonSocial,
			direccion : $scope.inputDireccion,
			descripcion : $scope.inputDescripcion
		};
		return data;
	}

	$scope.registrarEmpresa = function() {
		var data = $scope.populateParams();
		if(validate()){
			invokeRegistrarEmpresa($http, data, $scope.registrarEmpresaOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		}
		
	}
	
	function validate(){
		if ($scope.inputCodigo == null || $scope.inputRazonSocial == ""){
			return false;
		}
		if($scope.inputRazonSocial == null || $scope.inputRazonSocial == ""){
			return false;
		}
		return true;
	}
	
	function getErrorMessage(){

		if ($scope.inputCodigo == null || $scope.inputCodigo == ""){
			growl.error("Código numérico requerido");
		}
		if($scope.inputRazonSocial == null || $scope.inputRazonSocial == ""){
			growl.error("Razón Social requerida");
		}

	}

	$scope.actualizarEmpresa = function() {
		if(validate()){
			var data = $scope.populateParams();
			data.id = $rootScope.empresaToEdit.id;
			invokeActualizarEmpresa($http, data, $scope.actualizarEmpresaOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		
		}
	}

});
