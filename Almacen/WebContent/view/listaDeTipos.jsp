<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Tipos</title>
<jsp:include page="header.jsp"/>



</head>


<body>

	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuTecnica" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista de tipos.</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaAreas">

					<c:forEach items="${listaTipos}" var="tipo">
						<tr>
							<td>
								<c:out value="${tipo.getNombre()}" />
							</td>
							<td>
								<a href="../EditarTipo?tipoEditar=${tipo.getNombre()}" title="Editar">
										<i class="material-icons" style="font-size: 18px">edit</i>
									</a>
									<button class="btn btn-outline-danger" type="button"
									title="Eliminar"
									style="cursor: pointer"
									onclick="confirmar('../EliminarTipo?idEliminado=${tipo.getId()}');">
									<i class="material-icons" style="font-size: 16px"> delete </i>
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<script src="../vendor/Datatables/datatables.js"></script>

<script>	
		function confirmar(url) {
			var r = confirm("¿Está seguro que desea eliminar el tipo?");
			if (r == true) {
				$(location).attr('href', url);
			}
		}
	</script>

	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"responsive" : "true"} ]
			});
		});
	</script>
</body>
</html>
