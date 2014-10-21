'use strict';

/**
 * @ngdoc function
 * @name tpDesapMockupsApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the tpDesapMockupsApp
 */
angular.module('tp-dapp-eiroa-lando')
  .controller('NewSubcategoryCtrl', function ($scope,$log,$http,$modalInstance,data,
		  growl,globalService,dialogs,$location,$translate) {
	//-- Variables --//
	    console.log(data);
		$scope.user = {subcategoryName : '',idCategory: data.idCategory};

		//-- Methods --//

		$scope.cancel = function(){
			$modalInstance.dismiss('Canceled');
		}; // end cancel

		$scope.save = function(){
			$modalInstance.close($scope.user.subcategoryName);
		}; // end save
		
		$scope.registerSubcategoryOk = function(response) {
			//growl.info("Subcategoria registrada.");
			$modalInstance.close($scope.user.subcategoryName);
			$location.path('/cargarDatos');
			
	}
	
	 $scope.saveNewSubcategory = function() {
		 var data = {name: $scope.user.subcategoryName, idCategory: $scope.user.idCategory};
			if(validate()){
				invokeNewSubcategory($http, data, $scope.registerSubcategoryOk,
						defaultHandlerOnError);
			}else{
				getErrorMessage();
			}
	 };
	 
		function validate(){
			if ($scope.user.subcategoryName == null || $scope.user.subcategoryName== ""){
				return false;
			}
			return true;
		}
		
		function getErrorMessage(){

			if ($scope.subcategoryName == null || $scope.subcategoryName== ""){
				$translate('FORM_ERROR_NAME_REQUIRED').then(function (text) {
					growl.error(text );
				    });
			}

		}

//		$scope.hitEnter = function(evt){
//			if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.user.name,null) || angular.equals($scope.user.name,'')))
//				$scope.save();
//		};
  });
