<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Proveedores</title>

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/icons/materialicons.css" rel="stylesheet">
<link
	href="../vendor/Datatables/DataTables-1.10.18/css/dataTables.bootstrap4.css"
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
			<a class="navbar-brand" href="../">Inicio</a>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Artículos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../NuevoArticulo">Nuevo artículo</a>
							<a class="dropdown-item" href="../ListaArticulos">Lista de
								artículos</a> <a class="dropdown-item disabled" href="#">Artículo
								específico</a>
						</div></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../ProveedorNuevo">Nuevo
								proveedor</a> <a class="dropdown-item active" href="../ListaProveedores">Listar
								por artículos</a> <a class="dropdown-item disabled" href="#">Proveedor
								específico</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Pedidos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
							<a class="dropdown-item" href="../GenerarPedido">Nuevo pedido</a> <a
								class="dropdown-item" href="../ListaPedidos">Lista de pedidos</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="material-icons">perm_identity</i>
					</a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../UsuarioNuevo">Nuevo usuario</a>
							<a class="dropdown-item " href="../ListaUsuarios">Lista de usuarios</a> <a
								class="dropdown-item" href="../AreaNueva">Nueva área</a> <a
								class="dropdown-item" href="../CerrarSesion">Cerrar sesión</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>


	<!-- Page Content   -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1 class="mt-5">Lista de proveedores por artículo.</h1>

				<h1>&nbsp;</h1>

				<table class="table table-striped" id="tablaProveedores">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Artículos</th>
							<th>Ver más</th>
						</tr>
					</thead>
					<tbody id="proveedores">
						<c:forEach items="${listaProveedores}" var="proveedor">
							<tr>
								<td id="pID"><c:out value="${proveedor.getProvId()}" /></td>
								<td id="pNB"><c:out value="${proveedor.getProvNombre()}" /></td>
								<td><c:forEach items="${proveedor.getArticulos() }"
										var="articulo">
										<c:out value="-${articulo.getNombre()}" />
										<br>
									</c:forEach></td>
								<td><span> <a
										href="../Proveedor?proveedorId=${proveedor.getProvId()}">Detalle</a>
								</span></td>
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

			$('#tablaProveedores').DataTable({
				"columnDefs" : [ {
					"orderable" : false, "searchable":false,
					"targets" : [ 3 ]
				} ]
			});
		});
	</script>
</body>
</html>