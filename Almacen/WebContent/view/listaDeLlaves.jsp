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
		<jsp:param name="activeMenu" value="ListaLlaves" />
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
						<th>Disponible</th>
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
										<!--  
										<button class="btn btn-warning" type="button" title="Salida" style="cursor: pointer" onclick="alertar('${pageContext.request.contextPath }/CambioEstado?cambioId=${llave.getLlaveId()}&entidad=Llave');">S</button>
										-->
										<button class="btn btn-warning" type="button" title="Salida" style="cursor: pointer" data-toggle="modal" data-target="#modal1">S</button>
									
									</c:when>
										
									<c:when test="${llave.getEstado() == 'En uso'}">
											<button class="btn btn-outline-success" type="button" title="Entrada" style="cursor: pointer" data-toggle="modal" data-target="#modal1">E</button>
										</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
<div id="modal1" class="modal" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content align-content-md-center col-l-6 col-m-6 col-s-auto col-xl-10"   >
			<div class="modal-header text-center">
				<h4 class="modal-titlefont-weight-bold ">Ingrese el usuario solicitante</h4>
				<button type="button" class="btn btn-danger" data-dismiss="modal"  data-target="id01" aria-label="Cerrar">
					X
				</button>
			</div>
			<div>
				<form method="get" action="../CambioEstado">
					<div class="form-inline mt-2 ml-2 mb-2 mx-2 ">
						<input type="text" name="solicitanteId" id="solicitanteId" value="${usuario.id}">
					</div>
					<div class="modal-footer d-flex justify-content-center">
						<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: min-content; margin: auto; background-color: #f37321; cursor: pointer;"
						  onclick="alertar('${pageContext.request.contextPath}/CambioEstado?cambioId=${llave.getLlaveId()}&entidad=Llave');">Aceptar</button>
					</div>
				</form>
			</div>

			</div>
		</div>
	</div>

	<script src="../vendor/Datatables/datatables.js"></script>

	<script>
		function alertar(url,solicitanteId) {
			$(location).attr('href', url+"&salida=1&solicitanteId=");
		}

		function alertar2(usernameEquipo, usernameActual, rolActual, url) {
			if (usernameEquipo == usernameActual || rolActual == "SuperAdmin"
					|| rolActual == "Administrador Llave") {
				alert("Reingresando la llave..");
				$(location).attr('href', url+"&salida=0");
			} else {
				alert("Solo el usuario que realizó la salida puede hacer el ingreso!");
			}
		}
	</script>


	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"columnDefs" : [ {
					"responsive" : "true",
					"orderable" : false,
					"targets" : [ 5, 6 ]
				} ],
				"language" : {
					"emptyTable" : "No se encontraron llaves a mostrar!"
				}
			})
		});
	</script>
</body>
</html>
