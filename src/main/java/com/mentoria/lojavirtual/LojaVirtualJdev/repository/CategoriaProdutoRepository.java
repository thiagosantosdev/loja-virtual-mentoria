package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {

	@Query(nativeQuery = true, value = "select count(1) > 0 from categoria_produto where upper(trim(nome_categoria_produto)) = upper(trim(?1))")
	public boolean existeCategoria(String nome_categ_prod);

	@Query("select c from CategoriaProduto c where upper(trim(c.nome_categ_prod)) like %?1%")
	public List<CategoriaProduto> buscarCategoriaDesc(String nome_categ_prod);
}
 
            

