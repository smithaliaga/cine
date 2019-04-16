package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.entidad.Pelicula;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.UserTokenRequest;
import com.teamwork.cineperu.entidad.response.GetListMovieResponse;
import com.teamwork.cineperu.repositorio.PeliculaRepositorio;
import com.teamwork.cineperu.repositorio.UsuarioTokenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaNegocio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private UsuarioTokenRepositorio usuarioTokenRepositorio;
    @Autowired
    private UsuarioTokenNegocio usuarioTokenNegocio;

    public GetListMovieResponse listaPelicula(UserTokenRequest userTokenRequest){
        GetListMovieResponse getListMovieResponse = new GetListMovieResponse();
        getListMovieResponse.setErrorCode(0);
        getListMovieResponse.setErrorMessage("");
        try{
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(userTokenRequest.getToken());
            if (usuarioToken == null){
                getListMovieResponse.setErrorCode(100);
                getListMovieResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return getListMovieResponse;
            }

            List<Pelicula> listaPelicula = (List<Pelicula>) peliculaRepositorio.findAll();
            getListMovieResponse.setLista(listaPelicula);
        }catch (Exception ex){
            getListMovieResponse.setErrorCode(0);
            getListMovieResponse.setErrorMessage("Error en procesos");
            ex.printStackTrace();
        }
        return getListMovieResponse;
    }
}
