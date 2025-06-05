package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.NotaItemProduto;

@Repository
public interface NotaItemProdutoRepository extends JpaRepository<NotaItemProduto,Long> {

	@Query("select a from NotaItemProduto a where a.produto.id = ?1 and a.nota_fiscal_compra.id = ?2")
	List<NotaItemProduto> buscaNotaItemPorProdutoNota(Long idProduto, Long idNotaFiscal);
	
	@Query("select a from NotaItemProduto a where a.produto.id = ?1")
	List<NotaItemProduto> buscaNotaItemPorProduto(Long idProduto);
	
	@Query("select a from NotaItemProduto a where a.empresa.id = ?2")
	List<NotaItemProduto> buscaNotaItemPorEmpresa(Long idEmpresa);
	
	@Query("select a from NotaItemProduto a where a.empresa.id = ?2")
	List<NotaItemProduto> buscaNotaItemPorempresa(Long idEmpresa);
	
}
