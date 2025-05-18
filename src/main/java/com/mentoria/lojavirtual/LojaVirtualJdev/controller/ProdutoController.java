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
import com.mentoria.lojavirtual.LojaVirtualJdev.model.CategoriaProduto;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Produto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@PostMapping(value = "/salvarProduto")
	public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid Produto produto) throws ExceptionMentoriaJava{
		
		if(produto.getEmpresa() == null || produto.getEmpresa().getId_pessoa() <= 0) {
    		throw new ExceptionMentoriaJava("Empresa deve ser informada!");
	    }
		
		
	    if (produto.getId_produto() == null) {
	    	
	    	List<Produto> produtos = produtoRepository.buscarProdutoNome(produto.getNome().toUpperCase(),produto.getEmpresa().getId_pessoa());
	    	 
	        
	    	if(!produtos.isEmpty()) {
	    		throw new ExceptionMentoriaJava("Não pode cadastrar PRODUTO com mesmo nome!");
	    	}
	    }
	    
	    
	    
	    if(produto.getCategoriaProduto() == null || produto.getCategoriaProduto().getId_categ_prod() <= 0) {
    		throw new ExceptionMentoriaJava("Categoria deve ser informada!");
	    }
		
	    if(produto.getMarcaProduto() == null || produto.getMarcaProduto().getId_marca_prod() <= 0) {
    		throw new ExceptionMentoriaJava("Marca deve ser informada!");

		}
	    Produto produtoSalvo = produtoRepository.save(produto);
	    
	    return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
		
}
	
	
	@DeleteMapping(value = "/deleteProduto/{id_produto}", produces = "application/text")
	public String delete(@PathVariable("id_produto") Long id_produto) {

		produtoRepository.deleteById(id_produto);

		return "ok";
	}
	@GetMapping(value = "/buscarProduto/{id_produto}")
	public ResponseEntity<Produto> buscarProduto(@PathVariable("id_produto") Long id_produto)throws ExceptionMentoriaJava{
		
		Produto produto = produtoRepository.findById(id_produto).orElse(null);
		
		if(produto == null) {
			throw new ExceptionMentoriaJava("Não encontrou produto com código: " + id_produto);
			}
		
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	/* @GetMapping(value = "buscarProdNome/{desc}")
	  public ResponseEntity<List<Produto>> buscarProdNome(@PathVariable("desc") String desc){
		
		List<Produto> produto = produtoRepository.buscarProdutoName(desc.toUpperCase());
		
		return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);
	} */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
