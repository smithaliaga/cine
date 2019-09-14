package com.teamwork.cineperu.negocio;

import java.util.concurrent.ThreadLocalRandom;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.teamwork.cineperu.entidad.request.SendMessageRequest;

@Service
public class SendMailNegocio {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.fullname}")
	private String fullname;

	public void enviarMensajeRestauracion(SendMessageRequest sendMessageRequest) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int numero = ThreadLocalRandom.current().nextInt(1000, 9000);

					String titulo = "Solicitud de restauración de contraseña";
					String texto = "Su nueva contraseña es: " + numero;

					MimeMessage mailMessage = javaMailSender.createMimeMessage();
					MimeMessageHelper message = new MimeMessageHelper(mailMessage, true, "UTF-8");
					message.setFrom(username, fullname);
					message.setTo(sendMessageRequest.getCorreo());
					message.setSubject(titulo);
					message.setText(texto, true);
					javaMailSender.send(mailMessage);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();
	}
}
