<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New project</title>
</head>
<body>
   <%@ include file="header.jsp" %> 
   <div class="col-lg-4"></div>
   <div class="col-lg-4">
     <div class="panel panel-default">
        <div class="panel-heading"><h4>Create new project</h4></div>
        <div class="panel-body">
		<c:if test="${not empty ERROR}">
		   <div class="error">${ERROR}<br></div>
		</c:if>
		<c:set var="name" value=""/>
		<c:set var="psd" value=""/>
		<c:set var="ped" value=""/>
		<c:set var="asd" value=""/>
		<c:set var="aed" value=""/>
		<c:set var="description" value=""/>
		<c:set var="st" value=""/>
		<c:if test="${not empty PROJECT}">
		   <c:set var="name" value="${PROJECT.name}"/>
		   <c:set var="psd" value="${PROJECT.plannedStartDate}"/>
		   <c:set var="ped" value="${PROJECT.plannedEndDate}"/>
		   <c:set var="asd" value="${PROJECT.actualStartDate}"/>
		   <c:set var="aed" value="${PROJECT.actualEndDate}"/>
		   <c:set var="description" value="${PROJECT.description}"/>
		   <c:set var="st" value="${PROJECT.status}"/>
		</c:if>
		<form class="form-horizontal"  method="POST"
			action="<c:url value='/projectAdd.do'/>">
			<div class="form-group">
				<label class="control-label col-lg-2" for="title">Name of project</label>
				<div class="col-lg-10">
					<input name="name" class="form-control" type="text" size="30"
						value="${name}" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="title">Planned Start Date</label>
				<div class="col-lg-10">
					<input class="form-control" name="psd" type="date" size="30"
						value="${psd}" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="title">Planned End Date</label>
				<div class="col-lg-10">
					<input class="form-control" name="ped" type="date" size="30"
						value="${ped}" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="title">Actual Start Date</label>
				<div class="col-lg-10">
					<input class="form-control" name="asd" type="date" size="30"
						value="${asd}" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="title">Actual End Date</label>
				<div class="col-lg-10">
					<input class="form-control" name="aed" type="date" size="30"
						value="${aed }" required/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="content">Description</label>
				<div class="col-lg-10">
					<textarea class="form-control" name="description" rows="5" required
						cols="24"><c:out value="${description}" /></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2">Status</label>
				<div class="col-lg-10">
					<c:if test="${not empty STATUS_LIST}">
						<select class="form-control" name="status">
							<c:if test="${empty st}">
								<c:set var="st" value="${STATUS_LIST.get(0)}" />
							</c:if>
							<option selected>${st.name}</option>
							<c:forEach var="stat" items="${STATUS_LIST}">
								<c:if test="${stat.name != st.name}">
									<option>${stat.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>
			<input class="btn btn-default" type="submit" value="Create"/>
     		<input class="btn btn-default" type="button" onClick="window.history.go(-1)" value="Cancel"/>
		</form>
		</div>
		</div>
	</div>
</body>
</html>