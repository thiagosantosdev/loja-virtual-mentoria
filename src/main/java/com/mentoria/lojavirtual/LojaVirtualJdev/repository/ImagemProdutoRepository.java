package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.ImagemProduto;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long>{
	
	@Query("select i from ImagemProduto i where i.produto.id_produto = ?1")
	List<ImagemProduto> buscaImagemProduto(Long id_produto);
	
	@Query(nativeQuery = true, value = "delete from imagem_produto where produto_id = ?1)")
	void deleteImagem(Long id_produto);
	

}
