package com.teamwork.cineperu.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanUsuarioTokenFirebaseDTO {

	private List<BeanUsuarioTokenFirebase> clientes;

	public BeanUsuarioTokenFirebaseDTO() {
		this.clientes = new ArrayList<>();
	}

	public BeanUsuarioTokenFirebaseDTO(List<BeanUsuarioTokenFirebase> clientes) {
		this.clientes = clientes;
	}

	public List<BeanUsuarioTokenFirebase> getClientes() {
		return clientes;
	}

	public void setClientes(List<BeanUsuarioTokenFirebase> clientes) {
		this.clientes = clientes;
	}

	public void addCliente(List<BeanUsuarioTokenFirebase> clientes) {
		this.clientes = clientes;
	}
}
