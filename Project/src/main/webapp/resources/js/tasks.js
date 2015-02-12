angular.module('app', [ 'ui.bootstrap.dialog' ]).factory('apiModal',
		function($http, $q) {
			return {
				openWindow : function($scope, $dialog, $window, apiModal) {
					alert("Open window");
					$scope.test = {
						name : 'OK'
					};
					var options = {
						backdrop : true,
						keyboard : true,
						controller : 'dialogModal',
						template : ':{{test.name}}',
					// templateUrl: 'view.html',
					};
					var dialog = $dialog.dialog(options);
					dialog.open().then(function(result) {

						if (result === 1) {
							apiModal.request();
						}
					});
				},
				request : function(data) {
					alert('send data ->AJAX');
					return false;
				}
			};
		}).controller(
		'Ctrl',
		function($scope, $dialog, $window, apiModal) {
			console.log("Ctrl start...");
			$scope.openWindow = function() {
				console.log("Ctrl open Window");
				apiModal.openWindow($scope, $dialog, $window, apiModal);
			};
			$scope.loadForm = function() {
				console.log("Ctrl load form data");
				$http.get("/project/ajax.projects.do").success(
						function(response) {
							$scope.projects = response;
							$scope.project = $scope.projects[0];
							$http.get(
									"/project/ajax.members.do?id="
											+ $scope.project.id).success(
									function(response) {
										$scope.members = response;
										$scope.member = $scope.members[0];
									});
						});
			};
		}).controller('dialogModal', function($scope, dialog) {
	$scope.close = function(result) {
		console.log("Ctrl close");
		dialog.close(result);
	};
});
