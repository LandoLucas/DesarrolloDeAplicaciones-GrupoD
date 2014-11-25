'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');

app.controller('CargarDatosCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate) {

	$scope.paginaActual = 1;
	$scope.registrosTotales = 0;
	$rootScope.inDatos = false;
	
	 globalService.setInCargarDatos();


	

	$scope.activate = function(){$rootScope.inDatos = true;};

	


	// Default Controlador
	//$scope.inicializarVista();

});
