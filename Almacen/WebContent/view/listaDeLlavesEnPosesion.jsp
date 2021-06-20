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

<title>Llaves</title>
<jsp:include page="header.jsp"/>



</head>


<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="ListaLlavesIndividual" />
	</jsp:include>


	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">
				Lista de llaves.
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
					</tr>
				</thead>
				<tbody id="tablaEquipos">

					<c:forEach items="${llaves}" var="llave">
						<tr>
							<td><c:out value="${llave.getNombre()}" /></td>
							<td><c:out value="${llave.getCopia() }" /></td>
							<td><c:out value="${llave.getGrupoLlaves().getNombre() }" />
							</td>
							<td><c:out value="${llave.getLugar().getNombre()}" /></td>
							<td><c:out value="${llave.getObservaciones()}" /></td>
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
				"columnDefs" : [ {
					"responsive" : "true",
				} ],
				"language" : {
					"emptyTable" : "No se encontraron llaves a mostrar!"
				}
			})
		});
	</script>

</body>
</html>
