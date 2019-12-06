package service;

import java.util.Date;
import java.util.List;

import model.Alarma;

public interface ServiceInformes {
	List<Alarma> getInformeActividad(String dni);
	List<Alarma> getInformeTemporal(Date fecIni, Date fecFin);
}
