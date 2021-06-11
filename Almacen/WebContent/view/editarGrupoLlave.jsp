<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Editar Grupo de llaves</title>
</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuLlaves" />
	</jsp:include>

	<!-- Page Content   -->

	<div class="container">
		<div class="card card-signin my-5">
			<div class="card-body">
				<form class="form-signin" method="post" action="../EditarGrupoLlaves">
					<h3 class="text-center">Datos del grupo de llaves</h3>
					<div class="column">
						<input type="hidden" name="inputId" id="inputId" value="${grupo.getGrupoId() }">
						<div>Nombre del grupo</div>
						<div class="form-label-group>">
							<input type="text" name="inputNombre" id="inputNombre" class="form-control" placeholder="Nombre" required value="${grupo.getNombre() }" autocomplete="off" disabled="disabled">
						</div>

						<script type="text/javascript">
							$(document)
									.ready(
											function() {
												$('#boot-multiselect-llaves')
														.multiselect(
																{
																	nonSelectedText : 'Seleccionar llaves',
																	buttonWidth : 500,
																	enableFiltering : true
																});

												$('#aceptarbutton')
														.click(
																function() {
																	$(
																			'#inputLlaves')
																			.val(
																					$(
																							'#boot-multiselect-llaves')
																							.val());
																});
											});
						</script>

						<div style="text-align: center;">
							<div>Llaves</div>
							<input type="hidden" name="inputLlaves" id="inputLlaves" autocomplete="off"> <select id="boot-multiselect-llaves" name="boot-multiselect-llaves" multiple="multiple">
								<c:if test="${grupo.getLlaves()!= null }">
									<c:forEach items="${grupo.getLlaves() }" var="gLlave">
										<option selected value="${gLlave.getLlaveId()}">${gLlave.getNombre() }- ${gLlave.getLlaveId() }</option>
									</c:forEach>
								</c:if>
								<c:forEach items="${llaves}" var="llave">
									<option value="${llave.getLlaveId()}">${llave.getNombre() }- ${llave.getLlaveId() }</option>
								</c:forEach>
							</select>
						</div>

					</div>




					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" id="aceptarbutton" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer;">Aceptar</button>
				</form>
			</div>
		</div>

	</div>
</body>
</html>