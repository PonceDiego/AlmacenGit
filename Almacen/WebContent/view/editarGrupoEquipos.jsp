<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Editar Grupo de Equipos</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuTecnica" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../EditarGrupoEquipos">
					<h3 class="text-center">Datos del grupo de equipos</h3>
					<hr class="m-4">
					<div class="column">
						<div class="form-label-group>">
							<input type="hidden" name="inputId" id="inputId" value="${grupoEquipos.getGrupoEquipoId() }">
							<div>Nombre del grupo de equipos</div>
							<input type="text" name="inputNombre" class="form-control" placeholder="Nombre del grupo" required autocomplete="off" value="${grupoEquipos.getNombre() }" disabled="disabled">
						</div>

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
							<div>Equipos</div>
							<input type="hidden" id="inputEquipos" name="inputEquipos"> <select id="boot-multiselect-equipos" multiple="multiple">
								<c:if test="${grupoEquipos.getEquipos()!=null }">
									<c:forEach items="${grupoEquipos.getEquipos() }" var="gEquipo">
										<option selected value="${gEquipo.getEquipoId()}">${gEquipo.getEquipoId() }-${gEquipo.getNombre() }</option>
									</c:forEach>
								</c:if>
								<c:forEach items="${equipos}" var="equipo">
									<option value="${equipo.getEquipoId()}">${equipo.getEquipoId() }-${equipo.getNombre() }</option>
								</c:forEach>
							</select>
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