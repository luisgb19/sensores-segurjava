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
	@ManyToOne
	@JoinColumn(name="idSensor")
	private Sensor sensor;

	public Alarma() {
	}

	public AlarmaPK getId() {
		return this.id;
	}

	public void setId(AlarmaPK id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}