package com.teamwork.cineperu.entidad;

public class Entrada {

    private Long codigoEntrada;
    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaExpiracion;
    private String codigoSeguridad;
    private double monto;

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Long getCodigoEntrada() {
        return codigoEntrada;
    }

    public void setCodigoEntrada(Long codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }
}
