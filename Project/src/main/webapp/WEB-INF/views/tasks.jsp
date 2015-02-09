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
<title>Tasks</title>
</head>
<body>
	<%-- <c:when test="${empty TASK}">
				<p class="error">${ERROR}</p>
			</c:when> --%>
	<%@ include file="header.jsp"%>
	<div ng-app="app" class="row well" >
		<div class="span8" ng-controller="SetController">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Task</h4>
				</div>
				<div class="panel-body">
					<table class="table table-bordered">
						<thread>
						<tr>
							<td align="right">Assignee:</td>
							<td align="right">Project name:</td>
							<td align="right">Task description:</td>
							<td align="right">Status:</td>
						</tr>
						</thread>
						<tbody>
							<tr ng-repeat="assignee in assignees">
								<td>{{assignee.member.employee.firstName}}
									{{assignee.member.employee.lastName}}</td>
								<td><a href="#"
									onClick="sendPost('/project/project.do','{{assignee.task.project.id}}')">{{assignee.task.project.name}}</a></td>
								<td>{{assignee.task.description}}</td>
								<td>{{assignee.task.status.name}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>