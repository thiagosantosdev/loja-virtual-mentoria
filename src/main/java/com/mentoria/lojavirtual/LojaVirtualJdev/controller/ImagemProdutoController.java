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

import com.mentoria.lojavirtual.LojaVirtualJdev.model.ImagemProduto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.ImagemProdutoRepository;

@RestController
public class ImagemProdutoController {
	
	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;
	
	@PostMapping(value = "/salvarImagemProduto")
	public ResponseEntity<ImagemProduto> salvarImagemProduto(@RequestBody ImagemProduto imagemProduto){
		
		imagemProduto = imagemProdutoRepository.saveAndFlush(imagemProduto);
		
		return new ResponseEntity<ImagemProduto>(imagemProduto, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "/obterImagemPorProduto/{id_produto}")
	public ResponseEntity<List<ImagemProduto>> obterImagemPorProduto(@PathVariable("id_produto") Long id_produto){
		
		List<ImagemProduto> imagemProdutos = imagemProdutoRepository.buscaImagemProduto(id_produto);
		
		return new ResponseEntity<List<ImagemProduto>>(imagemProdutos, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/deleteImagemProdutoId/{id_produto}")
	public ResponseEntity<?> deleteImagemProdutoPorId(@PathVariable("id_produto") Long id_produto){
		
		imagemProdutoRepository.deleteById(id_produto);
		
		return new ResponseEntity<String>("Imagem do produto removida!", HttpStatus.OK);	
		
	}
	
	public ResponseEntity<?> deletetodoImagemProduto(@PathVariable("id_produto") Long id_produto){
		
		imagemProdutoRepository.deleteImagem(id_produto);
		
		return new ResponseEntity<String>("Imagem removida!", HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
