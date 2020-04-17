package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.bean.BeanButaca;

import java.util.List;

public class GetListButacaResponse extends EntityWSBase {

	private List<BeanButaca> lista;

	public List<BeanButaca> getLista() {
		return lista;
	}

	public void setLista(List<BeanButaca> lista) {
		this.lista = lista;
	}

}
