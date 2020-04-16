package com.teamwork.cineperu.entidad.request;

public class GetListHorarioRequest extends UserTokenRequest {

	private Long codigoPelicula;

	public Long getCodigoPelicula() {
		return codigoPelicula;
	}

	public void setCodigoPelicula(Long codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}

}
