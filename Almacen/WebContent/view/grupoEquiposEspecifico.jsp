<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Grupo de Equipos ${grupoEquipos.getNombre() }</title>
<jsp:include page="header.jsp"/>


</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuTecnica" />
	</jsp:include>

	<h2 class="mt-5 text-center">${grupoEquipos.getNombre() }
		<span><button class="btn btn-outline-dark" style="cursor: pointer" title="Editar" onclick="window.location.href=('${pageContext.request.contextPath }/EditarGrupoEquipos?idAEditar=${grupoEquipos.getGrupoEquipoId()}')">
				<i class="material-icons"> edit </i>
			</button></span> <span><button class="btn btn-outline-dark" style="cursor: pointer" title="Ver código QR" onclick="window.open('${pageContext.request.contextPath }/Qr?entidadId=${grupoEquipos.getGrupoEquipoId()}&entidad=GrupoEquipo','_blank')">
				<i class="material-icons"> border_outer </i>
			</button></span>
	</h2>
	<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">

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
</html>