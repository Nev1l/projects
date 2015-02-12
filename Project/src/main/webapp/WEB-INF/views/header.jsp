
<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/app.js">
	tasks.js
</script>
<div ng-app="app" ng-controller="Ctrl" class="navbar">
	<div class="navbar-inner">
		<div class="brand">Tracking system</div>
		<ul class="nav">
			<li><a href="<c:url value="/home.do"/>">Dashboard</a></li>
			<li><a href="<c:url value="/project.do"/>">Projects</a></li>
			<%--ng-init="loadForm()"  href="<c:url value="/tasks.do"/>" 
			<button href="#" ng-click="loadForm()">Issues
						;openWindow()</button>--%>
			<c:if test="${hasAccessCreateGlobalTask}">
				<li><button type="button" data-toggle="modal"
						data-target="#myModal" class="btn btn-default">Issues</button></li>
			</c:if>
			<li><a href="<c:url value="/logout.do"/>">Logout</a></li>
		</ul>
	</div>
	<%-- MODAL FORM PART1 style="display: none"--%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<div>
						<div>
							<select ng-model="project" ng-options="c.name for c in projects"></select>
							<select ng-model="member"
								ng-options="c.employee.firstName + ' ' + c.employee.lastName for c in members"></select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Save changes</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<%-- MODAL FORM PART2--%>

</div>
