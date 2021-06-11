<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">


<title>Agregar Nuevo Artículo</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../NuevoArticulo">
					<h3 class="text-center">Datos del nuevo artículo</h3>
					<div class="row">
						<div class="column">
							<div>Nombre del Artículo</div>
							<div id="advertencia" style="color: red"></div>
							<span><a href="" id="linkEditar"> </a></span>
							<div class="form-label-group> searchable2">
								<input tabindex="1" type="text" name="inputNombre" id="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off" onchange="comparar()" onkeyup="filterFunction2(this,event),botonAceptar()">
								<c:set var="articulo" value="${listaArticulos }"></c:set>
								<ul id="ulArticulos">
									<c:forEach items="${articulo }" var="art">
										<li value="${art.getNombre()}">${art.getNombre()}</li>
									</c:forEach>
								</ul>
							</div>
							<div>Stock Mínimo</div>
							<div class="form-label-group>">
								<input tabindex="3" type="text" name="inputSMinimo" oninput="this.value=this.value.replace(/[^0-9]/g,'');" class="form-control" placeholder="Stock Mínimo" required autocomplete="off">
							</div>
							<div>Categoría</div>
							<div class="form-label-group> searchable">
								<input tabindex="5" type="text" placeholder="Categoría" name="input" id="input" onkeyup="filterFunction(this,event)" required autocomplete="off">
								<c:set var="categoria" value="${categoriasListadas}" scope="application"></c:set>
								<ul>
									<c:forEach items="${categoria}" var="categoria">
										<li>-${categoria.nombre }</li>
									</c:forEach>
								</ul>
							</div>
							<div>Proveedor</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputProveedor" id="inputProveedor" autocomplete="off"> <select onchange="selected()" tabindex="7" id="provSelect" style="border-radius: 5px; font-size: 16px; padding: 10px" required>
									<option selected disabled>Seleccione un proveedor</option>
									<c:forEach items="${proveedores}" var="proveedor">

										<option>-${proveedor.nombre }</option>

									</c:forEach>
								</select>

							</div>

						</div>
						<div class="column">
							<div>Costo</div>
							<div class="form-label-group>">
								<input tabindex="2" type="text" name="inputCosto" class="form-control" placeholder="Costo" oninput="this.value=this.value.replace(/[^0-9]/g,'');" id="costoinput" required autocomplete="off">
							</div>

							<div>Stock Actual</div>
							<div class="form-label-group>">
								<input tabindex="4" type="text" name="inputStock" class="form-control" placeholder="Stock Actual" required autocomplete="off" oninput="this.value=this.value.replace(/[^0-9]/g,'');">
							</div>
							<div>Subcategoría</div>
							<div class=" form-label-group>" id="divSub">
								<input type="hidden" name="inputSub" id="inputSub"> <select tabindex="6" onchange="selected2()" id="selectSub" style="border-radius: 5px; font-size: 16px; padding: 10px;" required>
									<option disabled selected>Seleccione una subcategoría</option>
									<c:forEach items="${subCats}" var="scategoria">
										<option style="cursor: pointer" class="${scategoria.getCategoria().getNombre()} listaSub">-${scategoria.getNombre()}</option>
									</c:forEach>
								</select>

							</div>


						</div>
					</div>
					<hr>
					<button tabindex="8" class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
	<script>
		function selected() {
			var x = document.getElementById("provSelect").value;

			document.getElementById("inputProveedor").value = x;
		}
	</script>
	<script>
		function selected2() {
			var x = document.getElementById("selectSub").value;

			document.getElementById("inputSub").value = x;
		}
	</script>
	<script>
		function filterFunction(that, event) {
			let container, input, filter, li, input_val;
			container = $(that).closest(".searchable");
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
				container.find("input").val(
						container.find("ul li.selected").text()).blur();
				onSelect(container.find("ul li.selected").text())
			}

			container.find("ul li.selected")[0].scrollIntoView({
				behavior : "smooth",
			});
		}

		$(".searchable input").focus(function() {
			$(this).closest(".searchable").find("ul").show();
			$(this).closest(".searchable").find("ul li").show();
		});
		$(".searchable input").blur(function() {
			let that = this;
			setTimeout(function() {
				$(that).closest(".searchable").find("ul").hide();
			}, 300);
		});

		$(document).on(
				'click',
				'.searchable ul li',
				function() {
					$(this).closest(".searchable").find("input").val(
							$(this).text()).blur();

					onSelect($(this).text())

				});

		$(".searchable ul li").hover(
				function() {
					$(this).closest(".searchable").find("ul li.selected")
							.removeClass("selected");
					$(this).addClass("selected");
				});
		function onSelect(val) {

			var x = val.split("-");
			var y = document.getElementsByClassName('listaSub'), i;
			var z = document.getElementsByClassName(x[1]);

			for (var i = 0; i < y.length; i++) {
				y[i].style.display = "none";
			}
			for (var i = 0; i < z.length; i++) {
				z[i].style.display = "block";
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
		function comparar() {
			var valor = $('.selected');

			var x = document.getElementById('inputNombre');
			var a = x.value;

			if (valor.attr('value') === a) {
				onSelect2(valor.text());
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
			var listaMatches = document.getElementById('ulArticulos');
			var lis = listaMatches.getElementsByTagName('li:visible');
			var x = document.getElementById('inputNombre')
			for (var i = 0; i < lis.length; i++) {
				if (lis[i].value == x.value) {
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
		function onSelect2(val) {

			var x = document.getElementById('advertencia');
			var editar = document.getElementById('linkEditar');
			x.innerHTML = "Ya existe un artículo con el nombre " + val + "!";
			editar.text = "Editar";
			editar.href = "../EditarArticulo?nombreEditado=" + val;
			var y = document.getElementById('aceptarbutton');
			y.disabled = true;

		}
	</script>
</body>

</html>