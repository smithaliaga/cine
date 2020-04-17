package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.Butaca;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ButacaRepositorio extends CrudRepository<Butaca, Long> {

	@Query("SELECT p FROM Butaca p WHERE p.sala.codigoSala =:codigoSala")
    List<Butaca> buscarPorSala(@Param("codigoSala") long codigoSala);
}
