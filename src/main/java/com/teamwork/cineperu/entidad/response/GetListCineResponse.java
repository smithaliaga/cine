package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.entidad.Cine;

import java.util.List;

public class GetListCineResponse extends EntityWSBase {

	private List<Cine> lista;

	public List<Cine> getLista() {
		return lista;
	}

	public void setLista(List<Cine> lista) {
		this.lista = lista;
	}

}
