<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="row well">
		<div class="span7">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>User profile</h4>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-advance table-hover">
						<tbody id="complete-table">
							<%-- remove id from table --%>
							<tr>
								<th><i class="icon_profile"></i> Full Name</th>
								<td>${EMPLOYEE_PROFILE.firstName}
									${EMPLOYEE_PROFILE.lastName}</td>
							</tr>
							<tr>
								<th><i class="icon_cogs"></i> Position</th>
								<td>${EMPLOYEE_PROFILE.position.name}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="span8">
			<c:choose>
				<c:when test="${not empty EMPLOYEE_ASSIGNMENT}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Assigned to ${EMPLOYEE_PROFILE.firstName}
								${EMPLOYEE_PROFILE.lastName}</h4>
						</div>
						<div class="panel-body">
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
							<c:if test="${PAGE_NAVIGATOR.total>1}">
							    <div class="page-bar">
								<c:set var="url"
									value="/project/userprofile.do?id=${EMPLOYEE_PROFILE.id}&page=" />
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
					</div>
				</c:when>
				<c:otherwise>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Assigned to ${EMPLOYEE_PROFILE.firstName}
								${EMPLOYEE_PROFILE.lastName}</h4>
						</div>
						<div class="panel-body">User haven't assigned tasks</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>