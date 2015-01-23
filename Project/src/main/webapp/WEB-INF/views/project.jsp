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
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
	<c:url var="home" value="/home.do" />
	<a href="${home}">Home</a>
	<c:url var="projects" value="/project.do" />
	<a href="${projects}">Projects</a>
	<c:choose>
		<%-- project list available only for admin --%>
		<c:when test="${not empty PROJECT_LIST}">
			<c:if test="${EMPLOYEE.position.isAdmin()}">
				<c:url var="projectNew" value="/projectNew.do" />
				<form action="${projectNew}" method="post">
					<input type="submit" value="New project">
				</form>
			</c:if>
			<table>
				<tr>
					<td>Project name</td>
					<td>Project description</td>
					<td>Status</td>
					<td>Team</td>
					<%-- <c:if test="${EMPLOYEE.position.isAdmin()}"> </c:if>--%>
					<td>Actions</td>
				</tr>
				<c:forEach var="project" items="${PROJECT_LIST}" varStatus="status">
					<tr>
						<td><a href="#"
							onClick="sendPost('/project/project.do','${project.id}')">${project.name}</a></td>
						<td>${project.description}</td>
						<td>${project.status.name}</td>
						<td><a href="#"
							onClick="sendPost('/project/member.do','${project.id}')">Members</a></td>
						<%-- role > developer --%>
						<c:if test="${EMPLOYEE.position.isAdmin()}">
							<td><input type="button" value="Change"
								onClick="sendPost('/project/projectUpdate.do','${project.id}')" />
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<%-- member list(with roles on projects) available for ALL --%>
		<c:when test="${not empty MEMBER_LIST}">
			<table>
				<tr>
					<td>Project name</td>
					<td>Project description</td>
					<td>Status</td>
					<td>Team</td>
					<td>Actions</td>
				</tr>
				<c:forEach var="member" items="${MEMBER_LIST}" varStatus="status">
					<tr>
						<td><a href="#"
							onClick="sendPost('/project/project.do','${member.project.id}')">${member.project.name}</a></td>
						<td>${member.project.description}</td>
						<td>${member.project.status.name}</td>
						<td><a href="#"
							onClick="sendPost('/project/member.do','${member.project.id}')">Members</a></td>
						<td><c:choose>
								<c:when test="${not member.role.isDeveloper()}">
									<input type="button" value="Change"
										onClick="sendPost('/project/projectUpdate.do','${member.project.id}')" />
								</c:when>
								<c:otherwise>
						   	No available
						   </c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<%-- if user send id of project VIEW PROJECT WITH ID --%>
		<c:when test="${not empty PROJECT}">
			<%-- <c:choose>
				<c:when test="${EMPLOYEE.position.isAdmin()}">
					<c:set var="hasAccess" value="true" />
				</c:when>
				<c:when test="${not empty MEMBER}">
					<c:if test="${not MEMBER.role.isDeveloper()}">
						<c:set var="hasAccess" value="true" />
					</c:if>
				</c:when>
				<c:otherwise>
					<c:set var="hasAccess" value="false" />
				</c:otherwise>
			</c:choose> --%>
			<c:set var="hasMemberAccess" value="false" />
			<c:if test="${not empty MEMBER}">
				<c:if test="${not MEMBER.role.isDeveloper()}">
					<c:set var="hasMemberAccess" value="true" />
				</c:if>
			</c:if>
			<P>Project name:${PROJECT.name}</P>
			<c:if test="${hasMemberAccess}">
				<div>
					<input size="25" type="button"
						onClick="sendPost('/project/taskNew.do','${PROJECT.id}')"
						value="New task">
				</div>
			</c:if>
			<c:if test="${hasMemberAccess}">
				<div>
					<input size="25" type="button"
						onClick="sendPost('/project/memberNew.do','${PROJECT.id}')"
						value="New member">
				</div>
			</c:if>
			<a href="#" onClick="sendPost('/project/member.do','${PROJECT.id}')">Team</a>
			<c:choose>
				<c:when test="${not empty PROJECT_TASKS}">
					<table>
						<tr>
							<td>Task description</td>
							<td>Status</td>
							<td>Actions</td>
						</tr>
						<c:forEach var="task" items="${PROJECT_TASKS}" varStatus="status">
							<tr>
								<td><a href="#"
									onClick="sendPost('/project/task.do','${task.id}')">${task.description}</a>
								</td>
								<td>${task.status.name}</td>
								<td>
									<c:choose>
										<c:when test="${hasMemberAccess}">
											<input type="button"
												onClick="sendPost('/project/taskUpdate.do','${task.id}')"
												value="Change">
										</c:when>
										<c:otherwise>
											No available
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p>No tasks</p>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:when test="${not empty ERROR}">
			<p class="error">${ERROR}</p>
		</c:when>
		<c:otherwise>
			<div>No projects</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
