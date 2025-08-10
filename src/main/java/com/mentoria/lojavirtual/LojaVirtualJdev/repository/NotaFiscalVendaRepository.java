package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.NotaFiscalVenda;

@Repository
public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, Long> {

	@Query(value = "select n from NotaFiscalVenda n where n.vd_cp_lj_virt.id = ?1")
	List<NotaFiscalVenda> buscaNotaPorVenda(Long id);
	
	@Query(value = "select n from NotaFiscalVenda n where n.vd_cp_lj_virt.id = ?1")
	NotaFiscalVenda buscaNotaPorVendaUnica(Long id);
	
}
