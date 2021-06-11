<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Pedidos</title>



</head>


<body>

	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<c:set var="Estado" scope="session" value="${pedido.estadopedido.getNombreEstado()}" />
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista completa de pedidos.</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Fecha</th>
						<th>Usuario</th>
						<th>Área</th>
						<th>Estado</th>
						<th>Más información</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaPedidos">

					<c:forEach items="${pedidosCompleto}" var="pedido">
						<tr>
							<td>
								<c:out value="${pedido.fecha}" />
							</td>
							<td>
								<c:out value="${pedido.usuario.getNombre()} ${pedido.usuario.getApellido() }" />
							</td>
							<td>
								<c:out value="${pedido.usuario.getArea().getNombre()}" />
							</td>
							<td>
								<c:out value="${pedido.estadopedido.getNombreEstado()}" />
							</td>
							<td>
								<span>
									<a href="../Pedido?pedidoId=${pedido.pedidoId}">Detalle</a>
								</span>
							<td>
								<c:choose>
									<c:when test="${Estado=='En Espera'}">
										<button class="btn btn-warning" type="button" title="Entregar" style="cursor: pointer" onclick="alertar('${pageContext.request.contextPath }/EntregarPedido?idEntregado=${pedido.pedidoId}');">
											<i class="material-icons" style="width: 18px; font-size: 18px">check_circle_outline</i>
										</button>

									</c:when>
									<c:when test="${Estado == 'En Curso'}">
										<button class="btn btn-outline-success" type="button" title="Entregar" style="cursor: pointer" onclick="alertar('${pageContext.request.contextPath }/EntregarPedido?idEntregado=${pedido.pedidoId}');">
											<i class="material-icons" style="width: 18px; font-size: 18px">check_circle_outline</i>
										</button>
									</c:when>
								</c:choose>
								<button class="btn btn-outline-info" type="button" title="Editar" style="cursor: pointer" onclick="window.location.href='../EditarPedido?pedidoId=${pedido.pedidoId}'">
									<i class="material-icons" style="font-size: 18px"> edit </i>
								</button>

								<button class="btn btn-outline-danger" type="button" id="eliminarPedidoButton" title="Eliminar" style="cursor: pointer" onclick="confirmar('${pageContext.request.contextPath }/EliminarPedido?idEliminado=${pedido.pedidoId }');">
									<i class="material-icons" style="font-size: 18px"> delete </i>
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
		function alertar(url) {
			alert("Procesando entrega del pedido..");
			$(location).attr('href', url);
		}
	</script>

	<script>
		function confirmar(url) {
			var r = confirm("¿Está seguro que desea eliminar el pedido?");
			if (r == true) {
				$(location).attr('href', url);
			}
		}
	</script>

	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"columnDefs" : [ {
					"order": [[ 0, "desc" ]],
					"responsive" : "true",
					"orderable" : false,
					"targets" : [ 4, 5 ]
				} ],
				"language" : {
					"emptyTable" : "No se encontraron Pedidos a su nombre!"
				}
			})
		});
	</script>
</body>
</html>
