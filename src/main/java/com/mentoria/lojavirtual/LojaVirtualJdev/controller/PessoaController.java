package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.enums.TipoPessoa;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Endereco;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaJuridica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.CepDTO;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.dto.ConsultaCnpjDto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.EnderecoRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.PessoaFisicaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.PessoaRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.PessoaService;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.ServiceContagemAcessoApi;
import com.mentoria.lojavirtual.LojaVirtualJdev.util.ValidaCNPJ;
import com.mentoria.lojavirtual.LojaVirtualJdev.util.ValidaCPF;

@RestController
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaservice;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Autowired
	private ServiceContagemAcessoApi serviceContagemAcessoApi;

	
	@ResponseBody
	@GetMapping(value = "/consultaPfNome/{nome_pessoa}")
	public ResponseEntity<List<PessoaFisica>> consultaPfNome(@PathVariable("nome_pessoa") String nome_pessoa){
		
		List<PessoaFisica> fisicas = pessoaFisicaRepository.pesquisaPorNomePF(nome_pessoa.trim().toUpperCase());
		
		serviceContagemAcessoApi.atualizaAcessoEndPointPF();
		
		return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/consultaPfCPF/{cpf}")
	public ResponseEntity<List<PessoaFisica>> consultaPfCpf(@PathVariable("cpf") String cpf){
		
		List<PessoaFisica> fisicas = pessoaFisicaRepository.pesquisaPorCpfPF(cpf);
		
		serviceContagemAcessoApi.atualizaAcessoEndPointPJ();
		
		return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "/consultaNomePJ/{nome_pessoa}")
	public ResponseEntity<List<PessoaJuridica>> consultaNomePJ(@PathVariable("nome_pessoa") String nome_pessoa){
		
		List<PessoaJuridica> fisicas = pessoaRepository.pesquisaPorNomePJ(nome_pessoa.trim().toUpperCase());
		
		return new ResponseEntity<List<PessoaJuridica>>(fisicas, HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "/consultaCnpjPJ/{cnpj}")
	public ResponseEntity<List<PessoaJuridica>> consultaCnpjPJ(@PathVariable("cnpj") String cnpj){
		
		List<PessoaJuridica> fisicas = pessoaRepository.existeCnpjCadastradoList(cnpj);
		
		return new ResponseEntity<List<PessoaJuridica>>(fisicas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/consultaCep/{cep}")
	public ResponseEntity<CepDTO> consultaCep(@PathVariable("cep") String cep){
		
		CepDTO cepDTO = pessoaservice.consultaCep(cep);
		
		return new ResponseEntity<CepDTO>(cepDTO, HttpStatus.OK);
		
	}
	
	@ResponseBody
	@GetMapping(value = "/consultaCnpjReceitaWs/{cnpj}")
	public ResponseEntity<ConsultaCnpjDto> consultaCnpjReceitaWs(@PathVariable("cnpj") String cnpj){
		
		
		
		return new ResponseEntity<ConsultaCnpjDto>(pessoaservice.consultaCnpjReceitaWS(cnpj), HttpStatus.OK);
		
	}
	
	
	@ResponseBody
	@PostMapping(value = "/salvarPJ")
	public ResponseEntity<PessoaJuridica> salvarPJ( @RequestBody @Valid PessoaJuridica pessoajuridica) throws ExceptionMentoriaJava  {
		/*
		if(pessoajuridica.getNome_pessoa() == null || pessoajuridica.getNome_pessoa().trim().isEmpty()) {
			throw new ExceptionMentoriaJava("Informe o campo de nome");
		} */
		
		if(pessoajuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa Jurídica não pode ser NULL");
		} 
		
		if(pessoajuridica.getTipoPessoa() == null) {
			throw new ExceptionMentoriaJava("Informe o tipo jurídico ou fornecedor da LOJA");

		}
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeEmailCadastrado(pessoajuridica.getEmail()) != null) {
			throw new ExceptionMentoriaJava("Já existe esse email cadastrado: " + pessoajuridica.getEmail());
		}
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeCnpjCadastrado(pessoajuridica.getCnpj()) != null) {
			throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o número: " + pessoajuridica.getCnpj());
		}
		
		if(pessoajuridica.getId_pessoa() == null && pessoaRepository.existeInscEstadual(pessoajuridica.getInsc_estadual()) != null) {
			throw new ExceptionMentoriaJava("Já existe inscrição estadual cadastrada com o número: " + pessoajuridica.getInsc_estadual());
		}
		if(!ValidaCNPJ.isCNPJ(pessoajuridica.getCnpj())) {
			throw new ExceptionMentoriaJava("CNPJ: " + pessoajuridica.getCnpj() + " está inválido!");
		}
		
		if(pessoajuridica.getId_pessoa() == null || pessoajuridica.getId_pessoa() <= 0) {
			
			for(int p = 0; p < pessoajuridica.getEnderecos().size(); p++) {
				
				CepDTO cepDTO = pessoaservice.consultaCep(pessoajuridica.getEnderecos().get(p).getCep());
				
				pessoajuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
				pessoajuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
				pessoajuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
				pessoajuridica.getEnderecos().get(p).setRuaLogradouro(cepDTO.getLogradouro());
				pessoajuridica.getEnderecos().get(p).setUf(cepDTO.getUf());
				
			}
			
		}else {
			
			for(int p = 0; p < pessoajuridica.getEnderecos().size(); p++) {
			
				Endereco enderecoTemp = enderecoRepository.findById(pessoajuridica.getEnderecos().get(p).getId_endereco()).get();
				
				if(!enderecoTemp.getCep().equals(pessoajuridica.getEnderecos().get(p).getCep())) {
					
					CepDTO cepDTO = pessoaservice.consultaCep(pessoajuridica.getEnderecos().get(p).getCep());
					
					pessoajuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
					pessoajuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
					pessoajuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
					pessoajuridica.getEnderecos().get(p).setRuaLogradouro(cepDTO.getLogradouro());
					pessoajuridica.getEnderecos().get(p).setUf(cepDTO.getUf());
					
				}
			}
		}
		
		pessoajuridica = pessoaservice.salvarPessoaJuridica(pessoajuridica);
		
		return new ResponseEntity<PessoaJuridica>(pessoajuridica, HttpStatus.OK);
	}
	
	
	
	/*end-point é microsservicos é um API*/
	@ResponseBody
	@PostMapping(value = "/salvarPf")
	public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws ExceptionMentoriaJava{
		
		if (pessoaFisica == null) {
			throw new ExceptionMentoriaJava("Pessoa fisica não pode ser NULL");
		}
		
		if(pessoaFisica.getTipoPessoa() == null) {
			
			pessoaFisica.setTipoPessoa(TipoPessoa.FISICA.name());
			
		}
		
		
		if (pessoaFisica.getId_pessoa() == null && pessoaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
			throw new ExceptionMentoriaJava("Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
		}
		
		
		if (!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
			throw new ExceptionMentoriaJava("CPF : " + pessoaFisica.getCpf() + " está inválido.");
		}
		
		
			pessoaFisica = pessoaservice.salvarPessoaFisica(pessoaFisica);
		
		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
	}
	
	
}
	





















