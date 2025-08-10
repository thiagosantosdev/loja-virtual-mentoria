package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.NotaFiscalVenda;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.NotaFiscalVendaRepository;

@RestController
public class NotaFiscalVendaController {

	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;
	
	@GetMapping(value = "obterNotaFiscalVendaUnica/{id}")
	public ResponseEntity<NotaFiscalVenda> obterNotaFiscalVendaUnica(@PathVariable("id") Long id) throws ExceptionMentoriaJava{
		
		NotaFiscalVenda notaFiscalVenda = notaFiscalVendaRepository.buscaNotaPorVendaUnica(id);
		
		if(notaFiscalVenda == null) {
			throw new ExceptionMentoriaJava("Não encontrou Nota Fiscal de venda com código: " + id);
		}
		return new ResponseEntity<NotaFiscalVenda>(notaFiscalVenda, HttpStatus.OK);
	}
	
	@GetMapping(value = "obterNotaFiscalVenda/{id}")
	public ResponseEntity<List<NotaFiscalVenda>> obterNotaFiscalVenda(@PathVariable("id") Long id) throws ExceptionMentoriaJava{
		
		List<NotaFiscalVenda> notaFiscalVenda = notaFiscalVendaRepository.buscaNotaPorVenda(id);
		
		if(notaFiscalVenda == null) {
			throw new ExceptionMentoriaJava("Não encontrou Nota Fiscal de venda com código: " + id);
		}
		return new ResponseEntity<List<NotaFiscalVenda>>(notaFiscalVenda, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
