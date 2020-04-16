package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.entidad.Horario;

import java.util.List;

public class GetListHorarioResponse extends EntityWSBase {

	private List<Horario> lista;

	public List<Horario> getLista() {
		return lista;
	}

	public void setLista(List<Horario> lista) {
		this.lista = lista;
	}

}
