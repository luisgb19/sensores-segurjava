package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoAlarmas;
import dao.DaoSensores;
import model.Alarma;
import model.Persona;
import model.Sensor;
@Service
public class ServiceInformesImpl implements ServiceInformes {
	@Autowired
	DaoAlarmas daoAlarmas;
	@Autowired
	DaoSensores daoSensores;
	@Override
	public List<Alarma> getInformeActividad(String dni){
		// devolver todos los Sensores de un cliente
		List<Sensor> sensores = daoSensores.findByIdCliente(dni);
		List<Alarma> alarmas = null;
		// buscar las alarmas de cada sensor
		if(sensores.size()>0) {
			for(Sensor sensorCliente : sensores) {
				List<Alarma> alarmasSensor = daoAlarmas.findByIdSensor((Integer)sensorCliente.getIdSensor()); 
				if(alarmasSensor.size()>0) {
					for (Alarma alarmaSen : alarmasSensor) {
						alarmas.add(alarmaSen);
					}
				}
			}
		}
		return alarmas;
	}
	
	@Override
	public List<Alarma> getInformeTemporal(Date fecIni, Date fecFin) {
		// TODO Auto-generated method stub
		return null;
	}

}
