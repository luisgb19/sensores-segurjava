package model;

import java.util.Date;

public class DtoAlarma {
	private int idSensor;
	private Date fecha;
	
	public DtoAlarma() {
		super();
	}
	public DtoAlarma(int idSensor, Date fecha) {
		super();
		this.idSensor = idSensor;
		this.fecha = fecha;
	}
	public int getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
