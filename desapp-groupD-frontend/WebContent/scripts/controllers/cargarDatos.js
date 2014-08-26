'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');

app.controller('CargarDatosCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService) {

	$scope.paginaActual = 1;
	$scope.registrosTotales = 0;
	$rootScope.inDatos = false;
	
	 globalService.setInCargarDatos();


	// Inicializa los combos
	$scope.inicializarVista = function() {
		$scope.resultadoEmpresas = [];
		$scope.registrosTotales = 0;
	}

	$scope.activate = function(){$rootScope.inDatos = true;};

	


	// $scope.populateFiltro = function() {
	// 	var filtro = {
	// 		codigo : $scope.inputBusquedaCodigo,
	// 		razon_social : $scope.inputBusquedaRazonSocial,
	// 		direccion : $scope.inputBusquedaDireccion,
	// 		descripcion : $scope.inputBusquedaDescripcion,
	// 		pn : 1,
	// 		ps : 1,
	// 		rc : 0
	// 	}
	// 	return filtro;
	// }

	// $scope.buscarEmpresas = function() {
	// 	var filtro = $scope.populateFiltro();

	// 	$scope.tableParams.page(1);
	// 	$scope.tableParams.reload();

	// }

	// $scope.procesarBusquedaOk = function(response) {
	// 	$scope.registrosTotales = response["resultCount"];
	// 	$scope.resultadoEmpresas = [];
	// 	var datos = response["data"];
	// 	if (datos != null && datos != undefined) {
	// 		for (var n = 0; n < datos.length; n++) {
	// 			$scope.resultadoEmpresas.push(datos[n]);
	// 		}
	// 	}
	// 	if (!codigoOk(response)) {
	// 		var descripcion = response['desc'];
	// 		growl.error(descripcion);
	// 	}
	// }

	// $scope.tableParams = new ngTableParams({
	// 	page : $scope.paginaActual,
	// 	count : 10,
	// }, {
	// 	total : $scope.registrosTotales,
	// 	getData : function($defer, params) {

	// 		$scope.resultadoEmpresas = [];

	// 		// el filter que se usa aca es el que
	// 		// provee
	// 		// angular

	// 		var filtro = $scope.populateFiltro();
	// 		$scope.paginaActual = params.$params.page;
	// 		filtro.pn = params.$params.page;
	// 		filtro.ps = params.$params.count;
	// 		filtro.rc = $scope.registrosTotales;

	// 		invokeBuscarEmpresas($http, filtro, $scope.procesarBusquedaOk,
	// 				defaultHandlerOnError);

	// 		params.total($scope.registrosTotales); // Recalculamos
	// 		$defer.resolve($scope.resultadoEmpresas);
	// 	}
	// });

	// $scope.confirmacionBorrarEmpresa = function(idEmpresa, rs) {
	// 	var dlg = dialogs.confirm('¿Desea borrar la empresa ' + rs + ' ?',
	// 			'Junto con la empresa, borrará toda su información asociada: sectores, sucursales y terminales');
	// 	dlg.result.then(function(btn) {
	// 		borrarEmpresa(idEmpresa);
	// 	}, function(btn) {

	// 	});
	// };

	// function borrarEmpresa(idEmpresa) {

	// 	var filtro = {
	// 		id : idEmpresa,
	// 	};
	// 	invokeBorrarEmpresa($http, filtro, $scope.procesarBorradoOk,
	// 			defaultHandlerOnError);
	// }

	// $scope.procesarBorradoOk = function(response) {
	// 	$scope.tableParams.reload();
	// 	growl.info("La empresa ha sido eliminada");
	// 	if (!codigoOk(response)) {
	// 		var descripcion = response['desc'];
	// 		growl.error(descripcion);
	// 	}
	// }

	// $scope.nuevaEmpresa = function() {
	// 	$rootScope.editandoEmpresa = false;
	// 	$location.path('/nuevaEmpresa');
	// }

	// $scope.prepararEditarEmpresa = function(idEmpresa) {
	// 	var data = {
	// 		id : idEmpresa,
	// 	};
	// 	invokeBuscarEmpresa($http, data, function(response) {
	// 		$rootScope.empresaToEdit = response;
	// 		$rootScope.editandoEmpresa = true;
	// 		$location.path('/editarEmpresa');
	// 	}, defaultHandlerOnError);
	// }

	// Default Controlador
	//$scope.inicializarVista();

});
