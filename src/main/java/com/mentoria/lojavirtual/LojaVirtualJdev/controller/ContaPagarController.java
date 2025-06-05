package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.ContaPagar;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.ContaPagarRepository;

@RestController
public class ContaPagarController {

	@Autowired
	private ContaPagarRepository contaPagarRepository;
	
	@PostMapping(value = "salvarContaPagar")
	public ResponseEntity<ContaPagar> salvaContaPagar(@RequestBody @Valid ContaPagar contaPagar) throws ExceptionMentoriaJava{
		
		if(contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId_pessoa() <= 0) {
			throw new ExceptionMentoriaJava("Empresa responsável deve ser informada!");
		}
		if(contaPagar.getPessoa() == null || contaPagar.getPessoa().getId_pessoa() <= 0) {
			throw new ExceptionMentoriaJava("Pessoa responsável deve ser informada!");
		}
		if(contaPagar.getPessoa_fornecedor() == null || contaPagar.getPessoa_fornecedor().getId_pessoa() <= 0) {
			throw new ExceptionMentoriaJava("Fornecedor responsável deve ser informada!");
		}
		
		 if (contaPagar.getId_conta_pagar() == null) {
		    	
		    	List<ContaPagar> contas = contaPagarRepository.buscaContaDesc(contaPagar.getDescricao().toUpperCase(),contaPagar.getEmpresa().getId_pessoa());
		    	 
		        
		    	if(!contas.isEmpty()) {
		    		throw new ExceptionMentoriaJava("Não pode cadastrar conta a pagar com mesmo nome.");
		    	}
		    }
		
		ContaPagar contaPagarSalva = contaPagarRepository.save(contaPagar);
		
		return new ResponseEntity<ContaPagar>(contaPagarSalva, HttpStatus.OK);
	}
	
	
	/*
	@GetMapping(value = "/buscarContaPagarDesc/{desc}")
	public ResponseEntity<List<ContaPagar>> buscarContaPagarDesc(@PathVariable("desc") String desc){
		
		List<ContaPagar> contaPagar = contaPagarRepository.buscaContaDesc(desc.toUpperCase());
		
		return new ResponseEntity<List<ContaPagar>>(contaPagar, HttpStatus.OK);
		
		}  */ 
	
	@GetMapping(value = "/obterContaPagar/{id_conta_pagar}")
	public ResponseEntity<ContaPagar> obterContaPagar(@PathVariable("id_conta_pagar") Long id_conta_pagar) throws ExceptionMentoriaJava{
		
		ContaPagar contaPagar = contaPagarRepository.findById(id_conta_pagar).orElse(null);
		
		if(contaPagar == null) {
			throw new ExceptionMentoriaJava("Não encontrou CONTA A PAGAR com código: " + id_conta_pagar);
		}
		
		return new ResponseEntity<ContaPagar>(contaPagar, HttpStatus.OK);
	}
	@DeleteMapping(value = "/deleteContaPagarPorId/{id_conta_pagar}")
	public ResponseEntity<String> deleteContaPagarPorId(@PathVariable("id_conta_pagar") Long id_conta_pagar){
		
		contaPagarRepository.deleteById(id_conta_pagar);
		
		return new ResponseEntity<String>("Conta a Pagar removida!" , HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
