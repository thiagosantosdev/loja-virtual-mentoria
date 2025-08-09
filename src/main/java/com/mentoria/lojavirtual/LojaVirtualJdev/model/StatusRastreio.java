package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "venda_compra_loja_virt_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_loja_virt_id_fk"))
	private VendaCompraLojaVirtual vd_cp_lj_virt;
	
	@JsonIgnore
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;

	
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
	
	public VendaCompraLojaVirtual getVd_cp_lj_virt() {
		return vd_cp_lj_virt;
	}
	public PessoaJuridica getEmpresa() {
		return empresa;
	}
	public void setVd_cp_lj_virt(VendaCompraLojaVirtual vd_cp_lj_virt) {
		this.vd_cp_lj_virt = vd_cp_lj_virt;
	}
	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
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
