package com.teamwork.cineperu.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoHorario;
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "codigo_pelicula")
	@JsonIgnore
	private Pelicula pelicula;
	@ManyToOne
	@JoinColumn(name = "codigo_cine")
	@JsonIgnore
	private Cine cine;
	private boolean estadoRegistro;

	public Long getCodigoHorario() {
		return codigoHorario;
	}

	public void setCodigoHorario(Long codigoHorario) {
		this.codigoHorario = codigoHorario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public boolean isEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(boolean estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

}
