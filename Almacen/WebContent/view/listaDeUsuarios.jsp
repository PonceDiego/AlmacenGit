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

<title>Usuarios</title>

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="../vendor/Datatables/DataTables-1.10.18/css/dataTables.bootstrap4.css"
	rel="stylesheet">
<link href="../vendor/icons/materialicons.css" rel="stylesheet">


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
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../ProveedorNuevo">Nuevo
								proveedor</a> <a class="dropdown-item" href="../ListaProveedores">Listar
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
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="material-icons">perm_identity</i>
					</a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../UsuarioNuevo">Nuevo usuario</a>
							<a class="dropdown-item active" href="../ListaUsuarios">Lista de usuarios</a> <a
								class="dropdown-item" href="../AreaNueva">Nueva área</a> <a
								class="dropdown-item" href="../CerrarSesion">Cerrar sesión</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista de usuarios.</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre Usuario</th>
						<th>Rol</th>
						<th>Área</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Mail</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaPedidos">

					<c:forEach items="${listaUsuarios}" var="usuario">
						<tr>
							<td><c:out value="${usuario.getUsuarioId()}" /></td>
							<td><c:out value="${usuario.getNombreUsuario()}" /></td>
							<td><c:out value="${usuario.getRol().getNombreRol()}" /></td>

							<td><c:out
									value="${usuario.getArea().getNombreArea()}" /></td>
							<td><c:out value="${usuario.getNombre()}" /></td>
							<td><c:out value="${usuario.getApellido()}" /></td>
							<td><c:out value="${usuario.getEmail()}" /></td>
							<td>
								<button class="btn btn-outline-info" type="button"
									title="Editar">
									<i class="material-icons"> edit </i>
								</button>

								<button class="btn btn-outline-danger" type="button"
									title="Eliminar">
									<i class="material-icons"> delete </i>
								</button></td>
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
					"targets" : [ 7 ]
				} ]
			});
		});
	</script>
</body>
</html>
