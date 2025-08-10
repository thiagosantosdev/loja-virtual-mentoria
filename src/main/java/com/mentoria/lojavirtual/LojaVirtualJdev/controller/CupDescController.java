package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.CupomDesc;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.CupDescontoRepository;

@RestController
public class CupDescController {
	
	@Autowired
	private CupDescontoRepository cupDescontoRepository;
	
	/*Lista todos os cupons de desconto da empresa, basta colocar o id da mesma*/
	@GetMapping(value = "/listaCumpomDesc/{id_pessoa}")
	public ResponseEntity<List<CupomDesc>> listarCupomDesc(@PathVariable("id_pessoa") Long id_pessoa){
		
		return new ResponseEntity<List<CupomDesc>>(cupDescontoRepository.cupDescontoPorEmpresa(id_pessoa), HttpStatus.OK);
		
	}
	/*lista todos os cupons de todas as empresas*/
	@GetMapping(value = "/listaCumpomDesc")
	public ResponseEntity<List<CupomDesc>> listarCupomDesc(){
		
		return new ResponseEntity<List<CupomDesc>>(cupDescontoRepository.findAll(), HttpStatus.OK);
		
	}
	

}
