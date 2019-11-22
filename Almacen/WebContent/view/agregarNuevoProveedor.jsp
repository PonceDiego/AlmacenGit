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
<meta charset="UTF-8">

<!-- Bootstrap core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/iconfont/material-icons.css" rel="stylesheet">


<style>
div.searchable {
	float: left;
	margin: 0 15px;
}

.searchable input {
	width: 100%;
	font-size: 16px;
	padding: 10px;
	-webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
	-moz-box-sizing: border-box; /* Firefox, other Gecko */
	box-sizing: border-box; /* Opera/IE 8+ */
	display: block;
	font-weight: 400;
	line-height: 1.6;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	background:
		url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 4 5'%3E%3Cpath fill='%23343a40' d='M2 0L0 2h4zm0 5L0 3h4z'/%3E%3C/svg%3E")
		no-repeat right .75rem center/8px 10px;
}

.searchable ul {
	display: none;
	list-style-type: none;
	background-color: #fff;
	border-radius: 0 0 5px 5px;
	border: 1px solid #add8e6;
	border-top: none;
	max-height: 180px;
	margin: 0;
	overflow-y: scroll;
	overflow-x: hidden;
	padding: 0;
}

.searchable ul li {
	padding: 7px 9px;
	border-bottom: 1px solid #e1e1e1;
	cursor: pointer;
	color: #6e6e6e;
}

.searchable ul li.selected {
	background-color: #e8e8e8;
	color: #333;
}

.form-signin {
	width: 100%;
}

.form-signin .btn {
	font-size: 80%;
	border-radius: 5rem;
	letter-spacing: .1rem;
	font-weight: bold;
	padding: 1rem;
	transition: all 0.2s; . form-label-group { position : relative;
	margin-bottom: 1rem;
}

}
.form-label-group input {
	height: auto;
	border-radius: 2rem;
}

.form-label-group>input, .form-label-group>label {
	padding: var(- -input-padding-y) var(- -input-padding-x);
}

.form-label-group>label {
	position: absolute;
	top: 0;
	left: 0;
	display: block;
	width: 100%;
	margin-bottom: 0;
	/* Override default `<label>` margin */
	line-height: 1.5;
	color: #495057;
	border: 1px solid transparent;
	border-radius: .25rem;
	transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
	color: transparent;
}

.form-label-group input:-ms-input-placeholder {
	color: transparent;
}

.form-label-group input::-ms-input-placeholder {
	color: transparent;
}

.form-label-group input::-moz-placeholder {
	color: transparent;
}

.form-label-group input::placeholder {
	color: transparent;
}

.form-label-group input:not (:placeholder-shown )~label {
	padding-top: calc(var(- -input-padding-y)/3);
	padding-bottom: calc(var(- -input-padding-y)/3);
	font-size: 12px;
	color: #777;
}

.card-signin {
	border: 0;
	border-radius: 1rem;
	box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}

.card-signin .card-title {
	margin-bottom: 2rem;
	font-weight: 300;
	font-size: 1.5rem;
}

.card-signin .card-body {
	padding: 2rem;
}

.column {
	padding: 10px;
	flex: 50%;
}

div.searchable {
	margin: 0 15px;
}

.searchable input {
	width: 100%;
	height: 50px;
	font-size: 18px;
	padding: 10px;
	-webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
	-moz-box-sizing: border-box; /* Firefox, other Gecko */
	box-sizing: border-box; /* Opera/IE 8+ */
	display: block;
	font-weight: 400;
	line-height: 1.6;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}

.searchable ul {
	display: none;
	list-style-type: none;
	background-color: #fff;
	border-radius: 0 0 5px 5px;
	border: 1px solid #add8e6;
	border-top: none;
	max-height: 180px;
	margin: 0;
	overflow-y: scroll;
	overflow-x: hidden;
	padding: 0;
}

.searchable ul li {
	padding: 7px 9px;
	border-bottom: 1px solid #e1e1e1;
	cursor: pointer;
	color: #6e6e6e;
}

