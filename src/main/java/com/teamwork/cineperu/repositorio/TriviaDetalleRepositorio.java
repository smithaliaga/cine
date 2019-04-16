package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.Trivia;
import com.teamwork.cineperu.entidad.TriviaDetalle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TriviaDetalleRepositorio extends CrudRepository<TriviaDetalle, Long> {

    @Query("SELECT t FROM TriviaDetalle t WHERE t.trivia.codigoTrivia = :codigoTrivia AND t.estadoRegistro = 1")
    List<TriviaDetalle> buscarPreguntas(@Param("codigoTrivia") Long codigoTrivia);

}
