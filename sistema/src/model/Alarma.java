package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the alarmas database table.
 * 
 */
@Entity
@Table(name="alarmas")
@NamedQuery(name="Alarma.findAll", query="SELECT a FROM Alarma a")
public class Alarma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlarmaPK id;

	//bi-directional many-to-one association to Sensor
	@Column(name="idSensor",insertable = false, updatable = false)
	private int idSensor;

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public Alarma() {
	}

	public AlarmaPK getId() {
		return this.id;
	}

	public void setId(AlarmaPK id) {
		this.id = id;
	}
}