package com.teamwork.cineperu.bean;

public class BeanTriviaRespuesta{

    private Long codigoTriviaRespuesta;
    private String respuesta;
    private boolean estadoRespuesta;

    public Long getCodigoTriviaRespuesta() {
        return codigoTriviaRespuesta;
    }

    public void setCodigoTriviaRespuesta(Long codigoTriviaRespuesta) {
        this.codigoTriviaRespuesta = codigoTriviaRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEstadoRespuesta() {
        return estadoRespuesta;
    }

    public void setEstadoRespuesta(boolean estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }
}