<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">

<title>Agregar Nueva Categoría</title>
<jsp:include page="header.jsp"></jsp:include>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="NuevaCategoria" />
	</jsp:include>


	<!-- Page Content   -->

	<div class="container">
		<form class="form-signin" method="post" action="../NuevaCategoria">
			<div class="card card-signin my-5">
				<div class="card-body">
					<h3 class="text-center">Datos de la nueva categoría</h3>
					<div class="row">
						<div class="column">
							<div>Nombre</div>
							<div class="form-label-group>">
								<input type="text" name="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off">
							</div>
						</div>
					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer">Aceptar</button>
				</div>
			</div>
		</form>
	</div>
</body>

</html>