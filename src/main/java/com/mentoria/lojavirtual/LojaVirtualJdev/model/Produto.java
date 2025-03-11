package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private Long id_produto;
	
	@Column(nullable = false)
	private String tipo_unidade;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;

	@Column(columnDefinition = "text", length = 2000, nullable = false)
	private String descricao;
	// private  Nota_item_produto  nota_item_produto;
	/**Nota item nota produto - ASSOCIAR/ **/
	
	@Column(nullable = false)
	private Double peso;

	@Column(nullable = false)
	private Double largura;

	@Column(nullable = false)
	private Double altura;

	@Column(nullable = false)
	private Double profundidade;

	@Column(nullable = false)
	private BigDecimal valorVenda = BigDecimal.ZERO;

	@Column(nullable = false)
	private Integer qtdEstoque = 0;

	private Integer qtd_alerta_estoque = 0;
	
	private String link_youtube;
	
	private Integer qtd_clique = 0;
	
	private Boolean alerta_qtd_estoque = Boolean.FALSE;
	


	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;

	
	public Long getId_produto() {
		return id_produto;
	}
	public String getTipo_unidade() {
		return tipo_unidade;
	}
	public String getNome() {
		return nome;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public String getDescricao() {
		return descricao;
	}
	public Double getPeso() {
		return peso;
	}
	public Double getLargura() {
		return largura;
	}
	public Double getAltura() {
		return altura;
	}
	public Double getProfundidade() {
		return profundidade;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public Integer getQtd_alerta_estoque() {
		return qtd_alerta_estoque;
	}
	public String getLink_youtube() {
		return link_youtube;
	}
	public Integer getQtd_clique() {
		return qtd_clique;
	}
	public Boolean getAlerta_qtd_estoque() {
		return alerta_qtd_estoque;
	}
	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}
	public void setTipo_unidade(String tipo_unidade) {
		this.tipo_unidade = tipo_unidade;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public void setLargura(Double largura) {
		this.largura = largura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public void setQtd_alerta_estoque(Integer qtd_alerta_estoque) {
		this.qtd_alerta_estoque = qtd_alerta_estoque;
	}
	public void setLink_youtube(String link_youtube) {
		this.link_youtube = link_youtube;
	}
	public void setQtd_clique(Integer qtd_clique) {
		this.qtd_clique = qtd_clique;
	}
	public void setAlerta_qtd_estoque(Boolean alerta_qtd_estoque) {
		this.alerta_qtd_estoque = alerta_qtd_estoque;
	}
	
	
	public PessoaJuridica getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_produto == null) ? 0 : id_produto.hashCode());
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
		Produto other = (Produto) obj;
		if (id_produto == null) {
			if (other.id_produto != null)
				return false;
		} else if (!id_produto.equals(other.id_produto))
			return false;
		return true;
	}
	
	
	
}
