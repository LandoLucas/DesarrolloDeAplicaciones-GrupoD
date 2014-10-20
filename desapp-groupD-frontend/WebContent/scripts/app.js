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
				'ngResource', 'ngRoute', 'ngSanitize', 'ngTouch',
				'angularFileUpload', 'ngTable', 'http-auth-interceptor',
				'loginModule', 'contentModule', 'content-mocksModule',
				'LocalStorageModule', 'ngRoute', 'checklist-model',
				'angular-growl', 'ui.bootstrap', 'dialogs.main',
				'pascalprecht.translate','tp-dapp-eiroa-lando.services' ]);




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
                            
							$scope.inMainMenu = function(){
								return localStorageService.get('userInMainMenu');
							}; 

							$scope.setInNewOutcome = function(){
								$rootScope.editingOperation = false;
								$rootScope.outcomeOperation =true;
							};
							
							$scope.setInNewIncome = function(){
								$rootScope.editingOperation = false;
								$rootScope.outcomeOperation =false;
							};

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

//									$translateProvider.useStaticFilesLoader({
//										  prefix: 'scripts/languages/',
//										  suffix: '.json'
//										});

									dialogsProvider.setSize('m');
									
									$translateProvider.determinePreferredLanguage();
									$translateProvider.preferredLanguage('es');
									

									$translateProvider
											.translations(
													'es',
													{
														INDEX_APP_NAME: "Sistema de gestión",
														INDEX_SELECT_LANGUAGE: "Seleccione el lenguaje",
														INDEX_SELECT_SPANISH: "Español",
														INDEX_SELECT_ENGLISH:"Inglés",
														INDEX_GO_BACK: "Volver Atrás",
														INDEX_SECTIONS: "Secciones",
														INDEX_ACCOUNTS: "Cuentas",
														INDEX_LOAD_DATA: "Cargar Datos",
														INDEX_EXPENSES_PROJECTIONS: "Proyecciones",
														INDEX_CONSOL_ACCOUNT: "Consolidar cuentas",
														INDEX_HOME: "Inicio",
														INDEX_LOGIN: "Login",
														INDEX_LOG_OFF: "Desconectarse",
														INDEX_LOAD_OUTCOME_INCOME: "Cargar datos",
														INDEX_VIEW_EXPENSES_PROJECTIONS: "Gastos y proyecciones",
														INDEX_CATEGORY_CONF: "Configurar categorias",
														INDEX_LOAD_INCOME: "Cargar ingreso",
														INDEX_LOAD_OUTCOME: "Cargar egreso",
														INDEX_LOAD_RECEIPT: "Cargar comprobante",
														INDEX_BUYING_RECEIPT: "Comprobante de compra",
														INDEX_SELLING_RECEIPT: "Comprobante de venta",
														INDEX_TYPE_A: "Tipo A",
														INDEX_TYPE_B: "Tipo B",
														INDEX_TYPE_C: "Tipo C",
														INDEX_TYPE_X: "Tipo X",
														INDEX_USERS: "Usuarios",
														SELECT_OPTION: "Seleccione una opción",
														
														WELCOME: "Bienvenido",
														WELCOME_INFO: "Utilice el menu de navegación lateral o la barra de navegación superior para comenzar a utilizar el sistema",
														
														LOAD_DATA_SECTION:"Sección cargar datos",
														LOAD_DATA_SELECT:"Seleccione que tipo de operación desea ingresar",
														
														RECEIPT_SECTION: "Sección comprobantes",
														RECEIPT_SELECT: "Seleccione el tipo de comprobante a ingresar",
														
														
														
														TITLE_NEW_INCOME: "Nuevo ingreso",
														TITLE_NEW_OUTCOME: "Nuevo egreso",
														TITLE_ACCOUNTS:"Cuentas registradas",
														
														FORM_DATE: "Fecha",
														FORM_AMOUNT: "Monto",
														FORM_SELECT_CATEGORY: "Seleccionar categoría",
														FORM_NEW_CATEGORY: "Nueva Categoría",
														FORM_SELECT_SUBCATEGORY: "Seleccionar subcategoría",
														FORM_NEW_SUBCATEGORY: "Nueva Subcategoría",
														FORM_SELECT_PAYMENT_TYPE: "Seleccione el tipo de pago",
														FORM_CONCEPT: "Concepto",
														FORM_CONFIRM:"Confirmar",
														FORM_UPDATE: "Editar",
														FORM_CANCEL: "Cancelar",
														REGISTER_NEW_ACCOUNT: "Registrar nueva cuenta",
														FORM_NAME: "Nombre",
														IS_BANK_ACCOUNT: "Es cuenta bancaria",
														FORM_EDIT: "Editar",
														FORM_DELETE: "Borrar",
														FORM_ACTIONS: "Acciones",
														FORM_SEARCH: "Buscar",
														
														DIALOGS_ERROR : "Error",
														DIALOGS_ERROR_MSG : "Se ha producido un error desconocido.",
														DIALOGS_CLOSE : "Cerrar",
														DIALOGS_PLEASE_WAIT : "Espere por favor",
														DIALOGS_PLEASE_WAIT_ELIPS : "Espere por favor...",
														DIALOGS_PLEASE_WAIT_MSG : "Esperando en la operacion para completar.",
														DIALOGS_PERCENT_COMPLETE : "% Completado",
														DIALOGS_NOTIFICATION : "Notificacion",
														DIALOGS_NOTIFICATION_MSG : "Notificación de aplicacion Desconocido.",
														DIALOGS_CONFIRMATION : "Confirmación",
														DIALOGS_CONFIRMATION_MSG : "Se requiere confirmación.",
														DIALOGS_OK : "Aceptar",
														DIALOGS_YES : "Aceptar",
														DIALOGS_NO : "Cancelar",
														HEADLINE : "IMPLEMENTADO DESDE APP.JS"
													}) 
													
									.translations(
											'en',
											{
												INDEX_APP_NAME: "Management System",
												INDEX_SELECT_LANGUAGE: "Select language",
												INDEX_SELECT_SPANISH: "Spanish",
												INDEX_SELECT_ENGLISH:"English",
											    INDEX_GO_BACK: "Go back",
												INDEX_SECTIONS: "Sections",
												INDEX_ACCOUNTS: "Accounts",
												INDEX_LOAD_DATA: "Load Data",
												INDEX_EXPENSES_PROJECTIONS: "Expenses and projections",
												INDEX_CONSOL_ACCOUNT: "Consolidate accounts",
												INDEX_HOME: "Home",
												INDEX_LOGIN: "Login",
												INDEX_LOG_OFF: "Logout",
												INDEX_LOAD_OUTCOME_INCOME: "Load operations",
												INDEX_VIEW_EXPENSES_PROJECTIONS: "Expenses and projections",
												INDEX_CATEGORY_CONF: "Configure categories",
												INDEX_LOAD_INCOME: "Load income",
												INDEX_LOAD_OUTCOME: "Load outcome",
												INDEX_LOAD_RECEIPT: "Load bill",
												INDEX_BUYING_RECEIPT: "Buying bill",
												INDEX_SELLING_RECEIPT: "Selling bill",
												INDEX_TYPE_A: "Type A",
												INDEX_TYPE_B: "Type B",
												INDEX_TYPE_C: "Type C",
												INDEX_TYPE_X: "Type X",
												INDEX_USERS: "Users",
												SELECT_OPTION: "Select an option",
												
												WELCOME: "Welcome",
												WELCOME_INFO: " Use the left side menu or the top navigation bar to start using the system capabilities",
												
												LOAD_DATA_SECTION:"Load data section",
												LOAD_DATA_SELECT:"Select the type of operation to load",
												
												RECEIPT_SECTION: "Bills section",
												RECEIPT_SELECT: "Select the type of bill to load",
												
												
												TITLE_NEW_INCOME: "New income",
												TITLE_NEW_OUTCOME: "New outcome",
												TITLE_ACCOUNTS:"Registered accounts",
												
												FORM_DATE: "Date",
												FORM_AMOUNT: "Amount",
												FORM_SELECT_CATEGORY: "Select category",
												FORM_NEW_CATEGORY: "New category",
												FORM_SELECT_SUBCATEGORY: "Select subcategory",
												FORM_NEW_SUBCATEGORY: "New subcategory",
												FORM_SELECT_PAYMENT_TYPE: "Select payment type",
												FORM_CONCEPT: "Concept",
												FORM_CONFIRM:"Confirm",
												FORM_UPDATE: "Edit",
												FORM_CANCEL: "Cancel",
												REGISTER_NEW_ACCOUNT: "Register new account",
												FORM_NAME: "Name",
												IS_BANK_ACCOUNT: "Is bank account",
												FORM_EDIT: "Edit",
												FORM_DELETE: "Delete",
												FORM_ACTIONS: "Actions",
												FORM_SEARCH: "Search",
												
												DIALOGS_ERROR : "Error",
												DIALOGS_ERROR_MSG : "An unknown error has occurred.",
												DIALOGS_CLOSE : "Close",
												DIALOGS_PLEASE_WAIT : "Please wait",
												DIALOGS_PLEASE_WAIT_ELIPS : "Please wait...",
												DIALOGS_PLEASE_WAIT_MSG : "Waiting in the operation to complete.",
												DIALOGS_PERCENT_COMPLETE : "% Completed",
												DIALOGS_NOTIFICATION : "Notification",
												DIALOGS_NOTIFICATION_MSG : "Unknown application notification.",
												DIALOGS_CONFIRMATION : "Confirmation",
												DIALOGS_CONFIRMATION_MSG : "Confirmation required.",
												DIALOGS_OK : "Accept",
												DIALOGS_YES : "Accept",
												DIALOGS_NO : "Cancel",
												HEADLINE : "IMPLEMENTED FROM APP.JS"
											}); 
									
//									$translateProvider.determinePreferredLanguage();
									$translateProvider.preferredLanguage('en');
									
									

									
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
            }).when('/newBill', {
				templateUrl : 'views/crudBill.html',
				controller : 'CrudBillCtrl'    
			}).otherwise({
				redirectTo : '/'
			});
		});
	})();
