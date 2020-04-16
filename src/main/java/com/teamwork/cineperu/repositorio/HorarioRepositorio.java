package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.Horario;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HorarioRepositorio extends CrudRepository<Horario, Long> {

	@Query("SELECT p FROM Horario p WHERE p.pelicula.codigoPelicula=:codigoPelicula")
    List<Horario> buscarPorPelicula(@Param("codigoPelicula") long codigoPelicula);
}
