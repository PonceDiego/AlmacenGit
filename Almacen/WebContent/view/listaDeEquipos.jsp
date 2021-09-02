<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Equipos</title>
<jsp:include page="header.jsp" />


</head>


<body>

	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="ListaEquipos" />
	</jsp:include>
	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">
				Lista de equipos. <span> <a href="../ListaLugares"
					style="font-size: small;"> Lugares 🡕</a>
				</span> <span> <a href="../ListaTipos" style="font-size: small;">Tipos
						🡕</a>
				</span>
			</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Tipo</th>
						<th>Último Usuario</th>
						<th>Lugar</th>
						<th>Modelo</th>
						<th>Observaciones</th>
						<th>Accesorios</th>
						<th>Estado</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaEquipos">

					<c:forEach items="${equipos}" var="equipo">
						<tr>
							<td><c:out value="${equipo.getNombre()}" /></td>
							<td><c:out value="${equipo.getTipo().getNombre() }" /></td>
							<td><c:forEach items="${registros}" var="re">
									<c:if
										test="${re.getEntidadId() == equipo.getEquipoId().toString() }">
										<c:set var="usuarioEquipo" value="${re.getUsuarioByUsuario()}"></c:set>
										<c:out
											value="${re.getUsuarioByUsuario().getNombre() } ${re.getUsuarioByUsuario().getApellido()}"></c:out>
									</c:if>
								</c:forEach></td>
							<td><c:out value="${equipo.getLugar().getNombre()}" /></td>
							<td><c:out value="${equipo.getModelo()}" /></td>
							<td><c:out value="${equipo.getObservaciones()}" /></td>
							<td><c:out value="${equipo.getAccesorios()}" /></td>
							<td><c:choose>
									<c:when test="${equipo.getEstado()=='Disponible'}">
										<a style="color: green;"> <i class="material-icons">check</i>
										</a>
									</c:when>
									<c:when test="${equipo.getEstado() == 'En uso'}">
										<a style="color: red;"> <i class="material-icons">clear</i>
										</a>
									</c:when>
									<c:otherwise>
										<a> <i class="material-icons">trending_down</i>
										</a>
									</c:otherwise>
								</c:choose></td>

							<td><c:choose>
									<c:when test="${equipo.getEstado()=='Disponible'}">
										<button class="btn btn-warning" type="button" title="Salida"
											style="cursor: pointer"
											data-toggle="modal" onclick="setDataModal('${equipo.getEquipoId()}')"
											data-target="#modalEquipos">S</button>
									</c:when>
									<c:when test="${equipo.getEstado() == 'En uso'}">
										<button class="btn btn-outline-success" type="button"
											title="Entrada" style="cursor: pointer"
											onclick="alertar2('${usuarioEquipo.getNombreUsuario() }','${usuarioActual.getNombreUsuario()}','${usuarioActual.getRol().getNombre() }','../CambioEstado?cambioId=${equipo.getEquipoId()}&entidad=Equipo');">
											E</button>
									</c:when>
								</c:choose>
								 <a href="../Equipo?equipoId=${equipo.getEquipoId()}"> <i
									class="material-icons">history</i>
							</a> <c:if
									test="${usuarioActual.getRol().getNombre() == 'SuperAdmin' ||  usuarioActual.getRol().getNombre() == 'Administrador Tecnica' }">
									<br>
									<div>
									<button class="btn btn-outline-info" type="button"
										style="cursor: pointer" title="Editar"
										onclick="window.location.href='../EditarEquipo?equipoEditar=${equipo.getEquipoId()}'">
										<i class="material-icons" style="font-size: 14px"> edit </i>
									</button>
									<button class="btn btn-outline-danger" type="button"
										title="Eliminar" style="cursor: pointer"
										onclick="confirmar('../EliminarEquipo?idEliminado=${equipo.getEquipoId()}');">
										<i class="material-icons" style="font-size: 14px"> delete
										</i>
									</button>
									</div>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<div id="modalEquipos" class="modal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div
				class="modal-content align-content-md-center col-l-6 col-m-6 col-s-auto col-xl-10">
				<div class="modal-header text-center">
					<h4 class="modal-titlefont-weight-bold ">Ingrese el usuario
						solicitante</h4>
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						data-target="id01" aria-label="Cerrar">X</button>
				</div>
				<div>
					<form method="get" action="../CambioEstado" id="formMethod">
						<div class="form-inline mt-2 ml-2 mb-2 mx-2 ">
							<div class=" form-label-group> searchable">
							<input type="hidden" id="cambioIdModal" name="cambioId">
							<input type="hidden" id="salidaModal" name="salida" value = "1">
							<input type="hidden" id="entidadModal" name="entidad" value="Equipo">
								<input type="text" placeholder="Buscar usuario"
									name="solicitanteId" id="solicitanteId"
									value="${usuarioActual.getNombreUsuario()}"
									onkeyup="filterFunction(this,event)" required
									autocomplete="off">
								<c:set var="usuarios" value="${usuarios}"></c:set>
								<ul id="ulUsusarios">
									<c:forEach items="${usuarios}" var="usuario">

										<li value="${usuario}">${usuario}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="modal-footer d-flex justify-content-center">
							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								id="aceptarbutton"
								style="max-width: min-content; margin: auto; background-color: #f37321; cursor: pointer;"
								>Aceptar</button>
										
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<script>	
		function confirmar(url) {
			var r = confirm("¿Está seguro que desea eliminar el equipo?");
			if (r == true) {
				$(location).attr('href', url);
			}
		}
	</script>
	<script src="../vendor/Datatables/datatables.js"></script>

	<script>
	
		function setDataModal(llaveId) {
			console.log(llaveId);
			var x = document.getElementById('cambioIdModal');
			x.value = llaveId;
		}
		
		
		function alertar2(usernameEquipo, usernameActual, rolActual, url) {
			if (usernameEquipo == usernameActual || rolActual == "SuperAdmin"
					|| rolActual == "Administrador Tecnica") {
				alert("Reingresando el equipo..");
				$(location).attr('href', url);
			} else {
				alert("Solo el usuario que realizó la salida puede volver a ingresarlo!");
			}
		}
	</script>


	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"columnDefs" : [ {
					"responsive" : "true",
					"orderable" : false,
					"targets" : [ 5, 6, 7, 8 ]
				} ],
				"language" : {
					"emptyTable" : "No se encontraron equipos a mostrar!"
				}
			})
		});
	</script>
</body>
</html>
