angular.module('app')
.constant('ORDER_ENDPOINT','/api/orders/:id')
.factory('Order', function ($resource, ORDER_ENDPOINT) {
    return $resource(ORDER_ENDPOINT, {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });
})
.service('OrderService', function () {
    this.save = order => order.$save();
    this.update = order => order.$update({id: order.id});
})
.service('OrderEndService', function ($http) {
    this.save = orderId => $http.post(`/api/orders/${orderId}/end`);
});