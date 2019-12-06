package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Persona;

public interface DaoPersonas extends JpaRepository<Persona,String>{

		Persona findByUsuarioAndPassword(String user, String pwd);
}
