var app = angular.module('regatta.app', ['ngResource']);

// ============================== APPLICATION ==============================

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider.when('/regatta', {templateUrl: 'view/listRegatta.html', controller: 'ListRegattaController'});
    $routeProvider.otherwise({redirectTo: '/web'});
    $locationProvider.hashPrefix('!'); // Enable ajax crawling
});

// ============================== RESOURCES ==============================

app.factory('Regatta', ['$resource', function ($resource) {
    return $resource(
        '../rest/reggata/:id', { 'id': '@id'}, {'update': {method: 'PUT'} });
}]);

// ============================== CONTROLLERS ==============================

app.controller('ListController', ['$scope', 'Todo', '$location', function ($scope, Todo, $location) {
    $scope.todos = Todo.query();
    $scope.deleteTodo = function (todo) {
        todo.$delete(function () {
            $location.path("/list");
        });
    };
    $scope.toggleTodo = function (todo) {
        todo.$update(function () {
            $location.path('/list');
        });
    };
    $scope.todosLeft = function () {
        return $scope.todos.filter(function (t) {
            return ! t.done;
        });
    };
}]);

app.controller('AddController', ['$scope', 'Todo', '$routeParams', '$location', function ($scope, Todo, $routeParams, $location) {
    $scope.todo = new Todo();
    $scope.saveTodo = function () {
        Todo.save($scope.todo, function () {
            $location.path('/list');
        });
    };
}]);

app.controller('EditController', ['$scope', 'Todo', '$routeParams', '$location', function ($scope, Todo, $routeParams, $location) {
    $scope.todo = Todo.get({id: $routeParams.id});
    $scope.saveTodo = function () {
        Todo.update($scope.todo, function () {
            $location.path('/list');
        });
    };
}]);

app.controller('DisplayController', ['$scope', 'Todo', '$routeParams', function ($scope, Todo, $routeParams) {
    $scope.todo = Todo.get({id: $routeParams.id});
}]);

