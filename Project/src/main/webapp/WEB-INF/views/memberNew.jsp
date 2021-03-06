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
<title>New member</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="row well">
		<c:if test="${not empty ERROR}">
			<div class="error">${ERROR}<br>
			</div>
		</c:if>
		<c:if test="${not empty PROJECT}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>
						Select members for project:<a href="#"
							onClick="sendPost('/project/project.do','${PROJECT.id}')">${PROJECT.name}</a>
					</h4>
				</div>
				<div class="panel-body">
				<form method="post" action="<c:url value='/memberAdd.do'/>">
					<input type="hidden" name="id" value="${PROJECT.id}" />
					<table class="table table-bordered">
					<thead>
						<tr>
							<td>Name</td>
							<td>Position</td>
							<td>Project role</td>
							<td>Select</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${EMPLOYEE_LIST}"
							varStatus="status">
							<input type="hidden" name="member_id[]" value="0" />
							<input type="hidden" name="employee_id[]" value="${employee.id}" />
							<%-- name="check"                selectOption() js --%>
							<input type="hidden" id="cb${employee.id}" name="check[]"
								value="false" />
							<tr>
								<td><c:out
										value="${employee.firstName} ${employee.lastName}" /></td>
								<td><c:out value="${employee.position.name}" /></td>
								<td><c:if test="${not empty ROLE_LIST}">
										<select name="role[]">
											<c:set var="r" value="${ROLE_LIST.get(0)}" />
											<option selected>${r.name}</option>
											<c:forEach var="role" items="${ROLE_LIST}">
												<c:if test="${role.name != r.name}">
													<option>${role.name}</option>
												</c:if>
											</c:forEach>
										</select>
									</c:if></td>
								<!-- How to find current hidden input for check -->
								<td><input type="checkBox"
									onClick="selectOption(this,'cb${employee.id}')" /></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<div>
						<input class="btn btn-default" type="submit" value="Add" /> 
						<input class="btn btn-default" type="button" onClick="window.history.go(-1)" value="Cancel"/>
					</div>
				</form>
				</div>
				</div>
		</c:if>
	</div>
</body>
</html>