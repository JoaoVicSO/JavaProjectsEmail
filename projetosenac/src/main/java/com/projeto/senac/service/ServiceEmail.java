package com.projeto.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServiceEmail {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void SendEmail(String remetente, String emailPraQuem, String assuntoEmail, String corpoEmail) {
		
		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setFrom(remetente);
		mensagem.setTo(emailPraQuem);
		mensagem.setText(corpoEmail);
		mensagem.setSubject(assuntoEmail);
		javaMailSender.send(mensagem);
		
	} // Fim SendEmail
	

}