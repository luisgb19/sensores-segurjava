package service;

import java.util.List;

import model.Persona;
import model.Sensor;

public interface ServiceSensores {
	String buscarDireccion(int idSensor);
	void actualizarEstado(int idSensor, boolean estado);
	void actualizarModo(int idSensor, boolean modo);
	List<Sensor> obtenerSensoresPorCliente(Persona cliente);
	void eliminarSensorPorIdSensor(int idSensor);

	void eliminarSensorPorId(Persona cliente, int idSensor);
	void contratarSensor(Persona cliente, String ubicacion);
}