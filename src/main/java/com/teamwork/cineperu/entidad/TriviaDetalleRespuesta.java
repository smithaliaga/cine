package com.teamwork.cineperu.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class TriviaDetalleRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoDetalleRespuesta;

    private String respuesta;
    private boolean estadoRespuesta;
    private boolean estadoRegistro;

    @ManyToOne
    @JoinColumn(name = "codigo_trivia_detalle")
    @JsonIgnore
    private TriviaDetalle triviaDetalle;

    public Long getCodigoDetalleRespuesta() {
        return codigoDetalleRespuesta;
    }

    public void setCodigoDetalleRespuesta(Long codigoDetalleRespuesta) {
        this.codigoDetalleRespuesta = codigoDetalleRespuesta;
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

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
