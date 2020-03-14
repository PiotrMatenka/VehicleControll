angular.module('app')
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider
            .when('/users', {
                templateUrl: 'app/components/users/list/userList.html',
                controller: 'UserListController',
                controllerAs: 'ctrl'
            })
            .when('/user-add', {
                templateUrl: 'app/components/users/edit/userEdit.html',
                controller: 'UserEditController',
                controllerAs: 'ctrl'
            })
            .when('/user-login',{
                templateUrl:'app/components/users/login/login.html',
                controller: 'AuthenticationController',
                controllerAs: 'ctrl'
            })
            .when('/user-cars/:userId', {
                templateUrl: 'app/components/users/userCars/carList.html',
                controller: 'CarListController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/',
                controller: 'HomeController',
                controllerAs: 'ctrl'
            });
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});
