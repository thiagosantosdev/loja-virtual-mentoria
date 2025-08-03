package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Endereco;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.ItemVendaLoja;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.StatusRastreio;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.VendaCompraLojaVirtual;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.ItemVendaDTO;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.VendaCompraLojaVirtualDTO;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.EnderecoRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.NotaFiscalVendaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.StatusRastreioRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.VendaCompraLojaVirtualRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.VendaService;

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
	
	@Autowired
	private StatusRastreioRepository statusRastreioRepository;
	
	@Autowired
	private VendaService vendaService;
	
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
		
		
		for (int i = 0; i < vendaCompraLojaVirtual.getItemVendaLoja().size(); i++) {
			vendaCompraLojaVirtual.getItemVendaLoja().get(i).setEmpresa(vendaCompraLojaVirtual.getEmpresa());
			vendaCompraLojaVirtual.getItemVendaLoja().get(i).setVd_cp_lj_virt(vendaCompraLojaVirtual);
		}
		
		
		
		/* Salva primeiro a venda e todos os dados */
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		
		/*Salva o Status do rastreio - os locais onde está a mercadoria*/
		StatusRastreio statusRastreio = new StatusRastreio();
		statusRastreio.setCentro_distribuicao("Loja local");
		statusRastreio.setCidade("CidadeLocal");
		statusRastreio.setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		statusRastreio.setEstado("EstadoLocal");
		statusRastreio.setStatus("Inicio Compra");
		statusRastreio.setVd_cp_lj_virt(vendaCompraLojaVirtual);
		
		statusRastreioRepository.save(statusRastreio);
		
		/* Associa a venda gravada no banco com a nota fiscal */
		vendaCompraLojaVirtual.getNota_fiscal_venda().setVd_cp_lj_virt(vendaCompraLojaVirtual);
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		
		notaFiscalVendaRepository.saveAndFlush(vendaCompraLojaVirtual.getNota_fiscal_venda());
		
		VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
		compraLojaVirtualDTO.setValorTotal(vendaCompraLojaVirtual.getValor_total());
		compraLojaVirtualDTO.setPessoa(vendaCompraLojaVirtual.getPessoa());
		
		compraLojaVirtualDTO.setEntrega(vendaCompraLojaVirtual.getEndereco_entrega());
		compraLojaVirtualDTO.setCobranca(vendaCompraLojaVirtual.getEndereco_cobranca());
		compraLojaVirtualDTO.setValor_desc(vendaCompraLojaVirtual.getValor_desc());
		compraLojaVirtualDTO.setValor_frete(vendaCompraLojaVirtual.getValor_frete());
		compraLojaVirtualDTO.setId(vendaCompraLojaVirtual.getId());
		
		for (ItemVendaLoja item : vendaCompraLojaVirtual.getItemVendaLoja()) {

			ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
			itemVendaDTO.setQuantidade(item.getQuantidade());
			itemVendaDTO.setProduto(item.getProduto());

			compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
		}
		
		return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/consultaVendaId/{id}")
	public ResponseEntity<VendaCompraLojaVirtualDTO> consultaVendaId(@PathVariable("id") Long id){
		
		VendaCompraLojaVirtual compraLojaVirtual = vendaCompraLojaVirtualRepository.findByIdExclusao(id);
		
		if(compraLojaVirtual == null) {
			compraLojaVirtual = new VendaCompraLojaVirtual();
		}
		
		VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
		
		compraLojaVirtualDTO.setValorTotal(compraLojaVirtual.getValor_total());
		compraLojaVirtualDTO.setPessoa(compraLojaVirtual.getPessoa());
		
		compraLojaVirtualDTO.setEntrega(compraLojaVirtual.getEndereco_entrega());
		compraLojaVirtualDTO.setCobranca(compraLojaVirtual.getEndereco_cobranca());
		
		compraLojaVirtualDTO.setValor_desc(compraLojaVirtual.getValor_desc());
		compraLojaVirtualDTO.setValor_frete(compraLojaVirtual.getValor_frete());
		compraLojaVirtualDTO.setId(compraLojaVirtual.getId());

		for (ItemVendaLoja item : compraLojaVirtual.getItemVendaLoja()) {

			ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
			itemVendaDTO.setQuantidade(item.getQuantidade());
			itemVendaDTO.setProduto(item.getProduto());

			compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
		}
		
		return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);

		
	}
	
	@DeleteMapping(value = "/deleteVendaTotalBanco/{id}")
	public ResponseEntity<String> deleteVendaTotalBanco(@PathVariable(value = "id") Long id) throws SQLException{
		
		vendaService.exclusaoTotalVendaBanco(id);
		
		return new ResponseEntity<String>("Venda excluida com sucesso!", HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/deleteLogicoVenda/{id}")
	public ResponseEntity<String> deleteLogicoVendaBanco(@PathVariable(value = "id") Long id) throws SQLException{
		
		vendaService.exclusaoLogica(id);
		
		return new ResponseEntity<String>("Venda logicamente excluida com sucesso!", HttpStatus.OK);
		
	}
	@PutMapping(value = "/ativaRegistroVenda/{id}")
	public ResponseEntity<String> ativaRegistroLogicoVendaBanco(@PathVariable(value = "id") Long id) throws SQLException{
		
		vendaService.ativaVendaLogica(id);
		
		return new ResponseEntity<String>("Venda logicamente ATIVADA com sucesso!", HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "/consultaVendaPorProdutoId/{id}")
	public ResponseEntity<List<VendaCompraLojaVirtualDTO>> consultaVendaPorProduto(@PathVariable("id") Long idProd) {

		List<VendaCompraLojaVirtual> compraLojaVirtual = vendaCompraLojaVirtualRepository.vendaPorProduto(idProd);
		
		if (compraLojaVirtual == null) {
			compraLojaVirtual = new ArrayList<VendaCompraLojaVirtual>();
		}
		
		List<VendaCompraLojaVirtualDTO> compraLojaVirtualDTOList = new ArrayList<VendaCompraLojaVirtualDTO>();
		
		for (VendaCompraLojaVirtual vcl : compraLojaVirtual) {
			
			VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
	
			compraLojaVirtualDTO.setValorTotal(vcl.getValor_total());
			compraLojaVirtualDTO.setPessoa(vcl.getPessoa());
	
			compraLojaVirtualDTO.setEntrega(vcl.getEndereco_entrega());
			compraLojaVirtualDTO.setCobranca(vcl.getEndereco_cobranca());
	
			compraLojaVirtualDTO.setValor_desc(vcl.getValor_desc());
			compraLojaVirtualDTO.setValor_frete(vcl.getValor_frete());
			compraLojaVirtualDTO.setId(vcl.getId());

			for (ItemVendaLoja item : vcl.getItemVendaLoja()) {
	
				ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
				itemVendaDTO.setQuantidade(item.getQuantidade());
				itemVendaDTO.setProduto(item.getProduto());
	
				compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
			}
			
			compraLojaVirtualDTOList.add(compraLojaVirtualDTO);
		
		}

		return new ResponseEntity<List<VendaCompraLojaVirtualDTO>>(compraLojaVirtualDTOList, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/consultaVendaDinamica/{valor}/{tipoconsulta}")
	public ResponseEntity<List<VendaCompraLojaVirtualDTO>> consultaVendaDinamica(@PathVariable("valor") String valor,
																				@PathVariable("tipoconsulta") String tipoconsulta) {

		List<VendaCompraLojaVirtual> compraLojaVirtual = null;
		
		if(tipoconsulta.equalsIgnoreCase("POR_ID_PROD")) {
			compraLojaVirtual = vendaCompraLojaVirtualRepository.vendaPorProduto(Long.parseLong(valor));
			
		}else if(tipoconsulta.equalsIgnoreCase("POR_NOME_PROD")) {
			compraLojaVirtual = vendaCompraLojaVirtualRepository.vendaPorNomeProduto(valor.toUpperCase().trim());
		}
		else if(tipoconsulta.equalsIgnoreCase("POR_NOME_CLIENTE")) {
			compraLojaVirtual = vendaCompraLojaVirtualRepository.vendaPorNomeCliente(valor.toUpperCase().trim());
		}
		else if(tipoconsulta.equalsIgnoreCase("POR_ENDERECO_COBRANCA")) {
			compraLojaVirtual = vendaCompraLojaVirtualRepository.vendaPorEnderecoCobranca(valor.toUpperCase().trim());
		}
		else if(tipoconsulta.equalsIgnoreCase("POR_ENDERECO_ENTREGA")) {
			compraLojaVirtual = vendaCompraLojaVirtualRepository.vendaPorEnderecoEntrega(valor.toUpperCase().trim());
		}
		
		if (compraLojaVirtual == null) {
			compraLojaVirtual = new ArrayList<VendaCompraLojaVirtual>();
		}
		
		List<VendaCompraLojaVirtualDTO> compraLojaVirtualDTOList = new ArrayList<VendaCompraLojaVirtualDTO>();
		
		for (VendaCompraLojaVirtual vcl : compraLojaVirtual) {
			
			VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
	
			compraLojaVirtualDTO.setValorTotal(vcl.getValor_total());
			compraLojaVirtualDTO.setPessoa(vcl.getPessoa());
	
			compraLojaVirtualDTO.setEntrega(vcl.getEndereco_entrega());
			compraLojaVirtualDTO.setCobranca(vcl.getEndereco_cobranca());
	
			compraLojaVirtualDTO.setValor_desc(vcl.getValor_desc());
			compraLojaVirtualDTO.setValor_frete(vcl.getValor_frete());
			compraLojaVirtualDTO.setId(vcl.getId());

			for (ItemVendaLoja item : vcl.getItemVendaLoja()) {
	
				ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
				itemVendaDTO.setQuantidade(item.getQuantidade());
				itemVendaDTO.setProduto(item.getProduto());
	
				compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
			}
			
			compraLojaVirtualDTOList.add(compraLojaVirtualDTO);
		
		}

		return new ResponseEntity<List<VendaCompraLojaVirtualDTO>>(compraLojaVirtualDTOList, HttpStatus.OK);
	}
	
	
	

}
