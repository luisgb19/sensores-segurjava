package service;

import java.util.Date;
import java.util.List;

//import model.Alarma;
import model.DtoAlarma;
import model.Persona;

public interface ServiceAlarmas {
	boolean registrarAlarma(int idSensor, Date fecha);
	void enviarAviso(String direccion, Date fecha);
	List<DtoAlarma> obtenerAlarmasPorCliente(Persona cliente);
	List<DtoAlarma> obtenerAlarmasPorIdCliente(String dni);
}
