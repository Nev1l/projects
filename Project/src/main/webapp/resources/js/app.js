var tasks = angular.module('app', [ 'ui.bootstrap','ui.bootstrap.dialog' ]);
tasks.controller('Ctrl', function($scope, $http) {
	$scope.isInit = false;
	$scope.loadForm = function() {
		$http.get("/project/ajax.tasks.do").success(function(response) {
			$scope.tasks = response;
			$scope.project = $scope.projects[0];
			$scope.isInit = true;
		});
	};
	$scope.$watch('project', function() {
		if ($scope.isInit) {
			$http.get("/project/ajax.members.do?id=" + $scope.project.id)
					.success(function(response) {
						$scope.members = response;
						$scope.member = $scope.members[0];
					});
		}
	});
});
