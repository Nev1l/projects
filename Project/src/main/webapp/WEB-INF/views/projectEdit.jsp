<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Change project</title>
</head>
<body>
   <jsp:include page="header.jsp"/>
   <c:url var="home" value="/home.do" />
   <a href="${home}">Home</a>
   <c:url var="projects" value="/project.do" />
	<div align="center">
		<c:if test="${not empty ERROR}">
		   <div class="error">${ERROR}<br></div>
		</c:if>
		<c:set var="id" value=""/>
		<c:set var="name" value=""/>
		<c:set var="psd" value=""/>
		<c:set var="ped" value=""/>
		<c:set var="asd" value=""/>
		<c:set var="aed" value=""/>
		<c:set var="description" value=""/>
		<c:set var="st" value=""/>
		<c:if test="${not empty PROJECT}">
		   <c:set var="id" value="${PROJECT.id}"/>
		   <c:set var="name" value="${PROJECT.name}"/>
		   <c:set var="psd" value="${PROJECT.plannedStartDate}"/>
		   <c:set var="ped" value="${PROJECT.plannedEndDate}"/>
		   <c:set var="asd" value="${PROJECT.actualStartDate}"/>
		   <c:set var="aed" value="${PROJECT.actualEndDate}"/>
		   <c:set var="description" value="${PROJECT.description}"/>
		   <c:set var="st" value="${PROJECT.status}"/>
		</c:if>
		<form  method="POST"
			action="<c:url value='/projectEdit.do'/>">
			<input name="id" type="hidden" size="30" value="${id}"/>
			Edit project<br>
			<table>
				<tr>
					<td>Name of project:</td>
					<td><input name="name" type="text" size="30" value="${name}" required>
					</td>
				</tr>
				<tr>
					<td>Planned Start Date:</td>
					<!-- clear for date input-->
					<td><input name="psd" type="text" size="30" value="${psd}" required>
					</td>
				</tr>
				<tr>
					<td>Planned End Date:</td>
					<!-- clear for date input-->
					<td><input name="ped" type="text" size="30" value="${ped}" required>
					</td>
				</tr>
				<tr>
					<td>Actual Start Date:</td>
					<!-- clear for date input-->
					<td><input name="asd" type="text" size="30" value="${asd}" required>
					</td>
				</tr>
				<tr>
					<td>Actual End Date:</td>
					<!-- clear for date input-->
					<td><input name="aed" type="text" size="30" value="${aed }" required>
					</td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea name="description" rows="5" required
							cols="24"><c:out value="${description}"/></textarea>
					</td>
				</tr>
				<tr>
					<td>Status</td>
					<td>
					<c:if test="${not empty STATUS_LIST}">
						<select name="status">
							<c:if test="${empty st}"><c:set var="st" value="${STATUS_LIST.get(0)}"/></c:if>
							<option selected>${st.name}</option>
							<c:forEach var="stat" items="${STATUS_LIST}">
								<c:if test="${stat.name != st.name}">
            						<option>${stat.name}</option>
        						</c:if>
							</c:forEach>
						</select>
					</c:if>
					</td>
				</tr>
			</table>
			<input type="submit" value="Change">
     		<form method="POST" action="<c:url value='/project.do'/>">
				<input type="submit" value="Cancel">
			</form>
		</form>
	</div>
</body>
</html>