package com.teamwork.cineperu.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwork.cineperu.entidad.Entrada;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.*;
import com.teamwork.cineperu.entidad.response.*;
import com.teamwork.cineperu.jms.JmsProducer;
import com.teamwork.cineperu.negocio.CineNegocio;
import com.teamwork.cineperu.negocio.PeliculaNegocio;
import com.teamwork.cineperu.negocio.PersonaUsuarioNegocio;
import com.teamwork.cineperu.negocio.TriviaNegocio;
import com.teamwork.cineperu.negocio.UsuarioTokenNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	private CineNegocio cineNegocio;
	@Autowired
	private JmsProducer jmsProducer;

	@PostMapping("/WS_RegisterUser")
	public EntityWSBase WS_RegisterUser(@RequestBody RegisterUserRequest registerUserRequest) {
		return personaUsuarioNegocio.registrarPersonaUsuario(registerUserRequest);
	}

	@PostMapping("/WS_UserAuthenticate")
	public UserAuthenticateResponse WS_UserAuthenticate(@RequestBody UserAuthenticateRequest userAuthenticateRequest) {
		return personaUsuarioNegocio.autenticarUsuario(userAuthenticateRequest);
	}

	@PostMapping("/WS_UserGetInformation")
	public UserGetInformationResponse WS_UserGetInformation(@RequestBody UserTokenRequest userTokenRequest) {
		return personaUsuarioNegocio.obtenerInformacionUsuario(userTokenRequest);
	}

	@PostMapping("/WS_UserUpdateInformation")
	public EntityWSBase WS_UserUpdateInformation(
			@RequestBody UserUpdateInformationRequest userUpdateInformationRequest) {
		return personaUsuarioNegocio.actualizarInformacionUsuario(userUpdateInformationRequest);
	}

	@PostMapping("/WS_GetListMovie")
	public GetListMovieResponse WS_GetListMovie(@RequestBody UserTokenRequest userTokenRequest) {
		return peliculaNegocio.listaPelicula(userTokenRequest);
	}

	@PostMapping("/WS_GetListTrivia")
	public GetListTriviaResponse WS_GetListTrivia(@RequestBody UserTokenRequest userTokenRequest) {
		return triviaNegocio.listarTrivia(userTokenRequest);
	}

	@PostMapping("/WS_RegisterIntentTrivia")
	public RegisterIntentTriviaResponse WS_RegisterIntentTrivia(
			@RequestBody RegisterIntentTriviaRequest registerIntentTriviaRequest) {
		return triviaNegocio.registrarIntentoTrivia(registerIntentTriviaRequest);
	}

	@PostMapping("/WS_GetTriviaUser")
	public RegisterIntentTriviaResponse WS_GetTriviaUser(@RequestBody GetTriviaUsuarioRequest getTriviaUsuarioRequest) {
		return triviaNegocio.obtenerTriviaUsuario(getTriviaUsuarioRequest);
	}
	
	@PostMapping("/WS_GetListHorario")
	public GetListHorarioResponse WS_GetListHorario(@RequestBody GetListHorarioRequest getListHorarioRequest) {
		return peliculaNegocio.listaHorario(getListHorarioRequest);
	}
	
	@PostMapping("/WS_GetListButaca")
	public GetListButacaResponse WS_GetListButaca(@RequestBody GetListButacaRequest getListButacaRequest) {
		return peliculaNegocio.listaButaca(getListButacaRequest);
	}
	
	@PostMapping("/WS_GetMontoPago")
	public GetMontoPagoResponse WS_GetMontoPago(@RequestBody GetMontoPagoRequest getMontoPagoRequest) {
		return peliculaNegocio.consultarMontoPago(getMontoPagoRequest);
	}
	
	@PostMapping("/WS_RealizarPago")
	public RealizarPagoResponse WS_RealizarPago(@RequestBody RealizarPagoRequest realizarPagoRequest) {
		return peliculaNegocio.registrarPago(realizarPagoRequest);
	}

	@PostMapping("/WS_SendTransactionBuyTicket")
	public EntityWSBase WS_SendTransactionBuyTicket(
			@RequestBody SendTransactionBuyTicketRequest sendTransactionBuyTicketRequest) {
		EntityWSBase entityWSBase = new EntityWSBase();
		entityWSBase.setErrorCode(0);
		entityWSBase.setErrorMessage("Solicitud enviada a procesar");

		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio
					.obtenerUsuarioToken(sendTransactionBuyTicketRequest.getToken());
			if (usuarioToken == null) {
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
			// jmsProducer.send(jsonString);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			entityWSBase.setErrorCode(9);
			entityWSBase.setErrorMessage("Error al procesar solicitud de cola");
		}
		return entityWSBase;
	}

	@GetMapping("WS_RecoveryPassword")
	public EntityWSBase recuperarClave(@RequestBody RecoveryPasswordRequest sendMessageRequest) {
		return personaUsuarioNegocio.recuperarClave(sendMessageRequest);
	}
	
	@PostMapping("WS_GetListCine")
	public GetListCineResponse WS_GetListCine(@RequestBody UserTokenRequest userTokenRequest) {
		return cineNegocio.listaPelicula(userTokenRequest);
	}
}
