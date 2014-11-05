'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:ConfigureCategoriestrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');
app.controller('ConfigureCategoriesCtrl', function($scope, $log, $location, $http,
		$rootScope, growl,globalService,dialogs,$translate) {
	//globalService.setInConfigureCategories()

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
	
	$scope.confirmDeleteSubcategory = function(name) {
		var title;
		var desc;
		$translate('DIALOG_SUBCATEGORY_DELETE_TITLE').then(function (text) {
			 title = text;
			 $translate('DIALOG_SUBCATEGORY_DELETE_DESC').then(function (text) {
				 desc = text;
				 var dlg = dialogs.confirm(title + name + ' ?',desc);
					dlg.result.then(function(btn) {
						deleteSubcategory(name,$scope.categorySelected.id);
					}, function(btn) {

					});
			    });
		    });				
	};
	
	$scope.confirmDeleteConcept = function(name) {
		var title;
		var desc;
		$translate('DIALOG_CONCEPT_DELETE_TITLE').then(function (text) {
			 title = text;
			 var dlg = dialogs.confirm(title + name + ' ?','');
				dlg.result.then(function(btn) {
					deleteConcept(name,$scope.categorySelected.id,$scope.subcategorySelected.id);
				}, function(btn) {

				});
		    });				
	};
	
	function deleteCategory(name) {
		var data = {
			name : name
		};
		invokeDeleteCategory($http, data, $scope.deleteOk,
				defaultHandlerOnError);
	}
	
	function deleteSubcategory(name,idCat) {
		var data = {
			name : name,
			idCategory: idCat
		};
		invokeDeleteSubcategory($http, data, $scope.deleteOk,
				defaultHandlerOnError);
	}
	
	function deleteConcept(name,idCat,idSub) {
		var data = {
			name : name,
			idCategory: idCat,
			idSubcategory: idSub
		};
		invokeDeleteConcept($http, data, $scope.deleteOk,
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
	
	 $scope.dialog = function(data, messageSuccess, extraMessage) {
		 	var dlg = dialogs.create('views/newNameEntity.html','NewNameEntityCtrl',data,'lg');
			dlg.result.then(function(name){
				$translate(messageSuccess).then(function (text) {
					growl.info(text + name + extraMessage);
					$scope.inicializarVista();
				    });
				
			});
		 };
	
	 $scope.dialogNewCategory = function() {
		 var extras = {
				 title: 'TITLE_NEW_CATEGORY',//codigo de traduccion del titulo
				 serviceRest: 'category',//Campos rest son obligatorios
				 resourceRest: 'save'}
	 	$scope.dialog(extras,'DIALOG_CATEGORY_REGISTER_SUCCESS','');
	 };
	 
	 $scope.dialogNewSubcategory = function() {
		    if($scope.categorySelected != null){
		    	var extras = {
						 title: 'TITLE_NEW_SUBCATEGORY',
						 idCategory: $scope.categorySelected.id, //valor adicional
						 serviceRest: 'subcategory',
						 resourceRest: 'save'}
		    	$scope.dialog(extras,'DIALOG_SUBCATEGORY_REGISTER_SUCCESS','');
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
			    	$scope.dialog(extras,'DIALOG_CONCEPT_REGISTER_SUCCESS','');
			    }else{
			    	$translate('DIALOG_SUBCATEGORY_NOTSELECTED').then(function (text) {
			    		growl.error(text);
					 });
			    }
			 };
	 
	 $scope.dialogEditCategory = function() {
		 var extras = {
				 title: 'DIALOG_CATEGORY_EDIT_TITLE',
				 idCategory: $scope.categorySelected.id,
				 serviceRest: 'category',
				 resourceRest: 'update',
				 previousName: 	$scope.categorySelected.categoryName }
	 	$scope.dialog(extras,'DIALOG_EDIT_SUCCESS', ' <- '+ $scope.categorySelected.categoryName)
	 };
	 
	 
	 $scope.dialogEditSubcategory = function() {
		 var extras = {
				 title: 'DIALOG_SUBCATEGORY_EDIT_TITLE',
				 idSubcategory: $scope.subcategorySelected.id,
				 serviceRest: 'subcategory',
				 resourceRest: 'update',
				 previousName: 	$scope.subcategorySelected.subcategoryName }
	 	$scope.dialog(extras,'DIALOG_EDIT_SUCCESS',' <- '+$scope.subcategorySelected.subcategoryName)
	 };
	 
	 $scope.dialogEditConcept = function() {
		 var extras = {
				 title: 'DIALOG_CONCEPT_EDIT_TITLE',
				 idConcept: $scope.conceptSelected.id,
				 serviceRest: 'concept',
				 resourceRest: 'update',
				 previousName: 	$scope.conceptSelected.conceptName }
	 	$scope.dialog(extras,'DIALOG_EDIT_SUCCESS',' <- '+$scope.conceptSelected.conceptName)
	 };

		 $scope.inicializarVista();
});

