package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.Acesso;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.AcessoRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.AcessoService;

@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@PostMapping(value = "/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso){
		
		Acesso acessoSalvo = acessoService.save(acesso);
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteAcesso")
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso){
		
		 acessoRepository.deleteById(acesso.getId_Acesso());
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	

}
