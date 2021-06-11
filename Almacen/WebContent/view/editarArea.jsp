<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">

<title>Editar Área</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../EditarArea">
					<h3 class="text-center">Datos del área</h3>
					<hr class="m-4">
					<div class="row">
						<div class="column">
							<div>Nombre del Área</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputId" value="${areaEditar.getId() }"> <input type="text" name="inputNombre" class="form-control" placeholder="Nombre de área" required autocomplete="off" value="${areaEditar.getNombre() }">
							</div>

						</div>
						<div class="column">
							<div>Usuario Jefe</div>
							<div class=" form-label-group>">
								<select name="inputJefe" style="border-radius: 5px; font-size: 16px; padding: 5px; min-width: 50%" required>
									<c:forEach items="${usuarios}" var="usuario">
										<c:choose>
											<c:when test="${usuario.getNombreUsuario()==areaEditar.getUsuario().getNombreUsuario() }">
												<option selected>${usuario.getNombreUsuario()}</option>
											</c:when>
											<c:otherwise>
												<option>${usuario.getNombreUsuario()}</option>
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