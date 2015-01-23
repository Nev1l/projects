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
<title>Edit member</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
	<c:url var="home" value="/home.do" />
	<a href="${home}">Home</a>
	<div align="center">
		<c:if test="${not empty ERROR}">
			<div class="error">${ERROR}<br>
			</div>
		</c:if>
		<c:if test="${not empty PROJECT}">
			<div>
				Select members for project:<a href="#"
					onClick="sendPost('/project/project.do','${PROJECT.id}')">${PROJECT.name}</a>
			</div>
				<form method="post" action="<c:url value='/memberEdit.do'/>">
			    <input type="hidden" name="id" value="${PROJECT.id}" />
				<table border="solid 1px">
					<tr>
						<td>Name</td>
						<td>Position</td>
						<td>Project role</td>
						<td>Select</td>
					</tr>
					<c:forEach var="member" items="${PROJECT_MEMBERS}"	varStatus="status">
					  <input type="hidden" name="member_id[]" value="${member.id}" />
					  <input type="hidden" name="employee_id[]" value="${member.employee.id}" />
					  <input type="hidden" id="cb${member.employee.id}" name="check[]" value="false" />
					  <tr>
						<td><c:out value="${member.employee.firstName} ${member.employee.lastName}"/></td>
						<td><c:out value="${member.employee.position.name}"/></td>
						<td>
						    <c:if test="${not empty ROLE_LIST}">
								<select name="role[]">
									<c:set var="r" value="${member.role}" />
									<option selected>${r.name}</option>
									<c:forEach var="role" items="${ROLE_LIST}">
										<c:if test="${role.name != r.name}">
											<option>${role.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
						</td>
						<td><input type="checkBox" onClick="selectOption(this,'cb${member.employee.id}')"/></td>
					  </tr>
				   </c:forEach>
				</table>
				<div>
				    <input type="submit" value="Edit member list"/>
					<input type="button" onClick="sendPost('/project/project.do','${PROJECT.id}')" value="Cancel"/>
				</div>
				</form>
		</c:if>
	</div>
</body>
</html>