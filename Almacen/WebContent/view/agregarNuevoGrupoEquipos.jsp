<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">



<title>Grupo de Equipos Nuevo</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../NuevoGrupoEquipo">
					<h3 class="text-center">Datos del nuevo grupo de equipos</h3>
					<hr class="m-4">
					<div class="column">
						<div class="form-label-group>">
							<input tabindex="1" type="text" name="inputNombre" class="form-control" placeholder="Nombre del grupo" required autocomplete="off">
						</div>
						<p></p>



						<script type="text/javascript">
							$(document)
									.ready(
											function() {
												$('#boot-multiselect-equipos')
														.multiselect(
																{
																	nonSelectedText : 'Seleccionar equipos',
																	buttonWidth : 500,
																	enableFiltering : true
																});

												$('#aceptarbutton')
														.click(
																function() {
																	$(
																			'#inputEquipos')
																			.val(
																					$(
																							'#boot-multiselect-equipos')
																							.val());
																});

											});
						</script>
						<div style="text-align: center;">
							<input type="hidden" id="inputEquipos" name="inputEquipos"> <select tabindex="2" id="boot-multiselect-equipos" multiple="multiple">
								<c:forEach items="${equipos}" var="equipo">
									<option value="${equipo.getEquipoId()}">${equipo.getNombre() } - Modelo ${equipo.getModelo() }</option>
								</c:forEach>
							</select>
						</div>


					</div>
					<hr>
					<button tabindex="3" class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>