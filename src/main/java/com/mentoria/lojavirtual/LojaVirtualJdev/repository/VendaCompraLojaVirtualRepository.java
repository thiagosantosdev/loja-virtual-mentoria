package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.VendaCompraLojaVirtual;

@Repository
public interface VendaCompraLojaVirtualRepository extends JpaRepository<VendaCompraLojaVirtual, Long>{

	@Query(value = "select a from VendaCompraLojaVirtual a where a.id = ?1 and a.excluido = false")
	VendaCompraLojaVirtual findByIdExclusao(Long id);
	
	@Query(value="select i.vendaCompraLojaVirtual from ItemVendaLoja i where "
			+ " i.vendaCompraLojaVirtual.excluido = false and i.produto.id = ?1")
	List<VendaCompraLojaVirtual> vendaPorProduto(Long id_produto);

	@Query(value="select distinct (i.vendaCompraLojaVirtual) from ItemVendaLoja i where "
			+ " i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.produto.nome)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorNomeProduto(String valor);

	@Query(value="select distinct (i.vendaCompraLojaVirtual) from ItemVendaLoja i where "
			+ " i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vd_cp_lj_virt.pessoa.nome)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorNomeCliente(String nomepessoa);

	@Query(value="select distinct (i.vendaCompraLojaVirtual) from ItemVendaLoja i where "
			+ " i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vd_cp_lj_virt.endereco_cobranca.ruaLogradouro)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorEnderecoCobranca(String enderecocobranca);

	@Query(value="select distinct (i.vendaCompraLojaVirtual) from ItemVendaLoja i where "
			+ " i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vd_cp_lj_virt.endereco_entrega.ruaLogradouro)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorEnderecoEntrega(String endereco_entrega);
	
}
