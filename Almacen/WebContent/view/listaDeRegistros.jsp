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

<title>Registros</title>
<jsp:include page="header.jsp" />


</head>


<body>

	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="ListaRegistros" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1 class="mt-5">Lista de registros.</h1>
				<hr class="my-4">


				<form action="../ListaRegistros" method="GET">

					<div class="row">
						<div class="input-field">
							<label>Fecha desde</label> <input id="datePicker" type="text"
								class="form-control" name="filtroDesde" value="${filtroDesde}"
								placeholder="dd/mm/yyyy">
						</div>
						<div class="input-field">
							<label>Fecha hasta</label> <input id="datePicker" type="text"
								class="form-control" name="filtroHasta" value="${filtroHasta}"
								placeholder="dd/mm/yyyy">
						</div>
						<div class="input-field">
							<label>Usuario</label> <input type="text" name="filtroUsuario"
								value="${filtroUsuario}" class="form-control" />
						</div>
						<div class="input-field">
							<label>Entidad</label> <input type="text" name="filtroEntidad"
								value="${filtroEntidad}" class="form-control" />
						</div>


					</div>


					<div class="row">
						<div class="col-sm-10" style="text-align: initial">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="filtroEstado"
									id="gridRadios1" value="Todos"
									<%=(request.getSession().getAttribute("filtroEstado").equals("Todos")
		|| request.getSession().getAttribute("filtroEstado").equals("")) ? "checked" : ""%>>
								<label class="form-check-label" for="gridRadios1"> Todos
								</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="filtroEstado"
									id="gridRadios2" value="Entrada"
									<%=(request.getSession().getAttribute("filtroEstado").equals("Entrada")) ? "checked" : ""%>>
								<label class="form-check-label" for="gridRadios2">
									Entrada </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="filtroEstado"
									id="gridRadios3" value="Salida"
									<%=(request.getSession().getAttribute("filtroEstado").equals("Salida")) ? "checked" : ""%>>
								<label class="form-check-label" for="gridRadios3">
									Salida </label>
							</div>
						</div>
					</div>

					<div class="row">
						<button type="submit"
							class="btn btn-lg btn-primary btn-block text-uppercase"
							id="aceptarbutton"
							style="width: 30%; background-color: #f37321; cursor: pointer; border-radius: 5rem; margin-top: 10px">Buscar</button>
					</div>

				</form>

				<div>&nbsp;</div>
				<table class="table table-striped" id="myTable">
					<thead>
						<tr>
							<th>Fecha</th>
							<th>Entrada/Salida</th>
							<th>Último usuario</th>
							<th>Entidad</th>
							<th>Nombre</th>
							<!-- <th>Acción</th> -->
						</tr>
					</thead>
					<tbody id="tablaEquipos">

						<c:forEach items="${registros}" var="re">
							<tr>
								<td><c:out value="${re.getFecha().toString().substring(0, 16)}" /></td>
								<c:choose>
									<c:when test="${re.getEntrada() eq true }">
										<td><c:out value="Entrada" /></td>
									</c:when>
									<c:otherwise>
										<td><c:out value="Salida" /></td>
									</c:otherwise>
								</c:choose>
								<td><c:out
										value="${re.getUsuarioByUsuario().getNombre()} ${re.getUsuarioByUsuario().getApellido() }" />
								</td>
								<td><c:out value="${re.getEntidad()}" /></td>
								<c:choose>
									<c:when test="${re.getEntidad() eq 'Equipo' }">
										<td><c:out value="${re.getEquipo().getNombre()}" /></td>
									</c:when>
									<c:when test="${re.getEntidad() eq 'Llave' }">
										<td><c:out value="${re.getLlave().getNombre()}" /></td>
									</c:when>
									<c:when test="${re.getEntidad() eq 'Grupo equipo' }">
										<td><c:out value="${re.getGrupoEquipos().getNombre()}" />
										</td>
									</c:when>
									<c:when test="${re.getEntidad() eq 'Grupo llave' }">
										<td><c:out value="${re.getGrupoLlaves().getNombre()}" />
										</td>
									</c:when>
								</c:choose>
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
			$('#myTable').DataTable({
				"order" : [ [ 0, "desc" ] ],
				"columnDefs" : [ {
					"responsive" : "true"
				} ],
				"language" : {
					"emptyTable" : "No se encontraron registros a mostrar!",
					"search" : "Búsqueda rápida:"
				}
			})
			$("input[id='datePicker']").datepicker({
				language : 'es'
			});
		});

	</script>
</body>
</html>
