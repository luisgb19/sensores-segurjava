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

	@Override
	public Persona obtenerPersona(String dni) {
		return daoPersonas.findById(dni).orElse(null);
	}

	@Override
	public void deshabilitarCliente(String dni) {
		Persona cliente = daoPersonas.findById(dni).orElse(null);
		if(cliente.getRol().equals("ROL_USER")) cliente.setHabilitado(false);
			daoPersonas.save(cliente);
	}

}
