package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.NotaItemProduto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.NotaFiscalCompraRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.NotaItemProdutoRepository;

@RestController
public class NotaItemProdutoController {
	
	@Autowired
	private NotaFiscalCompraRepository notaFiscalCompraRepository;
	
	@Autowired
	private NotaItemProdutoRepository notaItemProdutoRepository;
	
	@PostMapping(value = "/salvarNotaItemProduto")
	public ResponseEntity<NotaItemProduto> salvarNotaItemProduto(@Valid @RequestBody  NotaItemProduto notaItemProduto) throws ExceptionMentoriaJava {
	   
	
	  if(notaItemProduto.getIdNotaItemProd() == null) {
		  
		
	  
		if (notaItemProduto.getProduto() == null || (notaItemProduto.getProduto().getId_produto() <= 0)) {
	        throw new ExceptionMentoriaJava("A produto deve ser informado!");
	    }
		
		if (notaItemProduto.getNota_fiscal_compra() == null || (notaItemProduto.getNota_fiscal_compra().getId_nota_fiscal_compra() <= 0)) {
	        throw new ExceptionMentoriaJava("A nota fiscal deve ser informada!");
	    }
		
		if (notaItemProduto.getEmpresa() == null || (notaItemProduto.getEmpresa().getId_pessoa() <= 0)) {
	        throw new ExceptionMentoriaJava("A empresa responsável deve ser informada!");
	    }
	    
	    
	  if (notaItemProduto.getIdNotaItemProd() == null) {
	    	
	    	List<NotaItemProduto> itens = notaItemProdutoRepository.buscaNotaItemPorProdutoNota(notaItemProduto.getProduto().getId_produto(),notaItemProduto.getNota_fiscal_compra().getId_nota_fiscal_compra());
	    	 
	        
	    	if(!itens.isEmpty()) {
	    		throw new ExceptionMentoriaJava("Não pode cadastrar nota fiscal de compra com o mesmo nome.");
	    	}
	    }
	   
	  }
	  
	  if (notaItemProduto.getQuantidade()  <= 0) {
	        throw new ExceptionMentoriaJava("A quantidade deve ser informado!");
	    }
	  
	
	  NotaItemProduto notaItemSalva = notaItemProdutoRepository.save(notaItemProduto);

	  notaItemSalva = notaItemProdutoRepository.findById(notaItemProduto.getIdNotaItemProd()).get();
	  
	    return new ResponseEntity<>(notaItemSalva, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "deleteNotaItemPorId/{idNotaItemProd}")
	public ResponseEntity<?> deleteNotaItemPorId(@PathVariable("idNotaItemProd") Long idNotaItemProd){
		
		notaItemProdutoRepository.deleteById(idNotaItemProd);
		
		return new ResponseEntity("Nota Item Produto Removido", HttpStatus.OK);
		
	}

}
