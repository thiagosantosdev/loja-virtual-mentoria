package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", initialValue = 1, allocationSize = 1)
public class NotaFiscalVenda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
	private Long id_nota_fiscal_venda;
	private String numero;
	private String serie;
	private String tipo;
	@Column(columnDefinition = "text")
	private String xml;
	@Column(columnDefinition = "text")
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
