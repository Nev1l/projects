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
	<%@ include file="header.jsp"%>
	<div class="row well">
		<c:choose>
			<c:when test="${empty TASK}">
				<p class="error">${ERROR}</p>
			</c:when>
			<c:otherwise>
				<div class="span8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Task</h4>
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<tbody>
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
								</tbody>
							</table>
							<div>
							<a href="#" class="btn btn-default"
									onClick="sendPost('/project/task.do','${TASK.id}')">Change</a>
								<input class="btn btn-default" type="button" value="Activity"
									onClick="sendPost('/project/activity.do','${ASSIGNEE.task.id}')" />
							</div>
						</div>
					</div>
				</div>
				<div class="span7">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Attachment</h4>
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>File name</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td></td>
									</tr>
								</tbody>
							</table>
							<form method="POST" enctype="multipart/form-data"
								action="<c:url value="/fileupload.do"/>">
								<input type="hidden" name="task_id" value="${TASK.id}" />
								<table class="table">
									<tbody>
										<tr class="form-group">
											<td><input id="exampleInputFile" type="file" name="file" required></td>
											<td><input class="btn btn-default" type="submit"
												value="Upload">
										</tr>
										<tr class="form-group">
											<td colspan="2"><input class="form-control" type="text"
												name="description" value="" placeholder="description"
												required /></td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>