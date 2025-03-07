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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", initialValue = 1, allocationSize = 1)
public class NotaFiscalVenda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
	private Long id_nota_fiscal_venda;
	
	@Column(nullable = false)
	private String numero;

	@Column(nullable = false)
	private String serie;

	@Column(nullable = false)
	private String tipo;

	@Column(columnDefinition = "text", nullable = false)
	private String xml;
	
	@Column(columnDefinition = "text", nullable = false)
	private String pdf;
	
	@OneToOne
	@JoinColumn(name = "venda_compra_loja_virt_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_loja_virt_id_fk"))
	private VendaCompraLojaVirtual vd_cp_lj_virt;
	
	
	public Long getId_nota_fiscal_venda() {
		return id_nota_fiscal_venda;
	}
	public String getNumero() {
		return numero;
	}
	public String getSerie() {
		return serie;
	}
	public String getTipo() {
		return tipo;
	}
	public String getXml() {
		return xml;
	}
	public String getPdf() {
		return pdf;
	}
	public void setId_nota_fiscal_venda(Long id_nota_fiscal_venda) {
		this.id_nota_fiscal_venda = id_nota_fiscal_venda;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_nota_fiscal_venda == null) ? 0 : id_nota_fiscal_venda.hashCode());
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
		NotaFiscalVenda other = (NotaFiscalVenda) obj;
		if (id_nota_fiscal_venda == null) {
			if (other.id_nota_fiscal_venda != null)
				return false;
		} else if (!id_nota_fiscal_venda.equals(other.id_nota_fiscal_venda))
			return false;
		return true;
	}
	

}
