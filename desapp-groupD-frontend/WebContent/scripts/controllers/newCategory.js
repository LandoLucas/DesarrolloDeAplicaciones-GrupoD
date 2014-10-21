'use strict';

/**
 * @ngdoc function
 * @name tpDesapMockupsApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the tpDesapMockupsApp
 */
angular.module('tp-dapp-eiroa-lando')
  .controller('NewCategoryCtrl', function ($scope,$log,$http,$modalInstance,data,
		  growl,globalService,dialogs,$location,$translate) {
	//-- Variables --//

	   $scope.user = {categoryName : ''};

		//-- Methods --//

		$scope.cancel = function(){
			$modalInstance.dismiss('Canceled');
		}; // end cancel

		$scope.save = function(){
			$modalInstance.close($scope.user.categoryName);
		}; // end save
		
		$scope.registerCategoryOk = function(response) {
				//growl.info("Categoria registrada.");
				$modalInstance.close($scope.user.categoryName);
				$location.path('/cargarDatos');
				
		}
		
		 $scope.saveNewCategory = function() {
			 var data = {name: $scope.user.categoryName};
				if(validate()){
					invokeNewCategory($http, data, $scope.registerCategoryOk,
							defaultHandlerOnError);
				}else{
					getErrorMessage();
				}
		 };
		 
			function validate(){
				if ($scope.user.categoryName == null || $scope.user.categoryName== ""){
					return false;
				}
				return true;
			}
			
			function getErrorMessage(){

				if ($scope.categoryName == null || $scope.categoryName== ""){
					$translate('FORM_ERROR_NAME_REQUIRED').then(function (text) {
						growl.error(text);
					    });
				}

			}
//		$scope.hitEnter = function(evt){
//			if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.user.name,null) || angular.equals($scope.user.name,'')))
//				$scope.save();
//		};
  });
