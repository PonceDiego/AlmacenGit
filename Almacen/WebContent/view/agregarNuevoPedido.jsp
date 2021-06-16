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

<title>Agregar Nuevo Pedido</title>
<jsp:include page="header.jsp"/>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="GenerarPedido" />
	</jsp:include>

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
						<div>Seleccionar usuario</div>

						<select id="selectUsuarios" onchange="setUser();"
							style="border-radius: 5px; padding: 2px;">
							<c:forEach items="${listaUsuario}" var="usuario">
								<c:choose>
									<c:when test="${usuario.getId() == usuarioActual.getId() }">
										<option selected value="${usuario.getId()}">${usuario.getNombre()}${usuario.getApellido()}</option>
									</c:when>
									<c:otherwise>
										<option value="${usuario.getId()}">${usuario.getNombre()}${usuario.getApellido()}</option>
									</c:otherwise>
								</c:choose>
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
								type="hidden" name="inputCantidad" id="inputCantidad"> <input
								type="hidden" name="UserId" id="UserId"
								value="${usuarioActual.getId()}">
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