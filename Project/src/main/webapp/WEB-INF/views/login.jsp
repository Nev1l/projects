<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<html>
<head>
<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/signin.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<c:if test="${not empty ERROR}">
			<p class="error">${ERROR}</p>
		</c:if>
		<form method="POST" name="loginForm" class="form-signin"
			action="<c:url value='/j_spring_security_check' />">
			<h4 class="form-signin-heading">Please sign in</h4>
			<input type="text" id="inputLogin" name="j_username"
				class="form-control" placeholder="Login" required autofocus>
			<input type="password" id="inputPassword" name="j_password"
				class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox"
					name="_spring_security_remember_me" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-default" type="submit">Sign in</button>
		</form>
	</div>
	<!-- footer -->
</body>
</html>
