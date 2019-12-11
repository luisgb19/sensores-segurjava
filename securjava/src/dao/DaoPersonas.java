package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Persona;

public interface DaoPersonas extends JpaRepository<Persona,String>{

		Persona findByUsuarioAndPassword(String user, String pwd);
		@Query("Select p From Persona p Where p.rol='ROLE_USER'")
		List<Persona> listaClientes();

		@Query(value = "Select p.dni as dni, p.usuario as usuario, p.nombre as nombre, s.idSensor as idSensor, s.ubicacion as ubicacion, a.fecha as fecha From Personas p, Sensores s, Alarmas a where p.dni=s.idCLiente and s.idSensor=a.idSensor and a.fecha>=?1 and a.fecha<=?2", nativeQuery = true)
		List<Object[]> informeTemporal(java.util.Date FecIni, java.util.Date FecFin);		
}