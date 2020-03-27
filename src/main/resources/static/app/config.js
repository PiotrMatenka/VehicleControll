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

            .when('/user-cars/:userId', {
                templateUrl: 'app/components/users/userCars/carList.html',
                controller: 'CarListController',
                controllerAs: 'ctrl'
            })
            .when('/user-login',{
                templateUrl: 'app/components/users/login/login.html',
                controller: 'AuthenticationController',
                controllerAs: 'ctrl'
            })
            .when ('/home', {
                templateUrl: 'app/components/home/home.html',
                controller: 'HomeController',
                controllerAs: 'ctrl'
            })
            .when('/car-add', {
                templateUrl: 'app/components/cars/addCar.html',
                controller: 'CarController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/',
            });
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    }
    );

