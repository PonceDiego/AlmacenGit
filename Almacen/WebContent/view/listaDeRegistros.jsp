<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Registros</title>


</head>


<body>

	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista de registros.</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Fecha</th>
						<th>Entrada/Salida</th>
						<th>Usuario</th>
						<th>Entidad</th>
						<th>Nombre</th>
						<!-- 						<th>Acción</th> -->
					</tr>
				</thead>
				<tbody id="tablaEquipos">

					<c:forEach items="${registros}" var="re">
						<tr>
							<td>
								<c:out value="${re.getFecha()}" />
							</td>
							<c:choose>
								<c:when test="${re.getEntrada() eq true }">
									<td>
										<c:out value="Entrada" />
									</td>
								</c:when>
								<c:otherwise>
									<td>
										<c:out value="Salida" />
									</td>
								</c:otherwise>
							</c:choose>
							<td>
								<c:out value="${re.getUsuario().getNombre()} ${re.getUsuario().getApellido() }" />
							</td>
							<td>
								<c:out value="${re.getEntidad()}" />
							</td>
							<c:choose>
								<c:when test="${re.getEntidad() eq 'Equipo' }">
									<td>
										<c:out value="${re.getEquipo().getNombre()}" />
									</td>
								</c:when>
								<c:when test="${re.getEntidad() eq 'Llave' }">
									<td>
										<c:out value="${re.getLlave().getNombre()}" />
									</td>
								</c:when>
								<c:when test="${re.getEntidad() eq 'Grupo equipo' }">
									<td>
										<c:out value="${re.getGrupoEquipos().getNombre()}" />
									</td>
								</c:when>
								<c:when test="${re.getEntidad() eq 'Grupo llave' }">
									<td>
										<c:out value="${re.getGrupoLlaves().getNombre()}" />
									</td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<script src="../vendor/Datatables/datatables.js"></script>




	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"order": [[ 0, "desc" ]],
				"columnDefs" : [ {
					"responsive" : "true"
				} ],
				"language" : {
					"emptyTable" : "No se encontraron registros a mostrar!"
				}
			})
		});
	</script>
</body>
</html>
