package com.mentoria.lojavirtual.LojaVirtualJdev.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1, allocationSize = 1)
public class Usuario implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id_usuario;
	
	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataSenha;
	
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaFisica pessoa;
	
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;

	


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_acesso", 
		uniqueConstraints = @UniqueConstraint (columnNames = {"usuario_id", "acesso_id"} ,
		name = "unique_acesso_user"),
	
	   joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", table = "usuario", 
	   unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)), 
	   
	inverseJoinColumns = @JoinColumn(name = "acesso_id", 
						unique = false, referencedColumnName = "id_Acesso", table = "acesso",
						foreignKey = @ForeignKey(name = "aesso_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Acesso> acessos;
	
	
	
	
	
	
	
	
	
	
	public PessoaJuridica getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	public Long getId_usuario() {
		return id_usuario;
	}
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	public Date getDataSenha() {
		return dataSenha;
	}
	public PessoaFisica getPessoa() {
		return pessoa;
	}
	
	public List<Acesso> getAcessos() {
		return acessos;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setDataSenha(Date dataSenha) {
		this.dataSenha = dataSenha;
	}
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}
	
	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.acessos;
	}
	@Override
	public String getPassword() {

		return this.senha;
	}
	@Override
	public String getUsername() {

		return this.login;
	}
	
	@Override
    public boolean isAccountNonExpired() {
       
		return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       
    	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
    	return true;
    }

    @Override
    public boolean isEnabled() {
        
    	return true;
    }
	
}
