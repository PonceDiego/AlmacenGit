<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Llaves</title>



</head>


<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>


	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">
				Lista de llaves.
				<span>
					<a href="../ListaLugares" style="font-size: small;"> Lugares 🡕</a>
				</span>
				<span>
					<a href="../BuscarGrupoLlaves" style="font-size: small;">Grupos 🡕</a>
				</span>
			</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Copia</th>
						<th>Grupo</th>
						<th>Lugar</th>
						<th>Observaciones</th>
						<th>Estado</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaEquipos">

					<c:forEach items="${llaves}" var="llave">
						<tr>
							<td>
								<c:out value="${llave.getNombre()}" />
							</td>
							<td>
								<c:out value="${llave.getCopia() }" />
							</td>
							<td>
								<c:out value="${llave.getGrupoLlaves().getNombre() }" />
							</td>
							<td>
								<c:out value="${llave.getLugar().getNombre()}" />
							</td>
							<td>
								<c:out value="${llave.getObservaciones()}" />
							</td>
							<td>
								<c:choose>
									<c:when test="${llave.getEstado()== 'Disponible'}">
										<a style="color: green;">
											<i class="material-icons">check</i>
										</a>
									</c:when>
									<c:when test="${llave.getEstado() == 'En uso'}">
										<a style="color: red;">
											<i class="material-icons">clear</i>
										</a>
									</c:when>
									<c:otherwise>
										<a>
											<i class="material-icons">trending_down</i>
										</a>
									</c:otherwise>
								</c:choose>
							</td>

							<td>
								<c:choose>
									<c:when test="${llave.getEstado() == 'Disponible'}">
										<button class="btn btn-warning" type="button" title="Salida" style="cursor: pointer" onclick="alertar('${pageContext.request.contextPath }/CambioEstado?cambioId=${llave.getLlaveId()}&entidad=Llave');">S</button>

									</c:when>
									<c:when test="${llave.getEstado() == 'En uso'}">
										<button class="btn btn-outline-success" type="button" title="Entrada" style="cursor: pointer"
											onclick="alertar2('${usuarioEquipo.getNombreUsuario() }','${usuarioActual.getNombreUsuario()}','${usuarioActual.getRol().getNombre() }','${pageContext.request.contextPath }/CambioEstado?cambioId=${llave.getLlaveId()}&entidad=Llave');">
											E</button>
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<script src="../vendor/Datatables/datatables.js"></script>

	<script>
		function alertar(url) {
			alert("Realizando salida de la llave");
			$(location).attr('href', url);
		}

		function alertar2(usernameEquipo, usernameActual, rolActual, url) {
			if (usernameEquipo == usernameActual || rolActual == "SuperAdmin"
					|| rolActual == "Administrador Llave") {
				alert("Reingresando la llave..");
				$(location).attr('href', url);
			} else {
				alert("Solo el usuario que realizó la salida puede hacer el ingreso!");
			}
		}
	</script>


	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"scrollX" : true,
				"columnDefs" : [ {
					"responsive" : "true",
					"orderable" : false,
					"targets" : [ 5, 6, 7, 8 ]
				} ],
				"language" : {
					"emptyTable" : "No se encontraron llaves a mostrar!"
				}
			})
		});
	</script>
</body>
</html>
