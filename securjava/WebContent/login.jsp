<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Login here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
</head>
<body>
<div class="container">
	<h3>segurJava</h3>
	<form action="toLogin" method="post">
		<div class="form-group">
			<label>Usuario:</label><input type="text" name="user" class="form-control" style="width:30%" placeholder="Usuario" autocomplete="name"/>
		</div>
		<div class="form-group">
			<label>Password:</label><input type="password" name="pwd" class="form-control" style="width:30%" placeholder="Contraseņa"/>
		</div>
		
		<button type="submit" class="btn btn-default">Enviar</button>
		
	</form>
	<br/><br/>
	<a href="toRegistro">Registrese</a>

</div>
</body>
</html>