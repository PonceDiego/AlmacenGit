<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Proveedores</title>



</head>


<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="ListaProveedores" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1 class="mt-5">Lista de proveedores por artículo.</h1>

				<h1>&nbsp;</h1>

				<table class="table table-striped" id="tablaProveedores">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Artículos</th>
							<th>Mail</th>
						</tr>
					</thead>
					<tbody id="proveedores">
						<c:forEach items="${listaProveedores}" var="proveedor">
							<tr>
								<td id="pID">
									<c:out value="${proveedor.getId()}" />
								</td>
								<td id="pNB">
									<a href="../Proveedor?proveedorId=${proveedor.getId()}">${proveedor.getNombre()}</a>
								<td>
									<c:forEach items="${proveedor.getArticulos() }" var="articulo">
										<c:out value="-${articulo.getNombre()}" />
										<br>
									</c:forEach>
								</td>
								<td id="pM">
									<c:out value="${proveedor.getMail()}" />
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="../vendor/Datatables/datatables.js"></script>



	<script>
		$(document).ready(function() {

			$('#tablaProveedores').DataTable({
				"columnDefs" : [ {
					"orderable" : false,
					"targets" : [ 2 ]
				} ]
			});
		});
	</script>
</body>
</html>