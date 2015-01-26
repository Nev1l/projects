<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="row well">
	<div class="span8">
	<c:choose>
	   <c:when test="${not empty EMPLOYEE_ASSIGNMENT}">
	   <div class="panel panel-default">
        <div class="panel-heading"><h4>Assigned to me</h4></div>
        <div class="panel-body">
			<table class="table table-bordered">
			<thead>
			<tr>
				<th>Project name</th>
				<th>Task status</th>
				<th>Task description</th>
			</tr>
			</thead>
			<tbody>
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
			</tbody>
			</table>
        </div>
      </div>
	   </c:when>
	   <c:otherwise>
	    <div class="panel panel-default">
         <div class="panel-heading"><h4>Assigned to me</h4></div>
         <div class="panel-body">You haven't assigned tasks</div>
        </div>
	   </c:otherwise>
	   </c:choose>
	   </div>
	   <div class="span8">
	   <input type="hidden" id="count" value="20"/>
       <c:choose>
	   <c:when test="${not empty LAST_ACTIVITY}">
	   <div class="panel panel-default">
        <div class="panel-heading"><h4>Activity stream</h4></div>
        <div class="panel-body">
	 	  <table class="table table-striped table-advance table-hover">
	 	    <tbody id="complete-table"><%-- remove id from table --%>
	 	    <tr>
	 	       <th><i class="icon_calendar"></i> Date</th>
	 	 	   <th><i class="icon_profile"></i> Full Name</th>
	 	 	   <th><i class="icon_cogs"></i> Action</th>
	 	    </tr>
			<c:forEach var="activity" items="${LAST_ACTIVITY}" varStatus="status">
				<tr>
					<td>${activity.date}</td>
					<td>	
					   <a href="#" onClick="sendPost('/project/userprofile.do','${activity.member.employee.id}')">${activity.member.employee.firstName} ${activity.member.employee.lastName} </a>
					</td>
					<td>
						${activity.comment}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
		<input class="btn btn-default"  type="button" name="showMore" onClick="load()"
			value="Show more.." />
	   </c:when>
	   <c:otherwise>
	    <div class="panel panel-default">
	        <div class="panel-heading"><h4>Activity stream</h4></div>
        	<div class="panel-body">
	  			 <div>Activity stream is empty</div>
	  		 </div>
	  	</div>
	   </c:otherwise>
	   </c:choose>
	   </div>
	</div>
	<%-- <p> ${sessionScope}</p> --%>
	<!-- footer -->
</body>
</html>
