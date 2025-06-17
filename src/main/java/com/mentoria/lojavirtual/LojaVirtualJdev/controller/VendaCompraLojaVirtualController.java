package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Endereco;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.VendaCompraLojaVirtual;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.VendaCompraLojaVirtualDTO;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.EnderecoRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.NotaFiscalVendaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.VendaCompraLojaVirtualRepository;

@RestController
public class VendaCompraLojaVirtualController {
	
	@Autowired
	private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;
	
	@Autowired
	private PessoaController pessoaController;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;
	
	@PostMapping(value = "/salvarVenda")
	public ResponseEntity<VendaCompraLojaVirtualDTO> salvarVenda(@Valid @RequestBody VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionMentoriaJava {
		
		vendaCompraLojaVirtual.getPessoa().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		PessoaFisica pessoaFisica = pessoaController.salvarPf(vendaCompraLojaVirtual.getPessoa()).getBody();
		vendaCompraLojaVirtual.setPessoa(pessoaFisica);
		
		vendaCompraLojaVirtual.getEndereco_cobranca().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEndereco_cobranca().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		Endereco enderecoCobranca = enderecoRepository.save(vendaCompraLojaVirtual.getEndereco_cobranca());
		vendaCompraLojaVirtual.setEndereco_cobranca(enderecoCobranca);

		vendaCompraLojaVirtual.getEndereco_entrega().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEndereco_entrega().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		Endereco enderecoEntrega = enderecoRepository.save(vendaCompraLojaVirtual.getEndereco_entrega());
		vendaCompraLojaVirtual.setEndereco_entrega(enderecoEntrega);

		vendaCompraLojaVirtual.getNota_fiscal_venda().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		
		/* Salva primeiro a venda e todo os dados */
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		
		/* Associa a venda gravada no banco com a nota fiscal */
		vendaCompraLojaVirtual.getNota_fiscal_venda().setVd_cp_lj_virt(vendaCompraLojaVirtual);
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		
		notaFiscalVendaRepository.saveAndFlush(vendaCompraLojaVirtual.getNota_fiscal_venda());
		
		VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
		compraLojaVirtualDTO.setValorTotal(vendaCompraLojaVirtual.getValor_total());
		return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
