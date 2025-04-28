package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaJuridica;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.PessoaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.PessoaService;
import com.mentoria.lojavirtual.LojaVirtualJdev.util.ValidaCNPJ;
import com.mentoria.lojavirtual.LojaVirtualJdev.util.ValidaCPF;

@RestController
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PessoaService pessoaservice;
	
	
	@ResponseBody
	@PostMapping(value = "/salvarPJ")
	public ResponseEntity<PessoaJuridica> salvarPJ( @RequestBody @Valid PessoaJuridica pessoajuridica) throws ExceptionMentoriaJava  {
		/*
		if(pessoajuridica.getNome_pessoa() == null || pessoajuridica.getNome_pessoa().trim().isEmpty()) {
			throw new ExceptionMentoriaJava("Informe o campo de nome");
		} */
		
		if(pessoajuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa Jurídica não pode ser NULL");
		} 
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeCnpjCadastrado(pessoajuridica.getCnpj()) != null) {
			throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o número: " + pessoajuridica.getCnpj());
		}
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeInscEstadual(pessoajuridica.getInsc_estadual()) != null) {
			throw new ExceptionMentoriaJava("Já existe inscrição estadual cadastrada com o número: " + pessoajuridica.getInsc_estadual());
		}
		if(!ValidaCNPJ.isCNPJ(pessoajuridica.getCnpj())) {
			throw new ExceptionMentoriaJava("CNPJ: " + pessoajuridica.getCnpj() + " está inválido!");
			
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
		
		
		
		if (pessoaFisica.getId_pessoa() == null && pessoaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
			throw new ExceptionMentoriaJava("Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
		}
		
		
		if (!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
			throw new ExceptionMentoriaJava("CPF : " + pessoaFisica.getCpf() + " está inválido.");
		}
		
		
			pessoaFisica = pessoaservice.salvarPessoaFisica(pessoaFisica);
		
		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
	}
	
	
}
	





















