<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">

<title>Agregar Nuevo Proveedor</title>

</head>
<body>
	<jsp:include page="menu-container.jsp">
		<jsp:param name="activeMenu" value="ProveedorNuevo" />
	</jsp:include>

	<!-- Page Content   -->
	<div class="container">
		<form class="form-signin" method="post" action="../ProveedorNuevo">
			<div class="card card-signin my-5">
				<div class="card-body">
					<h3 class="text-center">Datos del nuevo proveedor</h3>
					<div class="row">
						<div class="column">
							<div>Nombre del proveedor</div>
							<div class="form-label-group>">
								<input tabindex="1" type="text" name="provNombre" class="form-control" placeholder="Nombre" required autocomplete="off">
							</div>
							<div>Teléfono</div>
							<div class="form-label-group>">
								<input tabindex="3" type="text" name="provTel" class="form-control" placeholder="Teléfono" required oninput="this.value=this.value.replace(/[^0-9]/g,'');" autocomplete="off">
							</div>
							<div>Contacto</div>
							<div class="form-label-group>">
								<input tabindex="5" type="text" name="provCont" class="form-control" placeholder="Persona contacto" autocomplete="off">
							</div>


						</div>
						<div class="column">
							<div>Dirección</div>
							<div class="form-label-group>">
								<input tabindex="2" type="text" name="provDire" class="form-control" placeholder="Dirección" required autocomplete="off">
							</div>
							<div>Correo Electrónico</div>
							<div class="form-label-group>">
								<input tabindex="4" type="text" name="provMail" class="form-control" placeholder="Mail" required autocomplete="off">
							</div>

						</div>


					</div>
					<hr>
					<button tabindex="6" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer">Aceptar</button>
				</div>
			</div>
		</form>
	</div>
</body>

</html>