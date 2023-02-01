/**
*access to product.js
 */

(function(){ 
	 var productapp = angular.module('productapp');
	 
	 productapp.controller('searchController', function($scope,$http,$location){
		
		$scope.category = ['Cleaners', 'Dressings', 'Coatings', 'Equipment'];
		
		 $scope.getAllProducts = function() {
			 $http.get("/supplyguy/webapi/supplyguy/products")
				 .then(function(response) {
					 $scope.products = response.data;
					 console.log('number of products: ' + $scope.products.length);
				 }, function(response) {
					 console.log('error http GET products: ' + response.status);
				 });
		 }
		 
		 $scope.goToUpdateView = function(product_id){
			console.log('product_id: ' + product_id);
			localStorage.setItem('updateProductId', product_id)
			$location.path('/update/' + product_id);
		}
		
//		 $scope.getAllProducts();
	});
})()