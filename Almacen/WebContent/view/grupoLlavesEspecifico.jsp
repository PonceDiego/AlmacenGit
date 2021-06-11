<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Grupo de Llaves ${grupoLlaves.getNombre() }</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuLlaves" />
	</jsp:include>

	<h2 class="mt-5 text-center">${grupoLlaves.getNombre() }
		<span><button class="btn btn-outline-dark" style="cursor: pointer" title="Editar" onclick="window.location.href=('${pageContext.request.contextPath }/EditarGrupoLlaves?idAEditar=${grupoLlaves.getGrupoId()}')">
				<i class="material-icons"> edit </i>
			</button></span> <span><button class="btn btn-outline-dark" style="cursor: pointer" title="Ver código QR" onclick="window.open('${pageContext.request.contextPath }/Qr?entidadId=${grupoLlaves.getGrupoId()}&entidad=GrupoLlaves','_blank')">
				<i class="material-icons"> border_outer </i>
			</button></span>
	</h2>
	<div class="text-center lead" style="outline: 1px solid black; max-width: 70%; margin: auto">

		<table class="table">
			<tr>
				<th>ID:</th>
				<td>${grupoLlaves.getGrupoId()}</td>
				<th>Estado:</th>
				<td>${grupoLlavesEstado}</td>
			</tr>
		</table>
	</div>

	<div class="text-center" style="width: 70%; margin: auto">
		<!-- tabla de llaves -->
		<h3 class="mt-5 text-center">Llaves</h3>
		<table id="tablaLlaves" class="table">
			<tr>
				<th>Nombre</th>
				<th>Copia</th>
				<th>Estado</th>
			</tr>
			<c:forEach items="${grupoLlaves.llaves}" var="llave">
				<tr>
					<td><c:out value="${llave.getNombre()}" /></td>
					<td><c:out value="${llave.getCopia()}" /></td>
					<td><c:out value="${llave.getEstado()}" /></td>

				</tr>
			</c:forEach>
		</table>
	</div>


</body>
</html>