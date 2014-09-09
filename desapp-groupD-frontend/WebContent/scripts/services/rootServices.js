/* Create an application module that holds all services */
var rootServices = angular.module('tp-dapp-eiroa-lando.services', []);

/* Global service */
rootServices.service('globalService', function () {
    this.UserScope = {
        inMainMenu: 'false',
        inCargarDatos: 'false',
        inNewOperation: 'false',
        inNewBill: 'false',
        inBills: 'false',
    };
    this.getUserPosition= function () {
        return this.UserScope;
    };

    this.anulateAllExcept = function (val){
    	var values = this.UserScope;
     	angular.forEach(values, function(value, key) {
       		if(val == key ){
       			values[key] = "true";
       		}else{
       			values[key] = "false";
       		}
     });
    this.UserScope =  values; 
    };
    this.setInMainMenu = function () {
        this.anulateAllExcept('inMainMenu');
    };
    this.setInCargarDatos = function (){
    	this.anulateAllExcept('inCargarDatos');
    };
    
    this.setInNewOperation = function (){
    	this.anulateAllExcept('inNewOperation');
    };
    
    this.setInNewBill = function (){
    	this.anulateAllExcept('inNewBill');
    };
    this.setInBills = function (){
    	this.anulateAllExcept('inBills');
    };
});