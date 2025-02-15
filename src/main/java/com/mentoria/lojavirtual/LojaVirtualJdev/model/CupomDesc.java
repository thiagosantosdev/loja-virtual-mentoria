package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cupom_desc")
@SequenceGenerator(name = "seq_cupom_desc", sequenceName = "seq_cupom_desc", allocationSize = 1, initialValue = 1)
public class CupomDesc implements Serializable{

	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desc")
	private Long id_cupom_desc;
	
	@Column(nullable = false)
	private String cod_desc;
	
	private BigDecimal valor_real_desc;
	
	private BigDecimal valor_porcent;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data_validade;
	
	
	
	
	public Long getId_cupom_desc() {
		return id_cupom_desc;
	}
	public void setId_cupom_desc(Long id_cupom_desc) {
		this.id_cupom_desc = id_cupom_desc;
	}
	public String getCod_desc() {
		return cod_desc;
	}
	public void setCod_desc(String cod_desc) {
		this.cod_desc = cod_desc;
	}
	public BigDecimal getValor_real_desc() {
		return valor_real_desc;
	}
	public void setValor_real_desc(BigDecimal valor_real_desc) {
		this.valor_real_desc = valor_real_desc;
	}
	public BigDecimal getValor_porcent() {
		return valor_porcent;
	}
	public void setValor_porcent(BigDecimal valor_porcent) {
		this.valor_porcent = valor_porcent;
	}
	public Date getData_validade() {
		return data_validade;
	}
	public void setData_validade(Date data_validade) {
		this.data_validade = data_validade;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_cupom_desc == null) ? 0 : id_cupom_desc.hashCode());
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
		CupomDesc other = (CupomDesc) obj;
		if (id_cupom_desc == null) {
			if (other.id_cupom_desc != null)
				return false;
		} else if (!id_cupom_desc.equals(other.id_cupom_desc))
			return false;
		return true;
	}
	
	
}
