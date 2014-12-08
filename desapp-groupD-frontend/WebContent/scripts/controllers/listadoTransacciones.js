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

	$scope.accountsOK = function(response) {
		$scope.accounts = response;
	};
	
	$scope.consolidacionOK = function(response) {
		$scope.account = response.data;
	};
	
	
	function getAllOperations(){
		restServices.invokeGetAllOperations($http, {}, $scope.operacionesOk , restServices.defaultHandlerOnError);
	}
	
	function getAccountsState(){
		restServices.invokeGetAccounts($http, {}, $scope.accountsOK , restServices.defaultHandlerOnError);
	}
	
	getAllOperations();
	getAccountsState();
	
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
		available: "Available",
		devengado: "Devengado",
		devengada: "Necesita devengar",
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
		$translate('DEVENGADO').then(function(text) {
			$scope.tableColumns.devengado = text;
		});
		$translate('DEVENGADA').then(function(text) {
			$scope.tableColumns.devengada = text;
		});
	}
	
	
	$scope.consolidateAccounts = function() {
		restServices.invokeConsolidateAccounts($http, {} , $scope.consolidacionOK , restServices.defaultHandlerOnError);
		$scope.operaciones = getAllOperations();
		$scope.accounts = getAccountsState();
	};
	
	$(function () {
		  $('[data-toggle="popover"]').popover();
		});
	
	
	$scope.style = 'background-color : red'
	
	$scope.outcomeOperationColor = function(isIncome) {
		if(! isIncome){ return 'color : red'}
	}
		
});
