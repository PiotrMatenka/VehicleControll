angular.module('app')
.controller('HomeController', function ($routeParams, UserService, AuthenticationService) {
    const vm = this;
    const userId = $routeParams(userId);
    vm.user = UserService.get(userId);

});