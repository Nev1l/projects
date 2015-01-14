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
	<c:url var="project" value="/project.do" />
	<a href="${project}">Projects</a>
	<c:choose>
		<c:when test="${empty TASK}">
			<p class="error">${ERROR}</p>
		</c:when>
		<c:otherwise>
		    <a href="#" onclick="sendPost('/project/project.do','${TASK.project.id}')">Task List</a>
			<table>
				<c:set var="assignee" value="No assignee" />
				<c:if test="${not empty ASSIGNEE}">
					<c:set var="assignee"
						value="${ASSIGNEE.member.employee.firstName} ${ASSIGNEE.member.employee.lastName}" />
				</c:if>
				<tr>
					<td align="right">Assignee:</td>
					<td>${assignee}</td>
				</tr>
				<tr>
					<td align="right">Project name:</td>
					<td><a href="#"
						onClick="sendPost('/project/project.do','${TASK.project.id}')">${TASK.project.name}</a></td>
				</tr>
				<tr>
					<td align="right">Task description:</td>
					<td>${TASK.description}</td>
				</tr>
				<tr>
					<td align="right">Status:</td>
					<td>${TASK.status.name}</td>
				</tr>
				<tr>
					<td align="right">Planned date of start:</td>
					<td>${TASK.plannedStartDate}</td>
				</tr>
				<tr>
					<td align="right">Planned date of end:</td>
					<td>${TASK.plannedEndDate}</td>
				</tr>
				<tr>
					<td align="right">Actual date of start:</td>
					<td>${TASK.actualStartDate}</td>
				</tr>
				<tr>
					<td align="right">Actual date of end:</td>
					<td>${TASK.actualEndDate}</td>
				</tr>
			</table>
			<div>
				<input type="button" value="Reassignee"
					onClick="sendPost('/project/assignee.do','${ASSIGNEE.task.id}')" />
				<input type="button" value="Activity"
					onClick="sendPost('/project/activity.do','${ASSIGNEE.task.id}')" />
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>