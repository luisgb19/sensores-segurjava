package controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import model.DtoAlarma;
import model.Persona;
import model.Sensor;
import service.ServiceAlarmas;
import service.ServiceSensores;

@Controller
public class ClienteController {
	@Autowired
	ServiceSensores sSensores;
	@Autowired
	ServiceAlarmas sAlarmas;

	@GetMapping(value="/toDashboard")
	public String dashboard(@SessionAttribute("persona") Persona cliente,
						HttpServletRequest request) {
		List<Sensor> sensores = sSensores.obtenerSensoresPorCliente(cliente); //cliente.getSensores();
		Iterator<Sensor> iter = sensores.iterator();
		iter.forEachRemaining(System.out::println);
		request.setAttribute("sensores", sensores);
		return "cliente-menu";
	}
	
	@RequestMapping(value="/toGestionarSensores",method = {RequestMethod.GET,RequestMethod.POST})
	public String listadoSensores(@SessionAttribute("persona") Persona cliente,
						HttpServletRequest request) {
		List<Sensor> sensores = sSensores.obtenerSensoresPorCliente(cliente); //cliente.getSensores();
//		Iterator<Sensor> iter = sensores.iterator();
//		iter.forEachRemaining(System.out::println);
		request.setAttribute("sensores", sensores);
		
		List<DtoAlarma> alarmas = sAlarmas.obtenerAlarmasPorCliente(cliente);
		request.setAttribute("alarmas", alarmas);
		return "cliente-sensores";
	}

	@PostMapping(value="/doBajaSensor")
	public String eliminarSensor(@RequestParam("idSensor") int idSensor,
			@SessionAttribute("persona") Persona cliente, HttpServletRequest request) {
		System.out.println("Eliminando sensor: "+idSensor);
		sSensores.eliminarSensorPorId(cliente, idSensor);
//		List<Sensor> sensores = sSensores.obtenerSensoresPorCliente(cliente); 
//		request.setAttribute("sensores", sensores);
//		List<DtoAlarma> alarmas = sAlarmas.obtenerAlarmasPorCliente(cliente);
//		request.setAttribute("alarmas", alarmas);
		return "forward:/toGestionarSensores";
	}

	@PostMapping(value="/doAltaSensor")
	public String contratarSensor(@RequestParam("lugar") String ubicacion,
			@SessionAttribute("persona") Persona cliente, HttpServletRequest request) {
		sSensores.contratarSensor(cliente, ubicacion);
//		List<Sensor> sensores = sSensores.obtenerSensoresPorCliente(cliente); 
//		request.setAttribute("sensores", sensores);
//		List<DtoAlarma> alarmas = sAlarmas.obtenerAlarmasPorCliente(cliente);
//		request.setAttribute("alarmas", alarmas);
		return "forward:/toGestionarSensores";
	}

}