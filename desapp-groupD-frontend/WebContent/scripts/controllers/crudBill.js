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
					$scope.isOutcome =false;
					return 'Comprobante de Compra';
				} else {
					
					$scope.isOutcome =true;
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
				if (globalService.UserScope.inABill){$scope.billLetter = 'a';return 'A';;}
				if (globalService.UserScope.inBBill){$scope.billLetter = 'b';return 'B';}
				if (globalService.UserScope.inCBill){$scope.billLetter = 'c';return 'C';}
				if (globalService.UserScope.inXBill){$scope.billLetter = 'x';return 'X';}
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
			
			
			
		    var billElements = [
		                {name: "Item", cant: 1,price:0,iva:0.21,total:0}
		                ];
		    

		    
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
		    	$scope.totalAmount = billElements.sumTotal();
		    	$scope.totalAmountNoTaxes = billElements.sumTotalNoTaxes();
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
		    
		    Array.prototype.sumTotal = function () {
		        var total = 0
		        for ( var i = 0, _len = this.length; i < _len; i++ ) {
		            total +=  (this[i].price * (this[i].iva +1) ) * this[i].cant  
		        }
		        return total
		    }
		    Array.prototype.sumTotalNoTaxes = function () {
		        var total = 0
		        for ( var i = 0, _len = this.length; i < _len; i++ ) {
		            total +=  this[i].price   * this[i].cant  
		        }
		        return total
		    }
		    
		    $scope.registerBill = function() {
				var data = $scope.populateParams();
				if(validate()){
					restServices.invokeRegisterBill($http, data, $scope.registerBillOk,
							restServices.defaultHandlerOnError);
				}else{
					getErrorMessage();
				}
				
			}

			$scope.updateBillOk = function(response) {
					growl.info("Operacion actualizada.");
					$location.path('/cargarDatos');
			}

			$scope.populateParams = function() {
				var data = {
					letter: $scope.billLetter,
					date : $scope.inputDate,
					serie: $scope.inputSerie,
					billNumber: $scope.inputBillNumber,
					client_seller: $scope.inputClientSeller,
					phone: $scope.inputPhone,
					cuit: $scope.inputCuit,
					totalNoTaxes: $scope.totalAmountNoTaxes,
					total: $scope.totalAmount,
					category : $scope.categorySelected.categoryName,
					subCategory : $scope.subcategorySelected.subcategoryName,
					concept: $scope.conceptSelected.conceptName,
					paymentCode : $scope.paymentSelected.code,
					shift : $scope.inputShift,
					isOutcome: $scope.isOutcome,
					address: $scope.inputAddress,
					items: billElements
				};
				return data;
			}
		    


		    

			
			function validate(){
				if ($scope.inputDate == null || $scope.inputDate== ""){
					return false;
				}
				return true;
			}
			
			function getErrorMessage(){
				if ($scope.inputDate == null || $scope.inputDate== ""){
					$translate('FORM_ERROR_DATE_REQUIRED').then(function (text) {
						growl.error(text);
					});
				}
			}

			$scope.updateBill= function() {
				if(validate()){
					var data = $scope.populateParams();
					data.id = $rootScope.BillToEdit.id;
					restServices.invokeUpdateBill($http, data, $scope.updateBillOk,
							restServices.defaultHandlerOnError);
				}else{
					getErrorMessage();
				
				}
			}


			
		    $scope.inicializarVista();
			reloadTable();
		});