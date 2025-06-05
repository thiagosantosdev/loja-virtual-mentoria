package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.MarcaProduto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.MarcaRepository;

@RestController
public class MarcaProdutoController {
	
	
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@PostMapping(value = "/salvarMarca")
	public ResponseEntity<MarcaProduto> salvarMarca(@RequestBody MarcaProduto marcaProduto) throws ExceptionMentoriaJava{
		
		if(marcaProduto.getId_marca_prod() == null) {
			List<MarcaProduto> marcaProdutos = marcaRepository.buscarMarcaDesc(marcaProduto.getNome().toUpperCase(), marcaProduto.getEmpresa().getId_pessoa());

			if(!marcaProdutos.isEmpty()) {
				throw new ExceptionMentoriaJava("Já existe MARCA com a descrição: " + marcaProduto.getNome());
			}
		}
		
		MarcaProduto marcaProdutoSalvo = marcaRepository.save(marcaProduto);

	    
		return new ResponseEntity<MarcaProduto>(marcaProdutoSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteMarca/{id_marca_prod}")
	public ResponseEntity<?> deleteMarcaId(@PathVariable("id_marca_prod") Long id_marca_prod){
		
		marcaRepository.deleteById(id_marca_prod);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "/obterMarcaProduto/{id_marca_prod}")
	public ResponseEntity<MarcaProduto> obterMarcaId(@Validated @PathVariable("id_marca_prod") Long id_marca_prod) throws ExceptionMentoriaJava { 
		
		MarcaProduto marca = marcaRepository.findById(id_marca_prod).orElse(null);
		
		if (marca == null) {
			throw new ExceptionMentoriaJava("Não encontrou MARCA com código: " + id_marca_prod);
		}
	
		return new ResponseEntity<MarcaProduto>(marca,HttpStatus.OK);
	}

	/*
	@GetMapping(value = "/buscarMarcaProdutoPorDesc/{nome}")
	public ResponseEntity<List<MarcaProduto>>buscarMarcaProdPorDes(@PathVariable("nome") String nome){
		
		List<MarcaProduto> marca = marcaRepository.buscarMarcaDesc(nome.toLowerCase().trim());
		
		return new ResponseEntity<List<MarcaProduto>>(marca, HttpStatus.OK);
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
