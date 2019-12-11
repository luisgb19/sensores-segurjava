package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoAlarmas;
import dao.DaoPersonas;
import dao.DaoSensores;
import model.Alarma;
import model.Persona;
import model.Sensor;
@Service
public class ServiceInformesImpl implements ServiceInformes {
	@Autowired
	DaoPersonas daoPersonas;
	@Autowired
	DaoSensores daoSensores;
	@Autowired
	DaoAlarmas daoAlarmas;

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

	@Override
	public List<Sensor> getInformeFullActividad(String dni) {
		return daoSensores.findByIdCliente(dni); //buscarActividadPorCliente
	}

	@Override
	public List<Object[]> getInformeFullTemporal(String fecIni, String fecFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date fechaInicio = new Date();
		Date fechaFin = new Date();
		try {
			fechaInicio = sdf.parse(fecIni);
			fechaFin = sdf.parse(fecFin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("********************************************************");
		System.out.println("fechaInicio = "+fechaInicio);
		System.out.println("fechaFin = "+fechaFin);
		return daoPersonas.informeTemporal(fechaInicio,fechaFin);
	}	

}