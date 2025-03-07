package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "nota_fiscal_compra")
@SequenceGenerator(name = "seq_nota_fiscal_compra", sequenceName = "seq_nota_fiscal_compra", initialValue = 1, allocationSize = 1)
public class NotaFiscalCompra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_compra")
	private Long id_nota_fiscal_compra;
	
	@Column(nullable = false)
	private String numero_nota;

	@Column(nullable = false)
	private String serie_nota;

	private String descricao_obs;
	
	@Column(nullable = false)
	private BigDecimal valor_total;
	
	private BigDecimal valor_desconto;
	
	@Column(nullable = false)
	private BigDecimal valor_icms;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data_compra;
	
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaJuridica pessoa;
	
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;
	
	@ManyToOne
	@JoinColumn(name = "conta_pagar_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_pagar_fk"))
	private ContaPagar contaPagar;
	
	

	



	public Long getId_nota_fiscal_compra() {
		return id_nota_fiscal_compra;
	}



	public String getNumero_nota() {
		return numero_nota;
	}



	public String getSerie_nota() {
		return serie_nota;
	}



	public String getDescricao_obs() {
		return descricao_obs;
	}



	public BigDecimal getValor_total() {
		return valor_total;
	}



	public BigDecimal getValor_desconto() {
		return valor_desconto;
	}



	public BigDecimal getValor_icms() {
		return valor_icms;
	}



	public Date getData_compra() {
		return data_compra;
	}



	public PessoaJuridica getPessoa() {
		return pessoa;
	}



	public PessoaJuridica getEmpresa() {
		return empresa;
	}



	public ContaPagar getContaPagar() {
		return contaPagar;
	}



	public void setId_nota_fiscal_compra(Long id_nota_fiscal_compra) {
		this.id_nota_fiscal_compra = id_nota_fiscal_compra;
	}



	public void setNumero_nota(String numero_nota) {
		this.numero_nota = numero_nota;
	}



	public void setSerie_nota(String serie_nota) {
		this.serie_nota = serie_nota;
	}



	public void setDescricao_obs(String descricao_obs) {
		this.descricao_obs = descricao_obs;
	}



	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}



	public void setValor_desconto(BigDecimal valor_desconto) {
		this.valor_desconto = valor_desconto;
	}



	public void setValor_icms(BigDecimal valor_icms) {
		this.valor_icms = valor_icms;
	}



	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}



	public void setPessoa(PessoaJuridica pessoa) {
		this.pessoa = pessoa;
	}



	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}



	public void setContaPagar(ContaPagar contaPagar) {
		this.contaPagar = contaPagar;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_nota_fiscal_compra == null) ? 0 : id_nota_fiscal_compra.hashCode());
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
		NotaFiscalCompra other = (NotaFiscalCompra) obj;
		if (id_nota_fiscal_compra == null) {
			if (other.id_nota_fiscal_compra != null)
				return false;
		} else if (!id_nota_fiscal_compra.equals(other.id_nota_fiscal_compra))
			return false;
		return true;
	}
	
	
	
	
}
