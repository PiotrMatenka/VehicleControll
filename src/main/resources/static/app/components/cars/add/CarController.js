angular.module('app')
.controller('CarController', function ($routeParams, $location, $timeout, CarService, Car, $http ) {
    const vm = this;
    const carId = $routeParams.carId;

    if (carId)
        vm.car = CarService.get(carId);
    else
        vm.car = new Car();

    function getUser() {
        $http.get('/user')
            .then(function success(response) {
                vm.user = response.data;
                vm.car.userId = vm.user.id;

            }, function error(response) {
                console.log(response.status)
            })
    }
     getUser();

    const saveCallback = () => {
        $location.path(`/account`);
        vm.msg = "Dodano samochód";
    };
    const errorCallback = err => {
        vm.msg=`Błąd zapisu: ${err.data.message}`;
    };

    vm.saveCar = () => {
        CarService.save(vm.car)
            .then(saveCallback)
            .catch(errorCallback);
    };



    const updateCallback = response => vm.msg = "Zapisano zmiany";

    vm.updateCar = () => {
        CarService.update(vm.car)
            .then(updateCallback)
            .catch(errorCallback);
    };
})