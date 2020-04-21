angular.module('app')
    .controller('HomeController', function ( $http, UserService) {
        var vm = this;
        function getUser() {
            $http.get('/user')
                .then(function success(response) {
                    vm.user = response.data;
                    vm.orders = UserService.getOrders(vm.user.id);
                    vm.userRole = UserService.getRoles(vm.user.id);
                }, function error(response) {
                    console.log(response.status)
                })
        }
    getUser();


    });




