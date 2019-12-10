<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<head>
<title>View Sensors here</title>
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
var alerta_alarmas
function suscribir(){
	let url="http://localhost:9080/sensores/alarma";
	alerta_alarmas=new EventSource(url); //objeto para consumir flujos de datos
	//comenzamos suscripción
	alerta_alarmas.addEventListener("message",function(e){
		console.log("alarma recibida desde el sensor "+e.data)
		$("div#caja_sensor button.btn").filter("[value='"+e.data+"']").attr("class","btn btn-danger");
	});
}

function cancelar(){
	alerta_alarmas.close();
}
function onBlur() {
	windowFocus=false;
	cancelar()
	console.log("Suscriptor desconectado.")
	};
function onFocus(){
	windowFocus=true;
	suscribir()	//Llama a la función para suscribirse al flujo de alarmas.
	console.log("Suscriptor conectado.")
	};
	 
	if (/*@cc_on!@*/false) { // check for Internet Explorer
	document.onfocusin = onFocus;
	document.onfocusout = onBlur;
	} else {
	window.onfocus = onFocus;
	window.onblur = onBlur;
	}
	
$(function(){ 
let menu_sensors = ${!empty sensores}
	if(menu_sensors) { //Para cambiar la selección del menu.
		suscribir()	}//Llama a la función para suscribirse al flujo de alarmas.
/*		$("ul.nav.navbar-nav").children().first().removeClass("active")
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
	$("div#caja_sensor button.btn").click(function() { //Escucha el evento para verificar una alarma.
		if($(this).attr("class") == "btn btn-danger") {
		$(this).removeClass("btn-danger")
		console.log("botón "+$(this).val())
		$.ajax({// Envía una petición para actualizar los datos. 
			url:"http://localhost:8080/securjava/sensor/actualizar/modo",
// 			type:"GET",
// 			contentType: "application/x-www-form-urlencoded",
		    data: {"idSensor": $(this).val(), "modo": "verificado"}, 
		    success: $(this).addClass("btn-success")/*,
		    error: function( jqXHR, textStatus, errorThrown ) { $(this).addClass("btn-warning") }*/
		  })/*.done(function(){
			  $(this).addClass("btn-success");
		})*/.fail(function(jqXHR, textStatus, errorThrown){
			$(this).addClass("btn-warning");
		});
	$("div#caja_sensor button.btn-success").click(function() {
		$(this).removeClass("btn-success")
		$(this).addClass("btn-info")
// 		$(this).prop("disabled", true)
		})
		}
	})
		
	$("div#caja_sensor label.switch input[type='checkbox']").change(function() { //Escucha el evento de des/activar el sensor.
		console.log("botón "+$(this).val()+": "+$(this).prop("checked"))
		$(this).parent().siblings("button").toggleClass("disabled")
// 		console.log($(this).parent().siblings("button"))
		$.get("http://localhost:8080/securjava/sensor/actualizar/estado",
				  {// Envía una petición para actualizar los datos. 
		    idSensor: $(this).val(),
		    estado: $(this).prop("checked")
		  }, function(data, status){
		    console.log("Data: " + data + "\nStatus: " + status);
		  });
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
	<c:choose>
	<c:when test="${empty sensores}">
      <li class="active"><a href="#">Inicio</a></li>
      <li><a href="toDashboard">Ver Sensores</a></li>
    </c:when>
    <c:otherwise>
      <li><a href="toMenu">Inicio</a></li>
      <li class="active"><a href="#">Ver Sensores</a></li>
    </c:otherwise>
    </c:choose>
      <li><a href="toGestionarSensores">Gestionar Sensores</a></li>
      <li><a href="#">Darse de baja</a></li>
    </ul>
     <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionScope.persona.nombre}</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Salir</a></li>
    </ul>
  </div>
</nav>
</div>
	<div class="container">	
		<c:set var="sensores" value="${requestScope.sensores}" />
		<c:choose>
			<c:when test="${!empty sensores}">
	<h3>Sensores <span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span></h3>
				<div class="row">
					<c:forEach var="sensor" items="${sensores}">
						<div class="col-sm-2" id="caja_sensor">
						<c:choose>
							<c:when test="${sensor.modo == true}"><button type="button" value="${sensor.idSensor}" class="btn btn-danger">${sensor.ubicacion }</button></c:when>
							<c:when test="${sensor.estado == true}"><button type="button" value="${sensor.idSensor}" class="btn btn-info">${sensor.ubicacion }</button></c:when>
							<c:otherwise><button type="button" value="${sensor.idSensor}" class="btn btn-info disabled">${sensor.ubicacion }</button></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sensor.estado == true}"><label class="switch"> <input type="checkbox" value="${sensor.idSensor}" checked="checked"> <span class="slider round"></span></label></c:when>
							<c:otherwise><label class="switch"> <input type="checkbox" value="${sensor.idSensor}"> <span class="slider round"></span></label></c:otherwise>
						</c:choose>
							
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<h2>Seleccione una opción del menu</h2>
			</c:otherwise>
		</c:choose>	<br />
	<img class="img-rounded" id="plano_planta" alt="Plano de la planta del domicilio u oficina." src="<c:url value='/img/plano_planta.png'/>" ismap="ismap"/>
	<br />
	</div>
</body>
</html>