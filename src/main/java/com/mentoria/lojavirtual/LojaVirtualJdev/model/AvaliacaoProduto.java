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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "avaliacao_produto")
@SequenceGenerator(name = "seq_avaliacao_produto", sequenceName = "seq_avaliacao_produto", initialValue = 1, allocationSize = 1)
public class AvaliacaoProduto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_avaliacao_produto")
	private Long id_avaliacao_produto;
	
	@NotEmpty(message = "Informe uma descrição para a avaliação!")
	@Column(nullable = false)
	private String descricao;
	
	@Min(value = 1, message = "A nota deve ser no mínimo 1!")
	@Max(value = 10, message = "A nota deve ser no máximo 10!")
	@Column(nullable = false)
	private Integer nota;
	
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaFisica pessoa;
	
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;
	
	
	

	public Long getId_avaliacao_produto() {
		return id_avaliacao_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}

	
	public Produto getProduto() {
		return produto;
	}

	public void setId_avaliacao_produto(Long id_avaliacao_produto) {
		this.id_avaliacao_produto = id_avaliacao_produto;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	
	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public PessoaJuridica getEmpresa() {
		return empresa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_avaliacao_produto == null) ? 0 : id_avaliacao_produto.hashCode());
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
		AvaliacaoProduto other = (AvaliacaoProduto) obj;
		if (id_avaliacao_produto == null) {
			if (other.id_avaliacao_produto != null)
				return false;
		} else if (!id_avaliacao_produto.equals(other.id_avaliacao_produto))
			return false;
		return true;
	}
	
	
	
	
	
	

}
