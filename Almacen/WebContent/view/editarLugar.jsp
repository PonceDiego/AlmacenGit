<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="equipo" value="${equipoEspecifico }"></c:set>

<title>Editar lugar</title>
</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->
		<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../EditarLugar">
					<h3 class="text-center">Datos del lugar</h3>
					<input type="hidden" id="hiddenLugarId" name="hiddenLugarId" value="${lugar.getLugarId() }">
					<hr class="m-4">
					<div class="row">
						<div class="column">
							<div>Nombre del Lugar</div>
							<div class="form-label-group>">
								<input type="text" name="inputNombre" class="form-control" 
								value = "${lugar.getNombre()}"
								placeholder="Nombre del lugar" required autocomplete="off">
							</div>
							<div>Descripción</div>
							<div class="form-label-group>">
								<input type="text" name="inputDes" class="form-control"
								value = "${lugar.getDescripcion() }"
								 placeholder="Descripción" autocomplete="off" maxlength="140">
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