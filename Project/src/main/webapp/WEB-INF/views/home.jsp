<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
  rel="stylesheet" type="text/css" />
<html>
<head>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<title>Home</title>
</head>
<body>
  <!-- header -->
  <%--<jsp:include page="login.jsp" flush="true" />--%>
  <h1>HOME</h1>
  <p>Dashboards</p>
  <table width="15%">
    <tr>
      <td><c:choose>
          <c:when test="${not empty MEMBER}">
            <tr>
              <td><c:out value="${MEMBER.employee.position.name}" />
              </td>
            </tr>
            <tr>
              <td><c:out
                  value="${MEMBER.employee.firstName} ${MEMBER.employee.lastName}" />
              </td>
            </tr>
            <tr>
              <td>
                <form method="POST" name="loginForm"
                  action="<c:url value="/logout.do"/>">
                  <input type="submit" value="Logout">
                </form>
              </td>
            </tr>
          </c:when>
          <c:otherwise>
            <%-- no auth --%>
          </c:otherwise>
        </c:choose></td>
    </tr>
  </table>
  <div>
    <c:if test="${not empty MEMBER}">
      <c:url var="project" value="/project.do" />
      <a href="${project}">Projects</a>
      <c:if test="${not MEMBER.role.isDeveloper()}">
      </c:if>
    </c:if>
  </div>
  <div>
    <table width="35%" border="solid 1 px">
      <tr>
        <td>Project name</td>
        <td>Assignment description</td>
        <td>Task description</td>
      </tr>
      <c:forEach var="assignment" items="${EMPLOYEE_ASSIGNMENT}"
        varStatus="status">
        <tr>
          <td><a href="#"
            onClick="sendPost('/project/project.do','${assignment.task.project.id}')">${assignment.task.project.name}</a>
          </td>
          <td>${assignment.description}</td>
          <td><a href="#"
            onClick="sendPost('/project/task.do','${assignment.task.id}')">${assignment.task.description}</a>
          </td>
        </tr>
      </c:forEach>
    </table>
    <table width="35%" border="solid 1 px">
      <tr>
        <td>Last Activity</td>
        <td>Member</td>
        <td>Comment</td>
        <td>Task description</td>
      </tr>
      <c:forEach var="activity" items="${LAST_ACTIVITY}" varStatus="status">
        <tr>
          <td>${activity.date}</td>
          <td>${activity.assignment.member.employee.firstName}
            ${activity.assignment.member.employee.lastName}</td>
          <td>${activity.comment}</td>
          <td>${activity.assignment.task.description}</td>
        </tr>
      </c:forEach>         
    </table>
    <input type="button" name="showMore" onClick="load()"
      value="Show more.." />
  </div>
  <%-- <p> ${sessionScope}</p> --%>
  <!-- footer -->
</body>
</html>
