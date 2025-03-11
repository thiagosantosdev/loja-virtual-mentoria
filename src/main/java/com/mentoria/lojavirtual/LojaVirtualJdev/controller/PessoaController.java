package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaJuridica;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.PessoaRepository;

@RestController
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@ResponseBody
	@PostMapping(value = "/salvarPJ")
	public ResponseEntity<PessoaJuridica> salvarPJ(@RequestBody PessoaJuridica pessoajuridica) throws ExceptionMentoriaJava  {
		
		if(pessoajuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa Jurídica não pode ser NULL");
		}
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeCnpjCadastrado(pessoajuridica.getCnpj()) != null) {
			throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o número: " + pessoajuridica.getCnpj());
		}
		return new ResponseEntity<PessoaJuridica>(pessoajuridica, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
