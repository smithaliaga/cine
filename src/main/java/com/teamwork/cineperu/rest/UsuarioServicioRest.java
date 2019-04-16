package com.teamwork.cineperu.rest;

import com.teamwork.cineperu.entidad.request.*;
import com.teamwork.cineperu.entidad.response.*;
import com.teamwork.cineperu.negocio.PeliculaNegocio;
import com.teamwork.cineperu.negocio.PersonaUsuarioNegocio;
import com.teamwork.cineperu.negocio.TriviaNegocio;
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

    @PostMapping("/WS_RegisterUser")
    public EntityWSBase WS_RegisterUser(@RequestBody RegisterUserRequest registerUserRequest){
        return personaUsuarioNegocio.registrarPersonaUsuario(registerUserRequest);
    }

    @PostMapping("/WS_UserAuthenticate")
    public UserAuthenticateResponse WS_UserAuthenticate(@RequestBody UserAuthenticateRequest userAuthenticateRequest){
        return personaUsuarioNegocio.autenticarUsuario(userAuthenticateRequest);
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
}
