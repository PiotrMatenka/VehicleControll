angular.module('app')
.controller('OrderController', function (CarService, $routeParams, Order, OrderService, $http, OrderEndService, UserService, $location) {
const vm = this;
const orderId = $routeParams.orderId;
    if (orderId)
        vm.order = OrderService.get(orderId);
    else
        vm.order = new Order();

    function getUser() {
        $http.get('/user')
            .then(function success(response) {
                vm.user = response.data;
                vm.order.userId = vm.user.id;
                vm.cars = UserService.getCars(vm.user.id);

            }, function error(response) {
                console.log(response.status)
            })
    }
    getUser();


    /*const refreshData = () => {
        vm.ordered = false;
        vm.orders = UserService.getOrders(vm.car.userId);
        vm.orders.$promise.then(orders => {
            const activeOrder = vm.orders.filter(a => a.end == null);
            if(activeOrder.length) {
                vm.ordered = true;
            }
        });
    };*/

    const saveCallback = () => {
        $location.path(`/home`);
        vm.msg = "Dodano samochód";
    };

    const updateCallback = response => vm.msg = "Zapisano zmiany";
    const errorCallback = err => {
        vm.msg=`Błąd zapisu: ${err.data.message}`;
    };

    /*vm.finishOrder = order => {
    AssignmentEndService.save(assignment.id)
        .then(response => {
            assignment.end = response.data;
            vm.assigned = false;
        })
        .catch(errorCallback);
};*/

    vm.saveOrder = () =>{
        OrderService.save(vm.order)
            .then(saveCallback)
            .catch(errorCallback);
    };
    vm.updateOrder = () =>{
        OrderService.update(vm.order)
            .then(updateCallback)
            .catch(errorCallback);
    };

});