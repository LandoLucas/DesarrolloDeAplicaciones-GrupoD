'use Strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */

angular
		.module('tp-dapp-eiroa-lando')
		.controller(
				'AccountCtrl',
				function($scope, $location, $http, $rootScope, 
						growl,dialogs,globalService,$translate,restServices,ngTableParams) {

					// Inicializa los combos
					$scope.inicializarVista = function() {
						globalService.setInMainMenu();
						$scope.resultAccounts = [
								{id:1,name: 'Caja chica', isBankAccount: 'NO'},
								{id:2,name: 'Cuenta bancaria Banco Nación', isBankAccount: 'SI'}];
					}
					
					$scope.tableParams = new ngTableParams({
						page : 1, // show first page
						count : 10, // count per page
						sorting : {
							name : 'asc' // initial sorting
						}
					}, {
						total : function() {
							return getData().length;
						}, // length of data
						getData : function($defer, params) {
							restServices.invoke
							var filteredData = billElements;
							var orderedData = params.sorting() ? $filter('orderBy')(
									filteredData, params.orderBy()) : filteredData;

							$defer.resolve(orderedData.slice((params.page() - 1)
									* params.count(), params.page() * params.count()));
						},
						$scope : {
							$data : {}
						}
					});

					
					// Default Controlador
					$scope.inicializarVista();

				});


