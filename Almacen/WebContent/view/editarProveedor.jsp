<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">


<title>Editar Proveedor</title>
<jsp:include page="header.jsp"/>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuProveedores" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<form class="form-signin" method="post" action="../EditarProveedor">
			<div class="card card-signin my-5">
				<div class="card-body">
					<h3 class="text-center">Datos del proveedor</h3>
					<c:set var="proveedor" value="${proveedorE }"></c:set>
					<input type="hidden" value="${proveedor.getId() }" name="PvI" id="PvI">
					<div class="row">
						<div class="column">
							<div>Nombre</div>
							<div class="form-label-group>">
								<input type="text" name="PvN" class="form-control" placeholder="Nombre" required value="${proveedor.getNombre() }">
							</div>
							<div>Teléfono</div>
							<div class="form-label-group>">
								<input type="text" name="PvT" class="form-control" placeholder="Teléfono" required oninput="this.value=this.value.replace(/[^0-9]/g,'');" value="${proveedor.getTelefono() }">
							</div>
							<div>Contacto</div>
							<div class="form-label-group>">
								<input type="text" name="PvC" class="form-control" placeholder="Persona contacto" value="${proveedor.getContacto() }">
							</div>


						</div>
						<div class="column">
							<div>Dirección</div>
							<div class="form-label-group>">
								<input type="text" name="PvD" class="form-control" placeholder="Dirección" value="${proveedor.getDireccion() }">
							</div>
							<div>Correo Electrónico</div>
							<div class="form-label-group>">
								<input type="text" name="PvM" class="form-control" placeholder="Mail" required value="${proveedor.getMail()}">
							</div>

						</div>


					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer">Aceptar</button>
				</div>
			</div>
		</form>
	</div>
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
		function onSelect(val) {

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
	</script>
</body>

</html>