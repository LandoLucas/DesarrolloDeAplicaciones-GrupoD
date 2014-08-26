'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');
app.controller('CrudSucursalCtrl', function($scope, $location, $http,
		$rootScope, growl, dialogs) {

	$scope.inicializarVista = function() {
		
		if ($rootScope.editandoSucursal) {

			var sucursalToEdit = $rootScope.sucursalToEdit;
			$scope.titulo = "Editar Sucursal";
			
			sucursalToEdit.idEmpresa;

			obtenerComboEmpresa($http, function(response) {
				 $scope.empresas = extractCombo(response);
				 $scope.selectEmpresa = {};
				 $scope.selectEmpresa.value = null;
				 
				 
					for (var n = 0; n < $scope.empresas.length; n++) {
						if ($scope.empresas[n].value == sucursalToEdit.idEmpresa) {
							 $scope.selectEmpresa = $scope.empresas[n];
						}
					}
					
			});

			$scope.inputCodigo = sucursalToEdit.codigo;
			$scope.inputDescripcion = sucursalToEdit.descripcion;
			$scope.inputNumeroDeFantasia = sucursalToEdit.numeroDeFantasia;
			$scope.inputNombreDeFantasia = sucursalToEdit.nombreDeFantasia;
			$scope.inputDireccion = sucursalToEdit.direccion;
			$scope.inputResponsable = sucursalToEdit.responsable;
		} else {
			$scope.titulo = "Nueva sucursal";
			obtenerComboEmpresa($http, function(response) {
				 $scope.empresas = extractCombo(response);
				 $scope.selectEmpresa = null;
			});
		}

	}

	$scope.modoEdicion = function() {
		return $rootScope.editandoSucursal;
	}

	$scope.modoRegistro = function() {
		return $rootScope.editandoSucursal == false;
	}

	$scope.registrarSucursalOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Sucursal registrada.");
			$location.path('/sucursales');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}

	$scope.actualizarSucursalOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Sucursal actualizada.");
			$location.path('/sucursales');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}

	$scope.populateParams = function() {
		var data = {
			id_empresa : $scope.selectEmpresa.value,
			codigo : $scope.inputCodigo,
			descripcion : $scope.inputDescripcion,
			numero_de_fantasia : $scope.inputNumeroDeFantasia,
			nombre_de_fantasia : $scope.inputNombreDeFantasia,
			direccion : $scope.inputDireccion,
			responsable : $scope.inputResponsable
		};
		return data;
	}

	$scope.registrarSucursal = function() {
		if(validate()){
			var data = $scope.populateParams();
			invokeRegistrarSucursal($http, data, $scope.registrarSucursalOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		}
		
	}

	$scope.actualizarSucursal = function() {
		if(validate()){
			var data = $scope.populateParams();
			data.id = $rootScope.sucursalToEdit.id;
			invokeActualizarSucursal($http, data, $scope.actualizarSucursalOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		}
		
	}
	
	function validate(){
		if ($scope.inputCodigo == null || $scope.inputCodigo== ""){
			return false;
		}
		if($scope.inputDescripcion == null || $scope.inputDescripcion == ""){
			return false;
		}
		
		if($scope.selectEmpresa == null ){
			return false;
		}
		return true;
	}
	
	function getErrorMessage(){
							
		if ($scope.inputCodigo == null || $scope.inputCodigo== ""){
			growl.error("Código numérico requerido");
		}
		if($scope.inputDescripcion == null || $scope.inputDescripcion == ""){
			growl.error("Descripción requerida");
		    
		}
		
		if($scope.selectEmpresa == null ){
			growl.error("Seleccione una empresa") ;
		}
		
	}

	$scope.inicializarVista();
});
