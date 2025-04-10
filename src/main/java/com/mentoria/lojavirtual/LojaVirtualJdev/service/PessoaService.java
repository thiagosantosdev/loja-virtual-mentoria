package com.mentoria.lojavirtual.LojaVirtualJdev.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaJuridica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Usuario;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.PessoaFisicaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.PessoaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.UsuarioRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoajuridica) {
		
		
		for (int i = 0; i< pessoajuridica.getEnderecos().size(); i++) {
			pessoajuridica.getEnderecos().get(i).setPessoa(pessoajuridica);
			pessoajuridica.getEnderecos().get(i).setEmpresa(pessoajuridica);
		}
		
		
		pessoajuridica = pessoaRepository.save(pessoajuridica);
		

		Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoajuridica.getId_pessoa(), pessoajuridica.getEmail());
		
		if(usuarioPj == null) {
			
			
			
			usuarioPj = new Usuario();
			usuarioPj.setDataSenha(Calendar.getInstance().getTime());
			usuarioPj.setPessoa(pessoajuridica);
			usuarioPj.setEmpresa(pessoajuridica);
			usuarioPj.setLogin(pessoajuridica.getEmail());
			
			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			
			usuarioPj.setSenha(senhaCript);
			usuarioPj = usuarioRepository.save(usuarioPj);
			
		
			usuarioRepository.insereAcessoUserPj(usuarioPj.getId_usuario(), "ROLE_ADMIN");
			
			// Envio e-mail login e senha
						StringBuilder mensagemHtml = new StringBuilder();
						mensagemHtml.append("<b>Segue abaixo seus dados de acesso Pessoa Jurídica para a loja virtual:</b><br/><br/>");
						mensagemHtml.append("<b>Login:</b> " + pessoajuridica.getEmail() + "<br/>");
						mensagemHtml.append("<b>Senha:</b> ").append(senha).append("<br/><br/>");
						mensagemHtml.append("<b>Muito obrigado!</b>");
						try {
							serviceSendEmail.enviarEmailHtml(
								"Acesso gerado para Loja Virtual",
								mensagemHtml.toString(),
								pessoajuridica.getEmail()
							);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
		
		return pessoajuridica;
	}


	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
		
		
		
		
		for (int i = 0; i< pessoaFisica.getEnderecos().size(); i++) {
			pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
			pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica);
		}
		
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

	
		Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaFisica.getId_pessoa(), pessoaFisica.getEmail());

		
		if (usuarioPj == null) {
				
				
				usuarioPj = new Usuario();
				usuarioPj.setDataSenha(Calendar.getInstance().getTime());
				usuarioPj.setEmpresa(pessoaFisica.getEmpresa());
				usuarioPj.setPessoa(pessoaFisica);
				usuarioPj.setLogin(pessoaFisica.getEmail());
				
				String senha = "" + Calendar.getInstance().getTimeInMillis();
				String senhaCript = new BCryptPasswordEncoder().encode(senha);
				
				usuarioPj.setSenha(senhaCript);
				
				usuarioPj = usuarioRepository.save(usuarioPj);
				
				usuarioRepository.insereAcessoUser(usuarioPj.getId_usuario());
				
					}
			
			return pessoaFisica;
		}
		
	
	
	
	
	
	
	
	
}
