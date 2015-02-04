<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<html>
<head>
<!-- <script data-require="angular.js@1.3.x"
	src="https://code.angularjs.org/1.3.10/angular.js" data-semver="1.3.10"></script> -->
<script data-require="angular.js@*" data-semver="1.3.4"
	src="https://code.angularjs.org/1.3.4/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div ng-app="app">
		<div ng-controller="Controller">
			<select ng-model="project" ng-options="c.name for c in projects"></select>
			<input type="text" ng-model="project" /> <select ng-model="member"
				ng-options="c.employee.firstName for c in members"></select> <input
				type="text" ng-model="member" /> <input type="button"
				ng-click="loadForm()" value="Button" />
		</div>
	</div>
	<div class="row well">
		<div class="span7">
			<c:choose>
				<c:when test="${not empty EMPLOYEE_ASSIGNMENT}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Assigned to me</h4>
						</div>
						<div id="light-pagination" class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Project name</th>
										<th>Task status</th>
										<th>Task description</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="assignment" items="${EMPLOYEE_ASSIGNMENT}"
										varStatus="status">
										<tr>
											<td><a href="#"
												onClick="sendPost('/project/project.do','${assignment.task.project.id}')">${assignment.task.project.name}</a>
											</td>
											<td>${assignment.task.status.name}</td>
											<td><a href="#"
												onClick="sendPost('/project/task.do','${assignment.task.id}')">${assignment.task.description}</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Assigned to me</h4>
						</div>
						<div class="panel-body">You haven't assigned tasks</div>
					</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${PAGE_NAVIGATOR.total>1}">
				<div class="page-bar">
					<c:set var="url" value="/project/home.do?page=" />
					<ul class="pagination">
						<c:if test="${PAGE_NAVIGATOR.hasPrev1()}">
							<li><a class="page-link"
								href="${url}${PAGE_NAVIGATOR.current-1}">Prev</a></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasPrev2()}">
							<li><a class="page-link"
								href="${url}${PAGE_NAVIGATOR.current-2}">${PAGE_NAVIGATOR.current-2}</a></li>
						</c:if>

						<c:if test="${PAGE_NAVIGATOR.hasPrev1()}">
							<li><a class="page-link"
								href="${url}${PAGE_NAVIGATOR.current-1}">${PAGE_NAVIGATOR.current-1}</a></li>
						</c:if>
						<c:if
							test="${PAGE_NAVIGATOR.hasPrev1() || PAGE_NAVIGATOR.hasPrev2() || PAGE_NAVIGATOR.hasNext1() || PAGE_NAVIGATOR.hasNext2()}">
							<li class="active"><span>${PAGE_NAVIGATOR.current}</span></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasNext1()}">
							<li><a class="page-link"
								href="${url}${PAGE_NAVIGATOR.current+1}">${PAGE_NAVIGATOR.current+1}</a></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasNext2()}">
							<li><a class="page-link"
								href="${url}${PAGE_NAVIGATOR.current+2}">${PAGE_NAVIGATOR.current+2}</a></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasNext1()}">
							<li><a class="page-link"
								href="${url}${PAGE_NAVIGATOR.current+1}">Next</a></li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
		<div class="span7">
			<input type="hidden" id="count" value="6" />
			<c:choose>
				<c:when test="${not empty LAST_ACTIVITY}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Activity stream</h4>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-advance table-hover">
								<tbody id="complete-table">
									<%-- remove id from table --%>
									<tr>
										<th><i class="icon_calendar"></i> Date</th>
										<th><i class="icon_profile"></i> Full Name</th>
										<th><i class="icon_cogs"></i> Action</th>
									</tr>
									<c:forEach var="activity" items="${LAST_ACTIVITY}"
										varStatus="status">
										<tr>
											<td>${activity.date}</td>
											<td><a href="#"
												onClick="sendPost('/project/userprofile.do','${activity.member.employee.id}')">${activity.member.employee.firstName}
													${activity.member.employee.lastName} </a></td>
											<td>${activity.comment}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<input class="btn btn-default" type="button" name="showMore"
						onClick="load()" value="Show more.." />
				</c:when>
				<c:otherwise>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Activity stream</h4>
						</div>
						<div class="panel-body">
							<div>Activity stream is empty</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- footer -->
</body>
</html>
