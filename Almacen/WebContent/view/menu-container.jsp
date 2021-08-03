<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../vendor/bootstrap/datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet" type="text/css">

<!-- Bootstrap core JavaScript -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../vendor/bootstrap/datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="../vendor/bootstrap/datepicker/locales/bootstrap-datepicker.es.min.js"></script>
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">

<link href="../vendor/Datatables/DataTables-1.10.18/css/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Main CSS -->-

<link rel="stylesheet" href="../vendor/main.css" />





<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top" data-menuactive="${param.activeMenu}">
	<div class="container">
		<a class="navbar-brand" href="#">Almacén - Usuario ${usuarioActual.getRol().getNombre() }</a>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul id="menu" class="navbar-nav ml-auto">

				<!-- Articulos -->
				<li id="MenuArticulos" class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Artículos </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
						<a id="BuscarArticulo" class="dropdown-item" href="../BuscarArticulo">Buscar artículo</a>
						<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">
							<a id="NuevoArticulo" class="dropdown-item" href="../NuevoArticulo">Nuevo artículo</a>
							<a id="ListaArticulos" class="dropdown-item" href="../ListaArticulos">Lista de artículos</a>
							<div class="dropdown-divider"></div>
							<a id="Categorias" class="dropdown-item" href="../ListaCategorias">Categorías</a>
							<a id="Subcategorias" class="dropdown-item" href="../ListaSubcategorias">Subcategorías</a>
						</c:if>

					</div>
				</li>
				<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador'}">
					<li id="MenuProveedores" class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a id="ProveedorNuevo" class="dropdown-item" href="../ProveedorNuevo">Nuevo proveedor</a>
							<a id="ListaProveedores" class="dropdown-item" href="../ListaProveedores">Listar proveedores</a>
							<a id="BuscarProveedor" class="dropdown-item " href="../BuscarProveedor">Buscar proveedor específico</a>
						</div>
					</li>
				</c:if>
				<li id="MenuPedidos" class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Pedidos </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
						<a id="GenerarPedido" class="dropdown-item" href="../GenerarPedido">Nuevo pedido</a>
						<a id="ListaPedidos" class="dropdown-item" href="../ListaPedidos">Lista de pedidos</a>
					</div>
				</li>
				<li id="MenuTecnica" class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Técnica </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown3">
						<a id="ListaEquipos" class="dropdown-item" href="../ListaEquipos">Lista de equipos</a>
						<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador Tecnica'}">
							<a id="NuevoEquipo" class="dropdown-item " href="../NuevoEquipo">Nuevo equipo</a>
						</c:if>
						<div class="dropdown-divider"></div>
						<a id="BuscarGrupoEquipos" class="dropdown-item" href="../BuscarGrupoEquipos">Buscar grupo de equipos</a>
						<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador Tecnica'}">
							<a id="NuevoGrupoEquipo" class="dropdown-item " href="../NuevoGrupoEquipo">Nuevo grupo equipos</a>
							<div class="dropdown-divider"></div>
							<a id="Tipo" class="dropdown-item" href="../Tipo">Nuevo tipo</a>
							<a id="Lugar" class="dropdown-item " href="../Lugar">Nuevo lugar</a>
						</c:if>

					</div>
				</li>

				<li id="MenuLlaves" class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href='#' id="navbarDropdown4" role="button" data-toggle="dropdown" aria-haspoup="true" aria-expanded="false">Llaves </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown4">

						<c:choose>
							<c:when test="${usuarioActual.getRol().getNombre() == 'SuperAdmin' || usuarioActual.getRol().getNombre() == 'Administrador Llaves' }">
								<a id="ListaLlaves" class="dropdown-item" href="../ListaLlaves">Lista de llaves</a>
								<a id="NuevaLlave" class="dropdown-item" href="../NuevaLlave">Nueva llave</a>
								<div class="dropdown-divider"></div>
								<a id="BuscarGrupoLlaves" class="dropdown-item" href="../BuscarGrupoLlaves">Buscar grupo de llaves</a>
								<a id="NuevoGrupo" class="dropdown-item" href="../NuevoGrupo">Nuevo grupo</a>
							</c:when>
							<c:otherwise>
								<a id="ListaLlavesIndividual" class="dropdown-item" href="../ListaLlavesI">Llaves en posesión</a>
							</c:otherwise>
						</c:choose>

					</div>
				</li>


				<li id="MenuRegistros" class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href='#' id="navbarDropdown5" role="button" data-toggle="dropdown" aria-haspoup="true" aria-expanded="false">Registros </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown5">
						<a id="ListaRegistros" class="dropdown-item " href="../ListaRegistros">Lista de registros</a>
					</div>
				</li>

				<li id="MenuUsuario" class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="material-icons">perm_identity</i>
					</a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown2">
						<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'}">
							<a id="ListaUsuarios" class="dropdown-item " href="../ListaUsuarios">Lista de usuarios</a>
							<a id="UsuarioNuevo" class="dropdown-item" href="../UsuarioNuevo">Nuevo usuario</a>
							<div class="dropdown-divider"></div>
							<a id="ListaAreas" class="dropdown-item" href="../ListaAreas">Lista de áreas</a>
							<a id="AreaNueva" class="dropdown-item" href="../AreaNueva">Nueva Área</a>
							<div class="dropdown-divider"></div>
						</c:if>
						<a id="CerrarSesion" class="dropdown-item" href="../CerrarSesion">Cerrar sesión</a>
					</div>
				</li>
			</ul>
		</div>
	</div>
</nav>
<div class="loading" style="display: none;">
	<div class="loading-background"></div>
	<div class="loader"></div>
</div>


<script>
	$(window).on('pageshow', function(){
		$(".loading").hide();
	});
	
	$(document).ready(
			function() {
				
				var valueOfMenuActive = $("nav[data-menuactive]").attr(
						"data-menuactive");

				$("[id='" + valueOfMenuActive + "']").addClass("active");

				$("[id='" + valueOfMenuActive + "']").parents("li").addClass(
						"active")

			});

	$(document).ready(function() {
		$("a[href!='#'][href]").click(function(){
			console.log("loading...");
			$(".loading").fadeIn("slow");
		});
	});
</script>
