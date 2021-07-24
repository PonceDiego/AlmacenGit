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
<meta charset="UTF-8">

<title>Editar Tipo</title>
<jsp:include page="header.jsp" />

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="Tipo" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../EditarTipo">
					<h3 class="text-center">Datos del tipo</h3>
					<div class='text-center' id="advertencia" style="color: red">
					</div>
					<hr class="m-4">
					<div class="row">
						<div class="column">
							<div>Nombre del Tipo</div>
							<div class="form-label-group> searchable">
								<input tabindex="1" type="text" name="inputNombre"
									id="inputNombre" class="form-control"
									placeholder="Nombre del tipo" required autocomplete="off"
									onchange="comparar()" value ="${nombreTipoEditar }"
									onkeyup="filterFunction(this,event),botonAceptar()">
								<c:set var="articulo" value="${listaTipos }"></c:set>
								<ul id="ulArticulos">
									<c:forEach items="${articulo }" var="art">
										<li value="${art.getNombre()}">${art.getNombre()}</li>
									</c:forEach>
								</ul>
							</div>

						</div>
					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase"
						id="aceptarbutton" disabled
						style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>

	<script>
		function filterFunction(that, event) {
			document.getElementById('advertencia').innerHTML = "";
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
						comparar();
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
					comparar();
				});

		$(".searchable ul li").hover(
				function() {
					$(this).closest(".searchable").find("ul li.selected")
							.removeClass("selected");
					$(this).addClass("selected");
				});
		function onSelect(val) {

			var x = document.getElementById('advertencia');
			x.innerHTML = "Ya existe un tipo con el nombre " + val + "!";
			var y = document.getElementById('aceptarbutton');
			y.disabled = true;

		}

		function comparar() {
			var IsEqual = false;

			var x = document.getElementById('inputNombre');

			var a = x.value;

			$('#ulArticulos li').each(function(i) {
				var valueLi = $(this).attr('value');

				if (a === valueLi) {
					IsEqual = true;
				}
			});

			if (IsEqual) {
				onSelect(a);
			} else {
				x.innerHTML = "";
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
	</script>
</body>
</html>