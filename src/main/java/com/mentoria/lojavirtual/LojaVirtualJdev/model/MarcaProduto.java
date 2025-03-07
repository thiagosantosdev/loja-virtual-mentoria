package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "marca-produto")
@SequenceGenerator(name = "seq_marca_produto", sequenceName = "seq_marca_produto", allocationSize = 1, initialValue = 1)
public class MarcaProduto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca_produto")
	private Long id_marca_prod;

	@Column(nullable = false)
	private String nome;
	
	
	

	public Long getId_marca_prod() {
		return id_marca_prod;
	}

	public void setId_marca_prod(Long id_marca_prod) {
		this.id_marca_prod = id_marca_prod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
