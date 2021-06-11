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

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../UsuarioNuevo">
					<h3 class="text-center">Datos del nuevo usuario</h3>
					<p class="lead text-center">Recuerde que debe ingresar el nombre de usuario de LDAP</p>
					<hr class="m-4">
					<div class="row">
						<div class="column">
							<div>Nombre de Usuario</div>
							<div class="form-label-group>">
								<input tabindex="1" type="text" name="inputUsername" class="form-control" placeholder="Nombre de Usuario" required autocomplete="off">
							</div>
							<div>Nombre</div>
							<div class="form-label-group>">
								<input tabindex="3" type="text" name="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off">
							</div>
							<div>Área</div>
							<div class=" form-label-group>" id="area">
								<select tabindex="5" name="inputArea" style="border-radius: 5px; font-size: 16px; padding: 5px; min-width: 50%" required>
									<option disabled selected>Seleccione un área</option>
									<c:forEach items="${areas}" var="area">
										<option style="cursor: pointer">${area.getNombre()}</option>
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
								<select tabindex="6" name="inputRol" style="border-radius: 5px; font-size: 16px; padding: 5px; min-width: 50%" required>
									<option selected disabled>Seleccione un rol</option>
									<c:forEach items="${roles}" var="rol">

										<option>${rol.getNombre() }</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<hr>
					<button tabindex="7" class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>

</body>
</html>