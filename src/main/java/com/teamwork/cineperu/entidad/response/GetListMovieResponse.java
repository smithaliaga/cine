package com.teamwork.cineperu.entidad.response;

import com.teamwork.cineperu.entidad.Pelicula;

import java.util.List;

public class GetListMovieResponse extends EntityWSBase{

    private List<Pelicula> lista;

    public List<Pelicula> getLista() {
        return lista;
    }

    public void setLista(List<Pelicula> lista) {
        this.lista = lista;
    }
}
