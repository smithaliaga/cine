package com.teamwork.cineperu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamwork.cineperu.bean.BeanUsuarioTokenFirebaseDTO;
import com.teamwork.cineperu.negocio.UsuarioTokenNegocio;

@Controller
@RequestMapping("/usuarioToken")
public class UsuarioTokenController {

	@Autowired
	private UsuarioTokenNegocio usuarioTokenNegocio;

	@GetMapping(value = "/all")
	public String mostrarTodo(Model model) {
		model.addAttribute("usuarioToken", usuarioTokenNegocio.obtenerUsuariosActivos());
		return "listarClientes";
	}

	@PostMapping(value = "/enviar")
	public String enviarMensaje(@ModelAttribute BeanUsuarioTokenFirebaseDTO usuarioToken, String titulo, String mensaje, Model model) {
		usuarioTokenNegocio.enviarMensaje(usuarioToken.getClientes(), titulo, mensaje);
		return "redirect:/usuarioToken/all";
	}
}
