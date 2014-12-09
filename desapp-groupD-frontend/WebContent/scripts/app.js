	'use strict';

	/**
	 * @ngdoc overview
	 * @name tp-dapp-eiroa-lando
	 * @description # tp-dapp-eiroa-lando
	 * 
	 * Main module of the application.
	 */
	(function() {
		var app = angular.module('tp-dapp-eiroa-lando', [ 'ngAnimate', 'ngCookies',
				'ngResource', 'ngRoute', 'ngSanitize', 'ngTouch','facadeModule',
				'angularFileUpload', 'ngTable', 'http-auth-interceptor',
				'loginModule', 'contentModule', 'content-mocksModule',
				'LocalStorageModule', 'ngRoute', 'checklist-model',
				'angular-growl', 'ui.bootstrap', 'dialogs.main',
				'pascalprecht.translate','tp-dapp-eiroa-lando.services','percentage' ]);

		
		app.config(['$httpProvider', function($httpProvider) {
		    delete $httpProvider.defaults.headers.common["X-Requested-With"]
		}]);
		app.controller(
						'IndexCtrl',
						function($rootScope, $scope, $location, $http,
								localStorageService, $window, growl, $timeout,
								$document, $interval, dialogs,globalService,$translate) {
							
							$scope.changeLanguage = function (langKey) {
							    $translate.use(langKey);
							  };

							$scope.UserScope = globalService.getUserPosition();
							
                            $scope.goBack = function(){
                                $window.history.back();
                            };
                            
                            $scope.setInBills = function(){
                                globalService.setInBills();
                                $location.path('/comprobantes');
                            };
                            
                             $scope.setInBillTypes = function(){
                                globalService.setInBillTypes();
                                $location.path('/comprobantes');

                            };
                            
                            $scope.setInBuyingBillTypes = function(){
                                globalService.setInSellingBillTypes();
                                $location.path('/comprobantes');

                            };
                            
                            $scope.setInSellingBillTypes = function(){
                                globalService.setInBuyingBillTypes();
                                $location.path('/comprobantes');

                            };
                            
                            $scope.setInNewOutcome = function(){
                            	$rootScope.newOutcome = true;
                            }
                            
                            $scope.setInNewIncome = function(){
                            	$rootScope.newOutcome = false;
                            }
                            
							$scope.inMainMenu = function(){
								return localStorageService.get('userInMainMenu');
							}; 
							
							$scope.setInABill = function(){
								globalService.setInABill();
							}
							$scope.setInBBill = function(){
								globalService.setInBBill();
							}
							$scope.setInCBill = function(){
								globalService.setInCBill();
							}
							$scope.setInXBill = function(){
								globalService.setInXBill();
							}

							$rootScope.inDatos = false;
							$scope.usuarioLogueado = localStorageService
									.get('usuario');

							$rootScope.m = function(view_id) {
								var respuesta = false;
								var acciones = localStorageService.get('ac');
								if (acciones == null || acciones == undefined) {
									return false;
								} else {
									for (var n = 0; n < acciones.length; n++) {
										var a = acciones[n];
										if (a == view_id) {
											return true;
										}
									}
								}
								return respuesta;
							};

							$rootScope.check = function(view_id) {
								return true;
							}

							var logged = localStorageService.get('Logged');
							$scope.getLogged = function() {
								return logged;
							};
							$scope.getUsuario = function() {
								if (logged) {
									return localStorageService.get('usuario');
								} else {
									return 'Admin';
								}
							};


							$scope.launch = function(which) {
								switch (which) {
								case 'confirm':
									if (logged) {
										// Atencion, el dialog toma por defecto la
										// traduccion proporcionada
										// En este app.js, esta definida una
										// traduccion
										// pero tambien se puede enviar el titulo y
										// el mensaje de confirmacion
										// directamente como argumentos de la
										// funcion confirm()
										var dlg = dialogs.confirm();
										dlg.result.then(function(btn) {
											logout();
										}, function(btn) {

										});
									} else {
										growl.info("Usted ya se ha desconectado");
									}

									break;
								}
							}; 

							function logout() {
								localStorageService.remove('Logged');
								localStorageService.remove('tk');
								localStorageService.remove('ac');
								localStorageService.remove('np');
								localStorageService.remove('usuario');
								logged = false;

								$window.location.reload();
							}
							;

							$scope.restrictedAction = function() {
								if (!logged) {
									$http.post('data/protected').success(
											function(response) {
												// this piece of code will not be
												// executed
												// until user is
												// authenticated
											});
								}

							}
							$scope.isActive = function(route) {
								return route === $location.path();
							};
							localStorageService.set('userInMainMenu', false);
							
							
						})
						
				app.config(
						[
								'dialogsProvider',
								'$translateProvider',
								function(dialogsProvider, $translateProvider) {
									
									dialogsProvider.useBackdrop('static');
									dialogsProvider.useEscClose(true);
									dialogsProvider.useCopy(false);
									dialogsProvider.setSize('m'); 



									dialogsProvider.setSize('m');
									
									$translateProvider.useStaticFilesLoader({
										  prefix: 'scripts/languages/',
										  suffix: '.json'
										});
									
									$translateProvider.preferredLanguage('es');
									
									

									
								} ]);

		app.factory('UserService', [ function() {
			var sdo = {
				isLogged : false,
				username : ''
			};
			return sdo;
		} ]);

		app.controller('loginController', [
				'$scope',
				'$http',
				'UserService',
				function(scope, $http, User) {
					scope.login = function() {
						var config = { /* ... */}; // configuration object

						$http(config).success(
								function(data, status, headers, config) {
									if (data.status) {
										// succefull login
										User.isLogged = true;
										User.username = data.username;
									} else {
										User.isLogged = false;
										User.username = '';
									}
								}).error(function(data, status, headers, config) {
							User.isLogged = false;
							User.username = '';
						});
					};
				} ]);
		app.config([ 'growlProvider', function(growlProvider) {
			growlProvider.globalTimeToLive(5000);
		} ]);




		app.directive('ngReallyClick', [ '$parse', function($parse) {
			return {
				compile : function(tElement, tAttrs) {
					var fn = $parse(tAttrs.ngReallyClick);
					return function(scope, element, attrs) {
						element.on('click', function(event) {
							var message = attrs.ngReallyMessage;
							if (message && confirm(message)) {
								scope.$apply(function() {
									fn(scope, {
										$event : event
									});
								});
							}
						});
					};
				}
			};
		} ]);
		app.filter('numberFixedLen', function () {
		    return function(a,b){
		        return(1e8+a+"").slice(-b)
		    }
		});

		app.config(function($routeProvider) {

			$routeProvider.when('/', {
				templateUrl : 'views/inicio.html',
				controller : 'InicioCtrl'
			}).when('/login', {
				templateUrl : 'views/login.html',
				controller : 'LoginCtrl'
			}).when('/about', {
				templateUrl : 'views/about.html',
				controller : 'AboutCtrl'
			}).when('/ingreso', {
				templateUrl : 'views/crudMovimiento.html',
				controller : 'CrudOperationCtrl',
			}).when('/egreso', {
				templateUrl : 'views/crudMovimiento.html',
				controller : 'CrudOperationCtrl'
			}).when('/cuentas', {
				templateUrl : 'views/financialAccounts.html',
				controller : 'AccountCtrl'
			}).when('/comprobantes', {
				templateUrl : 'views/comprobantes.html',
				controller : 'ComprobanteCtrl'
			}).when('/cargarDatos', {
				templateUrl : 'views/cargarDatos.html',
				controller : 'CargarDatosCtrl'
            }).when('/listadoTransacciones', {
				templateUrl : 'views/listadoTransacciones.html',
				controller : 'ListadoTransaccionesCtrl'
            }).when('/reportes', {
				templateUrl : 'views/reportes.html',
				controller : 'ReportesCtrl'
            }).when('/newBill', {
				templateUrl : 'views/crudBill.html',
				controller : 'CrudBillCtrl'    
			}).when('/confCats', {
				templateUrl : 'views/configureCategories.html',
				controller : 'ConfigureCategoriesCtrl'    
			}).when('/pyc', {
				templateUrl : 'views/clientsAndProviders.html',
				controller : 'ClientsAndProvidersCtrl'    
			}).when('/provider', {
				templateUrl : 'views/crudProvider.html',
				controller : 'CrudProviderCtrl'    
			}).when('/importar', {
				templateUrl : 'views/importarTransacciones.html',
				controller : 'ImportCtrl'  
			}).otherwise({
				redirectTo : '/'
			});
		});
	})();
