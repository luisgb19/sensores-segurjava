package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoAlarmas;
import dao.DaoPersonas;
import dao.DaoSensores;
import model.Alarma;

@Service
public class ServiceAlarmasImpl implements ServiceAlarmas {
	@Autowired
	DaoAlarmas daoAlarmas;
	@Autowired
	DaoSensores daoSensores;
	@Autowired
	DaoPersonas daoPersonas;
	
	@Override
	public boolean registrarAlarma(int idSensor, Date fecha) {
		Alarma alarma = new Alarma(idSensor, fecha);
		daoAlarmas.save(alarma);
		return daoSensores.hasAvisoPolicia(idSensor);
	}

	@Override
	public List<Alarma> obtenerAlarmasPorCliente(String dni) {
		//	System.out.println(daoAlarmas.findAll());
		if (daoPersonas.existsById(dni)) {
			System.out.println(dni);
			return daoAlarmas.findAll();
		} else {
			return null;
		}
			
	}

}
