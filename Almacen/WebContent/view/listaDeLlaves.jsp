<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Llaves</title>
<jsp:include page="header.jsp"/>



</head>


<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="ListaLlaves" />
	</jsp:include>


	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">
				Lista de llaves. <span> <a href="../ListaLugares"
					style="font-size: small;"> Lugares 🡕</a>
				</span> <span> <a href="../BuscarGrupoLlaves"
					style="font-size: small;">Grupos 🡕</a>
				</span>
			</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Copia</th>
						<th>Grupo</th>
						<th>Lugar</th>
						<th>Observaciones</th>
						<th>Disponible</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody id="tablaEquipos">

					<c:forEach items="${llaves}" var="llave">
						<tr>
							<td><c:out value="${llave.getNombre()}" /></td>
							<td><c:out value="${llave.getCopia() }" /></td>
							<td><c:out value="${llave.getGrupoLlaves().getNombre() }" />
							</td>
							<td><c:out value="${llave.getLugar().getNombre()}" /></td>
							<td><c:out value="${llave.getObservaciones()}" /></td>
							<td><c:choose>
									<c:when test="${llave.getEstado()== 'Disponible'}">
										<a style="color: green;"> <i class="material-icons">check</i>
										</a>
									</c:when>
									<c:when test="${llave.getEstado() == 'En uso'}">
										<a style="color: red;"> <i class="material-icons">clear</i>
										</a>
									</c:when>
									<c:otherwise>
										<a> <i class="material-icons">trending_down</i>
										</a>
									</c:otherwise>
								</c:choose></td>

							<td><c:choose>
									<c:when test="${llave.getEstado() == 'Disponible'}">
										<button class="btn btn-warning" type="button" title="Salida"
											style="cursor: pointer" data-toggle="modal" onclick="setDataModal('${llave.getLlaveId()}','${llave.getEstado()}')"
											data-target="#modalLlaves">S</button>

									</c:when>

									<c:when test="${llave.getEstado() == 'En uso'}">
									<form method="get" action="../CambioEstado" id="formMethod">
									<input type="hidden" id="cambioId" name="cambioId" value="${llave.getLlaveId()}">
									<input type="hidden" id="salida" name="salida" value="${llave.getEstado() == 'En uso' ? '0' : '1'}">
									<input type="hidden" id="entidad" name="entidad" value="Llave">
									
										<button class="btn btn-outline-success" type="submit"
										 onclick="setData('${llave.getLlaveId()}','${llave.getEstado()}')"
											title="Entrada" style="cursor: pointer">E</button>
											</form>
									</c:when>
								</c:choose>
								<button class="btn btn-outline-info" type="button" style="cursor: pointer" title="Editar" onclick="window.location.href='../EditarLlave?nombreEditado=${llave.getNombre()}&copia=${llave.getCopia() }'">
									<i class="material-icons" style="font-size: 18px"> edit </i>
								</button>
								<button class="btn btn-outline-danger" type="button"
									title="Eliminar"
									style="cursor: pointer"
									onclick="confirmar('${pageContext.request.contextPath }/EliminarLlave?idEliminado=${llave.getLlaveId()}');">
									<i class="material-icons" style="font-size: 18px"> delete </i>
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div id="modalLlaves" class="modal" aria-hidden="true">
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
							<input type="hidden" id="salidaModal" name="salida">
							<input type="hidden" id="entidadModal" name="entidad" value="Llave">
								<input type="text" placeholder="Buscar usuario"
									name="solicitanteId" id="solicitanteId"
									value="${usuarioActual.getNombreUsuario()}"
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
								style="max-width: min-content; margin: auto; background-color: #f37321; cursor: pointer;"
								>Aceptar</button>
										
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<script src="../vendor/Datatables/datatables.js"></script>

	<script>
		function setData(llaveId,estado) {
			console.log(llaveId+estado);
			var x = document.getElementById('cambioId');
			var y = document.getElementById('salida');
			x.value = llaveId;
			
			
			if (estado == "En uso") {
				y.value = "0";
			}else{
				y.value = "1";
			}
		}
		
		function setDataModal(llaveId,estado) {
			console.log(llaveId+estado);
			var x = document.getElementById('cambioIdModal');
			var y = document.getElementById('salidaModal');
			x.value = llaveId;
			
			
			if (estado == "En uso") {
				y.value = "0";
			}else{
				y.value = "1";
			}
		}
	</script>

	<script>	
		function confirmar(url) {
			var r = confirm("¿Está seguro que desea eliminar la llave?");
			if (r == true) {
				$(location).attr('href', url);
			}
		}
	</script>

	<script>
		$(document).ready(function() {

			$('#myTable').DataTable({
				"columnDefs" : [ {
					"responsive" : "true",
					"orderable" : false,
					"targets" : [ 5, 6 ]
				} ],
				"language" : {
					"emptyTable" : "No se encontraron llaves a mostrar!"
				}
			})
		});
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
</body>
</html>
