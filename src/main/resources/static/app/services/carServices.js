angular.module('app')
    .constant('CAR_ENDPOINT','/api/cars/:id')
    .factory('Car', function ($resource, CAR_ENDPOINT) {
        return $resource(CAR_ENDPOINT, {id: '@_id'},{
            update: {
                method: "PUT"
            }
        });

    })
    .service('CarService', function (Car) {
        this.save = car => car.$save();
        this.update = car => car.$update({id: car.id});
        this.get = index => Car.get({id: index});
        this.getAll = params => Car.query(params);

    });