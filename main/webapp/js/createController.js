

(function(){
	let productapp = angular.module('productapp');
	
	productapp.controller('createController', function($scope, $http){
		
		$scope.category = ['Cleaners', 'Dressings', 'Equipment', 'Coatings'];
		
		$scope.createProduct = function() {
			$http.post("/supplyguy/webapi/supplyguy/products", $scope.product)
				.then(function(response) {
					$scope.createStatus = 'create successful';
					$scope.disableCreate = true;
				}, function(response) {
					$scope.createStatus = 'error trying to create product';
					console.log('error http POST product: ' + response.status);
				});
		}
		$scope.clear = function() {
			$scope.product.title = '';			
			$scope.product.category = '';
			$scope.disableCreate = false;
			$scope.createForm.$setUntouched();
			$scope.createForm.$setPrestine();
			$scope.createStatus = ' ';
		}
	});
})()
