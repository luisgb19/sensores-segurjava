package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoPersonas;
import model.Persona;
<<<<<<< HEAD

=======
>>>>>>> refs/heads/spring-security
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

}
