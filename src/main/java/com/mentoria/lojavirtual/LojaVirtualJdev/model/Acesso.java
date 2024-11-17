package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "acesso")
@SequenceGenerator(name = "seq_acesso", sequenceName = "seq_acesso", initialValue = 1, allocationSize = 1)
public class Acesso implements GrantedAuthority {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
	private Long id_Acesso;
	
	@Column(nullable = false)
	private String descricao;
	
	
	@Override
	public String getAuthority() {

		return descricao;
	}


	public Long getId_Acesso() {
		return id_Acesso;
	}


	public void setId_Acesso(Long id_Acesso) {
		this.id_Acesso = id_Acesso;
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
		result = prime * result + ((id_Acesso == null) ? 0 : id_Acesso.hashCode());
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
		Acesso other = (Acesso) obj;
		if (id_Acesso == null) {
			if (other.id_Acesso != null)
				return false;
		} else if (!id_Acesso.equals(other.id_Acesso))
			return false;
		return true;
	}
	
	
	
	

}
