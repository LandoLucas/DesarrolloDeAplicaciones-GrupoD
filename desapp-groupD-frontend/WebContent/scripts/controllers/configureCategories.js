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
			if(subcat.concept == null)  {
				$scope.concepts = [];
			}else {
				if(subcat.concept.length >= 1){
					$scope.concepts = subcat.concept;
				}else{
					$scope.concepts = [subcat.concept];
				}
			}
		}	
			console.log($scope.concepts );
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

	$scope.updateOperationOk = function(response) {
		if (codigoOk(response)) {
			growl.info("Operacion actualizada.");
			$location.path('/cargarDatos');
		} else {
			var descripcion = response['desc'];
			growl.error(descripcion);
		}
	}

	$scope.populateParams = function() {
		var data = {
			date : $scope.inputDate,
			amount: $scope.inputAmount,
			category : $scope.categorySelected.categoryName,
			subCategory : $scope.subcategorySelected.subcategoryName,
			concept: $scope.conceptSelected.conceptName,
			paymentType : $scope.inputPaymentType,
			shift : $scope.inputShift
		};
		return data;
	}
    
    $scope.populateParamsTest = function(){
        var data ={
            amount: $scope.inputAmount
        };
        
        return data;
    }

	$scope.registerOperation = function() {
		var data = $scope.populateParams();
		if(validate()){
			invokeRegisterOperation($http, data, $scope.registerOperationOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		}
		
	}
    

	
	function validate(){
		if ($scope.inputAmount == null || $scope.inputAmount == ""){
			return false;
		}
		return true;
	}
	
	function getErrorMessage(){
		if ($scope.inputAmount == null || $scope.inputAmount== ""){
			$translate('FORM_ERROR_AMOUNT_REQUIRED').then(function (text) {
				growl.error(text);
			});
		}
	}

	$scope.updateOperation= function() {
		if(validate()){
			var data = $scope.populateParams();
			data.id = $rootScope.operationToEdit.id;
			invokeUpdateOperation($http, data, $scope.updateOperationOk,
					defaultHandlerOnError);
		}else{
			getErrorMessage();
		
		}
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
		 var extras = {
				 title: 'TITLE_NEW_SUBCATEGORY',
				 idCategory: $scope.categorySelected.id, //valor adicional
				 serviceRest: 'subcategory',
				 resourceRest: 'save'}
		    if($scope.categorySelected != null){
		    	var dlg = dialogs.create('views/newNameEntity.html','NewNameEntityCtrl',extras,'lg');
				dlg.result.then(function(name){
					$translate('DIALOG_SUBCATEGORY_REGISTER_SUCCESS').then(function (text) {
						growl.info(text +name);
						$scope.inicializarVista();
					    });
				},function(){
				});
		    }else{
		    	$translate('DIALOG_SUBCATEGORY_CATEGORY_NOTSELECTED').then(function (text) {
		    		growl.error(text);
				 });
		    }
		 	
		 };
		 
		 $scope.inicializarVista();

});

