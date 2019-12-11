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

	private boolean avisoPolicia;

	private String cuenta;

	private String direccion;

	private String email;

	private boolean habilitado;

	private String nombre;

	private String password;

	private String rol;

	private String usuario;

	//bi-directional many-to-one association to Sensor
	@OneToMany(mappedBy="persona")
	private List<Sensor> sensores;

	public Persona() {
		super();
	}

	public Persona(String dni, boolean avisoPolicia, String cuenta, String direccion, String email, boolean habilitado,
			String nombre, String password, String rol, String usuario) {
		this.dni = dni;
		this.avisoPolicia = avisoPolicia;
		this.cuenta = cuenta;
		this.direccion = direccion;
		this.email = email;
		this.habilitado = habilitado;
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
		this.usuario = usuario;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public boolean getAvisoPolicia() {
		return this.avisoPolicia;
	}

	public void setAvisoPolicia(boolean avisoPolicia) {
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

	public boolean getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(boolean habilitado) {
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

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
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

/*	public Sensor addSensor(Sensor sensor) {
		this.sensores.add(sensor);
		sensor.setPersona(this);
		return sensor;
	}*/
	public void addSensor(Sensor sensor) {
		this.sensores.add(sensor);
	}

	public Sensor removeSensor(Sensor sensor) {
		this.sensores.remove(sensor);
		sensor.setPersona(null);
		return sensor;
	}
/*	public void removeSensor(Sensor sensor) {
		this.sensores.remove(sensor);
	}*/

}