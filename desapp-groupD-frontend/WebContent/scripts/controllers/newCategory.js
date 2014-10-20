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

	   $scope.user = {name : ''};

		//-- Methods --//

		$scope.cancel = function(){
			$modalInstance.dismiss('Canceled');
		}; // end cancel

		$scope.save = function(){
			$modalInstance.close($scope.user.name);
		}; // end save
		
		$scope.registerCategoryOk = function(response) {
//			if (codigoOk(response)) {
				growl.info("Categoria registrada.");
				$modalInstance.close($scope.user.name);
				$location.path('/cargarDatos');
				
//			} else {
//				var descripcion = response['desc'];
//				growl.error(descripcion);
//			}
		}
		
		 $scope.saveNewCategory = function() {
			 var data = {name: $scope.user.name};
				if(validate()){
					invokeNewCategory($http, data, $scope.registerCategoryOk,
							defaultHandlerOnError);
				}else{
					getErrorMessage();
				}
		 };
		 
			function validate(){
				if ($scope.user.name == null || $scope.user.name== ""){
					return false;
				}
				return true;
			}
			
			function getErrorMessage(){

				if ($scope.name == null || $scope.name== ""){
					growl.error("Nombre requerido");
				}

			}
//		$scope.hitEnter = function(evt){
//			if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.user.name,null) || angular.equals($scope.user.name,'')))
//				$scope.save();
//		};
  });
