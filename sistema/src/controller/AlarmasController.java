package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dao.DaoAlarmas;
import model.Alarma;
import model.DtoAlarma;
import reactor.core.publisher.Flux;
import service.ServiceAlarmas;
import service.ServiceSensores;

@RestController
public class AlarmasController {
	@Autowired
	ServiceAlarmas sAlarmas;
	@Autowired
	DaoAlarmas daoAlarmas;
	@Autowired
	ServiceSensores sSensores;
//	@Autowired
//	SensoresController cSensores;
//	@Autowired
//	RestTemplate template;
//	String urlPolicia ="http://localhost:8000/sensores/aviso";
	
	@CrossOrigin(origins = "*")
	@GetMapping(value="/alarma/sensor/{id}",produces=MediaType.TEXT_PLAIN_VALUE)
	public String recibirAlarma(@PathVariable("id") int idSensor) {
//		cSensores.mostrarAlarma(Integer.toString(idSensor));
		Date fecha = new Date();
		boolean avisoPolicia = sAlarmas.registrarAlarma(idSensor, fecha);
		if(avisoPolicia) {
			System.out.println("Avisando a la policía...");
			sAlarmas.enviarAviso(sSensores.buscarDireccion(idSensor), fecha);
//			String acuse = template.postForObject(urlPolicia, new DtoAviso("direccion",fecha), String.class);
//			System.out.println(acuse);
		}
		return avisoPolicia?"Se ha enviado un aviso a la Policía.": "Ha contactado con el servicio de Alarmas.";
	}

	@GetMapping(value="/alarmas/cliente/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DtoAlarma> obtenerAlarmasPorCliente(@PathVariable("id") String dni) {
//		System.out.println(sAlarmas.obtenerAlarmasPorCliente(dni));
		return sAlarmas.obtenerAlarmasPorIdCliente(dni);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value="/sensores/alarma",produces="text/event-stream")
	public Flux<List<Alarma>> mostrarAlarma() {// static
		return Flux.create(fs->{
			List<Alarma> anterior=null;
			while(true) {
				List<Alarma> lista=daoAlarmas.findAll();
				if(cambio(anterior,lista)) {
					fs.next(lista);
				}
				anterior=lista;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	private boolean cambio(List<Alarma> anterior,List<Alarma> actual ) {
		
		if(anterior==null) {
			return true;
		}else {
			for(int i=0;i<actual.size();i++) {
				if(!anterior.get(i).getId().equals(actual.get(i).getId())){
					return true;
				}
			}
		}
		return false;
	}

}