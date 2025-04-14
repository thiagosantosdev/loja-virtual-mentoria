package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaJuridica;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.PessoaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.PessoaService;

@RestController
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PessoaService pessoaservice;
	
	@ResponseBody
	@PostMapping(value = "/salvarPJ")
	public ResponseEntity<PessoaJuridica> salvarPJ(@RequestBody PessoaJuridica pessoajuridica) throws ExceptionMentoriaJava  {
		
		if(pessoajuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa Jurídica não pode ser NULL");
		}
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeCnpjCadastrado(pessoajuridica.getCnpj()) != null) {
			throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o número: " + pessoajuridica.getCnpj());
		}
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeInscEstadual(pessoajuridica.getInsc_estadual()) != null) {
			throw new ExceptionMentoriaJava("Já existe inscrição estadual cadastrada com o número: " + pessoajuridica.getInsc_estadual());
		}
		
		pessoajuridica = pessoaservice.salvarPessoaJuridica(pessoajuridica);
		
		return new ResponseEntity<PessoaJuridica>(pessoajuridica, HttpStatus.OK);
	}
	
	
	
	/*end-point é microsservicos é um API*/
	@ResponseBody
	@PostMapping(value = "/salvarPf")
	public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws ExceptionMentoriaJava{
		
		if (pessoaFisica == null) {
			throw new ExceptionMentoriaJava("Pessoa fisica não pode ser NULL");
		}
		
		
		
		
		pessoaFisica = pessoaservice.salvarPessoaFisica(pessoaFisica);
		
		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
