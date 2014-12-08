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
				restServices, $timeout) {

			$scope.detailMode = false;

			$scope.inputDate = $filter("date")(Date.now(), 'yyyy-MM-dd');

			$scope.autoload = true;

			$scope.searchProviderOk = function(response) {
				console.log(response)
				$scope.inputClientSellerName = response.name;
				$scope.inputTradeName = response.tradeName;
				$scope.inputCuit = response.cuit;
				$scope.inputPhone = response.telephone;
				$scope.inputAddress = response.direction;
				growl.info("Autoloaded..")
			}
			
			$scope.searchProviderFail = function(response){
				growl.error("Error al intentar obtener el id")
			}

			$scope.searchProvider = function() {
				console.log("Tratando de buscar datos para id:"
						+ $scope.inputClientSellerId)

						var data = {providerId: $scope.inputClientSellerId}
						restServices.invokeSearchProvider($http, data,
								$scope.searchProviderOk,
								$scope.searchProviderFail);
			}

			$scope.toggleAutoLoading = function() {
				$scope.autoload = !($scope.autoload);
			}
			var tempFilterText = '';
			var filterTextTimeout = 1000;
			$scope.$watch('inputClientSellerId', function(val) {
				if (filterTextTimeout)
					$timeout.cancel(filterTextTimeout);

				tempFilterText = val;
				filterTextTimeout = $timeout(function() {
					if ($scope.inputClientSellerId != null && $scope.autoload)
						$scope.searchProvider();
				}, 1000);
			})

			$scope.enableDetailMode = function() {
				$scope.detailMode = true;
			}

			$scope.disableDetailMode = function() {
				$scope.detailMode = false;
			}

			Array.prototype.sumTotal = function() {
				var total = 0
				for (var i = 0, _len = this.length; i < _len; i++) {
					total += (this[i].price * (this[i].iva + 1)) * this[i].cant
				}
				return total + $scope.inputIIBB + $scope.inputExtraTax
			}
			Array.prototype.sumTotalNoTaxes = function() {
				var total = 0
				for (var i = 0, _len = this.length; i < _len; i++) {
					total += this[i].price * this[i].cant
				}
				return total
			}

			$scope.getCategoriesOk = function(response) {
				// growl.info("Categorias obtenidas");
				$scope.categories = response;
			}
			$scope.inicializarVista = function() {

				restServices.invokeGetCategories($http, {},
						$scope.getCategoriesOk,
						restServices.defaultHandlerOnError);
				$scope.subCategories = null;
				$scope.concepts = null;
				$scope.categorySelected = null;
				$scope.subcategorySelected = null;
				$scope.conceptSelected = null;
				if (globalService.inSellingBillType()) {
					$scope.isOutcome = false;
				} else {
					$scope.isOutcome = true;
				}
			}

			$scope.tableColumns = {
				item : "item",
				cant : "cant",
				price : "price",
				iva : "Iva",
				total : "Total"
			};
			translateTableColumns();
			$rootScope.$on('$translateChangeSuccess', function() {
				translateTableColumns();
			});
			$scope.isCollapsed = false;

			function translateTableColumns() {
				$translate('FORM_NAME').then(function(text) {
					$scope.tableColumns.item = text;
				});
				$translate('FORM_QUANTITY').then(function(text) {
					$scope.tableColumns.cant = text;
				});
				$translate('FORM_PRICE').then(function(text) {
					$scope.tableColumns.price = text;
				});
				$translate('FORM_ACTIONS').then(function(text) {
					$scope.tableColumns.actions = text;
				});
			}

			$scope.shifts = [ {
				name : "Mañana"
			}, {
				name : "Tarde"
			}, {
				name : "Noche"
			} ];

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
				if (globalService.UserScope.inABill) {
					$scope.billLetter = 'a';
					return 'A';
					;
				}
				if (globalService.UserScope.inBBill) {
					$scope.billLetter = 'b';
					return 'B';
				}
				if (globalService.UserScope.inCBill) {
					$scope.billLetter = 'c';
					return 'C';
				}
				if (globalService.UserScope.inXBill) {
					$scope.billLetter = 'x';
					return 'X';
				}
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
					$scope
							.dialog(extras, 'DIALOG_CONCEPT_REGISTER_SUCCESS',
									'');
				} else {
					$translate('DIALOG_SUBCATEGORY_NOTSELECTED').then(
							function(text) {
								growl.error(text);
							});
				}
			};

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

			var billElements = [ {
				name : "Item",
				cant : 1,
				price : 0,
				iva : 0.21,
				total : 0
			} ];

			$scope.addElement = function() {
				var obj = {
					name : "Item",
					cant : 1,
					price : 0,
					iva : 0.21,
					total : 0
				};
				billElements.push(obj);
				reloadTable();
			}

			$scope.showTotalAmount = function() {
				if (!$scope.detailMode) {
					return $scope.inputNeto + $scope.inputIVA
							+ $scope.inputIIBB + $scope.inputExtraTax;
				} else {
					return billElements.sumTotal();
				}
			}
			$scope.showTotalAmountNoTaxes = function() {
				if (!$scope.detailMode) {
					return $scope.inputNeto;
				} else {
					return billElements.sumTotalNoTaxes();
				}
			}

			function reloadTable() {
				$timeout( // Advertencia, fix sucio con timeout, se debe a
				// error en ng-table en tableParams.reload
				function() {
					$scope.$watch("dataset", function() {
						$scope.tableParams.reload();
					});
				}, 0);
			}
			$scope.removeItem = function(item) {
				var index = billElements.indexOf(item)
				billElements.splice(index, 1);
				reloadTable();
			}

			$scope.updateItem = function(item) {
				item.$edit = false
				item.total = (item.price * (item.iva + 1)) * item.cant;
				reloadTable();
			}

			$scope.tableParams = new ngTableParams({
				page : 1, // show first page
				count : 30, // count per page
				sorting : {
					name : 'asc' // initial sorting
				}
			}, {
				counts : [],
				total : function() {
					return getData().length;
				}, // length of data
				getData : function($defer, params) {
					var filteredData = billElements;
					var orderedData = params.sorting() ? $filter('orderBy')(
							filteredData, params.orderBy()) : filteredData;

					$defer.resolve(orderedData.slice((params.page() - 1)
							* params.count(), params.page() * params.count()));
				},
				$scope : {
					$data : {}
				}
			});

			$scope.registerBill = function() {

				if (validate()) {
					var data = $scope.populateParams();
					restServices.invokeRegisterBill($http, data,
							$scope.registerBillOk,
							restServices.defaultHandlerOnError);
				} else {
					getErrorMessage();
				}

			}

			$scope.registerBillOk = function(response) {
				growl.info("Comprobante registrado.");
				$location.path('/cargarDatos');
			}

			$scope.updateBillOk = function(response) {
				growl.info("Operacion actualizada.");
				$location.path('/cargarDatos');
			}

			$scope.populateParams = function() {
				var sub = "";
				var con = "";
				var iibb = 0;
				var cuit = 0;
				var add = "";
				var phone = 0;
				var shift = "";
				if ($scope.subcategorySelected) {
					var sub = $scope.subcategorySelected.subcategoryName;
				}
				if ($scope.conceptSelected) {
					var con = $scope.conceptSelected.conceptName;
				}
				if ($scope.inputIIBB) {
					var iibb = $scope.inputIIBB;
				}
				if ($scope.inputPhone) {
					var phone = $scope.inputPhone;
				}
				if ($scope.inputCuit) {
					var cuit = $scope.inputCuit;
				}
				if ($scope.inputAddress) {
					var add = $scope.inputAddress;
				}
				if ($scope.inputShift) {
					var shift = $scope.inputShift;
				}

				var data = {
					letter : $scope.billLetter,
					date : $scope.inputDate,
					serie : $scope.inputSerie,
					billNumber : $scope.inputBillNumber,
					client_seller : $scope.inputClientSeller,
					iibb : iibb,
					phone : phone,
					cuit : cuit,
					totalNoTaxes : $scope.totalAmountNoTaxes,
					total : $scope.totalAmount,
					gravado : $scope.totalAmount,
					noGravado : $scope.totalAmountNoTaxes,
					category : $scope.categorySelected.categoryName,
					subCategory : sub,
					concept : con,
					paymentCode : $scope.paymentSelected.code,
					shift : shift,
					isOutcome : $scope.isOutcome,
					address : add,
					iva : 21,
					items : billElements
				};
				return data;
			}

			function validate() {
				if ($scope.inputDate == null || $scope.inputDate == "") {
					return false;
				}
				if ($scope.inputSerie == null || $scope.inputSerie == "") {
					return false;
				}
				if ($scope.inputBillNumber == null
						|| $scope.inputBillNumber == "") {
					return false;
				}
				if ($scope.inputClientSeller == null
						|| $scope.inputClientSeller == "") {
					return false;
				}
				if ($scope.categorySelected == null
						|| $scope.categorySelected == "") {
					return false;
				}
				if ($scope.paymentSelected == null
						|| $scope.paymentSelected == "") {
					return false;
				}
				return true;
			}

			function getErrorMessage() {
				if ($scope.inputDate == null || $scope.inputDate == "") {
					$translate('FORM_ERROR_DATE_REQUIRED').then(function(text) {
						growl.error(text);
					});
				}
				if ($scope.inputSerie == null || $scope.inputSerie == "") {
					$translate('FORM_ERROR_SERIE_REQUIRED').then(
							function(text) {
								growl.error(text);
							});
				}
				if ($scope.inputBillNumber == null
						|| $scope.inputBillNumber == "") {
					$translate('FORM_ERROR_BILLNUM_REQUIRED').then(
							function(text) {
								growl.error(text);
							});
				}

				if ($scope.inputClientSeller == null
						|| $scope.inputClientSeller == "") {
					if (globalService.inSellingBillType()) {
						$translate('FORM_ERROR_SELLER_REQUIRED').then(
								function(text) {
									growl.error(text);
								});
					} else {
						$translate('FORM_ERROR_CLIENT_REQUIRED').then(
								function(text) {
									growl.error(text);
								});
					}
				}

				if (billElements.length < 1) {
					$translate('FORM_ERROR_ITEM_REQUIRED').then(function(text) {
						growl.error(text);
					});
				}

				if ($scope.categorySelected == null
						|| $scope.categorySelected == "") {
					$translate('FORM_ERROR_CATEGORY_REQUIRED').then(
							function(text) {
								growl.error(text);
							});
				}

				if ($scope.paymentSelected == null
						|| $scope.paymentSelected == "") {
					$translate('FORM_ERROR_PAYMENTTYPE_REQUIRED').then(
							function(text) {
								growl.error(text);
							});
				}

			}

			$scope.updateBill = function() {
				if (validate()) {
					var data = $scope.populateParams();
					data.id = $rootScope.BillToEdit.id;
					restServices.invokeUpdateBill($http, data,
							$scope.updateBillOk,
							restServices.defaultHandlerOnError);
				} else {
					getErrorMessage();

				}
			}

			$scope.inicializarVista();
			reloadTable();

		});