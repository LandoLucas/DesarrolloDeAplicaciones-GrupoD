'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');
//var app = angular.module("nvd3TestApp", ['nvd3ChartDirectives']);

app.controller('ReportesCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate,restServices) {

	$scope.exampleData = [
	   { key: "One", y: 5 },
       { key: "Two", y: 2 },
	   { key: "Three", y: 9 },
	   { key: "Four", y: 7 },
	   { key: "Five", y: 4 },
	   { key: "Six", y: 3 },
	   { key: "Seven", y: 9 }
	];
	
	
	
	
});
