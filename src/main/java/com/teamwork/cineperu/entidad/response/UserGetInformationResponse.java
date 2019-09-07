package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.entidad.Persona;

public class UserGetInformationResponse extends EntityWSBase {

	private Persona persona;

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
