package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoPersonas;
import dao.DaoSensores;
import model.Persona;
import model.Sensor;

@Service
public class ServiceSensoresImpl implements ServiceSensores {
	@Autowired
	DaoSensores daoSensores;
	@Autowired
	DaoPersonas daoPersonas;

	@Override
	public String buscarDireccion(int idSensor) {
		Sensor sensor = daoSensores.findById(idSensor).orElse(null);
		if (sensor == null) return "sin dirección";
		return sensor.getPersona().getDireccion();
	}

	@Override
	public void actualizarEstado(int idSensor, boolean estado) {
		Sensor sensor = daoSensores.findById(idSensor).orElse(null);
		sensor.setEstado(estado);
		daoSensores.save(sensor);
	}

	@Override
	public void actualizarModo(int idSensor, boolean modo) {
		Sensor sensor = daoSensores.findById(idSensor).orElse(null);
		sensor.setModo(modo);
		daoSensores.save(sensor);
	}

	@Override
	public List<Sensor> obtenerSensoresPorCliente(Persona cliente) {
		return daoSensores.findByPersona(cliente);
	}

	@Override
	public void eliminarSensorPorIdSensor(int idSensor) {
		if(daoSensores.existsById(idSensor)) {
			daoSensores.deleteById(idSensor);
			System.out.println("Eliminado sensor: "+idSensor);
		}
	}

	@Override
	public void eliminarSensorPorId(Persona cliente, int idSensor) {
		// TODO Auto-generated method stub
		Sensor sensor = daoSensores.findById(idSensor).orElse(null);
		if(sensor != null) {
			cliente.removeSensor(sensor);
			daoPersonas.save(cliente);
		}
	}

	@Override
	public void contratarSensor(Persona cliente, String ubicacion) {
		Sensor sensor = new Sensor(ubicacion, cliente);
		daoSensores.save(sensor);
	}

}
