<%@page import="com.sun.xml.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">



<title>Agregar Nuevo Pedido</title>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
.addBtn {
	padding: 5px;
	width: 25%;
	background: #007bff;
	color: #ffffff;
	float: center;
	text-align: center;
	font-size: 16px;
	cursor: pointer;
	transition: 0.3s;
	border-radius: 25px;
}

.addBtn:hover {
	background-color: #0265cf;
}

.aceptarBtn {
	float: right;
	padding: 10px;
	width: 25%;
	background: #f37321;
	color: #ffffff;
	text-align: center;
	font-size: 16px;
	cursor: pointer;
	transition: 0.3s;
	border-radius: 25px;
}

.aceptarBtn:hover {
	background-color: #ff9933;
}
/* Style the close button */
.close {
	position: absolute;
	right: 0;
	top: 0;
	padding: 12px 16px 12px 16px;
	cursor: pointer;
	right: 0;
}

.close:hover {
	background-color: #f44336;
	color: white;
}
/* Style the list items */
div.pedido ul li {
	position: relative;
	padding: 12px 8px 12px 40px;
	list-style-type: none;
	background: #eee;
	font-size: 18px;
	transition: 0.2s;
	/* make the list items unselectable */
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* Set all odd list items to a different color (zebra-stripes) */
.pedido ul li:nth-child(odd) {
	background: #f9f9f9;
}

/* Darker background-color on hover */
.pedido ul li:hover {
	background: #ddd;
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
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Pedidos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
							<a class="dropdown-item active" href="../GenerarPedido">Nuevo
								pedido</a> <a class="dropdown-item" href="../ListaPedidos">Lista
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
				<h3 class="text-center">Generar nuevo pedido</h3>
				<hr class="m-4">


				<c:set value="${usuarioActual.getRol().getNombre() }"
					var="rolActual"></c:set>
				<c:if test="${rolActual=='SuperAdmin'||rolActual=='Administrador'}">
					<div style="margin: auto; text-align: center;">

						<select id="selectUsuarios" onchange="setUser();"
							style="border-radius: 5px; padding: 2px;">
							<option selected disabled>Seleccionar usuario</option>
							<c:forEach items="${listaUsuario}" var="usuario">
								<option value="${usuario.getId()}">${usuario.getNombre()}
									${usuario.getApellido()}</option>
							</c:forEach>
						</select>

						<hr class="m-4">

					</div>
				</c:if>


				<div class="row">

					<div class="column">

						<select id="myInput" style="border-radius: 5px; padding: 2px">
							<c:choose>
								<c:when test="${nombreArt !=null}">
									<option selected>${nombreArt}</option>
								</c:when>
								<c:otherwise>
									<option selected disabled>Seleccionar artículo</option>
								</c:otherwise>
							</c:choose>
							<c:forEach items="${subcategorias}" var="subc">
								<optgroup label="${subc.getNombre() }"></optgroup>
								<c:forEach items="${subc.getArticulos()}" var="articulo">
									<option>${articulo.getNombre()}</option>
								</c:forEach>
							</c:forEach>
						</select> <input style="border-radius: 5px" type="text" id="cantidad"
							placeholder="Cantidad" autocomplete="off"
							oninput="this.value=this.value.replace(/[^0-9]/g,'');"> <span
							onclick="newElement()" class="addBtn">Agregar</span>
					</div>
					<div class="column">
						<form action="../AgregarPedido" id="nuevoPedido">
							<button class="aceptarBtn" id="aceptar"
								onmouseover="aceptarBoton(this)">Aceptar</button>
							<input type="hidden" id="inputArt" name="inputArt"> <input
								type="hidden" name="inputCantidad" id="inputCantidad">
							<c:choose>
								<c:when
									test="${rolActual=='SuperAdmin'||rolActual=='Administrador'}">
									<input type="hidden" name="UserId" id="UserId"
										form="nuevoPedido">
								</c:when>
								<c:otherwise>
									<input type="hidden" name="UserId" id="UserId"
										value="${usuarioActual.getId()}">
								</c:otherwise>
							</c:choose>
						</form>
						<textarea rows="2" cols="30" maxlength="140"
							name="textAreaObservaciones" id="textAreaObservaciones"
							form="nuevoPedido" style="resize: none; border-radius: 12px"
							onkeyup="charcountupdate(this.value)" placeholder="Observaciones"></textarea>
						<span id=charcount style="font-size: small;">0/140</span>
					</div>
				</div>
				<hr class="m-2">
				<div class="pedido">
					<ul id="myUL">
					</ul>
				</div>
			</div>
		</div>
	</div>

	<script>
		function aceptarBoton(boton) {
			var ul = document.getElementById("myUL");
			var lis = ul.getElementsByTagName("li");
			var contador = 0;

			for (var i = 0; i < lis.length; i++) {
				if (lis[i].style.display == '') {
					contador = contador + 1;
				}
			}
			if (contador < 1) {
				boton.disabled = true;
			} else {
				boton.disabled = false;
			}
		}
	</script>

	<script>
		function setUser() {
			var x = document.getElementById("selectUsuarios");
			var y = x.options[x.selectedIndex].value;
			document.getElementById("UserId").value = y;
		}
	</script>
	<script>
		// Se crea una x para eliminar el articulo de la lista
		var myNodelist = document.getElementsByTagName($('#myUl li'));
		var i;
		for (i = 0; i < myNodelist.length; i++) {
			var span = document.createElement("SPAN");
			var txt = document.createTextNode("\u00D7");
			span.className = "close";
			span.appendChild(txt);
			myNodelist[i].appendChild(span);
		}

		// Clickear la x oculta el elemento
		var close = document.getElementsByClassName("close");
		var i;
		for (i = 0; i < close.length; i++) {
			close[i].onclick = function() {
				//sacar del nuevo pedido!
				var div = this.parentElement;
				div.style.display = "none";
			}
		}
		function newElement() {
			var li = document.createElement("li");
			var cant = document.getElementById("inputCantidad");
			var arts = document.getElementById("inputArt");

			var inputCant = document.getElementById("cantidad").value;
			var inputValue = document.getElementById("myInput").value;
			var t = document.createTextNode(inputCant + " - " + inputValue);
			li.appendChild(t);

			if (inputValue === 'Seleccionar artículo') {
				alert("Por favor seleccione un artículo");
			} else {
				if (inputCant === '') {
					alert("Por favor ingrese una cantidad");
				} else {
					document.getElementById("myUL").appendChild(li);
					//se le asignan los valores de las cantidades y articulos seleccionados a los inputs correspondientes del form.
					arts.value += inputValue + " - ";
					cant.value += inputCant + " - ";
				}

			}

			document.getElementById("myInput").value = "Seleccionar artículo";

			var span = document.createElement("SPAN");
			var txt = document.createTextNode("\u00D7");
			span.className = "close";
			span.appendChild(txt);
			li.appendChild(span);

			for (i = 0; i < close.length; i++) {
				close[i].onclick = function() {
					var div = this.parentElement;
					div.style.display = "none";
				}
			}
		}

		function charcountupdate(str) {
			var lng = str.length;
			document.getElementById("charcount").innerHTML = lng + '/140';
		}
	</script>
</body>
</html>