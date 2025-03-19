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
	
	
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoajuridica) {
		
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
			
			usuarioRepository.insereAcessoUserPj(usuarioPj.getId_usuario());
			
		}
		
		return pessoajuridica;
	}


	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
		
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
