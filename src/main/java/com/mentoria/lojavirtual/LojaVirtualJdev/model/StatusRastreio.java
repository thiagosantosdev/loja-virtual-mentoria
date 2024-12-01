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
@Table(name = "status_rastreio")
@SequenceGenerator(name = "seq_status_rastreio", sequenceName = "seq_status_rastreio", initialValue = 1, allocationSize = 1)
public class StatusRastreio implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status_rastreio")
	private Long id_status_rastreio;
	private String centro_distribuicao;
	private String cidade;
	private String estado;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "venda_compra_loja_virt_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_loja_virt_id_fk"))
	private VendaCompraLojaVirtual vd_cp_lj_virt;
	
	
	public Long getId_status_rastreio() {
		return id_status_rastreio;
	}
	public String getCentro_distribuicao() {
		return centro_distribuicao;
	}
	public String getCidade() {
		return cidade;
	}
	public String getEstado() {
		return estado;
	}
	public String getStatus() {
		return status;
	}
	public void setId_status_rastreio(Long id_status_rastreio) {
		this.id_status_rastreio = id_status_rastreio;
	}
	public void setCentro_distribuicao(String centro_distribuicao) {
		this.centro_distribuicao = centro_distribuicao;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_status_rastreio == null) ? 0 : id_status_rastreio.hashCode());
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
		StatusRastreio other = (StatusRastreio) obj;
		if (id_status_rastreio == null) {
			if (other.id_status_rastreio != null)
				return false;
		} else if (!id_status_rastreio.equals(other.id_status_rastreio))
			return false;
		return true;
	}
	
	
	
	



}
