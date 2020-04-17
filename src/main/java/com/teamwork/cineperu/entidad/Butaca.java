package com.teamwork.cineperu.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Butaca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoButaca;
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "codigo_sala")
	@JsonIgnore
	private Sala sala;
	@ManyToOne
	@JoinColumn(name = "codigo_estado_butaca")
	@JsonIgnore
	private EstadoButaca estadoButaca;
	private String fila;

	public Long getCodigoButaca() {
		return codigoButaca;
	}

	public void setCodigoButaca(Long codigoButaca) {
		this.codigoButaca = codigoButaca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public EstadoButaca getEstadoButaca() {
		return estadoButaca;
	}

	public void setEstadoButaca(EstadoButaca estadoButaca) {
		this.estadoButaca = estadoButaca;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

}
