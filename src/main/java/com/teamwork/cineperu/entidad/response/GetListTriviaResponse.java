package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.bean.BeanTriviaPregunta;

import java.util.List;

public class GetListTriviaResponse extends EntityWSBase{

    private Long codigoTrivia;
    private List<BeanTriviaPregunta> preguntas;

    public Long getCodigoTrivia() {
        return codigoTrivia;
    }

    public void setCodigoTrivia(Long codigoTrivia) {
        this.codigoTrivia = codigoTrivia;
    }

    public List<BeanTriviaPregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<BeanTriviaPregunta> preguntas) {
        this.preguntas = preguntas;
    }

}
