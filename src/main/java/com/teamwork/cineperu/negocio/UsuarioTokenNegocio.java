package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.Util.UtilHttp;
import com.teamwork.cineperu.Util.UtilObjectMapper;
import com.teamwork.cineperu.bean.BeanFirebaseBodyRequest;
import com.teamwork.cineperu.bean.BeanFirebaseRequest;
import com.teamwork.cineperu.bean.BeanUsuarioTokenFirebase;
import com.teamwork.cineperu.bean.BeanUsuarioTokenFirebaseDTO;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.repositorio.UsuarioTokenRepositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class UsuarioTokenNegocio {

	@Autowired
	private UsuarioTokenRepositorio usuarioTokenRepositorio;

	public UsuarioToken obtenerUsuarioToken(String token) {
		UsuarioToken usuarioToken = null;
		try {
			usuarioToken = usuarioTokenRepositorio.obtenerUsuarioPorToken(token);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return usuarioToken;
	}

	public BeanUsuarioTokenFirebaseDTO obtenerUsuariosActivos() {
		BeanUsuarioTokenFirebaseDTO beanUsuarioTokenFirebaseDTO = new BeanUsuarioTokenFirebaseDTO();
		List<BeanUsuarioTokenFirebase> clientes = new ArrayList<>();
		List<UsuarioToken> listaUsuarios = usuarioTokenRepositorio.obtenerUsuariosActivos();
		for (UsuarioToken usuarioToken : listaUsuarios) {
			BeanUsuarioTokenFirebase beanUsuarioTokenFirebase = new BeanUsuarioTokenFirebase();
			beanUsuarioTokenFirebase.setTokenFirebase(usuarioToken.getTokenFirebase());
			beanUsuarioTokenFirebase.setNombrePersona(usuarioToken.getUsuario().getPersona().getApellidos() + ", "
					+ usuarioToken.getUsuario().getPersona().getNombres());
			clientes.add(beanUsuarioTokenFirebase);
		}
		beanUsuarioTokenFirebaseDTO.setClientes(clientes);
		return beanUsuarioTokenFirebaseDTO;
	}

	public void enviarMensaje(List<BeanUsuarioTokenFirebase> clientes, String titulo, String mensaje) {
		try {
			BeanFirebaseRequest beanFirebaseRequest = new BeanFirebaseRequest();
			beanFirebaseRequest.setRegistration_ids(new ArrayList<>());
			for (BeanUsuarioTokenFirebase cliente : clientes) {
				if (cliente.isSeleccionado() != null && cliente.isSeleccionado()) {
					beanFirebaseRequest.getRegistration_ids().add(cliente.getTokenFirebase());
				}
			}
			beanFirebaseRequest.setPriority("high");
			BeanFirebaseBodyRequest beanFirebaseBodyRequest = new BeanFirebaseBodyRequest();
			beanFirebaseBodyRequest.setTitle(titulo);
			beanFirebaseBodyRequest.setDetail(mensaje);
			beanFirebaseRequest.setData(beanFirebaseBodyRequest);

			HashMap<String, String> parametersHeader = new HashMap<>();
			parametersHeader.put("Authorization",
					"key=AIzaSyBMr8SNVh3fjoBqqK98t7jJkGCVKgg_mOA");
			String parameters = UtilObjectMapper.getObjectMapper().writeValueAsString(beanFirebaseRequest);
			String respuesta = UtilHttp.sendMessage("https://fcm.googleapis.com/fcm/send", HttpMethod.POST.name(),
					parameters, "application/json; utf-8", parametersHeader, 30000);
			System.out.println("respuesta: " + respuesta);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
