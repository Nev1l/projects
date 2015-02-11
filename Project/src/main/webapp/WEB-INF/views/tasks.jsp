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
	<div class="row well">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>List of tasks</h4>
			</div>
			<div class="panel-body">
				<table class="table table-bordered">
					<tr>
						<td><input id="taskName" class="form-control" type="text"
							value="${ftn}"></td>
						<td><c:if test="${not empty STATUS_LIST}">
								<select id="status" class="form-control m-bot15" name="status">
									<c:choose>
										<c:when test="${not empty fs}">
											<option>All</option>
											<option selected>${fs}</option>
										</c:when>
										<c:otherwise>
											<option selected>All</option>
										</c:otherwise>
									</c:choose>
									<c:forEach var="stat" items="${STATUS_LIST}">
										<c:if test="${stat.name != fs}">
											<option>${stat.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if></td>
						<td><c:if test="${not empty PROJECT_LIST}">
								<select id="project" class="form-control m-bot15" name="status">
									<c:choose>
										<c:when test="${not empty fp}">
											<option>All</option>
											<option selected>${fp}</option>
										</c:when>
										<c:otherwise>
											<option selected>All</option>
										</c:otherwise>
									</c:choose>
									<c:forEach var="project" items="${PROJECT_LIST}">
										<c:if test="${project.name != fp}">
											<option>${project.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if></td>
						<td><input class="btn btn-default" type="button"
							value="Search"
							onClick="sendTasksPageReq('${PAGE_NAVIGATOR.current}')" /></td>
					</tr>
					<thread>
					<tr>
						<td align="right"><b>Task description</b></td>
						<td align="right"><b>Status</b></td>
						<td align="right"><b>Project name</b></td>
						<td align="right"><b>Assignee</b></td>
					</tr>
					</thread>
					<tbody>
						<c:forEach var="assignee" items="${ASSIGNMENTS}">
							<c:set var="isOwner" value="" />
							<tr>
								<td><a href="#"
									onClick="sendPost('/project/task.do','${assignee.task.id}')">${assignee.task.description}</a></td>
								<td>${assignee.task.status.name}</td>
								<td><a href="#"
									onClick="sendPost('/project/project.do','${assignee.task.project.id}')">${assignee.task.project.name}</a></td>
								<td><a href="#"
									onClick="sendPost('/project/userprofile.do','${assignee.member.employee.id}')">${assignee.member.employee.firstName}
										${assignee.member.employee.lastName}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<c:if test="${PAGE_NAVIGATOR.total>1}">
				<div class="page-bar1">
					<ul class="pagination">
						<c:if test="${PAGE_NAVIGATOR.hasPrev1()}">
							<li><a class="page-link"
								href="#" onClick="sendTasksPageReq('${PAGE_NAVIGATOR.current-1}')">Prev</a></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasPrev2()}">
							<li><a class="page-link"
								href="#" onClick="sendTasksPageReq('${PAGE_NAVIGATOR.current-2}')">${PAGE_NAVIGATOR.current-2}</a></li>
						</c:if>

						<c:if test="${PAGE_NAVIGATOR.hasPrev1()}">
							<li><a class="page-link"
								href="#" onClick="sendTasksPageReq('${PAGE_NAVIGATOR.current-1}')">${PAGE_NAVIGATOR.current-1}</a></li>
						</c:if>
						<c:if
							test="${PAGE_NAVIGATOR.hasPrev1() || PAGE_NAVIGATOR.hasPrev2() || PAGE_NAVIGATOR.hasNext1() || PAGE_NAVIGATOR.hasNext2()}">
							<li class="active"><span>${PAGE_NAVIGATOR.current}</span></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasNext1()}">
							<li><a class="page-link"
								href="#" onClick="sendTasksPageReq('${PAGE_NAVIGATOR.current+1}')">${PAGE_NAVIGATOR.current+1}</a></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasNext2()}">
							<li><a class="page-link"
								href="#" onClick="sendTasksPageReq('${PAGE_NAVIGATOR.current+2}')">${PAGE_NAVIGATOR.current+2}</a></li>
						</c:if>
						<c:if test="${PAGE_NAVIGATOR.hasNext1()}">
							<li><a class="page-link"
								href="#" onClick="sendTasksPageReq('${PAGE_NAVIGATOR.current+1}')">Next</a></li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>