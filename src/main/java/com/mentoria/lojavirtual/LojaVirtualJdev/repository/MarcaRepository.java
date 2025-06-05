
package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.MarcaProduto;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaProduto, Long>{

	@Query("select m from MarcaProduto m where upper(trim(m.nome)) like %?1% and empresa_id = ?2")
	List<MarcaProduto> buscarMarcaDesc(String nome, Long id_pessoa);
	
	
}

