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
	};
	
	function getAllOperations(){
		restServices.invokeGetAllOperations($http, {}, $scope.operacionesOk , restServices.defaultHandlerOnError);
	}
	
	getAllOperations();
	
	
	$scope.tableColumns = {
		date : "Date",
		category: "Category",
		subcategory: "Subcategory",
		concept: "Concept",	
		shift: "Shift",
		cash: "Cash",
		debit: "Debit",
		credir: "Credit",
		totalCash: "Total Cash",
		totalBank: "Total in Bank",
		available: "Available"
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
		$translate('SUBCATEGORY').then(function(text) {
			$scope.tableColumns.subcategory = text;
		});
		$translate('CONCEPT').then(function(text) {
			$scope.tableColumns.concept = text;
		});
		$translate('FORM_SHIFT').then(function(text) {
			$scope.tableColumns.shift = text;
		});
		$translate('FORM_CASH').then(function(text) {
			$scope.tableColumns.cash = text;
		});
		$translate('FORM_DEBIT').then(function(text) {
			$scope.tableColumns.debit = text;
		});
		$translate('FORM_CREDIT').then(function(text) {
			$scope.tableColumns.credit = text;
		});
		$translate('FORM_TOTAL_CASH').then(function(text) {
			$scope.tableColumns.totalCash = text;
		});
		$translate('FORM_TOTAL_BANK').then(function(text) {
			$scope.tableColumns.totalBank = text;
		});
		$translate('FORM_AVAILABLE').then(function(text) {
			$scope.tableColumns.available = text;
		});
		
	}

	
});
