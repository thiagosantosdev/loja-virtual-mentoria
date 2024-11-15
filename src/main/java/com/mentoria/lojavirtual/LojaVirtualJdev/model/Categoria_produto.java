package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name = "categoria_produto")
@SequenceGenerator(name = "seq_categoria_produto", sequenceName = "seq_categoria_produto", allocationSize = 1, initialValue = 1)
public class Categoria_produto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria_produto")
	private Long id_categ_prod;
	
	@Column(name = "nome_categoria_produto", nullable = false)
	private String nome_categ_prod;
	
	

	public Long getId_categ_prod() {
		return id_categ_prod;
	}

	public void setId_categ_prod(Long id_categ_prod) {
		this.id_categ_prod = id_categ_prod;
	}

	public String getNome_categ_prod() {
		return nome_categ_prod;
	}

	public void setNome_categ_prod(String nome_categ_prod) {
		this.nome_categ_prod = nome_categ_prod;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_categ_prod == null) ? 0 : id_categ_prod.hashCode());
		return result;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria_produto other = (Categoria_produto) obj;
		if (id_categ_prod == null) {
			if (other.id_categ_prod != null)
				return false;
		} else if (!id_categ_prod.equals(other.id_categ_prod))
			return false;
		return true;
	}
	
	
	
	
	

}
