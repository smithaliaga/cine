package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepositorio extends CrudRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario and u.clave = :clave and u.estadoRegistro = 1")
    Usuario buscarUsuarioPorCredencial(@Param("usuario") String usuario, @Param("clave") String clave);

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario and u.estadoRegistro = 1")
    Usuario buscarUsuario(@Param("usuario") String usuario);
}
