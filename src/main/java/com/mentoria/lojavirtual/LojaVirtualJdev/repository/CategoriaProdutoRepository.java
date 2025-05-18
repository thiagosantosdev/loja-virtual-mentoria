package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {

	@Query("select a from CategoriaProduto a where upper(trim(a.nome_categ_prod)) like %?1% and empresa_id = ?2")
	public List<CategoriaProduto> existeCategoria(String nome_categ_prod, Long id_pessoa);
	/*
	@Query(nativeQuery = true, value = "select count(1) > 0 from categoria_produto where upper(trim(nome_categoria_produto)) = upper(trim(?1)) and empresa_id = ?2")
	public boolean existeCategoria(String nome_categ_prod, Long id_pessoa);
	
	
	@Query("select count(1) > 0 from categoria_produto a where upper(trim(getNome_categ_prod)) = upper(trim(?1)) and empresa_id = ?2")
	public List<CategoriaProduto> existeCategoriaa(String nome_categ_prod, Long id_pessoa);
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from categoria_produto where upper(trim(getNome_categoria_produto)) = upper(trim(?1)) and empresa_id = ?2")
	public List<CategoriaProduto> existeCategoria3(String nome_categ_prod, Long id_pessoa);
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from categoria_produto where upper(trim(nome_categoria_produto)) = upper(trim(?1))")
	public boolean existeCategoria2(String nome_categ_prod);
     */
	@Query("select c from CategoriaProduto c where upper(trim(c.nome_categ_prod)) like %?1%")
	public List<CategoriaProduto> buscarCategoriaDesc(String nome_categ_prod);
}
 
            

