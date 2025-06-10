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
import com.mentoria.lojavirtual.LojaVirtualJdev.model.AvaliacaoProduto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.AvaliacaoProdutoRepository;

@RestController
public class AvaliacaoProdutoController {
	
	@Autowired
	private AvaliacaoProdutoRepository avaliacaoProdutoRepository;
	
	@PostMapping(value = "/salvarAvaliacaoProduto")
	public ResponseEntity<AvaliacaoProduto> salvarAvaliacao(@Valid @RequestBody AvaliacaoProduto avaliacaoProduto) throws ExceptionMentoriaJava{
		
		if (avaliacaoProduto.getEmpresa() == null || (avaliacaoProduto.getEmpresa() != null && avaliacaoProduto.getEmpresa().getId_pessoa() <= 0)) {
			 throw new ExceptionMentoriaJava("Informa a empresa dona do registro");
		}
		
		if(avaliacaoProduto.getProduto() == null || (avaliacaoProduto.getProduto() != null && avaliacaoProduto.getProduto().getId_produto() <= 0)) {
			 throw new ExceptionMentoriaJava("A avaliação deve conter o produto associado.");
		}
		
		
		if(avaliacaoProduto.getPessoa() == null || (avaliacaoProduto.getPessoa() != null && avaliacaoProduto.getPessoa().getId_pessoa() <= 0)) {
			 throw new ExceptionMentoriaJava("A avaliação deve conter a pessoa ou cliente associado.");
		}
		
		avaliacaoProduto = avaliacaoProdutoRepository.saveAndFlush(avaliacaoProduto);
		
		return new ResponseEntity<AvaliacaoProduto>(avaliacaoProduto, HttpStatus.OK);
		
	}
	
	
	
	@DeleteMapping(value = "/deleteAvalicaoPessoa/{id_avaliacao_produto}")
	public ResponseEntity<?> deleteAvalicaoPessoa(@PathVariable("id_avaliacao_produto") Long id_avaliacao_produto) { 
		
		avaliacaoProdutoRepository.deleteById(id_avaliacao_produto);
		
		return new ResponseEntity<String>("Avaliacao Removida!", HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/avaliacaoProduto/{id_produto}")
	public ResponseEntity<List<AvaliacaoProduto>> avaliacaoProduto(@PathVariable("id_produto") Long id_produto) { 
		
		List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoProduto(id_produto);
		
		return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/avaliacaoPessoa/{id_pessoa}")
	public ResponseEntity<List<AvaliacaoProduto>> avaliacaoPessoa(@PathVariable("id_pessoa") Long id_pessoa) { 
		
		List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoPessoa(id_pessoa);
		
		return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/avaliacaoProdutoPessoa/{id_produto}/{id_pessoa}")
	public ResponseEntity<List<AvaliacaoProduto>> 
	      avaliacaoProdutoPessoa(@PathVariable("id_produto") Long id_produto, @PathVariable("id_pessoa") Long id_pessoa) { 
		
		List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoProdutoPessoa(id_produto, id_pessoa);
		
		return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos,HttpStatus.OK);
	}
	

	
	
	
	

}
