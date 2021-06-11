<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Editar llave ${llaveAEditar.getNombre()}</title>
</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuLlaves" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../NuevaLlave">
					<h3 class="text-center">Datos de la llave</h3>
					<div class="row">
						<div class="column">
							<div>Nombre de la llave</div>

							<input type="text" name="inputNombre" id="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off" value="${llaveAEditar.getNombre() }" disabled="disabled">

							<div>Copia n°</div>

							<input type="number" name="inputCopia" id="inputCopia" class="form-control" placeholder="Copia" required autocomplete="off" value="${llaveAEditar.getCopia() }" disabled="disabled">
						</div>
						<div class="column">

							<div>Ubicación</div>
							<div class="form-label-group>">
								<input type="hidden" name="inputUbicacion" class="form-control" id="inputUbicacion" required autocomplete="off"> <select onchange="selected()" id="ubicacionSelect" style="border-radius: 5px; font-size: 16px; padding: 10px" required>
									<c:forEach items="${ubicaciones}" var="ubicacion">

										<c:if test="${ubicacion.nombre == llaveAEditar.getUbicacion().getNombre() }">
											<option selected value="${ubicacion.nombre }">${ubicacion.nombre }</option>
											<c:otherwise>
												<option value="${ubicacion.nombre }">${ubicacion.nombre }</option>
											</c:otherwise>
										</c:if>
									</c:forEach>
								</select>

							</div>
						</div>
					</div>
					<div>Observaciones</div>
					<input type="text" name="inputObservaciones" class="form-control" placeholder="Observaciones" maxlength="140" title="Observaciones" autocomplete="off" value="${llaveAEditar.getObservaciones()}">



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
</body>
</html>