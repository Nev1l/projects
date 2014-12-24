<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<link href="${pageContext.request.contextPath}/resources/css/style.css"	rel="stylesheet" type="text/css" />
<html>
<head>
</head>
<body>
	<!-- header -->
	<%--<jsp:include page="login.jsp" flush="true" />--%>
	<div class="loginbox">
	<c:if test="${not empty errorMessage}">
		<p class="error">${errorMessage}</p>
	</c:if>
	<c:url var="loginUrl" value='/login.do' />
	<form method="POST" name="loginForm" action="${loginUrl}">
			<!-- <P>Login:</P> -->
			<spring:message code="label.login" />
			<input type="text" name="login" value="" required />
			<!-- <P>Password:</P>  -->
			<spring:message code="label.password" />
			<input type="password" name="password" value="" required /> 
			<input type="submit" style="margin-top:10px" value="Login">
	</form>
	</div>
	<!-- footer -->
</body>
</html>
