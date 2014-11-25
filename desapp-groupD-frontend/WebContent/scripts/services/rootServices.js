/* Create an application module that holds all services */
var rootServices = angular.module('tp-dapp-eiroa-lando.services', []);

/* Global service */
rootServices.service('globalService', function () {
    this.UserScope = {
        inMainMenu: false,
        inCargarDatos: false,
        inNewOperation: false,
        inNewBill: false,
        inBills: false,
        inBillTypes: false,
        inConfigureCategories: false,
        inSellingBillTypes: false,
        inBuyingBillTypes: false,
        inABill: false,
        inBBill: false,
        inCBill: false,
        inXBill: false,
        
    };
    
    this.inBillType = {
    		selling: false,
    		buying: false,
    };
    
    this.setInBuyingBillType = function(){ return this.inBillType.buying}
    this.inSellingBillType = function(){ return this.inBillType.selling}
    
    this.getUserPosition= function () {
        return this.UserScope;
    };

    this.anulateAllExcept = function ( val){
    	var values = this.UserScope;
     	angular.forEach(values, function(value, key) {
       		if(val == key ){
       			values[key] = true;
       		}else{
       			values[key] = false;
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
    this.setInBillTypes = function (){
    	this.anulateAllExcept('inBillTypes');
    };
    
    this.setInBuyingBillTypes = function (){
    	this.anulateAllExcept('inBillTypes');
    	this.inBillType.selling = false
        this.inBillType.buying = true;
    };
    
    this.setInSellingBillTypes = function (){
    	this.anulateAllExcept('inBillTypes');
    	this.inBillType.selling = true
        this.inBillType.buying = false;
    };
    
    this.setInConfigureCategories = function (){
    	this.anulateAllExcept('inConfigureCategories');
    };
    
    this.setInABill = function (){
    	this.anulateAllExcept('inABill');
    };
    this.setInBBill = function (){
    	this.anulateAllExcept('inBBill');
    };
    this.setInCBill = function (){
    	this.anulateAllExcept('inCBill');
    };
    this.setInXBill = function (){
    	this.anulateAllExcept('inXBill');
    };
});