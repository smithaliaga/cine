package com.teamwork.cineperu.bean;

public class BeanPago {

	private String numeroTarjetaOfuscado;
	private String fechaTransaccion;
	private double subTotal;
	private double igv;
	private double total;

	public String getNumeroTarjetaOfuscado() {
		return numeroTarjetaOfuscado;
	}

	public void setNumeroTarjetaOfuscado(String numeroTarjetaOfuscado) {
		this.numeroTarjetaOfuscado = numeroTarjetaOfuscado;
	}

	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
