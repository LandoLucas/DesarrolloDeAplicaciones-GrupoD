'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');

app.controller('ReportesCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate,restServices) {

	$scope.distribucionOK = function(response) {
		
		drawChart();
		
		function drawChart() {
			$translate('CATEGORIES_DISTRIBUTION_TITLE').then(function(text) {
				
				$scope.options = {
				    title: text ,
				    'width':1000,
					'height':800
			    };
				
				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Category');
				data.addColumn('number', 'Amount');
				
				var rows = []
				for(var i=0;i<(Object.keys(response).length);i++){
					var key = Object.keys(response)[i];
					var value = response[Object.keys(response)[i]];
					rows.push([key, Math.round(value)]);
				}
				data.addRows(rows);
				var chart = new google.visualization.PieChart(document.getElementById('chartdiv'));
				chart.draw(data, $scope.options);
			});
		};
		
	};
	
	function getAllOperations(){
		restServices.invokeDistribucionDeGastos($http, {}, $scope.distribucionOK , restServices.defaultHandlerOnError);
	};	
	getAllOperations();
});
