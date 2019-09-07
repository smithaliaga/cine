package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.Util.EncriptarClave;
import com.teamwork.cineperu.entidad.Persona;
import com.teamwork.cineperu.entidad.Usuario;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.RegisterUserRequest;
import com.teamwork.cineperu.entidad.request.UserAuthenticateRequest;
import com.teamwork.cineperu.entidad.request.UserTokenRequest;
import com.teamwork.cineperu.entidad.request.UserUpdateInformationRequest;
import com.teamwork.cineperu.entidad.response.EntityWSBase;
import com.teamwork.cineperu.entidad.response.PersonaResponse;
import com.teamwork.cineperu.entidad.response.UserAuthenticateResponse;
import com.teamwork.cineperu.entidad.response.UserGetInformationResponse;
import com.teamwork.cineperu.repositorio.PersonaRepositorio;
import com.teamwork.cineperu.repositorio.UsuarioRepositorio;
import com.teamwork.cineperu.repositorio.UsuarioTokenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class PersonaUsuarioNegocio {

	@Autowired
	private PersonaRepositorio personaRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private UsuarioTokenRepositorio usuarioTokenRepositorio;
	@Autowired
	private UsuarioTokenNegocio usuarioTokenNegocio;

	public EntityWSBase registrarPersonaUsuario(RegisterUserRequest registerUserRequest) {
		EntityWSBase entityWSBase = new EntityWSBase();
		entityWSBase.setErrorCode(0);
		entityWSBase.setErrorMessage("Se registro el usuario satisfactoriamente");
		try {

			Persona persona = personaRepositorio.buscarPorDNI(registerUserRequest.getDni());
			if (persona != null) {
				entityWSBase.setErrorCode(3);
				entityWSBase.setErrorMessage("Ya existe una persona registrada con el mismo DNI");
				return entityWSBase;
			}

			Usuario usuario = usuarioRepositorio.buscarUsuarioPorCredencial(registerUserRequest.getUsuario(),
					EncriptarClave.encriptar(registerUserRequest.getClave()));
			if (usuario != null) {
				entityWSBase.setErrorCode(4);
				entityWSBase.setErrorMessage("Ya existe un usuario con las credenciales ingresadas");
				return entityWSBase;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			persona = new Persona();
			persona.setDni(registerUserRequest.getDni());
			persona.setNombres(registerUserRequest.getNombres());
			persona.setApellidos(registerUserRequest.getApellidos());
			persona.setDireccion(registerUserRequest.getDireccion());
			persona.setEmail(registerUserRequest.getEmail());
			persona.setGenero(registerUserRequest.getGenero().charAt(0));
			persona.setFechaNacimiento(sdf.parse(registerUserRequest.getFechaNacimiento()));

			persona = personaRepositorio.save(persona);

			usuario = new Usuario();
			usuario.setUsuario(registerUserRequest.getUsuario());
			usuario.setClave(EncriptarClave.encriptar(registerUserRequest.getClave()));
			usuario.setEstadoRegistro(true);
			usuario.setPersona(persona);

			usuario = usuarioRepositorio.save(usuario);

		} catch (Exception ex) {
			ex.printStackTrace();
			entityWSBase.setErrorCode(1);
			entityWSBase.setErrorMessage("Error en procesos");
		}
		return entityWSBase;
	}

	public UserAuthenticateResponse autenticarUsuario(UserAuthenticateRequest userAuthenticateRequest) {
		UserAuthenticateResponse userAuthenticateResponse = new UserAuthenticateResponse();
		userAuthenticateResponse.setErrorCode(0);
		userAuthenticateResponse.setErrorMessage("Credenciales de usuario correctas");
		try {
			Usuario usuario = usuarioRepositorio.buscarUsuarioPorCredencial(userAuthenticateRequest.getUsuario(),
					EncriptarClave.encriptar(userAuthenticateRequest.getClave()));
			if (usuario != null) {
				usuarioTokenRepositorio.descativarTokenActivo(usuario.getCodigoUsuario());

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");

				UsuarioToken usuarioToken = new UsuarioToken();
				usuarioToken.setToken(UUID.randomUUID() + sdf.format(new Date()));
				usuarioToken.setEstadoRegistro(true);
				usuarioToken.setUsuario(usuario);

				usuarioTokenRepositorio.save(usuarioToken);

				userAuthenticateResponse.setToken(usuarioToken.getToken());

			} else {
				userAuthenticateResponse.setErrorCode(2);
				userAuthenticateResponse.setErrorMessage("Las credenciales ingresadas son inválidas");
			}

		} catch (Exception ex) {
			userAuthenticateResponse.setErrorCode(1);
			userAuthenticateResponse.setErrorMessage("Error en procesos");
			ex.printStackTrace();
		}
		return userAuthenticateResponse;
	}

	public UserGetInformationResponse obtenerInformacionUsuario(UserTokenRequest userTokenRequest) {
		UserGetInformationResponse userGetInformationResponse = new UserGetInformationResponse();
		userGetInformationResponse.setErrorCode(0);
		userGetInformationResponse.setErrorMessage("Se obtuvo información del usuario satisfactoriamente");
		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(userTokenRequest.getToken());
			if (usuarioToken == null) {
				userGetInformationResponse.setErrorCode(100);
				userGetInformationResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
				return userGetInformationResponse;
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Persona persona = usuarioToken.getUsuario().getPersona();
				PersonaResponse personaResponse = new PersonaResponse();
				personaResponse.setApellidos(persona.getApellidos());
				personaResponse.setCodigoPersona(persona.getCodigoPersona());
				personaResponse.setDireccion(persona.getDireccion());
				personaResponse.setDni(persona.getDni());
				personaResponse.setEmail(persona.getEmail());
				personaResponse.setFechaNacimiento(sdf.format(persona.getFechaNacimiento()));
				personaResponse.setGenero(persona.getGenero().toString());
				personaResponse.setNombres(persona.getNombres());
				userGetInformationResponse.setPersona(personaResponse);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			userGetInformationResponse.setErrorCode(1);
			userGetInformationResponse.setErrorMessage("Error en procesos");
		}
		return userGetInformationResponse;
	}

	public EntityWSBase actualizarInformacionUsuario(UserUpdateInformationRequest userUpdateInformationRequest) {
		UserGetInformationResponse userGetInformationResponse = new UserGetInformationResponse();
		userGetInformationResponse.setErrorCode(0);
		userGetInformationResponse.setErrorMessage("Se actualizo la información del usuario satisfactoriamente");
		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio
					.obtenerUsuarioToken(userUpdateInformationRequest.getToken());
			if (usuarioToken == null) {
				userGetInformationResponse.setErrorCode(100);
				userGetInformationResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
				return userGetInformationResponse;
			} else {
				Persona persona = usuarioToken.getUsuario().getPersona();
				persona.setDireccion(userUpdateInformationRequest.getDireccion());
				persona.setEmail(userUpdateInformationRequest.getEmail());
				personaRepositorio.save(persona);

				if (!userUpdateInformationRequest.getClave().isEmpty()) {
					Usuario usuario = usuarioToken.getUsuario();
					usuario.setClave(EncriptarClave.encriptar(userUpdateInformationRequest.getClave()));
					usuarioRepositorio.save(usuario);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			userGetInformationResponse.setErrorCode(1);
			userGetInformationResponse.setErrorMessage("Error en procesos");
		}
		return userGetInformationResponse;
	}
}
