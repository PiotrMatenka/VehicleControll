angular.module('app')
    .constant('CAR_ENDPOINT','/api/cars/:id')
    .constant('CAR_ORDERS_ENDPOINT', '/api/cars/:id/orders')
    .factory('Car', function ($resource, CAR_ENDPOINT, CAR_ORDERS_ENDPOINT) {
        return $resource(CAR_ENDPOINT, {id: '@_id'},{
            update: {
                method: "PUT"
            },
            getOrders: {
                method: 'GET',
                url: CAR_ORDERS_ENDPOINT,
                params: {id: '@id'},
                isArray: true
            }
        });

    })
    .service('CarService', function (Car) {
        this.save = car => car.$save();
        this.update = car => car.$update({id: car.id});
        this.get = index => Car.get({id: index});
        this.getAll = params => Car.query(params);
        this.getOrders = index => Car.getOrders({id: index});

    });