package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.StatusRastreio;

public interface StatusRastreioRepository extends JpaRepository<StatusRastreio, Long>{

	@Query(value = "select s from StatusRastreio s where s.vd_cp_lj_virt.id = ?1 order by s.id")
	public List<StatusRastreio> listaRastreioVenda(Long idVenda);
	
}
