package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.bean.BeanPago;

public class RealizarPagoResponse extends EntityWSBase {

	private BeanPago pago;

	public BeanPago getPago() {
		return pago;
	}

	public void setPago(BeanPago pago) {
		this.pago = pago;
	}

}
