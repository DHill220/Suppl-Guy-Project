/**
* module
 */

(function(){
	var productapp = angular.module('productapp',['ngRoute']);
	
	productapp.config(function($routeProvider) {
		  $routeProvider
		  .when("/products", {
		    templateUrl : "products.html",
			controller : "productsController"
			
		  })
		 
		  .when("/search", {
		    templateUrl : "search.html", 
		    controller : "searchController"
		
		  })
		  .when("/create", {
		    templateUrl : "create.html",
		    controller : "createController"
		
		  })
		  .when("/fancy", {
		    templateUrl : "fancy.html",
		
		  })
		  .when("/stack", {
		    templateUrl : "stack.html"
		    
		  })
		  .when("/resume", {
		    templateUrl : "resume.html"
		  
		  })		     
		  .when("/update/:product_id", {
			templateUrl : "update.html",
			controller : "updateController"
			
		  })
		  .when("/cart",{
			templateUrl: "cart.html"
			
		  })		  
		  .when("/main", {
			templateUrl: "main.html"
		  
		  })		  		  
		  .otherwise({
			  templateUrl: "main.html"
			  
		  })
		 
		});

})()