package service;

import java.util.Date;
import java.util.List;

import model.Alarma;

public interface ServiceAlarmas {
	public boolean registrarAlarma(int idSensor, Date fecha);
	public List<Alarma> obtenerAlarmasPorCliente(String dni);
}
