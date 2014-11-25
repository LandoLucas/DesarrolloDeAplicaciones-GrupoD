'use strict';

/**
 * @ngdoc function
 * @name tpDesapMockupsApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the tpDesapMockupsApp
 */
angular.module('tp-dapp-eiroa-lando')
  .controller('NewNameEntityCtrl', function ($scope,$log,$http,$modalInstance,data,
		  growl,globalService,dialogs,$location,$translate,restServices) {
	//-- Variables --//
	   $scope.user = {name : ''};
	   $scope.additionalData= data;
	   $scope.user.name = $scope.additionalData.previousName;
	   console.log(data);
	   
	   //Traducimos el titulo
	   $translate($scope.additionalData.title).then(function (text) {
			$scope.title = text;;
		    });

	   //-- Methods --//

		$scope.cancel = function(){
			$modalInstance.dismiss('Canceled');
		}; // end cancel

		
		$scope.registerOk = function(response) {
				$modalInstance.close($scope.user.name);			
		}
		
		
		$scope.hitEnter = function(evt){
		    if(angular.equals(evt.keyCode,13) && 
		    		!(angular.equals($scope.user.name,null) || 
		    				angular.equals($scope.user.name,'')))
						$scope.save();
		  };
		  
		  function defHeader() {
  			return {
  				'Content-Type' : 'application/x-www-form-urlencoded;'
  			};
  		}

		 $scope.save = function(){
			 data = restServices.mergeJSONs({name: $scope.user.name}, $scope.additionalData);
			 delete data.serviceRest;
			 delete data.resourceRest;
			 var header = defHeader();
			 console.log("Enviando: ",data);
			 if(validate()){
				 restServices.invokeRestService($http, header, data, $scope.additionalData.serviceRest, 
						 $scope.additionalData.resourceRest,$scope.registerOk, restServices.defaultHandlerOnError);
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
					$translate('FORM_ERROR_NAME_REQUIRED').then(function (text) {
						growl.error(text);
					    });
				}

			}
			
			
			
  });
