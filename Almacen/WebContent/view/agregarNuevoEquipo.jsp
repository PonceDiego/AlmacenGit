<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">



<title>Agregar Nuevo Equipo</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="NuevoEquipo" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../NuevoEquipo">
					<h3 class="text-center">Datos del nuevo equipo</h3>
					<div class="row">
						<div class="column">
							<div>Nombre del Equipo</div>
							<div id="advertencia" style="color: red"></div>
							<span><a href="" id="linkEditar"> </a></span>
							<div class="form-label-group> searchable2">
								<input type="text" name="inputNombre" id="inputNombre" tabindex="1" class="form-control" placeholder="Nombre" required autocomplete="off" maxlength="50" onkeyup="filterFunction2(this,event),botonAceptar()">
								<c:set var="equipos" value="${listaEquipos }"></c:set>
								<ul id="ulArticulos">
									<c:forEach items="${equipos }" var="equipo">
										<li>${equipo.getNombre()}</li>
									</c:forEach>
								</ul>
							</div>
							<div>Tipo</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputTipo" id="inputTipo" autocomplete="off"> <select tabindex="3" onchange="selected()" id="tipoSelect" style="border-radius: 5px; font-size: 16px; padding: 10px; margin-top: 10px" required>
									<option selected disabled>Seleccione un tipo</option>
									<c:forEach items="${listaTipos}" var="tipo">
										<option value="${tipo.getNombre() }">${tipo.getNombre()}</option>
									</c:forEach>
								</select>
							</div>
							<div>Ubicación</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputLugar" id="inputLugar" autocomplete="off"> <select tabindex="5" onchange="selected2()" id="lugarSelect" style="border-radius: 5px; font-size: 16px; padding: 10px; margin-top: 10px" required>
									<option selected disabled>Seleccione un lugar</option>
									<c:forEach items="${listaLugares}" var="lugar">
										<option value="${lugar.getNombre() }">${lugar.getNombre()}</option>
									</c:forEach>
								</select>
							</div>
							<div>Accesorios</div>
							<div class="form-label-group>">
								<input tabindex="7" type="text" name="inputAccesorios" class="form-control" placeholder="Accesorios" id="inputAccesorios" maxlength="140" autocomplete="off">
							</div>
						</div>

						<div class="column">
							<div>Modelo</div>
							<div class="form-label-group>">
								<input tabindex="2" type="text" name="inpuModelo" class="form-control" placeholder="Modelo" id="inpuModelo" maxlength="50" autocomplete="off">
							</div>
							<div>Serial</div>
							<div class="form-label-group>">
								<input tabindex="4" type="text" name="inputSerial" class="form-control" placeholder="Serial" id="inputSerial" maxlength="50" autocomplete="off">
							</div>

							<div>Usuario habitual</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputUsuario" id="inputUsuario" autocomplete="off"> <select tabindex="6" onchange="selected3()" id="usuarioSelect" style="border-radius: 5px; font-size: 16px; padding: 10px; margin-top: 10px">
									<option selected disabled>Seleccione un usuario</option>
									<c:forEach items="${listaUsuarios}" var="usuario">
										<option value=${usuario.getNombreUsuario() }>${usuario.getNombreUsuario() }</option>
									</c:forEach>
								</select>
							</div>
							<div>Observaciones</div>
							<div class="form-label-group>">
								<input tabindex="8" type="text" name="inputObservaciones" class="form-control" placeholder="Observaciones" id="inputObservaciones" maxlength="140" autocomplete="off">
							</div>

						</div>
					</div>
					<hr>
					<button tabindex="9" class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
	<script>
		function selected() {
			console.log("selected");
			var x = document.getElementById("tipoSelect").value;

			document.getElementById("inputTipo").value = x;
		}
	</script>
	<script>
		function selected2() {
			console.log("selected2");
			var x = document.getElementById("lugarSelect").value;

			document.getElementById("inputLugar").value = x;
		}
	</script>
	<script>
		function selected3() {
			var x = document.getElementById("usuarioSelect").value;
			if (x == "Seleccione un usuario habitual") {
				document.getElementById("inputUsuario").value = "null";
			} else {
				document.getElementById("inputUsuario").value = x;
			}
		}
	</script>
	<script>
		function filterFunction2(that, event) {
			document.getElementById('advertencia').innerHTML = "";
			document.getElementById('linkEditar').innerHTML = "";
			let container, input, filter, li, input_val;
			container = $(that).closest(".searchable2");
			input_val = container.find("input").val().toUpperCase();

			if ([ "ArrowDown", "ArrowUp", "Enter" ].indexOf(event.key) != -1) {
				keyControl(event, container)
			} else {
				li = container.find("ul li");
				li.each(function(i, obj) {
					if ($(this).text().toUpperCase().indexOf(input_val) > -1) {
						$(this).show();
					} else {
						$(this).hide();
					}
				});

				container.find("ul li").removeClass("selected");
				setTimeout(function() {
					container.find("ul li:visible").first()
							.addClass("selected");
				}, 100)
			}
		}
		function botonAceptar() {
			var listaMatches = document.getElementById('ulArticulos');
			var lis = listaMatches.getElementsByTagName('li:visible');
			var x = document.getElementById('inputNombre')
			for (var i = 0; i < lis.length; i++) {
				if (lis[i].value == x.value) {
					document.getElementById('aceptarbutton').disabled = "true";
				} else {
					document.getElementById('aceptarbutton').disabled = "false";
				}
			}
		}
	</script>
</body>

</html>