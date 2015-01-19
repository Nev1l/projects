<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
 rel="stylesheet" type="text/css" />
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
  <!-- "${loginUrl}" "<c:url value="/j_spring_security_check" />" -->
  <form method="POST" name="loginForm" action="<c:url value='/j_spring_security_check' />">
   <spring:message code="label.login" />
   <input type="text" name="j_username" value="" required />
   <spring:message code="label.password" />
   <input type="password" name="j_password" value="" required /> 
   <spring:message code="label.remember" />
   <input type="checkbox" name="_spring_security_remember_me" /> 
   <input type="submit" style="margin-top: 10px" value="Login">
  </form>  
  <!-- <form method="POST"
   action="<c:url value="/j_spring_security_check" />">
   <table>
    <tr>
     <td align="right"><spring:message code="label.login" /></td>
     <td><input type="text" name="j_username" /></td>
    </tr>
    <tr>
     <td align="right"><spring:message code="label.password" /></td>
     <td><input type="password" name="j_password" /></td>
    </tr>
    <tr>
     <td colspan="2" align="right">
      <input type="submit" value="Login" />
     </td>
    </tr>
   </table>
  </form>  -->
 </div>

 <!-- footer -->
</body>
</html>
