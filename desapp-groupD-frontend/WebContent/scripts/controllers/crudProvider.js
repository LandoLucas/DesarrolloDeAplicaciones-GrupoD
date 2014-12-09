'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
angular.module('tp-dapp-eiroa-lando').controller(
		'CrudProviderCtrl',
		function($http, $location, $scope, ngTableParams, $filter, $window,
				$route, $rootScope, growl, dialogs, globalService, $translate,
				restServices, $timeout) {
			globalService.setInMainMenu();
			
			$scope.inicializarVista = function() {
				if(!$rootScope.registeringProvider){
					$scope.inputClientSeller = $rootScope.providerToEdit.name
					$scope.inputTradeName = $rootScope.providerToEdit.tradeName
					$scope.inputClientSellerId = $rootScope.providerToEdit.providerId
					$scope.inputPhone= $rootScope.providerToEdit.telephone
					$scope.inputCuit= $rootScope.providerToEdit.cuit
					$scope.inputAddress= $rootScope.providerToEdit.direction
				}
				
			}
			$scope.editing= function(){
				return !$rootScope.registeringProvider;
			}
			$scope.registerProvider = function() {

				if (validate()) {
					var data = $scope.populateParams();
					restServices.invokeRegisterProvider($http, data,
							$scope.registerProviderOk,
							restServices.defaultHandlerOnError);
				} else {
					getErrorMessage();
				}

			}

			$scope.registerProviderOk = function(response) {
				$translate('PROVIDER_REGISTERED').then(function (text) {
		    		growl.info(text);
				 });
				$location.path('/pyc');
			}

			$scope.updateProviderOk = function(response) {
				$translate('DIALOG_EDIT_SUCCESS').then(function (text) {
		    		growl.info(text);
				 });
				$location.path('/pyc');
			}

			$scope.populateParams = function() {

				var data = {
					name : $scope.inputClientSeller,
					tradeName: $scope.inputTradeName,
					providerId:$scope.inputClientSellerId,
					phone : $scope.inputPhone,
					cuit : $scope.inputCuit,
					address : $scope.inputAddress,
				};
				return data;
			}

			function validate() {
				if ($scope.inputClientSeller == null || $scope.inputClientSeller== "") {
					return false;
				}
				if ($scope.inputTradeName == null || $scope.inputTradeName== "") {
					return false;
				}
				if ($scope.inputClientSellerId == null
						|| $scope.inputClienSellerId == "") {
					return false;
				}
				if ($scope.inputPhone == null
						|| $scope.inputPhone== "") {
					return false;
				}
				if ($scope.inputCuit == null
						|| $scope.inputCuit == "") {
					return false;
				}
				if ($scope.inputAddress == null
						|| $scope.inputAddress== "") {
					return false;
				}
				return true;
			}

			function getErrorMessage() {
				if ($scope.inputClientSeller == null || $scope.inputClientSeller== "") {
					return false;
				}
				if ($scope.inputTradeName == null || $scope.inputTradeName== "") {
					return false;
				}
				if ($scope.inputClientSellerId == null
						|| $scope.inputClienSellerId == "") {
					return false;
				}
				if ($scope.inputPhone == null
						|| $scope.inputPhone== "") {
					return false;
				}
				if ($scope.inputCuit == null
						|| $scope.inputCuit == "") {
					return false;
				}
				if ($scope.inputAddress == null
						|| $scope.inputAddress== "") {
					return false;
				}
			}

			$scope.updateProvider = function() {
				if (validate()) {
					var data = $scope.populateParams();
					data.id = $rootScope.providerToEdit.id;
					restServices.invokeEditProvider($http, data,
							$scope.updateProviderOk,
							restServices.defaultHandlerOnError);
				} else {
					getErrorMessage();

				}
			}
			$scope.inicializarVista();

		});