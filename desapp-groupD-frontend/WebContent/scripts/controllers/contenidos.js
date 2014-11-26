'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the tp-dapp-eiroa-lando
 */
angular.module('tp-dapp-eiroa-lando')
  .controller('ContenidoCtrl', function ($scope) {
	$scope.elements = ['op1','op2','op3','op4'];
	$scope.empresas = [
	                    { empresa: 'Fallabella', value: 1 },
	                    { empresa: 'Cencosud', value: 2 },
	                    { empresa: 'Wal Mart', value: 3 }
	                  ];
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
