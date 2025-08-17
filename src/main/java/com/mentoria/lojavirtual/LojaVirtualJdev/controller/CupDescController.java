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
import com.mentoria.lojavirtual.LojaVirtualJdev.model.CupomDesc;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.CupDescontoRepository;

@RestController
public class CupDescController {
	
	@Autowired
	private CupDescontoRepository cupDescontoRepository;
	
	@PostMapping(value = "salvarCupDesc")
	public ResponseEntity<CupomDesc> salvarCupDesc(@RequestBody @Valid CupomDesc cupDesc) throws ExceptionMentoriaJava{
		
		CupomDesc cupDesc2 = cupDescontoRepository.save(cupDesc);
		
		return new ResponseEntity<CupomDesc>(cupDesc2, HttpStatus.OK);
		
	}
	
	/*Lista todos os cupons de desconto da empresa, basta colocar o id da mesma*/
	@GetMapping(value = "/listaCumpomDesc/{id_pessoa}")
	public ResponseEntity<List<CupomDesc>> listarCupomDesc(@PathVariable("id_pessoa") Long id_pessoa){
		
		return new ResponseEntity<List<CupomDesc>>(cupDescontoRepository.cupDescontoPorEmpresa(id_pessoa), HttpStatus.OK);
		
	}
	/*lista todos os cupons de todas as empresas*/
	@GetMapping(value = "/listaCumpomDesc")
	public ResponseEntity<List<CupomDesc>> listarCupomDesc(){
		
		return new ResponseEntity<List<CupomDesc>>(cupDescontoRepository.findAll(), HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/deleteCupomPorId/{id}")
	public ResponseEntity<?> deleteMarcaPorId(@PathVariable("id") Long id){
		
		cupDescontoRepository.deleteById(id);
		
		return new ResponseEntity("Cupom produto removido", HttpStatus.OK);
		
	}
	@GetMapping(value = "/obterCupom/{id}")
	public ResponseEntity<CupomDesc> obterCupom(@PathVariable("id") Long id) throws ExceptionMentoriaJava{
	
		CupomDesc cupDesc = cupDescontoRepository.findById(id).orElse(null);
		
		if(cupDesc == null) {
			throw new ExceptionMentoriaJava("Não encontrou cupom produto com código: " + id);
		}
		return new ResponseEntity<CupomDesc>(cupDesc, HttpStatus.OK);
	}
	
	
	/*
	
	select p.id as codigoProduto, p.nome as nomeProduto,
p.valor_venda as valorVendaProduto, ntp.quantidade as quantidadeComprada,
pj.id as codigoFornecedor, pj.nome as nomeFornecedor, cfc.data_compra as dataCompra
from nota_fiscal_compra as cfc
inner join nota_item_produto as ntp on cfc.id = nota_fiscal_compra_id
inner join produto as p on p.id = ntp.produto_id
inner join pessoa_juridica as pj on pj.id = cfc.pessoa_id
where upper(p.nome) like upper('%iphon%')
and cfc.data_compra >= '2022-01-01' and cfc.data_compra <= '2022-02-01'

--where upper(pj.nome) like upper('%jose%');
--where p.id = 15;
--where cfc.data_compra >= '2022-01-01' and cfc.data_compra <= '2022-02-01'
--where cfc.id = 21;

--por produto
--por intervalo de data
--por fornecedor
	
	
	*/
	

}
