'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
var app = angular.module('tp-dapp-eiroa-lando');

app.controller('ReportesCtrl', function($http, $location, $scope, ngTableParams,
		$filter, $window, $route, $rootScope, growl, dialogs,globalService,$translate,restServices) {

	$scope.drawChart = function drawChart(nameOfDiv , response) {
		$translate('SHIFT_DISTRIBUTION_TITLE').then(function(text) {
			
			$scope.options = {
			    'width':1000,
				'height':800
		    };
			
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Shift');
			data.addColumn('number', 'Amount');
			
			var rows = [];
			for(var i=0;i<(Object.keys(response).length);i++){
				var key = Object.keys(response)[i];
				var value = response[Object.keys(response)[i]];
				rows.push([key, Math.round(value)]);
			}
			data.addRows(rows);
			var chart = new google.visualization.PieChart(document.getElementById(nameOfDiv));
			chart.draw(data, $scope.options);
		});
	};
	
	$scope.distribucionTurnoNocheOK = function(response) {
		$scope.operaciones = response;
		$scope.drawChart("shiftChartNoche", response);
	};
	
	$scope.distribucionTurnoMananaOK = function(response) {
		$scope.operaciones = response;
		$scope.drawChart("shiftChartManana", response);
	};
	
	$scope.distribucionTurnoTardeOK = function(response) {
		$scope.operaciones = response;
		$scope.drawChart("shiftChartTarde", response);
	};
	
	$scope.distribucionOK = function(response) {
		$scope.drawChart("categoriesChart", response);
	};
	
	function invokeDistribucionDeGastosPorCategoria(){
		restServices.invokeDistribucionDeGastosPorCategoria($http, {}, $scope.distribucionOK , restServices.defaultHandlerOnError);
	};	
	invokeDistribucionDeGastosPorCategoria();
	
	function invokeDistribucionDeGastosPorTurno(turno , handlerOnSuccess){
		var data = {
				shift : turno
		};
		restServices.invokeDistribucionDeGastosPorTurno($http, data, handlerOnSuccess , restServices.defaultHandlerOnError);
	};	
	
	invokeDistribucionDeGastosPorTurno("Mañana", $scope.distribucionTurnoMananaOK);
	invokeDistribucionDeGastosPorTurno("Tarde" , $scope.distribucionTurnoTardeOK);
	invokeDistribucionDeGastosPorTurno("Noche" , $scope.distribucionTurnoNocheOK);
	
	
});
