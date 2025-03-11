package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa {

	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private String tipo_pessoa;
	
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;


	public String getCpf() {
		return cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getTipo_pessoa() {
		return tipo_pessoa;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setTipo_pessoa(String tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}

	public PessoaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
