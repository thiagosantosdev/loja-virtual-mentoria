package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.VendaCompraLojaVirtual;

@Repository
public interface VendaCompraLojaVirtualRepository extends JpaRepository<VendaCompraLojaVirtual, Long>{

	@Query(value = "select a from VendaCompraLojaVirtual a where a.id = ?1 and a.excluido = false")
	VendaCompraLojaVirtual findByIdExclusao(Long id);
	
	@Query(value="select i.vd_cp_lj_virt from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and i.produto.id = ?1")
	List<VendaCompraLojaVirtual> vendaPorProduto(Long id_produto);

	
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and upper(trim(i.produto.nome)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorNomeProduto(String valor);
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and upper(trim(i.vd_cp_lj_virt.pessoa.nome_pessoa)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorNomeCliente(String nomepessoa);
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and upper(trim(i.vd_cp_lj_virt.endereco_cobranca.ruaLogradouro)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorEnderecoCobranca(String enderecocobranca);
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and upper(trim(i.vd_cp_lj_virt.endereco_entrega.ruaLogradouro)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorEnderecoEntrega(String endereco_entrega);  
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and i.vd_cp_lj_virt.data_venda >= ?1 and i.vd_cp_lj_virt.data_venda <= ?2")
	List<VendaCompraLojaVirtual> consultaVendaFaixaData(Date data1, Date data2); 
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and upper(trim(i.vd_cp_lj_virt.pessoa.cpf)) like %?1%")
	List<VendaCompraLojaVirtual> vendaPorCpfClienteLike(String cpf); 
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and upper(trim(i.vd_cp_lj_virt.pessoa.cpf)) = ?1")
	List<VendaCompraLojaVirtual> vendaPorCpfClienteIgual(String cpf); 
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and upper(trim(i.vd_cp_lj_virt.pessoa.nome_pessoa)) like %?1% and i.vd_cp_lj_virt.pessoa.cpf = ?2")
	List<VendaCompraLojaVirtual> consultaVendaPorNomeCpfCliente(String nomepessoa, String cpf);
	
	@Query(value="select distinct (i.vd_cp_lj_virt) from ItemVendaLoja i where "
			+ " i.vd_cp_lj_virt.excluido = false and i.vd_cp_lj_virt.pessoa.id_pessoa = ?1")
	List<VendaCompraLojaVirtual> vendaPorCliente(Long id_pessoa); 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
