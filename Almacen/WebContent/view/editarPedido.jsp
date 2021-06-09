<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Editar Pedido Número ${pedidoId }</title>
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

.form-signin {
	width: 100%;
}

.form-signin .btn {
	font-size: 80%;
	border-radius: 5rem;
	letter-spacing: .1rem;
	font-weight: bold;
	padding: 1rem;
	transition: all 0.2s; . form-label-group { position : relative;
	margin-bottom: 1rem;
}

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



	<form method="post" class="form-singin"
		action="${pageContext.request.contextPath }/EditarPedido">


		<p></p>
		<div class="text-center lead"
			style="outline: 1px solid black; max-width: 70%; margin: auto">
			<table class="table">
				<tr>
					<th>ID:</th>
					<td>${pedidoId }<input type="hidden" name="idPEditado"
						value="${pedidoId }">
					</td>

					<th>Fecha:</th>
					<td>${pedidoFecha }</td>
					<th>Estado:</th>
					<td><select name="estadoPEditado">
							<c:forEach items="${estadosPedidoPosibles }" var="es">
								<c:choose>
									<c:when test="${es.getNombreEstado()==pedidoEstado }">
										<option selected>${es.getNombreEstado()}</option>
									</c:when>
									<c:otherwise>
										<option>${es.getNombreEstado() }</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<th>Usuario:</th>
					<td>${pedidoUsuario}</td>
					<th>Área:</th>
					<td>${pedidoArea}</td>
			</table>
			<hr>

			<div class="text-center lead" style="width: 100%">
				<!-- tablita de articulos -->
				<table id="tablaArticulosPedido">
					<thead>
						<tr>
							<td>Cantidad</td>
							<td>Artículo</td>
							<td>Categoría</td>
						</tr>
					</thead>
					<tbody id="articulos"
						style="color: black; background-color: white;">
						<c:forEach items="${articulosPPedido}" var="articulo">
							<tr>
								<td><input type="text" value="${articulo.cantidad}"
									name="cantidad"
									oninput="this.value=this.value.replace(/[^0-9]/g,'');"
									style="width: 20%"></td>
								<td><select class="nombreArts">
										<c:forEach items="${articulos }" var="arts">
											<c:choose>
												<c:when
													test="${arts.getNombre()==articulo.articulo.getNombre() }">
													<option selected>${arts.getNombre() }</option>
												</c:when>
												<c:otherwise>
													<option>${arts.getNombre() }</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select></td>
								<td><c:out
										value="${articulo.articulo.getSubcategoria().getNombre()}" /></td>

							</tr>
						</c:forEach>
					</tbody>

				</table>
				<table id="tableOb">
					<tr>
						<td style="background-color: #f2f2f2">Observaciones:</td>
					</tr>
					<tr>
						<td style="padding: 5px;"><textarea id='textArea'
								style="resize: none; border-radius: 12px" cols="80" rows="3"
								maxlength="200">${pedidoObs}</textarea></td>
					</tr>

				</table>

			</div>
			<hr>
			<input type="hidden" id='cantidades' name='cantidades'> <input
				type="hidden" id='nombres' name='nombres'> <input
				type="hidden" id='observaciones' name='observaciones'>
			<button class="btn btn-lg btn-primary btn-block text-uppercase"
				id="aceptarbutton" onmouseenter="setValues();"
				style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">
				Aceptar</button>
		</div>
	</form>

</body>
<script>
function setValues(){
	var cantidad= document.getElementsByName("cantidad");
	var art= document.getElementsByClassName('nombreArts');
	var cantidades= document.getElementById('cantidades');
	var nombres= document.getElementById('nombres');
	cantidades.value="";
	nombres.value="";
	var observaciones=document.getElementById('observaciones');
	var textA= document.getElementById('textArea');
	observaciones.value=textA.value;
	
	for(var i=0;i<cantidad.length;i++){
		cantidades.value+=cantidad[i].value+",";
		nombres.value+=art[i].value+",";
		}
	
}
</script>
</html>