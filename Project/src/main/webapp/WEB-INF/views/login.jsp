<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
   rel="stylesheet" type="text/css" />
</head>
<body>
   <jsp:include page="header.jsp"/>
   <div class="loginbox">
      <c:if test="${not empty ERROR}">
         <p class="error">${ERROR}</p>
      </c:if>
      <c:url var="loginUrl" value='/login.do' />
      <!-- "${loginUrl}" "<c:url value="/j_spring_security_check" />" -->
      <form method="POST" name="loginForm"
         action="<c:url value='/j_spring_security_check' />">
         <spring:message code="label.login" />
         <input type="text" name="j_username" value="" required />
         <spring:message code="label.password" />
         <input type="password" name="j_password" value="" required />
         <spring:message code="label.remember" />
         <input type="checkbox" name="_spring_security_remember_me" /> <input
            type="submit" style="margin-top: 10px" value="Login">
      </form>
   </div>
   <!-- footer -->
</body>
</html>
