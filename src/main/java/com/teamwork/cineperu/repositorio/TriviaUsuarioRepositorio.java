package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.Trivia;
import com.teamwork.cineperu.entidad.TriviaUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TriviaUsuarioRepositorio extends CrudRepository<TriviaUsuario, Long> {

    @Query("SELECT t FROM TriviaUsuario t WHERE t.usuario.codigoUsuario = :codigoUsuario AND t.trivia.codigoTrivia = :codigoTrivia")
    TriviaUsuario buscarPorTriviaYUsuario(@Param("codigoUsuario") Long codigoUsuario, @Param("codigoTrivia") Long codigoTrivia);

}
