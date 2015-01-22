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
		<%-- employee not empty!This is controled by spring security filter--%>
		<tr>
			<td>
		<tr>
			<td><c:out value="${EMPLOYEE.position.name}" /></td>
		</tr>
		<tr>
			<td>
			   <c:out value="${EMPLOYEE.firstName} ${EMPLOYEE.lastName}" />
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
	</table>
	<div>
			<c:url var="project" value="/project.do" />
			<a href="${project}">Projects</a>
	</div>
	<div>
	   <c:if test="${not empty EMPLOYEE_ASSIGNMENT}">
		<table width="35%" border="solid 1 px">
			<tr>
				<td>Project name</td>
				<td>Task status</td>
				<td>Task description</td>
			</tr>
			<c:forEach var="assignment" items="${EMPLOYEE_ASSIGNMENT}"
				varStatus="status">
				<tr>
					<td><a href="#"
						onClick="sendPost('/project/project.do','${assignment.task.project.id}')">${assignment.task.project.name}</a>
					</td>
					<td>${assignment.task.status.name}</td>
					<td><a href="#"
						onClick="sendPost('/project/task.do','${assignment.task.id}')">${assignment.task.description}</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	   </c:if>
	   <input type="hidden" id="count" value="10">
		<table id="complete-table"  width="35%" border="solid 1 px" >
			<tr>
				<td>Last Activity</td>
				<td>Member</td>
				<td>Comment</td>
			</tr>
			<c:forEach var="activity" items="${LAST_ACTIVITY}" varStatus="status">
				<tr>
					<td>${activity.date}</td>
					<td>
					    ${activity.member.employee.firstName}
						${activity.member.employee.lastName}
					</td>
					<td>${activity.comment}</td>
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
