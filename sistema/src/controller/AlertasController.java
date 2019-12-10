package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dao.DaoAlarmas;
import model.Alarma;

@RestController
public class AlertasController {
	@Autowired
	DaoAlarmas daoAlertas;
	
	@GetMapping(value="/alertas/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Alarma> obtenerAlarmasPorCliente(@PathVariable("id") String dni) {
		return null;
		
	}

}
