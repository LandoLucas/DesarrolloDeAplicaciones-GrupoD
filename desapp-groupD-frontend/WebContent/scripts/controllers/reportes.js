'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');

app.controller('ReportesCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate,restServices) {

	var data = google.visualization.arrayToDataTable([
	    ['Year', 'Sales', 'Expenses'],
	    ['2004', 1000, 400],
	    ['2005', 1170, 460],
	    ['2006', 660, 1120],
	    ['2007', 1030, 540]
	    ]);
	
	var options = {
	    title: 'Company Performance'
	};
	var chart = new google.visualization.PieChart(document.getElementById('chartdiv'));
	chart.draw(data, options);
	
});
