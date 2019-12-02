package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the personas database table.
 * 
 */
@Entity
@Table(name="personas")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private Object avisoPolicia;

	private String cuenta;

	private String direccion;

	private String email;

	private byte habilitado;

	private String nombre;

	private String password;

	private Object rolAdmin;

	private String usuario;

	//bi-directional many-to-one association to Sensor
	@OneToMany(mappedBy="persona")
	private List<Sensor> sensores;

	public Persona() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Object getAvisoPolicia() {
		return this.avisoPolicia;
	}

	public void setAvisoPolicia(Object avisoPolicia) {
		this.avisoPolicia = avisoPolicia;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(byte habilitado) {
		this.habilitado = habilitado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Object getRolAdmin() {
		return this.rolAdmin;
	}

	public void setRolAdmin(Object rolAdmin) {
		this.rolAdmin = rolAdmin;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Sensor> getSensores() {
		return this.sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public Sensor addSensore(Sensor sensore) {
		getSensores().add(sensore);
		sensore.setPersona(this);

		return sensore;
	}

	public Sensor removeSensore(Sensor sensore) {
		getSensores().remove(sensore);
		sensore.setPersona(null);

		return sensore;
	}

}