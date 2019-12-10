package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import model.Alarma;

public interface DaoAlarmas extends JpaRepository<Alarma,Integer>{

//	List<Alarma> findByIdSensor(int idSensor);
	
//	@Query("select a From alarmas a where a.fecha>=?1 and a.fecha<=?2")
//	List<Alarma> findByFecha(java.util.Date fecIni, java.util.Date fecFin);
}
