package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.bean.BeanButaca;
import com.teamwork.cineperu.bean.BeanHorario;
import com.teamwork.cineperu.entidad.Butaca;
import com.teamwork.cineperu.entidad.Horario;
import com.teamwork.cineperu.entidad.Pelicula;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.GetListButacaRequest;
import com.teamwork.cineperu.entidad.request.GetListHorarioRequest;
import com.teamwork.cineperu.entidad.request.UserTokenRequest;
import com.teamwork.cineperu.entidad.response.GetListButacaResponse;
import com.teamwork.cineperu.entidad.response.GetListHorarioResponse;
import com.teamwork.cineperu.entidad.response.GetListMovieResponse;
import com.teamwork.cineperu.repositorio.ButacaRepositorio;
import com.teamwork.cineperu.repositorio.HorarioRepositorio;
import com.teamwork.cineperu.repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculaNegocio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private UsuarioTokenNegocio usuarioTokenNegocio;
    @Autowired
    private HorarioRepositorio horarioRepositorio;
    @Autowired
    private ButacaRepositorio butacaRepositorio;

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

            List<BeanHorario> listaBeanHorario = new ArrayList<>();
            List<Horario> listaHorario = (List<Horario>) horarioRepositorio.buscarPorPelicula(getListHorarioRequest.getCodigoCine(), getListHorarioRequest.getCodigoPelicula());
            
            for (Horario horario : listaHorario) {

            	BeanHorario beanHorario = new BeanHorario();
            	beanHorario.setCodigoHorario(horario.getCodigoHorario());
            	beanHorario.setDescripcionHorario(horario.getDescripcion());
            	beanHorario.setCodigoSala(horario.getSala().getCodigoSala());
            	beanHorario.setNombreSala(horario.getSala().getNombre());
            	
            	listaBeanHorario.add(beanHorario);
            	
            }
            
            getListHorarioResponse.setLista(listaBeanHorario);
            
        }catch (Exception ex){
        	getListHorarioResponse.setErrorCode(0);
        	getListHorarioResponse.setErrorMessage("Error en procesos");
            ex.printStackTrace();
        }
        return getListHorarioResponse;
    }
    
    public GetListButacaResponse listaButaca(GetListButacaRequest getListButacaRequest){
    	GetListButacaResponse getListButacaResponse = new GetListButacaResponse();
    	getListButacaResponse.setErrorCode(0);
    	getListButacaResponse.setErrorMessage("");
        try{
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(getListButacaRequest.getToken());
            if (usuarioToken == null){
            	getListButacaResponse.setErrorCode(100);
            	getListButacaResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return getListButacaResponse;
            }

            List<BeanButaca> listaBeanButaca = new ArrayList<>();
            List<Butaca> listaButaca = (List<Butaca>) butacaRepositorio.buscarPorSala(getListButacaRequest.getCodigoSala());
            
            for (Butaca butaca : listaButaca) {
            	
            	BeanButaca beanButaca = new BeanButaca();
            	beanButaca.setCodigoButaca(butaca.getCodigoButaca());
            	beanButaca.setNombreButaca(butaca.getNombre());
            	beanButaca.setFilaButaca(butaca.getFila());
            	beanButaca.setCodigoEstadoButaca(butaca.getEstadoButaca().getCodigoEstadoButaca());
            	beanButaca.setNombreEstadoButaca(butaca.getEstadoButaca().getNombre());
            	
            	listaBeanButaca.add(beanButaca);
            	
            }
            
            getListButacaResponse.setLista(listaBeanButaca);
            
        }catch (Exception ex){
        	getListButacaResponse.setErrorCode(0);
        	getListButacaResponse.setErrorMessage("Error en procesos");
            ex.printStackTrace();
        }
        return getListButacaResponse;
    }
}
