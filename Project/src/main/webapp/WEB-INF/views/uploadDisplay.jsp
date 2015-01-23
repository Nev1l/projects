<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="by.epam.consts.ConstantsJSP"%>
<%@ page import="by.epam.consts.ConstantsError"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<head>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
  rel="stylesheet" type="text/css" />
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task</title>
</head>
<body>
<div class="span10">
    <h2>HEAD h2</h2>
    <p>Below is a list of attributes that were gathered in FileUploadController.java.</p>

    <table class="table-striped" cellpadding="5">
        <tr>
            <th>Friendly Name:</th>
            <td><c:out value="${friendlyName}"/></td>
        </tr>
        <tr>
            <th>Filename:</th>
            <td><c:out value="${fileName}"/></td>
        </tr>
        <tr>
            <th>File content type:</th>
            <td><c:out value="${contentType}"/></td>
        </tr>
        <tr>
            <th>File size:</th>
            <td><c:out value="${size}"/></td>
        </tr>
        <tr>
            <th class="tallCell">File Location:</th>
            <td>The file has been written to: <br />
                <a href="<c:out value="${link}"/>"><c:out value="${location}"/></a>
            </td>
        </tr>
    </table>
        <a class="btn" href="fileupload" >
            <i class="icon-upload"></i>
            Upload Another
        </a>
</div>


