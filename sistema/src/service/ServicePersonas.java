package service;

import model.Persona;

public interface ServicePersonas {
	Persona getPersona(String user, String pwd);
	void guardarPersona(Persona p);

}
