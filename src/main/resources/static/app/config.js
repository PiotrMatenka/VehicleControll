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
            .when('/user-add/:userId', {
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
                templateUrl: 'app/components/cars/add/addCar.html',
                controller: 'CarController',
                controllerAs: 'ctrl'
            })
            .when('/car-add/:carId', {
                templateUrl: 'app/components/cars/add/addCar.html',
                controller: 'CarController',
                controllerAs: 'ctrl'
            })
            .when('/order-add/',{
                templateUrl: 'app/components/orders/add/addOrder.html',
                controller: 'OrderAddController',
                controllerAs: 'ctrl'
            })
            .when('/order-edit/:orderId', {
                templateUrl: 'app/components/orders/edit/editOrder.html',
                controller: 'OrderEditController',
                controllerAs: 'ctrl'
            })
            .when('/car-orders/:carId', {
                templateUrl: 'app/components/cars/orderList/orderList.html',
                controller: 'CarOrdersController',
                controllerAs: 'ctrl'
            })
            .when('/orders-history',{
                templateUrl: 'app/components/orders/list/history/orderHistoryList.html',
                controller: 'OrderHistoryListController',
                controllerAs: 'ctrl'
            })
            .when('/orders-active', {
                templateUrl: 'app/components/orders/list/active/orderActiveList.html',
                controller: 'OrderActiveListController',
                controllerAs: 'ctrl'
            })
            .when('/users-list', {
                templateUrl: 'app/components/users/list/userList.html',
                controller: 'UserListController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/',
            });
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    }
    );

