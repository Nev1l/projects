var app = angular.module('app', []);

app.controller('Controller', function($scope, $http) {
	$scope.loadForm = function() {
		$scope.project = {};
		$scope.member = {};
		$http.get("/project/ajax/projects").success(function(response) {
			$scope.projects = response.data;
			$scope.project = $scope.projects[0];
		});
	};
	$scope.$watch('project', function() {
		$http.get("/project/ajax/members?id="+$scope.project.id).success(function(response) {
			$scope.members = response.data;
			$scope.member = $scope.members[0];
		});
	});
});
