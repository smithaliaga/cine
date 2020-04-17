package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.bean.BeanHorario;

import java.util.List;

public class GetListHorarioResponse extends EntityWSBase {

	private List<BeanHorario> lista;

	public List<BeanHorario> getLista() {
		return lista;
	}

	public void setLista(List<BeanHorario> lista) {
		this.lista = lista;
	}

}
