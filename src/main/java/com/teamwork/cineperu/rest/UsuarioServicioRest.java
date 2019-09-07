package com.teamwork.cineperu.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwork.cineperu.entidad.Entrada;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.*;
import com.teamwork.cineperu.entidad.response.*;
import com.teamwork.cineperu.jms.JmsProducer;
import com.teamwork.cineperu.negocio.PeliculaNegocio;
import com.teamwork.cineperu.negocio.PersonaUsuarioNegocio;
import com.teamwork.cineperu.negocio.TriviaNegocio;
import com.teamwork.cineperu.negocio.UsuarioTokenNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UsuarioServicioRest {

    @Autowired
    private PersonaUsuarioNegocio personaUsuarioNegocio;
    @Autowired
    private PeliculaNegocio peliculaNegocio;
    @Autowired
    private TriviaNegocio triviaNegocio;
    @Autowired
    private UsuarioTokenNegocio usuarioTokenNegocio;
    @Autowired
    private JmsProducer jmsProducer;

    @PostMapping("/WS_RegisterUser")
    public EntityWSBase WS_RegisterUser(@RequestBody RegisterUserRequest registerUserRequest){
        return personaUsuarioNegocio.registrarPersonaUsuario(registerUserRequest);
    }

    @PostMapping("/WS_UserAuthenticate")
    public UserAuthenticateResponse WS_UserAuthenticate(@RequestBody UserAuthenticateRequest userAuthenticateRequest){
        return personaUsuarioNegocio.autenticarUsuario(userAuthenticateRequest);
    }
    
    @PostMapping("/WS_UserGetInformacion")
    public UserGetInformationResponse WS_UserGetInformacion(@RequestBody UserTokenRequest userTokenRequest){
        return personaUsuarioNegocio.obtenerInformacionUsuario(userTokenRequest);
    }

    @PostMapping("/WS_GetListMovie")
    public GetListMovieResponse WS_GetListMovie(@RequestBody UserTokenRequest userTokenRequest){
        return peliculaNegocio.listaPelicula(userTokenRequest);
    }

    @PostMapping("/WS_GetListTrivia")
    public GetListTriviaResponse WS_GetListTrivia(@RequestBody UserTokenRequest userTokenRequest){
        return triviaNegocio.listarTrivia(userTokenRequest);
    }

    @PostMapping("/WS_RegisterIntentTrivia")
    public RegisterIntentTriviaResponse WS_RegisterIntentTrivia(@RequestBody RegisterIntentTriviaRequest registerIntentTriviaRequest){
        return triviaNegocio.registrarIntentoTrivia(registerIntentTriviaRequest);
    }

    @PostMapping("/WS_GetTriviaUser")
    public RegisterIntentTriviaResponse WS_GetTriviaUser(@RequestBody GetTriviaUsuarioRequest getTriviaUsuarioRequest){
        return triviaNegocio.obtenerTriviaUsuario(getTriviaUsuarioRequest);
    }

    @PostMapping("/WS_SendTransactionBuyTicket")
    public EntityWSBase WS_SendTransactionBuyTicket(@RequestBody SendTransactionBuyTicketRequest sendTransactionBuyTicketRequest){
        EntityWSBase entityWSBase = new EntityWSBase();
        entityWSBase.setErrorCode(0);
        entityWSBase.setErrorMessage("Solicitud enviada a procesar");

        try {
            UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(sendTransactionBuyTicketRequest.getToken());
            if (usuarioToken == null){
                entityWSBase.setErrorCode(100);
                entityWSBase.setErrorMessage("Credencial de acceso vencida o incorrecta");
                return entityWSBase;
            }

            ObjectMapper mapper = new ObjectMapper();

            Entrada entrada = new Entrada();
            entrada.setCodigoSeguridad(sendTransactionBuyTicketRequest.getCodigoSeguridad());
            entrada.setFechaExpiracion(sendTransactionBuyTicketRequest.getFechaExpiracion());
            entrada.setNombreTitular(sendTransactionBuyTicketRequest.getNombreTitular());
            entrada.setNumeroTarjeta(sendTransactionBuyTicketRequest.getNumeroTarjeta());
            entrada.setMonto(20.00);

            String jsonString = mapper.writeValueAsString(entrada);
            jmsProducer.enviarRecibir(jsonString);
            //jmsProducer.send(jsonString);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            entityWSBase.setErrorCode(9);
            entityWSBase.setErrorMessage("Error al procesar solicitud de cola");
        }
        return entityWSBase;
    }
}