.searchable ul li.selected {
	background-color: #e8e8e8;
	color: #333;
}
</style>
<title>Agregar Nuevo Proveedor</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>

	<!-- Bootstrap core JavaScript -->
	<script src="../vendor/jquery/jquery.slim.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
		<a class="navbar-brand" href="javascript:history.back()"> <i
			class="material-icons"style="font-size: 36px">arrow_back </i></a>
		<div class="container">
			<a class="navbar-brand" href="../Index">Inicio</a>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Artículos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../NuevoArticulo">Nuevo artículo</a>
							<a class="dropdown-item" href="../ListaArticulos">Lista de
								artículos</a> <a class="dropdown-item" href="../BuscarArticulo">Artículo
								específico</a>
						</div></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Proveedores </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item active" href="../ProveedorNuevo">Nuevo
								proveedor</a> <a class="dropdown-item" href="../ListaProveedores">Listar
								por artículos</a> <a class="dropdown-item" href="../BuscarProveedor">Proveedor
								específico</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Pedidos </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
							<a class="dropdown-item" href="../GenerarPedido">Nuevo pedido</a> <a
								class="dropdown-item" href="../ListaPedidos">Lista de pedidos</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="material-icons">perm_identity</i>
					</a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown2">
							<a class="dropdown-item" href="../UsuarioNuevo">Nuevo usuario</a>
							<a class="dropdown-item " href="../ListaUsuarios">Lista de usuarios</a> <a
								class="dropdown-item" href="../AreaNueva">Nueva área</a> <a
								class="dropdown-item" href="../CerrarSesion">Cerrar sesión</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content   -->





	<div class="container">
		<form class="form-signin" method="post" action="../ProveedorNuevo">
			<div class="card card-signin my-5">
				<div class="card-body">
					<h3 class="text-center">Datos del nuevo proveedor</h3>
					<div class="row">
						<div class="column">
							<div class="form-label-group>">
								<input type="text" name="provNombre" class="form-control"
									placeholder="Nombre" required autocomplete="off">
							</div>
							<div class="form-label-group>">
								<input type="text" name="provTel" class="form-control"
									placeholder="Teléfono" required oninput="this.value=this.value.replace(/[^0-9]/g,'');"autocomplete="off">
							</div>


						</div>
						<div class="column">
							<div class="form-label-group>">
								<input type="text" name="provDire" class="form-control"
									placeholder="Dirección"required autocomplete="off">
							</div>
							<div class="form-label-group>">
								<input type="text" name="provMail" class="form-control"
									placeholder="Mail" required autocomplete="off">
							</div>
							<div class="form-label-group>">
								<input type="text" name="provCont" class="form-control"
									placeholder="Persona contacto"autocomplete="off">
							</div>

						</div>


					</div>
					<hr>
					<button class="btn btn-lg btn-primary btn-block text-uppercase"
						type="submit"
						style="max-width: 50%; margin: auto; background-color: #f37321; cursor: pointer">Aceptar</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		function filterFunction(that, event) {
			let container, input, filter, li, input_val;
			container = $(that).closest(".searchable");
			input_val = container.find("input").val().toUpperCase();

			if ([ "ArrowDown", "ArrowUp", "Enter" ].indexOf(event.key) != -1) {
				keyControl(event, container)
			} else {
				li = container.find("ul li");
				li.each(function(i, obj) {
					if ($(this).text().toUpperCase().indexOf(input_val) > -1) {
						$(this).show();
					} else {
						$(this).hide();
					}
				});

				container.find("ul li").removeClass("selected");
				setTimeout(function() {
					container.find("ul li:visible").first()
							.addClass("selected");
				}, 100)
			}
		}

		function keyControl(e, container) {
			if (e.key == "ArrowDown") {

				if (container.find("ul li").hasClass("selected")) {
					if (container.find("ul li:visible").index(
							container.find("ul li.selected")) + 1 < container
							.find("ul li:visible").length) {
						container.find("ul li.selected")
								.removeClass("selected").nextAll().not(
										'[style*="display: none"]').first()
								.addClass("selected");
					}

				} else {
					container.find("ul li:first-child").addClass("selected");
				}

			} else if (e.key == "ArrowUp") {

				if (container.find("ul li:visible").index(
						container.find("ul li.selected")) > 0) {
					container.find("ul li.selected").removeClass("selected")
							.prevAll().not('[style*="display: none"]').first()
							.addClass("selected");
				}
			} else if (e.key == "Enter") {
				container.find("input").val(
						container.find("ul li.selected").text()).blur();
				onSelect(container.find("ul li.selected").text())
			}

			container.find("ul li.selected")[0].scrollIntoView({
				behavior : "smooth",
			});
		}
		function onSelect(val) {

		}

		$(".searchable input").focus(function() {
			$(this).closest(".searchable").find("ul").show();
			$(this).closest(".searchable").find("ul li").show();
		});
		$(".searchable input").blur(function() {
			let that = this;
			setTimeout(function() {
				$(that).closest(".searchable").find("ul").hide();
			}, 300);
		});

		$(document).on(
				'click',
				'.searchable ul li',
				function() {
					$(this).closest(".searchable").find("input").val(
							$(this).text()).blur();
					onSelect($(this).text())
				});

		$(".searchable ul li").hover(
				function() {
					$(this).closest(".searchable").find("ul li.selected")
							.removeClass("selected");
					$(this).addClass("selected");
				});
	</script>
</body>

</html>