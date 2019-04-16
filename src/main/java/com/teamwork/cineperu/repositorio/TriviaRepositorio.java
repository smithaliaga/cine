package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.Trivia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TriviaRepositorio extends CrudRepository<Trivia, Long> {

    @Query("SELECT t FROM Trivia t WHERE :fecha BETWEEN t.fechaInicio AND t.fechaFin AND t.estadoRegistro = 1")
    Trivia buscarTrivia(@Param("fecha") Date fecha);

}
