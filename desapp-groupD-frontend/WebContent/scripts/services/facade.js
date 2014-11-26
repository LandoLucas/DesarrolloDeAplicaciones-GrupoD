(function() {
	'use strict';
	var app = angular.module('facadeModule',
			[ 'http-auth-interceptor', 'LocalStorageModule' ,'angular-growl','ngRoute','pascalprecht.translate']);
	app.config(['$httpProvider', function($httpProvider) {
	    delete $httpProvider.defaults.headers.common["X-Requested-With"]
	}]);
	app.provider('restServices', function () {
		
		this.$get = [
		             '$rootScope',
		             '$filter',
		             '$translate',
		             'growl',
		             function ($rootScope, $filter,$translate,growl) {
		               
		         		function invokeRestService(httpService, header, data, modulo, servicio,
		        				handlerOnSuccess, handlerOnError) {
		        			var respuesta = [];

		        			httpService(
		        					{
		        						method : 'POST',
		        						url : 'http://' + restHost + '/' + restContext + '/rest/'
		        								+ modulo + '/' + servicio,
		        						data : data,
		        						headers: header,
		        						transformRequest : function(obj) {
		        							var str = [];
		        							for ( var p in obj)
		        								str.push(encodeURIComponent(p) + "="
		        										+ encodeURIComponent(obj[p]));
		        							return str.join("&");
		        						}
		        					}).success(function(response) {
		        				handlerOnSuccess(response);

		        			}).error(function(response) {
		        				console.log(response)
		        				handlerOnError(response);
		        			});
		        		}
		         		
		         		function invokeRestService_Json(httpService, header, data, modulo, servicio,
		        				handlerOnSuccess, handlerOnError) {
		        			var respuesta = [];

		        			httpService(
		        					{
		        						method : 'POST',
		        						url : 'http://' + restHost + '/' + restContext + '/rest/'
		        								+ modulo + '/' + servicio,
		        						data : data,
		        						headers: header
		        					}).success(function(response) {
		        				handlerOnSuccess(response);

		        			}).error(function(response) {
		        				console.log(response)
		        				handlerOnError(response);
		        			});
		        		}

		        		function invokeGetRestService(httpService, header, data, modulo, servicio,
		        				handlerOnSuccess, handlerOnError) {
		        			var respuesta = [];

		        			httpService(
		        					{
		        						method : 'GET',
		        						url : 'http://' + restHost + '/' + restContext + '/rest/'
		        								+ modulo + '/' + servicio,
		        						data : data,
		        						headers : header
		        						
		        					}).success(function(response) {
		        				handlerOnSuccess(response);

		        			}).error(function(response) {
		        				console.log(response)
		        				handlerOnError(response);
		        			});
		        		}

		        		function defaultHandlerOnError(response) {
		        			$translate('DEFAULT_ERROR').then(function (text) {
		        				growl.error(text);
		        			});
		        			console.log(response);
		        		}

		        		function defaultHeader() {
		        			return {
		        				'Content-Type' : 'application/x-www-form-urlencoded; charset=utf-8',
		        				'Accept-Charset':'charset=utf-8',
		        			};
		        		}

		        		function jsonHeader() {
		        			return {
		        				'Content-Type' : 'application/json'
		        			};
		        		}
		        		function mergeJSONs(obj1,obj2){ // Our merge function
		        		    var result = {}; // return result
		        		    for(var i in obj1){      // for every property in obj1 
		        		        if((i in obj2) && (typeof obj1[i] === "object") && (i !== null)){
		        		            result[i] = merge(obj1[i],obj2[i]); // if it's an object, merge   
		        		        }else{
		        		           result[i] = obj1[i]; // add it to result
		        		        }
		        		    }
		        		    for(i in obj2){ // add the remaining properties from object 2
		        		        if(i in result){ //conflict
		        		            continue;
		        		        }
		        		        result[i] = obj2[i];
		        		    }
		        		    return result;
		        		}

		        		function autenticar(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'seguridad', 'login',
		        					handlerOnSuccess, handlerOnError);

		        		}
		        		
		        		//==Rest Account==\\
		        		function invokeGetAccounts(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeGetRestService(cnxHttp, header, data, 'account', 'all',
		        					handlerOnSuccess, handlerOnError);
		        		}
		        		
		        		function invokeRegisterAccount(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'account', 'new',
		        					handlerOnSuccess, handlerOnError);
		        		}		
		        		
		        		//==Rest PaymentTypes==\\

		        		//==Rest Operations==\\


		        		function invokeRegisterOperation(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService_Json(cnxHttp, header, data, 'operation', 'new',
		        					handlerOnSuccess, handlerOnError);
		        		}
		        		
		        		function invokeGetAllOperations(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeGetRestService(cnxHttp, header, data, 'operation', 'all',
		        					handlerOnSuccess, handlerOnError);
		        		}
		        		
		        		
		        		//==Rest Bills==\\
		        		function invokeRegisterBill(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'bill', 'new',
		        					handlerOnSuccess, handlerOnError);
		        		}


		        		// ===Rest categorias ===
		        		function invokeGetCategories(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeGetRestService(cnxHttp, header, data, 'category', 'all',
		        					handlerOnSuccess, handlerOnError);
		        		}


		        		function invokeNewCategory(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'category', 'save',
		        					handlerOnSuccess, handlerOnError);
		        		}

		        		function invokeDeleteCategory(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'category', 'delete',
		        					handlerOnSuccess, handlerOnError);
		        		}
		        		

		        		//===Rest subcategorias===
		        		function invokeNewSubcategory(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'subcategory', 'save',
		        					handlerOnSuccess, handlerOnError);
		        		}

		        		function invokeDeleteSubcategory(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'subcategory', 'delete',
		        					handlerOnSuccess, handlerOnError);
		        		}

		        		//==Rest Concept==\\
		        		function invokeDeleteConcept(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'concept', 'delete',
		        					handlerOnSuccess, handlerOnError);
		        		}




		        		// Usuarios
		        		function invokeRegistrarUsuario(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'configuracion',
		        					'registrarUsuario', handlerOnSuccess, handlerOnError);
		        		}

		        		function invokeBuscarUsuario(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'configuracion', 'buscarUsuario',
		        					handlerOnSuccess, handlerOnError);
		        		}

		        		function invokeActualizarUsuario(cnxHttp, data, handlerOnSuccess,
		        				handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, data, 'configuracion',
		        					'actualizarUsuario', handlerOnSuccess, handlerOnError);
		        		}

		        		// Buscar Usuarios
		        		function invokeBuscarUsuarios(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, filtro, 'configuracion',
		        					'buscarUsuarios', handlerOnSuccess, handlerOnError);
		        		}

		        		function invokeBorrarUsuario(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			invokeRestService(cnxHttp, header, filtro, 'configuracion',
		        					'borrarUsuario', handlerOnSuccess, handlerOnError);
		        		}
		        		
		        		function invokeConsolidateAccounts(cnxHttp, data, handlerOnSuccess, handlerOnError) {
		        			var header = defaultHeader();
		        			
		        			invokeGetRestService(cnxHttp, header, data, 'consolidate', 'consolidate', handlerOnSuccess, handlerOnError);
		        		}
		            	 
		               return {
		                 invokeGetCategories: invokeGetCategories,
		                 invokeGetAllOperations : invokeGetAllOperations,
		                 defaultHandlerOnError: defaultHandlerOnError,
		                 invokeRestService: invokeRestService,
		                 invokeGetRestService: invokeGetRestService,
		                 invokeConsolidateAccounts : invokeConsolidateAccounts, 
		                 defaultHeader: defaultHeader,
		                 jsonHeader: jsonHeader,
		                 mergeJSONs: mergeJSONs,
		                 autenticar: autenticar,
		                 invokeRegisterOperation: invokeRegisterOperation,
		                 invokeNewCategory: invokeNewCategory,
		                 invokeDeleteCategory: invokeDeleteCategory,
		                 invokeNewSubcategory: invokeNewSubcategory,
		                 invokeDeleteSubcategory: invokeDeleteSubcategory,
		                 invokeDeleteConcept: invokeDeleteConcept,
		                 invokeRegistrarUsuario: invokeRegistrarUsuario,
		                 invokeRegisterBill: invokeRegisterBill
		                 
		               };
		             }
		           ];
	})
	
	
})();