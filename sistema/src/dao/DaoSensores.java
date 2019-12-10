package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Sensor;

public interface DaoSensores extends JpaRepository<Sensor,Integer>{
	@Query("SELECT s.persona.avisoPolicia FROM Sensor s WHERE s.idSensor=?")
	boolean hasAvisoPolicia(int idSensor);
	@Query("select s From Sensores s where s.persona.dni=?1 and s.estado=1")
	List<Sensor> findActiveByCliente(String dni);
}
