angular.module('app')
    .controller('UserEditController', function($scope, $routeParams, $location, $timeout, UserService, User) {
        const vm = this;
        const userId = $routeParams.userId;
        $scope.submitted = false;
        if(userId)
            vm.user = UserService.get(userId);
        else
            vm.user = new User();

        const saveCallback = () => {
            $location.path(`/user-edit/${vm.user.id}`);
            vm.msg = 'Dodano użytkownika';
        };
        const errorCallback = err => {
            $scope.errorMessage = err.data.message;
        };

        vm.saveUser = () => {
            $scope.submitted = true;
            UserService.save(vm.user)
                .then(saveCallback)
                .catch(errorCallback);
        };

        const updateCallback = response => vm.msg='Zapisano zmiany';

        vm.updateUser = () => {
            UserService.update(vm.user)
                .then(updateCallback)
                .catch(errorCallback);
        };
    });