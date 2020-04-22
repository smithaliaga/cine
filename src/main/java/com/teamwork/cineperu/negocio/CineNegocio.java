package com.teamwork.cineperu.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwork.cineperu.entidad.Cine;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.UserTokenRequest;
import com.teamwork.cineperu.entidad.response.GetListCineResponse;
import com.teamwork.cineperu.repositorio.CineRepositorio;

@Service
public class CineNegocio {

	@Autowired
	private CineRepositorio cineRepositorio;
	@Autowired
	private UsuarioTokenNegocio usuarioTokenNegocio;

	public GetListCineResponse listaPelicula(UserTokenRequest userTokenRequest){
		GetListCineResponse getListCineResponse = new GetListCineResponse();
		getListCineResponse.setErrorCode(0);
        getListCineResponse.setErrorMessage("");
        try{
        	/*
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(userTokenRequest.getToken());
            if (usuarioToken == null){
            	getListCineResponse.setErrorCode(100);
                getListCineResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return getListCineResponse;
            }
            */

            List<Cine> listaPelicula = (List<Cine>) cineRepositorio.findAll();
            getListCineResponse.setLista(listaPelicula);
        }catch (Exception ex){
        	getListCineResponse.setErrorCode(0);
        	getListCineResponse.setErrorMessage("Error en procesos");
            ex.printStackTrace();
        }
        return getListCineResponse;
    }
}
