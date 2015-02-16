<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>New task</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="col-lg-4"></div>
	<div class="col-lg-5">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>Create new task</h4>
			</div>
			<div class="panel-body">
				<c:if test="${not empty ERROR}">
					<div class="error">${ERROR}<br>
					</div>
				</c:if>
				<c:if test="${not empty PROJECT}">
					<c:set var="assign_member_value" value="(Automatic)" />
					<c:set var="summary" value="${ASSIGNEE.description}" />
					<c:if test="${not empty TASK}">
						<c:set var="psd" value="${TASK.plannedStartDate}" />
						<c:set var="ped" value="${TASK.plannedEndDate}" />
						<c:set var="asd" value="${TASK.actualStartDate}" />
						<c:set var="aed" value="${TASK.actualEndDate}" />
						<c:set var="description" value="${TASK.description}" />
						<c:set var="st" value="${TASK.status}" />
					</c:if>
					<form class="form-horizontal" method="POST"
						action="<c:url value='/taskAdd.do'/>">
						<input id="assignee" type="hidden" name="assign_member_id"
							value="${MEMBER_ID}" /> <input type="hidden" name="id"
							value="${PROJECT.id}" />
						<table class="table-responsive">
							<tbody>
								<tr class="form-group">
									<th class="control-label col-lg-5">Name of project</th>
									<td class="col-lg-12"><a href="#"
										onclick="sendPost('/project/project.do','${PROJECT.id}')">${PROJECT.name}</a>
									<td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Assignee</td>
									<td class="col-lg-10"><c:if
											test="${not empty PROJECT_MEMBERS}">
											<select class="form-control m-bot15" id="selectAssigneeBox"
												onchange="selectAssignee();">
												<option value="${MEMBER_ID}" selected>${assign_member_value}</option>
												<c:forEach var="member" items="${PROJECT_MEMBERS}">
													<option value="${member.id}">${member.employee.firstName}
														${member.employee.lastName}</option>
												</c:forEach>
											</select>
										</c:if></td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Summary</td>
									<td class="col-lg-12"><input class="form-control" type="text" size="30"
										name="summary" value="${summary}" />
									<td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Planned Start Date</td>
									<!-- clear for date input-->
									<td class="col-lg-12"><input class="form-control"
										name="psd" type="text" size="30" value="${psd}" required></td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Planned End Date</td>
									<!-- clear for date input-->
									<td class="col-lg-12"><input class="form-control"
										name="ped" type="text" value="${ped}" required></td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Actual Start Date</td>
									<!-- clear for date input-->
									<td class="col-lg-12"><input class="form-control"
										name="asd" type="text" value="${asd}" required></td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Actual End Date</td>
									<!-- clear for date input-->
									<td class="col-lg-12"><input class="form-control"
										name="aed" type="text" value="${aed}" required></td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Description</td>
									<td class="col-lg-12"><textarea
											class="form-control  m-bot15" name="description" rows="5"
											required cols="24"><c:out value="${description}" /></textarea></td>
								</tr>
								<tr class="form-group">
									<td class="control-label col-lg-5">Status</td>
									<td class="col-lg-10"><c:if
											test="${not empty STATUS_LIST}">
											<select class="form-control m-bot5" name="status">
												<c:if test="${empty st}">
													<c:set var="st" value="${STATUS_LIST.get(0)}" />
												</c:if>
												<option selected>${st.name}</option>
												<c:forEach var="stat" items="${STATUS_LIST}">
													<c:if test="${stat.name != st.name}">
														<option>${stat.name}</option>
													</c:if>
												</c:forEach>
											</select>
										</c:if></td>
								</tr>
								<tr>
									<td></td>
									<td>
										<div class="col-lg-4">
											<input class="btn btn-default" type="submit" value="Create">
										</div>
										<div class="col-lg-3">
											<input class="btn btn-default" type="button"
												onClick="window.history.go(-1)" value="Cancel" />
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>