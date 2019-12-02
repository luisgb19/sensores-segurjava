package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sensores database table.
 * 
 */
@Entity
@Table(name="sensores")
@NamedQuery(name="Sensor.findAll", query="SELECT s FROM Sensor s")
public class Sensor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSensor;

	private Object estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;

	private Object modo;

	private String ubicacion;

	//bi-directional many-to-one association to Alarma
	@OneToMany(mappedBy="sensor")
	private List<Alarma> alarmas;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Persona persona;

	public Sensor() {
	}

	public int getIdSensor() {
		return this.idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public Object getEstado() {
		return this.estado;
	}

	public void setEstado(Object estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Object getModo() {
		return this.modo;
	}

	public void setModo(Object modo) {
		this.modo = modo;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Alarma> getAlarmas() {
		return this.alarmas;
	}

	public void setAlarmas(List<Alarma> alarmas) {
		this.alarmas = alarmas;
	}

	public Alarma addAlarma(Alarma alarma) {
		getAlarmas().add(alarma);
		alarma.setSensor(this);

		return alarma;
	}

	public Alarma removeAlarma(Alarma alarma) {
		getAlarmas().remove(alarma);
		alarma.setSensor(null);

		return alarma;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}