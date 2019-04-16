package com.teamwork.cineperu.bean;

import java.util.List;

public class BeanTriviaPregunta{

    private Long codigoTriviaPregunta;
    private String pregunta;
    private List<BeanTriviaRespuesta> respuestas;

    public Long getCodigoTriviaPregunta() {
        return codigoTriviaPregunta;
    }

    public void setCodigoTriviaPregunta(Long codigoTriviaPregunta) {
        this.codigoTriviaPregunta = codigoTriviaPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<BeanTriviaRespuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<BeanTriviaRespuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
