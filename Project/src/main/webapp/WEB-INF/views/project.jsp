<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
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
<title>Task</title>
</head>
<body>
   <c:url var="home" value="/home.do" />
   <a href="${home}">Home</a>
   <c:url var="projects" value="/project.do" />
   <a href="${projects}">Projects</a>
   <c:choose>
      <c:when test="${not empty PROJECT_LIST}">
         <c:if test="${not MEMBER.role.isDeveloper()}">
         <c:url var="projectNew" value="/projectNew.do" />
            <form action="${projectNew}" method="post">
              <input type="submit" value="New project">
            </form>
         </c:if>
         <table>
            <tr>
               <td>Project name</td>
               <td>Project description</td>
               <td>Status</td>
               <td>Team</td>
            </tr>
            <c:forEach var="project" items="${PROJECT_LIST}"
               varStatus="status">
               <tr>
                  <td><a href="#"
                     onClick="sendPost('/project/project.do','${project.id}')">${project.name}</a></td>
                  <td>${project.description}</td>
                  <td>${project.status.name}</td>
                  <td><a href="#"
                     onClick="sendPost('/project/member.do','${project.id}')">Members</a></td>
               </tr>
            </c:forEach>
         </table>
      </c:when>
      <c:when test="${not empty PROJECT}">
         <P>Project name:${PROJECT.name}</P>
         <c:if test="${not MEMBER.role.isDeveloper()}">
            <div>
               <input type="button"
                  onClick="sendPost('/project/taskNew.do','${PROJECT.id}')"
                  value="New task">
            </div>
         </c:if>   
         <c:choose>
            <c:when test="${not empty PROJECT_TASKS}">
               <a href="#"
                  onClick="sendPost('/project/member.do','${PROJECT.id}')">Team</a>
               <table>
                  <tr>
                     <td>Task description</td>
                     <td>Status</td>
                  </tr>
                  <c:forEach var="task" items="${PROJECT_TASKS}"
                     varStatus="status">
                     <tr>
                        <td><a href="#"
                           onClick="sendPost('/project/task.do','${task.id}')">${task.description}</a></td>
                        <td>${task.status.name}</td>
                     </tr>
                  </c:forEach>
               </table>
            </c:when>
            <c:otherwise>
               <p>No tasks</p>
            </c:otherwise>
         </c:choose>
      </c:when>
      <c:when test="${not empty ERROR}">
         <p class="error">${ERROR}</p>
      </c:when>
      <c:otherwise>
         <div>No projects</div>
      </c:otherwise>
   </c:choose>
</body>
</html>
