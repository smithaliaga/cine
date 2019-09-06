package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.bean.BeanTriviaPregunta;
import com.teamwork.cineperu.bean.BeanTriviaRespuesta;
import com.teamwork.cineperu.entidad.*;
import com.teamwork.cineperu.entidad.request.GetTriviaUsuarioRequest;
import com.teamwork.cineperu.entidad.request.RegisterIntentTriviaRequest;
import com.teamwork.cineperu.entidad.request.UserTokenRequest;
import com.teamwork.cineperu.entidad.response.EntityWSBase;
import com.teamwork.cineperu.entidad.response.GetListTriviaResponse;
import com.teamwork.cineperu.entidad.response.RegisterIntentTriviaResponse;
import com.teamwork.cineperu.repositorio.TriviaDetalleRepositorio;
import com.teamwork.cineperu.repositorio.TriviaDetalleRespuestaRepositorio;
import com.teamwork.cineperu.repositorio.TriviaRepositorio;
import com.teamwork.cineperu.repositorio.TriviaUsuarioRepositorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TriviaNegocio {

    @Autowired
    private TriviaRepositorio triviaRepositorio;
    @Autowired
    private TriviaDetalleRepositorio triviaDetalleRepositorio;
    @Autowired
    private TriviaDetalleRespuestaRepositorio triviaDetalleRespuestaRepositorio;
    @Autowired
    private UsuarioTokenNegocio usuarioTokenNegocio;
    @Autowired
    private TriviaUsuarioRepositorio triviaUsuarioRepositorio;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GetListTriviaResponse listarTrivia(UserTokenRequest userTokenRequest){
        GetListTriviaResponse getListTriviaResponse = new GetListTriviaResponse();
        getListTriviaResponse.setErrorCode(0);
        getListTriviaResponse.setErrorMessage("");
        try{
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(userTokenRequest.getToken());
            if (usuarioToken == null){
                getListTriviaResponse.setErrorCode(100);
                getListTriviaResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return getListTriviaResponse;
            }

            SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
            Trivia trivia = triviaRepositorio.buscarTrivia(sdp.parse(sdp.format(new Date())));
            System.out.println("trivia:" + trivia);
            logger.info("trivia:" + trivia);
            if (trivia != null){

                List<TriviaDetalle> listTriviaDetalle = triviaDetalleRepositorio.buscarPreguntas(trivia.getCodigoTrivia());
                System.out.println("listTriviaDetalle.size:" + listTriviaDetalle.size());
                List<BeanTriviaPregunta> preguntas = new ArrayList<>();

                BeanTriviaPregunta beanTriviaPregunta = null;
                for(TriviaDetalle itemPregunta : listTriviaDetalle){
                    beanTriviaPregunta = new BeanTriviaPregunta();
                    beanTriviaPregunta.setCodigoTriviaPregunta(itemPregunta.getCodigoDetalleTrivia());
                    beanTriviaPregunta.setPregunta(itemPregunta.getPregunta());

                    List<TriviaDetalleRespuesta> listTriviaDetalleRespuesta =
                            triviaDetalleRespuestaRepositorio.buscarPreguntas(itemPregunta.getCodigoDetalleTrivia());
                    System.out.println("listTriviaDetalleRespuesta.size:" + listTriviaDetalleRespuesta.size());
                    logger.info("listTriviaDetalleRespuesta.size:" + listTriviaDetalleRespuesta.size());

                    List<BeanTriviaRespuesta> respuestas = new ArrayList<>();

                    BeanTriviaRespuesta beanTriviaRespuesta = null;
                    for(TriviaDetalleRespuesta itemRespuesta : listTriviaDetalleRespuesta){
                        beanTriviaRespuesta = new BeanTriviaRespuesta();
                        beanTriviaRespuesta.setCodigoTriviaRespuesta(itemRespuesta.getCodigoDetalleRespuesta());
                        beanTriviaRespuesta.setEstadoRespuesta(itemRespuesta.isEstadoRespuesta());
                        beanTriviaRespuesta.setRespuesta(itemRespuesta.getRespuesta());
                        respuestas.add(beanTriviaRespuesta);
                    }

                    beanTriviaPregunta.setRespuestas(respuestas);
                    preguntas.add(beanTriviaPregunta);
                }
                getListTriviaResponse.setCodigoTrivia(trivia.getCodigoTrivia());
                getListTriviaResponse.setPreguntas(preguntas);
                System.out.println("getCodigoTrivia:" + getListTriviaResponse.getCodigoTrivia());
                System.out.println("getPreguntas.size:" + getListTriviaResponse.getPreguntas().size());
                logger.info("getCodigoTrivia:" + getListTriviaResponse.getCodigoTrivia());
                logger.info("getPreguntas.size:" + getListTriviaResponse.getPreguntas().size());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            getListTriviaResponse.setErrorCode(1);
            getListTriviaResponse.setErrorMessage("Error en procesos");
        }
        return getListTriviaResponse;
    }

    public RegisterIntentTriviaResponse registrarIntentoTrivia(RegisterIntentTriviaRequest registerIntentTriviaRequest) {
        RegisterIntentTriviaResponse registerIntentTriviaResponse = new RegisterIntentTriviaResponse();
        registerIntentTriviaResponse.setErrorCode(0);
        registerIntentTriviaResponse.setErrorMessage("");
        try{
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(registerIntentTriviaRequest.getToken());
            if (usuarioToken == null){
                registerIntentTriviaResponse.setErrorCode(100);
                registerIntentTriviaResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return registerIntentTriviaResponse;
            }

            Trivia trivia = new Trivia();
            trivia.setCodigoTrivia(registerIntentTriviaRequest.getCodigoTrivia());

            TriviaUsuario triviaUsuario = new TriviaUsuario();
            triviaUsuario.setTrivia(trivia);
            triviaUsuario.setUsuario(usuarioToken.getUsuario());
            triviaUsuario.setEstadoCobro(false);
            triviaUsuario.setEstadoRespuesta(registerIntentTriviaRequest.estadoRespuesta);

            triviaUsuario = triviaUsuarioRepositorio.save(triviaUsuario);
            if (triviaUsuario != null){
                registerIntentTriviaResponse.setCodigoTriviaUsuario(triviaUsuario.getCodigoTriviaUsuario());
                registerIntentTriviaResponse.setEstadoRespuesta(triviaUsuario.isEstadoRespuesta());
                registerIntentTriviaResponse.setEstadoCobro(triviaUsuario.isEstadoCobro());
            }else{
                registerIntentTriviaResponse.setErrorCode(7);
                registerIntentTriviaResponse.setErrorMessage("No se pudo registrar el intento de trivia del usuario");
                return registerIntentTriviaResponse;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            registerIntentTriviaResponse.setErrorCode(1);
            registerIntentTriviaResponse.setErrorMessage("Error en procesos");
        }
        return registerIntentTriviaResponse;
    }

    public RegisterIntentTriviaResponse obtenerTriviaUsuario(GetTriviaUsuarioRequest getTriviaUsuarioRequest) {
        RegisterIntentTriviaResponse registerIntentTriviaResponse = new RegisterIntentTriviaResponse();
        registerIntentTriviaResponse.setErrorCode(0);
        registerIntentTriviaResponse.setErrorMessage("");
        try{
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(getTriviaUsuarioRequest.getToken());
            if (usuarioToken == null){
                registerIntentTriviaResponse.setErrorCode(100);
                registerIntentTriviaResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return registerIntentTriviaResponse;
            }

            TriviaUsuario triviaUsuario = triviaUsuarioRepositorio.buscarPorTriviaYUsuario(
                    usuarioToken.getUsuario().getCodigoUsuario(),getTriviaUsuarioRequest.getCodigoTrivia());
            if (triviaUsuario != null){
                registerIntentTriviaResponse.setCodigoTriviaUsuario(triviaUsuario.getCodigoTriviaUsuario());
                registerIntentTriviaResponse.setEstadoRespuesta(triviaUsuario.isEstadoRespuesta());
                registerIntentTriviaResponse.setEstadoCobro(triviaUsuario.isEstadoCobro());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            registerIntentTriviaResponse.setErrorCode(1);
            registerIntentTriviaResponse.setErrorMessage("Error en procesos");
        }
        return registerIntentTriviaResponse;
    }
}
