package com.teamwork.cineperu.bean;

public class BeanHorario {

	private Long codigoHorario;
	private String descripcionHorario;
	private long codigoSala;
	private String nombreSala;
	
	public Long getCodigoHorario() {
		return codigoHorario;
	}
	public void setCodigoHorario(Long codigoHorario) {
		this.codigoHorario = codigoHorario;
	}
	public String getDescripcionHorario() {
		return descripcionHorario;
	}
	public void setDescripcionHorario(String descripcionHorario) {
		this.descripcionHorario = descripcionHorario;
	}
	public long getCodigoSala() {
		return codigoSala;
	}
	public void setCodigoSala(long codigoSala) {
		this.codigoSala = codigoSala;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	
}
