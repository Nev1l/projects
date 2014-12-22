<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<html>
<head>
</head>
<body>
	<!-- header -->
	<%--<jsp:include page="login.jsp" flush="true" />--%>
	<div style="margin-left:40%;margin-top:25%;margin-right:40%;border:solid;padding:20px">
	<c:if test="${not empty errorMessage}">
		<p>${errorMessage}</p>
	</c:if>
	<c:url var="loginUrl" value='/login.do' />
	<form method="POST" name="loginForm" action="${loginUrl}">
			<P>Login:</P>
			<input type="text" name="login" value="" required />
			<P>Password:</P> 
			<input type="password" name="password" value="" required /> 
			<input type="submit" style="margin-top:10px" value="Login">
	</form>
	</div>
	<!-- footer -->
</body>
</html>
