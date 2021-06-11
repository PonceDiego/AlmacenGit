<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Artículos</title>


</head>


<body onload="setColor();">
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="ListaArticulos" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1 class="mt-5">Lista de artículos.</h1>
				<hr class="h4">
				<table class="table table-striped " id="tablaArticulos">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Categoría</th>
							<th>Subcategoría</th>
							<th>Stock</th>
							<th>Estado</th>
							<th>Acción</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaArticulos}" var="articulo">
							<tr>
								<td>
									<span>
										<a href="../Articulo?articuloID=${articulo.articuloId }">
											<c:out value="${articulo.getNombre()}" />
										</a>
									</span>
								</td>
								<td>
									<c:out value="${articulo.getSubcategoria().getCategoria().getNombre()}" />
								</td>
								<td>
									<c:out value="${articulo.getSubcategoria().getNombre()}" />
								</td>
								<td>
									<c:out value="${articulo.getStock()}" />
								</td>
								<td>
									<div class="color-box" id="${articulo.getEstadoarticulo().getNombreEstado() }"></div>
								</td>
								<td>
									<a href="../EditarArticulo?nombreEditado=${articulo.getNombre()}" title="Editar">
										<i class="material-icons" style="font-size: 18px">edit</i>
									</a>
									<a href="../GenerarPedido?articuloAgregar=${articulo.getNombre()}" title="Añadir a pedido">
										<i class="material-icons" style="font-size: 18px">add_shopping_cart</i>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>
	<script src="../vendor/Datatables/datatables.js"></script>



	<script>
		$(document).ready(function() {

			$('#tablaArticulos').DataTable({
				"columnDefs" : [ {
					"orderable" : false,
					"targets" : [ 4, 5 ]
				} ]
			});
		});
	</script>
	<script>
		function setColor() {
			var x = document.getElementsByClassName("color-box");
			for (var i = 0; i < x.length; i++) {
				if (x[i].id == "En Stock") {
					x[i].style.backgroundColor = "#43eb34";
				} else if (x[i].id == "Sin Stock") {
					x[i].style.backgroundColor = "#eb4034";
				}
			}
		}
	</script>
</body>
</html>
