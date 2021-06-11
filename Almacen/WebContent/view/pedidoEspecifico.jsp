<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Pedido Número ${pedidoId }</title>
</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>
	<form method="post" action="${pageContext.request.contextPath }/Pedido">



		<p></p>
		<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">
			<table class="table">
				<tr>
					<th>ID:</th>
					<td>${pedidoId }</td>
					<th>Fecha:</th>
					<td>${pedidoFecha }</td>
					<th>Estado:</th>
					<td>${pedidoEstado}</td>
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
							<td>Categoría - Subcategoría</td>
						</tr>
					</thead>
					<tbody id="articulos" style="color: black; background-color: white;">
						<c:forEach items="${articulosPPedido}" var="articulo">
							<tr>
								<td>
									<c:out value="${articulo.cantidad}" />
								</td>
								<td>
									<c:out value="${articulo.articulo.getNombre()}" />
								</td>
								<td>
									<c:out value="${articulo.articulo.getSubcategoria().getCategoria().getNombre()} - ${articulo.articulo.getSubcategoria().getNombre()}" />
								</td>

							</tr>
						</c:forEach>
					</tbody>

				</table>
				<table id="tableOb">
					<tr>
						<td style="background-color: #f2f2f2">Observaciones:</td>
					</tr>

					<tr>
						<td style="text-align: left; padding-left: 20px;">${pedidoObs}</td>
					</tr>

				</table>

			</div>
		</div>
	</form>


</body>
</html>