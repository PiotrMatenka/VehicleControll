angular.module('app')
.controller('OrderAddController', function (CarService, $routeParams, Order, OrderService, $http, OrderEndService, UserService, $location) {
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

    const saveCallback = () => {
        $location.path(`/home`);
        vm.msg = "Dodano samochód";
    };


    const errorCallback = err => {
        vm.msg=`Błąd zapisu: ${err.data.message}`;
    };

    vm.saveOrder = () =>{
        OrderService.save(vm.order)
            .then(saveCallback)
            .catch(errorCallback);
    };

});