angular.module('app')
.controller('OrderHistoryListController', function (OrderService) {
    const vm = this;
    vm.orders = OrderService.getAll();
    vm.search = text => {
        vm.orders = OrderService.getAll({text});
    };

});