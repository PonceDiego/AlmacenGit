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
<jsp:include page="header.jsp"/>

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
								<input type="text" name="inputNombre" id="inputNombre" tabindex="1" class="form-control" placeholder="Nombre" required autocomplete="off" maxlength="50" onkeyup="filterFunction2(this,event),botonAceptar()" onchange="comparar()">
								<c:set var="equipos" value="${listaEquipos }"></c:set>
								<ul id="ulArticulos">
									<c:forEach items="${equipos }" var="equipo">
										<li>${equipo.getNombre()}</li>
									</c:forEach>
								</ul>
							</div>
							<div>Tipo</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputTipo" id="inputTipo" autocomplete="off"> <select class='form-control input-lg'  tabindex="3" onchange="selected(),comparar()" id="tipoSelect" required>
									<option selected disabled>Seleccione un tipo</option>
									<c:forEach items="${listaTipos}" var="tipo">
										<option value="${tipo.getNombre() }">${tipo.getNombre()}</option>
									</c:forEach>
								</select>
							</div>
							<div>Ubicación</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputLugar" id="inputLugar" autocomplete="off"> <select class='form-control input-lg'  tabindex="5" onchange="selected2(),comparar()" id="lugarSelect" required>
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
								<input type="hidden" name="inputUsuario" id="inputUsuario" autocomplete="off"> <select class='form-control input-lg'  tabindex="6" onchange="selected3(),comparar()" id="usuarioSelect">
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
					<button tabindex="9" class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" disabled
					 style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
	<script>
		function selected() {
			var x = document.getElementById("tipoSelect").value;

			document.getElementById("inputTipo").value = x;
		}
	</script>
	<script>
		function selected2() {
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
	function comparar() {
		var x = document.getElementById('advertencia');
		var y = document.getElementById('inputTipo');
		var z = document.getElementById('inputLugar');
		var a = document.getElementById("inputUsuario");
		
		if (y.value == null || y.value === "") {
			x.innerHTML = "Por favor seleccione un tipo!";
		} else if (z.value == null || z.value === "") {
			x.innerHTML = "Por favor seleccione un lugar!";
		}  else if (a.value == null || a.value === "") {
			x.innerHTML = "Por favor seleccione un usuario!";
		}else {
			x.innerHTML = "";
			var y = document.getElementById('aceptarbutton');
			y.disabled = false;
		}

	}
	
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

		function keyControl(e, container) {
			if (e.key == "ArrowDown") {

				if (container.find("ul li").hasClass("selected")) {
					if (container.find("ul li:visible").index(
							container.find("ul li.selected")) + 1 < container
							.find("ul li:visible").length) {
						container.find("ul li.selected")
								.removeClass("selected").nextAll().not(
										'[style*="display: none"]').first()
								.addClass("selected");
					}

				} else {
					container.find("ul li:first-child").addClass("selected");
				}

			} else if (e.key == "ArrowUp") {

				if (container.find("ul li:visible").index(
						container.find("ul li.selected")) > 0) {
					container.find("ul li.selected").removeClass("selected")
							.prevAll().not('[style*="display: none"]').first()
							.addClass("selected");
				}
			} else if (e.key == "Enter") {
				if (container.find("input").val(
						container.find("ul li.selected").text) != "") {
				}
				container.find("input").val(
						container.find("ul li.selected").text()).blur();
				comparar();
			}

			container.find("ul li.selected")[0].scrollIntoView({
				behavior : "smooth",
			});
		}

		$(".searchable2 input").focus(function() {
			$(this).closest(".searchable2").find("ul").show();
			$(this).closest(".searchable2").find("ul li").show();
		});
		$(".searchable2 input").blur(function() {
			let that = this;
			setTimeout(function() {
				$(that).closest(".searchable2").find("ul").hide();
			}, 300);
		});

		$(document).on(
				'click',
				'.searchable2 ul li',
				function() {
					$(this).closest(".searchable2").find("input").val(
							$(this).text()).blur();

					comparar();

				});
		
		
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