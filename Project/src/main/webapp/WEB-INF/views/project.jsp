<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="row well">
		<c:choose>
			<%-- project list available only for admin --%>
			<c:when test="${not empty PROJECT_LIST}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Project list</h4>
					</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>Project name</th>
									<th>Project description</th>
									<th>Status</th>
									<th>Team</th>
									<%-- <c:if test="${EMPLOYEE.position.isAdmin()}"> </c:if>--%>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="project" items="${PROJECT_LIST}"
									varStatus="status">
									<tr>
										<td><a href="#"
											onClick="sendPost('/project/project.do','${project.id}')">${project.name}</a></td>
										<td>${project.description}</td>
										<td>${project.status.name}</td>
										<td><a href="#"
											onClick="sendPost('/project/member.do','${project.id}')">Members</a></td>
										<%-- role > developer --%>
										<c:if test="${EMPLOYEE.position.isAdmin()}">
											<td><input type="button" class="btn btn-default"
												value="Change"
												onClick="sendPost('/project/projectUpdate.do','${project.id}')" />
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<c:if test="${EMPLOYEE.position.isAdmin()}">
							<c:url var="projectNew" value="/projectNew.do" />
							<form action="${projectNew}" method="post">
								<input class="btn btn-default" type="submit" value="New project">
							</form>
						</c:if>
					</div>
				</div>
			</c:when>
			<%-- member list(with roles on projects) available for ALL --%>
			<c:when test="${not empty MEMBER_LIST}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Project list</h4>
					</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>Project name</th>
									<th>Project description</th>
									<th>Status</th>
									<th>Team</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="member" items="${MEMBER_LIST}"
									varStatus="status">
									<tr>
										<td><a href="#"
											onClick="sendPost('/project/project.do','${member.project.id}')">${member.project.name}</a></td>
										<td>${member.project.description}</td>
										<td>${member.project.status.name}</td>
										<td><a href="#"
											onClick="sendPost('/project/member.do','${member.project.id}')">Members</a></td>
										<td><c:choose>
												<%--by default member is not null (because this is element of list)--%>
												<c:when test="${not member.role.isDeveloper()}">
													<input type="button" class="btn btn-default" value="Change"
														onClick="sendPost('/project/projectUpdate.do','${member.project.id}')" />
												</c:when>
												<c:otherwise>
								   	No available
							    </c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:when>
			<%-- if user send id of project VIEW PROJECT WITH ID --%>
			<c:when test="${not empty PROJECT}">
				<c:set var="hasMemberAccess" value="false" />
				<c:if test="${not empty MEMBER}">
					<c:if test="${not MEMBER.role.isDeveloper()}">
						<c:set var="hasMemberAccess" value="true" />
					</c:if>
				</c:if>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							Project name <span class="project">${PROJECT.name}</span>
						</h4>
						<a href="#"
							onClick="sendPost('/project/member.do','${PROJECT.id}')">Team
						</a>
					</div>
					<div class="panel-body">
						<c:choose>
							<c:when test="${not empty PROJECT_TASKS}">
								<table class="table table-condensed">
									<thead>
										<tr>
											<th>Task description</th>
											<th>Status</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="task" items="${PROJECT_TASKS}"
											varStatus="status">
											<tr>
												<td><a href="#"
													onClick="sendPost('/project/task.do','${task.id}')">${task.description}</a>
												</td>
												<td>${task.status.name}</td>
												<td><c:choose>
														<c:when test="${hasMemberAccess}">
															<input class="btn btn-default" type="button"
																onClick="sendPost('/project/taskUpdate.do','${task.id}')"
																value="Change">
														</c:when>
														<c:otherwise>
											No available
										</c:otherwise>
													</c:choose></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<c:if test="${hasMemberAccess}">
									<div>
										<input size="25" class="btn btn-default" type="button"
											onClick="sendPost('/project/taskNew.do','${PROJECT.id}')"
											value="New task">
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<p>No tasks</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:when>
			<c:when test="${not empty ERROR}">
				<p class="error">${ERROR}</p>
			</c:when>
			<c:otherwise>
				<div>No projects</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
