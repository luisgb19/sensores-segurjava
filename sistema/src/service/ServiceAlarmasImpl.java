package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.DaoAlarmas;
import dao.DaoPersonas;
import dao.DaoSensores;
import model.Alarma;
import model.DtoAlarma;
import model.DtoAviso;
import model.Persona;
import model.Sensor;

@Service
public class ServiceAlarmasImpl implements ServiceAlarmas {
	@Autowired
	DaoAlarmas daoAlarmas;
	@Autowired
	DaoSensores daoSensores;
	@Autowired
	DaoPersonas daoPersonas;
	RestTemplate template = new RestTemplate();
	String urlPolicia ="http://localhost:8000/sensores/aviso";

	@Override
	public boolean registrarAlarma(int idSensor, Date fecha) {
		Alarma alarma = new Alarma(idSensor, fecha);
		System.out.println("Alarma registrada con fecha: "+fecha);
		daoAlarmas.save(alarma);
		return daoSensores.hasAvisoPolicia(idSensor);
	}

	@Override
	public void enviarAviso(String direccion, Date fecha) {
		System.out.println(direccion+", "+fecha);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		String dateString = format.format( new Date()   );
		DtoAviso aviso = new DtoAviso(direccion,dateString);
		String acuse = template.postForObject(urlPolicia, aviso, String.class);
		System.out.println("Aviso enviado");
		System.out.println(acuse);

	}

	@Override
	public List<DtoAlarma> obtenerAlarmasPorCliente(Persona cliente) {
		//	if (!daoPersonas.existsById(dni)) return null;
//		Persona cliente = daoPersonas.findById(dni).orElse(null);
		System.out.println(cliente.getNombre());
		List<Sensor> sensores = cliente.getSensores();
		int[] lista = new int[sensores.size()];
//		sensores.forEach((s) -> lista.add(s.getIdSensor()));
		for(int i=0; i < sensores.size(); i++) lista[i] = sensores.get(i).getIdSensor();
			System.out.println(lista);
			List<Alarma> alarmas = daoAlarmas.findAllByIdSensor(lista);
			System.out.println(alarmas);
			List<DtoAlarma> listado = new ArrayList<>();
			DtoAlarma alarm;
			for(Alarma a:alarmas) {
				alarm = new DtoAlarma(a.getIdSensor(),a.getId().getFecha());
//				System.out.println(alarm.getFecha());
				listado.add(alarm);
			}
//			System.out.println(listado);
			return listado;			
			
	}

	@Override
	public List<DtoAlarma> obtenerAlarmasPorIdCliente(String dni) {
		// TODO Auto-generated method stub
		Persona cliente = daoPersonas.findById(dni).orElse(null);
		return obtenerAlarmasPorCliente(cliente);
	}

}
