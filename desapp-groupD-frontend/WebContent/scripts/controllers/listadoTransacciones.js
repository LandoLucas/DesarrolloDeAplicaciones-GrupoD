'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');

app.controller('ListadoTransaccionesCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate,restServices) {

	$scope.operacionesOk = function(response) {
		$scope.operaciones = response;
	}
	
	function getAllOperations(){
		restServices.invokeGetAllOperations($http, {}, $scope.operacionesOk , restServices.defaultHandlerOnError);
	}
	
	getAllOperations()
	
	
	$scope.tableColumns = {
		date : "date",
		category: "category"
	};
	translateTableColumns();
	$rootScope.$on('$translateChangeSuccess', function() {
		translateTableColumns();
	});
	
	function translateTableColumns() {
		$translate('FORM_DATE').then(function(text) {
			$scope.tableColumns.date = text;
		});
		$translate('CATEGORY').then(function(text) {
			$scope.tableColumns.category = text;
		});
//		$translate('FORM_PRICE').then(function(text) {
//			$scope.tableColumns.price = text;
//		});
//		$translate('FORM_ACTIONS').then(function(text) {
//			$scope.tableColumns.actions = text;
//		});
	}

	
});
