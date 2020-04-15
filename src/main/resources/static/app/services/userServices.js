angular.module('app')
    .constant('USER_ENDPOINT', '/api/users/:id')
    .constant('USER_CARS_ENDPOINT', '/api/users/:id/cars')
    .constant('USER_ORDERS_ENDPOINT', '/api/users/:id/orders' )
    .factory('User', function($resource, USER_ENDPOINT, USER_CARS_ENDPOINT, USER_ORDERS_ENDPOINT) {
        return $resource(USER_ENDPOINT, { id: '@_id' }, {
            update: {
                method: 'PUT'
            },
            getCars: {
                method: 'GET',
                url: USER_CARS_ENDPOINT,
                params: {id: '@id'},
                isArray: true
            },
            getOrders: {
                method: 'GET',
                url: USER_ORDERS_ENDPOINT,
                params: {id: '@id'},
                isArray: true
            }

        });
    })
    .service('UserService', function(User) {
        this.getAll = params => User.query(params);
        this.get = index => User.get({id: index});
        this.getCars = index => User.getCars({id: index});
        this.getOrders = index => User.getOrders({id: index});
        this.save = user => user.$save();
        this.update = user => user.$update({id: user.id});
    });