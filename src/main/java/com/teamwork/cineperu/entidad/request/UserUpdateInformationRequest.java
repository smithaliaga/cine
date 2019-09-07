package com.teamwork.cineperu.entidad.request;

public class UserUpdateInformationRequest extends UserTokenRequest {

	private String email;
	private String direccion;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
