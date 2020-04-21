angular.module('app')
.controller('OrderActiveListController', function ($http, OrderEndService) {
    const vm = this;
    function getActiveOrders () {
        $http.get('api/orders/active')
            .then(function success(response) {
                vm.orders = response.data;
            }), function error(response) {
            console.log(response.status)
        }
    }
    getActiveOrders();

    const errorCallback = err => {
        vm.msg=`Błąd zapisu: ${err.data.message}`;
    };

    vm.finishOrder = order => {
        OrderEndService.save(order.id)
        .then(response => {
            order.end = response.data;
            vm.active = false;
        })
        .catch(errorCallback);
    };
});