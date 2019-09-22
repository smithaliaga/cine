package com.teamwork.cineperu.entidad;

import javax.persistence.*;

@Entity
public class Cine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoCine;

	private String nombre;
	private double latitud;
	private double longitud;

	public Long getCodigoCine() {
		return codigoCine;
	}

	public void setCodigoCine(Long codigoCine) {
		this.codigoCine = codigoCine;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

}
