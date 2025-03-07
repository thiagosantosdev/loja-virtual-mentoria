package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "imagem_produto")
@SequenceGenerator(name = "seq_imagem_produto", sequenceName = "seq_imagem_produto", initialValue = 1, allocationSize = 1)
public class ImagemProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imagem_produto")
	private Long id_imagem;
	
	@Column(columnDefinition = "text", nullable = false)
	private String imagem_original;
	
	@Column(columnDefinition = "text", nullable = false)
	private String imagem_miniatura;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;

	
	
	public Long getId_imagem() {
		return id_imagem;
	}

	public String getImagem_original() {
		return imagem_original;
	}

	public String getImagem_miniatura() {
		return imagem_miniatura;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setId_imagem(Long id_imagem) {
		this.id_imagem = id_imagem;
	}

	public void setImagem_original(String imagem_original) {
		this.imagem_original = imagem_original;
	}

	public void setImagem_miniatura(String imagem_miniatura) {
		this.imagem_miniatura = imagem_miniatura;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_imagem == null) ? 0 : id_imagem.hashCode());
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
		ImagemProduto other = (ImagemProduto) obj;
		if (id_imagem == null) {
			if (other.id_imagem != null)
				return false;
		} else if (!id_imagem.equals(other.id_imagem))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
