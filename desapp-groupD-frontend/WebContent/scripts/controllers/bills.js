'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the tp-dapp-eiroa-lando
 */
angular.module('tp-dapp-eiroa-lando')
  .controller('ComprobanteCtrl', function ($http, $location, $scope, ngTableParams,
			$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate) {
    
	  $scope.estado = function(){
		  if(globalService.UserScope.inBillTypes){
			  if (globalService.inSellingBillType()){
		    		return 'Usted está ingresando un comprobante de compra';
		    	}else{
		    		return 'Usted está ingresando un comprobante de venta';
		    	}		  
	      }
    
	  
	  }        
	
  });
