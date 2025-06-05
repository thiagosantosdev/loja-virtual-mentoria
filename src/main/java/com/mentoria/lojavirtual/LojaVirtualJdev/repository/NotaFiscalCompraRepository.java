package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.NotaFiscalCompra;

@Repository
public interface NotaFiscalCompraRepository extends JpaRepository<NotaFiscalCompra, Long>{

	@Query("select a from NotaFiscalCompra a where upper(trim(a.descricao_obs)) like %?1% and empresa_id = ?2")
	public List<NotaFiscalCompra> existeCategoria(String descricao_obs, Long id_pessoa);
	
	@Query("select a from NotaFiscalCompra a where upper(trim(a.descricao_obs)) like %?1%")
	List<NotaFiscalCompra> buscaNotaDesc(String desc);
	
	@Query("select a from NotaFiscalCompra a where a.pessoa.id = ?1")
	List<NotaFiscalCompra> buscaNotaPorPessoa(Long id_pessoa);
	
	@Query("select a from NotaFiscalCompra a where a.contaPagar.id = ?1")
	List<NotaFiscalCompra> buscaNotaContaPagar(Long idContaPagar);
	
	@Query("select a from NotaFiscalCompra a where a.empresa.id = ?1")
	List<NotaFiscalCompra> buscaNotaPorEmpresa(Long idEmpresa);
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(nativeQuery = true, value = "delete from nota_item_produto where id_nota_fiscal_compra = ?1")
	void deleteItemNotaFiscalCompra(Long idNotaItemProd);
	
	
	
	
}
