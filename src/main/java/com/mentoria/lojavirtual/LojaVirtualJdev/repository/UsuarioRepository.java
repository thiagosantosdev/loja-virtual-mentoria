package com.mentoria.lojavirtual.LojaVirtualJdev.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mentoria.lojavirtual.LojaVirtualJdev.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	@Query(value = "select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String login);
	
}
