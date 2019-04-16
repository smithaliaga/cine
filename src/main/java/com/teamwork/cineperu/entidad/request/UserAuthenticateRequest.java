package com.teamwork.cineperu.entidad.request;

public class UserAuthenticateRequest {

    private String usuario;
    private String clave;
    private String idioma;
    private String infoDispositivo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getInfoDispositivo() {
        return infoDispositivo;
    }

    public void setInfoDispositivo(String infoDispositivo) {
        this.infoDispositivo = infoDispositivo;
    }
}
