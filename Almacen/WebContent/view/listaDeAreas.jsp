<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Areas</title>


</head>


<body>

	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>
	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista de áreas.</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Usuario Jefe</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaAreas">

					<c:forEach items="${listaAreasCompleta}" var="area">
						<tr>
							<td>
								<c:out value="${area.getNombre()}" />
							</td>
							<td>
								<c:out value="${area.getUsuario().getNombreUsuario()}" />
							</td>
							<td>
								<button class="btn btn-outline-info" type="button" style="cursor: pointer" title="Editar" onclick="window.location.href='../EditarArea?ArEdId=${area.getId()}'">
									<i class="material-icons" style="font-size: 18px"> edit </i>
								</button>
								<!-- 								<button class="btn btn-outline-danger" type="button"  -->
								<%-- 									title="Eliminar" style="cursor:pointer"onclick="confirmar('${pageContext.request.contextPath }/EliminarArea?idEliminado=${area.getId() }');"> --%>
								<!-- 									<i class="material-icons" style="font-size: 18px"> delete </i> -->
								<!-- 								</button> -->
							</td>
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
				"responsive" : "true",
				"columnDefs" : [ {
					"orderable" : false,
					"targets" : [ 2 ]
				} ]
			});
		});
	</script>
	<script>
		function confirmar(url) {
			var r = confirm("¿Está seguro que desea dar de baja el área?");
			if (r == true) {
				$(location).attr('href', url);
			}
		}
	</script>
</body>
</html>
