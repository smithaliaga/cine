package com.teamwork.cineperu.entidad;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EstadoButaca {

	@Id
	private Long codigoEstadoButaca;
	private String nombre;
	private boolean estadoRegistro;

	public Long getCodigoEstadoButaca() {
		return codigoEstadoButaca;
	}

	public void setCodigoEstadoButaca(Long codigoEstadoButaca) {
		this.codigoEstadoButaca = codigoEstadoButaca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(boolean estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

}
