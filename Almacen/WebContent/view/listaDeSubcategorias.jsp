<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Categorias</title>
<jsp:include page="header.jsp" />


</head>


<body>

	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="Subcategorias" />
	</jsp:include>
	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista de subcategorías.</h1>


			<hr class="my-4">
			<button type="submit" onclick="location.href='../NuevaSubcategoria'" class="btn btn-lg btn-primary btn-block text-uppercase" id="nuevacategoria" style="width: 30%; background-color: #f37321; cursor: pointer; border-radius: 5rem; margin-top: 10px">Nueva subcategoría</button>
			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Categoría</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaSubcategorias">

					<c:forEach items="${subcategorias}" var="subcategoria">
						<tr>
							<td>
								<c:out value="${subcategoria.getNombre()}" />
							</td>
							<td>
								<c:out value="${subcategoria.getCategoria().getNombre()}" />
							</td>

							<td>
								<c:if test="${usuarioActual.getRol().getNombre() == 'SuperAdmin' ||  usuarioActual.getRol().getNombre() == 'Administrador Tecnica' }">
									<div>
										<button class="btn btn-outline-info" type="button" style="cursor: pointer" title="Editar" onclick="window.location.href='../EditarEquipo?equipoEditar=${subcategoria.getId()}'">
											<i class="material-icons" style="font-size: 14px"> edit </i>
										</button>
										<button class="btn btn-outline-danger" type="button" title="Eliminar" style="cursor: pointer" onclick="confirmar('${pageContext.request.contextPath }/EliminarEquipo?idEliminado=${subcategoria.getId()}');">
											<i class="material-icons" style="font-size: 14px"> delete </i>
										</button>
									</div>
								</c:if>
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
			var r = confirm("¿Está seguro que desea eliminar la subcategoria?");
			if (r == true) {
				$(location).attr('href', url);
			}
		}
	</script>
	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"order": [],
				"columnDefs" : [ 
					{ "targets" : [0, 1], "orderable" : true },
					{ "targets" : [2], "orderable" : false },
				],
				"language" : {
					"emptyTable" : "No se encontraron subcatergorias a mostrar!"
				},
			})
		});
	</script>
</body>
</html>
