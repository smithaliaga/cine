package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.bean.BeanMontoPago;

public class GetMontoPagoResponse extends EntityWSBase {

	private BeanMontoPago montoPago;

	public BeanMontoPago getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(BeanMontoPago montoPago) {
		this.montoPago = montoPago;
	}

}
