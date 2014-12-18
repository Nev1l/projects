<c:url var="logoutUrl" value='/logout.do' />
<form method="POST" name="loginForm" action="${logoutUrl}">
	<c:url value='/logout.do' />
	<input type="submit" value="Logout">
</form>