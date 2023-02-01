/**
*Access to the product page
 */


(function() {
	var productapp = angular.module('productapp');
	productapp.controller('productController', function($scope, $http) {

		$scope.itemsForSale = [{ image: 'comingsoon.jpg', title: 'Swipe', price: 25.00 },
		{ image: 'biozymegallon_large.jpeg', title: 'Bio-Zyme', price: 30.00 },
		{ image: 'bugoffgallon_large.jpeg', title: 'Bug Off', price: 30.00 },
		{ image: 'comingsoon.jpg', title: 'Super Car Wash', price: 18.00 },
		{ image: 'washwaxgallon_large.jpeg', title: 'Wash Wax', price: 18.00 },
		{ image: 'jadequartz.jpeg', title: 'Jade Quartz', price: 120.00 }];
		$scope.addToCart = function(item) {
			if (localStorage.getItem('cart') != null) {
				$scope.cart = JSON.parse(localStorage.getItem('cart'));
				$scope.cart.push(item);
				localStorage.setItem('cart', JSON.stringify($scope.cart));
			} else {
				// new cart
				$scope.cart = [item];
				localStorage.setItem('cart', JSON.stringify($scope.cart));
			}
		}
		$scope.getCart = function() {
			if (localStorage.getItem('cart') != null) {
				$scope.cart = JSON.parse(localStorage.getItem('cart'));
			} else {
				$scope.cart = [];
			}
		}
		$scope.getCart();
		$scope.viewCart = false;
		$scope.viewMyCart = function() {
			$scope.viewCart = true;
		}
		$scope.hideMyCart = function() {
			$scope.viewCart = false;
		}
		$scope.emptyMyCart = function() {
			if (localStorage.getItem('cart') != null) {
				$scope.cart = [];
				localStorage.setItem('cart', JSON.stringify($scope.cart));
			}
		}
	});
})()