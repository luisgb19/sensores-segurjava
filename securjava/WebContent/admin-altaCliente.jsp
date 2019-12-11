<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Administrador</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style>
/*the container must be positioned relative:*/
.autocomplete {
  position: relative;
  display: inline-block;
}

.autocomplete-items {
  position: absolute;
  border: 1px solid #d4d4d4;
  border-bottom: none;
  border-top: none;
  z-index: 99;
  /*position the autocomplete items to be the same width as the container:*/
  top: 100%;
  left: 0;
  right: 0;
}

.autocomplete-items div {
  padding: 10px;
  cursor: pointer;
  background-color: #fff; 
  border-bottom: 1px solid #d4d4d4; 
}

/*when hovering an item:*/
.autocomplete-items div:hover {
  background-color: #e9e9e9; 
}

/*when navigating through the items using the arrow keys:*/
.autocomplete-active {
  background-color: DodgerBlue !important; 
  color: #ffffff; 
}
</style>

</head>
<body>
<div class="container-fluid">	
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">segurJava</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="toMenu" class="active">Inicio</a></li>
	<li><a href="#" class="active">Dar de alta cliente</a></li>
<li><a href="admin-informeTemporal.jsp">Generar informe de un intervalo temporal</a></li>
<li><a href="toAdminInformeFullCliente">Generar informe de un cliente</a></li>
    </ul>
     <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionScope.persona.nombre}</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Salir</a></li>
    </ul>
  </div>
</nav>
</div>
<div class="container">

Introduzca los datos del nuevo cliente<br/><br/>


	<form class="form-horizontal" autocomplete="off" action="doAdminAltaCliente" method="post">
		<div class="form-group">
			<label class="col-sm-2 control-label">DNI:</label><div class="autocomplete col-sm-8" >	
			<input class="form-control" type="text" name="dni" id="dni" maxlength=45 required/>
		</div></div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Usuario:</label>
			    <div class="col-sm-10">
			<input type="text" class="form-control" name="usuario" id="usuario" maxlength=45 required/></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Password:</label>    <div class="col-sm-10">
			<input type="password" class="form-control" name="password" id="password" maxlength=100 required/></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Rol:</label>
			    <div class="col-sm-10">
			<select class="form-control" name="rol" id="rol">
				<option value="ROLE_USER" selected=true>Cliente</option>
				<option value="ROLE_ADMIN" >Administrador</option>
			</select></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Nombre:</label>    <div class="col-sm-10">
			<input type="text" class="form-control" name="nombre" id="nombre" maxlength=100 required/></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Dirección:</label>    <div class="col-sm-10">
			<input type="text" class="form-control" name="direccion" id="direccion" maxlength=100/></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email:</label>    <div class="col-sm-10">
			<input type="text" class="form-control" name="email" id="email" maxlength=100/></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Cuenta:</label>    <div class="col-sm-10">
			<input type="text" class="form-control" name="cuenta" id="cuenta" maxlength=24/></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Contratar el aviso a policia:</label>
			    <div class="col-sm-10">
			<select class="form-control" name="avisoPolicia" id="avisoPolicia">
				<option value="true" selected=true>Sí</option>
				<option value="false" >No</option>
			</select>
			</div>
			
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Habilitado:</label></td><td>
			    <div class="col-sm-10">
			<select class="form-control" name="habilitado" id="habilitado">
				<option value="true" selected=true>Sí</option>
				<option value="false" >No</option>
			</select></div>
		</div>
		<button type="submit" class="btn btn-primary">Dar de alta</button>
		
	</form>
</div>
<script>
function autocomplete(inp, arr) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/
      for (i = 0; i < arr.length; i++) {
        /*check if the item starts with the same letters as the text field value:*/
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          /*create a DIV element for each matching element:*/
          b = document.createElement("DIV");
          /*make the matching letters bold:*/
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          /*execute a function when someone clicks on the item value (DIV element):*/
          b.addEventListener("click", function(e) {
              /*insert the value for the autocomplete text field:*/
              inp.value = this.getElementsByTagName("input")[0].value;
              rellenarCampos(inp.value);
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed,
        increase the currentFocus variable:*/
        currentFocus++;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 13) {
        /*If the ENTER key is pressed, prevent the form from being submitted,*/
        e.preventDefault();
        if (currentFocus > -1) {
          /*and simulate a click on the "active" item:*/
          if (x) x[currentFocus].click();
        }
      }
  });
  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  /*execute a function when someone clicks in the document:*/
  document.addEventListener("click", function (e) {
      closeAllLists(e.target);
  });
}

<c:set var="clientes" value="${requestScope.clientes}"/>
var dnis = [<c:forEach var="c" items="${clientes}">"${c.dni}",</c:forEach>];
autocomplete(document.getElementById("dni"), dnis);

// Empieza la carga

var JSONObject = {<c:forEach var="c" items="${clientes}">"${c.dni}" : {"avisoPolicia":"${c.avisoPolicia}","cuenta":"${c.cuenta}","direccion":"${c.direccion}","email":"${c.email}","habilitado":"${c.habilitado}","nombre":"${c.nombre}","password":"${c.password}","rol":"${c.rol}","usuario":"${c.usuario}"},
	</c:forEach>
}

function rellenarCampos(valorSeleccionado){
	var sel = JSONObject[valorSeleccionado];
	document.getElementById("nombre").value=sel.nombre;
	document.getElementById("password").value=sel.password;
	document.getElementById("usuario").value=sel.usuario;
	document.getElementById("cuenta").value=sel.cuenta;
	document.getElementById("direccion").value=sel.direccion;
	document.getElementById("email").value=sel.email;
	document.getElementById("rol").value=sel.rol;
	document.getElementById("avisoPolicia").value=sel.avisoPolicia;
	document.getElementById("habilitado").value=sel.habilitado;
}
</script>
</body>
</html>