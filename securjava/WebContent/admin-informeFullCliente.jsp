<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>Seleccione el cliente</title>
<meta http-equiv="Content-Type" content="text/html;  charset=ISO-8859-1" >
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<script type="text/javascript">
function valorDNI(valor){
	if(valor!=null){	
		var selDni = document.getElementById("dni");
		selDni.value=valor;
		selDni.options[selDni.options.selectedIndex].selected = true;
	}
}
</script>		
</head>
<c:set var="seleccionado" value="${requestScope.valorSeleccionado}"/> 
<body onload="valorDNI(${seleccionado});">
<div class="container-fluid">	
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">segurJava</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="toMenu" class="active">Inicio</a></li>
	<li><a href="toAdminAltaCliente">Dar de alta cliente</a></li>
<li><a href="admin-informeTemporal.jsp">Generar informe de un intervalo temporal</a></li>
<li><a href="#" class="active">Generar informe de un cliente</a></li>
    </ul>
     <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionScope.persona.nombre}</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Salir</a></li>
    </ul>
  </div>
</nav>
</div>
<div class="container">
Seleccione el cliente para crear el informe<br/><br/>
<c:set var="clientes" value="${requestScope.clientes}"/> 
<form class="form-inline" action="doAdminInformeFullCliente" method="post">
		<div class="form-group">
	<select class="form-control" name="dni" id="dni">
		<c:forEach var="c" items="${clientes}">
			<option value="${c.dni}">${c.dni} - ${c.usuario} - ${c.nombre}</option>				
		</c:forEach>
	</select></div>
	<input type="submit" class="btn btn-default" value="Generar informe de cliente"/>
</form><br/><br/>
<c:set var="sensores" value="${requestScope.sensores}"/> 
<c:choose>
	<c:when test="${!empty sensores}">
		<table class="table table-stripped">
		    <tr><td width="300px" align="center"><br/><b>Sensor</b><br/>&nbsp;</td><td width="200px" align="center"><br/><b>Fecha de la alarma</b><br/>&nbsp;</td></tr>		    	
	    	<c:forEach var="sens" items="${sensores}">
	    		<tr>
	    			<td align="center"><br/>&nbsp;${sens.idSensor} - ${sens.ubicacion}<br/>&nbsp;</td>
					<td align="center">
					<c:forEach var="alm" items="${sens.alarmas}"><br/>&nbsp;
						<fmt:formatDate pattern="yyyy-MM-dd : HH:mm:ss" value="${alm.id.fecha}" />
						<br/>&nbsp;
					</c:forEach>	
					</td>
				</tr>		    	
	    	</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<c:if test="${!empty seleccionado}">
			<c:if test="${empty alarmas}">
				<h3>No hay alarmas para este cliente</h3>
			</c:if>
		</c:if>
	</c:otherwise>		
</c:choose>
</div>

</body>
</html>