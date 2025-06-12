package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.FormaPagamento;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.FormaPagamentoRepository;

@RestController
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@PostMapping(value = "/salvarFormaPagamento")
	public ResponseEntity<FormaPagamento> salvarFormaPagamento(@Valid @RequestBody FormaPagamento formaPagamento)throws ExceptionMentoriaJava{
		
		formaPagamento = formaPagamentoRepository.save(formaPagamento);
		
		return new ResponseEntity<FormaPagamento>(formaPagamento, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	

}
