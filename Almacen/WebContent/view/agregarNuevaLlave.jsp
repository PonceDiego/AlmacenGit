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


<style>
div.searchable {
	float: left;
	width: 100%;
}

.searchable input {
	width: 100%;
	font-size: 18px;
	padding: 10px;
	-webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
	-moz-box-sizing: border-box; /* Firefox, other Gecko */
	box-sizing: border-box; /* Opera/IE 8+ */
	display: block;
	font-weight: 400;
	line-height: 1.6;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	background:
		url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 4 5'%3E%3Cpath fill='%23343a40' d='M2 0L0 2h4zm0 5L0 3h4z'/%3E%3C/svg%3E")
		no-repeat right .75rem center/8px 10px;
}

.searchable ul {
	display: none;
	list-style-type: none;
	background-color: #fff;
	border-radius: 0 0 5px 5px;
	border: 1px solid #add8e6;
	border-top: none;
	max-height: 180px;
	margin: 0;
	overflow-y: scroll;
	overflow-x: hidden;
	padding: 0;
}

.searchable ul li {
	padding: 7px 9px;
	border-bottom: 1px solid #e1e1e1;
	cursor: pointer;
	color: #6e6e6e;
}

.searchable ul li.selected {
	background-color: #e8e8e8;
	color: #333;
}

.form-signin {
	width: 100%;
}

.form-signin .btn {
	font-size: 80%;
	border-radius: 5rem;
	letter-spacing: .1rem;
	font-weight: bold;
	padding: 1rem;
	transition: all 0.2s;
}

.form-label-group {
	position: relative;
	margin-bottom: 1rem;
}

.form-label-group input {
	height: auto;
	border-radius: 2rem;
}

.form-label-group>input, .form-label-group>label {
	padding: var(- -input-padding-y) var(- -input-padding-x);
}

.form-label-group>label {
	position: absolute;
	top: 0;
	left: 0;
	display: block;
	width: 100%;
	margin-bottom: 0;
	/* Override default `<label>` margin */
	line-height: 1.5;
	color: #495057;
	border: 1px solid transparent;
	border-radius: .25rem;
	transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
	color: transparent;
}

.form-label-group input:-ms-input-placeholder {
	color: transparent;
}

.form-label-group input::-ms-input-placeholder {
	color: transparent;
}

.form-label-group input::-moz-placeholder {
	color: transparent;
}

.form-label-group input::placeholder {
	color: transparent;
}

.form-label-group input:not (:placeholder-shown ) ~label {
	padding-top: calc(var(- -input-padding-y)/3);
	padding-bottom: calc(var(- -input-padding-y)/3);
	font-size: 12px;
	color: #777;
}

.card-signin {
	border: 0;
	border-radius: 1rem;
	box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}

.card-signin .card-title {
	margin-bottom: 2rem;
	font-weight: 300;
	font-size: 1.5rem;
}

.card-signin .card-body {
	padding: 2rem;
}

.column {
	padding: 10px;
	flex: 50%;
}

.form-control {
	margin-bottom: 1rem;
	font-size: 18px;
	border-radius: .25rem;
}

.error {
	border: 2px solid red;
}
</style>

<title>Llave Nueva</title>
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
								equipos</a> <a class="dropdown-item" href="../BuscarGrupoEquipos">Buscar grupo de equipos</a>
							<c:if
								test="${usuarioActual.getRol().getNombre()=='SuperAdmin'||usuarioActual.getRol().getNombre()=='Administrador Técnica'}">
								<a class="dropdown-item " href="../ListaRegistros">Lista de
									registros</a>
								<a class="dropdown-item " href="../NuevoEquipo">Nuevo equipo</a> <a class="dropdown-item active" href="../NuevoGrupoEquipo">Nuevo grupo equipos</a> 
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
								llaves</a> <a class="dropdown-item" href="../BuscarGrupoLlaves">Buscar grupo de llaves</a>
							<c:if
								test="${usuarioActual.getRol().getNombre() == 'SuperAdmin' || usuarioActual.getRol().getNombre() == 'Administrador Llaves' }">
								<a class="dropdown-item active" href="../NuevaLlave">Nueva
									llave</a>
								<a class="dropdown-item" href="../NuevoGrupo">Nuevo grupo</a>
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
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../NuevaLlave">
					<h3 class="text-center">Datos de la nueva llave</h3>
					<div class="row">
						<div class="column">
							<div>Nombre de la llave</div>
							<div id="advertencia" style="color: red"></div>
							<span><a href="" id="linkEditar"> </a></span>
							<div class="form-label-group> searchable2">
								<input type="text" name="inputNombre" id="inputNombre"
									class="form-control" placeholder="Nombre" required
									autocomplete="off"
									onkeyup="filterFunction2(this,event),botonAceptar()">
								<c:set var="llaves" value="${listaLlaves }"></c:set>
								<ul id="ulLlaves">
									<c:forEach items="${llaves }" var="llave">
										<li>${llave.getNombre()}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="column">

							<div>Ubicación</div>
							<div class="form-label-group>">
								<input type="text" name="inputUbicacion" class="form-control"
									placeholder="Ubicación" required autocomplete="off">
							</div>
						</div>
					</div>
					<div>Observaciones</div>
					<input type="text" name="inputObservaciones" class="form-control"
						placeholder="Observaciones" maxlength="140" title="Observaciones"
						autocomplete="off" >



					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase"
						id="aceptarbutton"
						style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">
						Aceptar</button>
				</form>
			</div>
		</div>

	</div>


</body>
</html>