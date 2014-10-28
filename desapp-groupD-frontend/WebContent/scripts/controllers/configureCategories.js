'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:ConfigureCategoriestrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');
app.controller('ConfigureCategoriesCtrl', function($scope, $log, $location, $http,
		$rootScope, growl,globalService,dialogs,$translate) {
	globalService.setInNewOperation()

	if ($rootScope.editingOperation) {
		var operationToEdit = $rootScope.operationToEdit;
		$scope.inputDate = operationToEdit.date;
		$scope.inputCode = operationToEdit.code;
		$scope.inputAmount = operationToEdit.amount;
		$scope.inputDescription = operationToEdit.description;
		$scope.inputPaymentType = operationToEdit.paymentType;
		$scope.inputCategory = operationToEdit.category;
		$scope.inputSubCategory = operationToEdit.subCategory;
		$scope.inputConcept = operationToEdit.concept;
		if($rootScope.outcomeOperation){
			$scope.titulo = "Editar egreso";
		}else{
			$scope.titulo = "Editar ingreso";
		}
	} else {
		if($rootScope.outcomeOperation){
			$scope.titulo = "Nuevo egreso";
		}else{
			$scope.titulo = "Nuevo ingreso";
		}
		
	};

	$scope.modoEdicion = function() {
		return $rootScope.editingOperation;
	}

	$scope.modoRegistro = function() {
		return $rootScope.editingOperation == false;
	}

	$scope.registerOperationOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Operacion registrada.");
			$location.path('/cargarDatos');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}
	
	$scope.getCategoriesOk = function(response) {
		//growl.info("Categorias obtenidas");
		$scope.categories = response;
	}
	
	$scope.selectCategory = function(category){
		if(category != null){
			if(category.subcategory == null){
				$scope.subCategories= [];
			}else {
				if (category.subcategory.length >= 1){
				$scope.subCategories = category.subcategory;
				}else{
					$scope.subCategories = null;
				}				
			}
			console.log($scope.subCategories);
		}
		
	}
	
	$scope.selectSubcategory = function(subcat){
		if(subcat != null){
			if(subcat.concepts == null)  {
				$scope.concepts = [];
			}else {
				if(subcat.concepts.length >= 1){
					$scope.concepts = subcat.concepts;
				}else{
					$scope.concepts = null;
				}
			}
		}	
			console.log($scope.concepts );
	}
	
	$scope.confirmDeleteCategory = function(name) {
		var title;
		var desc;
		$translate('DIALOG_CATEGORY_DELETE_TITLE').then(function (text) {
			 title = text;
			 $translate('DIALOG_CATEGORY_DELETE_DESC').then(function (text) {
				 desc = text;
				 var dlg = dialogs.confirm(title + name + ' ?',desc);
					dlg.result.then(function(btn) {
						deleteCategory(name);
					}, function(btn) {

					});
			    });
		    });		
		
		
	};
	
	function deleteCategory(name) {
		var data = {
			name : name,
		};
		invokeDeleteCategory($http, data, $scope.deleteOk,
				defaultHandlerOnError);
	}
	
	$scope.deleteOk = function(response) {
		$translate('DIALOG_DELETE_SUCCESS').then(function (text) {
			growl.info(text + name);
			$scope.inicializarVista();
		    });
	}

	
	$scope.inicializarVista = function() {
		
		invokeGetCategories($http, {}, $scope.getCategoriesOk,
				defaultHandlerOnError);
		$scope.subCategories=null;
		$scope.concepts=null;
		$scope.categorySelected=null;
		$scope.subcategorySelected=null;
		$scope.conceptSelected=null;

	}
	
	 $scope.dialogNewCategory = function() {
		 var extras = {
				 title: 'TITLE_NEW_CATEGORY',//codigo de traduccion del titulo
				 serviceRest: 'category',//Campos rest son obligatorios
				 resourceRest: 'add'}
	 	var dlg = dialogs.create('views/newNameEntity.html','NewNameEntityCtrl',extras,'lg');
		dlg.result.then(function(name){
			$translate('DIALOG_CATEGORY_REGISTER_SUCCESS').then(function (text) {
				growl.info(text +name);
				$scope.inicializarVista();
			    });
			
		},function(){	
		});
	 };
	 

	 
	 $scope.dialogNewSubcategory = function() {
		 
		    if($scope.categorySelected != null){
		    	var extras = {
						 title: 'TITLE_NEW_SUBCATEGORY',
						 idCategory: $scope.categorySelected.id, //valor adicional
						 serviceRest: 'subcategory',
						 resourceRest: 'save'}
		    	var dlg = dialogs.create('views/newNameEntity.html','NewNameEntityCtrl',extras,'lg');
				dlg.result.then(function(name){
					$translate('DIALOG_SUBCATEGORY_REGISTER_SUCCESS').then(function (text) {
						growl.info(text +name);
						$scope.inicializarVista();
					    });
				},function(){
				});
		    }else{
		    	$translate('DIALOG_CATEGORY_NOTSELECTED').then(function (text) {
		    		growl.error(text);
				 });
		    }
		 	
		 };
		 
		 $scope.dialogNewConcept = function() {
			 
			    if($scope.subcategorySelected != null){
			    	var extras = {
							 title: 'TITLE_NEW_CONCEPT',
							 idCategory: $scope.categorySelected.id, //valor adicional
							 idSubcategory: $scope.subcategorySelected.id,
							 serviceRest: 'concept',
							 resourceRest: 'save'}
			    	var dlg = dialogs.create('views/newNameEntity.html','NewNameEntityCtrl',extras,'lg');
					dlg.result.then(function(name){
						$translate('DIALOG_CONCEPT_REGISTER_SUCCESS').then(function (text) {
							growl.info(text +name);
							$scope.inicializarVista();
						    });
					},function(){
					});
			    }else{
			    	$translate('DIALOG_SUBCATEGORY_NOTSELECTED').then(function (text) {
			    		growl.error(text);
					 });
			    }
			 	
			 };
		 
		 $scope.inicializarVista();

});

