<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Proveedor ${proveedorNombre }</title>
<jsp:include page="header.jsp"/>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuProveedores" />
	</jsp:include>

	<form method="post" action="../Proveedor">



		<p></p>
		<h2 class="mt-5 text-center">${proveedorNombre }<span>
				<i class="material-icons" onclick="window.location.href=('../EditarProveedor?PvI=${provID}')" title="Editar" style="font-size: 18px; cursor: pointer">edit</i>
			</span>
		</h2>
		<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">

			<table class="table">
				<tr>
					<th>ID:</th>
					<td>${provID}</td>
					<th>Teléfono:</th>
					<td>${provTel}</td>
					<th>Persona de contacto:</th>
					<td>${provContacto}</td>
				</tr>
				<tr>
					<th>Dirección:</th>
					<td>${provDireccion}</td>
					<th></th>
					<td></td>
					<th>Mail:</th>
					<td>${proveedorMail}</td>
				</tr>


			</table>
		</div>
		<div class="text-center" style="width: 70%; margin: auto">
			<!-- tablita de articulos -->
			<h3 class="mt-5 text-center">Artículos</h3>
			<table id="tablaArticulosProveedor" class="table">
				<tr>
					<th>Nombre</th>
					<th>Categoría</th>
					<th>Subcategoría</th>
					<th>Stock</th>
				</tr>
				<c:forEach items="${proveedorArticulos}" var="articulo">
					<tr>
						<td>
							<a href="../Articulo?articuloID=${articulo.getArticuloId() }">
								<c:out value="${articulo.getNombre()}" />
							</a>
						</td>
						<td>
							<c:out value="${articulo.getSubcategoria().getCategoria().getNombre()}" />
						</td>
						<td>
							<c:out value="${articulo.getSubcategoria().getNombre()}" />
						</td>
						<td>
							<c:out value="${articulo.getStock() }"></c:out>
						</td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</form>


</body>
</html>