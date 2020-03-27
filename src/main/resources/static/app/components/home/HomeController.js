angular.module('app')
    .controller('HomeController', function ( $http) {
        var vm = this;
        function getUser() {
            $http.get('/user')
                .then(function success(response) {
                    vm.user = response.data;

                }, function error(response) {
                    console.log(response.status)
                })
        }
    getUser();
    });




