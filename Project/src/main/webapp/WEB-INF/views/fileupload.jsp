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
<div class="span3">
    <h2>upload.heading</h2>
    <p>upload.message</p>
</div>
<div class="span7">
    <form  method="post" action="<c:url value="/fileupload.do"/>" enctype="multipart/form-data" id="uploadForm">
			fileUpload.name([input path=name])
            <div class="controls">
	             <input id="name" name="path"/> 
            </div>
            fileUpload.file
            <div class="controls">
                <input type="file" name="file" id="file"/>
            </div>
            <input type="hidden" name="upload" value="false"/>
            <input type="submit" name="cancel" class="btn" onclick="upload(false)">
            <input type="submit" name="upload" class="btn btn-primary" onclick="upload(true)">
    </form>
</div>
