<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">



<title>Grupo de Equipos Nuevo</title>

<style>
.form-signin .btn {
	font-size: 80%;
	border-radius: 5rem;
	letter-spacing: .1rem;
	font-weight: bold;
	padding: 1rem;
	transition: all 0.2s;
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
								equipos</a> <a class="dropdown-item" href="../BuscarGrupoEquipos">Buscar
								grupo de equipos</a>
							<c:if
								test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador Técnica'}">
								<a class="dropdown-item " href="../ListaRegistros">Lista de
									registros</a>
								<a class="dropdown-item " href="../NuevoEquipo">Nuevo equipo</a>
								<a class="dropdown-item " href="../NuevoGrupoEquipo">Nuevo
									grupo equipos</a>
								<a class="dropdown-item" href="../Tipo">Nuevo tipo</a>
								<a class="dropdown-item " href="../Lugar">Nuevo lugar</a>
							</c:if>

						</div></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href='#' id="navbarDropdown4"
						role="button" data-toggle="dropdown" aria-haspoup="true"
						aria-expanded="false">Llaves </a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown4">

							<a class="dropdown-item" href="../ListaLlaves">Lista de
								llaves</a> <a class="dropdown-item" href="../BuscarGrupoLlaves">Buscar
								grupo de llaves</a>
							<c:if
								test="${usuarioActual.getRol().getNombre() == 'SuperAdmin' || usuarioActual.getRol().getNombre() == 'Administrador Llaves' }">
								<a class="dropdown-item" href="../NuevaLlave">Nueva llave</a>
								<a class="dropdown-item active" href="../NuevoGrupo">Nuevo
									grupo</a>
							</c:if>

						</div></li>

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
								<c:if test="${usuarioActual.getRol().getNombre()=='SuperAdmin'}">
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
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="get" action="../NuevoGrupo">
					<h3 class="text-center">Datos del nuevo grupo de llaves</h3>
					<hr class="m-4">
					<div class="column">
						<div class="form-label-group>">
							<input type="text" name="inputNombre" class="form-control"
								placeholder="Nombre del grupo" required autocomplete="off">
						</div>



						<script type="text/javascript">
							$(document).ready(function() {
								$('#boot-multiselect-llaves').multiselect({
									nonSelectedText : 'Seleccionar llaves',
									buttonWidth : 500,
									enableFiltering : true
								});
							});
						</script>
						<input type = "hidden" name="inputLlaves" id="inputLlaves" autocomplete="off">
						<select id="boot-multiselect-llaves" name="boot-multiselect-llaves" multiple="multiple" onchange="selected()">
							<c:forEach items="${llaves}" var="llave">
								<option value="${llave.getLlaveId()}">${llave.getNombre() }
									- ${llave.getLlaveId() }</option>
							</c:forEach>
						</select>


					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase"
						id="aceptarbutton"
						style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">
						Aceptar</button>
				</form>
			</div>
		</div>

	</div>
	<script>
		function selected(){
			var x = document.getElementById("boot-multiselect-llaves").value;
			document.getElementById("inputLlaves").value = x;
			
		}
	</script>


</body>
</html>