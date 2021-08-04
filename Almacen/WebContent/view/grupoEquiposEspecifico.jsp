<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Grupo de Equipos ${grupoEquipos.getNombre() }</title>
<jsp:include page="header.jsp" />


</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuTecnica" />
	</jsp:include>

	<h2 class="mt-5 text-center">${grupoEquipos.getNombre() }
		<span><button class="btn btn-outline-dark"
				style="cursor: pointer" title="Ver código QR"
				onclick="window.open('../Qr?entidadId=${grupoEquipos.getGrupoEquipoId()}&entidad=GrupoEquipo','_blank')">
				<i class="material-icons"> border_outer </i>
			</button></span> <span><button class="btn btn-outline-dark"
				style="cursor: pointer" title="Editar"
				onclick="window.location.href=('../EditarGrupoEquipos?idAEditar=${grupoEquipos.getGrupoEquipoId()}')">
				<i class="material-icons"> edit </i>
			</button></span>
		<button class="btn btn-outline-danger" type="button" title="Eliminar"
			style="cursor: pointer"
			onclick="confirmar('../EliminarGrupoEquipos?idEliminado=${grupoEquipos.getGrupoEquipoId()}');">
			<i class="material-icons"> delete </i>
		</button>
	</h2>
	<div class="text-center">
		<c:if test="${mostrarBoton }">
			<form method="get" action="../CambioEstado" id="formMethod">
				<input type="hidden" id="cambioId" name="cambioId"
					value="${grupoEquipos.getGrupoEquipoId()}"> <input
					type="hidden" id="salida" name="salida"
					value="${grupoEquiposEstado == 'En uso' ? '0' : '1'}"> <input
					type="hidden" id="entidad" name="entidad" value="GrupoEquipos">
				<c:choose>
					<c:when test="${grupoEquiposEstado == 'Disponible'}">
						<button class="btn btn-primary btn-lg mb-1" type="submit"
							title="Salida" style="cursor: pointer">Salida</button>

					</c:when>

					<c:when test="${grupoEquiposEstado == 'En uso'}">
						<button class="btn btn-primary btn-lg mb-1" type="submit"
							title="Entrada" style="cursor: pointer">Entrada</button>
					</c:when>
				</c:choose>
			</form>
		</c:if>
	</div>
	<div class="text-center lead"
		style="outline: 1px solid black; max-width: 70%; margin: auto;">

		<table class="table">
			<tr>
				<th>ID:</th>
				<td>${grupoEquipos.getGrupoEquipoId()}</td>
				<th>Estado:</th>
				<td>${grupoEquiposEstado}</td>
			</tr>
		</table>
	</div>

	<div class="text-center" style="width: 70%; margin: auto">
		<!-- tabla de equipos -->
		<h3 class="mt-5 text-center">Equipos</h3>
		<table id="tablaEquipos" class="table">
			<tr>
				<th>Nombre</th>
				<th>Tipo</th>
				<th>Estado</th>
			</tr>
			<c:forEach items="${grupoEquipos.getEquipos()}" var="equipo">
				<tr>
					<td><c:out value="${equipo.getNombre()}" /></td>
					<td><c:out value="${equipo.getTipo().getNombre()}" /></td>
					<td><c:out value="${equipo.getEstado()}" /></td>

				</tr>
			</c:forEach>
		</table>
	</div>


</body>
<script>
	function confirmar(url) {
		var r = confirm("¿Está seguro que desea eliminar el grupo de equipos?");
		if (r == true) {
			$(location).attr('href', url);
		}
	}
</script>

</html>