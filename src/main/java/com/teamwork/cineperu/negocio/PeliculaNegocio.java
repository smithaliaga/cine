package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.entidad.Horario;
import com.teamwork.cineperu.entidad.Pelicula;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.GetListHorarioRequest;
import com.teamwork.cineperu.entidad.request.UserTokenRequest;
import com.teamwork.cineperu.entidad.response.GetListHorarioResponse;
import com.teamwork.cineperu.entidad.response.GetListMovieResponse;
import com.teamwork.cineperu.repositorio.HorarioRepositorio;
import com.teamwork.cineperu.repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaNegocio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private UsuarioTokenNegocio usuarioTokenNegocio;
    @Autowired
    private HorarioRepositorio horarioRepositorio;

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
    
    public GetListHorarioResponse listaHorario(GetListHorarioRequest getListHorarioRequest){
    	GetListHorarioResponse getListHorarioResponse = new GetListHorarioResponse();
    	getListHorarioResponse.setErrorCode(0);
    	getListHorarioResponse.setErrorMessage("");
        try{
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(getListHorarioRequest.getToken());
            if (usuarioToken == null){
            	getListHorarioResponse.setErrorCode(100);
            	getListHorarioResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return getListHorarioResponse;
            }

            List<Horario> listaPelicula = (List<Horario>) horarioRepositorio.buscarPorPelicula(getListHorarioRequest.getCodigoPelicula());
            getListHorarioResponse.setLista(listaPelicula);
            
        }catch (Exception ex){
        	getListHorarioResponse.setErrorCode(0);
        	getListHorarioResponse.setErrorMessage("Error en procesos");
            ex.printStackTrace();
        }
        return getListHorarioResponse;
    }
}
