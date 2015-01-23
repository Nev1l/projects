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
  <c:choose>
    <c:when test="${empty curent_task}">
      <p class="error">${errorMessage}</p>
    </c:when>
    <c:otherwise>
      <table>
      	<tr>
      	  <td align="right">Assignee:</td>
          <td>${curent_task.project.name}</td>
        <tr>
          <td align="right">Project name:</td>
          <td>${curent_task.project.name}</td>
        </tr>
        <tr>
          <td align="right">Task description:</td>
          <td>${curent_task.description}</td>
        </tr>
        <tr>
          <td align="right">Status:</td>
          <td>${curent_task.status.name}</td>
        </tr>
        <tr>
          <td align="right">Planned date of start:</td>
          <td>${curent_task.plannedStartDate}</td>
        </tr>
        <tr>
          <td align="right">Planned date of end:</td>
          <td>${curent_task.plannedEndDate}</td>
        </tr>
        <tr>
          <td align="right">Actual date of start:</td>
          <td>${curent_task.actualStartDate}</td>
        </tr>
        <tr>
          <td align="right">Actual date of end:</td>
          <td>${curent_task.actualEndDate}</td>
        </tr>
      </table>
      <div>
      	<input type="button" value="Assignee" onClick="sendPost('/project/assignee.do','${assignment.task.id}')"/>
      	<input type="button" value="Activity" onClick="sendPost('/project/activity.do','${assignment.task.id}')"/>
      </div>
      <c:if test="${curent_member.role.isDeveloper()}">
        <form action="/project/taskAdd.do" method="post">
          <input type="submit" value="New task">
        </form>
      </c:if>
    </c:otherwise>
  </c:choose>
</body>
</html>