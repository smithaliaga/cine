package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonaRepositorio extends CrudRepository<Persona,Long> {

    @Query("SELECT p FROM Persona p WHERE p.dni=:dni")
    Persona buscarPorDNI(@Param("dni") String dni);
}
