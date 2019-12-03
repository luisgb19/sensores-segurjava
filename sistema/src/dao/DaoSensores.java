package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Persona;
import model.Sensor;

public interface DaoSensores extends JpaRepository<Persona,Integer>{

	@Query("select s From Sensores s where s.idCliente=?1 and s.estado=1")
	List<Sensor> findByIdCliente(String idCliente);
}
