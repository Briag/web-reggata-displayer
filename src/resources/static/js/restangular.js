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

var app = angular.module('restangular.app', ['ngResource']);

// ============================== APPLICATION ==============================

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider.when('/list', {templateUrl: 'view/list.html', controller: 'ListController'});
    $routeProvider.when('/add', {templateUrl: 'view/add.html', controller: 'AddController'});
    $routeProvider.when('/:id/edit', {templateUrl: 'view/add.html', controller: 'EditController'});
    $routeProvider.when('/:id', {templateUrl: 'view/display.html', controller: 'DisplayController'});
    $routeProvider.otherwise({redirectTo: '/list'});
    $locationProvider.hashPrefix('!'); // Enable ajax crawling
});

// ============================== RESOURCES ==============================

app.factory('Regatta', ['$resource', function ($resource) {
    return $resource(
        '../rest/regatta/:id', { 'id': '@idRegatta'}, {'update': {method: 'PUT'} });
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

