angular.module('app')
    .constant('USER_ENDPOINT', '/api/users/:id')
    .constant('USER_CARS_ENDPOINT', '/api/users/:id/cars')
    .factory('User', function($resource, USER_ENDPOINT, USER_CARS_ENDPOINT) {
        return $resource(USER_ENDPOINT, { id: '@_id' }, {
            update: {
                method: 'PUT'
            },
            getCars: {
                method: 'GET',
                url: USER_CARS_ENDPOINT,
                params: {id: '@id'},
                isArray: true
            }

        });
    })
    .service('UserService', function(User) {
        this.getAll = params => User.query(params);
        this.get = index => User.get({id: index});
        this.getCars = index => User.getCars({id: index});
        this.save = user => user.$save();
        this.update = user => user.$update({id: user.id});
    });