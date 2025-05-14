package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long>{

	@Query(value = "select pf from PessoaFisica pf where upper(trim(pf.nome_pessoa)) like %?1%")
	public List<PessoaFisica> pesquisaPorNomePF(String nome_pessoa);
	
	@Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
	public List<PessoaFisica> pesquisaPorCpfPF(String cpf); /*Exibe um cadastro de CPF ou lista*/
	
	
	
}
