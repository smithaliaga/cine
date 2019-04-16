package com.teamwork.cineperu.entidad;

import javax.persistence.*;

@Entity
public class Canjeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_canjeo")
    private Long codigoCanjeo;

    private String nombre;
    private boolean estadoRegistro;

    public Long getCodigoCanjeo() {
        return codigoCanjeo;
    }

    public void setCodigoCanjeo(Long codigoCanjeo) {
        this.codigoCanjeo = codigoCanjeo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
