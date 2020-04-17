package com.teamwork.cineperu.entidad.request;

import java.util.List;

public class GetMontoPagoRequest extends UserTokenRequest {

	private List<Long> butacas;
	private Long codigoSala;

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

}
