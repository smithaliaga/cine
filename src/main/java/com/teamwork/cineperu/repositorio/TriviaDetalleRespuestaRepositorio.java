package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.TriviaDetalle;
import com.teamwork.cineperu.entidad.TriviaDetalleRespuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TriviaDetalleRespuestaRepositorio extends CrudRepository<TriviaDetalleRespuesta, Long> {

    @Query("SELECT t FROM TriviaDetalleRespuesta t WHERE t.triviaDetalle.codigoDetalleTrivia = :codigoDetalleTrivia AND t.estadoRegistro = 1")
    List<TriviaDetalleRespuesta> buscarPreguntas(@Param("codigoDetalleTrivia") Long codigoDetalleTrivia);

}
