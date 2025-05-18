package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

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
import com.mentoria.lojavirtual.LojaVirtualJdev.model.CategoriaProduto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.CategoriaProdutoRepository;

@RestController
public class CategoriaProdutoController {

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;
	
	@PostMapping(value = "/salvarCategoria")
	public ResponseEntity<CategoriaProduto> salvarCategoria(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
	    
	    if (categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId_pessoa() == null)) {
	        throw new ExceptionMentoriaJava("A empresa deve ser informada!");
	    }
	    
	  
	    
	    if (categoriaProduto.getId_categ_prod() == null) {
	    	
	    	List<CategoriaProduto> categorias = categoriaProdutoRepository.existeCategoria(categoriaProduto.getNome_categ_prod().toUpperCase(),categoriaProduto.getEmpresa().getId_pessoa());
	    	 
	        
	    	if(!categorias.isEmpty()) {
	    		throw new ExceptionMentoriaJava("Não pode cadastrar categoria com mesmo nome.");
	    	}
	    }
	   
	
	    CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);

	    return new ResponseEntity<>(categoriaSalva, HttpStatus.OK);
	}
	
	/*
	 @PostMapping(value = "/salvarCategoria")
	public ResponseEntity<CategoriaProduto> salvarCategoria(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
	    
	    if (categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId_pessoa() == null)) {
	        throw new ExceptionMentoriaJava("A empresa deve ser informada!");
	    }
	    
	    // Aplica trim no nome da categoria antes de verificar a existência
	    String nomeCategoriaTrim = categoriaProduto.getNome_categ_prod() != null ? categoriaProduto.getNome_categ_prod().trim() : null;
	    
	    if (categoriaProduto.getId_categ_prod() == null && categoriaProdutoRepository.existeCategoria2(nomeCategoriaTrim)){
	        throw new ExceptionMentoriaJava("Não pode cadastrar categoria com mesmo nome.");
	    }
	   
	   
	    
	    

	    // Atualiza o nome sem espaços e salva
	    categoriaProduto.setNome_categ_prod(nomeCategoriaTrim);
	    CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);

	    return new ResponseEntity<>(categoriaSalva, HttpStatus.OK);
	}
	 
	 
	 
	 */

	@DeleteMapping(value = "/deleteCategoria/{id_categ_prod}", produces = "application/text")
	public String delete(@PathVariable("id_categ_prod") Long id_categ_prod) {

		categoriaProdutoRepository.deleteById(id_categ_prod);

		return "ok";
	}
	
	@GetMapping(value = "/buscarPorDescCategoria/{nome_categ_prod}")
	public ResponseEntity<List<CategoriaProduto>> buscarPorDesc(@PathVariable("nome_categ_prod") String nome_categ_prod){
		
		List<CategoriaProduto> buscaCategoria = categoriaProdutoRepository.buscarCategoriaDesc(nome_categ_prod.toUpperCase());
		
		return new ResponseEntity<List<CategoriaProduto>>(buscaCategoria, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
}
