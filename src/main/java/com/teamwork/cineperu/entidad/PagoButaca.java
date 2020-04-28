package com.teamwork.cineperu.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PagoButaca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoPagoButaca;
	@ManyToOne
	@JoinColumn(name = "codigo_butaca")
	@JsonIgnore
	private Butaca butaca;
	@ManyToOne
	@JoinColumn(name = "codigo_pago")
	@JsonIgnore
	private Pago pago;
	private double precio;

	public Long getCodigoPagoButaca() {
		return codigoPagoButaca;
	}

	public void setCodigoPagoButaca(Long codigoPagoButaca) {
		this.codigoPagoButaca = codigoPagoButaca;
	}

	public Butaca getButaca() {
		return butaca;
	}

	public void setButaca(Butaca butaca) {
		this.butaca = butaca;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
