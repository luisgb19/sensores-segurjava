package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoPersonas;
import model.Persona;

@Service
public class ServicePersonasImpl implements ServicePersonas {
	@Autowired
	DaoPersonas daoPersonas;
	@Override
	public Persona getPersona(String user, String pwd) {
		return daoPersonas.findByUsuarioAndPassword(user, pwd);
	}

	@Override
	public void guardarPersona(Persona p) {
		if (!daoPersonas.existsById(p.getDni())) {
			daoPersonas.save(p);
		}

	}
	@Override
	public List<Persona> getClientes() {
		List<Persona> clientes = daoPersonas.findAll();
/*		
		for (Persona persona : clientes) {
			if(persona.getRol().equalsIgnoreCase("ROLE_ADMIN")) {
				clientes.remove(persona);
			}
		}
		*/
		return clientes;
	}

}
