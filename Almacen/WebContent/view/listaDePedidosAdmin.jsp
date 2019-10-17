<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Pedidos</title>

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="../vendor/Datatables/DataTables-1.10.18/css/dataTables.bootstrap4.css"
	rel="stylesheet">
	
<link href="../vendor/icons/materialicons.css"
	rel="stylesheet">

</head>


<body>

	<!-- Bootstrap core JavaScript -->
	<script src="../vendor/jquery/jquery.slim.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
		<a class="navbar-brand" href="javascript:history.back()"> <i
			class="material-icons"> arrow_back </i></a>
		<div class="container">
			<a class="navbar-brand" href="../">Almacén - Pedidos</a>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link disabled">Pedidos
					</a></li>
					
					<li class="nav-item"><a class="nav-link"
						href="/Almacen/ListaArticulos">Artículos </a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="/Almacen/ListaProveedores">por
								Artículos</a> <a class="dropdown-item"
								href="/Almacen/ListaProveedoresB">por Categorías</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>


	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista de pedidos pendientes.</h1>


			<hr class="my-4">

			<form action="/Almacen/GenerarPedido">
				<button class="btn btn-primary mb-2 mx-sm-3" style="cursor: pointer" title="Nuevo Pedido"><i class="material-icons"> add </i>Nuevo</button>
			</form>
			<button onclick="window.location='listaDePedidosAdminCompleta.jsp'"
				class="btn btn-light mb-2 mx-sm-5" style="cursor: pointer">Ver
				todos</button>
		</div>
		<div>&nbsp;</div>
		<table class="table table-striped table-bordered" id="tabla">
			<thead>
				<tr>
					<th>Fecha</th>
					<th>ID</th>
					<th>Área</th>
					<th></th>
					<th>Acción</th>
				</tr>
			</thead>
			<tbody id="tablaPedidos">

				<c:forEach items="${pedidosPendientes}" var="pedido">
					<tr>
						<td><c:out value="${pedido.fecha}" /></td>
						<td><c:out value="${pedido.pedidoId}" /></td>
						<td><c:out
								value="${pedido.usuario.getArea().getNombreArea()}" /></td>
						<td><span> <a
								href="../Pedido?pedidoId=${pedido.pedidoId}">Ver más<i class="material-icons"> call_made </i></a>
						</span>
						<td><button class="btn btn-large btn-success" type="button">

								<i class="material-icons"> check_circle_outline </i>
							</button>
							<button class="btn btn-outline-info" type="button" title="Editar">
								<i class="material-icons"> edit </i>
							</button>

							<button class="btn btn-outline-danger" type="button"
								title="Eliminar">
								<i class="material-icons"> delete </i></button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<script src="../vendor/Datatables/datatables.js"></script>



	<script>
		$(document).ready(function() {

			$('#tabla').DataTable({
				"responsive" : "true",
				"columnDefs" : [ {
					"orderable" : false,
					"targets" : [ 3, 4 ]
				} ]
			});
		});
	</script>
</body>
</html>