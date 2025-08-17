package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.ArrayList;
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
import com.mentoria.lojavirtual.LojaVirtualJdev.model.NotaFiscalCompra;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.NotaFiscalCompraRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.NotaFiscalCompraService;

@RestController
public class NotaFiscalCompraController {
	
	@Autowired
	private NotaFiscalCompraRepository notaFiscalCompraRepository;
	
	@Autowired
	private NotaFiscalCompraService notaFiscalCompraService;
	
	@PostMapping(value = "/salvarNotaFiscalCompra")
	public ResponseEntity<NotaFiscalCompra> salvarNotaFiscalCompra(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra) throws ExceptionMentoriaJava {
	   
		if (notaFiscalCompra.getPessoa() == null || (notaFiscalCompra.getPessoa().getId_pessoa() <= 0 )) {
	        throw new ExceptionMentoriaJava("A Pessoa Jurídica da nota fiscal de compra deve ser informada!");
	    }
		
	  
		if (notaFiscalCompra.getEmpresa() == null || (notaFiscalCompra.getEmpresa().getId_pessoa() <= 0)) {
	        throw new ExceptionMentoriaJava("A empresa responsável deve ser informada!");
	    }
	    
	    
	  if (notaFiscalCompra.getId_nota_fiscal_compra() == null) {
	    	
	    	List<NotaFiscalCompra> notas = notaFiscalCompraRepository.existeCategoria(notaFiscalCompra.getDescricao_obs().toUpperCase(),notaFiscalCompra.getEmpresa().getId_pessoa());
	    	 
	        
	    	if(!notas.isEmpty()) {
	    		throw new ExceptionMentoriaJava("Não pode cadastrar nota fiscal de compra com o mesmo nome.");
	    	}
	    }
	   
	
	    NotaFiscalCompra notaSalva = notaFiscalCompraRepository.save(notaFiscalCompra);

	    return new ResponseEntity<>(notaSalva, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = "deleteNotaFiscalCompraPorId/{id_nota_fiscal_compra}")
	public ResponseEntity<?> deleteNotaFiscalCompraId(@PathVariable("id_nota_fiscal_compra") Long id_nota_fiscal_compra){
		
		notaFiscalCompraRepository.deleteItemNotaFiscalCompra(id_nota_fiscal_compra); /*Deleta os filhos*/
		notaFiscalCompraRepository.deleteById(id_nota_fiscal_compra); /*Deleta o pai*/
		
		
		return new ResponseEntity("Nota Fiscal de compra removida", HttpStatus.OK );
	}
	
	@GetMapping(value = "obterNotaFiscalCompra/{id_nota_fiscal_compra}")
	public ResponseEntity<NotaFiscalCompra> obterNotaFiscalCompra(@PathVariable("id_nota_fiscal_compra") Long id_nota_fiscal_compra) throws ExceptionMentoriaJava{
		
		NotaFiscalCompra notaFiscalCompra = notaFiscalCompraRepository.findById(id_nota_fiscal_compra).orElse(null);
		
		if(notaFiscalCompra == null) {
			throw new ExceptionMentoriaJava("Não encontrou Nota Fiscal com código: " + id_nota_fiscal_compra);
		}
		
		return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompra, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/buscarNotaFiscalPorDesc/{desc}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalPorDesc(@PathVariable("desc") String desc){
		
		List<NotaFiscalCompra> notaFiscalCompras = notaFiscalCompraRepository.buscaNotaDesc(desc.toUpperCase().trim());
		
		return new ResponseEntity<List<NotaFiscalCompra>>(notaFiscalCompras, HttpStatus.OK);
	}
	
	@GetMapping(value = "/relatorioProdCompraNotaFiscal")
	public ResponseEntity<List<ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO>> relatorioProdCompraNotaFiscal
	(@RequestBody ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO objetoRequisicaoRelatorioProdCompraNotaFiscalDTO){
		
		List<ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO> retorno = 
				new ArrayList<ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO>();
		
		retorno = notaFiscalCompraService.gerarRelatorioProdCompraNota(objetoRequisicaoRelatorioProdCompraNotaFiscalDTO);
		
		return new ResponseEntity<List<ObjetoRequisicaoRelatorioProdCompraNotaFiscalDTO>>(retorno, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
