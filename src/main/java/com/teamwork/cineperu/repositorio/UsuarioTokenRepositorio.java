package com.teamwork.cineperu.repositorio;

import com.teamwork.cineperu.entidad.UsuarioToken;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioTokenRepositorio extends CrudRepository<UsuarioToken,String> {

    @Query("SELECT u FROM UsuarioToken u WHERE u.token = :token and u.estadoRegistro = 1")
    UsuarioToken obtenerUsuarioPorToken(@Param("token") String token);

    @Transactional
    @Modifying
    @Query("UPDATE UsuarioToken u SET u.estadoRegistro = 0 WHERE u.usuario.codigoUsuario = :codigoUsuario")
    void descativarTokenActivo(@Param("codigoUsuario") Long codigoUsuario);
    
    @Query("SELECT u FROM UsuarioToken u WHERE u.estadoRegistro = 1")
    List<UsuarioToken> obtenerUsuariosActivos();
}
