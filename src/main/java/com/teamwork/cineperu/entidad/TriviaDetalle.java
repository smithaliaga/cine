package com.teamwork.cineperu.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class TriviaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_trivia_detalle")
    private Long codigoDetalleTrivia;

    @ManyToOne
    @JoinColumn(name = "codigo_trivia")
    @JsonIgnore
    private Trivia trivia;

    private String pregunta;
    private boolean estadoRegistro;

    public Long getCodigoDetalleTrivia() {
        return codigoDetalleTrivia;
    }

    public void setCodigoDetalleTrivia(Long codigoDetalleTrivia) {
        this.codigoDetalleTrivia = codigoDetalleTrivia;
    }

    public Trivia getTrivia() {
        return trivia;
    }

    public void setTrivia(Trivia trivia) {
        this.trivia = trivia;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
