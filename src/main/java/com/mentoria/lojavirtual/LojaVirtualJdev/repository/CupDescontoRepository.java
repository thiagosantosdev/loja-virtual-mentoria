package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.CupomDesc;

@Repository
public interface CupDescontoRepository extends JpaRepository<CupomDesc, Long>{
	@Query(value = "select c from CupomDesc c where c.empresa.id_pessoa = ?1")
	public List<CupomDesc> cupDescontoPorEmpresa(Long id_pessoa);
	
}
