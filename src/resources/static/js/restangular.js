/*
 * Copyright 2013 Olivier Croisier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var app = angular.module('restangular.app', ['ngResource', 'ngRoute']);

// ============================== APPLICATION ==============================

app.config(function ($routeProvider, $locationProvider) {
	$routeProvider.when('/admin/teammate/list', {templateUrl: 'view/teammate/list.html', controller: 'TeammateListController'});
    $routeProvider.when('/admin/teammate/add', {templateUrl: 'view/teammate/add.html', controller: 'TeammateAddController'});
    $routeProvider.when('/admin/teammate/:id/edit', {templateUrl: 'view/teammate/add.html', controller: 'TeammateEditController'});
    $routeProvider.when('/admin/teammate/:id', {templateUrl: 'view/teammate/display.html', controller: 'TeammateDisplayController'});
   
    $routeProvider.when('/list', {templateUrl: 'view/list.html', controller: 'ListController'});
    $routeProvider.when('/add', {templateUrl: 'view/add.html', controller: 'AddController'});
    $routeProvider.when('/:id/edit', {templateUrl: 'view/add.html', controller: 'EditController'});
    $routeProvider.when('/:id', {templateUrl: 'view/display.html', controller: 'DisplayController'});
    $routeProvider.otherwise({redirectTo: '/list'});
    $locationProvider.hashPrefix('!'); // Enable ajax crawling
});

app.config(function($httpProvider) {

    $httpProvider.defaults.transformResponse.push(function(data,headersGetter){
    	if(headersGetter()['content-type'] === "application/json")
    	{	console.log(":D");return JSOG.decode(data);
    	}
    	return data;
    });
    
    $httpProvider.defaults.transformRequest.unshift(function(data,headersGetter){
    	return JSOG.encode(data);
    });

})
// ============================== RESOURCES ==============================

app.factory('Regatta', ['$resource', function ($resource) {
    return $resource(
        '../rest/regatta/:id', { 'id': '@idRegatta'}, {'update': {method: 'PUT'} });
}]);

app.factory('Boat', ['$resource', function ($resource) {
    return $resource(
        '../rest/boat/:id', { 'id': '@idBoat'}, {'update': {method: 'PUT'} });
}]);

app.factory('Teammate', ['$resource', function ($resource) {
    return $resource(
        '../rest/teammate/:id', { 'id': '@idTeammate'}, {'update': {method: 'PUT'} });
}]);

// ============================== CONTROLLERS ==============================

app.controller('ListController', ['$scope', 'Regatta', '$location', function ($scope, Regatta, $location) {
    $scope.regattas = Regatta.query();
    $scope.deleteRegatta = function (regatta) {
        regatta.$delete(function () {
            $location.path("/list");
        });
    };
    $scope.toggleRegatta = function (regatta) {
        regatta.$update(function () {
            $location.path('/list');
        });
    };
    $scope.regattasLeft = function () {
        return $scope.regattas.filter(function (t) {
            return ! t.done;
        });
    };
}]);

app.controller('AddController', ['$scope', 'Regatta', '$routeParams', '$location', function ($scope, Regatta, $routeParams, $location) {
    $scope.regatta = new Regatta();
    $scope.saveRegatta = function () {
    	Regatta.save($scope.regatta, function () {
            $location.path('/list');
        });
    };
}]);

app.controller('EditController', ['$scope', 'Regatta', '$routeParams', '$location', function ($scope, Regatta, $routeParams, $location) {
    $scope.regatta = Regatta.get({id: $routeParams.id});
    $scope.saveRegatta = function () {
    	Regatta.update($scope.regatta, function () {
            $location.path('/list');
        });
    };
}]);

app.controller('DisplayController', ['$scope', 'Regatta', '$routeParams', function ($scope, Regatta, $routeParams) {
    $scope.regatta = Regatta.get({id: $routeParams.id});
}]);




app.controller('TeammateListController', ['$scope', 'Teammate', '$location', function ($scope, Teammate, $location) {
	$scope.teammates = Teammate.query();
    $scope.deleteTeammate = function (teammate) {
    	teammate.$delete(function () {
            $location.path("admin/teammate/list");
        });
    };
    $scope.toggleTeammate = function (teammate) {
    	teammate.$update(function () {
            $location.path('admin/teammate/list');
        });
    };
}]);

app.controller('TeammateAddController', ['$scope', 'Teammate', '$routeParams', '$location', function ($scope, Teammate, $routeParams, $location) {
    $scope.teammate = new Teammate();
    $scope.saveTeammate = function () {
    	Teammate.save($scope.teammate, function () {
            $location.path('admin/teammate/list');
        });
    };
}]);

app.controller('TeammateEditController', ['$scope', 'Teammate', '$routeParams', '$location', function ($scope, Teammate, $routeParams, $location) {
    $scope.teammate = Teammate.get({id: $routeParams.id});
    console.log($scope.teammate);
    $scope.saveTeammate = function () {
    	Teammate.update($scope.teammate, function () {
            $location.path('admin/teammate/list');
        });
    };
}]);

app.controller('TeammateDisplayController', ['$scope', 'Teammate', '$routeParams', function ($scope, Teammate, $routeParams) {
    $scope.teammate = Teammate.get({id: $routeParams.id});
}]);

