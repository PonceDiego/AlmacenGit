<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Editar Pedido Número ${pedidoId }</title>
<jsp:include page="header.jsp"/>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuPedidos" />
	</jsp:include>

	<form method="post" class="form-singin" action="${pageContext.request.contextPath }/EditarPedido">


		<p></p>
		<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">
			<table class="table">
				<tr>
					<th>ID:</th>
					<td>${pedidoId }<input type="hidden" name="idPEditado" value="${pedidoId }">
					</td>

					<th>Fecha:</th>
					<td>${pedidoFecha }</td>
					<th>Estado:</th>
					<td><select name="estadoPEditado">
							<c:forEach items="${estadosPedidoPosibles }" var="es">
								<c:choose>
									<c:when test="${es.getNombreEstado()==pedidoEstado }">
										<option selected>${es.getNombreEstado()}</option>
									</c:when>
									<c:otherwise>
										<option>${es.getNombreEstado() }</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<th>Usuario:</th>
					<td>${pedidoUsuario}</td>
					<th>Área:</th>
					<td>${pedidoArea}</td>
			</table>
			<hr>

			<div class="text-center lead" style="width: 100%">
				<!-- tablita de articulos -->
				<table id="tablaArticulosPedido">
					<thead>
						<tr>
							<td>Cantidad</td>
							<td>Artículo</td>
							<td>Subcategoría</td>
						</tr>
					</thead>
					<tbody id="articulos" style="color: black; background-color: white;">
						<c:forEach items="${articulosPPedido}" var="articulo">
							<tr>
								<td><input type="text" value="${articulo.cantidad}" name="cantidad" oninput="this.value=this.value.replace(/^[0\D]+/g,'');" style="width: 20%"></td>
								<td><select class="nombreArts">
										<c:forEach items="${articulos }" var="arts">
											<c:choose>
												<c:when test="${arts.getNombre()==articulo.articulo.getNombre() }">
													<option selected>${arts.getNombre() }</option>
												</c:when>
												<c:otherwise>
													<option>${arts.getNombre() }</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select></td>
								<td><c:out value="${articulo.articulo.getSubcategoria().getNombre()}" /></td>

							</tr>
						</c:forEach>
					</tbody>

				</table>
				<table id="tableOb">
					<tr>
						<td style="background-color: #f2f2f2">Observaciones:</td>
					</tr>
					<tr>
						<td style="padding: 5px;"><textarea id='textArea' style="resize: none; border-radius: 12px" cols="80" rows="3" maxlength="200">${pedidoObs}</textarea></td>
					</tr>

				</table>

			</div>
			<hr>
			<input type="hidden" id='cantidades' name='cantidades'> <input type="hidden" id='nombres' name='nombres'> <input type="hidden" id='observaciones' name='observaciones'>
			<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" onmouseenter="setValues();" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
		</div>
	</form>

</body>
<script>
	function setValues() {
		var cantidad = document.getElementsByName("cantidad");
		var art = document.getElementsByClassName('nombreArts');
		var cantidades = document.getElementById('cantidades');
		var nombres = document.getElementById('nombres');
		cantidades.value = "";
		nombres.value = "";
		var observaciones = document.getElementById('observaciones');
		var textA = document.getElementById('textArea');
		observaciones.value = textA.value;

		for (var i = 0; i < cantidad.length; i++) {
			cantidades.value += cantidad[i].value + ",";
			nombres.value += art[i].value + ",";
		}

	}
</script>
</html>