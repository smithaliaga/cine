package com.teamwork.cineperu.entidad.response;

public class RegisterIntentTriviaResponse extends EntityWSBase{

    private Long codigoTriviaUsuario;
    private boolean estadoRespuesta;
    private boolean estadoCobro;

    public Long getCodigoTriviaUsuario() {
        return codigoTriviaUsuario;
    }

    public void setCodigoTriviaUsuario(Long codigoTriviaUsuario) {
        this.codigoTriviaUsuario = codigoTriviaUsuario;
    }

    public boolean isEstadoRespuesta() {
        return estadoRespuesta;
    }

    public void setEstadoRespuesta(boolean estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }

    public boolean isEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(boolean estadoCobro) {
        this.estadoCobro = estadoCobro;
    }
}
