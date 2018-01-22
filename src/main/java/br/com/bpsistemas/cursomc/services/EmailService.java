package br.com.bpsistemas.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.bpsistemas.cursomc.domain.Cliente;
import br.com.bpsistemas.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}