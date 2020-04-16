package com.teamwork.cineperu.entidad.request;

public class GetListHorarioRequest extends UserTokenRequest {

	private Long codigoPelicula;
	private Long codigoCine;

	public Long getCodigoPelicula() {
		return codigoPelicula;
	}

	public void setCodigoPelicula(Long codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}

	public Long getCodigoCine() {
		return codigoCine;
	}

	public void setCodigoCine(Long codigoCine) {
		this.codigoCine = codigoCine;
	}

}
