package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import service.ServiceAlarmas;
import model.Alarma;

@RestController
public class AlarmasController {
	@Autowired
	ServiceAlarmas sAlarmas;
	
	@GetMapping(value="/alarmas/cliente/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Alarma> obtenerAlarmasPorCliente(@PathVariable("id") String dni) {
//		System.out.println(sAlarmas.obtenerAlarmasPorCliente(dni));
		return sAlarmas.obtenerAlarmasPorCliente(dni);
	}

}
