<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Artículo: ${articuloNombre }</title>
<jsp:include page="header.jsp"/>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuArticulo" />
	</jsp:include>

	<input type="hidden" name="articuloID" id="articuloID" value="${articuloId}">
	<h2 class="mt-5 text-center">${articuloNombre}
		<span>
			<button onclick="window.location.href=('../GenerarPedido?articuloAgregar=${articuloNombre}')" title="Añadir a pedido" style="cursor: pointer" class="btn-outline-info btn">
				<i class="material-icons">add_shopping_cart</i>
			</button>
		</span> <span><button class="btn btn-outline-dark" style="cursor: pointer" title="Ver código QR" onclick="window.open('../Qr?articuloID=${articuloId}','_blank')">
				<i class="material-icons"> border_outer </i>
			</button></span>
	</h2>
	<hr>
	<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">

		<table class="table">
			<tr>
				<th>Categoría:</th>
				<td>${articuloCatPadre}</td>
				<th>Subcategoría:</th>
				<td>${articuloCat}</td>
			</tr>
			<tr>
				<th>Estado:</th>
				<td>${articuloEstado.getNombreEstado()}</td>
				<th>Stock Actual:</th>
				<td>${articuloStock}</td>
			</tr>
		</table>
	</div>
</body>
</html>