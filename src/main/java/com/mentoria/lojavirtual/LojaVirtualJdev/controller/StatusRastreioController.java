package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.StatusRastreio;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.StatusRastreioRepository;

@RestController
public class StatusRastreioController {
	
	@Autowired
	private StatusRastreioRepository statusRastreioRepository;
	
	@GetMapping(value = "/listaRastreioVenda/{id}")
	public ResponseEntity<List<StatusRastreio>> listaRastreioVenda(@PathVariable("id") Long id){
		
		List<StatusRastreio> statusRastreio = statusRastreioRepository.listaRastreioVenda(id);
		
		return new ResponseEntity<List<StatusRastreio>>(statusRastreio, HttpStatus.OK);
		
	}

}
