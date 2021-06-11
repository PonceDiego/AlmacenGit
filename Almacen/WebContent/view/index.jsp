<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Bienvenido - Sistema Almacén</title>



</head>

<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1 class="mt-5">Bienvenido, ${usuarioActual.getNombre() }</h1>
				<p class="lead">Seleccione la opción deseada en el menú superior.</p>
			</div>
		</div>
	</div>


</body>

</html>
