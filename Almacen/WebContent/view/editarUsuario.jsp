<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">

<title>Editar Usuario</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuUsuario" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../EditarUsuario">
					<h3 class="text-center">Datos del usuario a editar</h3>
					<hr class="m-4">
					<c:set var="usuario" value="${usuarioEditar}"></c:set>
					<input type="hidden" value="${usuario.getId() }" id="idUEditar" name="idUEditar">
					<div class="row">
						<div class="column">
							<div>Nombre de Usuario</div>
							<div class="form-label-group>">
								<input type="text" name="inputUsername" class="form-control" placeholder="Nombre de Usuario" required value="${usuario.getNombreUsuario() }" readonly>
							</div>
							<div>Nombre</div>
							<div class="form-label-group>">
								<input type="text" name="inputNombre" class="form-control" placeholder="Nombre" required value="${usuario.getNombre() }">
							</div>
							<div>Rol</div>
							<div class="form-label-group>">
								<select name="inputRol" style="border-radius: 5px; font-size: 16px; padding: 5px; min-width: 50%" required>
									<c:forEach items="${roles}" var="rol">
										<c:choose>
											<c:when test="${rol.getNombre()==usuario.getRol().getNombre() }">
												<option selected>${rol.getNombre() }</option>
											</c:when>
											<c:otherwise>
												<option>${rol.getNombre() }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>

						</div>
						<div class="column">
							<div>Correo Electrónico</div>
							<div class="form-label-group>">
								<input type="text" name="inputMail" class="form-control" placeholder="eMail" required value="${usuario.getEmail() }">
							</div>

							<div>Apellido</div>
							<div class="form-label-group>">
								<input type="text" name="inputApellido" class="form-control" placeholder="Apellido" required value="${usuario.getApellido()}">
							</div>
							<div>Área</div>
							<div class=" form-label-group>" id="area">
								<select name="inputArea" style="border-radius: 5px; font-size: 16px; padding: 5px; min-width: 50%" required>
									<c:forEach items="${areas}" var="area">
										<c:choose>
											<c:when test="${area.getNombre()==usuario.getArea().getNombre()}">
												<option selected>${area.getNombre() }</option>
											</c:when>
											<c:otherwise>
												<option>${area.getNombre()}</option>
											</c:otherwise>
										</c:choose>

									</c:forEach>
								</select>

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