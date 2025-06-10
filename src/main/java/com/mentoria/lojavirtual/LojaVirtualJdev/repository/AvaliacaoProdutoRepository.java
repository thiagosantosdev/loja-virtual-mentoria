package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.AvaliacaoProduto;

@Repository
public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Long>{

	@Query(value = "select a from AvaliacaoProduto a where a.produto.id_produto = ?1")
	List<AvaliacaoProduto> avaliacaoProduto(Long id_avaliacao_produto);
	
	@Query(value = "select a from AvaliacaoProduto a where a.produto.id_produto = ?1 and a.pessoa.id_pessoa = ?2")
	List<AvaliacaoProduto> avaliacaoProdutoPessoa(Long id_produto, Long id_pessoa);
	
	@Query(value = "select a from AvaliacaoProduto a where a.pessoa.id_pessoa = ?1")
	List<AvaliacaoProduto> avaliacaoPessoa(Long id_pessoa);
}
