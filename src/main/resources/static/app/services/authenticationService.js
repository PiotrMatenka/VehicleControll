angular.module('app')
    .constant('LOGIN_ENDPOINT', '/user-login')
    .constant('LOGOUT_ENDPOINT', '/user-logout')
    .service('AuthenticationService', function($http, LOGIN_ENDPOINT, LOGOUT_ENDPOINT) {
        this.authenticate = function(credentials, successCallback) {
            var authHeader = {Authorization: 'Basic ' + btoa(credentials.email+':'+credentials.password)};
            var config = {headers: authHeader};
            $http
                .post(LOGIN_ENDPOINT, {}, config)
                .then(function success(value) {
                    successCallback();
                }, function error(reason) {
                    console.log('Login error');
                    console.log(reason);
                });
        }
        this.logout = function(successCallback) {
            $http.post(LOGOUT_ENDPOINT)
                .then(successCallback());
        }

    });