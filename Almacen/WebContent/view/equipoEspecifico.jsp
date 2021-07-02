<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="equipo" value="${equipoEspecifico }"></c:set>

<title>Equipo ${equipo.getNombre() }</title>
<jsp:include page="header.jsp"/>
</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuTecnica" />
	</jsp:include>

	<!-- Page Content   -->
	<hr>
	<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">

		<h4>${equipo.getNombre()}
			<span><button class="btn btn-outline-dark" style="cursor: pointer" title="Ver código QR" onclick="window.open('${pageContext.request.contextPath }/Qr?entidadId=${equipo.getEquipoId()}&entidad=Equipo','_blank')">
					<i class="material-icons"> border_outer </i>
				</button></span>
		</h4>

		<table class="table">
			<tr>
				<th>Usuario Habitual:</th>
				<td>${equipo.getUsuario().getNombreUsuario()}</td>
				<th>Tipo:</th>
				<td>${equipo.getTipo().getNombre()}</td>
				<th>Serial:</th>
				<td>${equipo.getSerial() }
			</tr>
			<tr>
				<th>Lugar Habitual:</th>
				<td>${equipo.getLugar().getNombre()}</td>
				<th>Estado:</th>
				<td>${equipo.getEstado()}</td>
			</tr>
		</table>
	</div>
	<hr>
	<div class="text-center lead" style="max-width: 80%; margin: auto; align-content: center;">
		<!-- tablita de registros del equipo -->
		<table id="tablaRegistroEquipo" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Entrada/Salida</th>
					<th>Usuario</th>
				</tr>
			</thead>
			<tbody id="registros" style="color: black; background-color: white;">
				<c:forEach items="${registrosEquipo}" var="re">
					<tr>
						<td><c:out value="${re.getFecha()}" /></td>
						<c:choose>
							<c:when test="${re.getEntrada() eq true }">
								<td><c:out value="Entrada"></c:out></td>
							</c:when>
							<c:otherwise>
								<td><c:out value="Salida"></c:out></td>
							</c:otherwise>
						</c:choose>
						<td><c:out value="${re.getUsuarioByUsuario().getNombreUsuario()}" /></td>

					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>


	<script src="../vendor/Datatables/datatables.js"></script>


	<script>
		$(document).ready(function() {
			$('#tablaRegistroEquipo').DataTable({
				"order": [[ 0, "desc" ]],
				"columnDefs" : [ {
					"responsive" : "true"
				} ],
				"language" : {
					"emptyTable" : "No existen registros para este equipo!"
				}
			})
		});
	</script>
</body>
</html>