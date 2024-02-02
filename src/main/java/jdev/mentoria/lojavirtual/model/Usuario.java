package jdev.mentoria.lojavirtual.model;

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
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails, Serializable  {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAtualSenha;
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private Pessoa pessoa;
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private Pessoa empresa;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "usuarios_role",
		uniqueConstraints = @UniqueConstraint(
			columnNames = {"usuario_id", "role_id"},
			name = "unique_role_user"
		),
		joinColumns = @JoinColumn(
			name = "usuario_id",
			unique = false,
			referencedColumnName = "id",
			table = "usuario",
			foreignKey = @ForeignKey(
				name = "usuario_fk",
				value = ConstraintMode.CONSTRAINT
			)
		),
		inverseJoinColumns = @JoinColumn(
			name = "role_id",
			unique = false,
			referencedColumnName = "id",
			table = "role",
			foreignKey = @ForeignKey(
				name = "acesso_fk",
				value = ConstraintMode.CONSTRAINT
			)
		)
	)
	private List<Roles> role;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataAtualSenha() {
		return dataAtualSenha;
	}

	public void setDataAtualSenha(Date dataAtualSenha) {
		this.dataAtualSenha = dataAtualSenha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Roles> getRole() {
		return role;
	}

	public void setRole(List<Roles> role) {
		this.role = role;
	}
	
	public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
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