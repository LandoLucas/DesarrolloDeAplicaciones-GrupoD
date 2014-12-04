'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');

app.controller('ReportesCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate,restServices) {

	$scope.data = [
	    {label: "one", value: 12.2, color: "red"}, 
	    {label: "two", value: 45, color: "#00ff00"},
	    {label: "three", value: 10, color: "rgb(0, 0, 255)"}
	];
	
	$scope.options = {thickness: 500};
	
});
