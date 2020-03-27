angular.module('app')
.controller('CarController', function ($routeParams, $location, $timeout, CarService, Car, $http ) {
    const vm = this;
    const carId = $routeParams.id;
    if (carId)
        vm.car = CarService.get(carId);
    else
        vm.car = new Car();
    function getUser() {
        $http.get('/user')
            .then(function success(response) {
                vm.user = response.data;

            }, function error(response) {
                console.log(response.status)
            })
    }
    const saveCallback = () => {
        getUser();
        vm.car.userId = vm.user.id;
        $location.path(`/car-add/${vm.car.id}`);
        vm.msg = "Dodano samochód";
    };
    const errorCallback = err => {
        $scope.errorMessage = err.data.message;
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