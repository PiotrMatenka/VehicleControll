angular.module('app')
.controller('OrderEditController', function ($routeParams, OrderService, $location) {
    const vm = this;
    const orderId = $routeParams.orderId;
    if (orderId)
        vm.order = OrderService.get(orderId);
    else
        vm.order = new Order();

    const updateCallback = () =>
    {
        $location.path('/orders-active')
        vm.msg = "Zapisano zmiany";
    }

    const errorCallback = err => {
        vm.msg=`Błąd zapisu: ${err.data.message}`;
    };

    vm.updateOrder = () =>{
        OrderService.update(vm.order)
            .then(updateCallback)
            .catch(errorCallback);
    };
})