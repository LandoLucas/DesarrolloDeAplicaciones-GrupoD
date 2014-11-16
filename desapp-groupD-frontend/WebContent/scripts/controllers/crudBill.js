'use strict';

/**
 * @ngdoc function
 * @name tp-dapp-eiroa-lando.controller:MainCtrl
 * @description # MainCtrl Controller of the tp-dapp-eiroa-lando
 */
angular.module('tp-dapp-eiroa-lando').controller(
		'CrudBillCtrl',
		function($http, $location, $scope, ngTableParams, $filter, $window,
				$route, $rootScope, growl, dialogs, globalService, $translate,
				restServices,$timeout) {

			$scope.billType = function() {
				if (globalService.inSellingBillType()) {
					return 'Comprobante de Compra';
				} else {
					return 'Comprobante de Venta';
				}
			}

			$scope.paymentTypes = [ {
				code : 0,
				name : "Efectivo"
			}, {
				code : 1,
				name : "Tarjeta de crédito"
			}, {
				code : 2,
				name : "Transferencia bancaria"
			} ];

			$scope.letter = function() {
				if (globalService.UserScope.inABill)
					return 'A';
				if (globalService.UserScope.inBBill)
					return 'B';
				if (globalService.UserScope.inCBill)
					return 'C';
				if (globalService.UserScope.inXBill)
					return 'X';
			}
			$scope.dialog = function(data, messageSuccess, extraMessage) {
				var dlg = dialogs.create('views/newNameEntity.html',
						'NewNameEntityCtrl', data, 'lg');
				dlg.result.then(function(name) {
					$translate(messageSuccess).then(function(text) {
						growl.info(text + name + extraMessage);
						$scope.inicializarVista();
					});

				});
			};

			$scope.dialogNewCategory = function() {
				var extras = {
					title : 'TITLE_NEW_CATEGORY',// codigo de traduccion del
													// titulo
					serviceRest : 'category',// Campos rest son obligatorios
					resourceRest : 'save'
				}
				$scope.dialog(extras, 'DIALOG_CATEGORY_REGISTER_SUCCESS', '');
			};

			$scope.dialogNewSubcategory = function() {
				if ($scope.categorySelected != null) {
					var extras = {
						title : 'TITLE_NEW_SUBCATEGORY',
						idCategory : $scope.categorySelected.id, // valor
																	// adicional
						serviceRest : 'subcategory',
						resourceRest : 'save'
					}
					$scope.dialog(extras,
							'DIALOG_SUBCATEGORY_REGISTER_SUCCESS', '');
				} else {
					$translate('DIALOG_CATEGORY_NOTSELECTED').then(
							function(text) {
								growl.error(text);
							});
				}

			};

			$scope.dialogNewConcept = function() {
				if ($scope.subcategorySelected != null) {
					var extras = {
						title : 'TITLE_NEW_CONCEPT',
						idCategory : $scope.categorySelected.id, // valor
																	// adicional
						idSubcategory : $scope.subcategorySelected.id,
						serviceRest : 'concept',
						resourceRest : 'save'
					}
					$scope.dialog(extras, 'DIALOG_CONCEPT_REGISTER_SUCCESS',
									'');
				} else {
					$translate('DIALOG_SUBCATEGORY_NOTSELECTED').then(
							function(text) {
								growl.error(text);
							});
				}
			};

			$scope.getCategoriesOk = function(response) {
				// growl.info("Categorias obtenidas");
				$scope.categories = response;
			}

			$scope.selectCategory = function(category) {
				if (category != null) {
					if (category.subcategory == null) {
						$scope.subCategories = [];
					} else {
						if (category.subcategory.length >= 1) {
							$scope.subCategories = category.subcategory;
						} else {
							$scope.subCategories = null;
						}
					}
					console.log($scope.subCategories);
				}

			}

			$scope.selectSubcategory = function(subcat) {
				if (subcat != null) {
					if (subcat.concepts == null) {
						$scope.concepts = [];
					} else {
						if (subcat.concepts.length >= 1) {
							$scope.concepts = subcat.concepts;
						} else {
							$scope.concepts = [ subcat.concepts ];
						}
					}
				}
				console.log($scope.concepts);
			}
			
			$scope.inicializarVista = function() {
				
				restServices.invokeGetCategories($http, {}, $scope.getCategoriesOk,
						restServices.defaultHandlerOnError);
				$scope.subCategories=null;
				$scope.concepts=null;
				$scope.categorySelected=null;
				$scope.subcategorySelected=null;
				$scope.conceptSelected=null;

			}
			
			
			
//			$scope.datasets = [1,2];
//		    $scope.dataset =1;
		    var billElements = [
		                {name: "Item", cant: 1,price:0,iva:0.21,total:0}
		                ];
		    

		    
//		    var getData = function() {
//		        return $scope.dataset === 1 ? data1 : data2;
//		    };
		    $scope.addElement = function(){
		    	var obj = {name:"Item",cant:1,price:0,iva:0.21,total:0};
		    	billElements.push(obj);
		    	reloadTable();
		    }
		    
		    
		    function reloadTable(){
		    	$timeout( // Advertencia, fix sucio con timeout, se debe a error en ng-table en tableParams.reload
		    		  function(){
		    			    $scope.$watch("dataset", function () {
		    			        $scope.tableParams.reload();
		    			    });     
		    		  },
		    		  0
		    		);
		    	$scope.totalAmount = billElements.sum();
		    }
		    $scope.removeItem = function(item) { 
		    	  var index = billElements.indexOf(item)
		    	  billElements.splice(index, 1);     
		    	  reloadTable();
		    	}
		    
		    $scope.updateItem = function(item){
		    	item.$edit = false
		    	item.total = (item.price * (item.iva +1) ) * item.cant ;
		    	reloadTable();
		    }
		    
		    
		    $scope.tableParams = new ngTableParams({
		        page: 1,            // show first page
		        count: 10,          // count per page
		        sorting: {
		            name: 'asc'     // initial sorting
		        }
		    }, {
		        total: function () { return getData().length; }, // length of data
		        getData: function($defer, params) {
		            var filteredData = billElements;
		            var orderedData = params.sorting() ?
		                                $filter('orderBy')(filteredData, params.orderBy()) :
		                                filteredData;

		            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
		        },
		        $scope: { $data: {} }
		    });
		    
		    Array.prototype.sum = function () {
		        var total = 0
		        for ( var i = 0, _len = this.length; i < _len; i++ ) {
		            total +=  (this[i].price * (this[i].iva +1) ) * this[i].cant  
		        }
		        return total
		    }
			
		    $scope.inicializarVista();
			reloadTable();
		});