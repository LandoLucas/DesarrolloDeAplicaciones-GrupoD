(function() {
	'use strict';
	angular.module('loginModule',
			[ 'http-auth-interceptor', 'LocalStorageModule' ,'angular-growl','ngRoute'])

	.controller(
			'LoginCtrl',
			function($scope, $http, authService, localStorageService,
					$location, $window,growl,$timeout,$route, $rootScope) {
				$scope.submitLogin = function() {
					var data = {
						username : $scope.user,
						password : $scope.pass
					}
					autenticar($http, data, $scope.procesarLoginOk,
							$scope.procesarLoginError);
				};

				$scope.procesarLoginOk = function(response) {

					var codigo = response['code'];
					var token = response['token'];
					var usuario = response['usuario'];
					var nivelPermisos = response['nivelPermisos'];
					var acciones = response['acciones'];

					if (codigo == 0) {
						localStorageService.set('Logged', true);
						localStorageService.set('tk', token);
						localStorageService.set('usuario', usuario);
						localStorageService.set('np', nivelPermisos);
						localStorageService.set('ac', acciones);
										
						authService.loginConfirmed();
						$location.path('/sincronizacion');
						$window.location.reload();
					} else {
						growl.error(response['desc']);
						
					}
				};

				$scope.procesarLoginError = function(response) {
					growl.error('Error al intentar autenticar al usuario: '
							+ response);

				};

			});
	
	
})();
