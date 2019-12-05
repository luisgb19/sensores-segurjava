package service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoAlarmas;
import dao.DaoSensores;
import model.Alarma;

@Service
public class ServiceAlarmasImpl implements ServiceAlarmas {
	@Autowired
	DaoAlarmas daoAlarmas;
	@Autowired
	DaoSensores daoSensores;
	
	@Override
	public boolean registrarAlarma(int idSensor, Date fecha) {
		Alarma alarma = new Alarma(idSensor, fecha);
		daoAlarmas.save(alarma);
		return daoSensores.hasAvisoPolicia(idSensor);
	}

}
