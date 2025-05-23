package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CNPJ;



@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaJuridica extends Pessoa  {

	
		private static final long serialVersionUID = 1L;
		@CNPJ(message = "CNPJ inválido!")
		@Column(nullable = false)
		private String cnpj;
		
		@Column(nullable = false)
		private String insc_estadual;
		
		private String insc_municipal;
		
		@Column(nullable = false)
		private String nome_fantasia;

		@Column(nullable = false)
		private String razao_social;
		
		private String categoria;
		
		@ManyToOne(targetEntity = Pessoa.class)
		@JoinColumn(name = "empresa_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
		private Pessoa empresa;
		
		

		public Pessoa getEmpresa() {
			return empresa;
		}

		public void setEmpresa(Pessoa empresa) {
			this.empresa = empresa;
		}

		public String getCnpj() {
			return cnpj;
		}

		public String getInsc_estadual() {
			return insc_estadual;
		}

		public String getInsc_municipal() {
			return insc_municipal;
		}

		public String getNome_fantasia() {
			return nome_fantasia;
		}

		public String getRazao_social() {
			return razao_social;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public void setInsc_estadual(String insc_estadual) {
			this.insc_estadual = insc_estadual;
		}

		public void setInsc_municipal(String insc_municipal) {
			this.insc_municipal = insc_municipal;
		}

		public void setNome_fantasia(String nome_fantasia) {
			this.nome_fantasia = nome_fantasia;
		}

		public void setRazao_social(String razao_social) {
			this.razao_social = razao_social;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		
		
		
		
}
