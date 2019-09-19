package com.teamwork.cineperu.bean;

public class BeanUsuarioTokenFirebase {

	private String tokenFirebase;
	private String nombrePersona;
	private Boolean seleccionado;

	public String getTokenFirebase() {
		return tokenFirebase;
	}

	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public Boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

}
