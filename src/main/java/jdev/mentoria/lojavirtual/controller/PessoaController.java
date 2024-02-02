package jdev.mentoria.lojavirtual.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;

@RestController
public class PessoaController {
	
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pesssoaJuridica) throws ExceptionMentoriaJava{
		
		if(pesssoaJuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa jurídica não pode ser nula");
		}
		if(pesssoaJuridica.getId() == null) {
			
		}
		
		return new ResponseEntity<PessoaJuridica>(pesssoaJuridica, HttpStatus.OK);
	}

}
