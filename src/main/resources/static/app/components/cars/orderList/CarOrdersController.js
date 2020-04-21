angular.module('app')
.controller('CarOrdersController', function ($routeParams, CarService) {
    const vm = this;
    const carId = $routeParams.carId;
    vm.orders = CarService.getOrders(carId);
});