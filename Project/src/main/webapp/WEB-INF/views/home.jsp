<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<link href="${pageContext.request.contextPath}/resources/css/style.css"	rel="stylesheet" type="text/css" />
<html>
<head>
<title>Home</title>
</head>
<body>
	<!-- header -->
	<%--<jsp:include page="login.jsp" flush="true" />--%>
	<h1>HOME</h1>
	<p>Dashboards</p>
	<table>
	
		<tr>
			<td></td>
			<td>
				<c:if test="${not empty errorMessage}">
					<p class="error">${errorMessage}</p>	
				</c:if>
			</td>
			<td>
			<c:if test="${not empty curent_employee}">
				<c:url var="role" value='/role.do' />
				<c:url var="member" value='/member.do' />
				<c:url var="project" value='/project.do' />
				<c:url var="assignment" value='/assignment.do' />
				<c:if test="${curent_employee.position.isAdmin()}">
					<a href="${member}">Member</a>
					<a href="${project}">Project</a>
					<a href="${assignment}">Assignment</a>
				</c:if>
			</c:if>
			</td>
			<td width="15%">
				<c:choose>
					<c:when test="${not empty curent_employee}">
						<c:out value="${curent_employee}"></c:out>
						<c:url var="logoutUrl" value='/logout.do' />
						<form method="POST" name="loginForm" action="${logoutUrl}">
							<input type="submit" value="Logout">
						</form>
					</c:when>
					<c:otherwise>
						<c:out value="${curent_employee}"></c:out>
						<form method="POST" name="loginForm" action="<c:url value="/j_spring_security_check" />">
							<spring:message code="label.login" />
							<input type="text" name="j_username" value="" required />
							<spring:message code="label.password" />
							<input type="password" name="j_password" value="" required /> 
							<input type="submit" style="margin-top: 10px" value="Login">
						</form>  
					</c:otherwise>
				</c:choose> 
			</td>
		</tr>
		<c:forEach var="project" items="${projects}" varStatus="status">
			<tr>
				<td>
					<%-- <a href="qweqwe('${project.id}')">${project.name}</a> --%>
				</td>
				<td>${project.plannedStartDate}</td>
				<td>${project.plannedEndDate}</td>
				<td>${project.actualStartDate}</td>
				<td>${project.actualEndDate}</td>
				<td>${project.status.name}</td>
			</tr>
		</c:forEach>
	</table>
	<p>Role ${obj}</p>
	<p>Connect message ${connectMessage}</p>
	<!-- footer -->
</body>
</html>
