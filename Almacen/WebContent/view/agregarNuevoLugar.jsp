<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">

<title>Lugar Nuevo</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="Lugar" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../Lugar">
					<h3 class="text-center">Datos del nuevo lugar</h3>
					<hr class="m-4">
					<div class="row">
						<div class="column">
							<div>Nombre del Lugar</div>
							<div class="form-label-group>">
								<input type="text" name="inputNombre" class="form-control" placeholder="Nombre del lugar" required autocomplete="off">
							</div>
							<div>Descripción</div>
							<div class="form-label-group>">
								<input type="text" name="inputDes" class="form-control" placeholder="Descripción" autocomplete="off" maxlength="140">
							</div>

						</div>
					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>

</body>
</html>