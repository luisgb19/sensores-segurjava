<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<head>
<title>Manage Sensors here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/modulo_web.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
$(function(){ /*
let menu_sensors = ${!empty sensores}
	if(menu_sensors) { //Para cambiar la selección del menu.
		$("ul.nav.navbar-nav").children().first().removeClass("active")
		$("ul.nav.navbar-nav").children().eq(1).addClass("active")
	} else {
	  	$("ul.nav.navbar-nav").children().first().addClass("active")
		$("ul.nav.navbar-nav").children().eq(1).removeClass("active")
	}*/
	$.ajaxSetup({
		  error: function( jqXHR, textStatus, errorThrown ) {
		          if (jqXHR.status === 0) {
		            console.log('Not connect: Verify Network.');
		          } else if (jqXHR.status == 404) {
		            console.log('Requested page not found [404]');
		          } else if (jqXHR.status == 500) {
		            console.log('Internal Server Error [500].');
		          } else if (textStatus === 'parsererror') {
		            console.log('Requested JSON parse failed.');
		          } else if (textStatus === 'timeout') {
		            console.log('Time out error.');
		          } else if (textStatus === 'abort') {
		            console.log('Ajax request aborted.');
		          } else {
		            console.log('Uncaught Error: ' + jqXHR.responseText);
		          }
		        }
		});
	var sensor_sel
	$("div#lista_sensores div.list-group div.list-group-item a").click(function() { //Escucha el evento para mostrar el panel de nuevo sensor.
		if($(this).text() == "Contratar sensor ") {
			$("div.panel#caja_lugar").toggle()
		}
	})
		
	$("div#bajaModal > div.modal-dialog > div.modal-content > div.modal-footer > button[data-dismiss='modal']").click(function() { //Escucha el evento de deshabilitar el cliente.
		console.log("botón "+$(this).val()+": "+$(this).prop("checked"))
		if($(this).text() == "Deshabilitar") {
		$(this).parent().siblings("button").toggleClass("disabled")
// 		console.log($(this).parent().siblings("button"))
		$.get("http://localhost:8080/securjava/cliente/deshabilitar",
				  {// Envía una petición para actualizar los datos. 
		    idCliente: ${sessionScope.persona.dni}
		  }, function(data, status){
		    console.log("Data: " + data + "\nStatus: " + status);
		  });
		}
		})
}); 
</script>
</head>
<body>
<div class="container-fluid">	
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">segurJava</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="toMenu">Inicio</a></li>
      <li><a href="toDashboard">Ver Sensores</a></li>
      <li class="active"><a href="#">Gestionar Sensores</a></li>
      <li><a href="#" data-toggle="modal" data-target="#bajaModal">Darse de baja</a></li>
    </ul>
     <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionScope.persona.nombre}</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Salir</a></li>
    </ul>
  </div>
</nav>
</div>
<!-- Modal -->
<div id="bajaModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Solicitar baja del sistema</h4>
      </div>
      <div class="modal-body">
        <p>Se deshabilitara su cuenta de cliente.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal"><span class="glyphicon glyphicon-warning" aria-hidden="true"></span> Cancelar</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Deshabilitar</button>
      </div>
    </div>

  </div>
</div>
	<div class="container">	
		<c:set var="sensores" value="${requestScope.sensores}" />
		<c:choose>
			<c:when test="${!empty sensores}">
	<h3>Sensores <span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span></h3>
			<div class="col-sm-4" id="lista_sensores">
				<div class="list-group"><form action="doBajaSensor" method="post">
					<c:forEach var="sensor" items="${sensores}">
						<label class="list-group-item"><input type="radio" class="form-control" name="idSensor" value="${sensor.idSensor}">${sensor.ubicacion }</label>
					</c:forEach>
					<div class="list-group-item"><input class="btn-link" type="submit" value="Quitar sensor"> <span class="glyphicon glyphicon-remove-circle"></span>
					<a href="#">Contratar sensor <span class="glyphicon glyphicon-plus-sign"></span></a></div></form>
				</div>
				<div class="panel panel-default" id="caja_lugar">
					<div class="panel-heading">Datos del nuevo sensor</div>
					<div class="panel-body">
					<form action="doAltaSensor" method="post">
					<label>Lugar de ubicación:</label><input type="text" name="lugar" class="form-control" style="width:30%" placeholder="sala o estancia"/>
					<button type="submit" class="btn btn-default">Añadir</button>
					</form>
					</div>
				</div>
			</div>
<div class="col-sm-8"><div class="panel panel-default" id="lista_alarmas">
	<div class="panel-heading">Alarmas</div>
			<table class="panel-body table table-hover">
			<tr><th>Sensor</th><th>Fecha</th></tr>
					<c:forEach var="alarm" items="${alarmas}">
					<tr><td>${alarm.idSensor }</td><td>${alarm.fecha }</td></tr>
					</c:forEach>
			</table></div></div>
			</c:when>
			<c:otherwise>
				<h3 class="warning">No tiene sensores contratados.</h3>
			</c:otherwise>
		</c:choose>	<br />
	<br />
	</div>
</body>
</html>