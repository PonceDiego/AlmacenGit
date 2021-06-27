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

<title>Grupo de Llaves ${grupoLlaves.getNombre() }</title>
<jsp:include page="header.jsp" />

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuLlaves" />
	</jsp:include>

	<h2 class="mt-5 text-center">${grupoLlaves.getNombre() }
		<span><button class="btn btn-outline-dark"
				style="cursor: pointer" title="Ver código QR"
				onclick="window.open('${pageContext.request.contextPath }/Qr?entidadId=${grupoLlaves.getGrupoId()}&entidad=GrupoLlaves','_blank')">
				<i class="material-icons"> border_outer </i>
			</button></span> <span><button class="btn btn-outline-dark"
				style="cursor: pointer" title="Editar"
				onclick="window.location.href=('${pageContext.request.contextPath }/EditarGrupoLlaves?idAEditar=${grupoLlaves.getGrupoId()}')">
				<i class="material-icons"> edit </i>
			</button></span>
		<button class="btn btn-outline-danger" type="button" title="Eliminar"
			style="cursor: pointer"
			onclick="confirmar('${pageContext.request.contextPath }/EliminarGrupoLlaves?idEliminado=${grupoLlaves.getGrupoId()}');">
			<i class="material-icons"> delete </i>
		</button>
	</h2>
	<div class="text-center">
		<c:if test="${mostrarBoton }">
			<form method="get" action="../CambioEstado" id="formMethod">
				<input type="hidden" id="cambioId" name="cambioId"
					value="${grupoLlaves.getGrupoId()}"> <input type="hidden"
					id="salida" name="salida"
					value="${grupoLlavesEstado == 'En uso' ? '0' : '1'}"> <input
					type="hidden" id="entidad" name="entidad" value="GrupoLlaves">
				<c:choose>
					<c:when test="${grupoLlavesEstado == 'Disponible'}">
						<button class="btn btn-primary btn-lg mb-1" data-toggle="modal"
							data-target="#modalUsuario" type="button" title="Salida"
							style="cursor: pointer"
							onclick="setDataModal('${grupoLlaves.getGrupoId()}','${grupoLlavesEstado}')">Salida</button>


					</c:when>

					<c:when test="${grupoLlavesEstado == 'En uso'}">
						<button class="btn btn-primary btn-lg mb-1" type="submit"
							title="Entrada" style="cursor: pointer">Entrada</button>
					</c:when>
				</c:choose>
			</form>
		</c:if>
	</div>
	<div class="text-center lead"
		style="outline: 1px solid black; max-width: 70%; margin: auto">

		<table class="table">
			<tr>
				<th>ID:</th>
				<td>${grupoLlaves.getGrupoId()}</td>
				<th>Estado:</th>
				<td>${grupoLlavesEstado}</td>
			</tr>
		</table>
	</div>

	<div class="text-center" style="width: 70%; margin: auto">
		<!-- tabla de llaves -->
		<h3 class="mt-5 text-center">Llaves</h3>
		<table id="tablaLlaves" class="table">
			<tr>
				<th>Nombre</th>
				<th>Copia</th>
				<th>Estado</th>
			</tr>
			<c:forEach items="${grupoLlaves.llaves}" var="llave">
				<tr>
					<td><c:out value="${llave.getNombre()}" /></td>
					<td><c:out value="${llave.getCopia()}" /></td>
					<td><c:out value="${llave.getEstado()}" /></td>

				</tr>
			</c:forEach>
		</table>
	</div>

	<div id="modalUsuario" class="modal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div
				class="modal-content align-content-md-center col-l-6 col-m-6 col-s-auto col-xl-10">
				<div class="modal-header text-center">
					<h4 class="modal-titlefont-weight-bold ">Ingrese el usuario
						solicitante</h4>
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						data-target="id01" aria-label="Cerrar">X</button>
				</div>
				<div>
					<form method="get" action="../CambioEstado" id="formMethod">
						<div class="form-inline mt-2 ml-2 mb-2 mx-2 ">
							<div class=" form-label-group> searchable">
								<input type="hidden" id="cambioIdModal" name="cambioId">
								<input type="hidden" id="salidaModal" name="salida"> <input
									type="hidden" id="entidadModal" name="entidad"
									value="GrupoLlaves"> <input type="text"
									placeholder="Buscar usuario" name="solicitanteId"
									id="solicitanteId" value="${usuarioActual.getNombreUsuario()}"
									onkeyup="filterFunction(this,event)" required
									autocomplete="off">
								<c:set var="usuarios" value="${usuarios }"></c:set>
								<ul id="ulUsusarios">
									<c:forEach items="${usuarios}" var="usuario">

										<li value="${usuario}">${usuario}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="modal-footer d-flex justify-content-center">
							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								id="aceptarbutton"
								style="max-width: min-content; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>


</body>
<script>
	function confirmar(url) {
		var r = confirm("¿Está seguro que desea eliminar el grupo de llaves?");
		if (r == true) {
			$(location).attr('href', url);
		}
	}
</script>

<script>
	function setDataModal(llaveId, estado) {
		console.log(llaveId + estado);
		var x = document.getElementById('cambioIdModal');
		var y = document.getElementById('salidaModal');
		x.value = llaveId;

		if (estado == "En uso") {
			y.value = "0";
		} else {
			y.value = "1";
		}
	}
</script>
<script>
	function filterFunction(that, event) {
		let container, input, filter, li, input_val;
		container = $(that).closest(".searchable");
		input_val = container.find("#solicitanteId").val().toUpperCase();

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
				container.find("ul li:visible").first().addClass("selected");
			}, 100)
		}
	}

	function keyControl(e, container) {
		if (e.key == "ArrowDown") {

			if (container.find("ul li").hasClass("selected")) {
				if (container.find("ul li:visible").index(
						container.find("ul li.selected")) + 1 < container
						.find("ul li:visible").length) {
					container.find("ul li.selected").removeClass("selected")
							.nextAll().not('[style*="display: none"]').first()
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
			container.find("#solicitanteId").val(
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
				$(this).closest(".searchable").find("#solicitanteId").val(
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
	}
</script>
</html>