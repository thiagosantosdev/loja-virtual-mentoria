package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.ContaPagar;

@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

	@Query("select c from ContaPagar c where upper(trim(c.descricao)) like %?1% and empresa_id = ?2")
	public List<ContaPagar> buscaContaDesc(String desc, Long id_pessoa);
	
	
	@Query("SELECT c FROM ContaPagar c JOIN c.pessoa p WHERE p.id_pessoa = ?1")
	List<ContaPagar> buscaContaPorPessoa(Long idPessoa);

	
	

	
	@Query("select c from ContaPagar c JOIN  c.pessoa_fornecedor p where p.id_pessoa = ?1")
	List<ContaPagar> buscaContaPorFornecedor(Long pessoa_fornecedor);
	
	@Query("select c from ContaPagar c JOIN  c.empresa p where p.id_pessoa = ?1")
	List<ContaPagar> buscaContaPorEmpresa(Long empresa);
	
	
}

