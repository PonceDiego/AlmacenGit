<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Código QR de ${entidadNombre }</title>
<jsp:include page="header.jsp"/>
<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">
</head>
<body>


	<div style="outline:1px solid black; max-width: 250px;margin: 2px">

		<img src="${imageSrc}" alt="CódigoQR">
		<h5 class="text-center" style="align-content: center;">${entidadNombre }</h5>
	</div>







</body>