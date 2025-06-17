package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vd_cp_lj_virt")
@SequenceGenerator(name = "seq_vd_cp_lj_virt", sequenceName = "seq_vd_cp_lj_virt", initialValue = 1, allocationSize = 1)
public class VendaCompraLojaVirtual implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vd_cp_lj_virt")
	private Long id;
    
	@NotNull(message = "A pessoa compradora deve ser informada!")
	@ManyToOne(targetEntity = PessoaFisica.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaFisica pessoa;

	@NotNull(message = "A empresa dona do registro deve ser informada!")
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;

	@NotNull(message = "A pessoa compradora deve ser informada!")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_entrega_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_entrega_fk"))
	private Endereco endereco_entrega;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_cobranca_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_cobranca_fk"))
	private Endereco endereco_cobranca;

	@Min(value = 1, message = "Valor total da venda é inválido!")
	@Column(nullable = false)
	private BigDecimal valor_total;

	private BigDecimal valor_desc;

	@NotNull(message = "A forma de pagamento deve ser informada!")
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "forma_pagamento_fk"))
	private FormaPagamento forma_pagamento;

	@JsonIgnoreProperties(allowGetters = true)
	//@NotNull(message = "A nota fiscal deve ser informada!")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nota_fiscal_venda_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "nota_fiscal_venda_fk"))
	private NotaFiscalVenda nota_fiscal_venda;

	@ManyToOne
	@JoinColumn(name = "cupom_desc_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cupom_desc_fk"))
	private CupomDesc cupom_desc;

	@Min(value = 5, message = "Valor do frete é inválido!")
	@NotNull(message = "O valor de frete deve ser informado!")
	@Column(nullable = false)
	private BigDecimal valor_frete;
	
	@Min(value = 1, message = "Dia de entrega é inválido!")
	@Column(nullable = false)
	private Integer dias_entrega;

	@NotNull(message = "A data da venda deve ser informada!")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data_venda;

	@NotNull(message = "A data da entrega deve ser informada!")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data_entrega;

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

	public Long getId() {
		return id;
	}

	public Endereco getEndereco_entrega() {
		return endereco_entrega;
	}

	public Endereco getEndereco_cobranca() {
		return endereco_cobranca;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public BigDecimal getValor_desc() {
		return valor_desc;
	}

	public FormaPagamento getForma_pagamento() {
		return forma_pagamento;
	}

	public NotaFiscalVenda getNota_fiscal_venda() {
		return nota_fiscal_venda;
	}

	public CupomDesc getCupom_desc() {
		return cupom_desc;
	}

	public BigDecimal getValor_frete() {
		return valor_frete;
	}

	public Integer getDias_entrega() {
		return dias_entrega;
	}

	public Date getData_venda() {
		return data_venda;
	}

	public Date getData_entrega() {
		return data_entrega;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEndereco_entrega(Endereco endereco_entrega) {
		this.endereco_entrega = endereco_entrega;
	}

	public void setEndereco_cobranca(Endereco endereco_cobranca) {
		this.endereco_cobranca = endereco_cobranca;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public void setValor_desc(BigDecimal valor_desc) {
		this.valor_desc = valor_desc;
	}

	public void setForma_pagamento(FormaPagamento forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}

	public void setNota_fiscal_venda(NotaFiscalVenda nota_fiscal_venda) {
		this.nota_fiscal_venda = nota_fiscal_venda;
	}

	public void setCupom_desc(CupomDesc cupom_desc) {
		this.cupom_desc = cupom_desc;
	}

	public void setValor_frete(BigDecimal valor_frete) {
		this.valor_frete = valor_frete;
	}

	public void setDias_entrega(Integer dias_entrega) {
		this.dias_entrega = dias_entrega;
	}

	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}

	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		VendaCompraLojaVirtual other = (VendaCompraLojaVirtual) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}