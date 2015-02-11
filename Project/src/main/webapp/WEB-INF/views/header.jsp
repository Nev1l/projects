
<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />
<link
	href="${pageContext.request.contextPath}/resources/css/elegant-icons-style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mystyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<div class="navbar">
	<div class="navbar-inner">
		<div class="brand">Tracking system</div>
		<ul class="nav">
			<li><a href="<c:url value="/home.do"/>">Dashboard</a></li>
			<li><a href="<c:url value="/project.do"/>">Projects</a></li>
			<c:if test="${hasAccessCreateGlobalTask}">
				<li><a href="<c:url value="/tasks.do"/>">Issues</a></li>
			</c:if>
			<li><a href="<c:url value="/logout.do"/>" ng-click="loadForm()">Logout</a></li>
		</ul>
	</div>
	<%-- MODAL FORM --%>
	<div ng-app="app" style="display:none">
		<div ng-controller="SetController">
			<select ng-model="project" ng-options="c.name for c in projects"></select>
			<select ng-model="member"
				ng-options="c.employee.firstName + ' ' + c.employee.lastName for c in members"></select>
		</div>
	</div>
</div>
