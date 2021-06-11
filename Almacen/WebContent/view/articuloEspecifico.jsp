<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Artículo: ${articuloNombre }</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuArticulo" />
	</jsp:include>

	<input type="hidden" name="articuloID" id="articuloID" value="${articuloId}">
	<h2 class="mt-5 text-center">${articuloNombre}
		<span>
			<button onclick="window.location.href=('${pageContext.request.contextPath }/GenerarPedido?articuloAgregar=${articuloNombre}')" title="Añadir a pedido" style="cursor: pointer" class="btn-outline-info btn">
				<i class="material-icons">add_shopping_cart</i>
			</button>
		</span> <span><button class="btn btn-outline-dark" style="cursor: pointer" title="Ver código QR" onclick="window.open('${pageContext.request.contextPath }/Qr?entidadId=${articuloId}&entidad=Articulo','_blank')">
				<i class="material-icons"> border_outer </i>
			</button></span><span>
			<button class="btn btn-outline-warning" style="cursor: pointer" title="Añadir stock" data-toggle="modal" data-target="#id01">
				<i class="material-icons">library_add</i>
			</button>
		</span>
	</h2>
	<hr>
	<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">

		<table class="table">
			<tr>
				<th>Categoría:</th>
				<td>${articuloCatPadre}</td>
				<th>Subcategoría:</th>
				<td>${articuloCat}</td>
				<th>Estado:</th>
				<td>${articuloEstado.getNombreEstado()}</td>
			</tr>
			<tr>
				<th>Fecha agregado:</th>
				<td>${articuloFecha}</td>

				<th>Stock Mínimo:</th>
				<td>${articuloStockMinimo}</td>
				<th>Stock Actual:</th>
				<td>${articuloStock}</td>
			</tr>
			<tr>
				<th>Costo:</th>
				<td>$${articuloCosto}</td>
				<th>Proveedor:</th>
				<c:forEach items="${artProveedores}" var="prov">


					<td><a href="../Proveedor?proveedorId=${prov.getId()}"> ${prov.getNombre() }</a></td>
				</c:forEach>
			</tr>
		</table>
	</div>


	<div id="id01" class="modal modal.fade" aria-hidden="true">
		<div class="modal-content align-content-md-center		">
			<div class="modal-header text-center">
				<h4 class="modal-titlefont-weight-bold">Ingrese la cantidad de stock a agregar</h4>

				<button type="button" class="close" data-dismiss="modal" aria-label="Cerrar" style="cursor: pointer; background-color: red;">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body mx-3">
				<form method="get" action="../ActualizarStock">
					<div class="mb-5 form-inline">
						<input type="hidden" name="articuloID" id="articuloID" value="${articuloId}"> <input type="text" id="cantidad" name="cantidad" onchange="validarSiNumero(this.value);">
					</div>
					<div class="modal-footer d-flex justify-content-center">
						<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 30%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
					</div>
				</form>
			</div>

		</div>


	</div>
	<script>
		function validarSiNumero(numero) {
			if (!/^([0-9,.])*$/.test(numero)) {

				document.getElementById("cantidad").className = document
						.getElementById("cantidad").className
						+ " error";
				document.getElementById("aceptarbutton").disabled = true;
			} else {
				document.getElementById("cantidad").className = document
						.getElementById("cantidad").className.replace(" error",
						"");
				document.getElementById("aceptarbutton").disabled = false;
			}

		}
	</script>
</body>
</html>