<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html lang="en">


<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Lugares</title>



</head>


<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="" value="" />
	</jsp:include>


	<!-- Page Content   -->

	<div class="container">
		<div class="col-lg-12 text-center">
			<h1 class="mt-5">Lista de lugares.</h1>


			<hr class="my-4">

			<div>&nbsp;</div>
			<table class="table table-striped table-bordered" id="myTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Descripción</th>
					</tr>
				</thead>
				<tbody id="tablaAreas">

					<c:forEach items="${listaLugares}" var="tipo">
						<tr>
							<td>
								<c:out value="${tipo.getNombre()}" />
							</td>
							<td>
								<c:out value="${tipo.getDescripcion()}" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<script src="../vendor/Datatables/datatables.js"></script>



	<script>
		$(document).ready(function(){

			$('#myTable').DataTable({
				"responsive" : "true"} ]
			});
		});
	</script>
</body>
</html>
