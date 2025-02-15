package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa {

	
		private static final long serialVersionUID = 1L;
		
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
		
		
		
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public String getInsc_estadual() {
			return insc_estadual;
		}
		public void setInsc_estadual(String insc_estadual) {
			this.insc_estadual = insc_estadual;
		}
		public String getInsc_municipal() {
			return insc_municipal;
		}
		public void setInsc_municipal(String insc_municipal) {
			this.insc_municipal = insc_municipal;
		}
		public String getNome_fantasia() {
			return nome_fantasia;
		}
		public void setNome_fantasia(String nome_fantasia) {
			this.nome_fantasia = nome_fantasia;
		}
		public String getRazao_social() {
			return razao_social;
		}
		public void setRazao_social(String razao_social) {
			this.razao_social = razao_social;
		}
		public String getCategoria() {
			return categoria;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		
		
		
		
}
