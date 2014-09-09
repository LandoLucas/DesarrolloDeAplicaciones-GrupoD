'use strict';

/**
 * @ngdoc function
 * @name tpDesapMockupsApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the tpDesapMockupsApp
 */
angular.module('tp-dapp-eiroa-lando')
  .controller('NewSubcategoryCtrl', function ($scope,$modalInstance,data) {
	//-- Variables --//

		$scope.user = {name : ''};

		//-- Methods --//

		$scope.cancel = function(){
			$modalInstance.dismiss('Canceled');
		}; // end cancel

		$scope.save = function(){
			$modalInstance.close($scope.user.name);
		}; // end save

//		$scope.hitEnter = function(evt){
//			if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.user.name,null) || angular.equals($scope.user.name,'')))
//				$scope.save();
//		};
  });
