var app = angular.module('app', []);

app.controller('SetController', function($scope, $http) {
		$scope.isInit = false;
		$scope.loadForm = function() {
			$http.get("/project/ajax.projects.do").success(function(response) {
				$scope.projects = response;
				$scope.project = $scope.projects[0];
				$scope.isInit = true;
			});
			$http.get("/project/ajax.members.do?id=" + $scope.project.id)
			.success(function(response) {
				$scope.members = response;
				$scope.member = $scope.members[0];
			});
		};
		$scope.$watch('project', function() {
			if ($scope.isInit) {
			}
		});
});
