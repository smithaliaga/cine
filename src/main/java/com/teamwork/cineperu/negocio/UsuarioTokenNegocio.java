package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.repositorio.UsuarioTokenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioTokenNegocio {

    @Autowired
    private UsuarioTokenRepositorio usuarioTokenRepositorio;

    public UsuarioToken obtenerUsuarioToken(String token){
        UsuarioToken usuarioToken = null;
        try{
            usuarioToken = usuarioTokenRepositorio.obtenerUsuarioPorToken(token);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return usuarioToken;
    }
}
