package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridica, Long>{
	
	@Query(value = "select pj from PessoaJuridica pj where upper(trim(pj.nome_pessoa)) like %?1%")
	public List<PessoaJuridica> pesquisaPorNomePJ(String nome_pessoa);
	
	@Query(value = "select pj from PessoaJuridica pj where pj.email = ?1")
	public PessoaJuridica existeEmailCadastrado(String email);
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1") /*Exibe um cadastro de CNPJ*/
	public PessoaJuridica existeCnpjCadastrado(String cnpj);

	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public List<PessoaJuridica> existeCnpjCadastradoList(String cnpj); /*Exibe lista de CNPJs iguais cadastrados*/
	
	@Query(value = "select pj from PessoaJuridica pj where pj.insc_estadual = ?1")
	public PessoaJuridica existeInscEstadual(String insc_estadual);  /*Exibe um cadastro de inscrição estadual*/
	
	@Query(value = "select pj from PessoaJuridica pj where pj.insc_estadual = ?1") /*Exibe uma lista de inscrição estadual iguais*/
	public List<PessoaJuridica> existeInscEstadualList(String insc_estadual);

	@Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
	public PessoaFisica existeCpfCadastrado(String cpf); /*Exibe um cadastro de CPF*/
	
	@Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
	public List<PessoaFisica> existeCpfCadastradoList(String cpf); /*Exibe lista de CPF iguais cadastrados*/
	
}

