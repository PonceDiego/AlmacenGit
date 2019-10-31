<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Proveedor ${proveedorNombre }</title>
<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/icons/materialicons.css" rel="stylesheet">

<style>
#tableOb {
	border-top: 1px solid black;
	border-collapse: collapse;
	width: 100%;
}

#tableOb tr {
	padding-top: 12px;
	padding-bottom: 12px;
}

#tablaArticulosPedido {
	width: 100%;
}

#tablaArticulosPedido thead {
	background-color: #f37321;
	color: white;
	font-weight: bold;
}
</style>
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
								artículos</a> <a class="dropdown-item" href="../BuscarArticulo">Artículo
								específico</a>
						</div></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../ProveedorNuevo">Nuevo
								proveedor</a> <a class="dropdown-item" href="../ListaProveedores">Listar
								por artículos</a> <a class="dropdown-item active">Proveedor
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
	<form method="post"
		action="${pageContext.request.contextPath }/Proveedor">



		<p></p>
		<h2 class="mt-5 text-center">${proveedorNombre }</h2>
		<div class="text-center lead"
			style="outline: 1px solid black; max-width: 70%; margin: auto">
			
			<table class="table">
			<tr>
			<th>ID: </th>
			<td>${provID}</td>
			<th>Teléfono: </th>
			<td>${provTel}</td>
			<th>Persona de contacto: </th>
			<td>${provContacto}</td>
			</tr>
			<tr>
			<th>Dirección: </th>
			<td>${provDireccion}</td>
			<th> </th>
			<td> </td>
			<th>Mail: </th>
			<td>${proveedorMail}</td>
			</tr>
			
			
			</table>
		</div>
		<div class="text-center" style="width: 70%; margin:auto">
			<!-- tablita de articulos -->
			<h3 class="mt-5 text-center">Artículos</h3>
			<table id="tablaArticulosProveedor" class="table">
				<tr>
					<th>Nombre</th>
					<th>Categoría</th>
					<th>Subcategoría</th>
					<th>Stock</th>
				</tr>
					<c:forEach items="${proveedorArticulos}" var="articulo">
						<tr>
							<td><a href="../Articulo?articuloID=${articulo.getArticuloId() }">
							<c:out value="${articulo.getNombre()}" />
							</a>
							</td>
							<td><c:out value="${articulo.getSubcategoria().getCategoria().getNombre()}" /></td>
							<td><c:out value="${articulo.getSubcategoria().getSubNombre()}" /></td>
							<td><c:out value="${articulo.getStock() }"></c:out></td>

						</tr>
					</c:forEach>
			</table>
		</div>
	</form>


</body>
</html>