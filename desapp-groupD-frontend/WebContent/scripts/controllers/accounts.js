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
						growl,dialogs,globalService,$translate,restServices) {

					// Inicializa los combos
					$scope.inicializarVista = function() {
						globalService.setInMainMenu();
						$scope.resultAccounts = [
								{id:1,name: 'Caja chica', isBankAccount: 'NO'},
								{id:2,name: 'Cuenta bancaria Banco Nación', isBankAccount: 'SI'}];
						

						

					}

					
					// Default Controlador
					$scope.inicializarVista();

				});
