var app = angular.module('app', []);

app.controller('SetController', function($scope, $http) {
	$scope.loadForm = function() {
		$http.get("/project/ajax.projects.do").success(function(response) {
			alert("result:"+response.data);
			$scope.projects = response.data;
			$scope.project = $scope.projects[0];
		});
	};
	$scope.project = {};
	$scope.$watch('project', function() {
		$http.get("/project/ajax.members.do?id="+$scope.project.id).success(function(response) {
			$scope.members = response.data;
			$scope.member = $scope.members[0];
		});
	});
});
