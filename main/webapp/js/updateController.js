

(function(){
	let productapp = angular.module('productapp');
	
	productapp.controller('updateController', function($scope, $http, $routeParams, $location){
		
		$scope.category = ['Cleaners', 'Dressings', 'Coatings', 'Equipment'];
		
		$scope.getProductsById = function(){
			
			var updateProductId = localStorage.getItem('updateProductId')
			$http.get("/supplyguy/webapi/supplyguy/products/" + updateProductId)
			.then(function(response){
				var products = response.data;
				if(products.length == 1){
					$scope.product = products[0];
				} else {
					//TODO error message
				}
			}, function(response){
				console.log('error http GET product by id: ' + response.status);
			});
		}
		
		$scope.getProductsById();
		
		$scope.updateProduct = function() {
			$http.put("/supplyguy/webapi/supplyguy/products/" + $scope.product.id, $scope.product)
				.then(function(response) {
					$scope.updateStatus = 'update successful';
				}, function(response) {
					$scope.updateStatus = 'error trying to update product';
					console.log('error http PUT product: ' + response.status);
				});
		}
		$scope.deleteProduct = function() {
			$http.delete("/supplyguy/webapi/supplyguy/products/" + $scope.product.id)
				.then(function(response) {
					$scope.updateStatus = 'delete successful';
					$scope.disableUpdate = true;
				}, function(response) {
					$scope.updateStatus = 'error trying to delete product';
					console.log('error http DELETE product: ' + response.status);
				});
			}
			
			$scope.goToSearchView = function(){
			console.log('got to search view');
			$location.path('/search');
			
			}
	});
	
	
})()