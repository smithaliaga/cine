package com.teamwork.cineperu.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Trivia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_trivia")
    private Long codigoTrivia;

    private Date fechaInicio;
    private Date fechaFin;
    private boolean estadoRegistro;

    @ManyToOne
    @JoinColumn(name = "codigo_canjeo")
    @JsonIgnore
    private Canjeo canjeo;

    public Long getCodigoTrivia() {
        return codigoTrivia;
    }

    public void setCodigoTrivia(Long codigoTrivia) {
        this.codigoTrivia = codigoTrivia;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Canjeo getCanjeo() {
        return canjeo;
    }

    public void setCanjeo(Canjeo canjeo) {
        this.canjeo = canjeo;
    }
}
