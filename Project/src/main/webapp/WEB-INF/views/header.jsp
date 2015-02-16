<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />
<script
	src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.0.js"></script>
<link
	href="${pageContext.request.contextPath}/resources/css/elegant-icons-style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mystyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<%-- 
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/app.js">
</script> --%>
<div class="navbar">
	<div class="navbar-inner">
		<div class="brand">Tracking system</div>
		<ul class="nav">
			<li><a href="<c:url value="/home.do"/>">Dashboard</a></li>
			<li><a href="<c:url value="/project.do"/>">Projects</a></li>
			<c:if test="${hasAccessCreateGlobalTask}">
				<li><a href="<c:url value="/tasks.do"/>">Issues</a></li>
			</c:if>
			<li>
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target=".bs-example-modal-lg">Create new tas</button>
			</li>
			<li><a href="#"
				onClick="sendPost('/project/userprofile.do','${EMPLOYEE.id}')">Profile
			</a></li>
			<li><a href="<c:url value="/logout.do"/>">Logout</a></li>
		</ul>
	</div>
	<!-- Modal -->
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div ng-app="app" ng-controller="Ctrl">
					<select ng-model="project" ng-options="c.name for c in projects"></select>
					<select ng-model="member"
						ng-options="c.employee.firstName + ' ' + c.employee.lastName for c in members"></select>
				</div>
			</div>
		</div>
	</div>
</div>
