angular.module('app')
.controller('OrderListController', function ($routeParams, CarService) {
    const vm = this;
    const carId = $routeParams.carId;
    vm.orders = CarService.getOrders(carId);

});