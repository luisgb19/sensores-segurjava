package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.ServicePersonas;
import service.ServiceSensores;

@RestController
public class SensoresController {
	@Autowired
	ServiceSensores sSensores;
	@Autowired
	ServicePersonas sPersonas;

	@GetMapping(value="/sensor/actualizar/estado")
	public void actualizarEstado(@RequestParam("idSensor") int idSensor, 
							@RequestParam("estado") boolean estado) {
		sSensores.actualizarEstado(idSensor, estado);
		System.out.println("Sensor "+idSensor+", actualizado a "+estado);
	}

	@GetMapping(value="/sensor/actualizar/modo")
	public void cambiarModo(@RequestParam("idSensor") int idSensor, 
							@RequestParam("modo") String modo) {
		sSensores.actualizarModo(idSensor, false);
		System.out.println("Sensor "+idSensor+", "+modo);
	}

	@DeleteMapping(value="/sensor/eliminar/{id}")
	public void eliminarSensor(@PathVariable("id") int idSensor) {
	sSensores.eliminarSensorPorIdSensor(idSensor);
	}

	@GetMapping(value="/cliente/deshabilitar",produces=MediaType.TEXT_PLAIN_VALUE)
	public String deshabilitarCliente(@RequestParam("idCliente") String dni) {
		sPersonas.deshabilitarCliente(dni);
		return "Usuario con "+dni+" deshabilitado.";		
	}
}