<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Llave Nueva</title>
</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="NuevaLlave" />
	</jsp:include>

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
								<input tabindex="1" type="text" name="inputNombre" id="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off" onkeyup="filterFunction2(this,event),botonAceptar()">
								<c:set var="llaves" value="${listaLlaves }"></c:set>
								<ul id="ulLlaves">
									<c:forEach items="${llaves }" var="llave">
										<li value="${llave.getNombre()}-${llave.getCopia()}">${llave.getNombre()}</li>
									</c:forEach>
								</ul>
							</div>
							<div>Copia n°</div>

							<input tabindex="3" type="number" name="inputCopia" id="inputCopia" class="form-control" placeholder="Copia" required autocomplete="off" value="1" onchange="comparar()">
						</div>
						<div class="column">

							<div>Ubicación</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputUbicacion" class="form-control" id="inputUbicacion" required autocomplete="off"> <select tabindex="2" onchange="selected()" id="ubicacionSelect"
									style="border-radius: 5px; font-size: 16px; padding: 10px" required>
									<option selected disabled>Seleccione una ubicación</option>
									<c:forEach items="${ubicaciones}" var="ubicacion">
										<option value="${ubicacion.nombre }">${ubicacion.nombre }</option>
									</c:forEach>
								</select>

							</div>
						</div>
					</div>
					<div>Observaciones</div>
					<input tabindex="4" type="text" name="inputObservaciones" class="form-control" placeholder="Observaciones" maxlength="140" title="Observaciones" autocomplete="off">



					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
	<script>
		function selected() {
			var x = document.getElementById("ubicacionSelect").value;

			document.getElementById("inputUbicacion").value = x;
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

		function comparar() {
			var valor = $('.selected');

			var x = document.getElementById('inputNombre');
			var z = document.getElementById('inputCopia').value;
			var a = x.value + "-" + z;

			console.log(a);
			console.log(valor.attr('value'));
			if (valor.attr('value') === a) {
				onSelect2(valor.text(), $('#inputCopia').val());
			} else {
				var x = document.getElementById('advertencia');
				var editar = document.getElementById('linkEditar');
				x.innerHTML = "";
				editar.text = "";
				editar.href = "";
				var y = document.getElementById('aceptarbutton');
				y.disabled = false;
			}
		}
		function botonAceptar() {
			var listaMatches = document.getElementById('ulLlaves');
			var lis = listaMatches.getElementsByTagName('li:visible');
			var x = document.getElementById('inputNombre');
			var z = document.getElementById('inputCopia');
			for (var i = 0; i < lis.length; i++) {
				var y = lis[i].value.split("-");
				if (y[0] === x.value && y[1] === z.value) {
					document.getElementById('aceptarbutton').disabled = true;
				} else {
					document.getElementById('aceptarbutton').disabled = false;
				}
			}
		}

		$(".searchable2 ul li").hover(
				function() {
					$(this).closest(".searchable2").find("ul li.selected")
							.removeClass("selected");
					$(this).addClass("selected");
				});
		function onSelect2(val, copia) {
			var x = document.getElementById('advertencia');
			var editar = document.getElementById('linkEditar');
			x.innerHTML = "Ya existe una llave con el nombre " + val
					+ " y número de copia " + copia + "!";
			editar.text = "Editar";
			editar.href = "../EditarLlave?nombreEditado=" + val + "&copia="
					+ copia;
			var y = document.getElementById('aceptarbutton');
			y.disabled = true;

		}
	</script>

</body>
</html>