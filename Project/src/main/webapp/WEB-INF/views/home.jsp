<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<!-- header -->
	<%--<jsp:include page="login.jsp" flush="true" />--%>
	<h1>Hello world!</h1>
	<p>Dashboards</p>
	<table>
		<tr>
			<td></td>
			<td></td>
			<td width="20%">
				<c:if test="${employee.role.isAdmin()}">
				</c:if>
				<c:url var="loginUrl" value='/login.do' />
					<form method="POST" name="loginForm" action="${loginUrl}">
						<input type="text" name="login" value="" required /> <input
							type="password" name="password" value="" required /> <input
							type="submit" value="Login">
					</form>
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
	<p>Connect message ${connectMessage}</p>
	<!-- footer -->
</body>
</html>
