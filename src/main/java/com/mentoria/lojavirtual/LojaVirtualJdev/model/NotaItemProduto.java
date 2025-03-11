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
@Table(name = "nota_item_produto")
@SequenceGenerator(name = "seq_nota_item_produto", sequenceName = "seq_nota_item_produto", initialValue = 1, allocationSize = 1)
public class NotaItemProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_item_produto")
	private Long idNotaItemProd;
	
	@Column(nullable = false)
	private Double quantidade;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "nota_fiscal_compra_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "nota_fiscal_compra_fk"))
	private NotaFiscalCompra nota_fiscal_compra;
	
	

	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;


	public Long getIdNotaItemProd() {
		return idNotaItemProd;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public NotaFiscalCompra getNota_fiscal_compra() {
		return nota_fiscal_compra;
	}

	public void setIdNotaItemProd(Long idNotaItemProd) {
		this.idNotaItemProd = idNotaItemProd;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setNota_fiscal_compra(NotaFiscalCompra nota_fiscal_compra) {
		this.nota_fiscal_compra = nota_fiscal_compra;
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
		result = prime * result + ((idNotaItemProd == null) ? 0 : idNotaItemProd.hashCode());
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
		NotaItemProduto other = (NotaItemProduto) obj;
		if (idNotaItemProd == null) {
			if (other.idNotaItemProd != null)
				return false;
		} else if (!idNotaItemProd.equals(other.idNotaItemProd))
			return false;
		return true;
	}
	
	
	
}
