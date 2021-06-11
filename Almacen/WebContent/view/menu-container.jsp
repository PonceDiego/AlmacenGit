<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/vendor/iconfont/material-icons.css" rel="stylesheet" type="text/css">

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<link href="../vendor/Datatables/DataTables-1.10.18/css/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Main CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/main.css" />


<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
	<div class="container">
		<a class="navbar-brand" href="#">Almac�n - Usuario ${usuarioActual.getRol().getNombre() }</a>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">

				<!-- Articulos -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Art�culos </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
						<a class="dropdown-item" href="../BuscarArticulo">Buscar art�culo</a>
						<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">

							<a class="dropdown-item" href="../NuevoArticulo">Nuevo art�culo</a>
							<a class="dropdown-item" href="../ListaArticulos">Lista de art�culos</a>
							<a class="dropdown-item" href="../NuevaCategoria">Nueva Categor�a</a>
							<a class="dropdown-item" href="../NuevaSubcategoria">Nueva Subcategor�a</a>
						</c:if>

					</div>
				</li>
				<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../ProveedorNuevo">Nuevo proveedor</a>
							<a class="dropdown-item" href="../ListaProveedores">Listar por art�culos</a>
							<a class="dropdown-item " href="../BuscarProveedor">Proveedor espec�fico</a>
						</div>
					</li>
				</c:if>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Pedidos </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
						<a class="dropdown-item" href="../GenerarPedido">Nuevo pedido</a>
						<a class="dropdown-item" href="../ListaPedidos">Lista de pedidos</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">T�cnica </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown3">
						<a class="dropdown-item" href="../ListaEquipos">Lista de equipos</a>
						<a class="dropdown-item" href="../BuscarGrupoEquipos">Buscar grupo de equipos</a>
						<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador T�cnica'}">

							<a class="dropdown-item " href="../NuevoEquipo">Nuevo equipo</a>
							<a class="dropdown-item " href="../NuevoGrupoEquipo">Nuevo grupo equipos</a>
							<a class="dropdown-item" href="../Tipo">Nuevo tipo</a>
							<a class="dropdown-item " href="../Lugar">Nuevo lugar</a>
						</c:if>

					</div>
				</li>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href='#' id="navbarDropdown4" role="button" data-toggle="dropdown" aria-haspoup="true" aria-expanded="false">Llaves </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown4">

						<a class="dropdown-item" href="../ListaLlaves">Lista de llaves</a>
						<a class="dropdown-item" href="../BuscarGrupoLlaves">Buscar grupo de llaves</a>
						<c:if test="${usuarioActual.getRol().getNombre() == 'SuperAdmin' || usuarioActual.getRol().getNombre() == 'Administrador Llaves' }">
							<a class="dropdown-item" href="../NuevaLlave">Nueva llave</a>
							<a class="dropdown-item" href="../NuevoGrupo">Nuevo grupo</a>
						</c:if>

					</div>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href='#' id="navbarDropdown5" role="button" data-toggle="dropdown" aria-haspoup="true" aria-expanded="false">Registros </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown5">
						<a class="dropdown-item " href="../ListaRegistros">Lista de registros</a>
					</div>
				</li>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="material-icons">perm_identity</i>
					</a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown2">
						<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">
							<a class="dropdown-item " href="../ListaUsuarios">Lista de usuarios</a>
							<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'}">
								<a class="dropdown-item" href="../UsuarioNuevo">Nuevo usuario</a>
								<a class="dropdown-item" href="../AreaNueva">Nueva �rea</a>
								<a class="dropdown-item" href="../ListaAreas">Lista de �reas</a>
							</c:if>
						</c:if>
						<a class="dropdown-item" href="../CerrarSesion">Cerrar sesi�n</a>
					</div>
				</li>
			</ul>
		</div>
	</div>
</nav>
