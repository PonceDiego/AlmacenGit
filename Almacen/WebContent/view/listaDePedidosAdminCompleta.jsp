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

<title>Pedidos</title>

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="../vendor/Datatables/DataTables-1.10.18/css/dataTables.bootstrap4.css"
	rel="stylesheet">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">


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
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Artículos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../BuscarArticulo">Buscar
								artículo</a>
							<c:if
								test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">

								<a class="dropdown-item" href="../NuevoArticulo">Nuevo
									artículo</a>

								<a class="dropdown-item" href="../ListaArticulos">Lista de
									artículos</a>
								 <a class = "dropdown-item" href="../NuevaCategoria">Nueva Categoría</a>
								 <a class = "dropdown-item" href="../NuevaSubcategoria">Nueva Subcategoría</a>
							</c:if>

						</div></li>
					<c:if
						test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Proveedores </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="../ProveedorNuevo">Nuevo
									proveedor</a> <a class="dropdown-item" href="../ListaProveedores">Listar
									por artículos</a> <a class="dropdown-item "
									href="../BuscarProveedor">Proveedor específico</a>
							</div></li>
					</c:if>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Pedidos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
							<a class="dropdown-item" href="../GenerarPedido">Nuevo pedido</a>
							<a class="dropdown-item active" href="../ListaPedidos">Lista
								de pedidos</a>
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
								
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href='#' id="navbarDropdown5"
							role="button" data-toggle="dropdown" aria-haspoup="true"
							aria-expanded="false">Registros </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdown5">
									<a class="dropdown-item " href="../ListaRegistros">Lista de registros</a>
							</div>
						</li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="material-icons">perm_identity</i>
					</a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown2">
							<c:if
								test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">
								<a class="dropdown-item " href="../ListaUsuarios">Lista de
									usuarios</a>
								<c:if
									test="${usuarioActual.getRol().getNombre()=='SuperAdmin'}">
									<a class="dropdown-item" href="../UsuarioNuevo">Nuevo
										usuario</a>
									<a class="dropdown-item" href="../AreaNueva">Nueva Área</a>
									<a class="dropdown-item" href="../ListaAreas">Lista de
										áreas</a>
								</c:if>
							</c:if>
							<a class="dropdown-item" href="../CerrarSesion">Cerrar sesión</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content   -->

	<div class="container">
		<c:set var="Estado" scope="session"
			value="${pedido.estadopedido.getNombreEstado()}" />
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
							<td><c:out value="${pedido.fecha}" /></td>
							<td><c:out
									value="${pedido.usuario.getNombre()} ${pedido.usuario.getApellido() }" /></td>
							<td><c:out
									value="${pedido.usuario.getArea().getNombre()}" /></td>
							<td><c:out value="${pedido.estadopedido.getNombreEstado()}" /></td>
							<td><span> <a
									href="../Pedido?pedidoId=${pedido.pedidoId}">Detalle</a>
							</span>
							<td><c:choose>
									<c:when test="${Estado=='En Espera'}">
										<button class="btn btn-warning" type="button" title="Entregar"
											style="cursor: pointer"
											onclick="alertar('${pageContext.request.contextPath }/EntregarPedido?idEntregado=${pedido.pedidoId}');">
											<i class="material-icons"
												style="width: 18px; font-size: 18px">check_circle_outline</i>
										</button>

									</c:when>
									<c:when test="${Estado == 'En Curso'}">
										<button class="btn btn-outline-success" type="button"
											title="Entregar" style="cursor: pointer"
											onclick="alertar('${pageContext.request.contextPath }/EntregarPedido?idEntregado=${pedido.pedidoId}');">
											<i class="material-icons"
												style="width: 18px; font-size: 18px">check_circle_outline</i>
										</button>
									</c:when>
								</c:choose>
								<button class="btn btn-outline-info" type="button"
									title="Editar" style="cursor: pointer"
									onclick="window.location.href='../EditarPedido?pedidoId=${pedido.pedidoId}'">
									<i class="material-icons" style="font-size: 18px"> edit </i>
								</button>

								<button class="btn btn-outline-danger" type="button"
									id="eliminarPedidoButton" title="Eliminar"
									style="cursor: pointer"
									onclick="confirmar('${pageContext.request.contextPath }/EliminarPedido?idEliminado=${pedido.pedidoId }');">
									<i class="material-icons" style="font-size: 18px"> delete </i>
								</button></td>
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
