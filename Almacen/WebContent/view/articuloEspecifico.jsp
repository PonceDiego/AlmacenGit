<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Artículo: ${articuloNombre }</title>
<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">

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

.error {
	border: 2px solid red;
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
			class="material-icons" style="font-size: 36px">arrow_back </i></a>
		<div class="container">
			<a class="navbar-brand" href="../Index">Inicio</a>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Artículos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../NuevoArticulo">Nuevo
								artículo</a> <a class="dropdown-item" href="../ListaArticulos">Lista
								de artículos</a> <a class="dropdown-item active">Artículo
								específico</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../ProveedorNuevo">Nuevo
								proveedor</a> <a class="dropdown-item" href="../ListaProveedores">Listar
								por artículos</a> <a class="dropdown-item" href="../BuscarProveedor">Proveedor
								específico</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Pedidos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
							<a class="dropdown-item" href="../GenerarPedido">Nuevo pedido</a>
							<a class="dropdown-item" href="../ListaPedidos">Lista de
								pedidos</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown3"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Técnica </a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown3">
							<a class="dropdown-item" href="../ListaEquipos">Lista de
								equipos</a> <a class="dropdown-item" href="../BuscarGrupoEquipos">Buscar grupo de equipos</a>
							<c:if
								test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador Técnica'}">
								<a class="dropdown-item " href="../NuevoEquipo">Nuevo equipo</a> <a class="dropdown-item " href="../NuevoGrupoEquipo">Nuevo grupo equipos</a> 
								<a class="dropdown-item" href="../Tipo">Nuevo tipo</a>
								<a class="dropdown-item " href="../Lugar">Nuevo lugar</a>
							</c:if>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href='#' id="navbarDropdown4"
						role="button" data-toggle="dropdown" aria-haspoup="true"
						aria-expanded="false">Llaves </a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown4">

							<a class="dropdown-item" href="../ListaLlaves">Lista de
								llaves</a> <a class="dropdown-item" href="../BuscarGrupoLlaves">Buscar grupo de llaves</a>
							<c:if
								test="${usuarioActual.getRol().getNombre() == 'SuperAdmin' || usuarioActual.getRol().getNombre() == 'Administrador Llaves' }">
								<a class="dropdown-item" href="../NuevaLlave">Nueva llave</a>
								<a class="dropdown-item" href="../NuevoGrupo">Nuevo grupo</a>
							</c:if>

						</div></li>
						<c:if
							test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador Técnica'}">
								
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href='#' id="navbarDropdown5"
							role="button" data-toggle="dropdown" aria-haspoup="true"
							aria-expanded="false">Registros </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdown5">
									<a class="dropdown-item " href="../ListaRegistros">Lista de registros</a>
							</div>
						</li>
					</c:if>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="material-icons">perm_identity</i>
					</a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../UsuarioNuevo">Nuevo usuario</a>
							<a class="dropdown-item " href="../ListaUsuarios">Lista de
								usuarios</a> <a class="dropdown-item" href="../AreaNueva">Nueva
								área</a> <a class="dropdown-item" href="../CerrarSesion">Cerrar
								sesión</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>


	<input type="hidden" name="articuloID" id="articuloID"
		value="${articuloId}">
	<h2 class="mt-5 text-center">${articuloNombre}
		<span>
			<button
				onclick="window.location.href=('${pageContext.request.contextPath }/GenerarPedido?articuloAgregar=${articuloNombre}')"
				title="Añadir a pedido" style="cursor: pointer"
				class="btn-outline-info btn">
				<i class="material-icons">add_shopping_cart</i>
			</button>
		</span> <span><button class="btn btn-outline-dark"
				style="cursor: pointer" title="Ver código QR"
				onclick="window.open('${pageContext.request.contextPath }/Qr?entidadId=${articuloId}&entidad=Articulo','_blank')">
				<i class="material-icons"> border_outer </i>
			</button></span><span>
			<button class="btn btn-outline-warning" style="cursor: pointer"
				title="Añadir stock" data-toggle="modal" data-target="#id01">
				<i class="material-icons">library_add</i>
			</button>
		</span>
	</h2>
	<hr>
	<div class="text-center lead"
		style="outline: 1px solid black; max-width: 70%; margin: auto">

		<table class="table">
			<tr>
				<th>Categoría:</th>
				<td>${articuloCatPadre}</td>
				<th>Subcategoría:</th>
				<td>${articuloCat}</td>
				<th>Estado:</th>
				<td>${articuloEstado.getNombreEstado()}</td>
			</tr>
			<tr>
				<th>Fecha agregado:</th>
				<td>${articuloFecha}</td>

				<th>Stock Mínimo:</th>
				<td>${articuloStockMinimo}</td>
				<th>Stock Actual:</th>
				<td>${articuloStock}</td>
			</tr>
			<tr>
				<th>Costo:</th>
				<td>$${articuloCosto}</td>
				<th>Proveedor:</th>
				<c:forEach items="${artProveedores}" var="prov">


					<td><a href="../Proveedor?proveedorId=${prov.getId()}">
							${prov.getNombre() }</a></td>
				</c:forEach>
			</tr>
		</table>
	</div>


	<div id="id01" class="modal modal.fade" aria-hidden="true">
		<div class="modal-content align-content-md-center		">
			<div class="modal-header text-center">
				<h4 class="modal-titlefont-weight-bold">Ingrese la cantidad de
					stock a agregar</h4>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Cerrar" style="cursor: pointer; background-color: red;">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body mx-3">
				<form method="get" action="../ActualizarStock">
					<div class="mb-5 form-inline">
						<input type="hidden" name="articuloID" id="articuloID"
							value="${articuloId}"> <input type="text" id="cantidad"
							name="cantidad" onchange="validarSiNumero(this.value);">
					</div>
					<div class="modal-footer d-flex justify-content-center">
						<button class="btn btn-lg btn-primary btn-block text-uppercase"
							id="aceptarbutton"
							style="max-width: 30%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
					</div>
				</form>
			</div>

		</div>


	</div>
	<script>
		function validarSiNumero(numero) {
			if (!/^([0-9,.])*$/.test(numero)) {

				document.getElementById("cantidad").className = document
						.getElementById("cantidad").className
						+ " error";
				document.getElementById("aceptarbutton").disabled = true;
			} else {
				document.getElementById("cantidad").className = document
						.getElementById("cantidad").className.replace(" error",
						"");
				document.getElementById("aceptarbutton").disabled = false;
			}

		}
	</script>
</body>
</html>