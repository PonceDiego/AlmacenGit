<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">
<title>Área Nueva</title>
<jsp:include page="header.jsp"/>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="AreaNueva" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../AreaNueva">
					<h3 class="text-center">Datos de la nueva área</h3>
					<div id="advertencia" style="color: red"></div>
					<hr class="m-4">
					<div class="row">
						<div class="column">
							<div>Nombre del Área</div>
							<div class="form-label-group>">
								<input type="text" name="inputNombre" class="form-control" onchange="comparar()"
								 placeholder="Nombre de área" tabindex="1" required autocomplete="off" maxlength="30">
							</div>

						</div>
						<div class="column">
							<div>Usuario Jefe</div>
							<div class=" form-label-group>">
								<input type="hidden" name="inputJefe" class="form-control"
										id="inputJefe" required autocomplete="off">
								<select tabindex="2" name="selectJefe" id="selectJefe" style="border-radius: 5px; font-size: 16px;
								 padding: 5px; min-width: 50%" required
								 onchange="selected(),comparar()">
									<option disabled selected>Seleccione un usuario jefe</option>
									<c:forEach items="${usuarios}" var="usuario">
										<option style="cursor: pointer" value="${usuario.getNombreUsuario()}">${usuario.getNombre()} ${usuario.getApellido()}</option>
									</c:forEach>
								</select>

							</div>
						</div>
					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" 
					disabled
					style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
<script>
function selected() {
	var x = document.getElementById("selectJefe").value;

	document.getElementById("inputJefe").value = x;
}
function comparar() {
		var y = document.getElementById('inputJefe');
		if (y.value == null || y.value === "") {
			var x = document.getElementById('advertencia');
			x.innerHTML = "Por favor seleccione un usuario jefe!";
		} else {
			var x = document.getElementById('advertencia');
			x.innerHTML = "";
			var y = document
					.getElementById('aceptarbutton');
			y.disabled = false;
		}

}
</script>
</body>
</html>