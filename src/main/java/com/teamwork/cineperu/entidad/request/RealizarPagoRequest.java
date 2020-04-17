package com.teamwork.cineperu.entidad.request;

import java.util.List;

public class RealizarPagoRequest extends UserTokenRequest {

	private List<Long> butacas;
	private Long codigoSala;
	private String numeroTarjeta;
	private String cvv;
	private String anioVencimiento;
	private String mesVencimiento;

	public List<Long> getButacas() {
		return butacas;
	}

	public void setButacas(List<Long> butacas) {
		this.butacas = butacas;
	}

	public Long getCodigoSala() {
		return codigoSala;
	}

	public void setCodigoSala(Long codigoSala) {
		this.codigoSala = codigoSala;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getAnioVencimiento() {
		return anioVencimiento;
	}

	public void setAnioVencimiento(String anioVencimiento) {
		this.anioVencimiento = anioVencimiento;
	}

	public String getMesVencimiento() {
		return mesVencimiento;
	}

	public void setMesVencimiento(String mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}

}
