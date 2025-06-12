package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.VendaCompraLojaVirtual;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.VendaCompraLojaVirtualRepository;

@RestController
public class VendaCompraLojaVirtualController {
	
	@Autowired
	private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;
	
	@PostMapping(value = "/salvarVenda")
	public ResponseEntity<VendaCompraLojaVirtual> salvarVenda(@Valid @RequestBody VendaCompraLojaVirtual vendaCompraLojaVirtual) {
		
		
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		
		return new ResponseEntity<VendaCompraLojaVirtual>(vendaCompraLojaVirtual, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
