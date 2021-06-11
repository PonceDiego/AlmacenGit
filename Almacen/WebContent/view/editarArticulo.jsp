<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">


<title>Editar Artículo</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../EditarArticulo">
					<h3 class="text-center">Datos del artículo a editar</h3>
					<div class="row">
						<div class="column">
							<div>Nombre del Artículo</div>
							<c:set value="${artEdit }" var="articulo"></c:set>
							<input type="hidden" name="editadoId" id="editadoId" value="${articulo.getArticuloId() }">
							<div class="form-label-group>">
								<input type="text" name="inputNombre" id="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off" value="${articulo.getNombre() }" disabled="disabled">
							</div>
							<div>Stock Mínimo</div>
							<div class="form-label-group>">
								<input type="text" name="inputSMinimo" oninput="this.value=this.value.replace(/[^0-9]/g,'');" class="form-control" placeholder="Stock Mínimo" required autocomplete="off" value="${articulo.getStockMinimo() }">
							</div>
							<div>Categoría</div>
							<div class="form-label-group> searchable">
								<input type="text" placeholder="Categoría" name="input" id="input" onkeyup="filterFunction(this,event)" required autocomplete="off" value="${articulo.getSubcategoria().getCategoria().getNombre()}">
								<c:set var="categoria" value="${categoriasListadas}" scope="application"></c:set>
								<ul>
									<c:forEach items="${categoria}" var="categoria">
										<li>-${categoria.getNombre()}</li>
									</c:forEach>
								</ul>
							</div>
							<div style="margin-top: 10px">Proveedor</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputProveedor" id="inputProveedor" autocomplete="off" value="${articulo.getProveedor().getNombre() }"> <select onchange="selected()" id="provSelect"
									style="border-radius: 5px; font-size: 16px; padding: 10px;" required>
									<c:forEach items="${proveedores}" var="proveedor">
										<c:choose>
											<c:when test="${articulo.getProveedor().getNombre() == proveedor.getNombre()}">
												<option selected>-${proveedor.getNombre()}</option>
											</c:when>
											<c:otherwise>
												<option>-${proveedor.getNombre()}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>

							</div>

						</div>
						<div class="column">
							<div>Costo</div>
							<div class="form-label-group>">
								<input type="text" name="inputCosto" class="form-control" placeholder="Costo" onchange="validarSiNumero(this.value);" id="costoinput" required autocomplete="off" value="${articulo.getCosto() }">
							</div>

							<div>Stock Actual</div>
							<div class="form-label-group>">
								<input type="text" name="inputStock" class="form-control" placeholder="Stock Actual" required autocomplete="off" value="${articulo.getStock() }">
							</div>
							<div>Subcategoría</div>
							<div class=" form-label-group>" id="divSub">
								<input type="hidden" name="inputSub" id="inputSub" value='${articulo.getSubcategoria().getNombre() }'> <select onchange="selected2()" id="selectSub" style="border-radius: 5px; font-size: 16px; padding: 10px;" required>
									<option selected>${articulo.getSubcategoria().getNombre() }</option>
									<c:forEach items="${subCats}" var="scategoria">
										<option style="cursor: pointer" class="${scategoria.getCategoria().getNombre()} listaSub">-${scategoria.getNombre()}</option>
									</c:forEach>
								</select>

							</div>


						</div>
					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
	<script>
		function validarSiNumero(numero) {
			if (!/^([0-9,.])*$/.test(numero)) {

				document.getElementById("costoinput").className = document
						.getElementById("costoinput").className
						+ " error";
				document.getElementById("aceptarbutton").disabled = true;
			} else {
				document.getElementById("costoinput").className = document
						.getElementById("costoinput").className.replace(
						" error", "");
				document.getElementById("aceptarbutton").disabled = false;
			}

		}
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
	</script>
</body>

</html>