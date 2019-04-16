package com.teamwork.cineperu.entidad.request;

public class GetTriviaUsuarioRequest extends UserTokenRequest{

    private Long codigoTrivia;

    public Long getCodigoTrivia() {
        return codigoTrivia;
    }

    public void setCodigoTrivia(Long codigoTrivia) {
        this.codigoTrivia = codigoTrivia;
    }
}
