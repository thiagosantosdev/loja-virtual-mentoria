package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_venda_loja")
@SequenceGenerator(name = "seq_item_venda_loja", sequenceName = "seq_item_venda_loja", initialValue = 1, allocationSize = 1)
public class ItemVendaLoja implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_venda_loja")
	private Long id_item_venda_loja;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "venda_compra_loja_virt_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_loja_virt_fk"))
	private VendaCompraLojaVirtual vd_cp_lj_virt;
	
	
	
	private Double quantidade;

	public Long getId_item_venda_loja() {
		return id_item_venda_loja;
	}

	public Produto getProduto() {
		return produto;
	}

	public VendaCompraLojaVirtual getVd_cp_lj_virt() {
		return vd_cp_lj_virt;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setId_item_venda_loja(Long id_item_venda_loja) {
		this.id_item_venda_loja = id_item_venda_loja;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setVd_cp_lj_virt(VendaCompraLojaVirtual vd_cp_lj_virt) {
		this.vd_cp_lj_virt = vd_cp_lj_virt;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_item_venda_loja == null) ? 0 : id_item_venda_loja.hashCode());
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
		ItemVendaLoja other = (ItemVendaLoja) obj;
		if (id_item_venda_loja == null) {
			if (other.id_item_venda_loja != null)
				return false;
		} else if (!id_item_venda_loja.equals(other.id_item_venda_loja))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}