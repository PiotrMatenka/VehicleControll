angular.module('app')
.controller('CarListController', function ($routeParams, UserService) {
    const vm = this;
    const userId = $routeParams.userId;
    vm.user = UserService.get(userId);
    vm.cars = UserService.getCars(userId);
});