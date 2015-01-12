<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<title>Member</title>
</head>
<body>
 <c:choose>
 <c:when test="${not empty PROJECT}">
 <div>Team of project:<a href="#" onClick="sendPost('/project/project.do','${PROJECT.id}')">${PROJECT.name}</a>:</div>
   <c:if test="${not MEMBER.role.isDeveloper()}">
   	<button onClick="sendPost('/project/memberNew.do','${PROJECT.id}')">New member</button>
   </c:if>
 <c:if test="${not empty PROJECT_MEMBERS}">
     <c:choose>
       <c:when test="${EMPLOYEE.position.isAdmin()}">
           <c:set var="hasAccess" value="true"/>
       </c:when>
       <c:when test="${not empty MEMBER}">
          <c:if test="${not MEMBER.isDeveloper()}">
          	<c:set var="hasAccess" value="true"/>
          </c:if>
       </c:when>
       <c:otherwise>
           <c:set var="hasAccess" value="false"/>
       </c:otherwise>
    </c:choose>
 <table border="solid 1 px">
  <tr>
   <td>Name</td>
   <td>Position</td>
   <c:if test="${hasAccess}">
     <td>Selected</td>
     <td>Actions</td>
   </c:if>
  </tr>
  <c:forEach var="member" items="${PROJECT_MEMBERS}" varStatus="status">
   <tr>
    <td>${member.employee.firstName} ${member.employee.lastName}</td>
    <td>${member.role.name}</td>
    <c:if test="${hasAccess}">
    <td>
      <input type="button" value="Change" onClick="sendPost('/project/memberEdit.do','${project.id}')"/>
      <input type="button" value="Delete" onClick="sendPost('/project/memberDelete.do','${project.id}')"/>
    </td>
    </c:if>
   </tr>
  </c:forEach>
  </table>
  </c:if>
 </c:when>
 <c:otherwise>
    <p class="error">${ERROR}</p>
 </c:otherwise>
 </c:choose> 
</body>
</html>