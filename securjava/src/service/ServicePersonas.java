package service;

import java.util.List;

import model.Persona;

public interface ServicePersonas {
	Persona getPersona(String user, String pwd);
	void guardarPersona(Persona p);
	List<Persona> getClientes();
	Persona obtenerPersona(String dni);
	void deshabilitarCliente(String dni);
	List<Persona> getUsuarios();
	void actualizarUsuario(Persona p);
	void codificarBBDD();
	void codificarBBDDPwd(String pwdPropuesta);
}
