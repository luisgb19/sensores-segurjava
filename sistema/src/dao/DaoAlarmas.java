package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Alarma;
import model.Sensor;

public interface DaoAlarmas extends JpaRepository<Sensor,Integer>{

//	List<Alarma> findByIdSensor(int idSensor);
}
