package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
	public List<Persona> getUsuarios() {
		List<Persona> p = daoPersonas.findAll();
		List<Persona> pDescodificada = new ArrayList<Persona>();
		for (Persona persona : p) {
			String pwdCodificada = persona.getPassword();
			String pwdDescodificada = descodificarPwd(pwdCodificada);
			persona.setPassword(pwdDescodificada);
			pDescodificada.add(persona);
		}
		return pDescodificada;
	}

	@Override
	public void actualizarUsuario(Persona p) {
		String pwdDescodificada = p.getPassword();
		p.setPassword(codificarPwd(pwdDescodificada));
		daoPersonas.save(p);
	}
	
	private String descodificarPwd(String pwdCodificada) {
		String pwdCodificadaTrim = pwdCodificada.trim();
		byte[] decodedBytes = Base64.decodeBase64(pwdCodificadaTrim); //getDecoder().decode
		return new String(decodedBytes);
	}
	
	public static String codificarPwd(String pwd) {
		byte[] encodedBytes = Base64.encodeBase64(pwd.getBytes()); //getEncoder().encode
		String sEncoded = new String(encodedBytes);
		int iEncoded = sEncoded.length();
		for (int i = iEncoded; i < 64; i++) {
			sEncoded = sEncoded + " ";
		}
		return new String(sEncoded);
	}

	@Override
	public void codificarBBDD() {
		// ejecutar para codificar las pwd que no tengan una longitud mínima de 64 
		List<Persona> p = daoPersonas.findAll();
		for (Persona persona : p) {
			String pwd = persona.getPassword();
			if(pwd.length()<64) {
				// No está codificada
				Persona pSinCodificar = persona;
				String pCodificada = codificarPwd(pwd);
				pSinCodificar.setPassword(pCodificada);
				daoPersonas.save(pSinCodificar);
			}
		}
	}

	@Override
	public void codificarBBDDPwd(String pwdPropuesta) {
		// ejecutar para codificar la misma pwd a todas las personas
		List<Persona> p = daoPersonas.findAll();
		for (Persona persona : p) {
			String pwd = "admin"; // default pwd
			if(pwdPropuesta.length()>3) pwd = pwdPropuesta;
			// No está codificada
			Persona pSinCodificar = persona;
			String pCodificada = codificarPwd(pwd);
			pSinCodificar.setPassword(pCodificada);
			//grabar esta persona
			daoPersonas.save(pSinCodificar);
		}
	}	

	@Override
	public Persona obtenerPersona(String dni) {
		return daoPersonas.findById(dni).orElse(null);
	}

	@Override
	public void deshabilitarCliente(String dni) {
		Persona cliente = daoPersonas.findById(dni).orElse(null);
		System.out.println(cliente.getRol());
		if(cliente.getRol().equals("ROLE_USER")) cliente.setHabilitado(false);
			daoPersonas.save(cliente);
	}

}
