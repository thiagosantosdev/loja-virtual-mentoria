package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "form_pag")
@SequenceGenerator(name = "seq_form_pag", sequenceName = "seq_form_pag", initialValue = 1, allocationSize = 1)
public class FormaPagamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_form_pag")
	private Long id_form_pag;
	
	@Column(nullable = false)
	private String descricao;
	
	
	
	public Long getId_form_pag() {
		return id_form_pag;
	}
	public void setId_form_pag(Long id_form_pag) {
		this.id_form_pag = id_form_pag;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_form_pag == null) ? 0 : id_form_pag.hashCode());
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
		FormaPagamento other = (FormaPagamento) obj;
		if (id_form_pag == null) {
			if (other.id_form_pag != null)
				return false;
		} else if (!id_form_pag.equals(other.id_form_pag))
			return false;
		return true;
	}
	
	
	
}
