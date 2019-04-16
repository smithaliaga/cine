package com.teamwork.cineperu.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class TriviaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoTriviaUsuario;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "codigo_trivia")
    @JsonIgnore
    private Trivia trivia;

    private boolean estadoRespuesta;
    private boolean estadoCobro;

    public Long getCodigoTriviaUsuario() {
        return codigoTriviaUsuario;
    }

    public void setCodigoTriviaUsuario(Long codigoTriviaUsuario) {
        this.codigoTriviaUsuario = codigoTriviaUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Trivia getTrivia() {
        return trivia;
    }

    public void setTrivia(Trivia trivia) {
        this.trivia = trivia;
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
