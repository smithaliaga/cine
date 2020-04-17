package com.teamwork.cineperu.entidad.request;

public class GetListButacaRequest extends UserTokenRequest {

	private Long codigoSala;

	public Long getCodigoSala() {
		return codigoSala;
	}

	public void setCodigoSala(Long codigoSala) {
		this.codigoSala = codigoSala;
	}

}
