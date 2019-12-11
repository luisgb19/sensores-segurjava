package service;

import java.util.Date;
import java.util.List;

import model.Alarma;
import model.Sensor;

public interface ServiceInformes {
	List<Alarma> getInformeActividad(String dni);
	List<Alarma> getInformeTemporal(Date fecIni, Date fecFin);
	List<Object[]> getInformeFullTemporal(String fecIni, String fecFin);
	List<Sensor> getInformeFullActividad(String dni);
}
