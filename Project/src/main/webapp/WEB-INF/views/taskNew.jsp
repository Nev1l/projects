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
<title>New task</title>
</head>
<body>
	<div align="center">
	 <c:if test="${not empty ERROR}">
	    <div class="error">${ERROR}<br></div>
	 </c:if>
     <c:if test="${not empty PROJECT}">
		<c:set var="psd" value=""/>
		<c:set var="ped" value=""/>
		<c:set var="asd" value=""/>
		<c:set var="aed" value=""/>
		<c:set var="description" value=""/>
		<c:set var="st" value=""/>
		<c:if test="${not empty TASK}">
		   <c:set var="psd" value="${TASK.plannedStartDate}"/>
		   <c:set var="ped" value="${TASK.plannedEndDate}"/>
		   <c:set var="asd" value="${TASK.actualStartDate}"/>
		   <c:set var="aed" value="${TASK.actualEndDate}"/>
		   <c:set var="description" value="${TASK.description}"/>
		   <c:set var="st" value="${TASK.status}"/>
		</c:if>
		<form  method="POST"
			action="<c:url value='/taskAdd.do'/>">
			New Task<br>
           <div>Name of project: ${PROJECT.name}
  				<input type="hidden" name="id" value="${PROJECT.id}"/>           
           </div>
			<table>
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
					<td><input name="aed" type="text" size="30" value="${aed}" required>
					</td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea name="description" rows="5" required
							cols="24"><c:out value="${description}"/></textarea></td>
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
			<input type="submit" value="Create">
     		<form method="POST" action="<c:url value='/task.do'/>">
				<input type="submit" value="Cancel">
			</form>
		</form>
		</c:if>
	</div>
</body>
</html>