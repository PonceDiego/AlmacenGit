<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Iniciar Sesión</title>
</head>
<body>
	<h4 id="mensaje"style="color: red;">${mensaje }</h4>
	<form method="post" action="/Almacen/IniciarSesion">
		Nombre de usuario:<input type="text" name="username" /><br /> Contraseña:<input
			type="password" name="pass" /><br /> <input type="submit"
			value="Ingresar" />
	</form>
</body>
</html>