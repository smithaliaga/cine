package com.teamwork.cineperu.entidad.request;

public class RegisterIntentTriviaRequest extends  UserTokenRequest{

    public Long codigoTrivia;
    public boolean estadoRespuesta;

    public Long getCodigoTrivia() {
        return codigoTrivia;
    }

    public void setCodigoTrivia(Long codigoTrivia) {
        this.codigoTrivia = codigoTrivia;
    }

    public boolean isEstadoRespuesta() {
        return estadoRespuesta;
    }

    public void setEstadoRespuesta(boolean estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }
}
