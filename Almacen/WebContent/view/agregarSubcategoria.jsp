<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">


<title>Agregar Nueva Subcategoría</title>
<jsp:include page="header.jsp"/>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="MenuArticulos" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<form class="form-signin" method="post" action="../NuevaSubcategoria">
			<div class="card card-signin my-5">
				<div class="card-body">
					<h3 class="text-center">Datos de la nueva subcategoría</h3>
					<div class="row">
						<div class="column">
							<div>Nombre</div>
							<div class="form-label-group>">
								<input type="text" name="inputNombre" id="inputNombre" class="form-control" placeholder="Nombre" required autocomplete="off" onchange="comparar()">
							</div>
							<div>Categoría</div>
							<div class=" form-label-group>" id="divCat">
								<input type="hidden" name="inputCat" id="inputCat" value=""> <select onchange="selected()" id="selectCat" style="border-radius: 5px; font-size: 16px; padding: 10px;" required>
									<option disabled selected value="">Seleccione una categoría</option>
									<c:forEach items="${cats}" var="categoria">
										<option style="cursor: pointer" class="${categoria.getNombre()} listaCat" value=${categoria.getCategoriaId() }>-${categoria.getNombre()}</option>
									</c:forEach>
								</select>

							</div>
						</div>
					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" disabled="disabled" id="buttonAceptar" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer">Aceptar</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		function selected() {
			var x = document.getElementById("selectCat").value;
			document.getElementById("inputCat").value = x;
			comparar();
		}
		function comparar() {
			var x = document.getElementById("inputCat").value;
			var y = document.getElementById("buttonAceptar");
			var b = document.getElementById("inputNombre");
			var a = b.value;
			if (a == null || a == "") {
				if (x == null || x == "") {
					y.disabled = true;
				}
			} else {
				y.disabled = false;
			}
		}
	</script>
</body>

</html>