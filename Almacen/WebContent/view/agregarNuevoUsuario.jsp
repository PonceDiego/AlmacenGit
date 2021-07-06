<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">

<title>Usuario Nuevo</title>
<jsp:include page="header.jsp" />

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="UsuarioNuevo" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../UsuarioNuevo">
					<h3 class="text-center">Datos del nuevo usuario</h3>
					<p class="lead text-center">Recuerde que debe ingresar el nombre de usuario de LDAP</p>
					<div id="advertencia" style="color: red"></div>
					<hr class="m-4">
					<div class="row">
						<div class="column">
							<div>Nombre de Usuario</div>
							<div class="form-label-group>">
								<input tabindex="1" type="text" name="inputUsername" class="form-control" placeholder="Nombre de Usuario" required autocomplete="off" onchange="comparar()">
							</div>
							<div>Nombre</div>
							<div class="form-label-group>">
								<input tabindex="3" type="text" name="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off">
							</div>
							<div>Área</div>
							<div class=" form-label-group>" id="area">
								<input type="hidden" name="inputArea" id="inputArea" required class="form-control"> 
								<input type="hidden" name="inputRol" id="inputRol" required class="form-control"> 
								<select tabindex="5" name="selectArea" onchange="selected(),comparar()" id="selectArea" style="border-radius: 5px; font-size: 16px; padding: 5px; min-width: 50%" required>
									<option disabled selected>Seleccione un área</option>
									<c:forEach items="${areas}" var="area">
										<option style="cursor: pointer" value="${area.getNombre()}">${area.getNombre()}</option>
									</c:forEach>
								</select>

							</div>


						</div>
						<div class="column">
							<div>Correo Electrónico</div>
							<div class="form-label-group>">
								<input tabindex="2" type="text" name="inputMail" class="form-control" placeholder="Mail" required autocomplete="off">
							</div>


							<div>Apellido</div>
							<div class="form-label-group>">
								<input tabindex="4" type="text" name="inputApellido" class="form-control" placeholder="Apellido" required autocomplete="off">
							</div>
							<div>Rol</div>
							<div class="form-label-group>">
								<select tabindex="6" name="selectRol" style="border-radius: 5px; font-size: 16px; padding: 5px; min-width: 50%" required onchange="selected2(),comparar()" id="selectRol">
									<option selected disabled>Seleccione un rol</option>
									<c:forEach items="${roles}" var="rol">

										<option value="${rol.getNombre() }">${rol.getNombre() }</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<hr>
					<button tabindex="7" class="btn btn-lg btn-primary btn-block text-uppercase" disabled id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
	<script>
		function selected() {
			var x = document.getElementById("selectArea").value;

			document.getElementById("inputArea").value = x;
		}
		function selected2() {
			var x = document.getElementById("selectRol").value;

			document.getElementById("inputRol").value = x;
		}

		function comparar() {
			var x = document.getElementById('advertencia');
			var y = document.getElementById('inputArea');
			var z = document.getElementById('inputRol');

			if (y.value == null || y.value === "") {
				x.innerHTML = "Por favor seleccione un area!";
			} else if (z.value == null || z.value === "") {
				x.innerHTML = "Por favor seleccione un rol!";
			} else {
				var x = document.getElementById('advertencia');
				x.innerHTML = "";
				var y = document.getElementById('aceptarbutton');
				y.disabled = false;
			}

		}
	</script>

</body>
</html>