package com.mentoria.lojavirtual.LojaVirtualJdev.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.Usuario;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.UsuarioRepository;

@Service
public class TarefaAutomatizadaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	//@Scheduled(initialDelay = 2000, fixedDelay = 86400000) /*Roda a cada 24 horas*/
	@Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo") /* vai rodar todo dia às 11 horas da manhã */
	public void notificaUserTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();
		
		for(Usuario usuario : usuarios) {
			
			StringBuilder msg = new StringBuilder();
			msg.append("Olá, ").append(usuario.getPessoa().getNome_pessoa()).append("<br/");
			msg.append("Está na hora de trocar sua senha, já passou 90 dias de validade!").append("<br/>");
			msg.append("Troque sua senha da Loja Virtual do Alex - JDEV treinamentos.");
			
			serviceSendEmail.enviarEmailHtml("Troca de senha", msg.toString(), usuario.getLogin());
			
			Thread.sleep(3000);
		}
		
		
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
}
